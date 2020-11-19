package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import model.Dice;
import model.Player;


public class ActionController {

	private FarkleController farkleController;

	public ActionController(FarkleController farkleController) {
		this.farkleController = farkleController;
	}

	public void choose(ArrayList<Dice> dices) {
		farkleController.getCalculationController().calculate(dices);
	}

	public void chooseAll(ArrayList<Dice> dices) {
		farkleController.getCalculationController().calculate(dices);
	}

	public void bank(Player player) {
		if (player.getRoundScore() >= 250) {
			farkleController.getFarkle().getCurrentGame().setCurrentPlayer(player);
			farkleController.getRoundController().setNextPlayer();
		}
	}

	public void confirm(ArrayList<Dice> dices) {
		ArrayList<Dice> diceArrayList = (ArrayList<Dice>) dices.stream().filter(c-> c.isUsed() && !c.isUsedBefore())
				.collect(Collectors.toList());
		int sum = farkleController.getCalculationController().calculate(diceArrayList);

		if (sum == 0) {
			throw new IllegalArgumentException();
		} else {
			Player player = farkleController.getFarkle().getCurrentGame().getCurrentPlayer();
			player.setRoundScore(sum + player.getRoundScore());
			player.setTakenDices(player.getTakenDices()+diceArrayList.size());

			if(dices.stream().allMatch(c-> c.isUsedBefore() || c.isUsed() )) {
				player.setScore( player.getRoundScore() + player.getScore());
				resetPlayer(player);
			}
			else {

				dices.stream().filter(Dice::isUsed).forEach(c -> c.setUsedBefore(true));
				if (farkleController.getRoundController().isEndOfTurn(farkleController.getFarkle().getCurrentGame().getCurrentPlayer())) {
					farkleController.getRoundController().setNextPlayer();
				}

				if (farkleController.getRoundController().isEndOfGame(farkleController.getFarkle().getCurrentGame().getCurrentRound())) {

					if (farkleController.getRoundController().isDraw(farkleController.getFarkle().getCurrentGame().getCurrentRound())) {
						farkleController.getRoundController().addExtraRound();
					} else {
						show();
					}
				}
			}
		}
	}

	private void resetPlayer (Player player){
		player.setRoundScore(0);
		player.setTakenDices(0);
		ArrayList<Dice> dices =new ArrayList<Dice>() ;
		for(Dice dice : dices)
		{
			dice.setUsed(false);
			dice.setUsedBefore(false);
		}
	}

	private void show (){
		ArrayList<Player> players = farkleController.getFarkle().getPlayer() ;
		players.sort(Comparator.comparing(Player::getScore));
		Collections.reverse(players);
		players.stream().map(player -> player.getUserName() + ": " + player.getScore()).forEach(System.out::println);
	}



}


