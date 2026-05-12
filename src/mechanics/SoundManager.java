package mechanics;

import javafx.scene.media.AudioClip;
import java.net.URL;

public class SoundManager {

    private static AudioClip clickSound;
    private static AudioClip squareClickSound;
    private static AudioClip nextRoundSound;
    private static AudioClip wrongSound;

    static {
        clickSound = loadSound("/sounds/click.wav");
        squareClickSound = loadSound("/sounds/squareClick.wav");
        nextRoundSound = loadSound("/sounds/nextRound.wav");
        wrongSound = loadSound("/sounds/wrong.wav");
    }

    private static AudioClip loadSound(String path) {
        try {
            URL resource = SoundManager.class.getResource(path);
            if (resource != null) {
                return new AudioClip(resource.toExternalForm());
            } else {
                System.out.println("Could not find sound file: " + path);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        }

        public static void playWrongSound() {
        if (wrongSound != null) {
            if (wrongSound.isPlaying()) {
                wrongSound.stop();
            }
            wrongSound.play();
        }
        }

    public static void playClickSound() {
        if (clickSound != null) {
            if(clickSound.isPlaying()) {
                clickSound.stop();
            }
            clickSound.play();
        }
    }

    public static void playNextRoundSound() {
        if (nextRoundSound != null) {
            nextRoundSound.play();
        }
    }

        public static void playSquareClick() {
        if (squareClickSound != null) {
            squareClickSound.play();
        }
        }
    }

