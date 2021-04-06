package main;

import Providers.ControllerProvider;
import controller.DomainController;
import controller_interfaces.IDomainController;
import gui.MainViewController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StartUpGuiClientApp extends Application {
	private Stage primaryStage;
	
    @Override
    public void start(Stage stage) {
    	IDomainController domainController = (IDomainController) new DomainController();
    	
    	var provider = new ControllerProvider(domainController);
			primaryStage = stage;
			
			MainViewController root = new MainViewController(provider);
			Scene scene = new Scene(root);
			
			primaryStage.setTitle("TicketingSystem");
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			//TODO icon doesn't work yet
			primaryStage.getIcons().add(new Image("file:../resources/icon.png"));
			primaryStage.show();
			
			root.openLogin();
    }

    public static void main(String... args) {
        Application.launch(StartUpGuiClientApp.class, args);
    }
}
