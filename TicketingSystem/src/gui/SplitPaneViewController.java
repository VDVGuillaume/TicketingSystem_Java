package gui;

import controller.DomainController;
import controller.LoginController;
import exceptions.ValidationException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SplitPaneViewController extends BaseScreenController {

	private MainViewController mainViewController;
	private ClientListViewController clientListViewController;
	private ClientDetailViewController clientDetailViewController;
	
	public SplitPaneViewController(MainViewController mainViewController) {
		super("SplitPane.fxml");
		this.mainViewController = mainViewController;
		
		clientListViewController = new ClientListViewController(mainViewController);
		this.setLeft(clientListViewController);
		clientDetailViewController = new ClientDetailViewController(mainViewController);
		this.setRight(clientDetailViewController);
		
	}
	
	

	@Override
	protected void loadData() {
		// TODO Auto-generated method stub	
	}
}
