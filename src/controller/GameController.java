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
	
	public void addPlayerToGame(Player player, Game game)
	{
		ArrayList<Player> players = game.getPlayers();
		if(players.size() <= 5)
		{
			players.add(player);
		}
	}
	
	public Game startTenRoundsGame(ArrayList<Player> players)
	{
		Game newGame = new Game(1, players);	// TODO 1 should changed to something like ID
		ArrayList<Round> rounds = new ArrayList<>();
		int roundNum = 1;
		for(int i = 1; i <= 10;i++)
		{
			rounds.add(new Round(roundNum, players));
			roundNum++;
		}
		newGame.setRounds(rounds);
		farkleController.getFarkle().setCurrentGame(newGame);
		return newGame;
	}
	
	public Game startOneThousendGame(ArrayList<Player> players)
	{
		Game newGame = new Game(2, players);	// TODO 2 should changed to something like ID
		ArrayList<Round> rounds = new ArrayList<>();
		rounds.add(new Round(1, players));
		newGame.setRounds(rounds);
		farkleController.getFarkle().setCurrentGame(newGame);
		return newGame;
	}
	
	public Game startSoloGame(ArrayList<Player> players)
	{
		if(players.size() == 1)
		{
			Game newGame = new Game(3, players);	// TODO 3 should changed to something like ID
			ArrayList<Round> rounds = new ArrayList<>();
			int roundNum = 1;
			for(int i = 1; i <= 10;i++)
			{
				rounds.add(new Round(roundNum, players));
				roundNum++;
			}
			newGame.setRounds(rounds);
			farkleController.getFarkle().setCurrentGame(newGame);
			return newGame;
		}
		else
		{
			return null;
		}
		
	}
}
