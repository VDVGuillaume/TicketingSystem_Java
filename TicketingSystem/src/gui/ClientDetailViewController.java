package gui;

import controller.ClientController;
import controller.UserController;
import domain.Address;
import domain.ApplicationUser;
import domain.Client;
import domain.Contract;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

import org.controlsfx.control.table.TableFilter;
import org.controlsfx.control.table.TableFilter.Builder;

import Constants.Constants;

public class ClientDetailViewController extends BaseScreenController {
	
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtCountry;
	@FXML
	private TextField txtCity;
	@FXML
	private TextField txtPostalCode;
	@FXML
	private TextField txtStreet;
	@FXML
	private TextField txtStreetNumber;
	
	@FXML
	private ListView lstTelPhone;
	@FXML
	private Button btnTelPhoneRemove;
	@FXML
	private Button btnTelPhoneAdd;
	@FXML
	private TextField txtTelPhone;
	
	@FXML
	private ListView lstContact;
	@FXML
	private TextField txtContactFirstName;
	@FXML
	private TextField txtContactName;
	@FXML
	private TextField txtContactEmail;
	@FXML
	private Button btnContactRemove;
	@FXML
	private Button btnContactAdd;
	@FXML
	private Button btnSubmit;
	
	private ClientController clientController;
	private Client client;
	private WindowState state;	
	
	public ClientDetailViewController(MainViewController mainViewController, Client client) {
		super("ClientDetailView.fxml");
		this.client = client;
		state = WindowState.DETAIL;
		initialize(mainViewController);
	}
	
	
	public ClientDetailViewController(MainViewController mainViewController) {
		super("ClientDetailView.fxml");
		state = WindowState.CREATE;
		initialize(mainViewController);
	}
	
	private void initialize(MainViewController mainViewController) {
		this.mainViewController = mainViewController;
		this.clientController = new ClientController();
		
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
			if(client.getAddress() == null) {
				client.setAddress(new Address());
			}
			
			// init data
			txtName.setText(client.getName());
			txtCountry.setText(client.getAddress().getCountry());
			txtCity.setText(client.getAddress().getCity());
			txtPostalCode.setText("" + client.getAddress().getPostalCode());
			txtStreet.setText(client.getAddress().getStreet());
			txtStreetNumber.setText("" + client.getAddress().getHouseNumber());
		}
		
		if(state == WindowState.DETAIL) {
			// disable controls
			txtName.setDisable(true);
			txtCountry.setDisable(true);
			txtCity.setDisable(true);
			txtPostalCode.setDisable(true);
			txtStreet.setDisable(true);
			txtStreetNumber.setDisable(true);
			lstContact.setDisable(true);
			lstTelPhone.setDisable(true);
			btnContactAdd.setDisable(true);
			btnContactRemove.setDisable(true);
			btnTelPhoneAdd.setDisable(true);
			btnTelPhoneRemove.setDisable(true);
		}else {
			// enable controls
			txtName.setDisable(false);
			txtCountry.setDisable(false);
			txtCity.setDisable(false);
			txtPostalCode.setDisable(false);
			txtStreet.setDisable(false);
			txtStreetNumber.setDisable(false);
			lstContact.setDisable(false);
			lstTelPhone.setDisable(false);
			btnContactAdd.setDisable(false);
			btnContactRemove.setDisable(false);
			btnTelPhoneAdd.setDisable(false);
			btnTelPhoneRemove.setDisable(false);
		}
	}
	
	@Override
	protected void loadData() {
	}
	
	private void fillData() {
	}
	
	public void returnToList() {
		this.mainViewController.openClients();
	}
	
	public void submit() {
		if(state == WindowState.DETAIL) {
			ChangeToUpdateView();
			return;
		}else {
			if(validate()) {
				if(state == WindowState.CREATE) {
					// create client
				}else if(state == WindowState.UPDATE) {
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
}
