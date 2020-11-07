package model;

public enum EffectType {

 START_ONE_HUNDERT(false),

 NO_FARKLE(false),

 START_BREAK(false),

 BREAK_RULE(false),

 MORE_THROW(false),

 SWITCH_SCORES(false),

 DOBULE_TIMER(false),

 HALF_TIMER(false),

 MAKE_UR_LUCK(false),

 NO_ROUND(false);

	private boolean used;
	
	// Constructor
	private EffectType(boolean used)
	{
		this.used = used;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

}
