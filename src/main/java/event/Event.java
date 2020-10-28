package event;

import java.io.FileNotFoundException;
import java.util.Objects;
import object.*;

/**
 * các sự kiện xảy ra - xử lí âm thanh, hình ảnh (được sinh ra sau khi đã xử lí các thông số  trên đối tượng chính)
 */
public abstract class Event {
    /* tên định danh sự kiện theo G_Obj (tùy, viết cũng được, không cũng không sao) */
    protected String name;
    /* link tới file chứa âm thanh, hình ảnh,... phục vụ sự kiện */
    protected String link_to_data_file;
    /* vị trí xảy ra sự kiện (đang suy nghĩ xem có nên để Pos để quản lí theo ô hay không) */
    protected double x,y;
    /* khởi tạo sự kiện tại 1 vị trí, lưu ý vị trí này không phải là 1 ô trên bản đồ */
    public Event(double x,double y,String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }
    public abstract void render() throws FileNotFoundException;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return Double.compare(event.x, x) == 0 &&
                Double.compare(event.y, y) == 0 &&
                name.equals(event.name) &&
                link_to_data_file.equals(event.link_to_data_file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, link_to_data_file, x, y);
    }
}
