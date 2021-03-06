package Gobject;

import Support_Type.Pos;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class Movable_Object extends Gobject {
    public Text text = new Text();
    protected int W = Pos.SIZE, H = Pos.SIZE * 2;
    protected Map<String, Image[]> frames;
    protected Image[] current_frames;
    protected int current_frame;
    Pos last_enter;
 //   protected double V_x, V_y;
    List<String> move_2 = new ArrayList<>();
    List<Pos> list_pos = new ArrayList<Pos>();

    public Movable_Object(double index, String name, double x, double y) {
        super(index, name, x, y);
        frames = new HashMap<>();
        current_frame = -1;
        text.setFill(Color.ORANGE);
        text.setFont(new Font(10));
        text.setWrappingWidth(30);


    }


    public Movable_Object(int i, String name, Pos pos) {
        super(i, name, pos);
        frames = new HashMap<>();
        current_frame = -1;
        text.setFill(Color.ORANGE);
        text.setFont(new Font(10));
        text.setWrappingWidth(30);
    }

    public Movable_Object(Simple_Data data) {
        super(data);
        frames = new HashMap<>();
        current_frame = -1;
        text.setFill(Color.ORANGE);
        text.setFont(new Font(10));
        text.setWrappingWidth(30);
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
                    new_y += this.v_y;
                    break;
                case "up":
                    new_y -= this.v_y;
                    break;
                case "left":
                    new_x -= this.v_x;
                    break;
                case "right":
                    new_x += this.v_x;
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
                last_enter = pos();
                x = new_x;
                y = new_y;
            }
            if (this.invisible>0 && current_map.get(new_pos)!=null) {
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
