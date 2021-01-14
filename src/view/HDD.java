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

public class HDD extends StackPane implements Initializable {

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

    public HDD(Stage primaryStage, FarkleController farkleController)				//Constructor
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





/*package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.FarkleController;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Player;

public class HUDViewController extends StackPane implements Initializable {
	

    @FXML
    private ImageView confirmaa;
	    @FXML
	    private ImageView imgPlayer;

	    @FXML
	    private HBox menubar;

	    @FXML
	    private ImageView exitB;

	    @FXML
	    private ImageView rulesB;

	    @FXML
	    private ImageView tippB;

	    @FXML
	    private ImageView highscoreB;

	    @FXML
	    private ImageView effectsB;

	    @FXML
	    private ImageView bankB;

	    @FXML
	    private ImageView confirmB;

	    @FXML
	    private ImageView throwB;

	    @FXML
	    private ImageView ply1;

	    @FXML
	    private ImageView ply2;

	    @FXML
	    private ImageView ply3;

	    @FXML
	    private ImageView ply4;

	    @FXML
	    private ImageView ply5;

	    @FXML
	    private ImageView ply6;

	    @FXML
	    private ImageView ply7;

	    @FXML
	    private Label ply1username;

	    @FXML
	    private Label ply1points;

	    @FXML
	    private Label ply2username;

	    @FXML
	    private Label ply2points;

	    @FXML
	    private Label ply3username;

	    @FXML
	    private Label ply3points;

	    @FXML
	    private Label ply4username;

	    @FXML
	    private Label ply4points;

	    @FXML
	    private Label ply5username;

	    @FXML
	    private Label ply5points;

	    @FXML
	    private Label ply6username;

	    @FXML
	    private Label ply6points;

	    @FXML
	    private Label ply7username;

	    @FXML
	    private Label ply7points;
	    
	    private FarkleController farkleController;
	    
	    private Stage primaryStage;
	    
	    private ScaleTransition scaleTransition;
	    
	    

	    
	    public HUDViewController(Stage primaryStage, FarkleController farkleController)				//Constructor
	    {
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HUD1.fxml"));
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
	    
	    public void refresh()
		{
	    	ArrayList<Label> labelsUsername=new ArrayList<>();
	    	labelsUsername.add(ply1username);
	    	labelsUsername.add(ply2username);
	    	labelsUsername.add(ply3username);
	    	labelsUsername.add(ply4username);
	    	labelsUsername.add(ply5username);
	    	labelsUsername.add(ply6username);
	    	labelsUsername.add(ply7username);
	    	ArrayList<Label> labelspoints=new ArrayList<>();
	    	labelspoints.add(ply1points);
	    	labelspoints.add(ply2points);
	    	labelspoints.add(ply3points);
	    	labelspoints.add(ply4points);
	    	labelspoints.add(ply5points);
	    	labelspoints.add(ply6points);
	    	labelspoints.add(ply7points);
	    	
			ArrayList<Player> allPlayers = farkleController.getFarkle().getCurrentGame().getPlayers();
			for(int i=0;i<allPlayers.size();i++){
				labelsUsername.get(i).setText(allPlayers.get(i).getUserName());
				labelspoints.get(i).setText("0");
			}
				
			
		}
	    
	    @FXML
	    public void initialize() {
	    	exitB.setImage(new Image("file:src/view/button_exit.png"));
	    	
	    	setScaleTransition(exitB);
	    	setScaleTransition(rulesB);
	    	setScaleTransition(effectsB);
	    	setScaleTransition(tippB);
	    	setScaleTransition(highscoreB);
	    	setScaleTransition(bankB);
	    	setScaleTransition(confirmB);
	    	setScaleTransition(throwB);
	    	setScaleTransition(ply6);
	    	
	    }
	    
	    private void setScaleTransition(ImageView imageView)
	    {
	    	imageView.setOnMouseEntered(new EventHandler <MouseEvent>() {
				public void handle(MouseEvent event) {
					if(event.getSource() instanceof  ImageView)
					{
						ImageView source = (ImageView)event.getSource();
						scaleTransition = new ScaleTransition(Duration.seconds(0.3), source);
						scaleTransition.setToX(1.01);
						scaleTransition.setToY(1.01);
						scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);
						scaleTransition.setAutoReverse(true);
						scaleTransition.play();
					}
					event.consume();
				}
			});
	    	
	    	imageView.setOnMouseExited(new EventHandler <MouseEvent>() {
				public void handle(MouseEvent event) {
					if(event.getSource() instanceof  ImageView)
					{
						ImageView source = (ImageView)event.getSource();
						source.setScaleX(1.0);
						source.setScaleY(1.0);
						scaleTransition.stop();
					}
					event.consume();
				}
			});
	    	
	    	imageView.setOnMousePressed(new EventHandler <MouseEvent>() {
				public void handle(MouseEvent event) {
					if(event.getSource() instanceof  ImageView)
					{
						ImageView source = (ImageView)event.getSource();
						DropShadow borderGlow= new DropShadow();
						borderGlow.setOffsetY(0f);
						borderGlow.setOffsetX(0f);
						borderGlow.setColor(Color.RED);
						borderGlow.setWidth(30);
						borderGlow.setHeight(30);
						source.setEffect(borderGlow);
					}
					event.consume();
				}
			});
	    	
	    	imageView.setOnMouseReleased(new EventHandler <MouseEvent>() {
				public void handle(MouseEvent event) {
					if(event.getSource() instanceof  ImageView)
					{
						ImageView source = (ImageView)event.getSource();
						source.setEffect(new ColorAdjust());
					}
					event.consume();
				}
			});
	    	
	    	
	    }

	    @FXML
	    void bankPressed(MouseEvent event) {

	    }

	    @FXML
	    void confirmPressed(MouseEvent event) {

	    }

	    @FXML
	    void effectsPressed(MouseEvent event) {

	    }

	    @FXML
	    void exitPressed(MouseEvent event) {
	    	System.out.println("FUCK YOU");
	    	System.exit(0);

	    }

	    @FXML
	    void highscorePressed(MouseEvent event) {

	    }

	    @FXML
	    void rulesPressed(MouseEvent event) {

	    }

	    @FXML
	    void throwPressed(MouseEvent event) {

	    }

	    @FXML
	    void tippPressed(MouseEvent event) {

	    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	/*

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

    public HUDViewController(Stage primaryStage, FarkleController farkleController, ArrayList<Player> gamePlayers)				//Constructor
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
		*/




