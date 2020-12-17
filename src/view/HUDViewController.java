package view;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
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
    
    
    FarkleController farkleController;
    
    
    public HUDViewController()				//Constructor
    {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HUD.fxml"));
    	loader.setRoot(this);
    	loader.setController(this);
    	try {
    		loader.load();
    	} catch (IOException e) {
    		
    		e.printStackTrace();
    	}
    }
    
    public class PlayerListViewCell extends ListCell<Player> 				// Cell Class
    {
    	private StackPane stackPane = new StackPane();
    	
    	private ImageView imgPlayerCell = null;

	    private Label labScore = new Label("");

	    private Label labName = new Label("");
	    
	    private HBox hbox = new HBox();
	    
	    public PlayerListViewCell() 
	    {
		super();
		
		hbox.getChildren().addAll(labName, labScore);
		hbox.setAlignment(Pos.BASELINE_LEFT);
		hbox.setPrefWidth(120);
		hbox.setMaxWidth(USE_PREF_SIZE);
		hbox.setMinWidth(USE_PREF_SIZE);
		//labName.setAlignment(Pos.CENTER_LEFT);
		//labScore.setAlignment(Pos.CENTER_RIGHT);
		 hbox.setSpacing(60);
		//stackPane.getChildren().addAll(imgPlayerCell, hbox);
		
		
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
        		setGraphic(hbox);
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		farkleController = new FarkleController();
		
		Player player1 = new Player("Abdul");
		Player player2 = new Player("Khaled");
		Player player3 = new Player("Ahmad");
		Player player4 = new Player("Nour");
		Player player5 = new Player("Yousef");
		
		ObservableList<Player> ob = FXCollections.observableArrayList();
		ob.addAll(player1, player2, player3, player4, player5);
		listPlayers.setItems(ob);
		listPlayers.setCellFactory(playerItem -> new PlayerListViewCell());
		
		
	}

}
