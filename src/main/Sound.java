package main;

import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound() {
        try {
            soundURL[0] = new File("src\\resources\\sound\\BlueBoyAdventure.wav").toURI().toURL();
            soundURL[1] = new File("src\\resources\\sound\\coin.wav").toURI().toURL(); 
            soundURL[2] = new File("src\\resources\\sound\\powerup.wav").toURI().toURL(); 
            soundURL[3] = new File("src\\resources\\sound\\unlock.wav").toURI().toURL(); 
            soundURL[4] = new File("src\\resources\\sound\\fanfare.wav").toURI().toURL(); 
            // System.out.println(soundURL[0]);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void setFile(int i) {
        try {
            if (soundURL[i] == null) {
                System.out.println("Error: Sound file at index " + i + " is null.");
                return;
            }
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();  // Print the error to know what went wrong
        }
    }

    public void play() {
        if (clip != null) {
            clip.start();
        } else {
            System.out.println("Error: Clip is null. Did you call setFile()?");
        }
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            System.out.println("Error: Clip is null. Did you call setFile()?");
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}
