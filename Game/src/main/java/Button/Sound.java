package Button;

import Loader.Data;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;

import java.util.List;

public class Sound extends Button{
    private static Image img = Data.get("Audio col_Square Button");
    public boolean on = false;
    public void setEffectButton(){
        colorAdjust.setBrightness(0.2);
        this.addEventFilter(MouseEvent.MOUSE_CLICKED, e-> {
            if(!on){
            this.setEffect(colorAdjust);on = true;} else {
                this.setEffect(null);
                on = false;
            }

        });
    }
    public Sound(double x, double y, double w, double h) {
        super(img, x, y, w, h);
    }
    public void set(boolean on){
        colorAdjust.setBrightness(0.2);
        this.on = on;
        if(on){
            this.setEffect(colorAdjust);
        } else {
            this.setEffect(null);
        }
    }
}
