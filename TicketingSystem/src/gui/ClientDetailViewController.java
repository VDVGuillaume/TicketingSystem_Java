package gui;

import controller.ClientController;
import controller.UserController;
import domain.Address;
import domain.ApplicationUser;
import domain.Client;
import domain.Contact;
import domain.Contract;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

import org.controlsfx.control.table.TableFilter;
import org.controlsfx.control.table.TableFilter.Builder;

import Constants.Constants;
import Helpers.IntParser;

public class ClientDetailViewController extends BaseScreenController {
	
	@FXML
	private Pane detailPane;
	@FXML
	private VBox detailVBox;
	@FXML
	private GridPane detailGridPane;
	
	@FXML
	private Label lblTitle;
	
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
	private ListView<String> lstTelPhone;
	@FXML
	private Button btnTelPhoneRemove;
	@FXML
	private Button btnTelPhoneAdd;
	@FXML
	private TextField txtTelPhone;
	
	@FXML
	private ListView<Contact> lstContact;
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
	
	public ClientDetailViewController(MainViewController mainViewController, ClientController clientController, Client client) {
		super("ClientDetailView.fxml");
		this.client = client;
		state = WindowState.DETAIL;
		initialize(mainViewController, clientController);
	}
	
	
	public ClientDetailViewController(MainViewController mainViewController, ClientController clientController) {
		super("ClientDetailView.fxml");
		state = WindowState.CREATE;
		initialize(mainViewController, clientController);
	}
	
