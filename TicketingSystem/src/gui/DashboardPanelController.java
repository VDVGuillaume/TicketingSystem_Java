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

public class DashboardPanelController extends BaseScreenController {

	private ControllerProvider _provider;
	
	public DashboardPanelController(ControllerProvider provider) {
		super("DashboardPanel.fxml");
		_provider = provider;
	}
	
	private void openDashboard() {
		Stage stage = getStage();
		DashboardPanelController root = new DashboardPanelController(_provider);
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
