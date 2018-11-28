package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// MATCH ANALYST

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
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

	@FXML
	private GridPane griglia;

	@FXML
	private Label messaggio;
	
	@FXML
	private Label messaggioGol;

	@FXML
	private BarChart<String, Integer> barchart;

	@FXML
	private Button nuovapartita;

	@FXML
	private Button aggiungi;

	@FXML
	private Button eliminapartita;

	@FXML
	private TextField avversario;

	@FXML
	private ComboBox<Integer> golSegnati;

	@FXML
	private ComboBox<Integer> golSubiti;

	@FXML
	private TextField marcatori;

	@FXML
	private ComboBox<Integer> possessoPalla;

	@FXML
	private ComboBox<Integer> tiriTot;

	@FXML
	private ComboBox<Integer> tiriPorta;

	@FXML
	private ComboBox<Integer> falliCommessi;

	@FXML
	private ComboBox<Integer> falliSubiti;

	@FXML
	private ComboBox<Integer> parate;

	@FXML
	private ComboBox<String> casaTrasf;

	@FXML
	private ComboBox<Integer> gol;

	@FXML
	private GridPane visualizza_partita;

	@FXML
	private PieChart grafico_sinistra;

	@FXML
	private PieChart grafico_destra;

	public HomePageController(Utente user, Database db) {
		this.user = user;
		this.db = db;
	}

	/**
	 * Metodo che viene richiamato a ogni apertura dell'interfaccia che permette di
	 * settare le informazioni dell'utente
	 */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nomesquadra.setText(user.getSquadra());
		nomecognome.setText(user.getNome() + " " + user.getCognome());

		XYChart.Series<String, Integer> series1 = new XYChart.Series<String, Integer>();
		series1.setName("gol");

		XYChart.Series<String, Integer> series2 = new XYChart.Series<String, Integer>();
		series2.setName("falli");

		int i;
		for (i = 0; i <= 20; ++i) {
			golSegnati.getItems().add(i);
		}

		for (i = 0; i <= 20; ++i) {
			golSubiti.getItems().add(i);
		}

		for (i = 0; i <= 100; ++i) {
			possessoPalla.getItems().add(i);
		}

		for (i = 0; i <= 150; ++i) {
			tiriTot.getItems().add(i);
		}

		for (i = 0; i <= 150; ++i) {
			tiriPorta.getItems().add(i);
		}

		for (i = 0; i <= 150; ++i) {
			falliSubiti.getItems().add(i);
		}

		for (i = 0; i <= 150; ++i) {
			falliCommessi.getItems().add(i);
		}

		for (i = 0; i <= 150; ++i) {
			parate.getItems().add(i);
		}

		for (i = 0; i <= 10; ++i) {
			gol.getItems().add(i);
		}

		casaTrasf.getItems().add("Home");
		casaTrasf.getItems().add("Away");

		// ObservableList<String> types = FXCollections.observableArrayList
		// ( "Andamento generale", "Andamento in casa", "Andamento in trasferta","Fase
		// offensiva","Fase difensiva" );
		//
		// grafici.setItems( types );
		//
		// x.setLabel("Avversario");
		//
		// y.setLabel("Gol");
		// XYChart.Series<String, Integer> series1 = new XYChart.Series<String,
		// Integer>();
		// series1.setName("Gol Fatti");
		// ResultSet rs = db.query("SELECT gol_segnati,avversario FROM partita ");
		// try {
		// while (rs.next()){
		// int sum = rs.getInt("gol_segnati");
		// String avversario = rs.getString("avversario");
		// series1.getData().add(new XYChart.Data<String, Integer>(avversario, sum));
		// }
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// grafico.getData().add(series1);

		i = 0;
		ResultSet rs1 = db.query(
				"Select avversario,gol_segnati, gol_subiti,possesso_palla, falli_commessi, falli_subiti from partita");
		try {

			while (rs1.next()) {

				Button partita = new Button();
				partita.setMinWidth(550);

				Label squadra = new Label();
				Label golsegnati = new Label();
				Label golsubiti = new Label();
				Label avversario = new Label();
				Label possessopalla = new Label();
				Label nome_completo = new Label();
				Label fallicommessi = new Label();
				Label fallisubiti = new Label();

				fallicommessi.setText(new Integer(rs1.getInt("falli_commessi")).toString());
				fallisubiti.setText(new Integer(rs1.getInt("falli_subiti")).toString());
				squadra.setText(user.getSquadra().toString());
				golsegnati.setText(new Integer(rs1.getInt("gol_segnati")).toString());
				golsubiti.setText(new Integer(rs1.getInt("gol_subiti")).toString());
				avversario.setText(rs1.getString("avversario"));
				possessopalla.setText(new Integer(rs1.getInt("possesso_palla")).toString());
				nome_completo.setText((squadra.getText().toString().concat("      ")
						.concat(golsegnati.getText().toString()).concat("  ").concat(golsubiti.getText().toString())
						.concat("      ").concat(avversario.getText().toString())));
				partita.setText(nome_completo.getText().toString());

				// inizializzo il grafico centrale
				if (i == 0) {
					barchart.setTitle("Statistiche");

					series1.getData().add(new XYChart.Data<String, Integer>(avversario.getText().toString(),
							new Integer(golsubiti.getText().toString())));
					series1.getData().add(new XYChart.Data<String, Integer>(user.getSquadra().toString(),
							new Integer(golsegnati.getText().toString())));

					series2.getData().add(new XYChart.Data<String, Integer>(avversario.getText().toString(),
							new Integer(fallisubiti.getText().toString())));
					series2.getData().add(new XYChart.Data<String, Integer>(user.getSquadra().toString(),
							new Integer(fallicommessi.getText().toString())));

					barchart.getData().addAll(series1, series2);
				}

				// quando clicco sul bottone
				partita.setOnAction(event -> {
					// prima cancello
					series1.getData().clear();
					series2.getData().clear();

					barchart.setTitle("Statistiche");

					// poi inserisco i nuovi dati
					series1.getData().add(new XYChart.Data<String, Integer>(avversario.getText().toString(),
							new Integer(golsubiti.getText().toString())));
					series1.getData().add(new XYChart.Data<String, Integer>(user.getSquadra().toString(),
							new Integer(golsegnati.getText().toString())));

					series2.getData().add(new XYChart.Data<String, Integer>(avversario.getText().toString(),
							new Integer(fallisubiti.getText().toString())));
					series2.getData().add(new XYChart.Data<String, Integer>(user.getSquadra().toString(),
							new Integer(fallicommessi.getText().toString())));

					// aggiungo
					barchart.getData().addAll(series1, series2);

				});

				RowConstraints row = new RowConstraints(40);
				griglia.getRowConstraints().add(row);
				GridPane.setConstraints(partita, 0, i);
				griglia.getChildren().add(partita);
				griglia.getRowConstraints().add(row);

				if (i == 0) {

					// Label avversario1 = new Label();
					// avversario1.setText(rs1.getString("avversario").toString());
					// GridPane.setConstraints(avversario1, 1, 0);
					// visualizza_partita.getChildren().add(avversario1);
					//
					// Label golsegnati = new Label();
					// golsegnati.setText(new Integer(rs1.getInt("gol_segnati")).toString());
					// GridPane.setConstraints(golsegnati, 1, 1);
					// visualizza_partita.getChildren().add(golsegnati);
					//
					//
					// Label golsubiti = new Label();
					// golsegnati.setText(new Integer(rs1.getInt("gol_subiti")).toString());
					// GridPane.setConstraints(golsubiti, 1, 2);
					// visualizza_partita.getChildren().add(golsubiti);
					//
					// Label possessopalla = new Label();
					// possessopalla.setText(new Integer(rs1.getInt("possesso_palla")).toString());
					// GridPane.setConstraints(possessopalla, 1, 4);
					// visualizza_partita.getChildren().add(possessopalla);
					//
				}
				++i;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void aggiungiPartita(ActionEvent event) {

		boolean cont = true;
		if (avversario.getText().isEmpty() || casaTrasf.getItems().isEmpty() || golSegnati.getItems().isEmpty()
				|| golSubiti.getItems().isEmpty() || marcatori.getText().isEmpty() || gol.getItems().isEmpty()
				|| possessoPalla.getItems().isEmpty() || tiriTot.getItems().isEmpty() || tiriPorta.getItems().isEmpty()
				|| falliCommessi.getItems().isEmpty() || falliSubiti.getItems().isEmpty()
				|| parate.getItems().isEmpty()) {
			messaggio.setText("Riempire i campi obbligatori");
			System.out.println("Riempire i campi obbligatori");
			cont = false;
		}

		if (cont) {
			db.update("INSERT INTO partita VALUES ('" + avversario.getText() + "', '" + casaTrasf.getValue() + "','"
					+ golSegnati.getValue() + "','" + golSubiti.getValue() + "', '" + possessoPalla.getValue() + "','"
					+ tiriTot.getValue() + "', '" + tiriPorta.getValue() + "', '" + falliCommessi.getValue() + "','"
					+ falliSubiti.getValue() + "', '" + parate.getValue() + "')");
			System.out.println("Inserimento avvenuto con successo!");

		}
		
	}

	@FXML
	void aggiungiMarcatore(ActionEvent event) {
		int sum = 0;
		boolean cont = true;
		sum += gol.getValue();
		if(marcatori.getText().isEmpty() || gol.getItems().isEmpty())
		{
			messaggio.setText("Riempire i campi obbligatori");
			cont = false;
		}
		if(sum > golSegnati.getValue())
		{
			messaggioGol.setText("Immesso un numero errato di gol");
			cont = false;
			sum = 0;
		}
		if(cont)
		{
		db.update("INSERT INTO marcatore VALUES ('"+avversario.getText()+"','" +casaTrasf.getValue()+"', '" +marcatori.getText()+ "', '" +gol.getValue()+ "')");
		}
		
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
