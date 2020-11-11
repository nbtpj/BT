package maxxam;

import engine.GameWorld;
import engine.ImageClass;
import engine.Sprite;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    GameWorld gameWorld = new TheGame(60, "Bomb IT");

    @Override
    public void start(Stage stage) {
        gameWorld.initialize(stage);
        gameWorld.beginGameLoop();
        stage.show();
    }

    @Override
    public void stop() {
        Platform.exit();
        gameWorld.shutdown();
    }

    public static void main(String[] args) {
        launch();
    }

}