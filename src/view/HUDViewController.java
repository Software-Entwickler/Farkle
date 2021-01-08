package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.FarkleController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Player;

public class HUDViewController extends StackPane implements Initializable {

    @FXML
    private Menu btnExit, btnRules, btnTipp, btnHighscore, btnEffects;


    @FXML
    private ListView<Player> listPlayers, listRoundScores;

    @FXML
    private Button btnThrow, btnConfirm, btnBank;


    @FXML
    private Label labScore;

    @FXML
    private ImageView imgPlayer;
    
    
    private FarkleController farkleController;
    
    private Stage primaryStage;

    public HUDViewController(Stage primaryStage, FarkleController farkleController)				//Constructor
    {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HUD.fxml"));
    	loader.setRoot(this);
    	loader.setController(this);
    	try {
    		loader.load();
    	} catch (IOException e) {
    		
    		e.printStackTrace();
    	}

    	this.primaryStage = primaryStage;
    	this.farkleController = farkleController;
    	
    	refresh();

    }
    
    public class PlayerListViewCell extends ListCell<Player> 				// Cell Class
    {
    	private StackPane stackPane = new StackPane();
    	
    	private ImageView imgPlayerCell = null;
    	
    	private Pane pane = new Pane();

	    private Label labScore = new Label("");

	    private Label labName = new Label("");
	    
	    private VBox vbox = new VBox();
	    
	    public PlayerListViewCell() 
	    {
	    	super();
		
	    	stackPane.getChildren().addAll(pane, vbox);
	    	vbox.getChildren().addAll(labName, labScore);
	    	vbox.setAlignment(Pos.BASELINE_LEFT);
	    	vbox.setPrefWidth(120);
	    	vbox.setMaxWidth(USE_PREF_SIZE);
	    	vbox.setMinWidth(USE_PREF_SIZE);
	    	vbox.setSpacing(10.0);
	    }
	
	    @Override
	    protected void updateItem(Player player, boolean empty)
	    {
    	
	    	super.updateItem(player, empty);
	    	setText(null);
        	setGraphic(null);
        	

        	if(player != null && !empty)
        	{

        		labName.setText(player.getUserName());
        		labScore.setText("" + player.getScore());
        		setGraphic(stackPane);
        	}
	    }
    }

    @FXML
    void bank(ActionEvent event) {

    }

    @FXML
    void confirmCollection(ActionEvent event) {

    }

    @FXML
    void exitGame(ActionEvent event) {

    }

    @FXML
    void giveATipp(ActionEvent event) {

    }

    @FXML
    void showEffects(ActionEvent event) {

    }

    @FXML
    void showHighscore(ActionEvent event) {

    }

    @FXML
    void showRules(ActionEvent event) {

    }

    @FXML
    void throwDices(ActionEvent event) {

    }
    
    public void refresh()
	{
		ArrayList<Player> allPlayers = farkleController.getFarkle().getCurrentGame().getPlayers();
		ObservableList<Player> ob = FXCollections.observableArrayList();
		for(Player player : allPlayers)
			{
				ob.addAll(player);
			}
		listPlayers.setItems(ob);
		listPlayers.setCellFactory(playerItem -> new PlayerListViewCell());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		}

}
