package gui;

import Providers.ControllerProvider;
import controller.DomainController;
import controller.LoginController;
import exceptions.ValidationException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;

public class LoginPanelController extends BaseScreenController {
	
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField pwdPassword;
	@FXML
	private Text lblValidation;
	
	private LoginController _lc;
	
	public LoginPanelController(ControllerProvider provider) {
		super("LoginPanel.fxml");
		_lc = new LoginController();
	}
	
	public void login() {
		try
		{
			_lc.login(txtUsername.getText(), pwdPassword.getText());
		}
		catch (ValidationException e)
		{
			lblValidation.setText(e.getMessage());
		}
	}

	@Override
	protected void loadData() {
		// TODO Auto-generated method stub	
	}
}
