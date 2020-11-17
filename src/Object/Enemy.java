package Object;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.util.List;
import static data.Data.link_bomberman;


public class Enemy extends Movable_Object{
    double V_x = 2, V_y = 2;
     String link_to_front = link_bomberman + "front_",
            link_to_back=link_bomberman + "back_",
            link_to_left=link_bomberman + "left_",
            link_to_right=link_bomberman + "right_";
     int n_front=4, n_back=4, n_left=4,n_right=4;
    int W = Pos.SIZE, H = Pos.SIZE * 2;
    public Enemy(String name, double x, double y) throws Exception {
        super(name, x, y);
        int i;
        back = new Image[n_back];
        for (i = 0; i < n_back; i++)
            back[i] = new Image(new FileInputStream(link_to_back  + i + ".png"), Pos.SIZE, Pos.SIZE, false, false);
        left = new Image[n_left];
        for (i = 0; i < n_left; i++)
            left[i] = new Image(new FileInputStream(link_to_left  + i + ".png"), Pos.SIZE, Pos.SIZE, false, false);
        front = new Image[n_front];
        for (i = 0; i < n_front; i++)
            front[i] = new Image(new FileInputStream(link_to_front  + i + ".png"), Pos.SIZE, Pos.SIZE, false, false);
        right = new Image[n_right];
        for (i = 0; i < n_right; i++)
            right[i] = new Image(new FileInputStream(link_to_right  + i + ".png"), Pos.SIZE, Pos.SIZE, false, false);
        current_frame = 0;
        current_Image = front[0];
        current_frames = front;
    }

    @Override
    public List<Object> update(double t){


        if (heal_index <= 0) {
            InUse = false;
            return null;
        }
        List<Object> L = current_map.get(this.cell());
        boolean stop = false;
        for(Object o: L){
            if(o instanceof Bomber){
                o.heal_index-=2;
                System.out.println(o.heal_index);
                move_2.clear();
                stop = true;
            }
        }
        if(stop) return null;
        if(move_2.size()<2){
            int a =(int)Math.round(4*Math.random());
            switch (a){
                case(0):
                    move_2.add("right");
                    move_2.add("right");
                    move_2.add("right");
                    move_2.add("right");
                    break;
                case(1):
                    move_2.add("back");
                    move_2.add("back");
                    move_2.add("back");
                    move_2.add("back");
                    break;
                case(2):
                    move_2.add("front");
                    move_2.add("front");
                    move_2.add("front");
                    move_2.add("front");
                    break;
                default:
                    move_2.add("left");
                    move_2.add("left");
                    move_2.add("left");
                    move_2.add("left");
                    break;
            }
        }
        /** if this object is moving */
        if (current_frame < current_frames.length && !move_2.isEmpty()) {
            current_frame++;
            double new_x = x, new_y = y;
          //  System.out.println(move_2);
            switch (move_2.get(0)) {
                case "front":
                    current_frames = front;
                    new_y+= V_y;
                    break;
                case "back":
                    current_frames = back;
                    new_y-= V_y;
                    break;
                case "left":
                    current_frames = left;
                    new_x-= V_x;
                    break;
                case "right":

                    current_frames = right;
                    new_x+= V_x;
                    break;
            }
            if(current_frame==3) {
                move_2.remove(0);
                //   if(move_2.isEmpty()) move_2 = new ArrayList<>();
                if(!move_2.isEmpty()) current_frame = -1;
            }
            Pos new_pos = new Pos(new_x, new_y);
            if(this.cell().equals(new_pos) ){
                x = new_x;
                y = new_y;
                return null;
            }
            if (!this.current_map.Check(new_pos).equals("Invalid")) {
                x = new_x;
                y = new_y;

            }

        }
        return null;
    }

}
