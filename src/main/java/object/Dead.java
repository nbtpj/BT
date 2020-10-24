package object;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * sự kiện bomber chết (demo)
 */
public class Dead extends Event {

    public Dead(int x,int y){

        super(x,y);
        type = "no_animation";
        link_to_data_file = "data\\events\\dead\\data.txt";

    }
    public static void main(String[] args) throws FileNotFoundException {
        Dead m = new Dead(10,20);
        File x = new File(m.link_to_data_file);
        Scanner sc = new Scanner(x);
        System.out.println(sc.next());
    }
}
