package view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.security.auth.Refreshable;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import application.AlertS;
import application.MusicLoader;
import controller.FarkleController;
import controller.GameController;
import javafx.animation.ScaleTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Dice;
import model.Player;
import model.Round;

public class HUDViewController extends StackPane implements Refreshable {
	
		@FXML
		private ImageView confirmaa;
		
	    @FXML
	    private ImageView imgPlayer;
	    
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
	    private Label roundL;
	    
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
	    private ImageView diceArea6=new ImageView();
	    
	    @FXML
	    private ImageView timer;
	    
	    	    
	    String path = "graphics/farkle/sounds/Barcelona.wav";
	    
	    private ArrayList<ImageView> imageArea = new ArrayList<>();
	    
	    //private Media media ;
	    
	    private MediaPlayer mediaPlayer ;
	    
	    private FarkleController farkleController;
	    	    	    	    
	    private Stage primaryStage;
	    
	    private ScaleTransition scaleTransition;
	    
	    
	    private HashMap<String, Integer> chosenMap = new HashMap<>();
	     
	    private ArrayList<String> thrownDicesStrings = new ArrayList<>();
	    
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
	    	
	    	refresh();

	    }
	    
	    @FXML
	    public void initialize() {
	    	
	    	if(OptionViewController.backGround!=null)
	    		imgPlayer.setImage(new Image("file:src/view/graphics/"+OptionViewController.backGround+".jpeg"));
	    	else
	    		imgPlayer.setImage(new Image("file:src/view/bsic_background.png"));

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
						String str = source.getId();
						if(chosenMap.containsKey(str)) {
							chosenMap.remove(str);
							source.setOpacity(1);
						}
						else {
							String[] splitArray = str.split("diceArea");
							int numberOfDice = (Integer) Integer.parseInt(splitArray[1]);
							chosenMap.put(str, (Integer) Integer.parseInt(thrownDicesStrings.get(numberOfDice - 1)));
								source.setOpacity(0.25);
						}
					}
					event.consume();
				}
	    	});
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
	    	throwB.setOpacity(1);
	    	throwB.setDisable(false);
	    	
	    	roundL.setText("Round: " + farkleController.getFarkle().getCurrentGame().getCurrentRound().getRoundNum());	  
	    	
	    	if(farkleController.getFarkle().getCurrentGame().getCurrentPlayer().getRoundScore() < 250 )
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
				if(farkleController.getFarkle().getCurrentGame().getCurrentPlayer().equals(allPlayers.get(i)))
				{
					playerProfil.get(i).setBlendMode(BlendMode.RED);
				}
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
	    	
	    	Player currentPlayer = farkleController.getFarkle().getCurrentGame().getCurrentPlayer();
	    	Round currentRound = farkleController.getFarkle().getCurrentGame().getCurrentRound();
	    	
	    	thrownDicesStrings = farkleController.getActionController().throwDice(currentPlayer);
	    	
	    	for(int i = 0; i < imageArea.size(); i++)
	    	{
	    		imageArea.get(i).setImage(new Image("file:src/view/dice"+ thrownDicesStrings.get(i) + ".png"));
	    	}
	    	
	    	for(int i = 0; i < currentPlayer.getDice().size(); i++)
	    	{
	    		if(currentPlayer.getDice().get(i).isUsedBefore())
	    		{
	    			imageArea.get(i).setVisible(false);
	    			imageArea.get(i).setDisable(true);
	    		}
	    		else
	    		{
	    			imageArea.get(i).setVisible(true);
	    			imageArea.get(i).setDisable(false);
	    		}
	    	}

	    	 
	    	if(farkleController.getRulesController().isOneFarkle(currentRound, currentPlayer))
	    	{
	    		AlertS.showAlert(AlertType.WARNING, "Warning", "", "Farkle");
	    		farkleController.getRoundController().setNextPlayer();
	    		resetField();
		    	throwB.setDisable(false);
		    	throwB.setOpacity(1);
		    	refresh();
		    	
		    	for(int i = 0; i < currentPlayer.getDice().size(); i++)
		    	{
		    		if(!currentPlayer.getDice().get(i).isUsed())
		    		{
		    			imageArea.get(i).setVisible(true);
		    		}
		    	}
	    	}
	    }

	    @FXML
	    void confirmPressed(MouseEvent event) {
	    	MusicLoader.loadSound("confirm.wav");
	    	if(!chosenMap.isEmpty())
	    	{
	    		ArrayList<Integer> chosen = new ArrayList<>(chosenMap.values());
	    		ArrayList<Dice> chosenDices = new ArrayList<>();
	    		for(int i = 0; i < chosen.size(); i++)
	    		{
	    			chosenDices.add(new Dice(chosen.get(i)));
	    		}
	    		if(farkleController.getRulesController().isValidCollection(chosenDices))
	    		{
	    			refreshPlayerDices();
	    			Player currentPlayer = farkleController.getFarkle().getCurrentGame().getCurrentPlayer();
		    		farkleController.getActionController().confirm(currentPlayer.getDice());
		    		if(currentPlayer.getTakenDices() == 0)
		    		{
		    			resetField();
		    		}
		    		else
		    		{
		    			refreshField();
		    		}
		    		
	    			refresh();
	    		}
	    		else
	    		{
	    			AlertS.showAlert(AlertType.WARNING, "Warning", "", "Wahl ist nicht gültig");
	    		}
	    	}
	    	else
	    	{
	    		AlertS.showAlert(AlertType.WARNING, "Warning", "", "Keine Würfeln gewählt");
	    	}
	    }
	    
		@FXML
	    void bankPressed(MouseEvent event) {
			MusicLoader.loadSound("shining.wav");
			farkleController.getRoundController().setNextPlayer();
			resetField();
	    	refresh();
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
	    }

	    @FXML
	    void rulesPressed(MouseEvent event) {
	    	MusicLoader.loadSound("button_click.wav");
	    }

	    
	    
	    @FXML
	    void tippPressed(MouseEvent event) {
	    	MusicLoader.loadSound("button_click.wav");
	    }
	    

	   
	    
	    private void refreshField()
	    {

	    	chosenMap.clear();
	    	diceArea1.setDisable(true);
    		diceArea2.setDisable(true);
    		diceArea3.setDisable(true);
    		diceArea4.setDisable(true);
    		diceArea5.setDisable(true);
    		diceArea6.setDisable(true);
	    }
	    
	    private void resetField()
	    {
	    	chosenMap.clear();
	    	diceArea1.setOpacity(1);
	    	diceArea2.setOpacity(1);
	    	diceArea3.setOpacity(1);
	    	diceArea4.setOpacity(1);
	    	diceArea5.setOpacity(1);
	    	diceArea6.setOpacity(1);
	    	diceArea1.setDisable(true);
    		diceArea2.setDisable(true);
    		diceArea3.setDisable(true);
    		diceArea4.setDisable(true);
    		diceArea5.setDisable(true);
    		diceArea6.setDisable(true);
    		diceArea1.setVisible(false);
	    	diceArea2.setVisible(false);
	    	diceArea3.setVisible(false);
	    	diceArea4.setVisible(false);
	    	diceArea5.setVisible(false);
	    	diceArea6.setVisible(false);
	    }
	    
	    private void refreshPlayerDices()
	    {
	    	ArrayList<Dice> playerDices = farkleController.getFarkle().getCurrentGame().getCurrentPlayer().getDice();
	    	 
	    	Set<String> mapKeys = chosenMap.keySet();
	    	ArrayList<String> arr =  new ArrayList<>(mapKeys);
	    	
	    	for(int i = 0; i < 6; i++)
	    	{
	    		if(arr.contains(imageArea.get(i).getId()))
	    		{
	    			playerDices.get(i).setUsed(true);
	    		}
	    	}
	    }
	    
		@Override
		public boolean isCurrent() {
			// TODO Auto-generated method stub
			return false;
		}
}