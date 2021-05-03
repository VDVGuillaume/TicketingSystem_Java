package gui;

import java.util.Date;
import org.controlsfx.control.table.TableFilter;
import controller.ContractController;
import domain.Contract;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ContractListViewController extends BaseScreenController {
	
	private MainViewController mainViewController;
	private ContractController contractController;
	
	@FXML
	public  TableView<Contract> contractTableView;
	@FXML
	private TableColumn<Contract,Integer> id;
	@FXML
	private TableColumn<Contract,String> type;
	@FXML
	private TableColumn<Contract,String> status;
	@FXML 
	private TableColumn<Contract,Date> validFrom;
	@FXML 
	private TableColumn<Contract,Date> validTo;
	@FXML
	private TableColumn<Contract,String> client;
	@FXML
	public Button btnCreateContract;
	
	public ContractListViewController(MainViewController mainViewController) {
		super("ContractListView.fxml");
		this.mainViewController = mainViewController;
		this.contractController = new ContractController();
		
		configureTableView();
		loadData();
		TableFilter.forTableView(contractTableView).apply();

		initializeData();
	}
	
	public void createContract() {
		this.mainViewController.openContractDetail();
	}
	
	public void editContract(MouseEvent arg0) {
		if(arg0.getClickCount() > 1)
			this.mainViewController.openContractDetail(contractTableView.getSelectionModel().getSelectedItem());
	}

  protected void configureTableView() {
  	id.setCellValueFactory(new PropertyValueFactory<>("id"));
  	//type.setCellValueFactory(new PropertyValueFactory<>("type"));
  	status.setCellValueFactory(new PropertyValueFactory<>("status"));
  	validFrom.setCellValueFactory(new PropertyValueFactory<>("validFrom"));
  	validTo.setCellValueFactory(new PropertyValueFactory<>("validTo"));
  	client.setCellValueFactory(new PropertyValueFactory<>("client"));
  }
  
  private void initializeData() {		
		int width = getSplitScreenWidth();
		contractTableView.setMinWidth(width);
	}

	@Override
	protected void loadData() {		
		contractTableView.getItems().clear();
		contractTableView.setItems(contractController.getContracts());		
	}
}
