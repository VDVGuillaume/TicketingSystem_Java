package controller;

import java.util.List;

import Helpers.PasswordHasher;
import Providers.DateProvider;
import domain.ApplicationUser;
import domain.ApplicationUserRole;
import domain.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.IUserRepository;
import repository.UserRepository;

public class UserController {
	private IUserRepository userRepo;
	private ObservableList<ApplicationUser> customers;
	private ObservableList<ApplicationUser> employees;
	private List<ApplicationUserRole> userRoles;
	
	public UserController(IUserRepository userRepo) {		
		this.userRepo = userRepo;
		customers = FXCollections.observableArrayList(
				userRepo.getAllCustomers());
		employees = FXCollections.observableArrayList(
				userRepo.getAllEmployees());
		
		userRoles = userRepo.GetAllRoles();
				
	}	
	
	public UserController() {		
		this(new UserRepository());
	}
	
	public List<ApplicationUser> getAllUsers() {
		return userRepo.getAll();
	}
	
	public ObservableList<ApplicationUser> getCustomers() {
	    return FXCollections.unmodifiableObservableList(customers);	
	}
		
	public ObservableList<ApplicationUser> getEmployees() {
	    return FXCollections.unmodifiableObservableList(employees);	
	}
		
	
	public ApplicationUser getUserByUsername(String username) {
		return userRepo.getUserByUsername(username);
	}
	
	public ApplicationUser createCustomer(String email, String firstName, String lastName, String username, String password) {
		String passwordHashed = new PasswordHasher().hashPassword(password);
		
		var user = new ApplicationUser(email, firstName, lastName, username, passwordHashed, Constants.Constants.CUSTOMER_ROLE);
		
		userRepo.createUser(user);
		
		return user;
	}
	
	public ApplicationUser updateUser(ApplicationUser user, String password) {
		if(!password.isEmpty()) {
			String passwordHashed = new PasswordHasher().hashPassword(password);
			user.setPasswordHash(passwordHashed);
		}
		
		userRepo.updateUser(user);
		
		return user;
	}	
}
