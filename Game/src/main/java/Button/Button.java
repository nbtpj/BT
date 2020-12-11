package Button;


import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public abstract class Button extends ImageView{
    public static ColorAdjust colorAdjust = new ColorAdjust();
    public void setEffectButton(){
        colorAdjust.setBrightness(0.2);
        this.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            this.setEffect(colorAdjust);

        });
        this.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            this.setEffect(null);
        });
    }
    protected Button(Image img,double x,double y, double w, double h){
        super(img);
        this.setFitWidth(w);
        this.setFitHeight(h);
        this.setX(x);
        this.setY(y);
        this.setPreserveRatio(true);
        this.setSmooth(true);
        this.setCache(true);
        this.setEffectButton();
    }
    
}
