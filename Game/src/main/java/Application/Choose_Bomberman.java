package Application;

import Loader.Data;
import Loader.Movable_Object_Images;
import Support_Type.Pos;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Support_Type.Map.SIZE_X;
import static Support_Type.Map.SIZE_Y;

public class Choose_Bomberman implements Part_Of_Game {
    public static final double S_H = 120, S_W = 60, S_P = 10;
    private static final Map<String, Movable_Object_Images> data = Movable_Object_Images.getData();
    public static String character_type = null;
    public static Canvas screen = new Canvas(SIZE_X * Pos.SIZE, SIZE_Y * Pos.SIZE);
    public static Image bkg = Data.getInstance().bkg;
    static private final Image triangle1 = Data.getInstance().triangle1;
    boolean music, sound;
    AudioClip ad;
    public Choose_Bomberman(AudioClip ad, boolean music, boolean sound) {
        this.music = music;
        this.ad = ad;
        this.sound = sound;
        if (!music) {
            ad.stop();
        }
    }

    static private Image getImage(int pos, List<Image> list) {
        while (pos < 0) {
            pos += list.size();
        }
        while (pos > list.size()) {
            pos -= list.size();
        }
        return list.get(pos);
    }

    public static Image choose(Canvas cv, List<Image> list, int pos) {
        Image rs = list.get(pos);
        int W = (int) cv.getWidth(), H = (int) cv.getHeight();
        int bef = pos - 1, aft = pos + 1;
        GraphicsContext gc = cv.getGraphicsContext2D();
        gc.setGlobalAlpha(1);
        gc.drawImage(bkg, 0, 0, W, H);
        gc.drawImage(getImage(pos, list), W / 2 - S_W / 2, H / 2 - S_H / 2, S_W, S_H);
        gc.drawImage(triangle1, W / 2 - S_W / 2, H / 2 - S_H, S_W, S_H / 3);
        double next_w = S_W * 0.9, next_h = S_H * 0.9, next_x = S_W / 2 + S_P, next_y = H / 2 - S_H / 2;
        while (next_w > 5 && next_x < W / 2 - next_w) {
            gc.setGlobalAlpha(0.5);
            gc.drawImage(getImage(bef, list), W / 2 - next_x - next_w, next_y, next_w, next_h);
            gc.setGlobalAlpha(0.5);
            gc.drawImage(getImage(aft, list), W / 2 + next_x, next_y, next_w, next_h);
            next_x += next_w + S_P;
            next_w *= 0.9;
            next_h *= 0.9;
            bef--;
            aft++;
        }
        return rs;
    }

    @Override
    public Scene turnOn(Stage stage) throws Exception {
        Group root = new Group();
        stage.getScene().setRoot(root);
        root.getChildren().add(screen);
        List<Image> temp = new ArrayList<>();
        Map<Image, String> mp = new HashMap<>();
        for (Movable_Object_Images ob : data.values()) {
            if (!ob.name.contains("Enemy")) {
                temp.add(ob.get("front")[0]);
                mp.put(ob.get("front")[0], ob.name);
            }
        }
        character_type = mp.get(choose(screen, temp, 0));
        choose(screen, temp, 0);
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            private int i = 0;

            @Override
            public void handle(MouseEvent e) {

                character_type = mp.get(choose(screen, temp, ++i));
            }
        };
        EventHandler<KeyEvent> keydown = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                try {
                    ad.stop();
                    stage.getScene().removeEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
                    stage.getScene().removeEventHandler(KeyEvent.KEY_TYPED, this);
                    (new Game_World(character_type, music, sound)).turnOn(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        };

        stage.getScene().addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        stage.getScene().addEventHandler(KeyEvent.KEY_TYPED, keydown);
        return stage.getScene();
    }
}
