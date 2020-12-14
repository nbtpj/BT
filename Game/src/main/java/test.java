import Loader.Data;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class test {
    public static void main(String[] args) {


    Media game_world_music = new javafx.scene.media.Media(new File(Data.localFilePath +
            "game_world_music.mp3").toURI().toString());
    MediaPlayer player = new MediaPlayer(game_world_music);
    player.play();
}
}
