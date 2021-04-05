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

public class LoginPanelController extends BaseScreenController {
	
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField pwdPassword;
	@FXML
	private Text lblValidation;
	
	private LoginController _lc;
	private ControllerProvider _provider;
	
	public LoginPanelController(ControllerProvider provider) {
		super("LoginPanel.fxml");
		_lc = new LoginController();
		_provider = provider;
	}
	
	public void login() {
		try
		{
			_lc.login(txtUsername.getText(), pwdPassword.getText());
		}
		catch (ValidationException e)
		{
			lblValidation.setText(e.getMessage());
			return;
		}

		openDashboard();
	}
	
	private void openDashboard() {
		Stage stage = getStage();
		DashboardPanelController root = new DashboardPanelController(_provider);
		Scene scene = new Scene(root);
		
		stage.setTitle("TicketingSystem - Dashboard");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	protected void loadData() {
		// TODO Auto-generated method stub	
	}
}
