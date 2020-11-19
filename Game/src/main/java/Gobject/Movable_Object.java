package Gobject;

import Support_Type.Pos;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;



public abstract class Movable_Object extends Gobject {
    protected String link_to_front,
            link_to_back,
            link_to_left,
            link_to_right;
    protected int n_front, n_back, n_left,n_right;
    protected double V_x = 1, V_y = 1;
    protected int W = Pos.SIZE, H = Pos.SIZE * 2;
    protected  Image[] front;
    protected  Image[] back;
    protected  Image[] left;
    protected  Image[] right;
    protected Image[] current_frames;
    List<String> move_2 = new ArrayList<>();
    List<Pos> list_pos= new ArrayList<Pos>();
    protected boolean move = false;
    protected String direction = "none";
    protected boolean set_bomb = false;
    protected int current_frame = 0;

    public Movable_Object(double index,String name, double x, double y) {
        super(index, name, x, y);

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
            if(target.equals(this.pos())){
                return;
            }
            this.list_pos = Pos.find_way(this.pos(), target, current_map);
            //  System.out.println(list_pos);
            move_2 =Pos.Pos2move(x,y,V_x*current_frames.length,V_y*current_frames.length,list_pos);
            current_frame=-1;
            //  System.out.println(move_2);
        }catch (Exception e){
            System.out.println("not found!");
        }


    }
    /**
     * render
     */
    @Override
    public Image render(){
        if (current_frame < current_frames.length && current_frame >= 0) {
            return current_frames[current_frame];
        }
        return front[0];

    }
}
