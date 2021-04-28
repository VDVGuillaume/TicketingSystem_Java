package gui;

import controller.UserController;
import domain.ApplicationUser;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.table.TableFilter;
import org.controlsfx.control.table.TableFilter.Builder;

public class UserDetailViewController extends BaseScreenController {
	
	@FXML
	private TextField txtUsername;
	@FXML
	private TextField txtLastName;
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtStatus;
	@FXML
	private TextField txtRole;
	@FXML
	private TextField txtCompany;
	
	private MainViewController mainViewController;
	private UserController userController;
	private String username;
	private ApplicationUser user;
	
	public UserDetailViewController(MainViewController mainViewController, ApplicationUser user) {
		super("UserDetailView.fxml");
		this.mainViewController = mainViewController;
		this.userController = new UserController();
		this.user = user;
		this.username = user.getUserName();
		
		loadData();
		fillData();
	}
	
	@Override
	protected void loadData() {
	}
	
	private void fillData() {
		txtUsername.setText(this.user.getUserName());
		txtLastName.setText(this.user.getLastName());
		txtFirstName.setText(this.user.getFirstName());
		txtEmail.setText(this.user.getEmail());
		txtStatus.setText(this.user.getStatus());
		txtRole.setText(this.user.getRole());
		txtCompany.setText(this.user.getCompany());
	}
	
	public void returnToList() {
		this.mainViewController.openUsers();
	}
}
