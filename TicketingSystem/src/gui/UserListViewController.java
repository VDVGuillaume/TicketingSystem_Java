package gui;

import controller.UserController;
import domain.ApplicationUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.table.TableFilter;
import org.controlsfx.control.table.TableFilter.Builder;

public class UserListViewController extends BaseScreenController {
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
	
	@FXML
	public Button btnUserDetails;
	
	
	private MainViewController mainViewController;
	private UserController userController;
	
	public UserListViewController(MainViewController mainViewController) {
		super("UserListView.fxml");
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
		
		TableFilter.forTableView(tblViewUsers).apply();
		
		tblViewUsers.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	    if (newSelection != null) {
	    	if (tblViewUsers.getSelectionModel().getSelectedItem().getClass().getName() == "domain.ApplicationUser"
	    			&& tblViewUsers.getSelectionModel().getSelectedItem().getUserName() != null) {
	    		btnUserDetails.setDisable(false);
	    	} else {
	    		btnUserDetails.setDisable(true);
	    	}
	    }
	});
	}
	
	@Override
	protected void loadData() {
		var users = userController.getAllUsers();
		tblViewUsers.getItems().clear();
		tblViewUsers.getItems().addAll(users);
	}
	
	public void openUserDetail() {
		this.mainViewController.openUserDetail(tblViewUsers.getSelectionModel().getSelectedItem().getUserName());
	}
}
