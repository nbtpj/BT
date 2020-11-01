package object;

import event.Event;

import java.util.ArrayList;
import java.util.List;

public class BigMap {
    /* thời gian của bản đồ */
    final double time_index;
    public BigMap(double time_index){
        this.time_index = time_index;
    };
    static final int Size_x = 100, Size_y =100;
    /* dữ liệu của bản đồ, là một tập hợp 2D các ô, mỗi ô chứa 1 lượng vật thể */
    private List<G_Obj>[][] data ;
    public List<G_Obj> data(){
        List<G_Obj> result = new ArrayList<>();
        for(int i=0;i<Size_x;i++){
            for(int j=0;j<Size_y;j++){
                result.addAll(data[i][j]);
            }
        }
        return result;
    }
    /* thêm vật thể */
    public void addG_Obj(G_Obj in){
        if(data == null){
            data = new List[Size_x][Size_y];
            for(int i=0;i<Size_x;i++){
                for(int j=0;j<Size_y;j++){
                    data[i][j] = new ArrayList<>();
                }
            }
        } else {
            data[in.map_x()][in.map_y()].add(in);
        }
    }
    /* lấy vật thể tại 1 ô */
    public List<G_Obj> get_G_Obj(int x, int y){
        if(data !=null && (x>=0 && x< Size_x)&&(y>=0 && y< Size_y)){
            return data[x][y];
        } else {
            return null;
        }
    }
    public List<G_Obj> get_G_Obj(Pos pos){
        int x= pos.x,y=pos.y;
        if(data !=null && (x>=0 && x< Size_x)&&(y>=0 && y< Size_y)){
            return data[x][y];
        } else {
            return null;
        }
    }
    /* trả về lượng máu bị trừ trong 1 ô */
    static public int lost_heal(List<G_Obj> cell){
        int result =0;
        for (Object x: cell
             ) {
            if(x instanceof object.Enemy ){
                result+=5;
            } else {
                if (x instanceof object.Fire) {
                    result += 3;
                }
            }
        }
        return result;
    }
    /* kiểm tra di chuyển hợp lệ */
    public String valid(Pos pos){
        List<G_Obj> cell =this.get_G_Obj(pos);
        for( G_Obj i: cell){
            if (i instanceof Bomb|| i instanceof Wall){
                return "Invalid";
            } else {
                if(i instanceof Fire){
                    return "Dangerous";
                }
            }
        }
        return "Safe";

    }

}
