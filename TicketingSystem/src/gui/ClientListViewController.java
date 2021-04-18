package gui;



import java.util.Date;

import org.controlsfx.control.table.TableFilter;

import controller.ClientController;
import domain.Address;
import domain.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ClientListViewController extends BaseScreenController {
	
	private MainViewController mainViewController;
	private ClientController clientController;
	
	private ObservableList<Client> clientList;

	
	@FXML
	public  TableView<Client> clientTableView;
	@FXML 
	private TableColumn<Client,String> name;
	@FXML 
	private TableColumn<Client,Date> dateCreated;
	@FXML 
	private TableColumn<Client,Integer> id;
	@FXML 
	private TableColumn<Client,Address> address;
	@FXML
	public Button btnCreateClient;
	
	public ClientListViewController( MainViewController mainViewController) {
		super("ClientListView.fxml");
		this.mainViewController = mainViewController;
		this.clientController = new ClientController();
		
		configureTableView();
		loadData();
		TableFilter.forTableView(clientTableView).apply();

	}
	
	public void createClient() {
		this.mainViewController.openClientDetail();
	}
	
	public void editClient(MouseEvent arg0) {
		if(arg0.getClickCount() > 1)
			this.mainViewController.openClientDetail(clientTableView.getSelectionModel().getSelectedItem());		
	}

    protected void configureTableView() {
    	
    	id.setCellValueFactory(new PropertyValueFactory<>("id"));
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	dateCreated.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

	@Override
	protected void loadData() {
		var clients = clientController.getClients();
		clientTableView.getItems().clear();
		clientTableView.getItems().addAll(clients);				
	}
}
