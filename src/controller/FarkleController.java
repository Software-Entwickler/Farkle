package controller;

import model.Farkle;

public class FarkleController {

	private Farkle farkle =  new Farkle();

	private CalculationController calculationController = new CalculationController(this);

	private AIController aIController = new AIController(this);

	private RoundController roundController = new RoundController(this);

	private ActionController actionController = new ActionController(this);

	private RulesController rulesController = new RulesController(this);

	private EffectsController effectsController = new EffectsController(this);

	// Constructor is empty.
	public FarkleController() {
		
	}
	
	public void setFarkle(Farkle farkle)
	{
		this.farkle = farkle;
	}

	public Farkle getFarkle() {
		return farkle;
	}
	
	public void setActionController(ActionController actionController) {
		this.actionController = actionController;
	}

	public ActionController getActionController() {
		return actionController;
	}
	
	public void setCalculationController(CalculationController calculationController) {
		this.calculationController = calculationController;
	}

	public CalculationController getCalculationController() {
		return calculationController;
	}

	public void setRoundController(RoundController roundController) {
		this.roundController = roundController;
	}
	
	public RoundController getRoundController() {
		return roundController;
	}

	public void setAIController(AIController aIController) {
		this.aIController = aIController;
	}
	
	public AIController getAIController() {
		return aIController;
	}

	public void setEffectsController(EffectsController effectsController) {
		this.effectsController = effectsController;
	}
	
	public EffectsController getEffectsController() {
		return effectsController;
	}

	public void setRulesController(RulesController rulesController) {
		this.rulesController = rulesController;
	}
	
	public RulesController getRulesController() {
		return rulesController;
	}

}
