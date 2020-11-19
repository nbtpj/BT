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
    public boolean equals(java.lang.Object o) {
        if (this == o) return true;
        if (!(o instanceof Pos)) return false;
        Pos pos = (Pos) o;
        //     System.out.println("["+x+","+y+"]");
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

    public static List<Pos> find_way (Pos begin, Pos end, Map map) {
        List<Pos> way = new ArrayList<Pos>(), temp;
        if (begin.equals(end)) {
            way.add(end);
            return way;
        }
        java.util.Map<Double, List<Pos>> length_nextSteps = new HashMap<Double, List<Pos>>();
        Pos     left = begin.left(),
                right = begin.right(),
                up = begin.up(),
                down = begin.down();
        if(!map.Check(left).equals("Invalid")){
            Double length = left.LengthTo(end);
            if(!length_nextSteps.containsKey(length))
            {length_nextSteps.put(length,new ArrayList<Pos>());}
            length_nextSteps.get(length).add(left);
        }
        if(!map.Check(right).equals("Invalid")){
            Double length = right.LengthTo(end);
            if(!length_nextSteps.containsKey(length))
            {length_nextSteps.put(length,new ArrayList<Pos>());}
            length_nextSteps.get(length).add(right);
        }
        if(!map.Check(up).equals("Invalid")){
            Double length = up.LengthTo(end);
            if(!length_nextSteps.containsKey(length))
            {length_nextSteps.put(length,new ArrayList<Pos>());}
            length_nextSteps.get(length).add(up);
        }
        if(!map.Check(down).equals("Invalid")){
            Double length = down.LengthTo(end);
            if(!length_nextSteps.containsKey(length))
            {length_nextSteps.put(length,new ArrayList<Pos>());}
            length_nextSteps.get(length).add(down);
        }
        Double[] keys = length_nextSteps.keySet().toArray(new Double[length_nextSteps.size()]);
        Arrays.sort(keys);
        if(length_nextSteps.isEmpty()){
            return null;
        }
        for(Double key: keys){
            if(!length_nextSteps.get(key).isEmpty())
                for(Pos next : length_nextSteps.get(key)){
                    temp = Pos.find_way(next,end,map);
                    if(temp !=null && temp.contains(end)){
                        way.add(next);
                        way.addAll(temp);
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
    public static List<String> Pos2move(double x,double y, double V_x, double V_y,List<Pos> input){
        List<String> rs = new ArrayList<String>();
        Pos now;
        int i =0, step =0;
        while(i<input.size()-1){
            step++;
            now = new Pos(x,y);
            if(input.get(i).equals(now.left())){
                x-=V_x;
                rs.add("left");
            } else {
                if(input.get(i).equals(now.right())){
                    x+=V_x;
                    rs.add("right");
                } else {
                    if(input.get(i).equals(now.up())){
                        y-=V_y;
                        rs.add("back");
                    } else {
                        if(input.get(i).equals(now.down())){
                            y+=V_y;
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
