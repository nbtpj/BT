package Support_Type;



import Gobject.*;
import Loader.Data;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Button.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Map implements Serializable {
    static public final int SIZE_X = 42, SIZE_Y = 22;
    public double time_index;
    public boolean lost = false;
    public Bomber main_c=null;
    private AnimationTimer timer;
    public List<Gobject>[][] data = new List[SIZE_X][SIZE_Y];
    public Canvas Frame;
    public Group graphic,pause;
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
                data[i][j].add(new Wall(new Pos(i,j)));
                count++;
            }
        }
    }
    public void save(){
        try {
            FileOutputStream fos = new FileOutputStream(Data.localFilePath+"data.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Map(Stage stage) throws Exception {
        time_index = System.nanoTime();
        this.background = Data.getInstance().background;
        this.graphic = new Group();
        this.pause = new Group();
      /*  Button main_menu = new Button("Main Menu")
                , quit = new Button("Quit")
                , continue_ = new Button("Continue");
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Stage stage = (Stage)graphic.getScene().getWindow();
                stage.close();
            }
        });
        continue_.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Frame.toFront();
                timer.start();
            }
        });*/
   //     pause.getChildren().add(main_menu);
        
     //   pause.getChildren().add(quit);
        this.Frame = new Canvas(SIZE_X * Pos.SIZE, SIZE_Y * Pos.SIZE);
        graphic.getChildren().add(pause);
        graphic.getChildren().add(Frame);
        pause.getChildren().add(new Quit(this,stage,Frame.getWidth()/2-Frame.getWidth()/10,Frame.getHeight()*3/5-Frame.getHeight()/16
                ,Frame.getWidth()/5,Frame.getHeight()/8));
        Resume resume = new Resume(Frame.getWidth()/2-Frame.getWidth()/10,Frame.getHeight()*1/5-Frame.getHeight()/16
                ,Frame.getWidth()/5,Frame.getHeight()/8);
        resume.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
             public void handle(MouseEvent e) {
                Frame.toFront();
                timer.start();
            }
        });
        pause.getChildren().add(resume);
        Main_Menu menu = new Main_Menu(stage,Frame.getWidth()/2-Frame.getWidth()/10,Frame.getHeight()*2/5-Frame.getHeight()/16
                ,Frame.getWidth()/5,Frame.getHeight()/8);
        pause.getChildren().add(menu);

        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                data[i][j] = new ArrayList<>();
                if (i == 0 || i == SIZE_X - 1 || j == 0 || j == SIZE_Y - 1 || i*j % 2 == 1) {
                    data[i][j].add(new Wall(new Pos(i,j)));
                }
            }
        }
        this.random_Wall();

    }
    public Map(Stage stage,List<Gobject> data) throws Exception {
        time_index = System.nanoTime();
        this.background = Data.getInstance().background;
        this.graphic = new Group();
        this.pause = new Group();
        this.Frame = new Canvas(SIZE_X * Pos.SIZE, SIZE_Y * Pos.SIZE);
        graphic.getChildren().add(pause);
        graphic.getChildren().add(Frame);
        pause.getChildren().add(new Quit(this,stage,Frame.getWidth()/2-Frame.getWidth()/10,Frame.getHeight()*3/5-Frame.getHeight()/16
                ,Frame.getWidth()/5,Frame.getHeight()/8));
        Resume resume = new Resume(Frame.getWidth()/2-Frame.getWidth()/10,Frame.getHeight()*1/5-Frame.getHeight()/16
                ,Frame.getWidth()/5,Frame.getHeight()/8);
        resume.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                Frame.toFront();
                timer.start();
            }
        });
        pause.getChildren().add(resume);
        Main_Menu menu = new Main_Menu(stage,Frame.getWidth()/2-Frame.getWidth()/10,Frame.getHeight()*2/5-Frame.getHeight()/16
                ,Frame.getWidth()/5,Frame.getHeight()/8);
        pause.getChildren().add(menu);
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                this.data[i][j] = new ArrayList<>();
            }
        }

        for(Gobject o: data){
            this.AddGobject(o);
        }

    }
    public void setMain(Bomber bomber){
        this.main_c = bomber;
        this.AddGobject(bomber);
    }
    public Bomber getMain(){
        return main_c;
    }

    public static void main(String[] args) throws Exception {
        Map a = new Map(null);
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

    public List<Gobject> getAll(){
        List<Gobject> rs = new ArrayList<>();
        for (int i = 0; i < SIZE_X; i++) {
            for(int j=0;j< SIZE_Y;j++){
                rs.addAll(data[i][j]);
            }
        }
        return rs;
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
            System.err.println(e.getMessage());
        }

    }

    public void setTimer(AnimationTimer timer) {
        this.timer = timer;
    }

    public void pause(){
        Frame.getGraphicsContext2D().setGlobalAlpha(0.5);
        Frame.getGraphicsContext2D().drawImage(background, 0, 0,SIZE_X*Pos.SIZE,SIZE_Y*Pos.SIZE);
        pause.toFront();
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
    public Canvas render(double t)  {
        Frame.getGraphicsContext2D().setGlobalAlpha(1);
        Frame.getGraphicsContext2D().drawImage(background, 0, 0,SIZE_X*Pos.SIZE,SIZE_Y*Pos.SIZE);
        List<Gobject> New = new ArrayList<>(), New_Pos = new ArrayList<>();
        List<List<Gobject>> Old_Pos = new ArrayList<>();
        List<Gobject> new_,delete_;
        /**
         * update all Object in map
         */
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                delete_ = new ArrayList<>();
                for (Gobject m : data[i][j]) {
                    if (m.using) {
                        new_ = m.update(t);
                        if (new_ != null && !new_.isEmpty()) {
                            New.addAll(new_);
                        }
                    } else {
                        delete_.add(m);
                    }
                    /**
                     * check the current valid current position of object
                     */
                    if (!m.pos().equals(i, j)) {
                        New_Pos.add(m);
                        Old_Pos.add(data[i][j]);
                    }
                }
                data[i][j].removeAll(delete_);
                delete_.clear();
            }
        }
        /**
         * add new objects (came from updating objects) to the map
         */
        for (Gobject o : New) {
            this.AddGobject(o);
        }
        /**
         * replace objects that have invalid position
         */
        for (int i = 0; i < New_Pos.size(); i++) {
            Gobject o = New_Pos.get(i);
            this.get(o.pos()).add(o);
            Old_Pos.get(i).remove(o);
        }
        /**
         * render the image of all objects on the Frame
         */
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                for (Gobject m : data[i][j]) {
                        if (m.render() != null) {

                            if (m instanceof Movable_Object) {
                                Frame.getGraphicsContext2D().drawImage(m.render(), m.x , m.y - Pos.SIZE/2+Pos.SIZE/5);

                            } else {
                                    Frame.getGraphicsContext2D().drawImage(m.render(), m.x, m.y, m.width, m.height);
                            }
                        }
                }
            }
        }
        return Frame;
    }
}
