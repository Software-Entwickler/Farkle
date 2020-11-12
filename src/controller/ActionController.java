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
		farkleController.getCalculationController().calculate(dices) ;
	}

	public void chooseAll(ArrayList<Dice> dices) {
		farkleController.getCalculationController().calculate(dices) ;
	}
	// TODO:1.sort,,alle einsen ist ok bis andere zahl gibt uzw solange alle richtig ausgewählte zeichnen kann man weiter
	// TODO ueberpruefen wenn was nicht stimmt dann stop und arraylist leeren .
	public void confirm(ArrayList<Dice> dices) {
		sort(dices);
		if(confirm1(dices)){
			int sum = farkleController.getCalculationController().calculate(dices);
			if(farkleController.getRoundController().isEndOfGame(farkleController.getFarkle().getCurrentGame().getCurrentRound()))
				//TODO : was passiert im Fall end of game ..
			if(farkleController.getRoundController().isEndOfRound(farkleController.getFarkle().getCurrentGame().getCurrentRound()))
				farkleController.getRoundController().setNextRound();
			if(farkleController.getRoundController().isEndOfTurn(farkleController.getFarkle().getCurrentGame().getCurrentPlayer()))
				farkleController.getRoundController().setNextPlayer();
			else {
				farkleController.getFarkle().getCurrentGame().getCurrentPlayer().setRoundScore(sum + farkleController
						.getFarkle().getCurrentGame().getCurrentPlayer().getRoundScore());
				dices.clear();
			}
		}
		else{
			dices.clear();
		}
	}
	private boolean confirm1 (ArrayList<Dice> dices){
		if (dices.size() == 0 ) return true ;

		else return false ;

	}

	private void sort(ArrayList<Dice> dices){
		Collections.sort(dices, Comparator.comparingInt(Dice::getValue));

	}

	public void bank(Player player) {

	}

}
