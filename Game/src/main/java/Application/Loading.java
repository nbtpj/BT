package Application;

import Loader.Data;
import Loader.Movable_Object_Images;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Loading implements Part_Of_Game{
    static private Image img = null;
    @Override
    public Scene turnOn(Stage stage) throws Exception {
        if(img==null){
            img = new Image(new FileInputStream(Data.localFilePath+"loading.gif"));
        }
        stage.close();
        stage.setTitle("loading");
        Group root = new Group();
        Scene rs = new Scene(root);
        Canvas cv = new Canvas();
        stage.setScene(rs);
        root.getChildren().add(cv);
        cv.getGraphicsContext2D().drawImage(img,0,0,stage.getWidth(),stage.getHeight());
        stage.show();
        Data.getInstance();
        Movable_Object_Images.getData();
        stage.close();
        return rs;
    }
}
