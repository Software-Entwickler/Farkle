
package view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

import javax.security.auth.Refreshable;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import application.AlertS;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
import javafx.util.Pair;
import model.Dice;
import model.Player;
import model.Round;

public class HUDViewController extends StackPane implements Refreshable {
	
		@FXML
		private ImageView confirmaa;

	    @FXML
	    private ImageView imgPlayer ;

	    @FXML
	    private ImageView farkle;


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

	    @FXML
		private StackPane stackPane;

	    private ArrayList<Dice> allDices = new ArrayList<>();;

		private boolean bS1 , bS2, bS3, bS4, bS5, bS6;


	    private ArrayList<ImageView> imageArea = new ArrayList<>();
	    
	    String path = "graphics/farkle/sounds/Barcelona.wav";
	    

	    private MediaPlayer mediaPlayer ;

	    private FarkleController farkleController;
	    
	    private Stage primaryStage;
	    
	    private ScaleTransition scaleTransition;
	    
	    boolean bankPressed = false;
	    @FXML
	    private ImageView timer;
	    
	    private ArrayList<String> arr = new ArrayList<>();

	    ArrayList <Dice> choiceDices = new ArrayList<>();
	    ArrayList <ImageView> chosenImageView = new ArrayList<>();

	    private ArrayList<Dice> dices;



	    public HUDViewController(Stage primaryStage, FarkleController farkleController)
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
	    	this.stackPane = this;

