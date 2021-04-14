package gui;

import controller.UserController;
import domain.ApplicationUser;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UsersViewController extends BaseScreenController {
	@FXML
	public TableView<ApplicationUser> tblViewUsers;
	
	@FXML
	public TableColumn tblColUsername;
	@FXML
	public TableColumn tblColName;
	@FXML
	public TableColumn tblColFirstName;
	@FXML
	public TableColumn tblColEmail;
	@FXML
	public TableColumn tblColStatus;
	@FXML
	public TableColumn tblColRole;
	@FXML
	public TableColumn tblColCompany;
	
	
	private MainViewController mainViewController;
	private UserController userController;
	
	public UsersViewController(MainViewController mainViewController) {
		super("UsersView.fxml");
		this.mainViewController = mainViewController;
		this.userController = new UserController();
		
		tblColUsername.setCellValueFactory(new PropertyValueFactory<>("userName"));
		tblColFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		tblColName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		tblColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		tblColStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		tblColRole.setCellValueFactory(new PropertyValueFactory<>("role"));
		tblColCompany.setCellValueFactory(new PropertyValueFactory<>("company"));

		loadData();
	}
	
	@Override
	protected void loadData() {
		var users = userController.getAllUsers();
		tblViewUsers.getItems().clear();
		tblViewUsers.getItems().addAll(users);
	}
}
