package Button;

import Loader.Data;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class Sound extends Button {
    private static final Image img = Data.get("Audio col_Square Button");
    public boolean on = false;

    public Sound(double x, double y, double w, double h) {
        super(img, x, y, w, h);
    }

    public void setEffectButton() {
        colorAdjust.setBrightness(0.2);
        this.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            if (!on) {
                this.setEffect(colorAdjust);
                on = true;
            } else {
                this.setEffect(null);
                on = false;
            }

        });
    }

    public void set(boolean on) {
        colorAdjust.setBrightness(0.2);
        this.on = on;
        if (on) {
            this.setEffect(colorAdjust);
        } else {
            this.setEffect(null);
        }
    }
}
