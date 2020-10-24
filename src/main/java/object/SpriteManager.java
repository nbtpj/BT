package object;

import java.util.*;

public class SpriteManager {
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
        for( Sprite game_obj : map.data()){
            result_events.addAll(game_obj.Update(time));
        }
        for( Sprite game_obj : map.data()){
            if (game_obj._Use){
                new_map.addSprite(game_obj);
            } else {
                Garbage = true;
            }
        }
        if (Garbage){
            System.gc();
        }
        events = (Set<Event>) result_events;
        map = new_map;
    }
}
