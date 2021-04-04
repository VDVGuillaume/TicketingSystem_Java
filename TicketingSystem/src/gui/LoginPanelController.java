package gui;

import Providers.ControllerProvider;
import controller.DomainController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class LoginPanelController extends BaseScreenController {
	
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField pwdPassword;
	
	public LoginPanelController(ControllerProvider provider) {
		// todo extract necessary controllers from provider(implement into provider if necessary).
		super("LoginPanel.fxml");
	}
	
	public void login() {
		//TO DO
	}

	@Override
	protected void loadData() {
		// TODO Auto-generated method stub	
	}
}
