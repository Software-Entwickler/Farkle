package controller;

import java.util.ArrayList;

import model.Dice;

public class DiceController {
	private FarkleController farkleController;
	
	private ArrayList<Dice> selectedDices;

	public DiceController(FarkleController farkleController) {
		this.farkleController = farkleController;
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

}