	private void initialize(MainViewController mainViewController, ClientController clientController) {
		this.mainViewController = mainViewController;
		this.clientController = clientController;
		
		txtTelPhone.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {
		    	setAddTelPhoneDisabled();
		    }
		});
		
		txtContactEmail.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {
		    	setAddContactDisabled();
		    }
		});
		
		txtContactFirstName.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {
		    	setAddContactDisabled();
		    }
		});
		
		txtContactName.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {
		    	setAddContactDisabled();
		    }
		});
		
		txtCity.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {
		    	validate();
		    	
		    }
		});
		
		txtCountry.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {
		    	validate();
		    }
		});
		
		txtName.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {
		    	validate();
		    }
		});
		
		txtPostalCode.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {
		    	if(IntParser.parseIntOrNull(newValue) == null) {
		    		newValue = oldValue;
		    		return;
		    	}
		    	
		    	validate();
		    }
		});
		
		txtStreet.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {
		    	validate();
		    }
		});
		
		txtStreetNumber.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {
		    	if(IntParser.parseIntOrNull(newValue) == null) {
		    		newValue = oldValue;
		    		return;
		    	}
		    		
		    	validate();
		    }
		});
		
		/* How to set display value in ListView:
		lstContact.setCellFactory(param -> new ListCell<Contact>() {
		    @Override
		    protected void updateItem(Contact item, boolean empty) {
		        super.updateItem(item, empty);

		        if (empty || item == null || item.getWord() == null) {
		            setText(null);
		        } else {
		            setText();
		        }
		    }
		});
		*/
		
		initializeData();
	}
	
	private void initializeData() {
		// clear some data
		
		
		// disable sub data buttons
		btnContactAdd.setDisable(true);
		btnContactRemove.setDisable(true);
		btnTelPhoneAdd.setDisable(true);
		btnTelPhoneRemove.setDisable(true);
		
		
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
			
			var contacts = FXCollections.observableArrayList(client.getContacts());
			lstContact.getItems().clear();
			lstContact.setItems(contacts);
			
			var telephoneNumber = FXCollections.observableArrayList(client.getTelephoneNumbers());
			lstTelPhone.getItems().clear();
			lstTelPhone.setItems(telephoneNumber);
			
			lblTitle.setText("Klant details");
		} else {
			lblTitle.setText("Nieuwe klant");
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
			txtContactEmail.setDisable(true);
			txtContactFirstName.setDisable(true);
			txtContactName.setDisable(true);
			txtTelPhone.setDisable(true);
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
			txtContactEmail.setDisable(false);
			txtContactFirstName.setDisable(false);
			txtContactName.setDisable(false);
			txtTelPhone.setDisable(false);
		}
		
		int width = getSplitScreenWidth();
		detailPane.setMinWidth(width);
		detailVBox.setMinWidth(width);
		detailGridPane.setMinWidth(width);
	}
	
	@Override
	protected void loadData() {
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
				List<String> telephoneNumbers = lstTelPhone.getItems();
				List<Contact> contacts = lstContact.getItems();
				
				if(state == WindowState.CREATE) {
					// create client
					this.client = new Client(txtName.getText(), txtStreet.getText(), Integer.parseInt(txtStreetNumber.getText()), txtCity.getText(), txtCountry.getText(), Integer.parseInt(txtPostalCode.getText()), telephoneNumbers, contacts);
					
					clientController.createClient(
							client
							);
				}else if(state == WindowState.UPDATE) {
					// update client
					client.setName(txtName.getText());
					client.getAddress().setCity(txtCity.getText());
					client.getAddress().setCountry(txtCountry.getText());
					client.getAddress().setHouseNumber(Integer.parseInt(txtStreetNumber.getText()));
					client.getAddress().setPostalCode(Integer.parseInt(txtPostalCode.getText()));
					client.getAddress().setStreet(txtStreet.getText());
					client.setContacts(contacts);
					client.setTelephoneNumbers(telephoneNumbers);
					
					clientController.updateClient(client);
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
		if(state == WindowState.DETAIL) {
			btnSubmit.setDisable(false);
			return false;
		}
		
		
		boolean disableButton =
				txtCity.getText().isEmpty() ||
				txtCountry.getText().isEmpty() ||
				txtName.getText().isEmpty() ||
				txtPostalCode.getText().isEmpty() ||
				txtStreet.getText().isEmpty() ||
				txtStreetNumber.getText().isEmpty() ||
				lstContact.getItems().size() == 0 ||
				lstTelPhone.getItems().size() == 0
				;
		
		btnSubmit.setDisable(disableButton);
		
		return !disableButton;
	}
	
	public void setAddTelPhoneDisabled() {
		if(txtTelPhone.getText().isEmpty()) {
			btnTelPhoneAdd.setDisable(true);
			return;
		}
		
		btnTelPhoneAdd.setDisable(false);
	}
	
	public void setAddContactDisabled() {
		if(txtContactEmail.getText().isEmpty() || txtContactFirstName.getText().isEmpty() || txtContactName.getText().isEmpty()) {
			btnContactAdd.setDisable(true);
			return;
		}
		
		if(!Contact.emailCheck(txtContactEmail.getText())) {
			btnContactAdd.setDisable(true);
			return;
		}
		
		btnContactAdd.setDisable(false);
	}
	
	@FXML
	public void lstContactClicked() {
		var item = lstContact.getSelectionModel().getSelectedItem();
		
		if(item == null) {
			btnContactRemove.setDisable(true);
			return;
		}
		
		btnContactRemove.setDisable(false);
	}
	
	@FXML
	public void lstTelPhoneClicked() {
		var item = lstTelPhone.getSelectionModel().getSelectedItem();
		
		if(item == null) {
			btnTelPhoneRemove.setDisable(true);
			return;
		}
		
		btnTelPhoneRemove.setDisable(false);
	}
	
	@FXML
	public void removeTelPhone() {
		var item = lstTelPhone.getSelectionModel().getSelectedItem();
		
		if(item == null) {
			return;
		}
		
		lstTelPhone.getItems().remove(item);
		lstTelPhoneClicked();
		validate();
	}
	
	@FXML
	public void addTelPhone() {
		if(txtTelPhone.getText().isEmpty()) {
			btnTelPhoneAdd.setDisable(true);
			return;
		}
		
		lstTelPhone.getItems().add(txtTelPhone.getText());
		txtTelPhone.setText("");
		setAddTelPhoneDisabled();
		validate();
	}
	
	@FXML
	public void removeContact() {
		var item = lstContact.getSelectionModel().getSelectedItem();
		
		if(item == null) {
			return;
		}
		
		lstContact.getItems().remove(item);
		lstContactClicked();		
		validate();

	}
	
	@FXML
	public void addContact() {
		Contact contact = new Contact(txtContactEmail.getText(), txtContactFirstName.getText(), txtContactName.getText());
		
		lstContact.getItems().add(contact);
		
		txtContactEmail.setText("");
		txtContactFirstName.setText("");
		txtContactName.setText("");
		setAddContactDisabled();
		validate();
	}
}
