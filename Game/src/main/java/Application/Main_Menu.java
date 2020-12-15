package Application;

import Button.*;
import Loader.Data;
import Support_Type.Pos;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static Support_Type.Map.SIZE_X;
import static Support_Type.Map.SIZE_Y;


public class Main_Menu implements Part_Of_Game {
    public static ColorAdjust colorAdjust = new ColorAdjust();
    boolean music, sound;

    public Main_Menu() {
    }

    public Main_Menu(boolean music, boolean sound) {
        this.music = music;
        this.sound = sound;
    }

    @Override
    public Scene turnOn(Stage stage){
        AudioClip ad = Data.getInstance().main_menu_music;
        Group root = new Group();
        stage.getScene().setRoot(root);
        Canvas cv = new Canvas(SIZE_X * Pos.SIZE, SIZE_Y * Pos.SIZE);
        root.getChildren().add(cv);
        cv.getGraphicsContext2D().setGlobalAlpha(0.9);
        cv.getGraphicsContext2D().drawImage(Data.getInstance().bkg, 0, 0, SIZE_X * Pos.SIZE, SIZE_Y * Pos.SIZE);

        root.getChildren().add(new Quit(stage, cv.getWidth() / 2 - cv.getWidth() / 10, cv.getHeight() * 3 / 5 - cv.getHeight() / 16
                , cv.getWidth() / 5, cv.getHeight() / 8));
        Music music = new Music(ad, cv.getWidth() / 2 - cv.getWidth() / 10, cv.getHeight() * 4 / 5 - cv.getHeight() / 16
                , cv.getWidth() / 5, cv.getHeight() / 8);
        root.getChildren().add(music);
        Sound sound = new Sound(cv.getWidth() / 2, cv.getHeight() * 4 / 5 - cv.getHeight() / 16
                , cv.getWidth() / 5, cv.getHeight() / 8);
        root.getChildren().add(sound);
        Continue continue_ = new Continue(stage, cv.getWidth() / 2 - cv.getWidth() / 10, cv.getHeight() / 5 - cv.getHeight() / 16
                , cv.getWidth() / 5, cv.getHeight() / 8);
        if (!continue_.isEmpty) {
            continue_.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                ad.stop();
                try {
                    (new Game_World(continue_.map, music.on, sound.on)).turnOn(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        } else {
            colorAdjust.setBrightness(-0.5);
            continue_.setEffect(colorAdjust);
        }
        music.set(this.music);
        sound.set(this.sound);
        root.getChildren().add(continue_);
        NewGame new_game = new NewGame(stage, cv.getWidth() / 2 - cv.getWidth() / 10, cv.getHeight() * 2 / 5 - cv.getHeight() / 16
                , cv.getWidth() / 5, cv.getHeight() / 8);
        root.getChildren().add(new_game);

        new_game.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                List<File> filesInFolder = Files.walk(Paths.get("Game/src/main/resources/data/"))
                        .filter(Files::isRegularFile)
                        .map(Path::toFile)
                        .collect(Collectors.toList());
                for (File f : filesInFolder) f.delete();
            } catch (Exception E) {
                E.printStackTrace();
            }
            try {
                (new Choose_Bomberman(ad, music.on, sound.on)).turnOn(stage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        return stage.getScene();
    }
}
