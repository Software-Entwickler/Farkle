package model;

import java.util.ArrayList;

public class Game {

	private int gameId;

	private ArrayList<Round> rounds;

	public Game(int gameId) {
		this.gameId = gameId;
		this.rounds = new ArrayList<>();
	}
	
	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public ArrayList<Round> getRunde() {
		return rounds;
	}

	public void setRunde(ArrayList<Round> rundes) {
		this.rounds = rundes;
	}

}
