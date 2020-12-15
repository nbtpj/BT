package Application;

import Loader.Data;
import Loader.Movable_Object_Images;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage theStage) throws Exception {

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);
        theStage.setTitle("Wibu's World");
        Data.getInstance();
        Movable_Object_Images.getData();
        (new Main_Menu()).turnOn(theStage);
        theStage.show();
    }
}
