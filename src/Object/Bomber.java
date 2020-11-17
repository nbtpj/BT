package Object;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import static data.Data.link_bomberman;

public class Bomber extends Movable_Object {
    protected double V_x = 3, V_y = 3;
    protected int W = Pos.SIZE, H = Pos.SIZE * 2;
 /*   protected final Image[] front;
    protected final Image[] back;
    protected final Image[] left;
    protected final Image[] right;
    protected Image[] current_frames;
    List<String> move_2 = new ArrayList<>();
    List<Pos> list_pos= new ArrayList<Pos>();
    protected boolean move = false;
    protected String direction = "none";
    protected boolean set_bomb = false;
    protected int current_frame = 0;*/

    public Bomber(String name, double x, double y) throws Exception {
        super(name, x, y);
        int i;
        back = new Image[4];
        for (i = 0; i < 4; i++)
            back[i] = new Image(new FileInputStream(link_bomberman + "back_" + i + ".png"), Pos.SIZE, Pos.SIZE, false, false);
        left = new Image[4];
        for (i = 0; i < 4; i++)
            left[i] = new Image(new FileInputStream(link_bomberman + "left_" + i + ".png"), Pos.SIZE, Pos.SIZE, false, false);
        front = new Image[4];
        for (i = 0; i < 4; i++)
            front[i] = new Image(new FileInputStream(link_bomberman + "front_" + i + ".png"), Pos.SIZE, Pos.SIZE, false, false);
        right = new Image[4];
        for (i = 0; i < 4; i++)
            right[i] = new Image(new FileInputStream(link_bomberman + "right_" + i + ".png"), Pos.SIZE, Pos.SIZE, false, false);
        current_frame = 0;
        current_Image = front[0];
        current_frames = front;

    }

    @Override
    public List<Object> update(double t) throws Exception {
        List<Object> rs = new ArrayList<>();
        if(set_bomb){
            rs.add(new Bomb(this.cell()));
            set_bomb = false;
        }

        if (heal_index <= 0) {
            InUse = false;
        }
        /** if this object is moving */
        if (current_frame < current_frames.length-1 && !move_2.isEmpty()) {
            current_frame++;
            double new_x = x, new_y = y;
            System.out.println(move_2);
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
                return rs;
            }
            if (!this.current_map.Check(new_pos).equals("Invalid")) {
                    //this.current_map.get(this.cell()).remove(this);
                x = new_x;
                y = new_y;
                    //this.current_map.get(this.cell()).add(this);

                System.out.println(x+" - "+y);

            }

        }
        return rs;

    }

    /**
     * user controls this action, change feature Immediately, and it does NOT relate to the update function
     */
    public void Act(String args) throws Exception {
        double new_x = x, new_y = y;

        move_2 = new ArrayList<>();
        Pos New;
        switch (args) {
            case "set_bomb":
                set_bomb = true;
            //    this.current_map.AddObject(new Bomb(x, y));
                return;
            case "left":
                direction = "left";
                move_2.add("left");
                current_frame = -1;

                break;
            case "right":
                direction = "right";
                move_2.add("right");
                current_frame = -1;
                break;
            case "up":
                direction = "back";
                move_2.add("back");
                current_frame = -1;
                break;
            case "down":
                direction = "front";
                move_2.add("front");
                current_frame = -1;
                break;
            default:
                direction = "none";
                return;
        }


    }
    public void Move2 (Pos target){
        try {
            if(target.equals(this.cell())){
                return;
            }
            this.list_pos = Pos.find_way(this.cell(), target, current_map);
         //   System.out.println(list_pos);
            move_2 =Pos.Pos2move(x,y,V_x*4,V_y*4,list_pos);
            current_frame=-1;
         //   System.out.println(move_2);
        }catch (Exception e){
            System.out.println("not found!");
        }


    }
    /**
     * render
     */
    @Override
    public Image render() throws Exception {
        if (current_frame < current_frames.length && current_frame >= 0) {
            return current_frames[current_frame];
        }
        return front[0];

    }
}
