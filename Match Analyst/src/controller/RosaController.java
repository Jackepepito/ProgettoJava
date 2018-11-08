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
    
    // Costruttore
    public RosaController(Database db)
    {
    		this.db = db;
    }
    @FXML
    void logout(ActionEvent event) {
       	try {
       		LoginController controller = new LoginController(db);
    			FXMLLoader loader = new FXMLLoader(TestApp.class.getResource("Login.fxml"));
    			loader.setController(controller);
    			ScrollPane s = (ScrollPane) loader.load();
    			Scene scene = new Scene(s);
    			TestApp.getStage().setScene(scene);
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
    }

}
