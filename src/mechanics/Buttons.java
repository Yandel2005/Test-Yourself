package mechanics;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class Buttons {
	
	private Button btnStart;
	private Button btnSB;
	private Button btnExit;
	private Button btnRestart;
	private Button btnBack;
	private Button btnCD;
	private Button btnE;
	private Button btnM;
	private Button btnH;
	private Button btnPA;

	public void createButtons() {
		btnStart = new Button("START");
		btnSB = new Button("SCOREBOARD");
		btnExit = new Button("EXIT");
		btnRestart = new Button("RESTART");
		btnBack = new Button("BACK");
		btnCD = new Button("CHANGE DIFFICULTY");
		btnE = new Button("EASY");
		btnM = new Button("MEDIUM");
		btnH = new Button("HARD");
		btnPA = new Button("PLAY AGAIN");
		
		createButtonListeners();
		styleButtons();
	}

	private void createButtonListeners() {
		btnExit.setOnAction(e -> System.exit(0));
	}
	
	public Button getBtnStart() { 
		return btnStart; 
	}
	
    public Button getBtnSB() { 
    	return btnSB; 
    }
    
    public Button getBtnExit() { 
    	return btnExit; 
    }
    
	public Button getBtnEasy() { 
		return btnE; 
	}
	
	public Button getBtnMedium() {
		return btnM; 
	}
	
	public Button getBtnHard() { 
		return btnH; 
	}
	
	public Button getBtnBack() { 
		return btnBack; 
	}
	
	public Button getBtnPA() {
		return btnPA;
	}
	
    private void styleButtons() {
	Button[] buttons = { btnStart, btnSB, btnExit, btnCD, btnE, btnM, btnH, btnPA };
	for (int i = 0; i < buttons.length; i++) {
		buttons[i].getStyleClass().add("main-buttons");
	}
	btnBack.getStyleClass().add("back-button");
}
}
