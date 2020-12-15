package Button;

import Loader.Data;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;

/**
 * default off
 */
public class Music extends Button {
    private static final Image img = Data.get("Music col_Square Button");
    public boolean on = false;
    private final AudioClip ad;

    public Music(AudioClip ad, double x, double y, double w, double h) {
        super(img, x, y, w, h);
        this.ad = ad;

    }

    public void setEffectButton() {
        colorAdjust.setBrightness(0.2);
        this.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            if (!on) {
                this.setEffect(colorAdjust);

                ad.setCycleCount(AudioClip.INDEFINITE);
                ad.play();
                on = true;
            } else {
                this.setEffect(null);
                on = false;
                ad.stop();

            }

        });
    }

    public void set(boolean on) {
        colorAdjust.setBrightness(0.2);
        this.on = on;
        if (on) {
            this.setEffect(colorAdjust);
            ad.setCycleCount(AudioClip.INDEFINITE);
            ad.play();
        } else {
            this.setEffect(null);
            ad.stop();
        }
    }

}
