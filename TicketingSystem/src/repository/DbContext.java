package repository;

import java.util.List;

import domain.ApplicationUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

public class DbContext {
	private UserDao userDao;
	private ClientDao clientDao;
	private TicketDao ticketDao;
	private UserLoginAttemptDao userLoginAttemptDao;
	
	private ObservableList<ApplicationUser> customers;
	private ObservableList<ApplicationUser> employees;
	
	
	
	public DbContext() {
		userDao = new UserDaoJpa();
		clientDao = new ClientDaoJpa();
		ticketDao = new TicketDaoJpa();
		userLoginAttemptDao = new UserLoginAttemptDaoJpa();
		
		customers = FXCollections.observableArrayList();
		employees = FXCollections.observableArrayList();
	}
	
	public void refreshUsers() {
		var users = userDao.findAll();
		
		customers.clear();
		employees.clear();
		
		for(var user : users) {
			if(user.getUserRoles().contains("CUSTOMER")) {
				customers.add(user);
			}
			
			if(user.getUserRoles().contains("SUPPORTMANAGER") || user.getUserRoles().contains("TECHNICIAN") || user.getUserRoles().contains("ADMINISTRATOR")) {
				employees.add(user);
			}
		}
	}
	
	public void addCustomerUser(ApplicationUser customer) {
		customers.add(customer);
	}
	
	public void addEmployeeUser(ApplicationUser employee) {
		employees.add(employee);
	}
	
	public ObservableList<ApplicationUser> getCustomerUsers() {
		return customers;
	}
	
	public ObservableList<ApplicationUser> getEmployeeUsers() {
		return employees;
	}
}
