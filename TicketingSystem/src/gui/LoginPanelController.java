package gui;

import controller.DomainController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class LoginPanelController extends BaseScreenController {
	
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField pwdPassword;
	
	public LoginPanelController(DomainController domainController) {
		super(domainController, "LoginPanel.fxml");
	}
	
	public void login() {
		//TO DO
	}

	@Override
	protected void loadData() {
		// TODO Auto-generated method stub	
	}
}
