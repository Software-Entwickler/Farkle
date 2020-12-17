package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.FarkleController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import model.Player;

public class AddPlayersViewController extends StackPane implements Initializable {
	
	@FXML
    private TextField player1, player2, player3, player4, player5, player6, player7;

    @FXML
    private ComboBox<String> avatar1, avatar2, avatar3, avatar4, avatar5, avatar6, avatar7;

    @FXML
    private ComboBox<String> playerType1, playerType2, playerType3, playerType4, 
    					playerType5, playerType6, playerType7;

    @FXML
    private Button btnStart, btnAdd, btnDelete, btnBack;
    
    int currentLine;
    
    FarkleController farkleController;
    
    public AddPlayersViewController() {
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddPlayersView.fxml"));
    	loader.setRoot(this);
    	loader.setController(this);
    	try {
    		loader.load();
    	} catch (IOException e) {
    		
    		e.printStackTrace();
    }
  
}

	 @FXML
	 void addPlayer(ActionEvent event) {
		 switch(currentLine) {
		 case 1 : 
			 player2.setVisible(true);
			 playerType2.setVisible(true);
			 avatar2.setVisible(true);
			 currentLine = currentLine +1;
			 break;
		 
		 case 2 : 
			 player3.setVisible(true);
			 playerType3.setVisible(true);
			 avatar3.setVisible(true);
			 currentLine = currentLine +1;
			 break;
		 
		 case 3 : 
			 player4.setVisible(true);
			 playerType4.setVisible(true);
			 avatar4.setVisible(true);
			 currentLine = currentLine +1;
			 break;
		 
		 case 4 : 
			 player5.setVisible(true);
			 playerType5.setVisible(true);
			 avatar5.setVisible(true);
			 currentLine = currentLine +1;
			 break;
		 
		 case 5 : 
			 player6.setVisible(true);
			 playerType6.setVisible(true);
			 avatar6.setVisible(true);
			 currentLine = currentLine +1;
			 break;
		 
		 case 6 : 
			 player7.setVisible(true);
			 playerType7.setVisible(true);
			 avatar7.setVisible(true);
			 currentLine = currentLine +1;
			 break;
		 }
		
	 }

	 @FXML
	 void removePlayer(ActionEvent event) {
		 switch(currentLine) {
		 case 1 : 
			 break;
		 
		 case 2 : 
			 player2.setVisible(false);
			 playerType2.setVisible(false);
			 avatar2.setVisible(false);
			 currentLine = currentLine -1;
			 break;
		 
		 case 3 : 
			 player3.setVisible(false);
			 playerType3.setVisible(false);
			 avatar3.setVisible(false);
			 currentLine = currentLine -1;
			 break;
		 
		 case 4 : 
			 player4.setVisible(false);
			 playerType4.setVisible(false);
			 avatar4.setVisible(false);
			 currentLine = currentLine -1;
			 break;
		 
		 case 5 : 
			 player5.setVisible(false);
			 playerType5.setVisible(false);
			 avatar5.setVisible(false);
			 currentLine = currentLine -1;
			 break;
		 
		 case 6 : 
			 player6.setVisible(false);
			 playerType6.setVisible(false);
			 avatar6.setVisible(false);
			 currentLine = currentLine -1;
			 break;
			 
		 case 7 : 
			 player7.setVisible(false);
			 playerType7.setVisible(false);
			 avatar7.setVisible(false);
			 currentLine = currentLine -1;
			 break;
		 }
	 }
	 
