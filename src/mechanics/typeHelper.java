package mechanics;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import screens.GameScreen;
import users.User;

import java.util.Random;
import java.util.Scanner;


public class typeHelper {



    Random random = new Random();

    private boolean wrongInput;

    private String userInput;

    private String chosenWord;

    private VBox challengeBox = new VBox();

    private String[] longWords = {
            "POLYMORPHISM", "ENCAPSULATION", "SYNCHRONIZED",
            "INHERITANCE", "ABSTRACTION", "INSTANTIATE",
            "PHENOMENON", "LABYRINTHINE", "QUINTESSENTIAL",
            "METAMORPHOSIS", "UNFATHOMABLE", "OSCILLATION"};

    public void createTypingUI(GameScreen screen, User user) {
        challengeBox.getChildren().clear();
        VBox typingUI = new VBox();
        String word = getRandomWord();
        Label label = new Label("TYPE");
        TextField textField = new TextField();
        Text instructions = new Text("Quick! Type: "  + word + "!");
        HBox hb = new  HBox(label, textField);
        hb.setAlignment(Pos.CENTER);

        typingUI.getChildren().addAll(instructions, hb);

        challengeBox.getChildren().add(typingUI);
        textField.requestFocus();

        textField.textProperty().addListener((obs, oldVal, newVal) -> {

            if (newVal.isEmpty()) return;

            int index = newVal.length() - 1;
            if (index >= word.length()) {
                penalty(user, textField);
                return;

            }


            char typed = Character.toLowerCase(newVal.charAt(index));
            String typedWord = "" + typed;
            char actual = Character.toLowerCase(word.charAt(index));

            if (typed != actual) {
                penalty(user, textField);
                return;
            } else {

                if (newVal.equalsIgnoreCase(word)) {
                    screen.getLayout().getChildren().clear();
                    screen.getLayout().setCenter(screen.getGamePane());
                    screen.startGame();

                } else if (!typedWord.equals(word)) {
                    screen.endGame();


                }
            }
        });

            }

    private void penalty(User user, TextField textField) {

        user.setScore(user.getScore() - 100);

    }

    public void startTypingChallenge(GameScreen screen, GameSquare square, User user) {
        screen.getGamePane().getChildren().clear();
        createTypingUI(screen, user);
        screen.getLayout().setCenter(challengeBox);

    }

    private String getRandomWord() {
        int random_word = random.nextInt(longWords.length);
        chosenWord = longWords[random_word];
        return chosenWord;
    }

    }

