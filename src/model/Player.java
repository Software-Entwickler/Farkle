package model;

import java.util.ArrayList;

import controller.FarkleController;

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
	
	private ArrayList<Dice> selectedDices;

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
		this.effectTypes = new ArrayList<>();
		playerTimer = new Timer() ;
		selectedDices=new ArrayList<>();
	}
	

	/*
	 * selektiert the dice aus dem Spielfeld
	 */
	public void selectDice(Dice dice) {
		if(dice!=null) {
			if(!this.selectedDices.contains(dice))
				this.selectedDices.add(dice);
		}
	}
	
	/*
	 * deselektiert the dice aus dem Spielfeld
	 */
	public void deselectDice(Dice dice) {
		if(dice!=null)
			this.selectedDices.remove(dice);
	}
	
	/*
	 * ob the dice schon selektiert oder not
	 */
	public boolean diceIsSelected(Dice dice) {
		return this.selectedDices.contains(dice);
	}
	
	/*
	 * wurde die Auswahl (the dices) vom Spielfeld return
	 */
	public ArrayList<Dice> getDicesSelection(){
		return this.selectedDices;
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