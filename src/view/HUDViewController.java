package view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
	    
	    
	    private ArrayList<ImageView> imageArea = new ArrayList<>();
	    
	    String path = "graphics/farkle/sounds/Barcelona.wav";
	    
	    //private Media media ;
	    
	    private MediaPlayer mediaPlayer ;
	    
	    private FarkleController farkleController;
	    	    	    	    
	    private Stage primaryStage;
	    
	    private ScaleTransition scaleTransition;
	    
	    private HashMap<String, Integer> chosenMap = new HashMap<>();
	    
	    private boolean bS1 , bS2, bS3, bS4, bS5, bS6;
	    
	    private ArrayList<String> thrownDicesStrings = new ArrayList<>();

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
	    	throwB.setOpacity(1);
	    	throwB.setDisable(false);
	    	
	    	roundL.setText("Round: " + farkleController.getFarkle().getCurrentGame().getCurrentRound().getRoundNum());	  
	    	
	    	if(farkleController.getFarkle().getCurrentGame().getCurrentPlayer().getRoundScore() < 200 )
	    	{
	    		bankB.setOpacity(0.25);
		    	bankB.setDisable(true);
	    	}
	    	else
	    	{
	    		bankB.setOpacity(1);
		    	bankB.setDisable(false);
	    	}
	    	
	    	/*bS1 = false;
	    	bS2 = false;
	    	bS3 = false;
	    	bS4 = false;
	    	bS5 = false;
	    	bS6 = false;*/
	    	
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
	    	diceArea1.setImage(new Image("file:src/view/dice"+ thrownDicesStrings.get(0) + ".png"));
	    	diceArea2.setImage(new Image("file:src/view/dice"+ thrownDicesStrings.get(1) + ".png"));
	    	diceArea3.setImage(new Image("file:src/view/dice"+ thrownDicesStrings.get(2) + ".png"));
	    	diceArea4.setImage(new Image("file:src/view/dice"+ thrownDicesStrings.get(3) + ".png"));
	    	diceArea5.setImage(new Image("file:src/view/dice"+ thrownDicesStrings.get(4) + ".png"));
	    	diceArea6.setImage(new Image("file:src/view/dice"+ thrownDicesStrings.get(5) + ".png"));
	    	
	    	if(!bS1)
	    	{
	    		diceArea1.setVisible(true);
	    		diceArea1.setDisable(false);
	    	}
	    	else
	    	{
	    		diceArea1.setVisible(false);
	    		diceArea1.setDisable(true);
	    		//currentPlayer.getDice().get(0).setValue(0);
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
	    		//currentPlayer.getDice().get(1).setValue(0);
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
	    		//currentPlayer.getDice().get(2).setValue(0);
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
	    		//currentPlayer.getDice().get(3).setValue(0);
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
	    		//currentPlayer.getDice().get(4).setValue(0);
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
	    		//currentPlayer.getDice().get(5).setValue(0);
	    	}
	    	 
	    	if(farkleController.getRulesController().isOneFarkle(currentRound, currentPlayer))
	    	{
	    		AlertS.showAlert(AlertType.WARNING, "Warning", "", "Farkle");
	    		farkleController.getRoundController().setNextPlayer();
	    		resetField();
		    	throwB.setDisable(false);
		    	throwB.setOpacity(1);
		    	refresh();
	    	}
	    }
	    
	    @FXML
	    void diceArea1Pressed(MouseEvent event) {
	    	
	    	if(chosenMap.containsKey("diceArea1"))
	    	{
	    		diceArea1.setOpacity(1);
	    		chosenMap.remove("diceArea1");
	    		//farkleController.getFarkle().getCurrentGame().getCurrentPlayer().getDice().get(0).setUsed(false);
	    	}
	    	else
	    	{
	    		diceArea1.setOpacity(0.25);
	    		chosenMap.put("diceArea1", (Integer) Integer.parseInt(thrownDicesStrings.get(0)));
	    		//farkleController.getFarkle().getCurrentGame().getCurrentPlayer().getDice().get(0).setUsed(true);
	    	}
	    }

	    @FXML
	    void diceArea2Pressed(MouseEvent event) {
	    	if(chosenMap.containsKey("diceArea2"))
	    	{
	    		diceArea2.setOpacity(1);
	    		chosenMap.remove("diceArea2");
	    		//farkleController.getFarkle().getCurrentGame().getCurrentPlayer().getDice().get(1).setUsed(false);
	    	}
	    	else
	    	{
	    		diceArea2.setOpacity(0.25);
	    		chosenMap.put("diceArea2", (Integer) Integer.parseInt(thrownDicesStrings.get(1)));
	    		//farkleController.getFarkle().getCurrentGame().getCurrentPlayer().getDice().get(1).setUsed(true);
	    	}
	    }

	    @FXML
	    void diceArea3Pressed(MouseEvent event) {
	    	if(chosenMap.containsKey("diceArea3"))
	    	{
	    		diceArea3.setOpacity(1);
	    		chosenMap.remove("diceArea3");
	    		//farkleController.getFarkle().getCurrentGame().getCurrentPlayer().getDice().get(2).setUsed(false);
	    	}
	    	else
	    	{
	    		diceArea3.setOpacity(0.25);
	    		chosenMap.put("diceArea3", (Integer) Integer.parseInt(thrownDicesStrings.get(2)));
	    		//farkleController.getFarkle().getCurrentGame().getCurrentPlayer().getDice().get(2).setUsed(true);
	    	}
	    }

	    @FXML
	    void diceArea4Pressed(MouseEvent event) {
	    	if(chosenMap.containsKey("diceArea4"))
	    	{
	    		diceArea4.setOpacity(1);
	    		chosenMap.remove("diceArea4");
	    		//farkleController.getFarkle().getCurrentGame().getCurrentPlayer().getDice().get(3).setUsed(false);
	    	}
	    	else
	    	{
	    		diceArea4.setOpacity(0.25);
	    		chosenMap.put("diceArea4", (Integer) Integer.parseInt(thrownDicesStrings.get(3)));
	    		//farkleController.getFarkle().getCurrentGame().getCurrentPlayer().getDice().get(3).setUsed(true);
	    	}
	    }

	    @FXML
	    void diceArea5Pressed(MouseEvent event) {
	    	if(chosenMap.containsKey("diceArea5"))
	    	{
	    		diceArea5.setOpacity(1);
	    		chosenMap.remove("diceArea5");
	    		//farkleController.getFarkle().getCurrentGame().getCurrentPlayer().getDice().get(4).setUsed(false);
	    	}
	    	else
	    	{
	    		diceArea5.setOpacity(0.25);
	    		chosenMap.put("diceArea5", (Integer) Integer.parseInt(thrownDicesStrings.get(4)));
	    		//farkleController.getFarkle().getCurrentGame().getCurrentPlayer().getDice().get(4).setUsed(true);
	    	}
	    }

	    @FXML
	    void diceArea6Pressed(MouseEvent event) {
	    	if(chosenMap.containsKey("diceArea6"))
	    	{
	    		diceArea6.setOpacity(1);
	    		chosenMap.remove("diceArea6");
	    		//farkleController.getFarkle().getCurrentGame().getCurrentPlayer().getDice().get(5).setUsed(false);
	    	}
	    	else
	    	{
	    		diceArea6.setOpacity(0.25);
	    		chosenMap.put("diceArea6", (Integer) Integer.parseInt(thrownDicesStrings.get(5)));
	    		//farkleController.getFarkle().getCurrentGame().getCurrentPlayer().getDice().get(5).setUsed(true);
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
		    		
		    		System.out.println("Roundscore: " + currentPlayer.getRoundScore());
	    			/*throwB.setDisable(false);
		    		throwB.setOpacity(1);*/
		    		//chosenDices.stream().forEach(elm -> elm.setUsed(true));
		    		
		    		System.out.println("the taken dices for player" + 
		    				currentPlayer.getUserName() + "is " + currentPlayer.getTakenDices());
		    		
		    		if(currentPlayer.getTakenDices() == 0)
		    		{
		    			resetField();
		    			/*diceArea1.setVisible(false);
		    	    	diceArea2.setVisible(false);
		    	    	diceArea3.setVisible(false);
		    	    	diceArea4.setVisible(false);
		    	    	diceArea5.setVisible(false);
		    	    	diceArea6.setVisible(false);*/
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
	    	/*throwB.setDisable(false);
	    	throwB.setOpacity(1);*/
	    	/*diceArea1.setVisible(false);
	    	diceArea2.setVisible(false);
	    	diceArea3.setVisible(false);
	    	diceArea4.setVisible(false);
	    	diceArea5.setVisible(false);
	    	diceArea6.setVisible(false);*/
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
	    	if(chosenMap.containsKey("diceArea1"))
	    	{
	    		bS1 = true;
	    	}
	    	if(chosenMap.containsKey("diceArea2"))
	    	{
	    		bS2 = true;
	    	}
	    	if(chosenMap.containsKey("diceArea3"))
	    	{
	    		bS3 = true;
	    	}
	    	if(chosenMap.containsKey("diceArea4"))
	    	{
	    		bS4 = true;
	    	}
	    	if(chosenMap.containsKey("diceArea5"))
	    	{
	    		bS5 = true;
	    	}
	    	if(chosenMap.containsKey("diceArea6"))
	    	{
	    		bS6 = true;
	    	}
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
	    	
	    	if(chosenMap.containsKey("diceArea1"))
    		{
	    		playerDices.get(0).setUsed(true);
    		}
    		if(chosenMap.containsKey("diceArea2"))
    		{
	    		playerDices.get(1).setUsed(true);
    		}
    		if(chosenMap.containsKey("diceArea3"))
    		{
	    		playerDices.get(2).setUsed(true);
    		}
    		if(chosenMap.containsKey("diceArea4"))
    		{
	    		playerDices.get(3).setUsed(true);
    		}
    		if(chosenMap.containsKey("diceArea5"))
    		{
	    		playerDices.get(4).setUsed(true);
    		}
    		if(chosenMap.containsKey("diceArea6"))
    		{
	    		playerDices.get(5).setUsed(true);
    		}
	    }
	    
		@Override
		public boolean isCurrent() {
			// TODO Auto-generated method stub
			return false;
		}

	
}