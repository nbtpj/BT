package object;

import engine.Double;
import engine.Images;
import engine.Sprite;
import interfaces.Collision;
import javafx.scene.shape.Rectangle;
import maxxam.App;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Player extends Sprite implements Collision {
    // Images
    private static final Images[] images_up = Images.player_up;
    private static final Images[] images_down = Images.player_down;
    private static final Images[] images_left = Images.player_left;
    private static final Images[] images_right = Images.player_right;

    // Direct status
    public final double velocity = 1;
    public boolean pressA;
    public boolean pressS;
    public boolean pressD;
    public boolean pressW;

    // Buff status
    public int power_flames = 1;

    public int power_bomb = 1;

    public double power_speed = 1;
    public double time_power_speed = 0;

    // Collision status
    public boolean on_bomb = false;

    // Beginning position
    public double height;
    public double width;

    // cool down bomb
    public List<Double> cool_down_bomb = new ArrayList<>();

    // live
    public int lives = 3;

    public Player(double height, double width, double x, double y) {
        node = images_down[0].getImageView(height, width);
        node.setTranslateX(x);
        node.setTranslateY(y);
        collisionBound = new Rectangle();
        this.setupRectangleBound((Rectangle) collisionBound, x, y, height -1, width - 8);
    }

    @Override
    public void update() {
        checkBomb();

        vX = 0;
        vY = 0;
        if (pressA) {
            vX -= velocity;
        }
        if (pressW) {
            vY -= velocity;
        }
        if (pressD) {
            vX += velocity;
        }
        if (pressS) {
            vY += velocity;
        }

        time_power_speed -= 1f/App.gameWorld.getFramesPerSecond();
        if (time_power_speed < 0) {
            power_speed = 1;
        }

        vX *= power_speed;
        vY *= power_speed;

        double x = node.getTranslateX();
        double y = node.getTranslateY();
        if (vY < 0) {
            App.gameWorld.getSceneNodes().getChildren().remove(node);
            node = images_up[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
            App.gameWorld.getSceneNodes().getChildren().add(node);
        } else if (vY > 0) {
            App.gameWorld.getSceneNodes().getChildren().remove(node);
            node = images_down[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
            App.gameWorld.getSceneNodes().getChildren().add(node);
        } else if (vX < 0) {
            App.gameWorld.getSceneNodes().getChildren().remove(node);
            node = images_left[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
            App.gameWorld.getSceneNodes().getChildren().add(node);
        } else if (vX > 0) {
            App.gameWorld.getSceneNodes().getChildren().remove(node);
            node = images_right[(int)(new Date().getTime() / 100)%3+2].getImageView(32, 32);
            App.gameWorld.getSceneNodes().getChildren().add(node);
        }
        node.setTranslateX(x + vX);
        node.setTranslateY(y + vY);
        collisionBound.setTranslateX(x + vX);
        collisionBound.setTranslateY(y + vY);
    }

    public static Player init(double height, double width) {
        Player player = new Player(App.gameWorld.getScale(),
                App.gameWorld.getScale(),
                height * App.gameWorld.getScale(),
                width * App.gameWorld.getScale());
        player.height = height;
        player.width = width;
        App.gameWorld.spawn(player);
        return player;
    }

    @Override
    public void executeCollision() {
        // Collide wall
        boolean collideWall = false;
        for(Sprite sprite: App.gameWorld.getSpriteManager().getGameActorsList()) {
            if (sprite instanceof Wall) {
                if (collide((Collision) sprite)) {
                    collideWall = true;
                    break;
                }
            }
        }
        if (collideWall) {
            backStep();
        }

        // Collide explode
        for(Sprite sprite: App.gameWorld.getSpriteManager().getGameActorsList()) {
            if (sprite instanceof Explore) {
                if (collide((Collision)sprite)) {
                    handleDeath();
                    return;
                }
            }
        }

        // Collide bomb
        boolean collideBomb = false;
        for(Sprite sprite: App.gameWorld.getSpriteManager().getGameActorsList()) {
            if (sprite instanceof Bomb) {
                if (collide((Collision)sprite)) {
                    collideBomb = true;
                }
            }
        }
        if (!on_bomb && collideBomb){
            backStep();
        } else if (on_bomb && !collideBomb){
            on_bomb = false;
        }
    }

    public void backStep(){
        node.setTranslateX(node.getTranslateX() - vX);
        node.setTranslateY(node.getTranslateY() - vY);
        collisionBound.setTranslateX(node.getTranslateX() - vX);
        collisionBound.setTranslateY(node.getTranslateY() - vY);
        vX = 0;
        vY = 0;
    }

    @Override
    public void handleDeath() {
        Random random = new Random();
        while (true) {
            int j = Math.abs(random.nextInt()) % App.gameWorld.width;
            int i = Math.abs(random.nextInt()) % App.gameWorld.height;
            if (App.gameWorld.sprite_map[i][j] == ' ') {
                App.gameWorld.sprite_map[i][j] = 'p';
                node.setTranslateX(j * App.gameWorld.getScale());
                node.setTranslateY(i * App.gameWorld.getScale());
                collisionBound.setTranslateX(j * App.gameWorld.getScale());
                collisionBound.setTranslateY(i * App.gameWorld.getScale());
                break;
            }
        }

        lives --;
        if (lives == 0) {
            App.gameWorld.level_next = 1;
//            App.gameWorld.start_level(App.gameWorld.stage);
            App.gameWorld.is_nx_level = true;
        }
    }

    public void storeBomb() {
        if (cool_down_bomb.size() >= power_bomb)
            return;
        if (Bomb.init((int) (node.getTranslateX() / App.gameWorld.getScale() + 0.5f),
                (int) (node.getTranslateY() / App.gameWorld.getScale() + 0.5f),
                this.power_flames) != null) {
            cool_down_bomb.add(new Double(Bomb.DEATH_TIME));
            on_bomb = true;
        }
    }

    public void checkBomb() {
        for (Double d: cool_down_bomb) {
            d.d -= 1.0f/App.gameWorld.getFramesPerSecond();
        }
        cool_down_bomb.removeIf(d -> d.d <= 0);
    }
}
