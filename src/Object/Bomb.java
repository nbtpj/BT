package Object;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Bomb extends Object{
    static final int W= Pos.SIZE,H=Pos.SIZE*2;
    public int current_I_Image;
    Image[] bomb = new Image[3];
    public Bomb(double x, double y) throws Exception {

        super("bomb", x, y);
        heal_index = 3;
        current_I_Image = 0;
        for(int i=1;i<=3;i++){
            bomb[i-1] = new Image(new FileInputStream("Sprites\\Bomb\\Bomb_f0"+i+".png"),Pos.SIZE,Pos.SIZE,false,false);
        }
        current_Image = bomb[0];
    }

    public Bomb( Pos X) throws Exception{
        super("bomb", X);
        heal_index = 3;
        current_I_Image = 0;
        for(int i=1;i<=3;i++){
            bomb[i-1] = new Image(new FileInputStream("Sprites\\Bomb\\Bomb_f0"+i+".png"),Pos.SIZE,Pos.SIZE,false,false);
        }
        current_Image = bomb[0];
    }

    @Override
    public List<Object> update(double t) throws Exception {
        List<Object> result = new ArrayList<>();
        heal_index -= t;
        if(heal_index<=0) {

            System.out.println("explosed----------------------------------------");
            InUse = false;
            Pos X = this.cell();
          /*  result.add(new Fire(X)) ;
            result.add(new Fire(X.left()));
            result.add(new Fire(X.right()));
            result.add(new Fire(X.up()));
            result.add(new Fire(X.down()));*/
            return result;
          //  System.out.println(InUse);
        }
        return result;
    }

    @Override
    public Image render() throws FileNotFoundException {
        System.out.println(this.heal_index);
        current_I_Image+=1;
        if(current_I_Image>2) current_I_Image = 0;
        return bomb[current_I_Image];

    }
}
