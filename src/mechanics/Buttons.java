package mechanics;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class Buttons {
	
	private int selectedGameModeId;
	private Button btnStart;
	private Button btnSB;
	private Button btnExit;
	private Button btnRestart;
	private Button btnBack;
	private Button btnCD;
	private Button btnE;
	private Button btnM;
	private Button btnH;

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
		
		createButtonListeners();
		styleButtons();
	}

	private void createButtonListeners() {
		btnE.setOnAction(e -> {
			selectedGameModeId = 1;
			System.out.println("Selected Easy Mode, ID = " + selectedGameModeId);
		});
		//btnStart.setOnAction(e -> ());
		//btnBack.setOnAction(e -> showGame());
		btnExit.setOnAction(e -> System.exit(0));
		//btnE.setOnAction(e -> showEasyMode());
		//btnRestart.setOnAction(e -> showGame());
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
	
    private void styleButtons() {
	Button[] buttons = { btnStart, btnSB, btnExit, btnCD, btnE, btnM, btnH };
	for (int i = 0; i < buttons.length; i++) {
		buttons[i].setFont(Font.font("Arial Black", 50));
		buttons[i].setStyle(
		"-fx-background-radius: 50;" + 
		"-fx-border-radius: 50;");
	}
	btnBack.setStyle("-fx-background-radius: 50;" + 
		"-fx-border-radius: 50;");
}
}
