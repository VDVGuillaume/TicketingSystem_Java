package repository;

import controller.ClientController;

public class DataInitializer {
    public void initialize() {
    
    	ClientController cc = new ClientController();
    	
    	cc.createClient("TestStraat",44 , "Antwerpen", "Belize", 7802, "NV Pol","123456789", "pol@polmail.be", "pol","lepel");

    }
}