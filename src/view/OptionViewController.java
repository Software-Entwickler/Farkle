package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.MusicLoader;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Dice;

public class OptionViewController  extends AnchorPane implements Initializable {
	
	 @FXML
	    private ImageView chooseB;

	    @FXML
	    private ImageView bg1;

	    @FXML
	    private ImageView bg2;

	    @FXML
	    private ImageView bg3;

	    @FXML
	    private ImageView bg4;

	    @FXML
	    private ImageView bg5;

	    @FXML
	    private ImageView bg6;

	    @FXML
	    private ImageView backB;
	    
	    static String backGround = null;
	    
	    private ArrayList<ImageView> imageBackGround = new ArrayList<>();
	    
	    private ScaleTransition scaleTransition;

    
        private Stage primaryStage;

    
    
    public OptionViewController (Stage primaryStage) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/OptionView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {

            e.printStackTrace();
        }

        this.primaryStage = primaryStage;
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	setScaleTransition(chooseB);
    	setScaleTransition(backB);
    	imageBackGround.add(bg1);
    	imageBackGround.add(bg2);
    	imageBackGround.add(bg3);
    	imageBackGround.add(bg4);
    	imageBackGround.add(bg5);
    	imageBackGround.add(bg6);
    	
    	setChoiceBackGround(bg1);
    	setChoiceBackGround(bg2);
    	setChoiceBackGround(bg3);
    	setChoiceBackGround(bg4);
    	setChoiceBackGround(bg5);
    	setChoiceBackGround(bg6);
    	
    	if(backGround!=null) {
    		//HUDViewController.imgPlayer.setImage(new Image("file:src/view/graphics/"+ backGround + ".jpeg"));
    	}
    	
		
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
    
    public void setChoiceBackGround(ImageView imageView) {
    	imageView.setOnMouseClicked(new EventHandler <MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getSource() instanceof ImageView) {
					String source = imageView.getId();
					
					if(source!=null) {
						for(int i=0;i<imageBackGround.size();i++) {
							if(imageBackGround.get(i).getId()==source) {
								backGround=imageBackGround.get(i).getId();
								imageBackGround.get(i).setOpacity(0.5);
							}
							else {
								imageBackGround.get(i).setOpacity(1);
							}
						}	
					}
						
				}
				event.consume();
			}
    	});
    }
    
    
    @FXML
    void backPressed(MouseEvent event) {
    	MusicLoader.loadSound("button_click.wav");
    	MainWindowViewController mainWindowViewController = new MainWindowViewController(this.primaryStage);
        Scene scene = new Scene(mainWindowViewController , primaryStage.getScene().getWidth() , primaryStage.getScene().getHeight());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void choosePressed(MouseEvent event) {
    	MusicLoader.loadSound("button_click.wav");
    	System.out.println("HHHHHHHHHHH");
    }
    

	
}
