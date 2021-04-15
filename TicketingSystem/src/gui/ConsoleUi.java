package gui;

import domain.ApplicationUser;
import domain.Client;
import domain.Ticket;
import java.util.Scanner;

import controller.ClientController;
import controller.DomainController;
import controller.LoginController;
import controller.TicketController;

public class ConsoleUi {
    private final TicketController ticketController;
    private final DomainController domainController;
    private final LoginController loginController;
    private final ClientController clientController;
    private final Scanner in = new Scanner(System.in);
    
    public ConsoleUi(TicketController tc, DomainController dc, LoginController lc,ClientController cc) {
       ticketController = tc;
	   domainController =  dc;
	   loginController = lc;
	   clientController = cc;
	  }

    public void run() {
        doStandardJob();
        domainController.close();
    }

    private void doStandardJob() {
    	
    	Client client = clientController.getClientById(4);
    	System.out.println(client.getName());
    	client.setName("Andr�");
    	clientController.updateClient(client);
    	
    	Client client1 = clientController.getClientById(4);
    	System.out.println(client1.getName());
    	
    }  
    
    
}