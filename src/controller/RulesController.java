package controller;

import java.util.ArrayList;
import model.Dice;
import model.Player;
import model.Round;


public class RulesController {

	private FarkleController farkleController;

	public RulesController(FarkleController farkleController) {
		this.farkleController = farkleController;
	}

	public boolean isOneFarkle(Round round, Player player) {
		return false;
	}

	public boolean isThreeFarkle(Round round, Player player) {
		return false;
	}

	public boolean isEndOfTime(Player player) {
		return false;
	}

	public boolean isMinusScore(ArrayList<Player> players) {
		return false;
	}

	public boolean isValidCollection(ArrayList<Dice> dices) {
		return false;
	}

	public boolean isAllSelected(Player player) {
		return false;
	}

}
