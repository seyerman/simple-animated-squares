package ui;


import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.MobilePoint;
import model.TwinklePoint;
import threads.SpriteThread;

public class SquareGUIController {

    @FXML
    private TextField redPoint;

    @FXML
    private TextField greenPoint;

    @FXML
    private TextField bluePoint;

    @FXML
    private BorderPane borderPane;
    
    @FXML
    private Button animateButton;
    
    @FXML
    private Label statusBarLabel;
    
    private GridPane gridPane;
    
    private Button[][] labels;
    
    private List<SpriteThread> spriteThreads;
    
    private final static int SIZE = 5;
    
    @FXML
    public void initialize() {
    	gridPane = new GridPane();
    	labels = new Button[SIZE][SIZE];
    	borderPane.setCenter(gridPane);
    	for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				labels[i][j] = new Button("   ");
				labels[i][j].setDisable(true);
				gridPane.add(labels[i][j], j, i);
			}
		}
    	spriteThreads = new ArrayList<>();
    }
    
    @FXML
    public void animate(ActionEvent event) {
    	try {
	    	animateButton.setDisable(true);
	    	redPoint.setEditable(false);
	    	greenPoint.setEditable(false);
	    	bluePoint.setEditable(false);
	    	
	    	long timeRed   = Long.parseLong(redPoint.getText());
	    	long timeGreen = Long.parseLong(greenPoint.getText());
	    	long timeBlue  = Long.parseLong(bluePoint.getText());
	    	
	    	MobilePoint point1 = new MobilePoint(0, SIZE, 1, 1, timeRed);
	    	SpriteThread pt1 = new SpriteThread(point1, this);
	    	spriteThreads.add(pt1);
	    	pt1.start();
	    	
	    	MobilePoint point2 = new MobilePoint(1, SIZE, -1, 2, timeGreen);
	    	SpriteThread pt2 = new SpriteThread(point2, this);
	    	spriteThreads.add(pt2);
	    	pt2.start();
	    	
	    	TwinklePoint point3 = new TwinklePoint(2, 3, timeBlue);
	    	SpriteThread pt3 = new SpriteThread(point3, this);
	    	spriteThreads.add(pt3);
	    	pt3.start();
	    	
    		statusBarLabel.setText("");
    	}catch (NumberFormatException nfe) {
    		statusBarLabel.setText("Please enter valid integer values on time sleep fields.");
    		
	    	animateButton.setDisable(false);
	    	redPoint.setEditable(true);
	    	greenPoint.setEditable(true);
	    	bluePoint.setEditable(true);    		
		}
    }
    
    public void paintPoint(int i, int j, int color) {
    	String colorStyle = getColorStyle(color);
    	labels[i][j].setStyle(colorStyle);
    }
    
    //0 is default color, 1 is red, 2 is green, 3 is blue
    private String getColorStyle(int color) {
    	String style = null;
    	switch (color) {
			case 0:
				style = null;				
			break;
			case 1:
				style = "-fx-background-color:#FF0000;";
			break;
			case 2:
				style = "-fx-background-color:#00FF00;";
			break;
			case 3:
				style = "-fx-background-color:#0000FF;";
			break;
			default:
				style = null;
			break;
		}
    	return style;
    }
    
    public void onCloseRequest() throws InterruptedException {
    	statusBarLabel.setText("Please wait until the threads are finalized");
    	System.out.println("Finalizing threads ...");
    	for (int i = 0; i < spriteThreads.size(); i++) {
			spriteThreads.get(i).deactivate();
			spriteThreads.get(i).join();
		}
    	System.out.println("Threads finalized!");
    }

}
