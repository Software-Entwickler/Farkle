package controller;

import model.Dice;
import model.Player;

import java.util.*;


public class AIController {

	private FarkleController farkleController;

	private int [][] selectedCollections;

	private int upTo;

	private boolean canPlayMore;

	public AIController(FarkleController farkleController) {
		this.farkleController = farkleController;
	}


	private static class Decision {

		private int [] collection;
		private int gain;

		public Decision(int [] collection, int gain) {
			this.collection = collection;
			this.gain = gain;
		}

		public int [] getCollection() {
			return collection;
		}


		public int getGain() {
			return gain;
		}

	}

	public String takeDecision (ArrayList<Dice> dices) {

		ArrayList<Decision> decisions = prepareDecision(dices);

		Collections.sort(decisions, Comparator.comparing(Decision::getGain));
		Collections.reverse(decisions);


		/*
		for ( Decision decision : decisions) {
			String str = Arrays.toString(decision.collection) + " calculate: " + decision.gain;
			System.out.println(str);
		}
		 */

		decisions.removeIf(d -> d.getGain() == 0);

		this.selectedCollections = new int[decisions.size()][];
		for (int i=0; i<decisions.size(); i++) {
			this.selectedCollections[i] = decisions.get(i).getCollection();
		}

		if (decisions.size()>0) {

			int [] decision = decisions.get(0).getCollection();

			Arrays.sort(decision);

			String result = "Pick up the dices with the following numbers: ";
			for (int pickedUpDice : decision) {
				result += pickedUpDice + ", ";
			}
			result = result.substring(0, result.length()-2) + ".";

			return result;

		} else {
			return "There is no valid collection!";
		}
	}

	private ArrayList<Decision> prepareDecision (ArrayList<Dice> dices) {

		int [] dicesArray = farkleController.getCalculationController().dicesToNumbers(dices);

		int [][] collections = powerSet(dicesArray);

		ArrayList<Decision> result = new ArrayList<>();

		for (int[] collection : collections) {

			int gain = farkleController.getCalculationController()
					.calculate(collectionToArraylistDices(collection));

			result.add(new Decision(collection, gain));
		}

		return result;
	}

	private static int [][] powerSet(int [] originalSet) {
		int max = 1 << originalSet.length;
		int[][] result = new int[max][];
		for (int i = 0; i < max; ++i) {
			result[i] = new int[Integer.bitCount(i)];
			for (int j = 0, b = i, k = 0; j < originalSet.length; ++j, b >>= 1)
				if ((b & 1) != 0)
					result[i][k++] = originalSet[j];
		}
		return result;
	}

	private ArrayList<Dice> collectionToArraylistDices (int [] array) {

		ArrayList<Dice> result = new ArrayList<>(array.length);

		for (int i =0; i< array.length; i++) {
			result.add(new Dice(array[i]));
		}

		return result;

	}

	public void playedByAI (Player player) {

		int playerType = player.getPlayerType();
		if(playerType==0) return;

		upTo = 6;

		if(playerType==1) {
			letEasyAIPlay();
		} else if(playerType==2) {
			letNormalAIPlay();
		} else {
			letHardAIPlay();
		}

	}

	private void letEasyAIPlay (){

		Player player = farkleController.getFarkle().getCurrentGame().getCurrentPlayer();

		canPlayMore = ! (farkleController.getRoundController().isEndOfGame(
				farkleController.getFarkle().getCurrentGame().getCurrentRound())
				|| player.isPlayed());


		while (canPlayMore) {

			ArrayList<Dice> dices = new ArrayList<>();
			Random random = new Random();

			if(upTo==0) upTo = 6;

			for( int i=0;i<upTo;i++) {
				dices.add(new Dice(random.nextInt(6) + 1));
			}

			takeDecision(dices);

			System.out.println();
			System.out.println("My name is: " + player.getUserName());
			System.out.println("I have yet " + upTo + " dices!");
			System.out.println("My current round score is: " + player.getRoundScore());
			System.out.println("My score is: " + player.getScore());
			System.out.println("That is the round number: "
					+ farkleController.getFarkle().getCurrentGame().getCurrentRound().getRoundNum());


			int size = selectedCollections.length;


			if (player.getRoundScore()>=250) {
				System.out.println("Bank");
				upTo = 6;
				farkleController.getActionController().bank(player);
				break;
			}


			if(size == 0) {
				System.out.println("Lol it is Farkle!");
				upTo = 6;
				farkleController.getActionController().farkle(player);
				break;
			}

			ArrayList<Dice> selectedDices = new ArrayList<>();
			int [] selected = this.selectedCollections.length > 0 ?
					selectedCollections[random.nextInt(this.selectedCollections.length)]
					: new int[0];

			for (int i : selected) {
				selectedDices.add(new Dice(i));
			}


			System.out.println("Confirm: " + Arrays.toString(selected));
			upTo -= selectedDices.size();
			farkleController.getActionController().confirmAI(selectedDices);
		}
	}

