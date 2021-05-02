package gui;

import controller.UserController;
import domain.Address;
import domain.ApplicationUser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import org.controlsfx.control.table.TableFilter;
import org.controlsfx.control.table.TableFilter.Builder;

import Constants.Constants;

public class UserDetailViewController extends BaseScreenController {
	
	@FXML
	private Pane detailPane;
	@FXML
	private VBox detailVBox;
	@FXML
	private GridPane detailGridPane;
	
	@FXML
	private Label lblTitle;
	
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
	
	@FXML
	private Button btnSubmit;
	
	private MainViewController mainViewController;
	private UserController userController;
	private String username;
	private ApplicationUser user;
	private WindowState state;
	
	public UserDetailViewController(MainViewController mainViewController, ApplicationUser user) {
		super("UserDetailView.fxml");
		this.mainViewController = mainViewController;
		this.userController = new UserController();
		this.user = user;
		this.username = user.getUserName();
		this.state = WindowState.DETAIL;
		
		initializeData();
	}
	
	public UserDetailViewController(MainViewController mainViewController) {
			super("UserDetailView.fxml");
			this.mainViewController = mainViewController;
			this.userController = new UserController();
			state = WindowState.CREATE;
			
			initializeData();
	}
	
	private void initializeData() {
		
		// set submit button text
		var btnSubmitText = 
				  state == WindowState.DETAIL ? Constants.btnSubmitEdit 
				: state == WindowState.UPDATE ? Constants.btnSubmitConfirm
				: Constants.btnSubmitCreate;
		btnSubmit.setText(btnSubmitText);
		
		
		if(state == WindowState.DETAIL || state == WindowState.UPDATE) {
			// init data
			lblTitle.setText("Gebruiker details");
			txtUsername.setText(this.user.getUserName());
			txtLastName.setText(this.user.getLastName());
			txtFirstName.setText(this.user.getFirstName());
			txtEmail.setText(this.user.getEmail());
			txtStatus.setText(this.user.getStatus());
			txtRole.setText(this.user.getRole());
			txtCompany.setText(this.user.getCompany());
		} else {
			lblTitle.setText("Nieuwe gebruiker");
		}
		
		if(state == WindowState.DETAIL) {
			// disable controls
			txtUsername.setDisable(true);
			txtLastName.setDisable(true);
			txtFirstName.setDisable(true);
			txtEmail.setDisable(true);
			txtStatus.setDisable(true);
			txtRole.setDisable(true);
			txtCompany.setDisable(true);
		} else {
			// enable controls
			txtUsername.setDisable(false);
			txtLastName.setDisable(false);
			txtFirstName.setDisable(false);
			txtEmail.setDisable(false);
			txtStatus.setDisable(false);
			txtRole.setDisable(false);
			txtCompany.setDisable(false);
		}
		
		int width = getSplitScreenWidth();
		detailPane.setMinWidth(width);
		detailVBox.setMinWidth(width);
		detailGridPane.setMinWidth(width);
	}
	
	public void submit() {
		if(state == WindowState.DETAIL) {
			ChangeToUpdateView();
			return;
		} else {
			if(validate()) {
				if(state == WindowState.CREATE) {
					// create client
				} else if(state == WindowState.UPDATE) {
					// update client
				}
				ChangeToDetailView();
				return;
			}
		}
	}
	
	private void ChangeToUpdateView() {
		state = WindowState.UPDATE;
		initializeData();
	}
	
	private void ChangeToDetailView() {
		state = WindowState.DETAIL;
		initializeData();
	}
	
	private boolean validate() {
		// TODO
		return false;
	}
	
	@Override
	protected void loadData() {
	}
	
	public void returnToList() {
		this.setVisible(false);
	}
}
