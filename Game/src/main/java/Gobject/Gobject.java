package Gobject;

import Support_Type.Map;
import Support_Type.Pos;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Objects;

public abstract class Gobject {
    public Map current_map;
    public double index;
    public boolean using = true;

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

    public final String name;// name of game object
    public double x,y;// current position
    public double v_x,v_y; // current velocity
    public Pos pos(){
        return new Pos(x,y);
    }

    public Gobject(double index, String name, double x, double y) {
        this.index = index;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public Gobject(double index, String name, Pos pos) {
        this.index = index;
        this.name = name;
        this.x = pos.x*Pos.SIZE;
        this.y = pos.y*Pos.SIZE;
    }

    abstract public List<Gobject> update(double t);
    abstract public Image render();
}