	private void letNormalAIPlay (){


		Player player = farkleController.getFarkle().getCurrentGame().getCurrentPlayer();

		canPlayMore = ! (farkleController.getRoundController().isEndOfGame(
				farkleController.getFarkle().getCurrentGame().getCurrentRound())
					|| player.isPlayed());

		while (canPlayMore) {

			ArrayList<Dice> dices = new ArrayList<>();
			Random random = new Random();

			if(upTo==0) upTo = 6;

			for( int i=0;i<upTo;i++) {
				dices.add(new Dice(random.nextInt(6) + 1));
			}

			takeDecision(dices);

			System.out.println();
			System.out.println("My name is: " + player.getUserName());
			System.out.println("I have yet " + upTo + " dices!");
			System.out.println("My current round score is: " + player.getRoundScore());
			System.out.println("My score is: " + player.getScore());
			System.out.println("That is the round number: "
					+ farkleController.getFarkle().getCurrentGame().getCurrentRound().getRoundNum());


			int size = selectedCollections.length;


			if (upTo < 3 && player.getRoundScore()>=250) {
				System.out.println("Bank");
				upTo = 6;
				farkleController.getActionController().bank(player);
				break;
			}


			if(size == 0) {
				System.out.println("Lol it is Farkle!");
				upTo = 6;
				farkleController.getActionController().farkle(player);
				break;
			}

			ArrayList<Dice> selectedDices = new ArrayList<>();
			int [] selected = this.selectedCollections.length > 0 ? selectedCollections[0] : new int[0];

			for (int i : selected) {
				selectedDices.add(new Dice(i));
			}

			System.out.println("Confirm: " + Arrays.toString(selected));
			upTo -= selectedDices.size();
			farkleController.getActionController().confirmAI(selectedDices);
		}

	}

	private void letHardAIPlay (){


		Player player = farkleController.getFarkle().getCurrentGame().getCurrentPlayer();

		canPlayMore = ! (farkleController.getRoundController().isEndOfGame(
				farkleController.getFarkle().getCurrentGame().getCurrentRound())
				|| player.isPlayed());

		while (canPlayMore) {

			ArrayList<Dice> dices = new ArrayList<>();
			Random random = new Random();

			if(upTo==0) upTo = 6;

			for( int i=0;i<upTo;i++) {
				dices.add(new Dice(random.nextInt(6) + 1));
			}

			takeDecision(dices);

			System.out.println();
			System.out.println("My name is: " + player.getUserName());
			System.out.println("I have yet " + upTo + " dices!");
			System.out.println("My current round score is: " + player.getRoundScore());
			System.out.println("My score is: " + player.getScore());
			System.out.println("That is the round number: "
					+ farkleController.getFarkle().getCurrentGame().getCurrentRound().getRoundNum());

			int size = selectedCollections.length;

			if ( player.getRoundScore() >= 1000 && upTo <= 2 || upTo < 3 && player.getRoundScore() >= 300 || upTo < 1 && player.getRoundScore()>=250) {
				System.out.println("Bank");
				upTo = 6;
				farkleController.getActionController().bank(player);
				break;
			}

			if(size == 0) {
				System.out.println("Lol it is Farkle!");
				upTo = 6;
				farkleController.getActionController().farkle(player);
				break;
			}

			ArrayList<Dice> selectedDices = new ArrayList<>();
			int [] selected = this.selectedCollections.length > 0 ? selectedCollections[0] : new int[0];

			for (int i : selected) {
				selectedDices.add(new Dice(i));
			}

			if (player.getFarkle() < 2) {

				if(selectedDices.stream().allMatch( dice -> dice.getValue()==1 || dice.getValue()==5 ) &&
						selectedDices.size() != 6  && !selectedDices.stream().allMatch(dice -> dice.getValue()==1) &&
						!selectedDices.stream().allMatch(dice -> dice.getValue()==5) &&
						selectedDices.size() != upTo) {

					if (selectedDices.stream().anyMatch(dice -> dice.getValue()==1)){
						selectedDices.removeIf(dice -> dice.getValue()==5);
					}

					if (selectedDices.size() > 0) {
						Dice d = selectedDices.get(0);
						selectedDices.clear();
						selectedDices.add(d);
					}

				}
			}

			int [] selectedModified = new int[selectedDices.size()];
			for (int i=0; i< selectedModified.length; i++) {
				selectedModified[i] = selectedDices.get(i).getValue();
			}

			System.out.println("Confirm: " + Arrays.toString(selectedModified));
			upTo -= selectedDices.size();
			farkleController.getActionController().confirmAI(selectedDices);
		}

	}

}