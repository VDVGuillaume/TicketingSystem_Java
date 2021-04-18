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
	private Label lblUsername;
	@FXML
	private Label lblLastName;
	@FXML
	private Label lblFirstName;
	@FXML
	private Label lblEmail;
	@FXML
	private Label lblStatus;
	@FXML
	private Label lblRole;
	@FXML
	private Label lblCompany;
	
	private MainViewController mainViewController;
	private UserController userController;
	private String username;
	private ApplicationUser user;
	
	public UserDetailViewController(MainViewController mainViewController, String username) {
		super("UserDetailView.fxml");
		this.mainViewController = mainViewController;
		this.userController = new UserController();
		this.username = username;
		
		loadData();
		fillData();
	}
	
	@Override
	protected void loadData() {
		this.user = userController.getUserByUsername(this.username);
	}
	
	private void fillData() {
		lblUsername.setText(this.user.getUserName());
		lblLastName.setText(this.user.getLastName());
		lblFirstName.setText(this.user.getFirstName());
		lblEmail.setText(this.user.getEmail());
		lblStatus.setText(this.user.getStatus());
		lblRole.setText(this.user.getRole());
		lblCompany.setText(this.user.getCompany());
	}
}