	 @FXML
		public void startTheGame(ActionEvent event) {
		 
		 	ArrayList<Player> gamePlayers = new ArrayList<>();
		 
		 	if(currentLine == 1)
		 	{
		 		Player gamePlayer1 = new Player(player1.getText());
		 		gamePlayers.add(gamePlayer1);
		 	}
		 	else if(currentLine == 2)
		 	{
		 		Player gamePlayer1 = new Player(player1.getText());
		 		Player gamePlayer2 = new Player(player2.getText());
		 		gamePlayers.add(gamePlayer1);
		 		gamePlayers.add(gamePlayer2);
		 	}
		 	else if(currentLine == 3)
		 	{
		 		Player gamePlayer1 = new Player(player1.getText());
		 		Player gamePlayer2 = new Player(player2.getText());
		 		Player gamePlayer3 = new Player(player3.getText());
		 		gamePlayers.add(gamePlayer1);
		 		gamePlayers.add(gamePlayer2);
		 		gamePlayers.add(gamePlayer3);
		 	}
		 	else if(currentLine == 4)
		 	{
		 		Player gamePlayer1 = new Player(player1.getText());
		 		Player gamePlayer2 = new Player(player2.getText());
		 		Player gamePlayer3 = new Player(player3.getText());
		 		Player gamePlayer4 = new Player(player4.getText());
		 		gamePlayers.add(gamePlayer1);
		 		gamePlayers.add(gamePlayer2);
		 		gamePlayers.add(gamePlayer3);
		 		gamePlayers.add(gamePlayer4);
		 	}
		 	else if(currentLine == 5)
		 	{
		 		Player gamePlayer1 = new Player(player1.getText());
		 		Player gamePlayer2 = new Player(player2.getText());
		 		Player gamePlayer3 = new Player(player3.getText());
		 		Player gamePlayer4 = new Player(player4.getText());
		 		Player gamePlayer5 = new Player(player5.getText());
		 		gamePlayers.add(gamePlayer1);
		 		gamePlayers.add(gamePlayer2);
		 		gamePlayers.add(gamePlayer3);
		 		gamePlayers.add(gamePlayer4);
		 		gamePlayers.add(gamePlayer5);
		 	}
		 	else if(currentLine == 6)
		 	{
		 		Player gamePlayer1 = new Player(player1.getText());
		 		Player gamePlayer2 = new Player(player2.getText());
		 		Player gamePlayer3 = new Player(player3.getText());
		 		Player gamePlayer4 = new Player(player4.getText());
		 		Player gamePlayer5 = new Player(player5.getText());
		 		Player gamePlayer6 = new Player(player6.getText());
		 		gamePlayers.add(gamePlayer1);
		 		gamePlayers.add(gamePlayer2);
		 		gamePlayers.add(gamePlayer3);
		 		gamePlayers.add(gamePlayer4);
		 		gamePlayers.add(gamePlayer5);
		 		gamePlayers.add(gamePlayer6);
		 	}
		 	else if(currentLine == 7)
		 	{
		 		Player gamePlayer1 = new Player(player1.getText());
		 		Player gamePlayer2 = new Player(player2.getText());
		 		Player gamePlayer3 = new Player(player3.getText());
		 		Player gamePlayer4 = new Player(player4.getText());
		 		Player gamePlayer5 = new Player(player5.getText());
		 		Player gamePlayer6 = new Player(player6.getText());
		 		Player gamePlayer7 = new Player(player7.getText());
		 		gamePlayers.add(gamePlayer1);
		 		gamePlayers.add(gamePlayer2);
		 		gamePlayers.add(gamePlayer3);
		 		gamePlayers.add(gamePlayer4);
		 		gamePlayers.add(gamePlayer5);
		 		gamePlayers.add(gamePlayer6);
		 		gamePlayers.add(gamePlayer7);
		 	}
			
		 	//TODO farkleController.getFarkle().getCurrentGame().setPlayers(gamePlayers);
		 	for(Player player : gamePlayers)
		 	{
		 		System.out.println(player.getUserName());
		 	}
		}
	 

	    @FXML
	    void backToMain(ActionEvent event) {
	    	//TODO 
	    }


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		playerType1.getItems().addAll("Human", "AI-Easy", "AI-Normal","AI-Hard");
		playerType2.getItems().addAll("Human", "AI-Easy", "AI-Normal","AI-Hard");
		playerType3.getItems().addAll("Human", "AI-Easy", "AI-Normal","AI-Hard");
		playerType4.getItems().addAll("Human", "AI-Easy", "AI-Normal","AI-Hard");
		playerType5.getItems().addAll("Human", "AI-Easy", "AI-Normal","AI-Hard");
		playerType6.getItems().addAll("Human", "AI-Easy", "AI-Normal","AI-Hard");
		playerType7.getItems().addAll("Human", "AI-Easy", "AI-Normal","AI-Hard");
		
		currentLine = 1;
		
		farkleController = new FarkleController();
	}

}
