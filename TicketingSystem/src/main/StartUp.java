package main;

import java.util.Arrays;

import controller.ClientController;
import controller.DomainController;
import controller.LoginController;
import controller.TicketController;
import gui.ConsoleUi;
import repository.TicketDaoJpa;
import repository.UserDaoJpa;
import repository.UserLoginAttemptDaoJpa;
import repository.UserRepository;

public class StartUp {
    public static void main(String [] arg) {
        new StartUp().run();
    }

    private void run() {    	
        ConsoleUi console = new ConsoleUi(new TicketController(), new DomainController(), new LoginController(),new ClientController());
        console.run();
    }
}