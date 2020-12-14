package Button;

import Application.Game_World;
import Gobject.Bomber;
import Gobject.Gobject;
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
    public boolean sound,music;
    public boolean isEmpty = true;
    public static Image img = Data.get("Continue  col_Button");
    public Map map;
    public void setEffectButton(){   }
    public Continue(Stage stage, double x, double y, double w, double h) {
        super(img, x, y, w, h);
        try
        {
            map = new Map(stage,new ArrayList<>());
            List<File> filesInFolder = Files.walk(Paths.get("Game/src/main/resources/data/"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            for (File f : filesInFolder){
                FileInputStream input = new FileInputStream(f);
                ObjectInputStream o = new ObjectInputStream(input);
                if(f.getName().contains("paimon") && !f.getName().contains("'s bomb")){
                    map.setMain((Bomber) ((Simple_Data)o.readObject()).cvt());
                } else
                map.AddGobject(((Simple_Data)o.readObject()).cvt());
                isEmpty=false;
                o.close();
                input.close();
                System.out.println("getting: "+f.getName().replace(".png",""));

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if(!isEmpty){
            colorAdjust.setBrightness(0.2);
            this.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
                this.setEffect(colorAdjust);

            });
            this.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
                this.setEffect(null);
            });

        }

    }
}
