package controller;

import java.util.ArrayList;
import java.util.Arrays;

import model.Dice;
import model.Player;
import model.Round;


public class RulesController {

	private GameController gameController;
	private FarkleController farkleController;
	private  CalculationController calculationController;

	public RulesController(FarkleController farkleController) {
		this.farkleController = farkleController;
		
	}
	
	public boolean isAllSelected(Player player) {
		if(isValidCollection(player.getDice()))
			return true;
		return false;
	}

	public boolean isOneFarkle(Round round, Player player) {
		boolean check=true;
		ArrayList<Dice> dices=player.getDice();
		for(int i=0;i<dices.size();i++) {
			for(int j=i;j<dices.size();j++) {
				ArrayList<Dice> subDice=(ArrayList<Dice>) dices.subList(i, j+1);
				if(isValidCollection(subDice)) {
					check=false;
					break;
				}
			}
		}
		if(check) {
			
			player.setFarkle(player.getFarkle()+1);
			isThreeFarkle(round,player);
			/*
			int roundNr=round.getRoundNum()-1;
			if(roundNr>=0) {
				Game game =this.gameController.getCurrentGame();
				for(Player ply : game.getRounds().get(roundNr).getPlayers() ) {
					if(ply.getUserName().equals(player.getUserName())) {
						if(ply.getRoundScore()==0)
							ply.setFarkle(1);
						else {
							if(ply.getFarkle()==2)
								player.setFarkle(-1);
						}
					}
				}
			}
			else
				player.setFarkle(0);
			
			*/
			return true;
			
		}
		else
			player.setFarkle(0);
		
			
		return false;
	}

	public boolean isThreeFarkle(Round round, Player player) {
		if(player.getFarkle()==3) {
			player.setFarkle(0);
			player.setScore(-1000);
			return true;
		}
		return false;
	}
	
	public boolean isMinusScore(Player player) {
		if(player.isAlreadyThousend() && player.getScore()<0) {
			System.out.println(player.getUserName() +" ist vom Spiel ausgeschlossen!");
			return true;
				
		}
		return false;
	}

	public  boolean isValidCollection(ArrayList<Dice> dices) {
		int [] collection = farkleController.getCalculationController().dicesToNumbers(dices);
		Arrays.sort(collection);
		
		switch (collection.length) {
		case 1:
			return oneOrFive(collection);
			
		case 2:
			return  oneOrFive(collection);
		case 3:
			return threeCollection(collection,0,2);
		case 4:
			return fourCollection(collection,0,3);
		case 5:
			return fiveCollection(collection,0,4);
		case 6:
			return sixCollection(collection);
		}
		return false;
		
	}
	
	private  boolean threeCollection(int[] collection, int i, int j) {
		
		return collection[i]==collection[j] || oneOrFive(collection);
		
	}
	
	private  boolean fourCollection(int[] collection, int i, int j) {
		if(collection[0]==1 || collection[0]==5)
			return threeCollection(collection,1,3);
		else
			return threeCollection(collection,0,2) && (collection[3]==collection[0] || collection[3]==5);
	}
	
	private  boolean fiveCollection(int[] collection, int i, int j) {
		if(collection[0]==1 || collection[0]==5)
			return fourCollection(collection,1,4);
		else
			return fourCollection(collection,0,3) && (collection[4]==collection[0] || collection[4]==5);
	}
	
	private  boolean sixCollection(int[] collection) {
		return  mixSixCollection(collection) || TriplesCollection(collection) || 
				doublesCollection(collection) || isStreet(collection);
	}

	private  boolean mixSixCollection(int[] collection) {
		if(collection[0]==1 || collection[0]==5)
			return fiveCollection(collection,1,5);
		else
			return fiveCollection(collection,0,4) && (collection[5]==collection[0] || collection[5]==5);
	}

	private  boolean oneOrFive(int []dices) {
		for(int i=0;i<dices.length;i++) {
			if(dices[i]!=1 || dices[i]!=5)
				return false;
		}
		return true;
	}
	
	private  boolean doublesCollection(int[] collection) {
		return collection[0]==collection[1] && collection[2]==collection[3] &&  collection[4]==collection[5];
	}

	private  boolean TriplesCollection(int[] collection) {
		return collection[0]==collection[2] && collection[3]==collection[5];
	}
	
	private  boolean isStreet(int[] collection) {
		for(int i=0;i<collection.length;i++) {
			if(collection[i]!=i+1)
				return false;
		}
		return true;
	}

	public boolean isEndOfTime(Player player) {
		return false;
	}


}
