package Button;

import Loader.Data;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class NewGame extends Button {
    public static Image img = Data.get("New Game  col_Button");

    public NewGame(Stage stage, double x, double y, double w, double h) {
        super(img, x, y, w, h);
    }

}
