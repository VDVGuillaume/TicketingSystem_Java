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

public class LoginViewController extends BaseScreenController {
	
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField pwdPassword;
	@FXML
	private Text lblValidation;
	
	private LoginController loginController;
	private ControllerProvider provider;
	private MainViewController mainViewController;
	
	public LoginViewController(ControllerProvider provider, MainViewController mainViewController) {
		super("loginView.fxml");
		this.loginController = new LoginController();
		this.provider = provider;
		this.mainViewController = mainViewController;
	}
	
	public void login() {
		try
		{
			this.loginController.login(txtUsername.getText(), pwdPassword.getText());
		}
		catch (ValidationException e)
		{
			lblValidation.setText(e.getMessage());
			return;
		}

		this.mainViewController.openDashboard();
	}

	@Override
	protected void loadData() {
		// TODO Auto-generated method stub	
	}
}