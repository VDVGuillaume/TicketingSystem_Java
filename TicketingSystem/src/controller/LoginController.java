package controller;

import Constants.Constants;
import Helpers.PasswordHasher;
import Providers.DateProvider;
import domain.ApplicationUser;
import domain.Ticket;
import exceptions.ValidationException;
import repository.IUserRepository;
import repository.TicketDao;
import repository.UserDao;
import repository.UserDaoJpa;
import repository.UserRepository;

public class LoginController {

	private IUserRepository userRepo;
	private DateProvider dateProvider;
	private PasswordHasher passwordHasher;
	
	
	public LoginController(IUserRepository userRepo, DateProvider dateProvider) {		
		this.userRepo = userRepo;
		this.dateProvider = dateProvider;
		this.passwordHasher = new PasswordHasher();
	}	
	
	public LoginController() {		
		this(new UserRepository(), new DateProvider());
	}
	
	public void login(String username, String password) {
		var user = userRepo.getUserByUsername(username);
		
		if(user == null) {
			throw new ValidationException(Constants.ERROR_LOGIN_USER_NOT_FOUND);
		}
		
		var date = dateProvider.getCurrentDate();
		if(user.getAccessFailedCount() > 4) {
			userRepo.createUserLoginAttempt(date, username, false);
			throw new ValidationException(Constants.ERROR_LOGIN_USER_LOCKED_OUT);
		}
		
		var loginSucceeded = passwordHasher.verifyHashedPassword(user.getPasswordHash(), password);
		userRepo.createUserLoginAttempt(date, username, loginSucceeded);
		
		if(!loginSucceeded) {
			userRepo.increaseAccessFailedCount(user.getId(), 1);
			throw new ValidationException(Constants.ERROR_LOGIN_FAILED);
		}
		
		// login succeeded!
	}
}
