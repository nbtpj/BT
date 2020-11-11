package test;

class Num {
    int i = 100;

    public void setI(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }
}

interface I {
    Num num = new Num();
}

public class Test implements I {

    public static void main(String[] args) {
        Test t1 = new Test();
        Test t2 = new Test();
        Test t3 = new Test();

        t1.num.setI(10);
        t2.num.setI(20);
        t3.num.setI(30);

        System.out.println(t1.num.getI());
    }
}
