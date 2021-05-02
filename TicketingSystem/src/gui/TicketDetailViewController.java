package gui;

import java.util.Date;

import org.controlsfx.control.table.TableFilter;

import Constants.Constants;
import controller.TicketController;
import domain.Address;
import domain.Ticket;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class TicketDetailViewController extends BaseScreenController {
	
	
	@FXML
	private TextField txtNr;
	@FXML
	private TextField txtTitle;
	@FXML
	private TextField txtStatus;
	@FXML
	private TextField txtType;
	@FXML
	private TextField txtDateAdded;
	@FXML
	private TextField txtDateClosed;
	@FXML
	private TextField txtDescription;
	@FXML
	private TextField txtClient;
	@FXML
	private TextField txtAssignedEngineer;
	
	
	@FXML
	private ListView comments;
	@FXML
	private Button btnCommentRemove;
	@FXML
	private Button btnCommentAdd;
	@FXML
	private TextField txtComment;
	@FXML
	private TextField txtCommentDate;
	@FXML
	private Button btnSubmit;

	
	private TicketController ticketController;
	private Ticket ticket;
	private WindowState state;	
	
	public TicketDetailViewController(MainViewController mainViewController, Ticket ticket) {
		super("TicketDetailView.fxml");
		this.ticket = ticket;
		state = WindowState.DETAIL;
		initialize(mainViewController);
	}
	
	
	public TicketDetailViewController(MainViewController mainViewController) {
		super("TicketDetailView.fxml");
		state = WindowState.CREATE;
		initialize(mainViewController);
	}
	
	private void initialize(MainViewController mainViewController) {
		this.mainViewController = mainViewController;
		this.ticketController = new TicketController();
		
		initializeData();
	}
	
	private void initializeData() {
		
		// set submit button text
		var btnSubmitText = 
				  state == WindowState.DETAIL ? Constants.btnSubmitEdit 
				: state == WindowState.UPDATE ? Constants.btnSubmitConfirm
				: Constants.btnSubmitCreate;
		btnSubmit.setText(btnSubmitText);	
		
		
		
			
			// init data
			txtTitle.setText(ticket.getTitle());
			txtDescription.setText(ticket.getDescription());
			txtClient.setText(ticket.getClient().getName());			
			txtStatus.setText(ticket.getStatus().name());
			txtType.setText(ticket.getType().getName());
			txtDateClosed.setText(ticket.getDateClosed().toString());
			txtDateAdded.setText(ticket.getDateAdded().toString());
			
			if(ticket.getAssignedEngineer() == null) {
				txtAssignedEngineer.setText("Niet Toegewezen");
			}else {
				txtAssignedEngineer.setText(ticket.getAssignedEngineer().getUserName());
			}
			
			
		
		
		if(state == WindowState.DETAIL) {
			// disable controls
			txtTitle.setDisable(true);
			txtDescription.setDisable(true);
			txtClient.setDisable(true);
			txtAssignedEngineer.setDisable(true);
			txtStatus.setDisable(true);
			txtType.setDisable(true);
			txtDateClosed.setDisable(true);
			txtDateAdded.setDisable(true);
			
		}else {
			// enable controls
			txtTitle.setDisable(false);
			txtDescription.setDisable(false);
			txtClient.setDisable(false);
			txtAssignedEngineer.setDisable(false);
			txtStatus.setDisable(false);
			txtType.setDisable(false);
			txtDateClosed.setDisable(false);
			txtDateAdded.setDisable(false);
		
		}
	}
	
	@Override
	protected void loadData() {
	}
	
	private void fillData() {
	}
	
	public void returnToList() {
		this.mainViewController.openTickets();
	}
	
	public void submit() {
		if(state == WindowState.DETAIL) {
			ChangeToUpdateView();
			return;
		}else {
			if(validate()) {
				if(state == WindowState.CREATE) {
					// create ticket
				}else if(state == WindowState.UPDATE) {
					// update ticket
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
