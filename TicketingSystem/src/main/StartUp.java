package main;

import controller.DomainController;
import controller.LoginController;
import controller.TicketController;
import gui.ConsoleUi;

public class StartUp {
    public static void main(String [] arg) {
        new StartUp().run();
    }

    private void run() {
    	/*
        ConsoleUi console = new ConsoleUi(new TicketController(), new DomainController(), new LoginController());
        console.run();
        */
    }
}