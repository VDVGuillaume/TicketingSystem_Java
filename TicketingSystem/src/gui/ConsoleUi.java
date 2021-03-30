package gui;

import domain.Ticket;
import java.util.Scanner;

import controller.DomainController;

public class ConsoleUi {
    private final DomainController domainController;
    private final Scanner in = new Scanner(System.in);
    
    public ConsoleUi(DomainController dc) {
       domainController = dc;
    }

    public void run() {
        doStandardJob();
        domainController.close();
    }

    private void doStandardJob() {
    	
    	Ticket ticket = domainController.getTicketByNr(1);
    	
    	System.out.println(ticket);

    }
    
    
    
    
    
}
