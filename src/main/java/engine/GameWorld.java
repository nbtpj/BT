package engine;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class GameWorld {

//#####################################################################################################################
// declare variables
    private static Timeline gameLoop;
    private final int framesPerSecond;
    private final String windowTile;

    private final SpriteManager spriteManager = new SpriteManager();
    private final SoundManager soundManager = new SoundManager(3);
    private final ImageClass imageClass = new ImageClass();

    private Scene gameSurface;
    private Group sceneNodes;

//#####################################################################################################################
// construct
    public GameWorld(final int fps, final String title) {
        framesPerSecond = fps;
        windowTile = title;

        buildAndSetGameLoop();
    }

//#####################################################################################################################
// getter and setter
    protected static Timeline getGameLoop() {
        return gameLoop;
    }
    protected int getFramesPerSecond() {
        return framesPerSecond;
    }
    protected String getWindowTile() {
        return windowTile;
    }
    public SoundManager getSoundManager() {
        return soundManager;
    }
    public SpriteManager getSpriteManager() {
        return spriteManager;
    }
    public ImageClass getImageClass() {
        return imageClass;
    }
    public Scene getGameSurface() {
        return gameSurface;
    }
    public Group getSceneNodes() {
        return sceneNodes;
    }

    protected static void setGameLoop(Timeline gameLoop) {
        GameWorld.gameLoop = gameLoop;
    }
    protected void setGameSurface(Scene gameSurface) {
        this.gameSurface = gameSurface;
    }
    public void setSceneNodes(Group sceneNodes) {
        this.sceneNodes = sceneNodes;
    }

//#####################################################################################################################
// abstract class
    public abstract void initialize(final Stage primaryStage);

//#####################################################################################################################
// internal method
    protected final void buildAndSetGameLoop() {
        final Duration oneFrameAmt = Duration.millis(1000 / (float) getFramesPerSecond());
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt, actionEvent -> {
                    updateSprites();
                    checkCollisions();
                    cleanupSprites();
                });

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(oneFrame);

        setGameLoop(timeline);
    }

    protected void updateSprites() {
        for (Sprite sprite : spriteManager.getGameActorsList()) {
            handleUpdate(sprite);
        }
    }

    protected void handleUpdate(Sprite sprite) {
    }

    protected void checkCollisions() {
        spriteManager.resetCollisionList();

        for (Sprite spriteA : spriteManager.getCollisionList()) {
            for (Sprite spriteB : spriteManager.getGameActorsList()) {
                if (handleCollision(spriteA, spriteB)) {
                    break;
                }
            }
        }
    }

    protected boolean handleCollision(Sprite spriteA, Sprite spriteB) {
        return false;
    }

    protected void cleanupSprites() {
        spriteManager.cleanupSprites();
    }
//#####################################################################################################################
// global method
    public void beginGameLoop() {
        getGameLoop().play();
    }

    public void shutdown() {
        getGameLoop().stop();
        getSoundManager().shutdown();
    }
}
