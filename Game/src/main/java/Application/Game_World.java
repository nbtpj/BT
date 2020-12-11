package Application;

import Gobject.Bomber;
import Support_Type.Map;
import Support_Type.Pos;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.table.DefaultTableCellRenderer;

public class Game_World implements Part_Of_Game {
    private String character_type;
    private Map map;
    private static String DEFAULT = "Cat01-1";
    public Game_World(String type){
        this.character_type = type;
        if(type==null){
            character_type = DEFAULT;
        }
    }
    public Game_World(Map map){
        this.map = map;
    }
    public Scene turnOn(Stage stage) throws Exception {
       // Group root = new Group();
        Map map;
        Bomber paimon;
        if(this.map==null) {
             paimon = new Bomber("paimon", character_type, 61, 61);
            map = new Map(stage);
            map.setMain(paimon);} else {
            paimon = this.map.getMain();
            map = this.map;
        }
            Scene theScene = new Scene(map.graphic);
            stage.setTitle("Wibu World");
            stage.setScene(theScene);

            //  root.getChildren().add( map.Frame );
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

            theScene.addEventHandler(KeyEvent.KEY_TYPED, keydown);


            AnimationTimer timer = new AnimationTimer() {
                public void handle(long currentNanoTime) {
                    try {
                        if (!paimon.using) {
                            (new Losing()).turnOn(stage);
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
                /*Pos target = new Pos(e.getX()- Pos.SIZE/2,e.getY()- Pos.SIZE/2+Pos.SIZE/5);
                System.out.println(target);*/
                    timer.stop();
                    map.pause();
                }
            };
            map.Frame.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);


        stage.show();

        return theScene;
    }
}
