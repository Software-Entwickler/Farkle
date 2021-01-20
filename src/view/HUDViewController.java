package view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.security.auth.Refreshable;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import application.MusicLoader;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import controller.FarkleController;
import controller.GameController;
import javafx.animation.ScaleTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import model.Dice;
import model.Player;

public class HUDViewController extends StackPane implements Refreshable {
	
	private GameController gameController;
	

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
	    
	    @FXML
	    private Slider musicslider;
	    
	    String path = "graphics/farkle/sounds/Barcelona.wav";
	    
	    
	    private Media media ;
	    
	    private MediaPlayer mediaPlayer ;
	    
	    @FXML
	    private ImageView diceArea1 =new ImageView();

	    @FXML
	    private ImageView diceArea2=new ImageView();;

	    @FXML
	    private ImageView diceArea3=new ImageView();;

	    @FXML
	    private ImageView diceArea4=new ImageView();;

	    @FXML
	    private ImageView diceArea5=new ImageView();;

	    @FXML
	    private ImageView diceArea6=new ImageView();;

	    @FXML
		private StackPane stackPane;

	    @FXML
		private AnchorPane anchorPane;

	    private ArrayList<ImageView> imageArea = new ArrayList<>();
	    
	    private FarkleController farkleController;
	    
	    private Stage primaryStage;
	    
	    private ScaleTransition scaleTransition;

	    private ArrayList<Dice> dices;



	    public HUDViewController(Stage primaryStage, FarkleController farkleController)
	    {
	    	imageArea.add(diceArea1);
	    	imageArea.add(diceArea2);
	    	imageArea.add(diceArea3);
	    	imageArea.add(diceArea4);
	    	imageArea.add(diceArea5);
	    	imageArea.add(diceArea6);
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
	    	this.stackPane = this;
	    	
	    	refresh();

	    }
	    
	    @FXML
	    public void initialize() {
	    	
	    	/*String path = "graphics/farkle/sounds/Barcelona.wav";
	    	media=new Media(new File(path).toURI().toString());
	    	mediaPlayer=new MediaPlayer(media);
	    	mediaPlayer.play();*/
	    	mediaPlayer=MusicLoader.mediaPlayerM;
	    	musicslider.setValue(mediaPlayer.getVolume()*100);
	    	musicslider.valueProperty().addListener(new InvalidationListener() {
				@Override
				public void invalidated(Observable observable) {
					
					mediaPlayer.setVolume(musicslider.getValue() / 100);
					
				}
	    	});
	    	
	    	
	    	setScaleTransition(exitB);
	    	setScaleTransition(rulesB);
	    	setScaleTransition(effectsB);
	    	setScaleTransition(tippB);
	    	setScaleTransition(highscoreB);
	    	setScaleTransition(bankB);
	    	setScaleTransition(confirmB);
	    	setScaleTransition(throwB);
	    	
	    	
	    	
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
						InnerShadow ligth = new InnerShadow();
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
	    
	    public void refresh()
		{
	    	ArrayList<ImageView> playerProfil =new ArrayList<>();
	    	playerProfil.add(ply1);
	    	playerProfil.add(ply2);
	    	playerProfil.add(ply3);
	    	playerProfil.add(ply4);
	    	playerProfil.add(ply5);
	    	playerProfil.add(ply6);
	    	playerProfil.add(ply7);
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
				playerProfil.get(i).setVisible(true);
				labelsUsername.get(i).setText(allPlayers.get(i).getUserName());
				labelspoints.get(i).setText("" + allPlayers.get(i).getScore());
			}
		}

		@FXML
	    void bankPressed(MouseEvent event) {
			MusicLoader.loadSound("shining.wav");
	    }

	    @FXML
	    void confirmPressed(MouseEvent event) {
	    	MusicLoader.loadSound("confirm.wav");
	    }

	    @FXML
	    void effectsPressed(MouseEvent event) {
	    	MusicLoader.loadSound("button_click.wav");
	    }

	    @FXML
	    void exitPressed(MouseEvent event) {
	    	MusicLoader.loadSound("game_over.wav");
	    	System.out.println("FUCK YOU");
	    	System.exit(0);

	    }

	    @FXML
	    void highscorePressed(MouseEvent event) {
	    	MusicLoader.loadSound("tada.wav");
			HighScoreViewController highScoreViewController = new HighScoreViewController(this.primaryStage);
			highScoreViewController.setPrefSize(primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());
			stackPane.getChildren().add(highScoreViewController);
	    }

	    @FXML
	    void rulesPressed(MouseEvent event) {
	    	MusicLoader.loadSound("button_click.wav");
	    }

	    @FXML
	    void throwPressed(MouseEvent event) {
	    	MusicLoader.loadSound("dice_throw.wav");
			Pair<ArrayList<String>, ArrayList<Dice>> currentThrow = farkleController.getActionController().throwDice();
	    	ArrayList<String> arr = currentThrow.getKey();
	    	diceArea1.setImage(new Image(arr.get(0)));
	    	diceArea2.setImage(new Image(arr.get(1)));
	    	diceArea3.setImage(new Image(arr.get(2)));
	    	diceArea4.setImage(new Image(arr.get(3)));
	    	diceArea5.setImage(new Image(arr.get(4)));
	    	diceArea6.setImage(new Image(arr.get(5)));

	    	dices = currentThrow.getValue();

		}
	
	    @FXML
	    void tippPressed(MouseEvent event) {

	    	MusicLoader.loadSound("button_click.wav");

			String tip = farkleController.getAIController().takeDecision(dices);

			Text text = new Text(tip);
			text.setFill(Color.DARKGOLDENROD);
			text.setStyle("-fx-font-size: 3em; -fx-font-style: italic;");

			Label label = new Label("Our advice for you:");
			label.setAlignment(Pos.CENTER);
			label.setScaleX(2); label.setScaleY(2);
			label.setStyle("-fx-font-size: 16px; -fx-text-fill-color: #2c061f;");

			Button button = new Button("Close advice");
			button.setAlignment(Pos.CENTER);
			button.setStyle("-fx-background-color: #2c061f; -fx-font-size: 20px;");
			button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #839b97; -fx-font-size: 20px;"));
			button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #2c061f; -fx-font-size: 20px;"));


			VBox vBox = new VBox(label, text, button);
			vBox.setFillWidth(true);
			vBox.setSpacing(50);
			vBox.setAlignment(Pos.CENTER);

			stackPane.getChildren().add(vBox);

			button.setOnMouseClicked(e -> stackPane.getChildren().remove(vBox));

	    }
	    

	    @FXML
	    void diceArea1Pressed(MouseEvent event) {
	    	System.out.println(diceArea1);
	    	diceArea1.setOpacity(0.25);
	    }

	    @FXML
	    void diceArea2Pressed(MouseEvent event) {
	    	diceArea2.setOpacity(0.25);
	    }

	    @FXML
	    void diceArea3Pressed(MouseEvent event) {
	    	diceArea3.setOpacity(0.25);
	    }

	    @FXML
	    void diceArea4Pressed(MouseEvent event) {
	    	diceArea4.setOpacity(0.25);
	    }

	    @FXML
	    void diceArea5Pressed(MouseEvent event) {
	    	diceArea5.setOpacity(0.25);
	    }

	    @FXML
	    void diceArea6Pressed(MouseEvent event) {
	    	diceArea6.setOpacity(0.25);
	    }
	    
		@Override
		public boolean isCurrent() {
			// TODO Auto-generated method stub
			return false;
		}

	
}
	