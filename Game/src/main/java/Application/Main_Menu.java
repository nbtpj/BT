package Application;

import Loader.Data;
import Support_Type.Pos;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import Button.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import static Support_Type.Map.SIZE_X;
import static Support_Type.Map.SIZE_Y;


public class Main_Menu implements Part_Of_Game{
    public static ColorAdjust colorAdjust = new ColorAdjust();
    public static void setEffectButton(ImageView imageView){
        colorAdjust.setBrightness(0.2);
        imageView.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            imageView.setEffect(colorAdjust);

        });
        imageView.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            imageView.setEffect(null);
        });
    }
    @Override
    public Scene turnOn(Stage stage) throws Exception {
        stage.setTitle("Choose Character");

        Group root = new Group();
        Scene theScene = new Scene(root);
        stage.setScene(theScene);
        Canvas cv = new Canvas(SIZE_X * Pos.SIZE, SIZE_Y * Pos.SIZE);
        root.getChildren().add(cv);
        cv.getGraphicsContext2D().setGlobalAlpha(0.9);
        cv.getGraphicsContext2D().drawImage(Data.getInstance().bkg,0,0,SIZE_X * Pos.SIZE, SIZE_Y * Pos.SIZE);

        root.getChildren().add(new Continue(stage,cv.getWidth()/2-cv.getWidth()/10,cv.getHeight()/5-cv.getHeight()/16
                ,cv.getWidth()/5,cv.getHeight()/8));
        root.getChildren().add(new NewGame(stage,cv.getWidth()/2-cv.getWidth()/10,cv.getHeight()*2/5-cv.getHeight()/16
                ,cv.getWidth()/5,cv.getHeight()/8));

        root.getChildren().add(new Quit(stage,cv.getWidth()/2-cv.getWidth()/10,cv.getHeight()*3/5-cv.getHeight()/16
                ,cv.getWidth()/5,cv.getHeight()/8));


        stage.show();
        return theScene;
    }
}
