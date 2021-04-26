package gui;

import controller.DomainController;
import controller.LoginController;
import domain.Client;
import exceptions.ValidationException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MainViewController extends BaseScreenController {

	private LoginViewController loginViewController;
	private DashboardViewController dashboardViewController;
	private UserListViewController usersViewController;
	private UserDetailViewController userDetailController;
	private ClientListViewController clientListViewController;
	
	@FXML
	TilePane menu;
	
	public MainViewController() {
		super("mainView.fxml");
	}
	
	public void openLogin() {
		Stage stage = (Stage) this.getScene().getWindow();
		
		stage.setTitle("TicketingSystem - Log in");
		
		if (loginViewController == null)
		{
			loginViewController = new LoginViewController(this);
		}
		
		this.setCenter(loginViewController);
	}
	
	public void openDashboard() {

		Stage stage = (Stage) this.getScene().getWindow();
		
		stage.setTitle("TicketingSystem - Dashboard");
		
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
	
	public void openClients() {
		Stage stage = (Stage) this.getScene().getWindow();
		
		stage.setTitle("TicketingSystem - Klanten");
		
		if (clientListViewController == null)
		{
			clientListViewController = new ClientListViewController(this);
		}
		
		this.setCenter(clientListViewController);
	}

	public void openSystemUsers() {
		//this.mainViewController.openSystemUsers();
	}
	
	public void openUserDetail(String username) {
		Stage stage = (Stage) this.getScene().getWindow();
		
		stage.setTitle("TicketingSystem - Gebruiker - " + username);
		
		if (userDetailController == null)
		{
			userDetailController = new UserDetailViewController(this, username);
		} else {
			userDetailController.reloadData(username);
		}
		
		this.setCenter(userDetailController);
	}
	
	public void openClientDetail() {
		Stage stage = (Stage) this.getScene().getWindow();
		
		stage.setTitle("TicketingSystem - Klant - Nieuw");
		
		var clientDetailController = new ClientDetailViewController(this);
		this.setCenter(clientDetailController);
	}
	
	public void openClientDetail(Client client) {
		Stage stage = (Stage) this.getScene().getWindow();
		
		stage.setTitle("TicketingSystem - Klant - " + client.getName());
		
		var clientDetailController = new ClientDetailViewController(this, client);
		this.setCenter(clientDetailController);
	}

	public void hideMenu() {
		menu.setVisible(false);
	}
	
	public void showMenu() {
		menu.setVisible(true);
	}
	
	@Override
	protected void loadData() {
		// TODO Auto-generated method stub	
	}
}
