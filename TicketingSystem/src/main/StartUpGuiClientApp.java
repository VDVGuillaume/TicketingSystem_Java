package main;

import Providers.ControllerProvider;
import controller.DomainController;
import controller_interfaces.IDomainController;
import gui.LoginPanelController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StartUpGuiClientApp extends Application {
	private Stage primaryStage;
	
    @Override
    public void start(Stage stage) {
    	IDomainController domainController = (IDomainController) new DomainController();
    	
    	var provider = new ControllerProvider(domainController);
    	primaryStage = stage;
    	
    	LoginPanelController root = new LoginPanelController(provider);
		Scene scene = new Scene(root);

		primaryStage.setTitle("TicketingSystem");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    public static void main(String... args) {
        Application.launch(StartUpGuiClientApp.class, args);
    }
}
