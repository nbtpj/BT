package object;
import javafx.scene.Node;

import java.util.*;

abstract class Sprite extends Node {
    /* vị trí, vận tốc vật thể */
    protected double x,y,Vx,Vy;
    /* danh sách các vật thể mà vật thể này có thể tác động */
    protected Set<Sprite> near_by = new HashSet<>();
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
                Double.compare(sprite.Vy, Vy) == 0 &&
                near_by.equals(sprite.near_by);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, Vx, Vy, near_by);
    }
}
