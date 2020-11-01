package event.Bomb;


import java.io.FileNotFoundException;

public class Explose extends Bomb_Event {
    public Explose(double x, double y) {
        super(x, y, 0);
    }

    @Override
    public void render() throws FileNotFoundException {
        System.out.println("Bomb explose at: ("+x+','+y+")!");
    }
}
