package Button;

import Application.Choose_Bomberman;
import Loader.Data;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class NewGame extends Button {
    public static Image img = Data.get("New Game  col_Button");

    public NewGame(Stage stage, double x, double y, double w, double h) {
        super(img,x, y, w, h);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try{
                    List<File> filesInFolder = Files.walk(Paths.get("Game/src/main/resources/data/"))
                            .filter(Files::isRegularFile)
                            .map(Path::toFile)
                            .collect(Collectors.toList());
                    for (File f : filesInFolder) f.delete();}
                catch (Exception E){}
                stage.close();
                try {
                    (new Choose_Bomberman()).turnOn(stage);
                } catch (Exception exception) {

                }
            }
        });
    }

}
