package controller;

// MATCH ANALYST

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import view.TestApp;
import model.*;
import javafx.fxml.Initializable;

public class HomePageController implements Initializable {

    @FXML
    private Button indietro;
    
    
    private Utente user;
    private Database db;
    
    @FXML
    private Label nomesquadra;
   
    
    public HomePageController(Utente user, Database db)
    {
    		this.user = user;
    		this.db = db;
    }
    
    /**
     * Metodo che viene richiamato a ogni apertura dell'interfaccia che permette di settare le informazioni dell'utente 
     */
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nomesquadra.setText(user.getSquadra());
		
	}
 
    
   // Metodo che permette di tornare alla pagina di login 
    
    @FXML
    void logout(ActionEvent event) {
       	try {
       		LoginController controller = new LoginController(db);
    			FXMLLoader loader = new FXMLLoader(TestApp.class.getResource("Login.fxml"));
    			loader.setController(controller);
    			ScrollPane login = (ScrollPane) loader.load();
    			Scene scene = new Scene(login);
    			TestApp.getStage().setScene(scene);
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
    }
    
}
