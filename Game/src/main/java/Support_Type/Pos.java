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
    public List<Pos> around(){
        List<Pos> rs = new ArrayList<>();
        rs.add(this.left());
        rs.add(this.right());
        rs.add(this.up());
        rs.add(this.down());
        return rs;
    }
    
    public static List<Pos> find_way_1(Pos begin, Pos end, Map map, List<Pos> before){
        if(begin.equals(end)){
            return new ArrayList<>();
        }
        List<Pos> around = new ArrayList<>();
        List<Double> length = new ArrayList<>();
        if(map.Check(begin.left()).equals("Valid") && (before == null ||!before.contains(begin.left()))){
            around.add(begin.left());
            length.add(begin.left().LengthTo(end));
        }
        if(map.Check(begin.right()).equals("Valid") && (before == null ||!before.contains(begin.left()))){
            around.add(begin.right());
            length.add(begin.right().LengthTo(end));
        }
        if(map.Check(begin.up()).equals("Valid") && (before == null ||!before.contains(begin.left()))){
            around.add(begin.up());
            length.add(begin.up().LengthTo(end));
        }
        if(map.Check(begin.down()).equals("Valid") && (before == null ||!before.contains(begin.left()))){
            around.add(begin.down());
            length.add(begin.down().LengthTo(end));
        }
        for(int i=0;i<length.size()-1;i++){
            for(int j=i+1;j<length.size();j++){
                if(length.get(i).compareTo(length.get(j))>0){
                    Collections.swap(length,i,j);
                    Collections.swap(around,i,j);
                }
            }
        }
        List<Pos> t,t_before;
        for(Pos o : around){
            t_before = new ArrayList<>(before);
            t_before.add(o);
            t = Pos.find_way(o,end,map,t_before);
            if(t !=null){
                t.add(o);
                return t;
            }

        }

        
        return null;
        
    }
    
    
    
    
    
    public static List<Pos> find_way(Pos begin, Pos end, Map map, List<Pos> before) {
        List<Pos> way = new ArrayList<>(), temp;
        if(map.Check(end).equals("Invalid")) {
            return way;
        }
        if (begin.equals(end)) {
            way.add(end);
            return way;
        }
        List<Pos> list = new ArrayList<>();
        if(!map.Check(begin.left()).equals("Invalid") && !before.contains(begin.left())) list.add(begin.left());
        if(!map.Check(begin.right()).equals("Invalid") && !before.contains(begin.right())) list.add(begin.right());
        if(!map.Check(begin.up()).equals("Invalid") && !before.contains(begin.up())) list.add(begin.up());
        if(!map.Check(begin.down()).equals("Invalid") && !before.contains(begin.down())) list.add(begin.down());
        if(list.isEmpty()) return way;
        Pos[] L = list.toArray(new Pos[list.size()]);
        /** L is always valid */
        for(int i=0;i<L.length-1; i++){
            for(int j=i+1;j<L.length;j++){
                if(L[i].LengthTo(end)>L[j].LengthTo(end)){
                    Pos x = L[i];
                    L[i] = L[j];
                    L[j] = x;
                }
            }
        }
        List<Pos> t;
        for(int i=0;i<L.length;i++){
            try{
                t = new ArrayList<Pos>(before);
                t.add(L[i]);
                temp = Pos.find_way(L[i],end,map,t);
                if(temp!=null && temp.contains(end) /*&& !temp.contains(L[i])*/){
                    way.add(L[i]);
                    way.addAll(temp);
                    return way;
                }
            } catch (Exception e){};
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