	    	refresh();

	    }
	    
	    @FXML
	    public void initialize() {
	    	if(OptionViewController.backGround!=null)
	    		imgPlayer.setImage(new Image("file:src/view/graphics/"+OptionViewController.backGround+".jpeg"));
	    	else
	    		imgPlayer.setImage(new Image("file:src/view/bsic_background.png"));


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
	    	imageArea.add(diceArea1);
	    	imageArea.add(diceArea2);
	    	imageArea.add(diceArea3);
	    	imageArea.add(diceArea4);
	    	imageArea.add(diceArea5);
	    	imageArea.add(diceArea6);
	    	
	    	setScaleTransition(exitB);
	    	setScaleTransition(rulesB);
	    	setScaleTransition(effectsB);
	    	setScaleTransition(tippB);
	    	setScaleTransition(highscoreB);
	    	setScaleTransition(bankB);
	    	setScaleTransition(confirmB);
	    	setScaleTransition(throwB);
	    	setChoiceDice(diceArea1);
	    	setChoiceDice(diceArea2);
	    	setChoiceDice(diceArea3);
	    	setChoiceDice(diceArea4);
	    	setChoiceDice(diceArea5);
	    	setChoiceDice(diceArea6);
	    }

	    public void setChoiceDice(ImageView imageView) {
	    	imageView.setOnMouseClicked(new EventHandler <MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					if(event.getSource() instanceof ImageView) {
						ImageView source = (ImageView) event.getSource();
						Dice dice = getDiceOf(source);
						if(dice!=null) {
							if(farkleController.getFarkle().getCurrentGame().getCurrentPlayer().diceIsSelected(dice)) {
								farkleController.getFarkle().getCurrentGame().getCurrentPlayer().deselectDice(dice);
								chosenImageView.remove(source);

								source.setOpacity(1);
							}
							else {
								chosenImageView.add(source);
								farkleController.getFarkle().getCurrentGame().getCurrentPlayer().selectDice(dice);
								source.setOpacity(0.25);
							}
						}
					}
					event.consume();
				}
	    	});
	    }
	    
	    private Dice getDiceOf(ImageView imageView) {
			ArrayList<Dice> fieldDices = farkleController.getFarkle().getCurrentGame().getCurrentPlayer().getDice();
			switch (imageView.getId()) {
			case "diceArea1":
				return fieldDices.get(0);
			case "diceArea2":
				return fieldDices.get(1);
			case "diceArea3":
				return fieldDices.get(2);
			case "diceArea4":
				return fieldDices.get(3);
			case "diceArea5":
				return fieldDices.get(4);
			case "diceArea6":
				return fieldDices.get(5);

			}
			return null;
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
			bankPressed=true;
			MusicLoader.loadSound("shining.wav");
			farkleController.getRoundController().setNextPlayer();
	    	nextPlayerOrRound();
	    	throwB.setDisable(false);
	    	throwB.setOpacity(1);
	    	chosenImageView.clear();

	    	refresh();
	    }

	    @FXML
	    void confirmPressed(MouseEvent event) {

	    	MusicLoader.loadSound("confirm.wav");
	    	choiceDices=farkleController.getFarkle().getCurrentGame().getCurrentPlayer().getDicesSelection();
	    	if(choiceDices.size()!=0)
	    	{
	    		System.out.println("choiceDices " + choiceDices.size());
	    		throwB.setDisable(false);
	    		throwB.setOpacity(1);
	    		if(farkleController.getActionController().confirm(choiceDices)!=0) {
	    			choiceDices.clear();
	    			try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			timer.setImage(new Image("file:src/view/graphics/timerYou.gif"));
	    			refreshField();
		    		refresh();
	    		}
	    	}
	    	if(chosenImageView.size()==6) {
	    		throwB.setDisable(false);
	    		throwB.setOpacity(1);
	    		choiceDices.clear();
	    	}

	    }


	    @FXML
	    void throwPressed(MouseEvent event) {

	    	MusicLoader.loadSound("dice_throw.wav");

	    	throwB.setOpacity(0.25);
	    	throwB.setDisable(true);
	    	confirmB.setOpacity(1);
	    	confirmB.setDisable(false);
	    	bankB.setOpacity(0.25);
	    	bankB.setDisable(true);

	    	if(bankPressed) {
	    		setImageViewVisible(true);
	    		chosenImageView.clear();
	    		bankPressed=false;
	    	}

	    	ArrayList<Dice>dices=new ArrayList<>();
	    	for(int i=0;i<6;i++) {
	    		Random rand =new Random();
	    		if(imageViewIsSelected(imageArea.get(i)) && chosenImageView.size()!=6) {
	    			imageArea.get(i).setImage(null);
	    			dices.add(null);
	    		}

    			else {
    				int randNumber = rand.nextInt ((6-1)+1)+1;
    				dices.add(new Dice(randNumber));
    				imageArea.get(i).setImage(new Image("file:src/view/dice"+ randNumber + ".png"));

    			}
	    	}

	    	allDices = dices;
			allDices.removeIf(Objects::isNull);


			farkleController.getFarkle().getCurrentGame().getCurrentPlayer().setDice(dices);

            if(chosenImageView.size()==6) {
            	chosenImageView.clear();
	    		setImageViewVisible(true);
	    		diceArea1.setOpacity(1);
	    		diceArea2.setOpacity(1);
	    		diceArea3.setOpacity(1);
	    		diceArea4.setOpacity(1);
	    		diceArea5.setOpacity(1);
	    		diceArea6.setOpacity(1);
	    	}

            Player currentPlayer = farkleController.getFarkle().getCurrentGame().getCurrentPlayer();

			if(farkleController.getRulesController().isOneFarkle( currentPlayer)) {
				  farkle.setImage(new Image("file:src/view/graphics/farkle.gif"));
				  MusicLoader.loadSound("farkle2.wav");
				  farkleController.getRoundController().setNextPlayer();
					throwB.setDisable(false);
		    		throwB.setOpacity(1);
			    	nextPlayerOrRound();
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

	    /*
	    @FXML
	    void throwPressed(MouseEvent event) {
	    	MusicLoader.loadSound("dice_throw.wav");

			throwB.setOpacity(0.25);
			throwB.setDisable(true);
			confirmB.setOpacity(1);
			confirmB.setDisable(false);
			bankB.setOpacity(0.25);
			bankB.setDisable(true);


			Pair<ArrayList<String>, ArrayList<Dice>> pair = farkleController.getActionController().throwDice();

	    	arr = pair.getKey();
			diceArea1.setImage(new Image("file:src/view/dice"+ arr.get(0) + ".png"));
			diceArea2.setImage(new Image("file:src/view/dice"+ arr.get(1) + ".png"));
			diceArea3.setImage(new Image("file:src/view/dice"+ arr.get(2) + ".png"));
			diceArea4.setImage(new Image("file:src/view/dice"+ arr.get(3) + ".png"));
			diceArea5.setImage(new Image("file:src/view/dice"+ arr.get(4) + ".png"));
			diceArea6.setImage(new Image("file:src/view/dice"+ arr.get(5) + ".png"));

	    	allDices = pair.getValue();
	    	Dice d1 = allDices.get(0);
			Dice d2 = allDices.get(1);
			Dice d3 = allDices.get(2);
			Dice d4 = allDices.get(3);
			Dice d5 = allDices.get(4);
			Dice d6 = allDices.get(5);

			if(!bS1)
			{
				diceArea1.setVisible(true);
				diceArea1.setDisable(false);
			}
			else
			{
				diceArea1.setVisible(false);
				diceArea1.setDisable(true);
				allDices.remove(d1);
			}
			if(!bS2)
			{
				diceArea2.setVisible(true);
				diceArea2.setDisable(false);
			}
			else
			{
				diceArea2.setVisible(false);
				diceArea2.setDisable(true);
				allDices.remove(d2);
			}
			if(!bS3)
			{
				diceArea3.setVisible(true);
				diceArea3.setDisable(false);
			}
			else
			{
				diceArea3.setVisible(false);
				diceArea3.setDisable(true);
				allDices.remove(d3);
			}
			if(!bS4)
			{
				diceArea4.setVisible(true);
				diceArea4.setDisable(false);
			}
			else
			{
				diceArea4.setVisible(false);
				diceArea4.setDisable(true);
				allDices.remove(d4);
			}
			if(!bS5)
			{
				diceArea5.setVisible(true);
				diceArea5.setDisable(false);
			}
			else
			{
				diceArea5.setVisible(false);
				diceArea5.setDisable(true);
				allDices.remove(d5);
			}
			if(!bS6)
			{
				diceArea6.setVisible(true);
				diceArea6.setDisable(false);
			}
			else
			{
				diceArea6.setVisible(false);
				diceArea6.setDisable(true);
				allDices.remove(d6);
			}

	    }

	     */
	


	    private boolean imageViewIsSelected(ImageView imageView) {
	    	for(int i=0;i<chosenImageView.size();i++) {
	    		if(imageView.getId().contains(chosenImageView.get(i).getId()))
	    			return true;
	    	}
	    	return false;
	    }
	    @FXML
	    void tippPressed(MouseEvent event) {

	    	MusicLoader.loadSound("button_click.wav");

			String tip = farkleController.getAIController().takeDecision(allDices);

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
	    }

	    @FXML
	    void diceArea2Pressed(MouseEvent event) {
	    }

	    @FXML
	    void diceArea3Pressed(MouseEvent event) {
	    }
	    @FXML
	    void diceArea4Pressed(MouseEvent event) {
	    }

	    @FXML
	    void diceArea5Pressed(MouseEvent event) {
	    }

	    @FXML
	    void diceArea6Pressed(MouseEvent event) {
	    }

	    private void setImageViewVisible(boolean set) {
	    	for(int i=0;i<imageArea.size();i++)
	    		imageArea.get(i).setVisible(set);
	    }

	    private void refreshField()
	    {
	    	for(int i=0;i<chosenImageView.size();i++) {
	    		chosenImageView.get(i).setVisible(false);
	    	}
	    }

	    private void nextPlayerOrRound()
	    {
	    	setImageViewVisible(false);
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

