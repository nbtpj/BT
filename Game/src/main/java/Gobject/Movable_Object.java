package Gobject;

import Support_Type.Pos;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class Movable_Object extends Gobject {
    protected int n_front, n_back, n_left, n_right;
    protected int W = Pos.SIZE, H = Pos.SIZE * 2;
    protected Map<String, Image[]> frames;
    protected Image[] current_frames;
    protected int current_frame;
    protected double V_x, V_y;
    List<String> move_2 = new ArrayList<>();
    List<Pos> list_pos = new ArrayList<Pos>();

    public Movable_Object(double index, String name, double x, double y) {
        super(index, name, x, y);
        frames = new HashMap<>();
        current_frame = -1;

    }

    public Movable_Object(int i, String name, Pos pos) {
        super(i, name, pos);
        frames = new HashMap<>();
        current_frame = -1;
    }

    /**
     * the object moves according to the value in the move_2 components (in order)
     * after this step, current frame will be updated (and move_2, frames)
     */
    public void move() {
        if (current_frame < current_frames.length && !move_2.isEmpty()) {
            current_frame++;
            double new_x = x, new_y = y;
            current_frames = frames.get(move_2.get(0));
            switch (move_2.get(0)) {
                case "down":
                    new_y += V_y;
                    break;
                case "up":
                    new_y -= V_y;
                    break;
                case "left":
                    new_x -= V_x;
                    break;
                case "right":
                    new_x += V_x;
                    break;
            }
            if (current_frame == current_frames.length) {
                move_2.remove(0);
                //   if(move_2.isEmpty()) move_2 = new ArrayList<>();
                if (!move_2.isEmpty()) current_frame = -1;
            }
            Pos new_pos = new Pos(new_x, new_y);
            if (this.pos().equals(new_pos)) {
                x = new_x;
                y = new_y;
            }
            if (!this.current_map.Check(new_pos).equals("Invalid")) {
                x = new_x;
                y = new_y;
            }
        }
    }


    /**
     * allow directly change object's parameters
     *
     * @param arg
     */
    abstract public void Act(String arg);

    /**
     * render
     */
    @Override
    public Image render() {
        if (current_frame < current_frames.length && current_frame >= 0) {
            return current_frames[current_frame];
        }
        return current_frames[0];

    }
}
