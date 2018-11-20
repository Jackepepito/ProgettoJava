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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
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
	    private LineChart<String, Integer> grafico1;

	    @FXML
	    private CategoryAxis x;

	    @FXML
	    private NumberAxis y;
	    
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
	    	
	    	
	    		System.out.println("ciao");
	    		x.setLabel("Avversario");
	    		y.setLabel("Gol");
	    		XYChart.Series<String, Integer> series1 = new XYChart.Series<String, Integer>();
	        	series1.setName("Gol Fatti");
	        	ResultSet rs = db.query("SELECT gol_segnati,avversario FROM partita ");
	        	try {
	    			while (rs.next()){
	    				System.out.println("ciao");
	    				int sum = rs.getInt("gol_segnati");
	    				String avversario = rs.getString("avversario");
	    				series1.getData().add(new XYChart.Data<String, Integer>(avversario, sum));				
	    			}
	    		}  catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	        	grafico1.getData().add(series1);
	            
	    	
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			
			ObservableList<String> types = FXCollections.observableArrayList
				    ( "Andamento generale", "Andamento in casa", "Andamento in trasferta","Fase offensiva","Fase difensiva" );

				andamenti.setItems( types );
				
				 
		}
		
		
}
