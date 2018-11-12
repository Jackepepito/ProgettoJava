package controller;

import javafx.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import view.TestApp;
import model.*;

public class RosaController {

    @FXML
    private Button indietro;
    private Database db;
    private Utente user;
    
    // Costruttore
    public RosaController(Utente user, Database db)
    {
    		this.db = db;
    		this.user = user;
    }
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
