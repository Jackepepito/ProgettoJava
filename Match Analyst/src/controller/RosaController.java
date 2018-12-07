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
			System.out.println(numeroinput.getValue().toString());
			System.out.println(nomeinput.getText());
			System.out.println("Inserire almeno numero, nome e cognome");
			cont = false;
		}

		if (cont) {
			try {
				ResultSet rs;
				rs = db.query("SELECT * FROM giocatore U where U.numero = '" + numeroinput.getValue() + "'");
				int i = 0;

				if (rs.next()) {
					messaggio.setText("Numero non disponibile");
				} else {
					System.out.println("Ok");
					db.update("INSERT INTO Giocatore VALUES ('" + numeroinput.getValue() + "','" + nomeinput.getText()
							+ "','" + ruoloinput.getText() + "',0, 0, 0)");

					ResultSet rs3 = db.query("Select count(*) AS TOTAL  from giocatore");

					i = rs3.getInt("total");
					i *= 5;
					int k = 6; // i primi 5 sono le label sopra, poi ci sono 5 nodi per riga, che sono i dati
								// dei giocatori

					System.out.println(k);
					griglia.getChildren().remove(k, i + 1);

					griglia.setGridLinesVisible(true);

					// reinserimento dei giocatori
					i = 2;

					ResultSet rs2 = db.query("Select numero,nome,ruolo,gol,assist from giocatore");
					try {

						while (rs.next()) {

							Label port4 = new Label();
							port4.setText(new Integer(rs2.getInt("numero")).toString());
							GridPane.setConstraints(port4, 0, i);
							griglia.getChildren().add(port4);

							Label port = new Label();
							port.setText(rs2.getString("nome").toString());
							GridPane.setConstraints(port, 1, i);
							griglia.getChildren().add(port);

							Label port2 = new Label();
							port2.setText(rs2.getString("ruolo").toString());
							GridPane.setConstraints(port2, 2, i);
							griglia.getChildren().add(port2);

							Label port3 = new Label();
							port3.setText(new Integer(rs2.getInt("gol")).toString());
							GridPane.setConstraints(port3, 3, i);
							griglia.getChildren().add(port3);

							Label port5 = new Label();
							port5.setText(new Integer(rs2.getInt("assist")).toString());
							GridPane.setConstraints(port5, 4, i);
							griglia.getChildren().add(port5);

							++i;
						}
						griglia.setGridLinesVisible(true);
					} catch (SQLException e) {
						e.printStackTrace();

					}
				}

			}

			catch (SQLException ex) {
				System.out.println("Errore nell' interrogazione al DB");
			}
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nomesquadra.setText(user.getSquadra());
		int i;
		// inserire controllo sui numeri disponibili
		for (i = 0; i <= 99; ++i) {
			numeroinput.getItems().add(i);
		}

		i = 2;

		ResultSet rs = db.query("Select numero,nome,ruolo,gol,assist from giocatore");
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

				Label port5 = new Label();
				port5.setText(new Integer(rs.getInt("assist")).toString());
				GridPane.setConstraints(port5, 4, i);
				griglia.getChildren().add(port5);

				++i;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
}