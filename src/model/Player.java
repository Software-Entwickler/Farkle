package model;

import java.util.ArrayList;

public class Player {

	private String userName;

	private int score;

	private boolean played;

	private int farkle;

	private int roundScore;

	private boolean alreadyThousend;

	private int takenDices;

	private Highscore highscore;

	private ArrayList<Dice> dices;

	private ArrayList<EffectType> effectTypes;

	private Timer playerTimer;
	
	public Player(String userName)
	{
		this.userName = userName;
		this.score = 0;
		this.played = false;
		this.farkle = 0;
		this.roundScore = 0;
		this.alreadyThousend = false;
		this.takenDices = 0;
		this.highscore = null;
		this.dices = new ArrayList<>();
		for(int index = 1; index <= 6; index++)
		{
			dices.add(new Dice(0));
		}
		this.effectTypes = new ArrayList<>();
		playerTimer = new Timer() ;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isPlayed() {
		return played;
	}

	public void setPlayed(boolean played) {
		this.played = played;
	}

	public int getFarkle() {
		return farkle;
	}

	public void setFarkle(int farkle) {
		this.farkle = farkle;
	}

	public int getRoundScore() {
		return roundScore;
	}

	public void setRoundScore(int roundScore) {
		this.roundScore = roundScore;
	}

	public boolean isAlreadyThousend() {
		return alreadyThousend;
	}

	public void setAlreadyThousend(boolean alreadyThousend) {
		this.alreadyThousend = alreadyThousend;
	}

	public int getTakenDices() {
		return takenDices;
	}

	public void setTakenDices(int takenDices) {
		this.takenDices = takenDices;
	}

	public Highscore getHighscore() {
		return highscore;
	}

	public void setHighscore(Highscore highscore) {
		this.highscore = highscore;
	}

	public ArrayList<Dice> getDice() {
		return dices;
	}

	public void setDice(ArrayList<Dice> dices) {
		this.dices = dices;
	}

	public ArrayList<EffectType> getEffectType() {
		return effectTypes;
	}

	public void setEffectType(ArrayList<EffectType> effectTypes) {
		this.effectTypes = effectTypes;
	}

	public Timer getPlayerTimer() {
		return playerTimer;
	}

	public void setPlayerTimer(Timer playerTimer) {
		this.playerTimer = playerTimer;
	}
}
