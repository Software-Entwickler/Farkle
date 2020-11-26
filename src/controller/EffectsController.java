package controller;

import model.Dice;
import model.EffectType;
import model.Player;
import model.Timer;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class EffectsController {

	private FarkleController farkleController;

	public EffectsController(FarkleController farkleController) {
		this.farkleController = farkleController;
	}

	private void chooseLuckNumber (Dice dice, int n) {
		if (n > 0 && n <=6) dice.setValue(n);
	}

	private void choosePlayerToSwitch (Player me, Player enemy) {
		int temp = me.getScore();
		me.setScore(enemy.getScore());
		enemy.setScore(temp);
	}

	public void useEffect(EffectType effect) {

		Player player = farkleController.getFarkle().getCurrentGame().getCurrentPlayer();

		switch (effect) {

			case BREAK_RULE:

				// Not now...

				if (!effect.isUsed()) {



				}

				break;

			case NO_ROUND:

				if(!effect.isUsed()) {
					farkleController.getRoundController().setNextPlayer();
					farkleController.getRoundController().setNextPlayer();
				}

				break;

			case NO_FARKLE:

				if (!effect.isUsed()) player.setFarkle(player.getFarkle()-1);

				break;

			case HALF_TIMER:

				if (!effect.isUsed()){
					farkleController.getFarkle().getCurrentGame().getCurrentPlayer().setPlayerTimer(new Timer(15));

				}

				break;

			case MORE_THROWS:

				if (!effect.isUsed()) {
					player.setScore(player.getRoundScore());
					farkleController.getActionController().resetPlayer(player);
				}

				break;

			case START_BANK:

				if (!effect.isUsed()) {
					farkleController.getFarkle().getCurrentGame().setCurrentPlayer(player);
					farkleController.getRoundController().setNextPlayer();
				}

				break;

			case DOBULE_TIMER:

				if (!effect.isUsed()){
					farkleController.getFarkle().getCurrentGame().getCurrentPlayer().setPlayerTimer(new Timer(60));
				}

				break;

			case MAKE_UR_LUCK:

				if (!effect.isUsed()) {
					ArrayList<Dice> playerDices = player.getDice();
					ArrayList<Dice> dices = (ArrayList<Dice>) playerDices.stream().filter(d -> ! (d.isUsed() || d.isUsedBefore()))
							.collect(Collectors.toList());
					chooseLuckNumber(dices.remove(0), 6);
				}
				break;

			case SWITCH_SCORES:

				if (!effect.isUsed()) {
					choosePlayerToSwitch(player, new Player(""));
				}
				break;

			case START_ONE_HUNDERT:

				if(!effect.isUsed()) {
					player.setScore(100);
				}

				break;

		}

	}

}
