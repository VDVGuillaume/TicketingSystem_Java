package gui;

import domain.DomainController;
import domain.Ticket;
import persistence.SQLDatabaseConnection;

import java.sql.ResultSet;
import java.util.Scanner;

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
