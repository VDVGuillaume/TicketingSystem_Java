package gui;

import controller.DomainController;
import controller.LoginController;
import domain.ApplicationUser;
import domain.Client;
import domain.Ticket;
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
	private TicketListViewController ticketListViewController;
	private SplitPaneViewController splitPaneViewController;
	
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
		
		splitPaneViewController = new SplitPaneViewController(this);
		usersViewController = new UserListViewController(this);
		
		splitPaneViewController.setLeft(usersViewController);
		this.setCenter(splitPaneViewController);
	}
	
	public void openClients() {
		Stage stage = (Stage) this.getScene().getWindow();
		
		stage.setTitle("TicketingSystem - Klanten");
		
		splitPaneViewController = new SplitPaneViewController(this);
		clientListViewController = new ClientListViewController(this);
		
		splitPaneViewController.setLeft(clientListViewController);
		this.setCenter(splitPaneViewController);
	}
	
	public void openTickets() {
		Stage stage = (Stage) this.getScene().getWindow();
		
		stage.setTitle("TicketingSystem - Tickets");
		
		splitPaneViewController = new SplitPaneViewController(this);
		ticketListViewController = new TicketListViewController(this);
		
		splitPaneViewController.setLeft(ticketListViewController);
		this.setCenter(splitPaneViewController);
	}

	public void openSystemUsers() {
		//this.mainViewController.openSystemUsers();
	}
	
	public void openUserDetail() {
		Stage stage = (Stage) this.getScene().getWindow();
		
		stage.setTitle("TicketingSystem - Gebruikers - Nieuw");
		
		userDetailController = new UserDetailViewController(this);
		splitPaneViewController.setRight(userDetailController);
	}
	
	public void openUserDetail(ApplicationUser user) {
		Stage stage = (Stage) this.getScene().getWindow();
		
		stage.setTitle("TicketingSystem - Gebruikers - " + user.getUserName());
		
		userDetailController = new UserDetailViewController(this, user);
		splitPaneViewController.setRight(userDetailController);
	}
	
	public void openClientDetail() {
		Stage stage = (Stage) this.getScene().getWindow();
		
		stage.setTitle("TicketingSystem - Klanten - Nieuw");
		
		var clientDetailController = new ClientDetailViewController(this);
		splitPaneViewController.setRight(clientDetailController);
	}
	
	public void openClientDetail(Client client) {
		Stage stage = (Stage) this.getScene().getWindow();
		
		stage.setTitle("TicketingSystem - Klanten - " + client.getName());
		
		var clientDetailController = new ClientDetailViewController(this, client);
		splitPaneViewController.setRight(clientDetailController);
	}

	public void openTicketDetail() {
		Stage stage = (Stage) this.getScene().getWindow();
		
		stage.setTitle("TicketingSystem - Tickets - Nieuw");
		
		var ticketDetailController = new TicketDetailViewController(this);
		splitPaneViewController.setRight(ticketDetailController);
	}
	
	public void openTicketDetail(Ticket ticket) {
		Stage stage = (Stage) this.getScene().getWindow();
		
		stage.setTitle("TicketingSystem - Tickets - Nieuw");
		
		var ticketDetailController = new TicketDetailViewController(this,ticket);
		splitPaneViewController.setRight(ticketDetailController);
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
