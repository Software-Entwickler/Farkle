package view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.BlendMode;
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
import model.Player;
import model.Round;

public class HUDViewController extends StackPane implements Refreshable {
	
		@FXML
		private ImageView confirmaa;

	    @FXML
	    private ImageView imgPlayer;

	    @FXML
	    private HBox menubar;

	    @FXML
	    private ImageView exitB, rulesB, tippB, highscoreB, effectsB, bankB, confirmB, throwB;

	    @FXML
	    private ImageView ply1, ply2, ply3, ply4, ply5, ply6, ply7;

	    @FXML
	    private Label ply1username, ply2username, ply3username, ply4username, ply5username, ply6username, ply7username;

	    @FXML
	    private Label ply1points, ply2points, ply3points, ply4points, ply5points, ply6points, ply7points;
	    
	    @FXML
	    private Slider musicslider;

	    @FXML
	    private ImageView diceArea1 =new ImageView();

	    @FXML
	    private ImageView diceArea2=new ImageView();

	    @FXML
	    private ImageView diceArea3=new ImageView();

	    @FXML
	    private ImageView diceArea4=new ImageView();

	    @FXML
	    private ImageView diceArea5=new ImageView();

	    @FXML
	    private ImageView diceArea6=new ImageView();;

	    
	    private ArrayList<ImageView> imageArea = new ArrayList<>();
	    
	    String path = "graphics/farkle/sounds/Barcelona.wav";

	    //private Media media ;

	    private MediaPlayer mediaPlayer ;

	    private FarkleController farkleController;
	    
	    private Stage primaryStage;
	    
	    private ScaleTransition scaleTransition;

	    private HashMap<String, Integer> chosenMap = new HashMap<>();

	    private boolean bS1 , bS2, bS3, bS4, bS5, bS6 = false;

	    private ArrayList<String> arr = new ArrayList<>();

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
	    	confirmB.setOpacity(0.25);
	    	confirmB.setDisable(true);


	    	Player currentPlayer = farkleController.getFarkle().getCurrentGame().getCurrentPlayer();

