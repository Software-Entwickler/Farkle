package model;

import java.util.ArrayList;

public class Game {

	private int gameId;

	private ArrayList<Round> rounds;
	
	private ArrayList<Player> players;
	
	private Player currentPlayer;
	
	private Round currentRound;

	public Game(int gameId, ArrayList<Player> players) {
		this.gameId = gameId;
		this.rounds = new ArrayList<>();
		this.players = players;
	}
	
	public Game(int gameId) {
		this.gameId = gameId;
		this.rounds = new ArrayList<>();
		this.players = new ArrayList<>();
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

	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Round getCurrentRound() {
		return currentRound;
	}

	public void setCurrentRound(Round currentRound) {
		this.currentRound = currentRound;
	}

}
