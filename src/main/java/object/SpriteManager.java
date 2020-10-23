package object;

import java.util.*;

public class SpriteManager {
    /* tất cả vật thể đang trong bản đồ */
    public Set<Sprite> map = new HashSet<>();
    /* tât cả các sự kiện đang diễn ra trong bản đồ */
    public Set<Event> events = new HashSet<>();

    /**
     * cập nhập lại toàn bộ sau 1 khoảng thời gian rất nhỏ t(s)
     * @param time là một khoảng thởi gian rất nhỏ ( 1s/ FPS )
     */
    public void update(double time){
        boolean Garbage = false;
        List<Event> result_events = new ArrayList<>();
        List<Sprite> result_map = new ArrayList<>();
        for( Sprite game_obj : map){
            result_events.addAll(game_obj.Update(time));
        }
        for( Sprite game_obj : map){
            if (game_obj._Use){
                result_map.add(game_obj);
            } else {
                Garbage = true;
            }
        }
        if (Garbage){
            System.gc();
        }
        events = (Set<Event>) result_events;
        map = (Set<Sprite>) result_map;
    }
}
