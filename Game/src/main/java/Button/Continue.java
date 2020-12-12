package Button;

import Application.Game_World;
import Gobject.Bomber;
import Gobject.Simple_Data;
import Loader.Data;
import Support_Type.Map;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Continue extends Button{
    public static Image img = Data.get("Continue  col_Button");
    private Map map;
    public Continue(Stage stage, double x, double y, double w, double h) {
        super(img, x, y, w, h);
        try
        {
            map = new Map(stage);
            List<File> filesInFolder = Files.walk(Paths.get("Game/src/main/resources/data/"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            for (File f : filesInFolder){
                ObjectInputStream o = new ObjectInputStream(new FileInputStream(f));
                if(f.getName().contains("paimon")){
                    map.setMain((Bomber) ((Simple_Data)o.readObject()).cvt());
                } else
                map.AddGobject(((Simple_Data)o.readObject()).cvt());
                o.close();
                System.out.println("getting: "+f.getName().replace(".png",""));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        this.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                stage.close();
                try {
                    (new Game_World(map)).turnOn(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }
}
