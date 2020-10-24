package object;
import javafx.scene.Node;

import java.util.*;

abstract class Sprite extends Node {
    /* thời điểm của vật thể */
    final public double time_index(){
        return this.current_map.time_index;
    }
    static final int SIZE_OF_EACH = 5;
    /* vị trí ô trên bản đồ */
    protected int map_x(){ return(int)Math.round(x/5);}
    protected int map_y(){ return(int)Math.round(y/5);}
    /* bản đồ toàn bộ */
    protected BigMap current_map;
    /* vị trí, vận tốc vật thể */
    protected double x,y,Vx,Vy;
    /* vật thể còn tồn tại hay không */
    protected boolean _Use;
    /**
     * cập nhập thông số của vật thể, trả về một chuổi sự kiện mà vật thể tạo ra sau đó t(s)
     * @param time là một khoảng thởi gian rất nhỏ ( 1s/ FPS )
     * @return một chuổi các sự kiện
     */
    abstract public Set<Event> Update(double time);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sprite)) return false;
        Sprite sprite = (Sprite) o;
        return Double.compare(sprite.x, x) == 0 &&
                Double.compare(sprite.y, y) == 0 &&
                Double.compare(sprite.Vx, Vx) == 0 &&
                Double.compare(sprite.Vy, Vy) == 0 ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, Vx, Vy);
    }
}
