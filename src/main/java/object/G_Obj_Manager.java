package object;
import event.*;
import java.util.*;

/**
 * lớp quản lí
 * các game object (G_Obj) sẽ KHÔNG BỊ COPY
 * các map sẽ BỊ CẬP NHẬP (XÓA VÀ TẠO MỚI) LIÊN TỤC
 */
public class G_Obj_Manager {
    /* thời gian */
    public double time;
    /* tât cả các sự kiện đang diễn ra trong bản đồ */
    public Set<Event> events = new HashSet<>();
    /* bản đồ lớn */
    public BigMap map;
    /**
     * cập nhập lại toàn bộ sau 1 khoảng thời gian rất nhỏ t(s)
     * @param time là một khoảng thởi gian rất nhỏ ( 1s/ FPS )
     */
    public void update(double time){
        boolean Garbage = false;
        List<Event> result_events = new ArrayList<>();
        BigMap new_map = new BigMap(time + this.time);
        /* Lấy sự kiện và cập nhập vật thể */
        for( G_Obj game_obj : map.data()){
            result_events.addAll(game_obj.Update(time));
        }
        /* tạo một map mới tại thời điểm mới */
        for( G_Obj game_obj : map.data()){
            if (game_obj._Use){
                new_map.addG_Obj(game_obj);
            } else {
                Garbage = true;
            }
        }
        /* cập nhập lại toàn bộ map cho vật thể */
        for( G_Obj game_obj: new_map.data()){
            game_obj.setCurrent_map(new_map);
        }

        if (Garbage){
            System.gc();
        }
        events = (Set<Event>) result_events;
        map = new_map;
    }
}
