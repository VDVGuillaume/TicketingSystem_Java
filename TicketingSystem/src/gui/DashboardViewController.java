package gui;

import Providers.ControllerProvider;
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

	private ControllerProvider _provider;
	
	public DashboardViewController(ControllerProvider provider) {
		super("dashboardView.fxml");
		_provider = provider;
	}
	
	private void openDashboard() {
		Stage stage = getStage();
		DashboardViewController root = new DashboardViewController(_provider);
		Scene scene = new Scene(root);
		
		stage.setTitle("TicketingSystem - Dashboard");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	protected void loadData() {
		// TODO Auto-generated method stub	
	}
}
