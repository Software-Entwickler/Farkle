package controller;

import java.util.ArrayList;
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

	}

	public void confirm(ArrayList<Dice> dices) {

	}

	public void bank(Player player) {

	}

}
