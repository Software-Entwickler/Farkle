package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import application.AlertS;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import model.Dice;
import model.Player;


public class ActionController {

	private FarkleController farkleController;

	public ActionController(FarkleController farkleController) {
		fill ();
		this.farkleController = farkleController;
	}
	private ArrayList<Integer> numberOfDice =new ArrayList<>();
	
	private void fill () {
		for(int i=1;i<=6;i++)
			numberOfDice.add(i);
	}
	
	
	public ArrayList<String> throwDice(Player player) {
		ArrayList<String> fill = new ArrayList<String>();
		ArrayList<Dice> dices = player.getDice();
		 for( int i=0;i<6;i++) {
			 Collections.shuffle(numberOfDice);
			 fill.add("" + numberOfDice.get(0));
			 dices.get(i).setValue(numberOfDice.get(0));
			 //fill.add("file:src/view/dice"+numberOfDice.get(0)+".png");
			 }
			 
		return fill;
	}

	/*
	public void choose(ArrayList<Dice> dices) {
		farkleController.getCalculationController().calculate(dices);
	}

	public void chooseAll(ArrayList<Dice> dices) {
		farkleController.getCalculationController().calculate(dices);
	}
	*/

	public void bank(Player player) {
		if (player.getRoundScore() >= 250) {
			farkleController.getRoundController().setNextPlayer();
		}
	}

	public void farkle(Player player)
	{
		player.setRoundScore(0);
		farkleController.getRoundController().setNextPlayer();
	}
	
	public void confirm(ArrayList<Dice> dices) {
		ArrayList<Dice> diceArrayList = (ArrayList<Dice>) dices.stream().filter(c-> c.isUsed() && !c.isUsedBefore())
				.collect(Collectors.toList());
		int sum = farkleController.getCalculationController().calculate(diceArrayList);


		if(sum != 0)
		{
			Player player = farkleController.getFarkle().getCurrentGame().getCurrentPlayer();
			player.setRoundScore(sum + player.getRoundScore());
			player.setTakenDices(player.getTakenDices()+diceArrayList.size());
			System.out.println("Hier: " + player.getTakenDices());

			if(player.getDice().stream().allMatch(c-> c.isUsedBefore() || c.isUsed() )) {
				System.out.println("Hier wieder ");
				player.setScore( player.getRoundScore() + player.getScore());
				resetPlayer(player);
			}
			else {

				player.getDice().stream().filter(elem -> elem.isUsed()).forEach(c -> c.setUsedBefore(true));
//				if (farkleController.getRoundController().isEndOfTurn(farkleController.getFarkle().getCurrentGame().getCurrentPlayer())) {
//					System.out.println("set the next player");
//					farkleController.getRoundController().setNextPlayer();
//				}
//
//				if (farkleController.getRoundController().isEndOfGame(farkleController.getFarkle().getCurrentGame().getCurrentRound())) {
//
//					if (farkleController.getRoundController().isDraw(farkleController.getFarkle().getCurrentGame().getCurrentRound())) {
//						farkleController.getRoundController().addExtraRound();
//					} else {
//						show();
//					}
//				}
			}
		}
	}

	public void resetPlayer (Player player){
		player.setRoundScore(0);
		player.setTakenDices(0);
		ArrayList<Dice> dices =player.getDice() ;
		for(Dice dice : dices)
		{
			dice.setUsed(false);
			dice.setUsedBefore(false);
			dice.setValue(0);
		}
	}

//	private void show (){
//		ArrayList<Player> players = farkleController.getFarkle().getPlayer() ;
//		players.sort(Comparator.comparing(Player::getScore));
//		Collections.reverse(players);
//		players.stream().map(player -> player.getUserName() + ": " + player.getScore()).forEach(System.out::println);
//	}

}
