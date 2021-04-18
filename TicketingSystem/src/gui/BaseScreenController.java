package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class BaseScreenController extends BorderPane{	
	
	protected String resource;
	protected MainViewController mainViewController;
	
	protected BaseScreenController(String resource)  {
		this.resource = resource;
		FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
		loader.setController(this);
		loader.setRoot(this);

		try {
			loader.load();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	protected Stage getStage() {
    return (Stage) this.getScene().getWindow();
	}
	
	protected void openDialogWindow(BaseScreenController viewController, String title, double sceneWidth, double sceneHeight) {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(viewController, sceneWidth, sceneHeight));
        stage.show();
	}

	// method to override with child implementation of this class
	protected abstract void loadData();	
}