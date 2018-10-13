package application;
	
import java.awt.ScrollPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	static Stage stage;
	@Override
	public void start(Stage s) {
		try {
			stage = s;
			FXMLLoader l = new FXMLLoader(Main.class.getResource("Home.fxml"));
			ScrollPane loginLayout = (ScrollPane) l.load();
			
			Scene scene = new Scene(loginLayout);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
