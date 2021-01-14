package model;

public class Dice {
	
	private int value;
	
	private boolean used;

	private boolean usedBefore ;
	
	
	
	public Dice(int value)
	{
		this.value = value;
		setUsed(false);
		setUsedBefore(false);
	}

	public Dice(){

	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public boolean isUsedBefore() {
		return usedBefore;
	}

	public void setUsedBefore(boolean usedBefore) {
		this.usedBefore = usedBefore;
	}
}
