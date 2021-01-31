package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
	
//	public void setNextRound()
//	{
//		Round currentRound = farkleController.getFarkle().getCurrentGame().getCurrentRound();
//		if (farkleController.getFarkle().getCurrentGame().getGameId() == 1
//				|| farkleController.getFarkle().getCurrentGame().getGameId() == 3) {
//			if (currentRound.getRoundNum()<10) {
//				Round nextRound = farkleController.getFarkle().getCurrentGame().getRounds().get(currentRound.getRoundNum());
//				farkleController.getFarkle().getCurrentGame().setCurrentRound(nextRound);
//				reSetPlayers();
//			} else {
//				farkleController.getFarkle().getCurrentGame().setCurrentRound(new Round(11));
//				reSetPlayers();
//			}
//		} else {
//			Round nextRound = farkleController.getFarkle().getCurrentGame().getRounds().get(currentRound.getRoundNum());
//			farkleController.getFarkle().getCurrentGame().setCurrentRound(nextRound);
//			reSetPlayers();
//		}
//
//	}
	
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
				dice.setValue(0);
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

						if(farkleController.getFarkle().getCurrentGame().getGameId() == 2)
						{
							addExtraRound();
						}
						else
						{
							setNextRound();
						}

						if (allPlayers.get(0).getPlayerType()>0)
							farkleController.getAIController().playedByAI(allPlayers.get(0));

					}
					else
					{
						if(isDraw())
						{
							farkleController.getFarkle().getCurrentGame().setCurrentPlayer(allPlayers.get(0));

							addExtraRound();

							if (allPlayers.get(0).getPlayerType()>0)
								farkleController.getAIController().playedByAI(allPlayers.get(0));

						}
						else
						{
							farkleController.getFarkle().getCurrentGame().setEndGame(true);
							System.out.println("this is the end of the game");
							Collections.sort(allPlayers, Comparator.comparing(Player::getScore));
							Collections.reverse(allPlayers);
							allPlayers.stream().forEach(player -> System.out.println(player.getUserName() + ": " + player.getScore()));
						}
					}
				}
				else
				{
					farkleController.getFarkle().getCurrentGame().setCurrentPlayer(allPlayers.get(index+1));

					if (allPlayers.get(index+1).getPlayerType()>0)
						farkleController.getAIController().playedByAI(allPlayers.get(index+1));

				}
			}
		}
	}

	public boolean isEndOfGame(Round round) {
			if(farkleController.getFarkle().getCurrentGame().getGameId() == 1 ||
				farkleController.getFarkle().getCurrentGame().getGameId() == 3 )
			{
				return round.getRoundNum() >= 10;
			}
			else
			{
				ArrayList<Player> allPlayers = farkleController.getFarkle().getCurrentGame().getPlayers();
				Collections.sort(allPlayers, Comparator.comparing(Player::getScore));
				Collections.reverse(allPlayers);
				return allPlayers.get(0).getScore() >= 10000;
			}

	}

	public boolean isDraw() {
		ArrayList<Player> allPlayers = farkleController.getFarkle().getCurrentGame().getPlayers();
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
