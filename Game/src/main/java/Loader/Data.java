package Loader;

import javafx.scene.image.Image;

public class Data {
    private static volatile Data data = null;
    Image[] Bomber;
    Image[] Bomb;
    Image[] Enemy;
    Image[] Wall;
    Image[] Item;
    Image[] Flame;
    private Data(){
        Bomber = null;
        Bomb = null;
        Enemy = null;
        Wall = null;
        Item = null;
        Flame = null;
    }
    public static Data getInstance(){
        if (data==null){
            data = new Data();
        }
        return data;
    }
}
