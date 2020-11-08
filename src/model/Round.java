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
	
	public Round(int roundNum)
	{
		this.roundNum = roundNum;
		this.players = new ArrayList<>();
	}
	
	public int getRoundNum() {
		return roundNum;
	}

	public void setRoundNum(int roundNum) {
		this.roundNum = roundNum;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

}
