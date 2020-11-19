package controller;

import java.util.ArrayList;

import model.Dice;
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
		reSetPlayers();
	}
	
	public void addExtraRound()
	{
		ArrayList<Round> allRounds = farkleController.getFarkle().getCurrentGame().getRounds();
		Round newRound = new Round(allRounds.size() + 1);
		allRounds.add(newRound);
		farkleController.getFarkle().getCurrentGame().setRounds(allRounds);
		farkleController.getFarkle().getCurrentGame().setCurrentRound(allRounds.get(allRounds.size() - 1));
		reSetPlayers();
	}
	
	private void reSetPlayers()
	{
		ArrayList<Player> allPlayers = farkleController.getFarkle().getCurrentGame().getPlayers();
		for(Player player : allPlayers)
		{
			player.setPlayed(false);
			player.setRoundScore(0);
			player.setTakenDices(0);
			ArrayList<Dice> dices =player.getDice();
			for(Dice dice : dices)
			{
				dice.setUsed(false);
				dice.setUsedBefore(false);
			}
		}
	}
	
	public void setNextPlayer()
	{
		Player currentPlayer = farkleController.getFarkle().getCurrentGame().getCurrentPlayer();
		currentPlayer.setPlayed(true);
		currentPlayer.setScore(currentPlayer.getScore() + currentPlayer.getRoundScore());
		ArrayList<Player> allPlayers = farkleController.getFarkle().getCurrentGame().getPlayers();
		for(int index = 0; index < allPlayers.size(); index++)
		{
			if(allPlayers.get(index).equals(currentPlayer))
			{
				if(index == allPlayers.size() -1)
				{
					if(!isEndOfGame(farkleController.getFarkle().getCurrentGame().getCurrentRound())) {
						farkleController.getFarkle().getCurrentGame().setCurrentPlayer(allPlayers.get(0));
						setNextRound();
					}
					else break;
				}
				else
				{
					farkleController.getFarkle().getCurrentGame().setCurrentPlayer(allPlayers.get(index+1));
				}
			}
		}
	}
	
	public boolean isEndOfTurn(Player player)
	{
		if(player.getTakenDices() == 6)
		{
			return false;
		}
		else
		{
			if(player.getDice().size() != 0)
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean isEndOfRound(Round round) {
		ArrayList<Player> allPlayers = round.getPlayers();
		for(Player player : allPlayers)
		{
			if(!player.isPlayed())
			{
				return false;
			}
		}
	 return true;
	}

	public boolean isEndOfGame(Round round) {
		if( isEndOfRound(round) )
		{
			if(farkleController.getFarkle().getCurrentGame().getGameId() == 1 ||
				farkleController.getFarkle().getCurrentGame().getGameId() == 3 )
			{
				return round.getRoundNum() >= 10;
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
				return max >= 10000;
			}
		}
		return false;
	}

	public boolean isDraw(Round round) {
		ArrayList<Player> allPlayers = round.getPlayers();
		for (int i = 0; i < allPlayers.size(); i++) {
			for (int j = i + 1; j < allPlayers.size(); j++) {
				if (allPlayers.get(i).getScore() == allPlayers.get(j).getScore()) {
					return true;
				}
			}
		}
		return false;
	}
}
