package controller;

import java.util.ArrayList;

import model.Game;
import model.Player;
import model.Round;


public class RoundController {

	private FarkleController farkleController;

	public RoundController(FarkleController farkleController) {
		this.farkleController = farkleController;
	}
	
	
	
	public void setNextRound()
	{
		Round currentRound = farkleController.getFarkle().getCurrentGame().getCurrentRound();
		Round nextRound = farkleController.getFarkle().getCurrentGame().getRounds().get(currentRound.getRoundNum());
		farkleController.getFarkle().getCurrentGame().setCurrentRound(nextRound);
	}
	
	public void addExtraRound()
	{
		ArrayList<Round> allRounds = farkleController.getFarkle().getCurrentGame().getRounds();
		Round newRound = new Round(allRounds.size() + 1);
		allRounds.add(newRound);
		farkleController.getFarkle().getCurrentGame().setRounds(allRounds);
		farkleController.getFarkle().getCurrentGame().setCurrentRound(allRounds.get(allRounds.size() - 1));
	}
	
	public Player setNextPlayer(Player currentPlayer, Round currentRound)
	{
		ArrayList<Player> allPlayers = farkleController.getFarkle().getCurrentGame().getPlayers();
		for(int index = 0; index < allPlayers.size(); index++)
		{
			if(allPlayers.get(index).equals(currentPlayer))
			{
				if(index == allPlayers.size() -1)
				{
					setNextRound();
					return allPlayers.get(0);
				}
				else
				{
					return allPlayers.get( + 1);
				}
			}
		}
		throw new NullPointerException();
	}
	
	public boolean isEndOfRound(Round round) {
		ArrayList<Player> allPlayers = round.getPlayers();
		for(Player player : allPlayers)
		{
			if(player.isPlayed() == false)
			{
				return false;
			}
		}
	 return true;
	}

	public boolean isEndOfGame(Round round) {
		if(isEndOfRound(round) && !isDraw(round))
		{
			if(farkleController.getFarkle().getCurrentGame().getGameId() == 1 ||
				farkleController.getFarkle().getCurrentGame().getGameId() == 3 )
			{
				return round.getRoundNum() == 10;
			}
			else
			{
				ArrayList<Player> allPlayers = round.getPlayers();
				int max = 0;
				for(Player player : allPlayers)
				{
					if(player.getScore() > max)
					{
						max = player.getScore();
					}
				}
				return max >= 1000;
			}
		}
		return false;
	}

	public boolean isDraw(Round round)
	{
		if(isEndOfRound(round) &&
			farkleController.getFarkle().getCurrentGame().getGameId() != 3)
		{
			ArrayList<Player> allPlayers = round.getPlayers();
			for(int i = 0; i < allPlayers.size();i++)
			{
				for(int j = i+1; j < allPlayers.size();j++)
				{
					if(allPlayers.get(i).getScore() == allPlayers.get(j).getScore())
					{
						return true;
					}
				}
			}
		}
		return false;
	}
}
