package application;


import controller.FarkleController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.MainWindowViewController;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("To be implemented later!");
		}));

		try {

			MainWindowViewController mainWindowViewController = new MainWindowViewController(primaryStage);

			Scene scene = new Scene(mainWindowViewController);

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
