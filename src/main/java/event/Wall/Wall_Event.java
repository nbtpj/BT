package event.Wall;

import event.Event;

public abstract class Wall_Event extends Event {
    /**
     * name: [static,non_static]
     */
    public int heal_index;
    public Wall_Event(double x, double y, String type, int heal_index) {
        super(x, y, type);
        this.heal_index = heal_index;
    }
    public void render_heal_bar(){
        if(this.name.equals("non_static")){
       /* System.out.println("Wall : "+heal_index);*/
       }

    }
}
