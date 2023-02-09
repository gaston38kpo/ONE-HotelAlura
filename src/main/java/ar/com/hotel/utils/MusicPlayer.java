package ar.com.hotel.utils;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class MusicPlayer {

    Clip clip;
    AudioInputStream sound;
    long currentFrame;

    public void setFile(String soundFileName) {
        try {
            File file = new File(soundFileName);
            sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
        }
    }

    public void play() {
        clip.start();
    }

    public void stop() throws IOException {
        sound.close();
        clip.close();
        clip.stop();
    }

    public void pause() {
        if (clip.isRunning()) {
            currentFrame = clip.getMicrosecondPosition();
            clip.stop();
        }
    }

    public void resume() {
        clip.setMicrosecondPosition(currentFrame);
        clip.start();
    }
}
