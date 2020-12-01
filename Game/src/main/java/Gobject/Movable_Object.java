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
        current_frame = 0;

    }

    public Movable_Object(int i, String name, Pos pos) {
        super(i, name, pos);
        frames = new HashMap<>();
        current_frame = 0;
    }

    /**
     * the object moves according to the value in the move_2 components (in order)
     * after this step, current frame will be updated (and move_2, frames)
     */
    public void move() {
        if (!move_2.isEmpty()) {
            double new_x = x, new_y = y;
            current_frames = frames.get(move_2.get(0));
            switch (move_2.get(0)) {
                case "up":
                    new_y -= V_y;
                    break;
                case "down":
                    new_y += V_y;
                    break;
                case "left":
                    new_x -= V_x;
                    break;
                case "right":
                    new_x += V_x;
                    break;
            }
            if (current_frame == current_frames.length) {
                current_frame = 0;
                move_2.remove(0);
                if (!move_2.isEmpty()) {
                    current_frames = frames.get(move_2.get(0));
                }
            }
            Pos new_pos = new Pos(new_x, new_y);
            if (pos().equals(new_pos) || current_map.Check(new_pos).equals("Valid")) {
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
        System.out.println(current_frame);
        Image rs = current_frames[current_frame];
        if (!move_2.isEmpty()) current_frame++;
        return rs;

    }
}
