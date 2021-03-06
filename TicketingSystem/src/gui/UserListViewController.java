package gui;

import controller.UserController;
import domain.ApplicationUser;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;

import org.controlsfx.control.table.TableFilter;
import org.controlsfx.control.table.TableFilter.Builder;

import Constants.Constants;

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
	public Button btnCreateUser;
	
	
	private MainViewController mainViewController;
	private UserController userController;
	
	public UserListViewController(MainViewController mainViewController, ObservableList<ApplicationUser> users) {
		super("UserListView.fxml");
		this.mainViewController = mainViewController;
		this.userController = new UserController();
		
		tblColUsername.setCellValueFactory(new PropertyValueFactory<>("userName"));
		tblColFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		tblColName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		tblColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		tblColStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
		tblColCompany.setCellValueFactory(new PropertyValueFactory<>("company"));

		loadData(users);
		
		TableFilter.forTableView(tblViewUsers).apply();
		
		initializeData();
	}
	

	protected void loadData(ObservableList<ApplicationUser> users) {
		tblViewUsers.getItems().clear();
		tblViewUsers.setItems(users);
	}
	
	@Override
	protected void loadData() {
	}
	
	private void initializeData() {		
		int width = getSplitScreenWidth();
		tblViewUsers.setMinWidth(width);
	}
	
	public void editClient(MouseEvent arg0) {
		if(arg0.getClickCount() > 1)
			this.mainViewController.openUserDetail(tblViewUsers.getSelectionModel().getSelectedItem());		
	}
	
	public void createUser() {
		this.mainViewController.openUserDetail();
	}
	
	public void openUserDetail() {
		this.mainViewController.openUserDetail(tblViewUsers.getSelectionModel().getSelectedItem());
	}
}
