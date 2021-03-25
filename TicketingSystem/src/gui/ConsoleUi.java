package gui;

import domain.DomainController;
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
    	
    	System.out.print(domainController.getTicketByNr(1));

    }
    
    
    
    
    
}
