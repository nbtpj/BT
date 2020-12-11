package Application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.scene.canvas.Canvas;

public class test_multiple_canvas extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene theScene = new Scene( root );
        stage.setScene( theScene );
        Canvas a= new Canvas(600,600), b= new Canvas(200,200);
        root.getChildren().add(a);root.getChildren().add(b);
//        a.getGraphicsContext2D().setGlobalAlpha(0.5);
        a.getGraphicsContext2D().setFill(Color.GREEN);
        a.getGraphicsContext2D().fillOval(100,100,50,50);
 //       a.toFront();
        b.getGraphicsContext2D().fillRect(50,50,70,70);
        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
