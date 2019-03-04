package model;

public abstract class Sprite {
	protected int row;
	protected int column;
	
	protected int color;
	protected long timeSleep;
	
	public Sprite(int initPos, int c, long ts) {
		timeSleep = ts;
		color = c;
		
		row = initPos;
		column = initPos;		
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public int getColor() {
		return color;
	}
	
	public long getTimeSleep() {
		return timeSleep;
	}
	
	public abstract void move();
}
