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
import model.*;
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
    
    private Database db;

    public LoginController(Database db)
    {
    		this.db = db;
    }
    
@FXML
	void login(ActionEvent e) {
		try {
			ResultSet rs = db.query("SELECT * from Utente where username = '" +username.getText()+ "' AND password = '" +String.valueOf(password.getText())+ "'");
			if (rs.next()){
				try {
				HomePageController controller = new HomePageController(new Utente(rs.getString("username"), db), db);
				FXMLLoader loader = new FXMLLoader(TestApp.class.getResource("HomePage.fxml"));
				loader.setController(controller);
				ScrollPane s = (ScrollPane) loader.load();
				Scene scene = new Scene(s);
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
			RegistrazioneController controller = new RegistrazioneController(db);
			FXMLLoader loader = new FXMLLoader(TestApp.class.getResource("Registrazione.fxml"));
			loader.setController(controller);
			ScrollPane s = (ScrollPane) loader.load();
			Scene scene = new Scene(s);
			TestApp.getStage().setScene(scene);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
}



}

