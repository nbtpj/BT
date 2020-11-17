package Object;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class Map {
    static final int SIZE_X = 42, SIZE_Y = 22;
    public double time_index;
    public List<Object>[][] data = new List[SIZE_X][SIZE_Y];
    public Canvas Frame;
    public Image background;

    public Map(double time_index) throws Exception {
        this.time_index = time_index;
        this.background = new Image(new FileInputStream("data\\map.jpg"));
        this.Frame = new Canvas(SIZE_X * Pos.SIZE, SIZE_Y * Pos.SIZE);
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                data[i][j] = new ArrayList<>();
                if (i == 0 || i == SIZE_X - 1 || j == 0 || j == SIZE_Y - 1 || i*j % 2 == 1) {
                    data[i][j].add(new Wall("wall", i * Pos.SIZE, j * Pos.SIZE));
                }
            }
        }
        for ( int i=0;i<6;i++){
            double x =Math.random()*SIZE_X*Pos.SIZE, y = Math.random()*SIZE_Y*Pos.SIZE;
            this.AddObject(new Enemy("enemy",x,y));
        }
    }

    public static void main(String[] args) throws Exception {
        Map a = new Map(0);
        int i = 0;
        for (List<Object>[] x_Object : a.data
        ) {
            for (List<Object> y_Object : x_Object) {
                if (y_Object == null) {
                    i++;
                    System.out.println("null");
                }
            }

        }
        System.out.println(i);
    }


    public List<Object> get(Pos x) {
        return data[x.x][x.y];
    }

    public void AddObject(Object x) {
        x.current_map = this;
        System.out.println("adding " + x.name);
        try {
            data[x.cell().x][x.cell().y].add(x);
        } catch (Exception e) {
            System.out.println("out of range of map");
        }

    }

    public String Check(Pos x) {
        if((x.x - SIZE_X)*(x.x-0)>=0||(x.y - SIZE_Y)*(x.y-0)>=0){
            return "Invalid";
        }
        for (Object m : this.get(x)) {
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

        List<Object> delete;
        Frame.getGraphicsContext2D().drawImage(this.background, 0, 0);
        //Frame.getGraphicsContext2D().setFill(Color.GREEN);
        List<Object> New = new ArrayList<Object>(), New_Pos = new ArrayList<>();
        List<List<Object>> Old_Pos = new ArrayList<List<Object>>();
        List<Object> New_;
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                for (Object m : data[i][j]) {
                    if (m.InUse) {
                        New_ = m.update(t);
                        if (New_ != null && !New_.isEmpty()) {
                            New.addAll(New_);
                        }
                    }
                    if (!m.cell().equals(i, j)) {
                        New_Pos.add(m);
                        Old_Pos.add(data[i][j]);
                    }
                }
            }
        }
        for (Object o : New) {
            this.AddObject(o);
        }
        for (int i = 0; i < New_Pos.size(); i++) {
            Object o = New_Pos.get(i);
            this.get(o.cell()).add(o);
            Old_Pos.get(i).remove(o);
        }
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                delete = new ArrayList<>();
                for (Object m : data[i][j]) {
                    if (m.InUse) {
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
                    for (Object e : delete) {
                        System.out.println("deleted :" + e.name);
                    }
                }

                delete.clear();
            }
        }
        return Frame;
    }
}
