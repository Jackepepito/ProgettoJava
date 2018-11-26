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
import javafx.scene.chart.AreaChart;
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

public class StatisticheController implements Initializable {

	@FXML
	private Database db;

	private Utente user;

	@FXML
	private Button indietro;

	@FXML
	private Button mostra;

	@FXML
	private ComboBox<String> sceltaGrafico;

	@FXML
	private LineChart<String, Integer> graficoGolSegnati;

	@FXML
	private LineChart<String, Integer> graficoGolSubiti;

	@FXML
	private CategoryAxis x1;

	@FXML
	private NumberAxis y1;

	@FXML
	private CategoryAxis x2;

	@FXML
	private NumberAxis y2;

	private XYChart.Series<String, Integer> series;

	public StatisticheController(Utente user, Database db) {
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

	@FXML
	void mostra(ActionEvent event) {
		graficoGolSegnati.setVisible(false);
		graficoGolSubiti.setVisible(false);
		if (sceltaGrafico.getValue().equals("Gol Fatti")) {
			creaGrafico(graficoGolSegnati, x1, y1, "gol_segnati");
		}

		if (sceltaGrafico.getValue().equals("Gol Subiti")) {
			creaGrafico(graficoGolSubiti, x2, y2, "gol_subiti");
		}
	}

	private void creaGrafico(LineChart<String, Integer> grafico, CategoryAxis x, NumberAxis y, String s) {
		grafico.setLegendVisible(false);
		x.setLabel("Avversario");
		y.setLabel("Gol");
		series = new XYChart.Series<String, Integer>();
		series.setName(s);
		ResultSet rs = db.query("SELECT " + s + ", avversario FROM partita ");
		try {
			while (rs.next()) {
				System.out.println(rs.getString("avversario") + " " + rs.getInt(s));
				series.getData().add(new XYChart.Data<String, Integer>(rs.getString("avversario"), rs.getInt(s)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		grafico.getData().add(series);
		grafico.setVisible(true);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// ObservableList<String> types = FXCollections.observableArrayList
		// ("Gol Fatti", "Gol Subiti");

		sceltaGrafico.getItems().add("Gol Fatti");
		sceltaGrafico.getItems().add("Gol Subiti");

	}
}
