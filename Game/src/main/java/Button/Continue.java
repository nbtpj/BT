package Button;

import Application.Game_World;
import Gobject.Gobject;
import Loader.Data;
import Support_Type.Map;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class Continue extends Button{
    public static Image img = Data.get("Continue  col_Button");
    private Map map;
    public Continue(Stage stage, double x, double y, double w, double h) {
        super(img, x, y, w, h);
        try
        {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream(Data.localFilePath+"data.bin"));
           // List<Gobject> data =(ArrayList<Gobject>) o.readObject();
            map = new Map(stage);
            o.close();
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
