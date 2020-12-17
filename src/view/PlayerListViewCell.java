package view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import model.Player;

public class PlayerListViewCell extends ListCell<Player> {

	 	@FXML
	    private ImageView imgPlayerCell;

	    @FXML
	    private Label labScore;

	    @FXML
	    private Label labName;
	    
	    
	public PlayerListViewCell() {
		
	}
	
    @Override
    protected void updateItem(Player player, boolean empty) {
    	
        super.updateItem(player, empty);

        if(empty || player == null) {

            setText(null);
            setGraphic(null);
            System.out.println("Fick dich!");

        } else {
        	System.out.println("Fick euch!");
               FXMLLoader mLLoader = new FXMLLoader(getClass().getResource("/fxml/PlayerListViewCell.fxml"));
                mLLoader.setController(this);
                mLLoader.setRoot(this);
                System.out.println("Fick euch!");
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            labName.setText(player.getUserName());
            labScore.setText("" + player.getScore());
            
            setText(null);

        }
    }
}
