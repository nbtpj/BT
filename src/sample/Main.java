package sample;

import data.AnimatedImage;
import data.Data;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Object.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.TimerTask;

public class Main extends Application {
    @Override
    /*public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Wibu World");
        //Handling the key typed event
        Bomber paimon = new Bomber("paimon",15,15);
        Map x = new Map(0);
        x.AddObject(paimon);
        EventHandler<KeyEvent> keydown = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String a = event.getCharacter().toUpperCase();
                switch (a){
                    case "A":
                        System.out.println("left");
                        paimon.Act("left");
                        try {
                            primaryStage.getScene().setRoot(x.render());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "W":
                        System.out.println("up");
                        paimon.Act("up");
                        try {
                            primaryStage.getScene().setRoot(x.render());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "S":
                        System.out.println("right");
                        paimon.Act("right");
                        try {
                            primaryStage.getScene().setRoot(x.render());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Z":
                        System.out.println("down");
                        paimon.Act("down");
                        try {
                            primaryStage.getScene().setRoot(x.render());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case " ":
                        System.out.println("set_bomb");
                        paimon.Act("set_bomb");
                        try {
                            primaryStage.getScene().setRoot(x.render());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;

                }
            }
        };

        Group root = x.render();
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.addEventHandler(KeyEvent.KEY_TYPED,keydown);
        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
              //  paimon.Act("None");
            }
        }.start();
        primaryStage.show();

    }*/
        public void start(Stage theStage) throws Exception {
            theStage.setTitle( "Wibu World" );

            Group root = new Group();
            Scene theScene = new Scene( root );
            theStage.setScene( theScene );
            Bomber paimon = new Bomber("paimon",61,61);
            Map map = new Map(0);
            map.AddObject(paimon);
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
