package main;

import controller.DomainController;
import gui.ConsoleUi;

public class StartUp {
    public static void main(String [] arg) {
        new StartUp().run();
    }

    private void run() {
        new ConsoleUi(new DomainController()).run();
        
    }
    
}