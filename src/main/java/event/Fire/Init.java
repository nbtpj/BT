package event.Fire;

import java.io.FileNotFoundException;

public class Init extends Fire_Event {
    public Init(double x, double y) {
        super(x, y);
    }

    @Override
    public void render() throws FileNotFoundException {
        System.out.println("fire at: ("+x+','+y+")!");
    }
}
