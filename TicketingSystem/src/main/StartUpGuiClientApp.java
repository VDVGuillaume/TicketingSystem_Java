package main;

import controller.DomainController;
import gui.MainViewController;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import repository.DataInitializer;

public class StartUpGuiClientApp extends Application {
	private Stage primaryStage;
	
    @Override
    public void start(Stage stage) {
   			primaryStage = stage;
			
			MainViewController root = new MainViewController();
			Scene scene = new Scene(root);
			
			primaryStage.setTitle("TicketingSystem");
			
			Screen screen = Screen.getPrimary();
			Rectangle2D bounds = screen.getVisualBounds();
			primaryStage.setX(bounds.getMinX());
			primaryStage.setY(bounds.getMinY());
			primaryStage.setWidth(bounds.getWidth());
			primaryStage.setHeight(bounds.getHeight());
			
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			//Add icons in 2 sizes. JavaFX chooses correct size for different uses.
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/favicon16x16.png")));
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/favicon32x32.png")));
			primaryStage.show();
			
			root.openLogin();
    }

    public static void main(String... args) {
        Application.launch(StartUpGuiClientApp.class, args);        
    }
}
