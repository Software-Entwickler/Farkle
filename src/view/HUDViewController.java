package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import controller.FarkleController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.FocusModel;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Dice;
import model.Player;

public class HUDViewController extends StackPane implements Initializable {

    @FXML
    private Menu menGame, menRules, menTipp, menHighscore, menOptions;
    
    @FXML
    private MenuItem menItExitGame;

    @FXML
    private ListView<Player> listPlayers, listRoundScores;

    @FXML
    private Button btnThrow, btnConfirm, btnBank;

    @FXML
    private Label labScore, S1, S2, S3, S4, S5, S6, lblAmZug, lblRunde;

    @FXML
    private ImageView imgPlayer;
    
    @FXML
    private GridPane DicePlace;
    
    private ArrayList<String> chosenNumbers = new ArrayList<>();
    
    private HashMap<String, Integer> chosenMap = new HashMap<>();
    
    private boolean thrown;
    
    private boolean bS1 , bS2, bS3, bS4, bS5, bS6 = false;
    
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
    	farkleController.getRoundController().setNextPlayer();
    	nextPlayerOrRound();
    	btnThrow.setDisable(false);
    	S1.setVisible(false);
    	S2.setVisible(false);
    	S3.setVisible(false);
    	S4.setVisible(false);
    	S5.setVisible(false);
    	S6.setVisible(false);
    	refresh();
    }

    @FXML
    void confirmCollection(ActionEvent event) {
    	if(!chosenMap.isEmpty())
    	{
    		btnThrow.setDisable(false);
    		ArrayList<Integer> chosen = new ArrayList<>(chosenMap.values());
    		ArrayList<Dice> chosenDices = new ArrayList<>();
    		for(int i = 0; i < chosen.size(); i++)
    		{
    			chosenDices.add(new Dice(chosen.get(i), true));
    		}
    		farkleController.getActionController().confirm(chosenDices);
    		refreshField();
    		refresh();
    	}
    }

    @FXML
    void exitGame(ActionEvent event) {
    	farkleController.getFarkle().setCurrentGame(null);
    	MainWindowViewController mainWindowViewController = new MainWindowViewController(this.primaryStage);
		Scene scene = new Scene(mainWindowViewController , primaryStage.getScene().getWidth() , primaryStage.getScene().getHeight());
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    

    @FXML
    void giveATipp(ActionEvent event) {

    }

    @FXML
    void showOptions(ActionEvent event) {

    }

    @FXML
    void showHighscore(ActionEvent event) {

    }

    @FXML
    void showRules(ActionEvent event) {

    }
    
	@FXML
    void addS1(MouseEvent event) {
    	if(chosenMap.containsKey("S1"))
    	{
    		S1.setTextFill(Color.BLACK);
    		S1.setFont(new Font(13));
    		chosenMap.remove("S1");
    	}
    	else
    	{
    		S1.setTextFill(Color.RED);
    		S1.setFont(new Font(18));
    		chosenMap.put("S1", (Integer) Integer.parseInt(S1.getText()));
    	}
    }

    @FXML
    void addS2(MouseEvent event) {
    	if(chosenMap.containsKey("S2"))
    	{
    		S2.setTextFill(Color.BLACK);
    		S2.setFont(new Font(13));
    		chosenMap.remove("S2");
    	}
    	else
    	{
    		S2.setTextFill(Color.RED);
    		S2.setFont(new Font(18));
    		chosenMap.put("S2", (Integer) Integer.parseInt(S2.getText()));
    	}
    }
    
    @FXML
    void addS3(MouseEvent event) {
    	if(chosenMap.containsKey("S3"))
    	{
    		S3.setTextFill(Color.BLACK);
    		S3.setFont(new Font(13));
    		chosenMap.remove("S3");
    	}
    	else
    	{
    		S3.setTextFill(Color.RED);
    		S3.setFont(new Font(18));
    		chosenMap.put("S3", (Integer) Integer.parseInt(S3.getText()));
    	}
    }

    @FXML
    void addS4(MouseEvent event) {
    	if(chosenMap.containsKey("S4"))
    	{
    		S4.setTextFill(Color.BLACK);
    		S4.setFont(new Font(13));
    		chosenMap.remove("S4");
    	}
    	else
    	{
    		S4.setTextFill(Color.RED);
    		S4.setFont(new Font(18));
    		chosenMap.put("S4", (Integer) Integer.parseInt(S4.getText()));
    	}
    }

    @FXML
    void addS5(MouseEvent event) {
    	if(chosenMap.containsKey("S5"))
    	{
    		S5.setTextFill(Color.BLACK);
    		S5.setFont(new Font(13));
    		chosenMap.remove("S5");
    	}
    	else
    	{
    		S5.setTextFill(Color.RED);
    		S5.setFont(new Font(18));
    		chosenMap.put("S5", (Integer) Integer.parseInt(S5.getText()));
    	}
    }

    @FXML
    void addS6(MouseEvent event) {
    	if(chosenMap.containsKey("S6"))
    	{
    		S6.setTextFill(Color.BLACK);
    		S6.setFont(new Font(13));
    		chosenMap.remove("S6");
    	}
    	else
    	{
    		S6.setTextFill(Color.RED);
    		S6.setFont(new Font(18));
    		chosenMap.put("S6", (Integer) Integer.parseInt(S6.getText()));
    	}
    }

    @FXML
    void throwDices(ActionEvent event) {
    	
    	btnThrow.setDisable(true);
    	Random rand = new Random();
    	if(!bS1)
    	{
    		int posRandInt1 = ((rand.nextInt( Integer.MAX_VALUE ) + 1) % 6) + 1;
        	S1.setText("" + posRandInt1);
        	S1.setVisible(true);
        	S1.setDisable(false);
    	}
    	else
    	{
    		S1.setVisible(false);
    		S1.setDisable(true);
    	}
    	if(!bS2)
    	{
    		int posRandInt2 = ((rand.nextInt( Integer.MAX_VALUE ) + 1) % 6) + 1;
        	S2.setText("" + posRandInt2);
        	S2.setVisible(true);
        	S2.setDisable(false);
    	}
    	else
    	{
    		S2.setVisible(false);
    		S2.setDisable(true);
    	}
    	if(!bS3)
    	{
    		int posRandInt3 = ((rand.nextInt( Integer.MAX_VALUE ) + 1) % 6) + 1;
        	S3.setText("" + posRandInt3);
        	S3.setVisible(true);
        	S3.setDisable(false);
    	}
    	else
    	{
    		S3.setVisible(false);
    		S3.setDisable(true);
    	}
    	if(!bS4)
    	{
    		int posRandInt4 = ((rand.nextInt( Integer.MAX_VALUE ) + 1) % 6) + 1;
        	S4.setText("" + posRandInt4);
        	S4.setVisible(true);
        	S4.setDisable(false);
    	}
    	else
    	{
    		S4.setVisible(false);
    		S4.setDisable(true);
    	}
    	if(!bS5)
    	{
    		int posRandInt5 = ((rand.nextInt( Integer.MAX_VALUE ) + 1) % 6) + 1;
        	S5.setText("" + posRandInt5);
        	S5.setVisible(true);
        	S5.setDisable(false);
    	}
    	else
    	{
    		S5.setVisible(false);
    		S5.setDisable(true);
    	}
    	if(!bS6)
    	{
    		int posRandInt6 = ((rand.nextInt( Integer.MAX_VALUE ) + 1) % 6) + 1;
        	S6.setText("" + posRandInt6);
        	S6.setVisible(true);
        	S6.setDisable(false);
    	}
    	else
    	{
    		S6.setVisible(false);
    		S6.setDisable(true);
    	}
    }
    
    private void refresh()
	{
		ArrayList<Player> allPlayers = farkleController.getFarkle().getCurrentGame().getPlayers();
		ObservableList<Player> ob = FXCollections.observableArrayList();
		for(Player player : allPlayers)
			{
				ob.addAll(player);
			}
		listPlayers.setItems(ob);
		listPlayers.setCellFactory(playerItem -> new PlayerListViewCell());
		
		lblAmZug.setText("Am Zug: " + farkleController.getFarkle().getCurrentGame().getCurrentPlayer().getUserName());
		lblRunde.setText("Runde: " + farkleController.getFarkle().getCurrentGame().getCurrentRound().getRoundNum());
	}
    
    private void nextPlayerOrRound()
    {
    	bS1 = false;
    	bS2 = false;
    	bS3 = false;
    	bS4 = false;
    	bS5 = false;
    	bS6 = false;
    	S1.setTextFill(Color.BLACK);
		S1.setFont(new Font(13));
		S2.setTextFill(Color.BLACK);
		S2.setFont(new Font(13));
		S3.setTextFill(Color.BLACK);
		S3.setFont(new Font(13));
		S4.setTextFill(Color.BLACK);
		S4.setFont(new Font(13));
		S5.setTextFill(Color.BLACK);
		S5.setFont(new Font(13));
		S6.setTextFill(Color.BLACK);
		S6.setFont(new Font(13));
    }
    
    private void refreshField()
    {
    	if(chosenMap.containsKey("S1"))
    	{
    		System.out.print("Contains S1");
    		bS1 = true;
    		S1.setDisable(true);
    	}
    	if(chosenMap.containsKey("S2"))
    	{
    		System.out.print("Contains S2");
    		bS2 = true;
    		S2.setDisable(true);
    	}
    	if(chosenMap.containsKey("S3"))
    	{
    		System.out.print("Contains S3");
    		bS3 = true;
    		S3.setDisable(true);
    	}
    	if(chosenMap.containsKey("S4"))
    	{
    		System.out.print("Contains S4");
    		bS4 = true;
    		S4.setDisable(true);
    	}
    	if(chosenMap.containsKey("S5"))
    	{
    		System.out.print("Contains S5");
    		bS5 = true;
    		S5.setDisable(true);
    	}
    	if(chosenMap.containsKey("S6"))
    	{
    		System.out.print("Contains S6");
    		bS6 = true;
    		S6.setDisable(true);
    	}
    	chosenMap.clear();	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		}

}
