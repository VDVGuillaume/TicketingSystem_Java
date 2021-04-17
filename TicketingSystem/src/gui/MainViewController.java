package gui;

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

	private LoginViewController loginViewController;
	private DashboardViewController dashboardViewController;
	private UserListViewController usersViewController;
	private ClientListViewController clientListViewController;
	
	public MainViewController() {
		super("mainView.fxml");
	}
	
	public void openLogin() {
		Stage stage = (Stage) this.getScene().getWindow();
		
		stage.setTitle("TicketingSystem - Log in");
		stage.setHeight(600);
		
		if (loginViewController == null)
		{
			loginViewController = new LoginViewController(this);
		}
		
		this.setCenter(loginViewController);
	}
	
	public void openDashboard() {

		Stage stage = (Stage) this.getScene().getWindow();
		
		stage.setTitle("TicketingSystem - Dashboard");
		stage.setHeight(900);
		
		if (dashboardViewController == null)
		{
			dashboardViewController = new DashboardViewController(this);
		}
		
		this.setCenter(dashboardViewController);
	}
	
	public void openUsers() {

		Stage stage = (Stage) this.getScene().getWindow();
		
		stage.setTitle("TicketingSystem - Gebruikers");
		
		if (usersViewController == null)
		{
			usersViewController = new UserListViewController(this);
		}
		
		this.setCenter(usersViewController);
	}
	
	public void openClientList() {
		Stage stage = (Stage) this.getScene().getWindow();
		
		stage.setTitle("TicketingSystem - Klanten");
		
		if (clientListViewController == null)
		{
			clientListViewController = new ClientListViewController(this);
		}
		
		this.setCenter(clientListViewController);
	}

	@Override
	protected void loadData() {
		// TODO Auto-generated method stub	
	}
}
