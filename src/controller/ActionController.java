package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
		int sum = farkleController.getCalculationController().calculate(dices);
		if (sum == 0) {
			dices.clear();
		} else {
			Player player = farkleController.getFarkle().getCurrentGame().getCurrentPlayer();
			if (farkleController.getRoundController().isEndOfGame(farkleController.getFarkle().getCurrentGame().getCurrentRound())) {
				if(farkleController.getRoundController().isDraw(farkleController.getFarkle().getCurrentGame().getCurrentRound())){
					farkleController.getRoundController().addExtraRound();
				}
				show();
			}

			if (farkleController.getRoundController().isEndOfRound(farkleController.getFarkle().getCurrentGame().getCurrentRound())) {
				farkleController.getRoundController().setNextRound();
			}

			if (farkleController.getRoundController().isEndOfTurn(farkleController.getFarkle().getCurrentGame().getCurrentPlayer())) {
				player.setRoundScore(sum);
				farkleController.getRoundController().setNextPlayer();

			}

			else {
				player.setRoundScore(sum);
				if(player.getTakenDices() !=6) {
					player.setDice(reduce(dices, player.getDice()));
					player.setTakenDices(player.getTakenDices() + dices.size());
				}
				else {
					player.setScore(sum + player.getScore());
					resetPlayer(player);
				}
			}
		}

	}

	private ArrayList<Dice> reduce(ArrayList<Dice> takenDice, ArrayList<Dice> reducedArray) {
		if (reducedArray.size() >= takenDice.size()) {
			int i = takenDice.size();
			while (i >0){
				reducedArray.remove(i);
				i-- ;
			}
		}
		return reducedArray;
	}

	private void resetPlayer (Player player){
		player.setPlayed(false);
		player.setRoundScore(0);
		player.setTakenDices(0);
		ArrayList<Dice> dices = player.getDice();
		for(Dice dice : dices)
			dice.setUsed(false);
	}

	private void show (){
		ArrayList<Player> players = farkleController.getFarkle().getPlayer() ;
		players.sort(Comparator.comparing(Player::getScore));
		Collections.reverse(players);
		players.stream().map(player -> player.getUserName() + ": " + player.getScore()).forEach(System.out::println);

	}

}


