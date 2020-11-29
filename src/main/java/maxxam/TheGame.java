package maxxam;

import engine.GameWorld;
import engine.Sprite;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import object.*;

import java.io.File;
import java.util.Scanner;

public class TheGame extends GameWorld {

    Player player;
    Scanner map;
    int level;
    int height;
    int width;

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
    protected void handleUpdate(Sprite sprite) {
        sprite.update();
    }

    @Override
    protected void handleCollision(Sprite sprite) {
        sprite.executeCollision();
    }


//#####################################################################################################################
// internal method
    private void generateMap(Stage stage) {

        try {
            File file = new File(getClass().getResource("/maxxam/map/level1.txt").toURI());
            System.out.println(file);
            map = new Scanner(file);
        } catch (Exception e) {
            System.out.println("can not find level-text file");
        }

        level = map.nextInt();
        height = map.nextInt();
        width = map.nextInt();

        sprite_map = new char[height][width];

        String s = map.nextLine();

        System.out.println(level);
        System.out.println(height);
        System.out.println(width);

        Background.init(height, width);

        for (int i = 0; i < height; i++) {
            s = map.nextLine();
            for (int j = 0; j < width; j++) {
                sprite_map[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                char c = sprite_map[i][j];
                if (c == '#') {
                    Wall.init(j, i);
                }
                if (c == 'p') {
                    player = Player.init(j, i);
                }
                if (c == '*') {
                    Box.init(j, i);
                }
                System.out.print(c);
            }
            System.out.println("");
        }

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
            if (keyEvent.getCode() == KeyCode.SPACE) {
                if (count_bombs() < player.power_bomb) {
                    player.storeBomb();
                }
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

    private int count_bombs() {
        int cnt = 0;
        for (Sprite sprite: getSpriteManager().getGameActorsList()) {
            if (sprite instanceof Bomb) {
                cnt ++;
            }
        }
        return cnt;
    }
}
