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

    public Scanner map;

    public TheGame(int fps, String title, String level_url) {
        super(fps, title, level_url);
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
            File file = new File(getClass().getResource(level_url).toURI());
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
                    sprite_map[i][j] = ' ';
                }
                if (c == '*') {
                    Box.init(j, i);
                }
                if (c == '0') {
                    Enemy.init(j, i, 0);
                    sprite_map[i][j] = ' ';
                }
                if (c == '1') {
                    Enemy.init(j, i, 1);
                    sprite_map[i][j] = ' ';
                }
                if (c == '2') {
                    Enemy.init(j, i, 2);
                    sprite_map[i][j] = ' ';
                }
                if (c == '3') {
                    Enemy.init(j, i, 3);
                    sprite_map[i][j] = ' ';
                }
                if (c == '4') {
                    Enemy.init(j, i, 4);
                    sprite_map[i][j] = ' ';
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
                player.storeBomb();
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
}
