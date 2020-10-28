package event.Bomber;

import event.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import event.*;
public class Dead extends Event {

    public Dead(int x, int y) {
        super(x, y);
        type = "Bomber";
        link_to_data_file = "data\\events\\Bomber\\data.txt";
    }
    public void render() throws FileNotFoundException {
        File x = new File(this.link_to_data_file);
        Scanner sc = new Scanner(x);
        System.out.println(sc.next());
    }
    public static void main(String[] args) throws FileNotFoundException {
        Dead m = new Dead(10,20);
        m.render();
    }

}
