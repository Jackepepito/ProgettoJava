package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import model.*;
import view.TestApp;

public class NuovaPartitaController implements Initializable {
	
	
	private Utente user;
	private Database db;
	
	@FXML
	private Button indietro;
	
	public NuovaPartitaController(Utente user, Database db)
	{
		this.user = user;
		this.db = db;
	}
	
	// _________________
	public void initialize(URL arg0, ResourceBundle arg1) {
		int i;
		
    //	sesso.getItems().addAll("uomo", "donna");
		
		for(i=50; i<=300; ++i){
			//altezza.getItems().add(i);
		}
		
		for(i=20; i<=500; ++i){
			//peso.getItems().add(i);
		}
		
		//attivita.getItems().addAll("Leggera", "Moderata", "Pesante");
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
	
	
	
}
