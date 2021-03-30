package gui;

import domain.ApplicationUser;
import domain.Ticket;
import java.util.Scanner;

import controller.DomainController;
import controller.LoginController;
import controller.TicketController;

public class ConsoleUi {
    private final TicketController ticketController;
    private final DomainController domainController;
    private final LoginController loginController;
    private final Scanner in = new Scanner(System.in);
    
    public ConsoleUi(TicketController tc, DomainController dc, LoginController lc) {
       ticketController = tc;
	   domainController =  dc;
	   loginController = lc;
	  }

    public void run() {
        doStandardJob();
        domainController.close();
    }

    private void doStandardJob() {
    	
    	Ticket ticket = ticketController.getTicketByNr(1);
    	ApplicationUser user =  loginController.getUserByUserName("customer");
    	
    	System.out.printf("Ticket : %s%nUser:%s",ticket,user);

    }
    
    
    
    
    
}
