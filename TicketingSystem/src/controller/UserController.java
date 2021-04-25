package controller;

import java.util.List;

import Helpers.PasswordHasher;
import Providers.DateProvider;
import domain.ApplicationUser;
import repository.IUserRepository;
import repository.UserRepository;

public class UserController {
	private IUserRepository userRepo;
	
	
	public UserController(IUserRepository userRepo) {		
		this.userRepo = userRepo;
	}	
	
	public UserController() {		
		this(new UserRepository());
	}
	
	public List<ApplicationUser> getAllUsers() {
		return userRepo.getAll();
	}
	
	public List<ApplicationUser> getAllCustomers() {
		return userRepo.getAllCustomers();
	}
	
	public List<ApplicationUser> getAllEmployees() {
		return userRepo.getAllEmployees();
	}
	
	public ApplicationUser getUserByUsername(String username) {
		return userRepo.getUserByUsername(username);
	}
}
