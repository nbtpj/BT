package Object;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;


public class Wall extends Object{
    public Wall(String name, double x, double y) throws Exception {

        super(name, x, y);
        current_Image = new Image(new FileInputStream("Sprites\\Blocks\\SolidBlock.png"),Pos.SIZE,Pos.SIZE,false,false);
    }

    @Override
    public List<Object> update(double t) throws Exception {
        if(this.heal_index<=0){
            InUse = false;
        }
        return null;

    }

    @Override
    public Image render() throws FileNotFoundException {
        return current_Image;
    }
}
