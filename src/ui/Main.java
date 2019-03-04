package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("AnimatedSquare.fxml"));
    	Parent root = loader.load();
    	
    	SquareGUIController guiController = loader.getController();
    	stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                try {
					guiController.onCloseRequest();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        });
    	
    	Scene scene = new Scene(root);
    	stage.setTitle("Animated Square!");
    	stage.setScene(scene);
    	stage.sizeToScene();
    	stage.show();
	}
}
