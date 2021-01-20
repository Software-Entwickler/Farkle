package controller;

import model.Dice;
import java.util.*;


public class AIController {

	private FarkleController farkleController;

	public AIController(FarkleController farkleController) {
		this.farkleController = farkleController;
	}

	private class Decision {

		private int [] collection;
		private int gain;

		public Decision(int [] collection, int gain) {
			this.collection = collection;
			this.gain = gain;
		}

		public int [] getCollection() {
			return collection;
		}

		public void setCollection(int [] collection) {
			this.collection = collection;
		}

		public int getGain() {
			return gain;
		}

		public void setGain(int gain) {
			this.gain = gain;
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
}
