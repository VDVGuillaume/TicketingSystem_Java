package main;

import controller.DomainController;
import controller.LoginController;
import controller.TicketController;
import gui.ConsoleUi;
import repository.UserLoginAttemptDaoJpa;
import repository.UserRepository;

public class StartUp {
    public static void main(String [] arg) {
        new StartUp().run();
    }

    private void run() {
    	var repository = new UserRepository();
    	
    	var user = repository.getUserByUsername("supportmanager");
    	
    	var stophere = true;
    	/*
        ConsoleUi console = new ConsoleUi(new TicketController(), new DomainController(), new LoginController());
        console.run();
        */
    }
}