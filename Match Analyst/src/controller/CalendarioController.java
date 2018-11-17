package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
    private Label prova;
    
    @FXML
    private Button scegli;
    

    @FXML
    private Label gol_segnati;

    @FXML
    private Label gol_subiti;

    @FXML
    private Label marcatori;

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
    
	public CalendarioController(Utente user, Database db)
	{
		this.user = user;
		this.db = db;
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		ResultSet rs = db.query("Select avversario, girone from partita");
		try {
			while(rs.next()) {
				partite.getItems().add(rs.getString("avversario")+ " "+ rs.getString("girone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
		String[] value = partite.getValue().split(" ");
		System.out.println(partite.getValue());
		System.out.println(value[0]);
		System.out.println(value[1]);
		ResultSet rs = db.query("Select * from partita where avversario = '" +value[0]+ "' and girone = '" +value[1]+ "' ");
		try {
			System.out.println(rs.getInt("gol_segnati"));
			System.out.println(new Integer(rs.getInt("gol_segnati")));
			System.out.println(new Integer(rs.getInt("gol_segnati")).toString());
			gol_segnati.setText(new Integer(rs.getInt("gol_segnati")).toString());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		//gol_segnati.setText("SELECT gol_segnati from partite where avversario = '" +partite.getItems().add("avversario")+ "' and girone = '" +partite.getItems().add("avversario")+ "'");
	}
}
