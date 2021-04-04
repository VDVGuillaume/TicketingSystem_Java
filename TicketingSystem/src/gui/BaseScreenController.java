package gui;

import java.io.IOException;

import Providers.ControllerProvider;
import controller.DomainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public abstract class BaseScreenController extends BorderPane{
	protected BaseScreenController(String resource)  {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
		loader.setController(this);
		loader.setRoot(this);

		try {
			loader.load();
			loadData();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	// method to override with child implementation of this class
	protected abstract void loadData();	
}