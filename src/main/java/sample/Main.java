package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;

public class Main extends Application {
    private static final double W = 600, H = 400;

    private static final String HERO_IMAGE_LOC =
            "data/events/Bomber/paimon.png";
    boolean running, goNorth, goSouth, goEast, goWest;
    private Image heroImage;
    private Node hero;

    public static void main(String[] args) {
        launch(args);
        //System.out.println("hello world");

    }

    @Override
    public void start(Stage stage) throws Exception {
        heroImage = new Image(new FileInputStream(HERO_IMAGE_LOC));
        hero = new ImageView(heroImage);

        Group dungeon = new Group(hero);

        moveHeroTo(W / 2, H / 2);

        Scene scene = new Scene(dungeon, W, H, Color.FORESTGREEN);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        goNorth = true;
                        break;
                    case DOWN:
                        goSouth = true;
                        break;
                    case LEFT:
                        goWest = true;
                        break;
                    case RIGHT:
                        goEast = true;
                        break;
                    case SHIFT:
                        running = true;
                        break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        goNorth = false;
                        break;
                    case DOWN:
                        goSouth = false;
                        break;
                    case LEFT:
                        goWest = false;
                        break;
                    case RIGHT:
                        goEast = false;
                        break;
                    case SHIFT:
                        running = false;
                        break;
                }
            }
        });

        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;

                if (goNorth) dy -= 1;
                if (goSouth) dy += 1;
                if (goEast) dx += 1;
                if (goWest) dx -= 1;
                if (running) {
                    dx *= 3;
                    dy *= 3;
                }

                moveHeroBy(dx, dy);
            }
        };
        timer.start();
    }

    private void moveHeroBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        final double cx = hero.getBoundsInLocal().getWidth() / 2;
        final double cy = hero.getBoundsInLocal().getHeight() / 2;

        double x = cx + hero.getLayoutX() + dx;
        double y = cy + hero.getLayoutY() + dy;

        moveHeroTo(x, y);
    }

    private void moveHeroTo(double x, double y) {
        final double cx = hero.getBoundsInLocal().getWidth() / 2;
        final double cy = hero.getBoundsInLocal().getHeight() / 2;

        if (x - cx >= 0 &&
                x + cx <= W &&
                y - cy >= 0 &&
                y + cy <= H) {
            hero.relocate(x - cx, y - cy);
        }
    }

 /*   @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        stage.setTitle("Hello World");
        InputStream stream = new FileInputStream("data/events/Bomber/paimon.png");
        Image image = new Image(stream);
        //Creating the image view
        ImageView imageView = new ImageView();
        //Setting image to the image view
        imageView.setImage(image);
        //Setting the image view parameters
        imageView.setX(80);
        imageView.setY(80);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);
        Group Root = new Group(imageView);
        Scene scene = new Scene(Root, 595, 370);
        stage.setTitle("Displaying Image");
        stage.setScene(scene);
        stage.show();
    }*/
}
