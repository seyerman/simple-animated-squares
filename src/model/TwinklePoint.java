package model;

public class TwinklePoint extends Sprite{
	private boolean state;
	private int colorAssigned;
	public TwinklePoint(int pos, int c, long ts) {
		super(pos, c, ts);
		colorAssigned = c;
		state = true;
	}
	
	public void move() {
		state = !state;
		if(state) {
			color = colorAssigned;
		}else {
			color = 0;
		}
	}
}
