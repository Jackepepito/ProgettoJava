package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import view.TestApp;
import model.*;

public class CalendarioController implements Initializable {
	
	private Utente user;
	private Database db;
	
	@FXML
	private Button indietro;
	
    @FXML
    private ComboBox<String> partite;
    
    @FXML
    private Button scegli;
    
	public CalendarioController(Utente user, Database db)
	{
		this.user = user;
		this.db = db;
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) {
	//ResultSet rs = Database.query("SELECT count(partita.avversario) from partita");
	
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
	
	@FXML
	public void scegli(ActionEvent event)
	{
		
	}
}
