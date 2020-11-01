package object;

import event.Enemy.Attack;
import event.Enemy.Dead;
import event.Enemy.Lost_Heal;
import event.Event;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends G_Obj{
    public int heal_index = 1000;
    public Enemy(double x, double y, BigMap current_map) {
        super(x, y, current_map);
        Vx=1;
        Vy=0;
    }


    @Override
    public List<Event> Update(double time) {
        List<Event> result = new ArrayList<>();
        /** heal_index <=0 -> dead */
        if(this.heal_index<=0) {
            result.add(new Dead(x,y,"enemy",0));
            return result;
        }
        boolean attacking= false;
        /** có lửa ở ô hiện tại -> mất máu, có Bombẻ ở ô hiện tại -> tấn công */
        for(G_Obj X: this.current_map.get_G_Obj(this.pos())){
            if(X instanceof Fire){
                this.heal_index-=10;
                result.add(new Lost_Heal(this.x,this.y,"enemy",heal_index));
            } else {
                if(X instanceof Bomber){
                    result.add(new Attack(x,y,"enemy",heal_index));
                    attacking = true;
                }
            }
        }
        if(!attacking){

        }


        return result;
    }
}
