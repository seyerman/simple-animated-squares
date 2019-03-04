package model;

public class MobilePoint extends Sprite{
	private int direction;
	private int state;
	
	private int rowMaxLimit;
	private int rowMinLimit;
	private int colMaxLimit;
	private int colMinLimit;	
	
	public final static int LEFT  = 0;
	public final static int UP    = 1;
	public final static int RIGHT = 2;
	public final static int DOWN  = 3;
	
	public MobilePoint(int initPos, int length, int dir, int c, long ts) {
		super(initPos, c, ts);
		
		direction = dir;		
		if(direction>0) {
			state = UP;
		}else {
			state = LEFT;
		}
		
		row = initPos;
		column = initPos;		
		
		rowMinLimit = initPos; 
		colMinLimit = initPos;
		
		rowMaxLimit = length-initPos-1;
		colMaxLimit = length-initPos-1;
	}
	
	public void move() {
		int factor = 1;
		switch(state) {
			case LEFT:
				factor = -1;				
			case RIGHT:
				row += factor*direction;
				if(row >=rowMaxLimit) {
					state = DOWN;
				}else if(row <=rowMinLimit) {
					state = UP;
				}
			break;
			case DOWN:
				factor = -1;
			case UP:
				column += factor*direction;
				if(column >=colMaxLimit) {
					state = RIGHT;
				}else if(column <=colMinLimit) {
					state = LEFT;
				}
			break;
		}
	}	
}
