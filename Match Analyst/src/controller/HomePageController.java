package controller;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import view.TestApp;

public class HomePageController {

    @FXML
    private Button logout;

    @FXML
    void logout(ActionEvent event) {
       	try {
    			FXMLLoader loader = new FXMLLoader(TestApp.class.getResource("Login.fxml"));
    			ScrollPane login = (ScrollPane) loader.load();
    			Scene scene = new Scene(login);
    			TestApp.getStage().setScene(scene);
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
    }

}
