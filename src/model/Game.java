package model;

import java.util.ArrayList;

public class Game {

	private int gameId;

	private ArrayList<Round> rounds;
	
	private ArrayList<Player> players;
	
	private Player currentPlayer;
	
	private Round currentRound;
	
	private boolean endGame;
	
	public Game(int gameId) {
		this.gameId = gameId;
		this.rounds = new ArrayList<>();
		this.players = new ArrayList<>();
		this.setEndGame(false);
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

	public boolean isEndGame() {
		return endGame;
	}

	public void setEndGame(boolean endGame) {
		this.endGame = endGame;
	}

}
