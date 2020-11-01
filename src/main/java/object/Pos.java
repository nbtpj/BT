package object;

import java.util.Objects;

import static object.G_Obj.SIZE_OF_EACH;

/**
 * một kiểu dữ liệu điểm, để quản lí theo ô trên màn hình
 */
public class Pos {
    public int x,y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pos)) return false;
        Pos pos = (Pos) o;
        return x == pos.x &&
                y == pos.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Pos(double x, double y){
        this.x = (int)Math.round(x/SIZE_OF_EACH);
        this.y = (int)Math.round(y/SIZE_OF_EACH);
    }

}
