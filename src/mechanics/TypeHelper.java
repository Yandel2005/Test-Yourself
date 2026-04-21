package mechanics;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import modes.HardMode;
import screens.GameScreen;
import users.User;
import java.util.Random;

public class TypeHelper {


    private HardMode hardMode;

    Random random = new Random();

    private String chosenWord;

    private VBox typingUI = new VBox();

    private String[] longWords = {
            "POLYMORPHISM", "ENCAPSULATION", "SYNCHRONIZED",
            "INHERITANCE", "ABSTRACTION", "INSTANTIATE",
            "PHENOMENON", "LABYRINTHINE", "QUINTESSENTIAL",
            "METAMORPHOSIS", "UNFATHOMABLE", "OSCILLATION"};

    public void createTypingUI(GameScreen screen, User user, HardMode hardMode) {
        String word = getRandomWord();
        Label label = new Label("TYPE: ");
        Label penaltyDescription = new Label();
        TextField textField = new TextField();
        Text instructions = new Text("Quick! Type: "  + word + "!");
        HBox hb = new  HBox(label, textField);

        typingUI.getChildren().clear();

        label.getStyleClass().add("typeInstructions");
        textField.getStyleClass().add("text-field");
        instructions.getStyleClass().add("header-title");
        penaltyDescription.getStyleClass().add("stats");

        hb.setAlignment(Pos.CENTER);
        typingUI.setAlignment(Pos.CENTER);
        typingUI.getChildren().addAll(instructions, hb, penaltyDescription);

        textField.setTextFormatter(new javafx.scene.control.TextFormatter<>(change -> {
            if (change.isAdded()) {
                String newText = change.getControlNewText();
                int index = newText.length() - 1;

                if (newText.length() > word.length()) {
                    return null;
                }
                char typedChar = change.getText().charAt(0);
                char expectedChar = word.charAt(index);

                if (Character.toLowerCase(typedChar) == Character.toLowerCase(expectedChar)) {
                    penaltyDescription.setVisible(false);
                    if(newText.equalsIgnoreCase(word)) {
                        javafx.application.Platform.runLater(() -> {
                            user.setAccuracy(user.getAccuracy() + 30);
                            hardMode.stopChallengeTimer();
                            screen.showHeader();
                            screen.getLayout().setCenter(screen.getGamePane());
                            screen.startGame();
                        });
                    }
                    return change;
                } else {
                    penalty(user, textField, penaltyDescription);
                    return null;
                }
            }
            return change;
        }));

        Platform.runLater(() -> {
            textField.requestFocus();

        });
    }

    public void penalty(User user, TextField textField, Label penaltyDescription ) {
        penaltyDescription.setVisible(true);
        penaltyDescription.setText("-100!");
        int newScore = user.getScore() - 100;

        if (newScore < 0) {
            user.setScore(0);
            penaltyDescription.setText("KEEP UP!");
        } else {
            user.setScore(newScore);

        }

    }

    public void startTypingChallenge(GameScreen screen, GameSquare square, User user, HardMode hardMode) {
        screen.hideHeader();
        createTypingUI(screen, user, hardMode);
        screen.getLayout().setCenter(typingUI);

    }

    private String getRandomWord() {
        int random_word = random.nextInt(longWords.length);
        chosenWord = longWords[random_word];
        return chosenWord;
    }
    }

