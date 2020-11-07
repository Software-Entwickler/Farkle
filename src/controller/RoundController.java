package controller;

import java.util.ArrayList;
import model.Player;
import model.Round;


public class RoundController {

	private FarkleController farkleController;

	public RoundController(FarkleController farkleController) {
		this.farkleController = farkleController;
	}
	
	public Round addExtraRound()
	{
		ArrayList<Round> allRounds = farkleController.getFarkle().getCurrentGame().getRounds();
		ArrayList<Player> allPlayers = farkleController.getFarkle().getCurrentGame().getPlayers();
		Round newRound = new Round(allRounds.size() + 1, allPlayers);
		allRounds.add(newRound);
		farkleController.getFarkle().getCurrentGame().setRounds(allRounds);
		return newRound;
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
