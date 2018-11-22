/*
 MATCH ANALYST
 * */

package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Database;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import controller.*;

/**
 * Classe che permette all' utente di effettuare il login o se non � iscritto di effettuare la sua registrazione 
 *
 */
public class TestApp extends Application {
	static Stage stage;
	static Database db;
	
	/**
	 *  Metodo con cui viene caricata la pagina dedicata all'amministratore
	 */
	
	@Override
	public void start(Stage s) {
		try {
			 stage=s;
			 LoginController controller = new LoginController(db);
			 FXMLLoader loader = new FXMLLoader(TestApp.class.getResource("Login.fxml"));
			 loader.setController(controller);
	         ScrollPane loginLayout = (ScrollPane) loader.load();
	    
	         Scene scene = new Scene(loginLayout);
	         stage.setScene(scene);
	         //stage.setMaxHeight(13600);
	         //stage.setMaxWidth(1000);
	         stage.show();
	         stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	             public void handle(WindowEvent we) {
	                 db.closeConnection();
	                 System.out.println("!!");
	             }
	         });
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Stage getStage(){
		return stage;
	}
	

	public static void main(String[] args) {
		db = new Database();
		db.testInsert();
		launch(args);
	}
	
}

