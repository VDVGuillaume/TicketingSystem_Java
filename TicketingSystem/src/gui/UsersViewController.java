package gui;

import Providers.ControllerProvider;
import controller.DomainController;
import controller.LoginController;
import exceptions.ValidationException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UsersViewController extends BaseScreenController {
	
	private MainViewController mainViewController;
	
	public UsersViewController(ControllerProvider provider, MainViewController mainViewController) {
		super("UsersView.fxml", provider);
		this.mainViewController = mainViewController;
	}

	@Override
	protected void loadData() {
		// TODO Auto-generated method stub	
	}
}
