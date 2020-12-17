package maxxam;

import Controller.EnemyController;
import engine.GameWorld;
import engine.MusicManager;
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
import java.util.Random;
import java.util.Scanner;

public class TheGame extends GameWorld {

    public Scanner map;

    public TheGame(int fps, String title, Stage stage) {
        super(fps, title);
        this.stage = stage;
    }

    @Override
    public void initialize(Stage primaryStage) {
        primaryStage.setTitle(getWindowTile());
        init_sound();
    }

    @Override
    public void init_sound() {
        getSoundManager().loadSoundEffects("step_right", getClass().getResource("/maxxam/sounds/step_right.mp3"));
        getSoundManager().loadSoundEffects("step_left", getClass().getResource("/maxxam/sounds/step_left.mp3"));
        getSoundManager().loadSoundEffects("explode", getClass().getResource("/maxxam/sounds/bomb_explode.mp3"));
        getSoundManager().loadSoundEffects("eaten", getClass().getResource("/maxxam/sounds/buff_eaten.mp3"));
        MusicManager.loadMusic(getClass().getResource("/maxxam/sounds/funny1.mp3"));
        MusicManager.loadMusic(getClass().getResource("/maxxam/sounds/funny2.mp3"));
        MusicManager.loadMusic(getClass().getResource("/maxxam/sounds/funny3.mp3"));
    }

    public void start_level(Stage primaryStage) {
        is_nx_level = false;
        getSpriteManager().resetAll();
        setSceneNodes(new Group());
        setGameSurface(new Scene(getSceneNodes(), 1056, 704, Color.GREEN));

        primaryStage.setScene(getGameSurface());

        generateMap(primaryStage);
    }

    @Override
    public void exeWin() {
        for(Sprite sprite: getSpriteManager().getGameActorsList()) {
            if (sprite instanceof Enemy) {
                return;
            }
        }
//        start_level(stage);
        is_nx_level = true;
    }

    @Override
    public void checkNxLevel() {
        if (is_nx_level)
            start_level(stage);
    }

    @Override
    public void setTitleStage() {
        String title = "Bom IT"
                + "      [[ LEVEL " + (level_next-1) + " ]] "
                + "      [[ MAX LEVEL: " + max_level + " ]] "
                + "      [[ LIVES: " + player.lives + " ]] "
                + "      [[ MAX BOMB: " + player.power_bomb + " ]] "
                + "      [[ POWER: " + player.power_flames + " ]] "
                + "      [[ SPEED x" + player.power_speed + " ]] ";
        stage.setTitle(title);
        max_level = Math.max(max_level, level_next-1);
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
        stage.setTitle("Bom IT: level " + level_next);

        if (level_next <= 3) {
            try {
                File file = new File(getClass().getResource("/maxxam/map/level" + level_next + ".txt").toURI());
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

            for (int i = 0; i < height; i++) {
                s = map.nextLine();
                for (int j = 0; j < width; j++) {
                    sprite_map[i][j] = s.charAt(j);
                }
            }
        } else {
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (i == 0 || i == height-1 || j == 0|| j == width-1) {
                        sprite_map[i][j] = '#';
                    }
                    else {
                        sprite_map[i][j] = ' ';
                    }
                }
            }

            Random random = new Random();

            while (true) {
                int j = Math.abs(random.nextInt()) % width;
                int i = Math.abs(random.nextInt()) % height;
                if (sprite_map[i][j] == ' ') {
                    sprite_map[i][j] = 'p';
                    break;
                }
            }
            while (true) {
                int j = Math.abs(random.nextInt()) % width;
                int i = Math.abs(random.nextInt()) % height;
                if (sprite_map[i][j] == ' ') {
                    sprite_map[i][j] = 'x';
                    break;
                }
            }
            int wall = 70;
            int box = 100;
            int enemy = level_next*3;
            int cnt;

            cnt = 0;
            while (true) {
                int j = Math.abs(random.nextInt()) % width;
                int i = Math.abs(random.nextInt()) % height;
                if (sprite_map[i][j] == ' ') {
                    sprite_map[i][j] = '#';
                    cnt++;
                    if (cnt >= wall)
                        break;
                }
            }

            cnt = 0;
            while (true) {
                int j = Math.abs(random.nextInt()) % width;
                int i = Math.abs(random.nextInt()) % height;
                if (sprite_map[i][j] == ' ') {
                    sprite_map[i][j] = '*';
                    cnt++;
                    if (cnt >= box)
                        break;
                }
            }

            cnt = 0;
            while (true) {
                int j = Math.abs(random.nextInt()) % width;
                int i = Math.abs(random.nextInt()) % height;
                if (sprite_map[i][j] == ' ') {
                    sprite_map[i][j] = (char)(EnemyController.rand_int_in(5) + '0');
                    cnt++;
                    if (cnt >= enemy)
                        break;
                }
            }
        }
        level_next++;

        Background.init(height, width);

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
                    Box.init(j, i, false);
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
                if (c == 'x') {
                    Box.init(j, i, true);
                }
                if (c == 'B') {
                    PowerUpBombs.init(j, i);
                }
                if (c == 'F') {
                    PowerUpFlames.init(j, i);
                }
                if (c == 'S') {
                    PowerUpSpeed.init(j, i);
                }
                System.out.print(c);
            }
            System.out.println("");
        }

        setInput(stage);

        // set music
        MusicManager.playMusic();
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
