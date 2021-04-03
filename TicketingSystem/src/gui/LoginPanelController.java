package gui;

import domain.ApplicationUser;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.PasswordField;

public class LoginPanelController extends VBox {
	
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField pwdPassword;
	
	public LoginPanelController() {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPanel.fxml"));
    loader.setRoot(this);
    loader.setController(this);
    try {
        loader.load();
    } catch (IOException ex) {
        throw new RuntimeException(ex);
    }
	}
	
	public void login() {
		//TO DO
	}

}
