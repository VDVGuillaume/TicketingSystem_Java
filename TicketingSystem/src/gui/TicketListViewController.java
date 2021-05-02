package gui;

import java.util.Date;

import org.controlsfx.control.table.TableFilter;

import controller.TicketController;
import domain.Address;
import domain.ApplicationUser;
import domain.Client;
import domain.Ticket;
import domain.TicketStatus;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class TicketListViewController extends BaseScreenController {

	
	private MainViewController mainViewController;
	private TicketController ticketController;
	
		
	@FXML
	public  TableView<Ticket> ticketTableView;
	@FXML 
	private TableColumn<Ticket,Integer> ticketnr;
	@FXML 
	private TableColumn<Ticket,String> title;
	@FXML 
	private TableColumn<Ticket,TicketStatus> status;
	@FXML 
	private TableColumn<Ticket,Date> dateAdded;
	@FXML 
	private TableColumn<Ticket,Date> dateClosed;
	@FXML 
	private TableColumn<Ticket,Client> client;
	@FXML 
	private TableColumn<Ticket,ApplicationUser> assignedEngineer;
	
	@FXML
	public Button btnCreateTicket;
	
	public TicketListViewController( MainViewController mainViewController) {
		super("TicketListView.fxml");
		this.mainViewController = mainViewController;
		this.ticketController = new TicketController();
		
		configureTableView();
		loadData();
		TableFilter.forTableView(ticketTableView).apply();

	}
	
	public void createTicket() {
		this.mainViewController.openTicketDetail();
	}
	
	public void editTicket(MouseEvent arg0) {
		if(arg0.getClickCount() > 1)
			this.mainViewController.openTicketDetail(ticketTableView.getSelectionModel().getSelectedItem());		
	}

  protected void configureTableView() {
  	ticketnr.setCellValueFactory(new PropertyValueFactory<>("ticketnr"));
  	title.setCellValueFactory(new PropertyValueFactory<>("title"));
  	status.setCellValueFactory(new PropertyValueFactory<>("status"));
  	dateAdded.setCellValueFactory(new PropertyValueFactory<>("dateAdded"));
  	dateClosed.setCellValueFactory(new PropertyValueFactory<>("dateClosed"));
  	client.setCellValueFactory(new PropertyValueFactory<>("client"));
  	assignedEngineer.setCellValueFactory(new PropertyValueFactory<>("assignedEngineer"));
  }

	@Override
	protected void loadData() {		
		ticketTableView.getItems().clear();
		ticketTableView.setItems(ticketController.getTickets());		
	}
	
	
	
	
	
	
	

}
