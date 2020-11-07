package model;

import java.util.ArrayList;

public class Farkle {

	private ArrayList<Player> players;

	private ArrayList<Game> games;
	
	private Game currentGame;

	private ArrayList<Highscore> highscores;

	private ArrayList<EffectType> effectTypes;
	
	public Farkle()
	{
		
	}
	
	public ArrayList<Player> getPlayer() {
		return players;
	}

	public void setPlayer(ArrayList<Player> players) {
		this.players = players;
	}

	public ArrayList<Game> getGames() {
		return games;
	}

	public void setGames(ArrayList<Game> games) {
		this.games = games;
	}

	public ArrayList<Highscore> getHighscore() {
		return highscores;
	}

	public void setHighscore(ArrayList<Highscore> highscores) {
		this.highscores = highscores;
	}

	public ArrayList<EffectType> getEffectType() {
		return effectTypes;
	}

	public void setEffectType(ArrayList<EffectType> effectTypes) {
		this.effectTypes = effectTypes;
	}

	public void setCurrentGame(Game currentGame) {
		this.currentGame = currentGame;
	}
	
	public Game getCurrentGame() {
		return currentGame;
	}

}
