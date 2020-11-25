package maxxam;

import engine.GameWorld;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    public static GameWorld gameWorld = new TheGame(60, "Bomb IT");

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