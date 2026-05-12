package mechanics;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

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
	private Button btnLogin;
	private Button btnRegister;

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
		btnLogin = new Button("LOGIN");
		btnRegister = new Button("REGISTER");
		
		createButtonListeners();
		styleButtons();
	}

	private void createButtonListeners() {
		Button[] allButtons = {
				btnStart, btnSB, btnExit, btnRestart,
				btnBack, btnCD, btnE, btnM,
				btnH, btnPA, btnLogin, btnRegister
		};

		for (int i = 0; i < allButtons.length; i++) {
			Button b = allButtons[i];

			if (b != null) {
				b.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
					mechanics.SoundManager.playClickSound();
				});
			}
		}
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
		Button back = new Button("BACK");
		back.getStyleClass().add("main-buttons");
		back.getStyleClass().add("back-button");

		return back;
	}

	public Button getBtnPA() {
		return btnPA;
	}

public Button getBtnLogin() {
		btnLogin.getStyleClass().add("login_buttons");
		return btnLogin;
}

public Button getBtnRegister() {
		btnRegister.getStyleClass().add("login_buttons");
		return btnRegister;
}

	private void styleButtons() {
		Button[] buttons = {btnStart, btnBack, btnSB, btnExit, btnCD, btnE, btnM, btnH, btnPA};
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].getStyleClass().add("main-buttons");
		}
	}
}
