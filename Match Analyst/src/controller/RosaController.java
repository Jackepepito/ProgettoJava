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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableView<Giocatore> tableview;

    @FXML
    private TableColumn<Giocatore, Integer> colnumero;

    @FXML
    private TableColumn<Giocatore, String> colnome;

    @FXML
    private TableColumn<Giocatore, String> colcognome;

    @FXML
    private TableColumn<Giocatore, String> colruolo;

    @FXML
    private TableColumn<Giocatore, Integer> colgol;

    @FXML
    private TableColumn<Giocatore, Integer> colassist;

    @FXML
    private TableColumn<Giocatore, Integer> colgol_subiti;
    
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
    private ComboBox<Integer> assistinput;

    @FXML
    private ComboBox<Integer> golsubitiinput;

  
    
    @FXML//vedi se è da inserire su scenbuilder
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
		if(numeroinput.getValue().equals(null) || nomeinput.getText().isEmpty() || cognomeinput.getText()==null){
			messaggio.setText("Inserire almeno numero, nome e cognome");
			System.out.println("Inserire almeno numero, nome e cognome");
			cont = false;
		}
		
		if(cont){
			try {
				ResultSet rs;
				rs = Database.query("SELECT * FROM Giocatore U where U.numero = '" +numeroinput.getValue()+ "'");
				Giocatore giocatore= new Giocatore(this.user);
				giocatore.setGiocatoreNumero(numeroinput.getValue());
				giocatore.setGiocatoreNome(nomeinput.getText());
				giocatore.setGiocatoreCognome(cognomeinput.getText());
				giocatore.setGiocatoreRuolo(ruoloinput.getText());
				giocatore.setGiocatoreGol(golinput.getValue());
				giocatore.setGiocatoreAssist(assistinput.getValue());
				giocatore.setGiocatoreGolsubiti(golsubitiinput.getValue());
				tableview.getItems().add(giocatore);
				if (rs.next()){
					messaggio.setText("Numero non disponibile");
				}
				else {
					Database.update("INSERT INTO Giocatore VALUES ('" +numeroinput.getValue()+ "','" + nomeinput.getText()+ "','"
						+cognomeinput.getText()+ "','" + ruoloinput.getText() + "','" +golinput.getValue()+ "','"  +assistinput.getValue()+ "','"  +golsubitiinput.getValue()+ "')");
				
				
				}
			}	
			
			catch (SQLException ex) {
				System.out.println("Errore nell' interrogazione al DB");
			}
		}
			
		messaggio.setVisible(true);
		
}





//aggiungere la tabella del database nella tableview
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nomesquadra.setText(user.getSquadra());
		int i;
		
		for(i=0; i<=99; ++i){
			numeroinput.getItems().add(i);
		}
		
		for(i=0; i<=150; ++i){
			golinput.getItems().add(i);
		}
		for(i=0; i<=150; ++i){
			assistinput.getItems().add(i);
		}
		for(i=0; i<=150; ++i){
			golsubitiinput.getItems().add(i);
		}
		
		
	}
	}