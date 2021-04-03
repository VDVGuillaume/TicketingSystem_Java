package main;

import controller.DomainController;
import gui.LoginPanelController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StartUpGuiClientApp extends Application {
	private Stage primaryStage;
	
    @Override
    public void start(Stage stage) {
    	var domainController = new DomainController();
    	primaryStage = stage;
    	
    	LoginPanelController root = new LoginPanelController(domainController);
		Scene scene = new Scene(root);

		primaryStage.setTitle("Sokoban");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    public static void main(String... args) {
        Application.launch(StartUpGuiClientApp.class, args);
    }
}
