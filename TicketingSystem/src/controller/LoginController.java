package controller;

import domain.ApplicationUser;
import domain.Ticket;
import repository.TicketDao;
import repository.UserDao;
import repository.UserDaoJpa;

public class LoginController {

	private UserDao userRepo;
	
	
	public LoginController() {		
		this.userRepo = new UserDaoJpa();
	}


	public ApplicationUser getUserByUserName(String userName) {
			
		  return userRepo.getUserByUserName(userName);
  
	  }
}
