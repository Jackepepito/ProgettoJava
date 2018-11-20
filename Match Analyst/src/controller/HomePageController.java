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
    
    @FXML
    private Label nomecognome;
    
    //@FXML
    //private Button rosa;
    
    //@FMXL
    //private Button 
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
		nomecognome.setText(user.getNome() + " " + user.getCognome());
	}
    
    @FXML
    void nuovaPartita(ActionEvent event)
    {
     	try {
    		NuovaPartitaController controller = new NuovaPartitaController(user, db);
    		FXMLLoader loader = new FXMLLoader(TestApp.class.getResource("NuovaPartita.fxml"));
    		loader.setController(controller);
    		ScrollPane s = (ScrollPane) loader.load();
    		Scene scene = new Scene(s);
    		TestApp.getStage().setScene(scene);
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	}
    }

    @FXML
    void rosa(ActionEvent event) {
    	try {
    		RosaController controller = new RosaController(user, db);
    		FXMLLoader loader = new FXMLLoader(TestApp.class.getResource("Rosa.fxml"));
    		loader.setController(controller);
    		ScrollPane s = (ScrollPane) loader.load();
    		Scene scene = new Scene(s);
    		TestApp.getStage().setScene(scene);
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	}
    }
    
    @FXML
    void calendario(ActionEvent event) {
    	try {
    		CalendarioController controller = new CalendarioController(user, db);
    		FXMLLoader loader = new FXMLLoader(TestApp.class.getResource("Calendario.fxml"));
    		loader.setController(controller);
    		ScrollPane s = (ScrollPane) loader.load();
    		Scene scene = new Scene(s);
    		TestApp.getStage().setScene(scene);
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	}
    }
    @FXML
    void statistiche(ActionEvent event) {
    	try {
    		StatisticheController controller = new StatisticheController(user, db);
    		FXMLLoader loader = new FXMLLoader(TestApp.class.getResource("Statistiche.fxml"));
    		loader.setController(controller);
    		ScrollPane s = (ScrollPane) loader.load();
    		Scene scene = new Scene(s);
    		TestApp.getStage().setScene(scene);
    	} catch (IOException e1) {
    		e1.printStackTrace();
    	}
    }
    

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
