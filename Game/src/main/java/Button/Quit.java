package Button;

import Loader.Data;
import Support_Type.Map;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Quit extends Button{
    public static Image img = Data.get("Quit  col_Button");
    public Quit(Stage stage, double x, double y, double w, double h) {
        super(img, x, y, w, h);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                stage.close();
            }
        });
    }
    public Quit(Map map,Stage stage, double x, double y, double w, double h) {
        super(img, x, y, w, h);

        this.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
              /*  try{
                    List<File> filesInFolder = Files.walk(Paths.get("Game/src/main/resources/data/"))
                            .filter(Files::isRegularFile)
                            .map(Path::toFile)
                            .collect(Collectors.toList());
                    for (File f : filesInFolder) f.delete();}
                catch (Exception E){

                }*/
                map.save();
                stage.close();
            }
        });
    }
}
