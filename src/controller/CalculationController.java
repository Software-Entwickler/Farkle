package controller;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.util.Pair;
import model.Dice;

public class CalculationController {

	private FarkleController farkleController;

	public CalculationController(FarkleController farkleController) {
		this.farkleController = farkleController;
	}

	public int calculate(ArrayList<Dice> dices) {

		if (!this.farkleController.getRulesController().isValidCollection(dices)) return 0;
		
			
		int [] collection = dicesToNumbers(dices);

		switch (collection.length) {

			case 1:

				if (collection[0] == 1) {
					return 100;
				}
				else  {
					return 50;
				}

			case 2:

				ArrayList<Dice> case2_1 = new ArrayList<>();
				case2_1.add(dices.get(0));
				ArrayList<Dice> case2_2 = new ArrayList<>();
				case2_2.add(dices.get(1));

				return calculate(case2_1) + calculate(case2_2) ;

			case 3:

				if (areEqual(collection)) {
					if (collection[0] == 1) {
						return 300;
					}
					else {
						return collection[0] * 100 ;
					}
				}
				else {

					ArrayList<Dice> case3_1 = new ArrayList<>();
					case3_1.add(dices.get(0));
					ArrayList<Dice> case3_2 = new ArrayList<>();
					case3_2.add(dices.get(1));
					ArrayList<Dice> case3_3 = new ArrayList<>();
					case3_3.add(dices.get(2));

					return calculate(case3_1) + calculate(case3_2) + calculate(case3_3) ;

				}

			case 4:

				if (areEqual(collection)) {
					return 1000;
				}
				else {

					Pair<ArrayList<Dice>, ArrayList<Dice>> case4 = separateCollection(collection);

					return calculate(case4.getKey()) + calculate(case4.getValue()) ;

				}

			case 5:

				if (areEqual(collection)) {
					return 2000;
				}
				else {

					int majorCount = majorCounter(collection);

					if (majorCount==4) {

						Pair<ArrayList<Dice>, ArrayList<Dice>> case5_4 = separateCollection(collection);

						return calculate(case5_4.getKey()) + calculate(case5_4.getValue()) ;

					}
					else {
						return evaluateCollection(dices);
					}

				}

			case 6:

				if (areEqual(collection)) {
					return 3000;
				}
				else if (majorCounter(collection) == 1) {
					return 1500;
				}
			    else {

			    	if (isThreePairs(collection)) {
			    		return 1500;
					}
					else if (isTowTriplets(collection)) {
						return 2500;
					}
					else if (isFourOfKindPlusPair(collection)) {
						return 1500;
					}
					else {

						return evaluateCollection(dices);

					}
				}
		}

		return 0;

	}

	int [] dicesToNumbers (ArrayList<Dice> dices) {

		int [] result = new int[dices.size()];

		for ( int i = 0; i < dices.size(); i++) {
			result[i] = dices.get(i).getValue();
		}

		return result;

	}

	private boolean areEqual (int [] numbers) {

		int number = numbers[0];

		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] != number) return false;
		}

		return true;

	}

	private Pair<ArrayList<Dice>, ArrayList<Dice>> separateCollection (int [] numbers) {

		int major = numbers[0] == numbers[1] ? numbers[0] : numbers[2] ;

		ArrayList<Dice> resultMaxList = new ArrayList<>();
		ArrayList<Dice> resultMinList = new ArrayList<>();

		for ( int number : numbers ) {

			if (number != major) {
				resultMinList.add(new Dice(number));
			}
			else {
				resultMaxList.add(new Dice(number));
			}

		}

		return new Pair<>(resultMinList, resultMaxList);

	}

	private int majorCounter (int [] array) {

		int entityCount = 0;

		for (int number : array) {
			int tempMajor = 0;
			for (int count : array) {
				if (count == number) tempMajor++;
			}
			if (entityCount < tempMajor) entityCount = tempMajor ;
		}

		return entityCount;

	}

	private int majorValue (int [] array) {

		int majorCount = majorCounter(array);

		for (int number : array) {
			int tempCounter = 0;
			for (int count : array) {
				if (count == number) tempCounter++;
			}
			if (majorCount == tempCounter) return number;
		}

		return 0;

	}

	private boolean isThreePairs (int [] array) {

		Arrays.sort(array);

		return array[0]==array[1] && array[2]==array[3] && array[4]==array[5] ;

	}

	private boolean isTowTriplets (int [] array) {


		Arrays.sort(array);

		return (array[0]==array[1] && array[1]==array[2]) && (array[3]==array[4] && array[4]==array[5]) ;

	}

	private boolean isFourOfKindPlusPair (int [] array) {


		Arrays.sort(array);

		return ( (array[0]==array[1] && array[1]==array[2] && array[2]==array[3]) && (array[4]==array[5]) )
			||
				( (array[0]==array[1]) && (array[2]==array[3] && array[3]==array[4] && array[4]==array[5]) );

	}

	private int evaluateCollection (ArrayList<Dice> dices) {

		int [] collection = dicesToNumbers(dices);

		int major_Value = majorValue(collection);
		int major_Count = majorCounter(collection);

		ArrayList<Dice> maxList = new ArrayList<>();

		while (major_Count>0) {
			maxList.add(new Dice(major_Value));
			major_Count--;
		}

		ArrayList<Dice> minList = new ArrayList<>();
		minList.addAll(dices);
		minList.removeIf(dice -> dice.getValue() == major_Value);

		return calculate(maxList) + calculate(minList) ;

	}

}
