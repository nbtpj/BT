package event;

import java.io.FileNotFoundException;
import java.util.Objects;
import object.*;

/**
 * các sự kiện xảy ra - xử lí âm thanh, hình ảnh (được sinh ra sau khi đã xử lí các thông số  trên đối tượng chính)
 * nên nhó rằng các event nay là rời rạc và không có mối liên hệ gì vói nhau khi chạy chương trình. chúng đơn thuần chỉ
 * làm nhiệm vụ hiển thị, sau đó bị hủy, rồi sự kiện mới được sinh ra. còn lõi của chúng luông luôn phải do G_Obj quản lí.
 * (đại khái là chỉ tỏa sáng 1 lần)
 */
public abstract class Event {
    /* tên định danh sự kiện theo G_Obj (tùy, viết cũng được, không cũng không sao) */
    protected String name;
    /* link tới file chứa âm thanh, hình ảnh,... phục vụ sự kiện */
    protected String link_to_data_file;
    /* vị trí xảy ra sự kiện (đang suy nghĩ xem có nên để Pos để quản lí theo ô hay không) */
    protected double x,y;
    /* khởi tạo sự kiện tại 1 vị trí, lưu ý vị trí Luôn nằm ở giữa 1 ô */
    public Event(double x,double y,String name){
        this.x=(double) Math.round(x / G_Obj.SIZE_OF_EACH) * G_Obj.SIZE_OF_EACH + (double) G_Obj.SIZE_OF_EACH / 2;
        this.y=(double) Math.round(y / G_Obj.SIZE_OF_EACH) * G_Obj.SIZE_OF_EACH + (double) G_Obj.SIZE_OF_EACH / 2;
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
