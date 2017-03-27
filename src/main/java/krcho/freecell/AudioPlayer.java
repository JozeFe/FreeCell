package krcho.freecell;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Singleton class for playing sound in application.
 *
 * @author Jozef Krcho
 */
public class AudioPlayer {

    private static final AudioPlayer instance = new AudioPlayer();

    private final URL pick;
    private final URL drop;
    private final URL victory;

    public static AudioPlayer getInstance() {
        return instance;
    }

    private AudioPlayer() {
        this.pick = AudioPlayer.class.getResource("/zvuky/pick.wav");
        this.drop = AudioPlayer.class.getResource("/zvuky/drop.wav");
        this.victory = AudioPlayer.class.getResource("/zvuky/victory.wav");
    }

    /**
     * Play pick sound.
     */
    public void playPick() {
        this.playSound(pick);
    }

    /**
     * Play drop sound.
     */
    public void playDrop() {
         this.playSound(drop);
    }
    
    /**
     * Play victory sound.
     */
    public void playVictory() {
         this.playSound(victory);
    }

    /**
     * Play sound from existing file.
     * @param url sound file url
     */
    private void playSound(URL url) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
