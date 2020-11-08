package model;

public class Dice {
	
	private int value;
	
	private boolean used;
	
	public Dice(int value)
	{
		this.value = value;
		setUsed(false);
		
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
}
