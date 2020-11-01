package event.Bomb;


import java.io.FileNotFoundException;

public class Init extends Bomb_Event {
    public Init(double x, double y, double time) {
        super(x, y, time);
    }

    @Override
    public void render() throws FileNotFoundException {
        System.out.println("bomb has been set at: ("+x+','+y+")!");
    }
}
