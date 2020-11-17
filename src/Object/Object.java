package Object;

import javafx.scene.Node;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Objects;

/** each Game Object will has many same features below */
public abstract class Object extends Node {
    static public final double FPS = 60, dt = 1/FPS;
    protected Image current_Image = null;
    protected Map current_map=null;
    public String name="default";
    public double heal_index = 1000;
    protected double time_index=0;
    public boolean InUse = true;
    public double x=0,y=0;
    public Pos cell(){
        return new Pos(x,y);
    } ;
    public Object(String name,double x, double y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Object)) return false;
        Object object = o;
        return heal_index == object.heal_index &&
                Double.compare(object.time_index, time_index) == 0 &&
                InUse == object.InUse &&
                Double.compare(object.x, x) == 0 &&
                Double.compare(object.y, y) == 0 &&
                current_Image.equals(object.current_Image) &&
                current_map.equals(object.current_map) &&
                name.equals(object.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(current_Image, current_map, name, heal_index, time_index, InUse, x, y);
    }

    public Object(String name, Pos X){
        this.name = name;
        this.x = X.x*Pos.SIZE;
        this.y = X.y*Pos.SIZE;
        System.out.println("init_"+name);
    }
    /** predict what this object will do in the next t second(s) so change environment 's features ( if necessary)
     * @return*/
    abstract public List<Object> update(double t) throws Exception;
    /** get the next Image, and store it into current_Image, too */
    abstract public Image render() throws Exception;
}
