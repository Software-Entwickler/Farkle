package controller;

import java.util.ArrayList;

import model.Game;
import model.Player;
import model.Round;

public class GameController {
	private FarkleController farkleController;

	public GameController(FarkleController farkleController) {
		this.farkleController = farkleController;
	}
	
	
	public void chooseTenRoundsGame()
	{
		Game newGame = new Game(1);	// TODO 1 should changed to something like ID
		ArrayList<Round> rounds = new ArrayList<>();

		for(int i = 1; i <= 10;i++)
		{
			rounds.add(new Round(i));

		}
		newGame.setRounds(rounds);
		farkleController.getFarkle().setCurrentGame(newGame);
	}
	
	public void chooseTenThousendGame()
	{
		Game newGame = new Game(2);	// TODO 2 should changed to something like ID
		ArrayList<Round> rounds = new ArrayList<>();
		rounds.add(new Round(1));
		newGame.setRounds(rounds);
		farkleController.getFarkle().setCurrentGame(newGame);
	}
	
//	public void chooseSoloGame()
//	{
//			Game newGame = new Game(3);	// TODO 3 should changed to something like ID
//			ArrayList<Round> rounds = new ArrayList<>();
//			int roundNum = 1;
//			for(int i = 1; i <= 10;i++)
//			{
//				rounds.add(new Round(roundNum));
//				roundNum++;
//			}
//			newGame.setRounds(rounds);
//			farkleController.getFarkle().setCurrentGame(newGame);
//	}
	
	public void addPlayersToGame(ArrayList<Player> players)
	{
		if(farkleController.getFarkle().getCurrentGame() == null)
		{
			System.out.println("Fuck this!");
		}
		else
		{
			farkleController.getFarkle().getCurrentGame().setPlayers(players);
		}
	}
	
	public void startGame()
	{
		Game currentGame = farkleController.getFarkle().getCurrentGame();
		currentGame.setCurrentRound(currentGame.getRounds().get(0));
		currentGame.setCurrentPlayer(currentGame.getPlayers().get(0));

		Player currentPlayer = farkleController.getFarkle().getCurrentGame().getCurrentPlayer();

		if (currentPlayer.getPlayerType()>0) farkleController.getAIController().playedByAI(currentPlayer);

	}
}
