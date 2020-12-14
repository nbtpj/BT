package Gobject;

import Support_Type.Map;
import Support_Type.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public abstract class Gobject implements Serializable {
    public Map current_map;
    public double index,maxIndex;
    public boolean using = true;
    protected String type;
    public double width=Pos.SIZE,height=Pos.SIZE;
    public double invisible = 0,max_invisible_time;
    public double invincible = 0,max_invincible_time;
    public double poisonous =0,max_poisonous_time;
    public double rage =0,max_rage_time ;
    public double healing=0,max_healing_time;
    public final String name;// name of game object
    public double x,y;// current position
    public double v_x,v_y,default_vx,default_vy; // current velocity
    

    public Gobject(Simple_Data data) {
        this.healing = data.healing;
        this.max_healing_time = data.max_healing_time;
        this.max_invincible_time=data.max_invincible_time;
        this.max_invisible_time =data.max_invisible_time;
        this.max_poisonous_time=data.max_poisonous_time;
        this.max_rage_time =data.max_rage_time;
        this.index = data.index;
        this.using = data.using;
        this.type = data.type;
        this.width = data.width;
        this.height = data.height;
        this.name = data.name;
        this.maxIndex = data.maxIndex;
        this.x = data.x;
        this.y = data.y;
        this.v_x = data.v_x;
        this.v_y = data.v_y;
        this.default_vx = data.default_vx;
        this.default_vy = data.default_vy;
        this.invincible = data.invincible;
        this.invisible = data.invisible;
        this.poisonous = data.poisonous;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gobject)) return false;
        Gobject gobject = (Gobject) o;
        return Double.compare(gobject.index, index) == 0 &&
                Double.compare(gobject.x, x) == 0 &&
                Double.compare(gobject.y, y) == 0 &&
                Double.compare(gobject.v_x, v_x) == 0 &&
                Double.compare(gobject.v_y, v_y) == 0 &&
                Objects.equals(name, gobject.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, name, x, y, v_x, v_y);
    }

    
    public Pos pos(){
        return new Pos(x,y);
    }

    public Gobject(double index, String name, double x, double y) {
        this.index = index;
        maxIndex = index;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public Gobject(double index, String name, Pos pos) {
        this.index = index;
        maxIndex = index;
        this.name = name;
        this.x = pos.x*Pos.SIZE;
        this.y = pos.y*Pos.SIZE;
    }

    abstract public List<Gobject> update(double t);
    abstract public Image render();
    public void drawIndexBar(GraphicsContext gc){
        gc.setFill(Color.GREEN);
        gc.fillRect(x,y-0.8*height,width*index/maxIndex,height*0.15);
        if(rage>0){
            gc.setFill(Color.ORANGE);
            gc.fillRect(x,y-0.6*height,width*rage/max_rage_time,height*0.15);
        }
        if(invincible>0){
            gc.setFill(Color.YELLOWGREEN);
            gc.fillRect(x,y-0.4*height,width*invincible/max_invincible_time,height*0.15);
        }
        if(invisible>0){
            gc.setFill(Color.DARKCYAN);
            gc.fillRect(x,y-0.2*height,width*invisible/max_invisible_time,height*0.15);
        }
        if(poisonous>0){
            gc.setFill(Color.RED);
            gc.fillRect(x,y-height,width*(1-poisonous/max_poisonous_time),height*0.15);
        }
    }

   // abstract public String toJson();
}
