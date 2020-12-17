package view;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainWindowViewController {

    @FXML
    private Button targetFarkle;

    @FXML
    private Button roundsFarkle;

    @FXML
    private Button exitFarkle;


    @FXML
    void exitChoosen(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void roundsChoosen(ActionEvent event) {
        System.out.println("10 rounds to play!");
    }

    @FXML
    void targetChoosen(ActionEvent event) {
        System.out.println("10000 points to win!");
    }

}
