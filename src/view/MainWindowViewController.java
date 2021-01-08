package view;


import controller.FarkleController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;


/*
		AddPlayersViewController addPlayersViewController = new AddPlayersViewController();
		HUDViewController hudViewController = new HUDViewController();
		SampleViewController sampleViewController = new SampleViewController();

		Scene scene = new Scene(hudViewController, 800,600);
		primaryStage.setScene(scene);
		primaryStage.show();

 */
public class MainWindowViewController extends HBox {

    @FXML
    private Button targetFarkle;

    @FXML
    private Button roundsFarkle;

    @FXML
    private Button exitFarkle;



    private Stage primaryStage;

    private FarkleController farkleController;

    public MainWindowViewController (Stage primaryStage) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainWindowView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {

            e.printStackTrace();
        }

        this.primaryStage = primaryStage;
        this.farkleController = new FarkleController();

    }


    @FXML
    void exitChoosen(ActionEvent event) {
        System.exit(0);
    }


    @FXML
    void roundsChoosen(ActionEvent event) {
        System.out.println("10 rounds to play!");
        farkleController.getGameController().chooseTenRoundsGame();
        AddPlayersViewController addPlayersViewController = new AddPlayersViewController(this.primaryStage, this.farkleController);
        Scene scene = new Scene(addPlayersViewController, primaryStage.getScene().getWidth() , primaryStage.getScene().getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void targetChoosen(ActionEvent event) {
        System.out.println("10000 points to win!");
        farkleController.getGameController().chooseTenThousendGame();
        AddPlayersViewController addPlayersViewController = new AddPlayersViewController(this.primaryStage, this.farkleController);
        Scene scene = new Scene(addPlayersViewController, primaryStage.getScene().getWidth() , primaryStage.getScene().getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
