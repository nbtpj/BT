package Button;

import Loader.Data;
import javafx.scene.image.Image;

public class Resume extends Button {
    public static Image img = Data.get("Resume  col_Button");

    public Resume(double x, double y, double w, double h) {
        super(img, x, y, w, h);
    }
}
