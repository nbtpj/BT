package event.Bomber;

import event.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import event.*;
public class Dead extends Bomber_Event {

    public Dead(double x, double y, String name, int heal_index) {
        super(x, y, name, heal_index);
    }


    public void render() throws FileNotFoundException {
        System.out.println(name+" was dead!");
    }


}
