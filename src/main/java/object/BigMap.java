package object;

import java.util.HashSet;
import java.util.Set;

public class BigMap {
    /* thời gian của bản đồ */
    final double time_index;
    public BigMap(double time_index){
        this.time_index = time_index;
    };
    static final int Size_x = 100, Size_y =100;
    /* dữ liệu của bản đồ, là một tập hợp 2D các ô, mỗi ô chứa 1 lượng vật thể */
    private Set<Sprite>[][] data ;
    public Set<Sprite> data(){
        Set<Sprite> result = new HashSet<>();
        for(int i=0;i<Size_x;i++){
            for(int j=0;j<Size_y;j++){
                result.addAll(data[i][j]);
            }
        }
        return result;
    }
    /* thêm vật thể */
    void addSprite(Sprite in){
        if(data == null){
            data = new Set[Size_x][Size_y];
            for(int i=0;i<Size_x;i++){
                for(int j=0;j<Size_y;j++){
                    data[i][j] = new HashSet<>();
                }
            }
        } else {
            data[in.map_x()][in.map_y()].add(in);
        }
    }
    /* lấy vật thể tại 1 ô */
    public Set<Sprite> get_Sprite(int x, int y){
        if(data !=null && (x>=0 && x< Size_x)&&(y>=0 && y< Size_y)){
            return data[x][y];
        } else {
            return null;
        }
    }
}
