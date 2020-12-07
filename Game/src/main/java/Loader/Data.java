package Loader;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Support_Type.Pos;

public class Data {
    public static String localFilePath = "Game/src/main/resources/";
    private static volatile Data Instance = null;

    public static Map<String,Image> load_all() throws IOException {
        Map<String,Image> rs = new HashMap<>();
        List<File> filesInFolder = Files.walk(Paths.get("Game/src/main/resources/"))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
        for (File f : filesInFolder){
            System.out.println("getting: "+f.getName().replace(".png",""));
            rs.put(f.getName().replace(".png",""),new Image(new FileInputStream(f)));
        }
        return rs;
    }
    public static String Bomber_name ;
    public static String Enemy_name ;
    public static final int W = Pos.SIZE, H = Pos.SIZE;
    public Image[] bomberman_front, bomberman_back, bomberman_left, bomberman_right, bomb,
            enemy_front, enemy_back, enemy_left, enemy_right, fire;
    public Image background,wall;
    public static void choose_character(String Bomber_name, String Enemy_name){
        Data.Bomber_name = Bomber_name;
        Data.Enemy_name = Enemy_name;
    }

    private Data() throws FileNotFoundException {
        /** init bomberman series */

        int i;
        bomberman_back = new Image[3];
        for (i = 0; i < 3; i++)
            bomberman_back[i] = new Image(new FileInputStream(localFilePath + Bomber_name + "back_" + i + ".png"), Pos.SIZE, Pos.SIZE, false, false);
        bomberman_left = new Image[3];
        for (i = 0; i < 3; i++)
            bomberman_left[i] = new Image(new FileInputStream(localFilePath + Bomber_name + "left_" + i + ".png"), Pos.SIZE, Pos.SIZE, false, false);
        bomberman_front = new Image[3];
        for (i = 0; i < 3; i++)
            bomberman_front[i] = new Image(new FileInputStream(localFilePath + Bomber_name + "front_" + i + ".png"), Pos.SIZE, Pos.SIZE, false, false);
        bomberman_right = new Image[3];
        for (i = 0; i < 3; i++)
            bomberman_right[i] = new Image(new FileInputStream(localFilePath + Bomber_name + "right_" + i + ".png"), Pos.SIZE, Pos.SIZE, false, false);
        /** init enemy series */
        enemy_back = new Image[3];
        for (i = 0; i < 3; i++)
            enemy_back[i] = new Image(new FileInputStream(localFilePath + Enemy_name + "back_" + i + ".png"), Pos.SIZE, Pos.SIZE, false, false);
        enemy_left = new Image[3];
        for (i = 0; i < 3; i++)
            enemy_left[i] = new Image(new FileInputStream(localFilePath + Enemy_name + "left_" + i + ".png"), Pos.SIZE, Pos.SIZE, false, false);
        enemy_front = new Image[3];
        for (i = 0; i < 3; i++)
            enemy_front[i] = new Image(new FileInputStream(localFilePath + Enemy_name + "front_" + i + ".png"), Pos.SIZE, Pos.SIZE, false, false);
        enemy_right = new Image[3];
        for (i = 0; i < 3; i++)
            enemy_right[i] = new Image(new FileInputStream(localFilePath + Enemy_name + "right_" + i + ".png"), Pos.SIZE, Pos.SIZE, false, false);
        /** init bomb series */
        bomb = new Image[3];
        for (i = 1; i <= 3; i++) {
            bomb[i - 1] = new Image(new FileInputStream(localFilePath + "Bomb_f0" + i + ".png"), Pos.SIZE, Pos.SIZE, false, false);
        }
        /** init fire series */
        fire = new Image[5];
        for (i = 0; i < 5; i++) {
            fire[i] = new Image(new FileInputStream(localFilePath + "Flame_F0" + i + ".png"), Pos.SIZE, Pos.SIZE, false, false);
        }
        background = new Image(new FileInputStream(localFilePath+"map.jpg"));
        wall = new Image(new FileInputStream(localFilePath+"SolidBlock.png"), Pos.SIZE, Pos.SIZE, false, false);
    }

    public static Data getInstance() {
        if (Instance == null) {
            if(Bomber_name == null || Enemy_name == null){
                Data.choose_character("Female02-4_","Soldier07-2_");
            }
            try {
                Instance = new Data();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return Instance;
    }

    public static void main(String[] args) throws IOException {
        //Data.choose_character("Female02-4_","Soldier07-2_");
        //Data x = getInstance();
        Map<String,Image> a = Data.load_all();
        System.out.println(a);
    }


}
