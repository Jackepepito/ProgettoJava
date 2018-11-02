package controller;

// MATCH ANALYST

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import DB.Database;
//import model.Utente;
import view.TestApp;

public class LoginController {

    @FXML
    private Separator login;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button registrazione;

    @FXML
    private Label errore;


// Metodo invocato quando l'utente esegue il login
    
@FXML
void login(ActionEvent e) {
	try {
		ResultSet rs = Database.query("SELECT * from Utente where username = '" +username.getText()+ "' AND password = '" +String.valueOf(password.getText())+ "'");
		if (rs.next()){
			try {
				HomePageController controller = new HomePageController();//(new Utente(rs.getString("username")));
				FXMLLoader loader = new FXMLLoader(TestApp.class.getResource("HomePage.fxml"));
				loader.setController(controller);
				ScrollPane registrazione = (ScrollPane) loader.load();
				Scene scene = new Scene(registrazione);
				TestApp.getStage().setScene(scene);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else{
			errore.setVisible(true);
		}	
	}
	catch (SQLException ex) {
		System.out.println("Errore nell' interrogazione al DB");
	}
}

// Metodo che viene invocato quando l'utente effettua la registrazione all'applicazione

@FXML
void registrazione(ActionEvent event) {
	try {
		FXMLLoader loader = new FXMLLoader(TestApp.class.getResource("Registrazione.fxml"));
		ScrollPane registrazione = (ScrollPane) loader.load();
		Scene scene = new Scene(registrazione);
		TestApp.getStage().setScene(scene);
	} catch (IOException e1) {
		e1.printStackTrace();
	}
}



}

