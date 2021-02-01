package view;

import controller.FarkleController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class HighScoreViewController extends AnchorPane implements Initializable {

    @FXML
    private TableView<PlayerScoreTableCell> highScoreTable;

    @FXML
    private TableColumn<PlayerScoreTableCell, String> playerName;

    @FXML
    private TableColumn<PlayerScoreTableCell, Integer> playerScore;

    @FXML
    private Button back;

    private final Stage primaryStage;

    private FarkleController farkleController = new FarkleController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<HighScoreViewController.PlayerScoreTableCell> playerScoreTableCells =
                FXCollections.observableArrayList(farkleController.getIoController().getPlayerScoreTableCells());

        playerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        playerScore.setCellValueFactory(new PropertyValueFactory<>("score"));

        highScoreTable.setItems(playerScoreTableCells);

    }

    public static class PlayerScoreTableCell {

        private final SimpleIntegerProperty score;
        private final SimpleStringProperty name;

        public PlayerScoreTableCell (String name, Integer score) {
            this.score = new SimpleIntegerProperty(score);
            this.name = new SimpleStringProperty(name);
        }

        public int getScore() {
            return score.get();
        }

        public SimpleIntegerProperty scoreProperty() {
            return score;
        }

        public void setScore(int score) {
            this.score.set(score);
        }

        public String getName() {
            return name.get();
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }
    }

    public HighScoreViewController (Stage primaryStage) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HighScoreView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {

            e.printStackTrace();
        }

        this.primaryStage = primaryStage;
    }

    @FXML
    void goBack(ActionEvent event) {
        Parent parent = this.getParent();
        if (parent instanceof StackPane){
            StackPane stackPane = (StackPane) parent;
            stackPane.getChildren().remove(this);
        } else {
            AnchorPane anchorPane = (AnchorPane) parent;
            anchorPane.getChildren().remove(this);
        }
    }

}
