package maxxam;

import engine.GameWorld;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    public static GameWorld gameWorld;

    @Override
    public void stop() {
        Platform.exit();
        gameWorld.shutdown();
    }

    @Override
    public void start(Stage stage) {
        gameWorld = new TheGame(60, "Bomb IT", stage);
        gameWorld.initialize(stage);
        gameWorld.start_level(stage);
        gameWorld.beginGameLoop();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}