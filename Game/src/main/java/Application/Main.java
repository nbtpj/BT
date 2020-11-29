package Application;

import Gobject.*;
import Support_Type.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage theStage) throws Exception {
        theStage.setTitle( "Wibu World" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );
        Bomber paimon = new Bomber("paimon",61,61);
        Map map = new Map(0);
        map.AddGobject(paimon);
        root.getChildren().add( map.Frame );
        EventHandler<KeyEvent> keydown = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String a = event.getCharacter().toUpperCase();
                switch (a){
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
        };/*
        EventHandler<MouseEvent> mouseclick = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Pos target = new Pos(event.getX(),event.getY());
                paimon.move2(target);
                }
            }
        };*/

        theStage.addEventHandler(KeyEvent.KEY_TYPED,keydown);
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                Pos target = new Pos(e.getX()- Pos.SIZE/2,e.getY()- Pos.SIZE/2+Pos.SIZE/5);
                System.out.println(target);
                paimon.Move2(target);
            }
        };
        map.Frame.addEventHandler(MouseEvent.MOUSE_CLICKED,eventHandler);

        long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {

                double t = (currentNanoTime - startNanoTime) / 1000000.0;
                   /* try {
                        paimon.Act("none");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/
                //  System.out.println(t);
                try {
                    //   if(paimon.heal_index<0) return;
                    map.render(0.016);
                } catch (Exception e) {
                    return;
                }
            }
        }.start();

        theStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
