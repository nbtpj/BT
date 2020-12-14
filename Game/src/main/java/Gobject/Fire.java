package Gobject;

import Loader.Data;
import Support_Type.Pos;
import javafx.scene.image.Image;

import java.util.List;

public class Fire extends Gobject {
    private Image[] frame;
    private int current_frame;
    public Fire( double x, double y) {
        super(1, "fire", x, y);
        frame = Data.getInstance().fire;
        current_frame =-1;

    }

    public Fire( Pos pos) {
        super(1, "fire", pos);
        frame = Data.getInstance().fire;
        current_frame =-1;
    }

    public Fire(Simple_Data data) {
        super(data);
        frame = Data.getInstance().fire;
        current_frame =-1;
    }

    @Override
    public List<Gobject> update(double t) {


        if(index<=0) using = false;
        index-=t;
        for(Gobject o: current_map.get(pos())){
            if(!(o instanceof Fire|| o instanceof Effect)){
                if (o.invincible<=0)
                    o.index-=0.3;
            }
            if(o instanceof Bomb){
                o.index =0;
            }
        }
        current_frame = (current_frame+1)% frame.length;
       /* for(Gobject o : current_map.get(this.pos())){
            if(o instanceof Movable_Object){
                o.index -=10;
            }
            if(o instanceof Bomb){
                o.index =0;
            }
        }*/
        return null;
    }

    @Override
    public Image render() {
        current_frame = (current_frame+1)%frame.length;
        return frame[current_frame];
    }
}