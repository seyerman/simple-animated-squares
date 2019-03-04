package threads;

import model.Sprite;
import ui.SquareGUIController;

public class SpriteThread extends Thread{
	private Sprite sprite;
	private SquareGUIController animatedGUI;
	private boolean active;
	
	public SpriteThread(Sprite sp, SquareGUIController gui) {
		sprite = sp;
		animatedGUI = gui;
		active = true;
	}
	
	public void run() {
		while(active) {
			animatedGUI.paintPoint(sprite.getRow(), sprite.getColumn(), 0);
			sprite.move();
			animatedGUI.paintPoint(sprite.getRow(), sprite.getColumn(), sprite.getColor());
			try {
				sleep(sprite.getTimeSleep());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deactivate() {
		active = false;
	}
}
