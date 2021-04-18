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

public class DashboardViewController extends BaseScreenController {

	private MainViewController mainViewController;
	
	public DashboardViewController(MainViewController mainViewController) {
		super("DashboardView.fxml");
		this.mainViewController = mainViewController;
	}
	
	

	@Override
	protected void loadData() {
		// TODO Auto-generated method stub	
	}
}
