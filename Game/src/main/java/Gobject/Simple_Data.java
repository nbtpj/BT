package Gobject;

import Loader.Data;
import Loader.Movable_Object_Images;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Simple_Data implements Serializable {
    public String data_type, type, name;
    public double index, x, y, v_x, v_y, width, height, invisible, invincible, rage, poisonous, maxIndex, default_vx, default_vy, max_invincible_time, max_rage_time, max_invisible_time, max_poisonous_time, healing, max_healing_time, score;
    public boolean using;

    public Simple_Data(Gobject o) {
        this.score = o.score;
        this.data_type = o.getClass().getTypeName();
        this.healing = o.healing;
        this.max_healing_time = o.max_healing_time;
        this.max_invincible_time = o.max_invincible_time;
        this.max_invisible_time = o.max_invisible_time;
        this.max_poisonous_time = o.max_poisonous_time;
        this.max_rage_time = o.max_rage_time;
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
        this.maxIndex = o.maxIndex;
        this.invisible = o.invisible;
        this.invincible = o.invincible;
        this.poisonous = o.poisonous;
        this.default_vx = o.default_vx;
        this.default_vy = o.default_vy;
        this.rage = o.rage;
    }

    public static void main(String[] args) {
        Data.getInstance();
        Movable_Object_Images.getData();
        Bomber paimon = new Bomber("paimon", "Cat01-1", 61, 61);
        Simple_Data o = new Simple_Data(paimon);
        o.save();


    }

    public void save() {
        try {

            FileOutputStream out = new FileOutputStream(Data.localFilePath + "data/" + this.name + System.nanoTime() + ".bin");
            ObjectOutputStream oos = new ObjectOutputStream(out);
            oos.writeObject(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Gobject cvt() throws Exception {
        switch (data_type) {
            case ("Gobject.Bomber"):
                return new Bomber(this);
            case ("Gobject.Bomb"):
                return new Bomb(this);
            case ("Gobject.Enemy_1"):
                return new Enemy_1(this);
            case ("Gobject.Enemy_2"):
                return new Enemy_2(this);
            case ("Gobject.Enemy_3"):
                return new Enemy_3(this);
            case ("Gobject.Enemy_4"):
                return new Enemy_4(this);
            case ("Gobject.Enemy_5"):
                return new Enemy_5(this);
            case ("Gobject.Fire"):
                return new Fire(this);
            case ("Gobject.Soft_Wall"):
                return new Soft_Wall(this);
            case ("Gobject.Appear"):
                return new Appear(this);
            case ("Gobject.Core"):
                return new Core(this);
            case ("Gobject.Rage"):
                return new Rage(this);
            case ("Gobject.Healing"):
                return new Healing(this);
            case ("Gobject.Invisible"):
                return new Invisible(this);
            case ("Gobject.Invincible"):
                return new Invincible(this);
            case ("Gobject.Poison"):
                return new Poison(this);
            default:
                return new Wall(this);
        }
    }


}
