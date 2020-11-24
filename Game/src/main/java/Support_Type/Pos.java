package Support_Type;


import java.util.*;

public class Pos {
    static public final int SIZE = 30;
    public int x,y;
    public Pos(double x,double y){
        this.x = (int) Math.round(x/SIZE);
        this.y = (int) Math.round(y/SIZE);
    }
    public Pos(int x,int y){
        this.x = x;
        this.y = y;
    }
    public List<Pos> around(){
        List<Pos> rs = new ArrayList<>();
        rs.add(this.left());
        rs.add(this.right());
        rs.add(this.up());
        rs.add(this.down());
        return rs;
    }

    public double get_center_X(){
        return this.x*Pos.SIZE+SIZE/2;
    }
    public double get_center_Y(){
        return this.y*Pos.SIZE+SIZE/2;
    }
    public Pos left(){
        return new Pos(x-1,y);
    }
    public Pos right(){
        return new Pos(x+1,y);
    }
    public Pos up(){
        return new Pos(x,y-1);
    }
    public Pos down(){
        return new Pos(x,y+1);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pos)) return false;
        Pos pos = (Pos) o;
        return x == pos.x &&
                y == pos.y;
    }
    public boolean equals(int x, int y) {
        return x == this.x &&
                y == this.y;
    }
    public Double LengthTo(Pos B){
        return (Double)Math.sqrt((x - B.x)*(x - B.x)+(y - B.y)*(y - B.y));
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public static List<Pos> find_way (Pos begin, Pos end, Map map,List<Pos> traveled) {
        List<Pos> way = new ArrayList<>(), temp,travel_temp;
        if (begin.equals(end)) {
            way.add(end);
            return way;
        }
        Pos[] next_step = begin.around().toArray(new Pos[4]);
        for(int i=0;i<3;i++){
            for(int j=0;j<4;j++){
                if(next_step[i].LengthTo(end)>next_step[j].LengthTo(end)){
                    Pos t = next_step[i];
                    next_step[i] = next_step[j];
                    next_step[j] = t;
                }
            }
        }
        for( Pos pos : next_step){
            if(map.Check(pos).equals("Valid") && !traveled.contains(pos)){
                travel_temp = new ArrayList<>(traveled);
                travel_temp.add(pos);
                temp = find_way(pos,end,map,travel_temp);
                if(temp!=null && temp.contains(end)){
                    way.addAll(temp);
                    way.add(pos);
                    return way;
                }
            }
        }
        return null;
    }

    public String toString() {
        return "(" + x +
                ","+ y +
                ')';
    }

    public static List<String> Pos2move(double x, double y, double V_up, double V_down, double V_left,
                                        double V_right, List<Pos> input) {
        List<String> rs = new ArrayList<>();
        Pos now;
        int i = 0, step = 0;
        if(input==null) return rs;
        while (i < input.size() - 1) {
            step++;
            now = new Pos(x, y);
            if (input.get(i).equals(now.left())) {
                x -= V_left;
                rs.add("left");
            } else {
                if (input.get(i).equals(now.right())) {
                    x += V_right;
                    rs.add("right");
                } else {
                    if (input.get(i).equals(now.up())) {
                        y -= V_up;
                        rs.add("back");
                    } else {
                        if (input.get(i).equals(now.down())) {
                            y += V_down;
                            rs.add("front");
                        } else {
                            i++;
                        }
                    }
                }
            }
        }
        //System.out.println(step);
        return rs;


    }
    public static void main(String[] args){

    }
}
