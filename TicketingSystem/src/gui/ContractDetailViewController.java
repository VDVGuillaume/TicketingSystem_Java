package gui;

import controller.ClientController;
import controller.ContractController;
import controller.UserController;
import domain.Address;
import domain.ApplicationUser;
import domain.Client;
import domain.Contract;
import domain.Contract.ContractStatus;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.sql.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.controlsfx.control.table.TableFilter;
import org.controlsfx.control.table.TableFilter.Builder;

import Constants.Constants;
import Helpers.IntParser;

public class ContractDetailViewController extends BaseScreenController {
	
	@FXML
	private Pane detailPane;
	@FXML
	private VBox detailVBox;
	@FXML
	private GridPane detailGridPane;
	
	@FXML
	private Label lblTitle;
	
	@FXML
	private Spinner<Integer> txtId;
	@FXML
	private TextField txtStatus;
	@FXML
	private DatePicker txtValidFrom;
	@FXML
	private DatePicker txtValidTo;
	@FXML
	private TextField txtClient;
	@FXML
	private TextField txtContractType;
	@FXML
	private DatePicker txtDateCreated;
	@FXML
	private DatePicker txtDateClosed;

	@FXML
	private Button btnSubmit;
	
	private ContractController contractController;
	private Contract contract;
	private WindowState state;
	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
	
	public ContractDetailViewController(MainViewController mainViewController, Contract contract) {
		super("ContractDetailView.fxml");
		this.contract = contract;
		state = WindowState.DETAIL;
		initialize(mainViewController);
	}
	
	
	public ContractDetailViewController(MainViewController mainViewController) {
		super("ContractDetailView.fxml");
		state = WindowState.CREATE;
		initialize(mainViewController);
	}
	
	private void initialize(MainViewController mainViewController) {
		this.mainViewController = mainViewController;
		this.contractController = new ContractController();
		
		/*
		txtId.property().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {
		    	validate();
		    }
		});*/
		
		txtStatus.textProperty().addListener(new ChangeListener<String>() {
	    @Override
	    public void changed(ObservableValue<? extends String> observable,
	            String oldValue, String newValue) {
	    	validate();
	    }
		});
		/*
		txtValidFrom.textProperty().addListener(new ChangeListener<String>() {
	    @Override
	    public void changed(ObservableValue<? extends String> observable,
	            String oldValue, String newValue) {
	    	validate();
	    }
		});
		
		txtValidTo.textProperty().addListener(new ChangeListener<String>() {
	    @Override
	    public void changed(ObservableValue<? extends String> observable,
	            String oldValue, String newValue) {
	    	validate();
	    }
		});
		*/
		txtClient.textProperty().addListener(new ChangeListener<String>() {
	    @Override
	    public void changed(ObservableValue<? extends String> observable,
	            String oldValue, String newValue) {
	    	validate();
	    }
		});
		
		txtContractType.textProperty().addListener(new ChangeListener<String>() {
	    @Override
	    public void changed(ObservableValue<? extends String> observable,
	            String oldValue, String newValue) {
	    	validate();
	    }
		});
		
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
			//txtId.getValueFactory().setValue(contract.getId());
			txtStatus.setText(contract.getStatus().toString());
			txtValidFrom.setValue(LocalDate.ofInstant(contract.getValidFrom().toInstant(), ZoneId.systemDefault()));
			txtValidFrom.setValue(LocalDate.ofInstant(contract.getValidTo().toInstant(), ZoneId.systemDefault()));
			txtClient.setText(contract.getClient().getName());
			txtContractType.setText(contract.getType().getName());
			txtDateCreated.setValue(LocalDate.ofInstant(contract.getDateCreated().toInstant(), ZoneId.systemDefault()));
			txtDateClosed.setValue(LocalDate.ofInstant(contract.getDateClosed().toInstant(), ZoneId.systemDefault()));
			
			lblTitle.setText("Contract details");
		} else {
			lblTitle.setText("Nieuw contract");
		}
		
		if(state == WindowState.DETAIL) {
			// disable controls
			txtId.setDisable(true);
			txtStatus.setDisable(true);
			txtValidFrom.setDisable(true);
			txtValidTo.setDisable(true);
			txtClient.setDisable(true);
			txtContractType.setDisable(true);
			txtDateCreated.setDisable(true);
			txtDateClosed.setDisable(true);
		}else {
			// enable editable controls
			txtId.setDisable(true);
			txtStatus.setDisable(true);
			txtValidFrom.setDisable(false);
			txtValidTo.setDisable(false);
			txtClient.setDisable(true);
			txtContractType.setDisable(true);
			txtDateCreated.setDisable(true);
			txtDateClosed.setDisable(true);
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
		this.mainViewController.openContracts();
	}
	
	public void submit() {
		if(state == WindowState.DETAIL) {
			ChangeToUpdateView();
			return;
		}else {
			if(validate()) {				
				if(state == WindowState.CREATE) {
					// create client
					/*
					this.contract = new Contract(Date.valueOf(txtValidFrom.getValue()), Date.valueOf(txtValidTo.getValue(), ...);
					contractController.createContract(contract);
					*/
				}else if(state == WindowState.UPDATE) {
					// update client
					contract.setValidFrom(Date.valueOf(txtValidFrom.getValue()));
					contract.setValidTo(Date.valueOf(txtValidTo.getValue()));
					contract.setStatus(ContractStatus.valueOf(txtStatus.getText()));
					//ToDo: setClient
					//ToDo: setContractType
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
		return true;
	}
}
