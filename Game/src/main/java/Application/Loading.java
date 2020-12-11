package Application;

import Loader.Data;
import Loader.Movable_Object_Images;
import Support_Type.Pos;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;

import static Support_Type.Map.SIZE_X;
import static Support_Type.Map.SIZE_Y;

public class Loading extends Application implements Part_Of_Game {
    static private Image img = null;
    @Override
    public Scene turnOn(Stage stage) throws Exception {
     /* ;*/
        Data.getInstance();
        Movable_Object_Images.getData();
        stage.close();
        return null;
    }

    @Override
    public void start(Stage stage) throws Exception {

        if(img==null){
            try{
                img = new Image(new FileInputStream(Data.localFilePath+"loading.gif"));}
            catch(Exception e){
                System.err.println(e.getMessage());
            }
        }
        stage.setTitle("loading");
        ImageView x= new ImageView(img);
        x.setX(0);
        x.setY(0);
        x.setFitWidth(SIZE_X * Pos.SIZE);
        x.setFitHeight(SIZE_Y * Pos.SIZE);
        Canvas cv = new Canvas();
        cv.getGraphicsContext2D().setFill(Color.BLACK);
        Group root = new Group();
        Scene rs = new Scene(root);
        stage.setScene(rs);
        root.getChildren().add(cv);
        root.getChildren().add(x);
        stage.show();
        Data.getInstance();
        Movable_Object_Images.getData();
        stage.close();
    }
    public static void main(String[] args){
        launch(args);
    }
}