	    	if(currentPlayer.getScore() < 200 )
	    	{
	    		bankB.setOpacity(0.25);
		    	bankB.setDisable(true);
	    	}
	    	else
	    	{
	    		bankB.setOpacity(1);
		    	bankB.setDisable(false);
	    	}


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
				playerProfil.get(i).setBlendMode(null);
				if(currentPlayer.equals(allPlayers.get(i)))
				{
					playerProfil.get(i).setBlendMode(BlendMode.RED);
				}
			}
		}

		@FXML
	    void bankPressed(MouseEvent event) {
			MusicLoader.loadSound("shining.wav");
			farkleController.getRoundController().setNextPlayer();
	    	nextPlayerOrRound();
	    	throwB.setDisable(false);
	    	throwB.setOpacity(1);
	    	diceArea1.setVisible(false);
	    	diceArea2.setVisible(false);
	    	diceArea3.setVisible(false);
	    	diceArea4.setVisible(false);
	    	diceArea5.setVisible(false);
	    	diceArea6.setVisible(false);
	    	refresh();
	    }

	    @FXML
	    void confirmPressed(MouseEvent event) {
	    	MusicLoader.loadSound("confirm.wav");
	    	if(!chosenMap.isEmpty())
	    	{
	    		throwB.setDisable(false);
	    		throwB.setOpacity(1);
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
	    void effectsPressed(MouseEvent event) {
	    	MusicLoader.loadSound("button_click.wav");
	    }

	    @FXML
	    void exitPressed(MouseEvent event) {
	    	MusicLoader.loadSound("game_over.wav");
	    	farkleController.getFarkle().setCurrentGame(null);
	    	MainWindowViewController mainWindowViewController = new MainWindowViewController(this.primaryStage);
			Scene scene = new Scene(mainWindowViewController , primaryStage.getScene().getWidth() , primaryStage.getScene().getHeight());
			primaryStage.setScene(scene);
			primaryStage.show();
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
	    	ArrayList<String> arr=farkleController.getActionController().throwDice();
	    	diceArea1.setImage(new Image(arr.get(0)));
	    	diceArea2.setImage(new Image(arr.get(1)));
	    	diceArea3.setImage(new Image(arr.get(2)));
	    	diceArea4.setImage(new Image(arr.get(3)));
	    	diceArea5.setImage(new Image(arr.get(4)));
	    	diceArea6.setImage(new Image(arr.get(5)));
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

	    	if(chosenMap.containsKey("diceArea1"))
	    	{
	    		diceArea1.setOpacity(1);
	    		chosenMap.remove("diceArea1");
	    	}
	    	else
	    	{
	    		diceArea1.setOpacity(0.25);
	    		chosenMap.put("diceArea1", (Integer) Integer.parseInt(arr.get(0)));
	    	}
	    }

	    @FXML
	    void diceArea2Pressed(MouseEvent event) {
	    	if(chosenMap.containsKey("diceArea2"))
	    	{
	    		diceArea2.setOpacity(1);
	    		chosenMap.remove("diceArea2");
	    	}
	    	else
	    	{
	    		diceArea2.setOpacity(0.25);
	    		chosenMap.put("diceArea2", (Integer) Integer.parseInt(arr.get(1)));
	    	}
	    }

	    @FXML
	    void diceArea3Pressed(MouseEvent event) {
	    	if(chosenMap.containsKey("diceArea3"))
	    	{
	    		diceArea3.setOpacity(1);
	    		chosenMap.remove("diceArea3");
	    	}
	    	else
	    	{
	    		diceArea3.setOpacity(0.25);
	    		chosenMap.put("diceArea3", (Integer) Integer.parseInt(arr.get(2)));
	    	}
	    }

	    @FXML
	    void diceArea4Pressed(MouseEvent event) {
	    	if(chosenMap.containsKey("diceArea4"))
	    	{
	    		diceArea4.setOpacity(1);
	    		chosenMap.remove("diceArea4");
	    	}
	    	else
	    	{
	    		diceArea4.setOpacity(0.25);
	    		chosenMap.put("diceArea4", (Integer) Integer.parseInt(arr.get(3)));
	    	}
	    }

	    @FXML
	    void diceArea5Pressed(MouseEvent event) {
	    	if(chosenMap.containsKey("diceArea5"))
	    	{
	    		diceArea5.setOpacity(1);
	    		chosenMap.remove("diceArea5");
	    	}
	    	else
	    	{
	    		diceArea5.setOpacity(0.25);
	    		chosenMap.put("diceArea5", (Integer) Integer.parseInt(arr.get(4)));
	    	}
	    }

	    @FXML
	    void diceArea6Pressed(MouseEvent event) {
	    	if(chosenMap.containsKey("diceArea6"))
	    	{
	    		diceArea6.setOpacity(1);
	    		chosenMap.remove("diceArea6");
	    	}
	    	else
	    	{
	    		diceArea6.setOpacity(0.25);
	    		chosenMap.put("diceArea6", (Integer) Integer.parseInt(arr.get(5)));
	    	}
	    }

	    private void refreshField()
	    {
	    	if(chosenMap.containsKey("diceArea1"))
	    	{
	    		System.out.print("Contains diceArea1");
	    		bS1 = true;
	    	}
	    	if(chosenMap.containsKey("diceArea2"))
	    	{
	    		System.out.print("Contains diceArea2");
	    		bS2 = true;
	    	}
	    	if(chosenMap.containsKey("diceArea3"))
	    	{
	    		System.out.print("Contains diceArea3");
	    		bS3 = true;
	    	}
	    	if(chosenMap.containsKey("diceArea4"))
	    	{
	    		System.out.print("Contains diceArea4");
	    		bS4 = true;
	    	}
	    	if(chosenMap.containsKey("diceArea5"))
	    	{
	    		System.out.print("Contains diceArea5");
	    		bS5 = true;
	    	}
	    	if(chosenMap.containsKey("diceArea6"))
	    	{
	    		System.out.print("Contains diceArea6");
	    		bS6 = true;
	    	}
	    	chosenMap.clear();
	    	diceArea1.setDisable(true);
    		//diceArea1.setVisible(false);
    		diceArea2.setDisable(true);
    		//diceArea2.setVisible(false);
    		diceArea3.setDisable(true);
    		//diceArea3.setVisible(false);
    		diceArea4.setDisable(true);
    		//diceArea4.setVisible(false);
    		diceArea5.setDisable(true);
    		//diceArea5.setVisible(false);
    		diceArea6.setDisable(true);
    		//diceArea6.setVisible(false);
	    }

	    private void nextPlayerOrRound()
	    {
	    	bS1 = false;
	    	bS2 = false;
	    	bS3 = false;
	    	bS4 = false;
	    	bS5 = false;
	    	bS6 = false;
	    	diceArea1.setOpacity(1);
	    	diceArea2.setOpacity(1);
	    	diceArea3.setOpacity(1);
	    	diceArea4.setOpacity(1);
	    	diceArea5.setOpacity(1);
	    	diceArea6.setOpacity(1);
	    }
	    
		@Override
		public boolean isCurrent() {
			// TODO Auto-generated method stub
			return false;
		}

	
}