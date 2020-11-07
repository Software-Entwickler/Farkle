package model;

import java.util.ArrayList;

public class Game {

	private int gameId;

	private ArrayList<Round> rounds;
	
	private ArrayList<Player> players;

	public Game(int gameId, ArrayList<Player> players) {
		this.gameId = gameId;
		this.rounds = new ArrayList<>();
		this.players = players;
	}
	
	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public ArrayList<Round> getRounds() {
		return rounds;
	}

	public void setRounds(ArrayList<Round> rounds) {
		this.rounds = rounds;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

}
