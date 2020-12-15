package Application;

import Gobject.Bomber;
import Support_Type.Map;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Game_World implements Part_Of_Game {
    private static final String DEFAULT = "Cat01-1";
    boolean music, sound;
    private String character_type;
    private Map map;

    public Game_World(String type, boolean music, boolean sound) {
        this.music = music;
        this.sound = sound;
        this.character_type = type;
        if (type == null) {
            character_type = DEFAULT;
        }
    }

    public Game_World(Map map, boolean music, boolean sound) {
        this.map = map;
        this.sound = sound;
        this.music = music;
    }

    public Scene turnOn(Stage stage) throws Exception {
        Map map;
        Bomber paimon;
        if (this.map == null) {
            paimon = new Bomber("paimon", character_type, 61, 61);
            map = new Map(stage);
            map.setMain(paimon);
        } else {
            paimon = this.map.getMain();
            map = this.map;
        }
        map.setSoundnMusic(sound, music);
        stage.getScene().setRoot(map.graphic);
        EventHandler<KeyEvent> keydown = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String a = event.getCharacter().toUpperCase();
                switch (a) {
                    case "A":
                        try {
                            paimon.Act("left");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "W":
                        try {
                            paimon.Act("up");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "S":
                        try {
                            paimon.Act("right");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Z":
                        try {
                            paimon.Act("down");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case " ":
                        try {
                            paimon.Act("set_bomb");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;

                }
            }
        };

        stage.getScene().addEventHandler(KeyEvent.KEY_TYPED, keydown);


        AnimationTimer timer = new AnimationTimer() {
            public void handle(long currentNanoTime) {
                try {
                    if (!paimon.using) {
                        this.stop();
                    }
                    map.render(0.016);
                } catch (Exception e) {
                    return;
                }
            }
        };
        map.setTimer(timer);
        timer.start();
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                timer.stop();
                map.pause();
            }
        };
        map.Frame.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        return stage.getScene();
    }
}
