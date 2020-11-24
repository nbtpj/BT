package Support_Type;


import Gobject.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class Map {
    static final int SIZE_X = 42, SIZE_Y = 22;
    public double time_index;
    public List<Gobject>[][] data = new List[SIZE_X][SIZE_Y];
    public Canvas Frame;
    public Image background;
    public void random_Wall () throws Exception{
        int count =0;
        while(count<30){
            int i= (int) Math.round(Math.random()*(SIZE_X-1)), j = (int) Math.round(Math.random()*(SIZE_Y-1));
            boolean ct = false;
            for (Gobject o: data[i][j]){
                if(o instanceof Wall) ct = true;
            }
            if(!ct) {
               // data[i][j].add(new Wall("wall",i * Pos.SIZE, j * Pos.SIZE));
                count++;
            }
        }
    }
    public Map(double time_index) throws Exception {
        this.time_index = time_index;
        this.background = new Image(new FileInputStream("data\\map.jpg"));
        this.Frame = new Canvas(SIZE_X * Pos.SIZE, SIZE_Y * Pos.SIZE);
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                data[i][j] = new ArrayList<>();
                if (i == 0 || i == SIZE_X - 1 || j == 0 || j == SIZE_Y - 1 || i*j % 2 == 1) {
                 //   data[i][j].add(new Wall("wall", i * Pos.SIZE, j * Pos.SIZE));
                }
            }
        }
       /* for ( int i=0;i<6;i++){
            double x =Math.random()*SIZE_X*Pos.SIZE, y = Math.random()*SIZE_Y*Pos.SIZE;
            this.AddGobject(new Enemy("enemy",x,y));
        }*/
        this.random_Wall();
    }

    public static void main(String[] args) throws Exception {
        Map a = new Map(0);
        int i = 0;
        for (List<Gobject>[] x_Gobject : a.data
        ) {
            for (List<Gobject> y_Gobject : x_Gobject) {
                if (y_Gobject == null) {
                    i++;
                    System.out.println("null");
                }
            }

        }
        System.out.println(i);
    }


    public List<Gobject> get(Pos x) {
        return data[x.x][x.y];
    }

    public void AddGobject(Gobject x) {
        x.current_map = this;
        System.out.println("adding " + x.name);
        try {
            Pos pos = x.pos();
            data[pos.x][pos.y].add(x);
        } catch (Exception e) {
            System.out.println("out of range of map");
        }

    }

    public String Check(Pos x) {
        if((x.x - SIZE_X)*(x.x-0)>=0||(x.y - SIZE_Y)*(x.y-0)>=0){
            return "Invalid";
        }
        for (Gobject m : this.get(x)) {
            if (m instanceof Bomb || m instanceof Wall) {
                //   System.out.println("invalid");
                return "Invalid";
            }
        }
        return "Valid";
    }

    /**
     * update map and this 's Frame in the next t second(s)
     */
    public Canvas render(double t) throws Exception {

        List<Gobject> delete;
        Frame.getGraphicsContext2D().drawImage(this.background, 0, 0);
        List<Gobject> New = new ArrayList<Gobject>(), New_Pos = new ArrayList<>();
        List<List<Gobject>> Old_Pos = new ArrayList<List<Gobject>>();
        List<Gobject> New_;
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                for (Gobject m : data[i][j]) {
                    if (m.index>=0) {
                        New_ = m.update(t);
                        if (New_ != null && !New_.isEmpty()) {
                            New.addAll(New_);
                        }
                    }
                    if (!m.pos().equals(i, j)) {
                        New_Pos.add(m);
                        Old_Pos.add(data[i][j]);
                    }
                }
            }
        }
        for (Gobject o : New) {
            this.AddGobject(o);
        }
        for (int i = 0; i < New_Pos.size(); i++) {
            Gobject o = New_Pos.get(i);
            this.get(o.pos()).add(o);
            Old_Pos.get(i).remove(o);
        }
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                delete = new ArrayList<>();
                for (Gobject m : data[i][j]) {
                    if (m.index>=0) {
                        if (m.render() != null) {
                            if (m instanceof Bomber) {
                                Frame.getGraphicsContext2D().drawImage(m.render(), m.x , m.y - Pos.SIZE/2+Pos.SIZE/5);
                            } else {
                                if (m instanceof  Enemy){
                                    Frame.getGraphicsContext2D().drawImage(m.render(), m.x, m.y- Pos.SIZE/2+Pos.SIZE/5);
                                } else {
                                    Frame.getGraphicsContext2D().drawImage(m.render(), m.x, m.y);
                                }
                            }
                        }
                    } else {
                        delete.add(m);
                    }
                }
                boolean deleted = data[i][j].removeAll(delete);

                if (deleted) {
                    for (Gobject e : delete) {
                        System.out.println("deleted :" + e.name);
                    }
                }

                delete.clear();
            }
        }
        return Frame;
    }
}
