package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import object.*;

import java.io.FileInputStream;
import java.io.InputStream;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
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
    }


    public static void main(String[] args) {
        launch(args);
        //System.out.println("hello world");

    }
}
