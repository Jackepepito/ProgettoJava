package controller;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import view.TestApp;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.*;


//va instanziato il rosacontroller gestito da un certo utente
public class RosaController implements Initializable{

    @FXML
   
    private Database db;

    @FXML
    private Button indietro;
    
    @FXML
    private Button aggiungi;
    
    @FXML
    private Label nomesquadra;
    
    
    
    @FXML
    private ComboBox<Integer> numeroinput;
    
    @FXML
    private TextField nomeinput;

    @FXML
    private TextField cognomeinput;

    @FXML
    private TextField ruoloinput;

    @FXML
    private ComboBox<Integer> golinput;


    @FXML
    private GridPane griglia;
  
    
    @FXML
    private Label messaggio;
    
    private Utente user;

    // Costruttore
    public RosaController(Utente user,Database db)
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
    
    
    //manca il controllo sull'utente, dato che ogni utente gestisce la sua squadra
    //manca il controllo infatti che la squadra del giocatore debba essere la stessa dell'user
	@FXML
	    void aggiungi(ActionEvent event) {
		boolean cont = true;
		if(numeroinput.getValue().equals(null) || nomeinput.getText()==null || cognomeinput.getText()==null){
			messaggio.setText("Inserire almeno numero, nome e cognome");
			System.out.println(numeroinput.getValue().toString());
			System.out.println(nomeinput.getText());
			System.out.println(cognomeinput.getText());
			System.out.println("Inserire almeno numero, nome e cognome");
			cont = false;
		}
		
		if(cont){
			try {
				ResultSet rs;
				rs = db.query("SELECT * FROM giocatore U where U.numero = '" +numeroinput.getValue()+ "'");
				
				
				if (rs.next()){
					messaggio.setText("Numero non disponibile");
				}
				else {
					System.out.println("Ok");
					db.update("INSERT INTO Giocatore VALUES ('" +numeroinput.getValue()+ "','" + nomeinput.getText()+ "','"
						+cognomeinput.getText()+ "','" + ruoloinput.getText() + "','" +golinput.getValue()+ "', 0, 0)");
				
					  //vedere come inizializzare nuovamente la pagina..
					
				
				
				
				}
			}	
			
			catch (SQLException ex) {
				System.out.println("Errore nell' interrogazione al DB");
			}
		}
			
		messaggio.setVisible(true);
		
}



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nomesquadra.setText(user.getSquadra());
		int i;
		//inserire controllo sui numeri disponibili
		for(i=0; i<=99; ++i){
			numeroinput.getItems().add(i);
		}
		
		for(i=0; i<=150; ++i){
			golinput.getItems().add(i);
		}
		
		i=2;
        ResultSet rs = db.query("Select numero,nome,cognome,ruolo,gol from giocatore");
		try {
			
			while(rs.next()) {
				
				Label port4 = new Label();
	    		port4.setText(new Integer(rs.getInt("numero")).toString());
	    		GridPane.setConstraints(port4, 0, i);
	    		griglia.getChildren().add(port4);
	    		
				Label port = new Label();
	    		port.setText(rs.getString("nome").toString());
	    		GridPane.setConstraints(port, 1, i);
	    		griglia.getChildren().add(port);
	    		
	    		Label port1 = new Label();
	    		port1.setText(rs.getString("cognome").toString());
	    		GridPane.setConstraints(port1, 2, i);
	    		griglia.getChildren().add(port1);
	    		
	    		Label port2 = new Label();
	    		port2.setText(rs.getString("ruolo").toString());
	    		GridPane.setConstraints(port2, 3, i);
	    		griglia.getChildren().add(port2);
	    		
	    		Label port3 = new Label();
	    		port3.setText(new Integer(rs.getInt("gol")).toString());
	    		GridPane.setConstraints(port3, 4, i);
	    		griglia.getChildren().add(port3);
	    		
	    		++i;}
		} catch (SQLException e) {
			e.printStackTrace();
				
	
	}
		}}