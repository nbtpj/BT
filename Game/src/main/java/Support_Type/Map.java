package Support_Type;

import Application.Screen;
import Gobject.*;
import javafx.scene.canvas.Canvas;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map {
    private Canvas static_objects;
    private Canvas movable_objects;
    private Canvas bombs_;
    private Canvas flame_;
    private List<Gobject>[][] data;
    private int N_WIDTH,N_HEIGHT;
    private Map(Gobject gobject){
        static_objects = new Canvas(Screen.WIDTH,Screen.HEIGHT);
        movable_objects = new Canvas(Screen.WIDTH,Screen.HEIGHT);
        bombs_ = new Canvas(Screen.WIDTH,Screen.HEIGHT);
        flame_ = new Canvas(Screen.WIDTH,Screen.HEIGHT);
        N_HEIGHT = Screen.HEIGHT/Pos.SIZE;
        N_WIDTH = Screen.WIDTH/Pos.SIZE;
        this.data = new List[N_HEIGHT][N_WIDTH];
        boolean added = false;
        for(int i=0;i<N_HEIGHT;i++){
            for(int j=0;j<N_WIDTH;j++){
                data[i][j] = new ArrayList<>();
                if(!added && gobject.pos().equals(i,j)) {
                    data[i][j].add(gobject);
                    added = true;
                }
            }
        }
    }
    public static Map Init(Gobject object){
        return new Map(object);
    }
    public void update(double t){
        java.util.Map<Gobject, Pair<List<Gobject>,List<Gobject>>> change_pos = new HashMap<>();
        java.util.Map<Gobject,List<Gobject>> add_new = new HashMap<>();
        java.util.Map<Gobject,List<Gobject>> remove_ = new HashMap<>();
        List<Gobject> new_;
        for(int i=0;i<N_HEIGHT;i++){
            for(int j=0;j<N_WIDTH;j++){
                for(Gobject gobject: data[i][j]){
                    if(gobject.index>0){
                        new_= gobject.update(t);
                        if(new_!=null && !new_.isEmpty()){
                            for( Gobject new_o : new_){
                                Pos pos = new_o.pos();
                                add_new.put(new_o,data[pos.x][pos.y]);
                            }
                        }
                        Pos pos = gobject.pos();
                        if(!pos.equals(i,j)){
                            remove_.put(gobject,data[i][j]);
                            add_new.put(gobject,data[pos.x][pos.y]);
                        }
                    } else {
                        remove_.put(gobject,data[i][j]);
                    }
                }
            }
        }
        for(Gobject o : remove_.keySet()){
            remove_.get(o).remove(o);
        }
        for(Gobject o : add_new.keySet()){
            add_new.get(o).add(o);
        }

    }


    public String Check(Pos pos){
        int X= pos.x,Y=pos.y;
        for(Object o:data[X][Y]){
            if(o instanceof Wall){
                return "Invalid";
            }
        }
        return "Valid";
    }

}
