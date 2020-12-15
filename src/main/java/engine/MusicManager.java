package engine;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MusicManager {
    public static MediaPlayer mediaPlayer;
    public static List<Media> musicsList = new ArrayList<>();

    public static void loadMusic(URL url) {
        if (url == null) {
            System.out.println("Music url is null!");
            return;
        }

        Media music = new Media(url.toExternalForm());
        musicsList.add(music);
    }

    public static void playMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        int x = (new Random()).nextInt(musicsList.size());
        mediaPlayer = new MediaPlayer(musicsList.get(x));
        mediaPlayer.setOnEndOfMedia(() -> {
            MusicManager.playMusic();
        });
        mediaPlayer.play();
    }
}
