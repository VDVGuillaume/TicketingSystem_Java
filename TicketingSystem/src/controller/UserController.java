package controller;

import java.util.List;

import Helpers.PasswordHasher;
import Providers.DateProvider;
import domain.ApplicationUser;
import domain.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.IUserRepository;
import repository.UserRepository;

public class UserController {
	private IUserRepository userRepo;
	private ObservableList<ApplicationUser> customers;
	private ObservableList<ApplicationUser> employees;
	
	public UserController(IUserRepository userRepo) {		
		this.userRepo = userRepo;
		customers = FXCollections.observableArrayList(
				userRepo.getAllCustomers());
		employees = FXCollections.observableArrayList(
				userRepo.getAllEmployees());
				
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
}
