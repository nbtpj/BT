package Gobject;

import Loader.Data;
import Loader.Movable_Object_Images;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Simple_Data implements Serializable {
    public String data_type,type,name;
    public double index,x,y,v_x,v_y,width,height;
    public boolean using;

    public Simple_Data(Gobject o) {
        this.data_type = o.getClass().getTypeName();
        this.type = o.type;
        this.name = o.name;
        this.index = o.index;
        this.x = o.x;
        this.y = o.y;
        this.v_x = o.v_x;
        this.v_y = o.v_y;
        this.width = o.width;
        this.height = o.height;
        this.using = o.using;
    }
    public void save() {
        try {

            FileOutputStream out = new FileOutputStream(Data.localFilePath+"data/"+this.name+System.nanoTime()+".bin");
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(this);
        } catch (Exception e) {
           // e.printStackTrace();
            System.err.println("không ghi được");
        }
    }
    public Gobject cvt() throws Exception{
        switch (data_type){
            case("Gobject.Bomber"):
                return new Bomber(this);
            case("Gobject.Bomb"):
                return new Bomb(this);
            case("Gobject.Enemy"):
                return new Enemy(this);
            case("Gobject.Fire"):
                return new Fire(this);
            default:
                return new Wall(this);
        }
    }
    public static void main(String[] args){
        Data.getInstance();
        Movable_Object_Images.getData();
        Bomber paimon = new Bomber("paimon","Cat01-1",61,61);
        Simple_Data o = new Simple_Data(paimon);
        o.save();


    }


}
