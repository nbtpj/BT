package object;

import event.Bomber.*;
import event.Event;
import user_control.Commands;

import java.util.ArrayList;
import java.util.List;

/**
 * bomber object (demo)
 */
public class Bomber extends G_Obj {
    public String name;
    public int heal;
    /**
     * đây là dãy lệnh đưa vào theo format sau
     * control.getDirection()= hướng di chuyển (none|left|right|top|bottom)
     * control.isSet_bomb()= đặt boom (true|false)
     * nhân vật luôn luôn phải nhận được lệnh điều khiển trước mỗi khung hình
     * default : direction = none, set_bomb = false ( sẽ đwọc tự động trả về sau khi update t)
     */
    private Commands control;

    public Bomber(double x, double y, BigMap current_map, String name) {
        super(x, y, current_map);
        this.name = name;
        this.heal = 1000;
    }

    void setControl(Commands cmd) {
        this.control = cmd;
    }


    public Event move(String type) {
        switch (type) {
            case ("top"):
                Vx = 0;
                Vy = -1;
                break;
            case ("bottom"):
                Vx = 0;
                Vy = 1;
                break;
            case ("left"):
                Vx = -1;
                Vy = 0;
                break;
            case ("right"):
                Vx = 1;
                Vy = 0;
                break;
            default:
                Vx = 0;
                Vy = 0;
                return new Stay(x, y, name, heal);
        }
        return new Move(x, y, name, heal, type);
    }

    /**
     * bắt đầu duyệt theo thứ tự: bị động (chết, mất máu) -> chủ động (đặt bom, di chuyển)
     *
     * @param t là một khoảng thởi gian rất nhỏ ( 1s/ FPS )
     * @return
     */

    public List<Event> Update(double t) {
        List<Event> new_event = new ArrayList<>();
        /** all the actions below happen in current time */
        /* is he alive?*/
        if (heal <= 0) {
            new_event.add(new Dead(x, y, name, heal));
            return new_event;
        }
        /* is he being attacked?*/
        int x_t = this.map_x();
        int y_t = this.map_y();
        List<G_Obj> current_cell = current_map.get_G_Obj(x_t, y_t);
        int heal_lost = BigMap.lost_heal(current_cell);
        if (heal_lost > 0) {
            heal -= heal_lost;
            new_event.add(new Lost_Heal(x, y, name, heal));
        }
        /* is he setting bomb?*/
        if (this.control.isSet_bomb()) {
            new_event.add(new Set_Bomb(x, y, name, heal));
        }
        /** all the actions below happen in the next t second(s)
         /* is he moving?*/
        Event move = this.move(this.control.getDirection());
        /** update his position in the next t second(s)
         * chỗ này hơi củ chuối nên t sẽ giải thích
         * đại khái cái hàm này chỉ check xem có thể đi đến ô mà player điều kiển có thể
         * hay không (đối tượng cản ở đây chỉ là tường,bomb không chỉ chuyển đc, à còn rìa màn hình nữa, mà kệ tính sau),
         * chứ không phải xét xem trong
         * đó sẽ có những cái gì đến trong t giây tới (ý là như mấy con enemy chẳng hạn)
         * */
        if (move instanceof Move) {
            Pos new_pos = new Pos(x + t * Vx, y + t * Vy);
            boolean valid = true;
            if (this.pos().equals(new_pos) == false) {
                valid = this.current_map.valid(new_pos)=="Safe"||this.current_map.valid(new_pos)=="Dangerous";
            }
            if (valid) {
                x += t * Vx;
                y += t * Vy;
            } else {
                move = null;
            }
        }
        if (move != null) new_event.add(move);


        /* renew control */
        this.control = new Commands("none", false);
        return new_event;
    }
}
