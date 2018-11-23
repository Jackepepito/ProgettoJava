package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// MATCH ANALYST

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
    
    // ____

    @FXML
    private GridPane nuova_partita;
    
    @FXML
    private GridPane griglia;
  

  
   

    @FXML
    private Label possesso_palla;

    @FXML
    private Label tiri_tot;

    @FXML
    private Label tiri_porta;

    @FXML
    private Label falli_commessi;

    @FXML
    private Label falli_subiti;

    @FXML
    private Label parate;
    
    @FXML
    private Label avversario;

    @FXML
    private TextField avversario1;

    @FXML
    private ComboBox<Integer> gol_segnati1;

    @FXML
    private ComboBox<Integer> gol_subiti1;

    @FXML
    private TextField marcatori1;

    @FXML
    private ComboBox<Integer> possesso_palla1;

    @FXML
    private ComboBox<Integer> tiri_tot1;

    @FXML
    private ComboBox<Integer> tiri_porta1;

    @FXML
    private ComboBox<Integer> falli_commessi1;

    @FXML
    private ComboBox<Integer> falli_subiti1;

    @FXML
    private ComboBox<Integer> parate1;
    
    @FXML
    private Button aggiungi;
    
    @FXML
    private Label messaggio;
    
    @FXML
    private Button add;
    

    @FXML
    private LineChart<String, Integer> grafico;

 

    @FXML
    private ComboBox<String> grafici;
    
    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
    
    @FXML
    private GridPane visualizza_partita;
    
    
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
		
		nuova_partita.setVisible(false);
		aggiungi.setVisible(false);
		add.setVisible(false);
		
	int i;
		
		for(i=0; i<=20; ++i){
			gol_segnati1.getItems().add(i);
		}
		
		for(i=0; i<=20; ++i){
			gol_subiti1.getItems().add(i);
		}
		
		for(i=0; i<=100; ++i){
			possesso_palla1.getItems().add(i);
		}
		
		for(i=0; i<=150; ++i){
			tiri_tot1.getItems().add(i);
		}
		
		for(i=0; i<=150; ++i){
			tiri_porta1.getItems().add(i);
		}
		
		for(i=0; i<=150; ++i){
			falli_subiti1.getItems().add(i);
		}
		
		for(i=0; i<=150; ++i){
			falli_commessi1.getItems().add(i);
		}
		
		for(i=0; i<=150; ++i){
			parate1.getItems().add(i);
		}
		
		//girone.getItems().add("A");
		//girone.getItems().add("R");
		ObservableList<String> types = FXCollections.observableArrayList
			    ( "Andamento generale", "Andamento in casa", "Andamento in trasferta","Fase offensiva","Fase difensiva" );

			grafici.setItems( types );
			
			x.setLabel("Avversario");
		    
    		y.setLabel("Gol");
    		XYChart.Series<String, Integer> series1 = new XYChart.Series<String, Integer>();
        	series1.setName("Gol Fatti");
        	ResultSet rs = db.query("SELECT gol_segnati,avversario FROM partita ");
        	try {
    			while (rs.next()){
    				int sum = rs.getInt("gol_segnati");
    				String avversario = rs.getString("avversario");
    				series1.getData().add(new XYChart.Data<String, Integer>(avversario, sum));				
    			}
    		}  catch (SQLException e) {
    			e.printStackTrace();
    		}
        	grafico.getData().add(series1);
	
             System.out.println("ciccio");
             i=0;
        	 ResultSet rs1 = db.query("Select avversario,gol_segnati, gol_subiti,possesso_palla from partita");
     		try {
     			
     			while(rs.next()) {
     				
     				Button port4 = new Button();
     				port4.setPrefWidth(350);
     				Label port3 = new Label();
     	    		port3.setText((user.getSquadra().toString().concat("      ").concat(new Integer(rs1.getInt("gol_segnati")).toString()).concat("  ").concat(new Integer(rs1.getInt("gol_subiti")).toString()).concat("      ").concat(rs1.getString("avversario"))));
     	    		port4.setText(port3.getText().toString());
     	    		GridPane.setConstraints(port4, 0, i);
     	    		griglia.getChildren().add(port4);
     	    		
     				if(i==0) {
     					
     					Label avversario1 = new Label();
     					avversario1.setText(rs1.getString("avversario").toString());
     					GridPane.setConstraints(avversario1, 1, 0);
     					visualizza_partita.getChildren().add(avversario1);
     					
     					Label golsegnati = new Label();
     					golsegnati.setText(new Integer(rs1.getInt("gol_segnati")).toString());
     					GridPane.setConstraints(golsegnati, 1, 1);
     					visualizza_partita.getChildren().add(golsegnati);
     					
     				
     					Label golsubiti = new Label();
     					golsegnati.setText(new Integer(rs1.getInt("gol_subiti")).toString());
     					GridPane.setConstraints(golsubiti, 1, 2);
     					visualizza_partita.getChildren().add(golsubiti);
     					
     					Label possessopalla = new Label();
     					possessopalla.setText(new Integer(rs1.getInt("possesso_palla")).toString());
     					GridPane.setConstraints(possessopalla, 1, 4);
     					visualizza_partita.getChildren().add(possessopalla);
     					
     				}
     	    		++i;}
     		} catch (SQLException e) {
     			e.printStackTrace();
     				
     	
     			
     	}
    
    }
    

    
    @FXML
    void aggiungi_partita(ActionEvent event) {

    	boolean cont = true;
    	//manca girone
		if(avversario.getText().isEmpty() ||  gol_segnati1.getItems().isEmpty() || gol_subiti1.getItems().isEmpty() || marcatori1.getText().isEmpty() || possesso_palla1.getItems().isEmpty() 
				|| tiri_tot1.getItems().isEmpty() || tiri_porta1.getItems().isEmpty() || falli_commessi1.getItems().isEmpty() 
				|| falli_subiti1.getItems().isEmpty() || parate1.getItems().isEmpty()){
			messaggio.setText("Riempire i campi obbligatori");
			System.out.println("Riempire i campi obbligatori");
			cont = false;
		}
		
		if(cont) {
			db.update("INSERT INTO partita VALUES ('" +avversario.getText()+ "', '" +gol_segnati1.getValue()+ "','"
					+gol_subiti1.getValue()+ "','" +marcatori1.getText()+ "', '" +possesso_palla1.getValue()+ "','"  
					+tiri_tot1.getValue()+ "', '" +tiri_porta1.getValue()+"', '" +falli_commessi1.getValue()+"','"
					+falli_subiti1.getValue()+ "', '"+parate1.getValue()+"')");
			System.out.println("Inserimento avvenuto con successo!");
			
	}

    }

    @FXML
    void combo_visibile(ActionEvent event)
    {
    nuova_partita.setVisible(true);
    aggiungi.setVisible(true);
    add.setVisible(true);
    }
    
    @FXML
    void aggiungi_marcatore(ActionEvent event)
    {
    		
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
