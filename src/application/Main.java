package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.AddPlayersViewController;
import view.HUDViewController;
import view.MainViewController;


import java.net.URL;

public class Main extends Application {

/*	public void start(Stage primaryStage) throws IOException {
		
		AddPlayersViewController addPlayersViewController = new AddPlayersViewController();
		HUDViewController hudViewController = new HUDViewController();
		SampleViewController sampleViewController = new SampleViewController();
    
		Scene scene = new Scene(hudViewController, 800,600);
		primaryStage.setScene(scene);
		primaryStage.show();*/
  
	@Override
	public void start(Stage primaryStage) {

		try {

			FXMLLoader loader = new FXMLLoader();
			URL xmlUrl = getClass().getResource("../view/MainWindowView.fxml");
			loader.setLocation(xmlUrl);
			Parent root = loader.load();

			Scene scene = new Scene(root);
			primaryStage.setScene(scene);

			primaryStage.setTitle("Farkle");
			primaryStage.getIcons().add(new Image("application/icon.jpg"));

			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
