package view;

import java.io.IOException;

import application.MusicLoader;
import controller.FarkleController;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


/*
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;
*/

/*
		AddPlayersViewController addPlayersViewController = new AddPlayersViewController();
		HUDViewController hudViewController = new HUDViewController();
		SampleViewController sampleViewController = new SampleViewController();
		Scene scene = new Scene(hudViewController, 800,600);
		primaryStage.setScene(scene);
		primaryStage.show();
 */

public class MainWindowViewController extends AnchorPane  {

	    @FXML
	    private ImageView farkle10000;

	    @FXML
	    private ImageView farkle10Rounds;

	    @FXML
	    private ImageView exitmain;
	    
	    @FXML
	    private ImageView musicon;
	    
	    @FXML
	    private ImageView highscoremain;

	    @FXML
	    private ImageView optionsmain;

	    @FXML
		private AnchorPane anchorPane;

	    private int music=1;
	    
	    
	    private Stage primaryStage;

	    private FarkleController farkleController;
	    private ScaleTransition scaleTransition;

	    public MainWindowViewController (Stage primaryStage) {
	    	

	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainWindowViewB.fxml"));
	        loader.setRoot(this);
	        loader.setController(this);
	        try {
	            loader.load();
	        } catch (IOException e) {

	            e.printStackTrace();
	        }

	        this.primaryStage = primaryStage;
	        this.farkleController = new FarkleController();
	        this.anchorPane = this;

	    }


	    @FXML
	    void exitmainPressed(MouseEvent event) {
	    	MusicLoader.loadSound("button_menue.wav");
	    	 System.exit(0);

	    }
	    
	    @FXML
	    void farkle10RoundsPressed(MouseEvent event) {
	    	MusicLoader.loadSound("button_menue.wav");
	    	System.out.println("10 rounds to play!");
	        farkleController.getGameController().chooseTenRoundsGame();
	        AddPlayersViewController addPlayersViewController = new AddPlayersViewController(this.primaryStage, this.farkleController);
	        Scene scene = new Scene(addPlayersViewController, primaryStage.getScene().getWidth() , primaryStage.getScene().getHeight());
	        primaryStage.setScene(scene);
	        primaryStage.show();

	    }

	    @FXML
	    void farkle10000Pressed(MouseEvent event) {
	    	MusicLoader.loadSound("button_menue.wav");
	    	 System.out.println("10000 points to win!");
	         farkleController.getGameController().chooseTenThousendGame();
	         AddPlayersViewController addPlayersViewController = new AddPlayersViewController(this.primaryStage, this.farkleController);
	         Scene scene = new Scene(addPlayersViewController, primaryStage.getScene().getWidth() , primaryStage.getScene().getHeight());
	         primaryStage.setScene(scene);
	         primaryStage.show();

	    }
	    

	    @FXML
	    void musiconPressed(MouseEvent event) {
	    	if(music%2==1) {
	    		musicon.setImage(new Image("file:src/view/musicofff.png"));
	    		MusicLoader.muteSound(true);
	    		music++;
	    	}
	    	else {
	    		musicon.setImage(new Image("file:src/view/musicon.png"));
	    		MusicLoader.muteSound(false);
	    		music++;
	    	}
	    }
	    
	    @FXML
	    public void initialize() {
	    	if(MusicLoader.getPlayed()) {
	    		MusicLoader.startBackgroundMusic();
	    		MusicLoader.setPlayed(false);
	    	}
	    	
	    	setScaleTransition(farkle10000);
	    	setScaleTransition(farkle10Rounds);
	    	setScaleTransition(exitmain);
	    	setScaleTransition(musicon);
	    	setScaleTransition(highscoremain);
	    	setScaleTransition(optionsmain);
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
	void highscoremainPressed(MouseEvent event) {
		MusicLoader.loadSound("tada.wav");
		System.out.println("highSCore");
		HighScoreViewController highScoreViewController = new HighScoreViewController(this.primaryStage);
		highScoreViewController.setPrefSize(primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());
		anchorPane.getChildren().add(highScoreViewController);
	}


	    
	    @FXML
	    void optionsmainPressed(MouseEvent event) {
	    	 OptionViewController  optionViewController = new OptionViewController(this.primaryStage);
		     Scene scene = new Scene(optionViewController, primaryStage.getScene().getWidth() , primaryStage.getScene().getHeight());
		     primaryStage.setScene(scene);
	         primaryStage.show();
	    }
}

