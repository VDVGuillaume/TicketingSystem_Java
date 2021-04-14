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
import validators.RoleValidator;

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
		ApplicationUser user = null;
		try
		{
			user = userRepo.getUserByUsername(username);
			
			if(user == null) {
				throw new ValidationException(Constants.ERROR_LOGIN_USER_NOT_FOUND);
			}
		}
		catch (Exception ex)
		{
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
			user.setAccessFailedCount(user.getAccessFailedCount() + 1);
			userRepo.updateAccessFailedCount(user);
			throw new ValidationException(Constants.ERROR_LOGIN_FAILED);
		}
		
		//validate user roles
		if(!RoleValidator.ValidateRolesAllowedInApplication(user)) {
			throw new ValidationException(Constants.ERROR_LOGIN_NO_VALID_ROLE);
		}
		
		// login succeeded!
		user.setAccessFailedCount(0);
		userRepo.updateAccessFailedCount(user);
	}
	
	
}
