package model;

import java.util.ArrayList;

public class Round {

	private int roundNum;

	private ArrayList<Player> players;

	public Round(int roundNum , ArrayList<Player> players)
	{
		this.roundNum = roundNum;
		this.players = players;
	}
	
	public int getRoundNum() {
		return roundNum;
	}

	public void setRoundNum(int roundNum) {
		this.roundNum = roundNum;
	}

	public ArrayList<Player> getPlayer() {
		return players;
	}

	public void setPlayer(ArrayList<Player> players) {
		this.players = players;
	}

}
