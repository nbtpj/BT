package Button;

import Application.Choose_Bomberman;
import Loader.Data;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main_Menu extends Button {
    public static Image img = Data.get("Menu  col_Button");
    public Main_Menu(Stage stage, double x, double y, double w, double h) {
        super(img, x, y, w, h);

    }
}
