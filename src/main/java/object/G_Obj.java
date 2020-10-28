package object;
import javafx.scene.Node;

import java.util.*;
import event.*;
abstract public class G_Obj{

    static final int SIZE_OF_EACH = 5;

    /* vị trí, vận tốc vật thể */
    protected double x,y,Vx,Vy;
    /* vật thể còn tồn tại hay không */
    public boolean _Use;
    /* vị trí ô trên bản đồ */
    protected int map_x(){ return(int)Math.round(x/SIZE_OF_EACH);}
    protected int map_y(){ return(int)Math.round(y/SIZE_OF_EACH);}
    /* bản đồ toàn bộ */
    protected BigMap current_map;

    public G_Obj(double x, double y, BigMap current_map) {
        this.x = x;
        this.y = y;
        _Use = true;
        Vx = 0;
        Vy = 0;
        this.current_map = current_map;
    }

    /* update lại toàn bộ bản đồ */
    public void setCurrent_map(BigMap new_map){
        this.current_map = new_map;
    }
    /* thời điểm của vật thể */
    public double time_index(){
        return this.current_map.time_index;
    }

    /**
     * cập nhập thông số của vật thể, trả về một chuổi sự kiện mà vật thể tạo ra sau đó t(s)
     * @param time là một khoảng thởi gian rất nhỏ ( 1s/ FPS )
     * @return một chuổi các sự kiện
     */
    abstract public Set<Event> Update(double time);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof G_Obj)) return false;
        G_Obj sprite = (G_Obj) o;
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
