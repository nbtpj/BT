package Application;

import Loader.Data;
import Loader.Movable_Object_Images;
import Support_Type.Pos;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Support_Type.Map.SIZE_X;
import static Support_Type.Map.SIZE_Y;

public class Choose_Bomberman extends Application {
    public static final int SIZE_CHOOSING = 30;
    private static Map<String, Movable_Object_Images> data = Movable_Object_Images.getData();
    public static Canvas screen = new Canvas(SIZE_X * Pos.SIZE, SIZE_Y * Pos.SIZE);
    public static List<Image> renderList(Canvas cv){
        int i=0,j=0,count =0;
        List<Image> rs = new ArrayList<>();
        for(String key: data.keySet()){
            if(i*SIZE_CHOOSING>=cv.getWidth()){
                i=0;
                j++;
            }
            rs.add(data.get(key).get("front")[0]);
            count++;
           // cv.getGraphicsContext2D().drawImage(data.get(key).get("front")[0],i*SIZE_CHOOSING,j*SIZE_CHOOSING,SIZE_CHOOSING,SIZE_CHOOSING);
            i++;
        }
        return rs;
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Choose Character");
        Group root = new Group();
        Scene theScene = new Scene( root );
        stage.setScene( theScene );
        root.getChildren().add(screen);
        List<Image> ls = renderList(screen);
        EventHandler<KeyEvent> keydown = new EventHandler<KeyEvent>() {
            private int i=0;
            @Override
            public void handle(KeyEvent event) {
                String a = event.getCharacter().toUpperCase();
                if (a.equals(" ")){
                    screen.getGraphicsContext2D().drawImage(Data.getInstance().background, 0, 0,SIZE_X*Pos.SIZE,SIZE_Y*Pos.SIZE);
                    i = Math.abs(i+1)%ls.size();
                        screen.getGraphicsContext2D().drawImage(ls.get(i),0,0,100,100);
                } else {
                    if (a.equals("A")){
                        i= Math.abs(i-1)%ls.size();
                        screen.getGraphicsContext2D().drawImage(Data.getInstance().background, 0, 0,SIZE_X*Pos.SIZE,SIZE_Y*Pos.SIZE);
                        screen.getGraphicsContext2D().drawImage(ls.get(i),0,0,100,100);
                    }
                }
            }
        };
        stage.addEventHandler(KeyEvent.KEY_TYPED,keydown);;
        stage.show();
    }
    public static void maiṇ(String[] args){
        launch(args);
    }
}
