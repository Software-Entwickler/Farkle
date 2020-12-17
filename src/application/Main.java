package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.MainViewController;

import java.net.URL;

public class Main extends Application {

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
