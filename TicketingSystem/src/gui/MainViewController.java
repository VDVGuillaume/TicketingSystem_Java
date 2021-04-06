package gui;

import Providers.ControllerProvider;
import controller.DomainController;
import controller.LoginController;
import exceptions.ValidationException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainViewController extends BaseScreenController {

	private ControllerProvider _provider;
	private LoginViewController loginViewController;
	private DashboardViewController dashboardViewController;
	
	public MainViewController(ControllerProvider provider) {
		super("mainView.fxml");
		_provider = provider;
	}
	
	public void openLogin() {
		Stage stage = (Stage) this.getScene().getWindow();
		
		stage.setTitle("TicketingSystem - Log in");
		stage.setHeight(600);
		
		if (loginViewController == null)
		{
			loginViewController = new LoginViewController(_provider, this);
		}
		
		this.setCenter(loginViewController);
	}
	
	public void openDashboard() {

		Stage stage = (Stage) this.getScene().getWindow();
		
		stage.setTitle("TicketingSystem - Dashboard");
		stage.setHeight(900);
		
		if (dashboardViewController == null)
		{
			dashboardViewController = new DashboardViewController(_provider);
		}
		
		this.setCenter(dashboardViewController);
	}

	@Override
	protected void loadData() {
		// TODO Auto-generated method stub	
	}
}
