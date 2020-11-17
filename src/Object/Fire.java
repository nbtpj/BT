package Object;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class Fire extends Object{
    public int current_I_Image;
    public Image flame ;
    public Fire(double x, double y) throws Exception {

        super("fire", x, y);
        System.out.println("init fire");
     /*   current_I_Image = 0;*/
        heal_index = 5;
        flame = new Image(new FileInputStream("data\\Fire\\fire.png"),Pos.SIZE,Pos.SIZE,false,false);

    }
    public Fire(Pos pos) throws Exception{
        super("fire",pos);
        heal_index = 5;
        flame = new Image(new FileInputStream("data\\Fire\\fire.png"),Pos.SIZE,Pos.SIZE,false,false);
    }



    @Override
    public List<Object> update(double t) throws Exception {
        heal_index -=t;
        if(heal_index<=0) {
            InUse = false;
            return null;
        }
        List<Object> current_cell = this.current_map.get(this.cell());
        for(Object m: current_cell){
            if(m instanceof Enemy ||m instanceof Bomber||m instanceof Wall){
                m.heal_index -=100;
            }
        }
        return null;
    }

    @Override
    public Image render() throws FileNotFoundException {
        return flame;
    }

}
