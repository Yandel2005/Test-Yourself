package screens;

import gameoutput.GameData;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mechanics.Buttons;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import users.User;

public class LoginScreen {

    private HBox buttonLayout;
    private VBox layout;
    private GameData db;
    private Stage primaryStage;
    private Buttons buttons;
    private TextField usernameInput;
    private PasswordField passwordInput;
    private Label errorLabel;
    private HBox header;

    public LoginScreen(Stage primaryStage, GameData db) {
        this.primaryStage = primaryStage;
        this.db = db;
        this.buttons = new Buttons();
        this.buttons.createButtons();
        this.errorLabel = new Label();

        errorLabel.getStyleClass().add("error-text");

        createHeader();
        createLayout();

        buttons.getBtnLogin().setOnAction(e -> {
           String typedUser = usernameInput.getText();
           String typedPass = passwordInput.getText();
           User verifiedUser = db.checkLogin(typedUser, typedPass);
           if(verifiedUser != null) {
               MainMenu mainMenu = new MainMenu(primaryStage, verifiedUser, db);
               primaryStage.getScene().setRoot(mainMenu.getLayout());
           } else {
               errorLabel.setText("INVALID USERNAME OR PASSWORD.");
           }
        });

        buttons.getBtnRegister().setOnAction(e -> {
            boolean success = db.registerUser(usernameInput.getText(), passwordInput.getText());
            if(success) {
                errorLabel.setText("ACCOUNT CREATED! YOU MAY NOW LOGIN.");
            } else {
                errorLabel.setText("REGISTRATION FAILED.");
            }
        });
    }

    private void createLayout() {
        layout = new VBox(10);
        buttonLayout = new HBox(20);
        buttonLayout.setAlignment(Pos.CENTER);
        layout.setAlignment(Pos.CENTER);

        Text usernameText = new Text("USERNAME: ");
        Text passwordText = new Text("PASSWORD: ");
        usernameText.getStyleClass().add("label");
        passwordText.getStyleClass().add("label");

        usernameInput = new TextField();
        usernameInput.setPromptText("ENTER USERNAME: ");
        usernameInput.getStyleClass().add("text-field");

        passwordInput = new PasswordField();
        passwordInput.setPromptText("ENTER PASSWORD: ");
        usernameInput.setMaxWidth(300);
        passwordInput.setMaxWidth(300);

        buttonLayout.getChildren().addAll(buttons.getBtnLogin(), buttons.getBtnRegister());
        layout.getChildren().addAll(header, usernameText, usernameInput, passwordText, passwordInput, buttonLayout, errorLabel);

        BorderPane mainRoot = new BorderPane();

        mainRoot.getStyleClass().add("mainRoot");
        mainRoot.setTop(header);
        mainRoot.setCenter(layout);

        if (primaryStage.getScene() == null) {
            primaryStage.setScene(new Scene(mainRoot));
        } else {
            primaryStage.getScene().setRoot(mainRoot);
        }
        String css = getClass().getResource("/styles.css").toExternalForm();
        if (!primaryStage.getScene().getStylesheets().contains(css)) {
            primaryStage.getScene().getStylesheets().add(css);
        }

            primaryStage.setMaximized(true);
            primaryStage.show();
    }

    private void createHeader() {
        Text title = new Text("TEST YOURSELF");
        title.getStyleClass().add("header-title");
        header = new HBox(title);
        header.setAlignment(Pos.TOP_CENTER);

    }
}
