package controller;

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
import javafx.scene.Node;
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
public class RosaController implements Initializable {

	@FXML

	private Database db;

	@FXML
	private Button indietro;

	@FXML
	private Button aggiungi;

    @FXML
    private Button elimina;
    
	@FXML
	private Label nomesquadra;

	@FXML
	private ComboBox<Integer> numeroinput;

	@FXML
	private TextField nomeinput;

	@FXML
	private TextField ruoloinput;

	@FXML
	private GridPane griglia;

    @FXML
    private GridPane GrigliaMarcatori;
    
    @FXML
    private ComboBox<String> sceltaruolo;
    
    @FXML
    private ComboBox<String> sceltagiocatore;

    
	@FXML
	private Label messaggio;

	private Utente user;

	// Costruttore
	public RosaController(Utente user, Database db) {
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

	// manca il controllo sull'utente, dato che ogni utente gestisce la sua squadra
	// manca il controllo infatti che la squadra del giocatore debba essere la
	// stessa dell'user
	@FXML
	void aggiungi(ActionEvent event) {
		boolean cont = true;
		if (numeroinput.getValue().equals(null) || nomeinput.getText() == null) {
			messaggio.setText("Inserire almeno numero, nome e cognome");
			
			System.out.println("Inserire almeno numero, nome e cognome");
			cont = false;
		}

		if (cont) {
			try {
				ResultSet rs;
				rs = db.query("SELECT * FROM giocatore U where U.numero = '" + numeroinput.getValue() + "' and squadra = '" +user.getSquadra()+"'");
				int i = 0;

				if (rs.next()) {
					messaggio.setText("Numero non disponibile");
				} else {
					
					
					ResultSet rs3 = db.query("Select count(numero) from giocatore");
					i = rs3.getInt(1);
				
					
					db.update("INSERT INTO Giocatore VALUES ('" + numeroinput.getValue() + "', '" +user.getSquadra()+ "','" + nomeinput.getText().toString()
							+ "','" + sceltaruolo.getValue().toString() + "',0, 0)");

					
					
					i *= 4;
					int k = 5; // i primi 4 sono le label sopra, poi ci sono 4 nodi per riga, che sono i dati
								// dei giocatori

					
					griglia.getChildren().remove(k, i+1);
					griglia.setGridLinesVisible(true);
					i=2;
					ResultSet rs2 = db.query("Select numero,nome,ruolo,gol from giocatore where squadra = '" +user.getSquadra()+ "'");
          				try {

						while (rs2.next()) {

							Label portA = new Label();
							portA.setText(new Integer(rs2.getInt("numero")).toString());
							GridPane.setConstraints(portA, 0, i);
							griglia.getChildren().add(portA);

							Label portB = new Label();
							portB.setText(rs2.getString("nome").toString());
							GridPane.setConstraints(portB, 1, i);
							griglia.getChildren().add(portB);

							Label portC = new Label();
							portC.setText(rs2.getString("ruolo").toString());
							GridPane.setConstraints(portC, 2, i);
							griglia.getChildren().add(portC);

							Label portD = new Label();
							portD.setText(new Integer(rs2.getInt("gol")).toString());
							GridPane.setConstraints(portD, 3, i);
							griglia.getChildren().add(portD);

							

							++i;
						}
					} catch (SQLException e) {
						e.printStackTrace();

					}
          				griglia.setGridLinesVisible(true);

					

					
				}

			}

			catch (SQLException ex) {
				System.out.println("Errore nell' interrogazione al DB");
			}
		}

	}
	
	
	
	@FXML
    void elimina(ActionEvent event) {
		int k=1;
		System.out.print(k);
		if(sceltagiocatore.getValue().indexOf(" ")!=k)
			k=2;
		System.out.println("hhhh");
		System.out.println(sceltagiocatore.getValue().substring(0, k));
		 db.update("DELETE FROM giocatore where numero = '" + sceltagiocatore.getValue().substring(0, k).toString() + "' and squadra = '" +user.getSquadra()+"'");
		int i;
		 
		 ResultSet rs3 = db.query("Select count(numero) from giocatore");
		 try {
			 i = rs3.getInt(1);
			 i *= 4;
			 k = 5; // i primi 4 sono le label sopra, poi ci sono 4 nodi per riga, che sono i dati
							// dei giocatori
			 griglia.getChildren().remove(k, i+1);
			 
		 }
		 catch(SQLException e){
			 e.printStackTrace();
		 }
			
		 i=2;

		ResultSet rs = db.query("Select numero,nome,ruolo,gol from giocatore where squadra = '" +user.getSquadra()+ "'");
		try {

			while (rs.next()) {

				Label port4 = new Label();
				port4.setText(new Integer(rs.getInt("numero")).toString());
				GridPane.setConstraints(port4, 0, i);
				griglia.getChildren().add(port4);

				Label port = new Label();
				port.setText(rs.getString("nome").toString());
				GridPane.setConstraints(port, 1, i);
				griglia.getChildren().add(port);

				Label port2 = new Label();
				port2.setText(rs.getString("ruolo").toString());
				GridPane.setConstraints(port2, 2, i);
				griglia.getChildren().add(port2);

				Label port3 = new Label();
				port3.setText(new Integer(rs.getInt("gol")).toString());
				GridPane.setConstraints(port3, 3, i);
				griglia.getChildren().add(port3);

				sceltagiocatore.getItems().add(port4.getText().concat(" ").concat(port.getText()));

				++i;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		 griglia.setGridLinesVisible(true);
		
    }

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		int i;
		int numero;
		int controllo;
		
		
		nomesquadra.setText(user.getSquadra());
		
		
		
		
		
		sceltaruolo.getItems().add("Portiere");
		sceltaruolo.getItems().add("Difensore");
		sceltaruolo.getItems().add("Centrocampista");
		sceltaruolo.getItems().add("Attaccante");
		// inserire controllo sui numeri disponibili
		for (i = 0; i <= 99; ++i) {
			ResultSet rsNumero = db.query("Select numero from giocatore where squadra = '" +user.getSquadra()+ "'");
			controllo=0;
			try {
				while (rsNumero.next()) {
				numero=new Integer(rsNumero.getInt("numero"));
			if((int)numero==i) 
				controllo=1;
			System.out.print(controllo);
			}
			if(controllo==0)
				numeroinput.getItems().add(i);
		 }
			catch(SQLException e) {
				e.printStackTrace();

			}
		
		 }
		
		i=0;
		ResultSet rsgol = db.query("Select nome,gol from giocatore where squadra = '" +user.getSquadra()+"' order by gol desc limit 5");
		try {

			while (rsgol.next()) {
				Label marcatore = new Label();
				marcatore.setText(rsgol.getString("nome").toString());
				GridPane.setConstraints(marcatore, 0, i);
				GrigliaMarcatori.getChildren().add(marcatore);
				
				Label golfatti = new Label();
				golfatti.setText(new Integer(rsgol.getInt("gol")).toString());
				GridPane.setConstraints(golfatti, 1, i);
				GrigliaMarcatori.getChildren().add(golfatti);
				
				++i;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		

		i = 2;

		ResultSet rs = db.query("Select numero,nome,ruolo,gol from giocatore where squadra = '" +user.getSquadra()+ "'");
		try {

			while (rs.next()) {

				Label port4 = new Label();
				port4.setText(new Integer(rs.getInt("numero")).toString());
				GridPane.setConstraints(port4, 0, i);
				griglia.getChildren().add(port4);

				Label port = new Label();
				port.setText(rs.getString("nome").toString());
				GridPane.setConstraints(port, 1, i);
				griglia.getChildren().add(port);

				Label port2 = new Label();
				port2.setText(rs.getString("ruolo").toString());
				GridPane.setConstraints(port2, 2, i);
				griglia.getChildren().add(port2);

				Label port3 = new Label();
				port3.setText(new Integer(rs.getInt("gol")).toString());
				GridPane.setConstraints(port3, 3, i);
				griglia.getChildren().add(port3);

				sceltagiocatore.getItems().add(port4.getText().concat(" ").concat(port.getText()));

				++i;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
}