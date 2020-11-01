package manager;
import event.*;
import java.util.*;

import event.Bomb.Explose;
import event.Bomber.Set_Bomb;
import object.*;
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
        List<Event> temp;
        BigMap new_map = new BigMap(time + this.time);
        /* Lấy sự kiện, cập nhập vật thể,  thêm các vật thể mới vào new map*/
        for( G_Obj game_obj : map.data()){
            temp = game_obj.Update(time);
            for (Event e:temp){
                if (e instanceof Explose){
                    new_map.addG_Obj(new Fire(e.pos()));
                } else {
                    if(e instanceof Set_Bomb){
                        new_map.addG_Obj(new Bomb(e.pos()));
                    }
                }
            };
            result_events.addAll(temp);
        }
        /* add các vật thể cũ (còn tồn tại) vào map mới sau update */
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
