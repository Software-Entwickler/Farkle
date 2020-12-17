package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.AddPlayersViewController;
import view.HUDViewController;
import view.SampleViewController;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		AddPlayersViewController addPlayersViewController = new AddPlayersViewController();
		HUDViewController hudViewController = new HUDViewController();
		SampleViewController sampleViewController = new SampleViewController();
    
		Scene scene = new Scene(hudViewController, 800,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
