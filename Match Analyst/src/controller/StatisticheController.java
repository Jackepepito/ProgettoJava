package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import view.TestApp;
import model.*;


public class StatisticheController implements Initializable{
	
	
	@FXML
	 private Database db;
	
	 private Utente user;
	 
	    @FXML
	    private Button indietro;


	    @FXML
	    private Button mostra;
	    
	    @FXML
	    private ComboBox<String> andamenti;

	 
	    @FXML
	    private LineChart<?, ?> grafico1;

	    
	    
	    public StatisticheController(Utente user,Database db)
	    {
	    		this.db = db;
	    		this.user=user;
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
	    

	    @FXML
	    void mostra(ActionEvent event) {
//metodi per creare i grafici
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			ObservableList<String> types = FXCollections.observableArrayList
				    ( "Andamento generale", "Andamento in casa", "Andamento in trasferta","Fase offensiva","Fase difensiva" );

				andamenti.setItems( types );
				
				 
		}
		
		
}
