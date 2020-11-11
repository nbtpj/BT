package maxxam;

import engine.GameWorld;
import engine.ImageClass;
import engine.Sprite;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import object.Background;
import object.Player;
import object.Wall;

public class TheGame extends GameWorld {

    ImageClass imageClass = getImageClass();
    Player player;

    public TheGame(int fps, String title) {
        super(fps, title);
    }

    @Override
    public void initialize(Stage primaryStage) {
        primaryStage.setTitle(getWindowTile());

        setSceneNodes(new Group());
        setGameSurface(new Scene(getSceneNodes(), 1080, 720, Color.GREEN));

        primaryStage.setScene(getGameSurface());

        generateMap(primaryStage);
    }

    @Override
    protected void handleUpdate(Sprite sprite){
        sprite.update();
    }


//#####################################################################################################################
// internal method
    private void generateMap(Stage stage) {
        initBackground();
        initWall();
        initPlayer();

        setInput(stage);
    }

    private void setInput(Stage stage) {

        EventHandler<KeyEvent> playerMovePress = keyEvent -> {
            if (keyEvent.getCode() == KeyCode.A) {
                player.pressA = true;
            } else if (keyEvent.getCode() == KeyCode.S) {
                player.pressS = true;
            } else if (keyEvent.getCode() == KeyCode.D) {
                player.pressD = true;
            } else if (keyEvent.getCode() == KeyCode.W) {
                player.pressW = true;
            }
        };
        stage.getScene().setOnKeyPressed(playerMovePress);

        EventHandler<KeyEvent> playerMoveRelease = keyEvent -> {
            if (keyEvent.getCode() == KeyCode.A) {
                player.pressA = false;
            } else if (keyEvent.getCode() == KeyCode.S) {
                player.pressS = false;
            } else if (keyEvent.getCode() == KeyCode.D) {
                player.pressD = false;
            } else if (keyEvent.getCode() == KeyCode.W) {
                player.pressW = false;
            }
        };
        stage.getScene().setOnKeyReleased(playerMoveRelease);
    }

    private void initBackground() {
        Background background = new Background(imageClass, "ground", 720, 1080);
        getSpriteManager().addSprites(background);
        getSceneNodes().getChildren().add(background.node);
    }
    private void initPlayer() {
        Player player = new Player(imageClass, "player", 80, 80, 40, 40, 40);
        this.player = player;
        getSpriteManager().addSprites(player);
        getSceneNodes().getChildren().add(player.node);
    }
    private void initWall() {
        Wall wall = new Wall(imageClass, "wall", 80, 80, 120, 120, 40);
        getSpriteManager().addSprites(wall);
        getSceneNodes().getChildren().add(wall.node);
    }
}
