package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import view.TestApp;
import model.*;

public class CalendarioController {
	
	private Utente user;
	private Database db;
	
	@FXML
	private Button indietro;
	
	public CalendarioController(Utente user, Database db)
	{
		this.user = user;
		this.db = db;
	}
	
	
	public void indietro(ActionEvent event)
	{
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
