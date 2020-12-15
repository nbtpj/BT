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
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

public class Data {

    public static String localFilePath = "Game/src/main/resources/";

    private static volatile Data Instance = null;
    public AudioClip game_world_music,main_menu_music,choose_character_music, cloning, effect, explosion;
    public static Font font;
    public static Map<String,Image> load_all() throws IOException {

        Map<String,Image> rs = new HashMap<>();
        List<File> filesInFolder = Files.walk(Paths.get("Game/src/main/resources/"))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
        for (File f : filesInFolder){
            System.out.println("getting: "+f.getName().replace(".png",""));
            FileInputStream input = new FileInputStream(f);
            if(f.getName().contains(".jpg")||f.getName().contains(".gif")||f.getName().contains(".png")){
            rs.put(f.getName().replace(".png",""),new Image(input));}
            if(f.getName().contains("MachineGunk")){
                System.out.println("found");
                try{
                    font = Font.loadFont(input,150);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            input.close();
        }
        return rs;
    }
    public static final int W = Pos.SIZE, H = Pos.SIZE;
    public Image[] bomb,fire;
    public Image background,wall,triangle1,bkg;
    public  Map<String,Image> img_map;

    private Data() {
        try {
            game_world_music = new AudioClip(new File(localFilePath+"game_world_music.mp3").toURI().toString());
            choose_character_music = new AudioClip(new File(localFilePath+"game_world_music.mp3").toURI().toString());
            main_menu_music = new AudioClip(new File(localFilePath+"main_menu_music.mp3").toURI().toString());
            cloning = new AudioClip(new File(localFilePath+"cloning.wav").toURI().toString());
            effect = new AudioClip(new File(localFilePath+"effect.wav").toURI().toString());
            explosion = new AudioClip(new File(localFilePath+"explosion.wav").toURI().toString());
            img_map = load_all();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i;
        /** init bomb series */
        bomb = new Image[3];
        for (i = 1; i <= 3; i++) {
            bomb[i - 1] = img_map.get("Bomb_f0" + i );
        }
        /** init fire series */
        fire = new Image[5];
        for (i = 0; i < 5; i++) {
            fire[i] = img_map.get("Flame_F0" + i );
        }
        background = img_map.get("map.jpg");
        wall = img_map.get("SolidBlock");
        triangle1 = img_map.get("tri2");
        bkg = img_map.get("Space_Background1");
    }

    public static Data getInstance() {
        if (Instance == null) {
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
    public static Image get(String key){
        return Data.getInstance().img_map.get(key);
    }


}
