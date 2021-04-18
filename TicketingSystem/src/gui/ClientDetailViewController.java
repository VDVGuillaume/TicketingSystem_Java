package gui;

import controller.ClientController;
import controller.UserController;
import domain.ApplicationUser;
import domain.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.table.TableFilter;
import org.controlsfx.control.table.TableFilter.Builder;

public class ClientDetailViewController extends BaseScreenController {
	
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtAddress;
	@FXML
	private ListView lstTelPhone;
	@FXML
	private Button btnTelPhoneRemove;
	@FXML
	private Button btnTelPhoneAdd;
	@FXML
	private TextField txtTelPhone;
	@FXML
	private Button btnSubmit;

	private ClientController clientController;
	private Client client;
	private boolean isCreateAction;
	
	public ClientDetailViewController(MainViewController mainViewController, Client client) {
		this(mainViewController, false);
		this.client = client;
	}
	
	
	public ClientDetailViewController(MainViewController mainViewController, boolean isCreateAction) {
		super("ClientDetailView.fxml");
		this.isCreateAction = isCreateAction;
		this.clientController = new ClientController();
		this.mainViewController = mainViewController;
	}
	
	@Override
	protected void loadData() {
	}
	
	private void fillData() {
	}
	
	public void returnToList() {
		this.mainViewController.openClients();
	}
}
