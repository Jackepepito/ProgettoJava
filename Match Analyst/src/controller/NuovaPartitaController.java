package controller;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import model.*;
import view.TestApp;

public class NuovaPartitaController implements Initializable {
	
	
	private Utente user;
	private Database db;
	
    @FXML
    private Button indietro;

    @FXML
    private TextField avversario;

    @FXML
    private ComboBox<Integer> gol_segnati;

    @FXML
    private ComboBox<Integer> gol_subiti;

    @FXML
    private TextField marcatori;

    @FXML
    private ComboBox<Integer> possesso_palla;

    @FXML
    private ComboBox<Integer> tiri_tot;

    @FXML
    private ComboBox<Integer> tiri_porta;

    @FXML
    private ComboBox<Integer> falli_commessi;

    @FXML
    private ComboBox<Integer> falli_subiti;

    @FXML
    private ComboBox<Integer> parate;
	
    @FXML
    private Label messaggio;
    
	public NuovaPartitaController(Utente user, Database db)
	{
		this.user = user;
		this.db = db;
	}
	
	// _________________
	public void initialize(URL arg0, ResourceBundle arg1) {
		int i;
		
		for(i=0; i<=20; ++i){
			gol_segnati.getItems().add(i);
		}
		
		for(i=0; i<=20; ++i){
			gol_subiti.getItems().add(i);
		}
		
		for(i=0; i<=100; ++i){
			possesso_palla.getItems().add(i);
		}
		
		for(i=0; i<=150; ++i){
			tiri_tot.getItems().add(i);
		}
		
		for(i=0; i<=150; ++i){
			tiri_porta.getItems().add(i);
		}
		
		for(i=0; i<=150; ++i){
			falli_subiti.getItems().add(i);
		}
		
		for(i=0; i<=150; ++i){
			falli_commessi.getItems().add(i);
		}
		
		for(i=0; i<=150; ++i){
			parate.getItems().add(i);
		}
	}
	
	//____
	@FXML
    void indietro(ActionEvent event) {
       	try {
       		HomePageController controller = new HomePageController(user, db);
    			FXMLLoader loader = new FXMLLoader(TestApp.class.getResource("HomePage.fxml"));
    			loader.setController(controller);
    			ScrollPane s = (ScrollPane) loader.load();
    			Scene scene = new Scene(s);
    			TestApp.getStage().setScene(scene);
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
    }
	
	@FXML
    void aggiungi(ActionEvent event) {

    	boolean cont = true;
		if(avversario.getText().isEmpty() || gol_segnati.getItems().isEmpty() || gol_subiti.getItems().isEmpty() || marcatori.getText().isEmpty() || possesso_palla.getItems().isEmpty() 
				|| tiri_tot.getItems().isEmpty() || tiri_porta.getItems().isEmpty() || falli_commessi.getItems().isEmpty() 
				|| falli_subiti.getItems().isEmpty() || parate.getItems().isEmpty()){
			messaggio.setText("Riempire i campi obbligatori");
			System.out.println("Riempire i campi obbligatori");
			cont = false;
		}
		
		if(cont) {
			Database.update("INSERT INTO partita VALUES ('" +avversario.getText()+ "','" +gol_segnati.getValue()+ "','"
					+gol_subiti.getValue()+ "','" +marcatori.getText()+ "', " +possesso_palla.getValue()+ "','"  
					+tiri_tot.getValue()+ "', '" +tiri_porta.getValue()+"', '" +falli_commessi.getValue()+"','"
					+falli_subiti.getValue()+ "', '"+parate.getValue()+"')");
			System.out.println("Inserimento avvenuto con successo!");
			
	}

    }
	
	@FXML
	void add_marcatore(ActionEvent event)
	{
		
	}
}


