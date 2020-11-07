package controller;

import java.util.ArrayList;
import java.util.List;

import model.Player;
import model.Round;


public class RoundController {

	private FarkleController farkleController;

	public RoundController(FarkleController farkleController) {
		this.farkleController = farkleController;
	}

	public boolean isEndOfRound(Round round) {
		return false;
	}

	public boolean isEndOfGame(Round round, ArrayList<Player> players) {
		return false;
	}

	public void draw() {

	}

}
