package controller;

// MATCH ANALYST

import javafx.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import view.TestApp;
import model.*;

public class HomePageController {

    @FXML
    private Button indietro;
    
    private Utente user;
    private Database db;
    
    public HomePageController(Utente user, Database db)
    {
    		this.user = user;
    		this.db = db;
    }
    
   // Metodo che permette di tornare alla pagina di login 
    
    @FXML
    void logout(ActionEvent event) {
       	try {
    			FXMLLoader loader = new FXMLLoader(TestApp.class.getResource("Login.fxml"));
    			ScrollPane login = (ScrollPane) loader.load();
    			Scene scene = new Scene(login);
    			TestApp.getStage().setScene(scene);
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
    }

}
