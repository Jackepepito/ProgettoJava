package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import model.*;
import view.TestApp;

public class RegistrazioneController {

    @FXML
    private TextField nome;

    @FXML
    private TextField cognome;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private TextField squadra;

    @FXML
    private SplitMenuButton stagione;

    @FXML
    private Button registrazione;

    @FXML
    private Button indietro;
    
    @FXML
    private Label messaggio;
    
    private Database db;
    	
    public RegistrazioneController(Database db)
    {
    		this.db = db;
    }
    @FXML
    void indietro(ActionEvent event) {
    	try {
    			FXMLLoader loader = new FXMLLoader(TestApp.class.getResource("Login.fxml")); // Istanzio una nuova pagina fxml, in questo caso login
    			LoginController controller = new LoginController(db); // Istanzio un controller che andrà a controllare la mia pagina
			loader.setController(controller); // associo il controller alla pagina, in questo modo la pagina è controllata dalla classe LoginController
			ScrollPane s = (ScrollPane) loader.load(); // Carico la pagina, che in questo momento si apre
			Scene scene = new Scene(s); // In questo modo visualizzo graficamente lo scrollpane
			TestApp.getStage().setScene(scene);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }

    @FXML
    void registrazione(ActionEvent event) {

    	boolean cont = true;
		if(username.getText().isEmpty() || password.getText().isEmpty() || stagione.getText()==null){
			messaggio.setText("Riempire i campi obbligatori");
			System.out.println("Riempire i campi obbligatori");
			cont = false;
		}
		
		if(cont){
			try {
				ResultSet rs;
				rs = Database.query("SELECT * FROM Utente U where U.username = '" +username.getText()+ "'");
				if (rs.next()){
					messaggio.setText("Username non valido");
				}
				else {
					Database.update("INSERT INTO Utente VALUES ('" +username.getText()+ "','" + password.getText()+ "','"
						+nome.getText()+ "','" + cognome.getText() + "','" +squadra.getText()+ "','"  +stagione.getText()+ "')");
				}	
			}
			catch (SQLException ex) {
				System.out.println("Errore nell' interrogazione al DB");
			}
		}
		messaggio.setVisible(true);
    }
   
}

