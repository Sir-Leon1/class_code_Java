public class Test {
    int x, y;

    public static void main(String args[]){
        Test t = new Test();
        t.x = 1;
        t.y = 2;
        t.add(t);
        System.out.println(t.x);
    }

    public void add(Test tt){
        tt.x += tt.y;
        System.out.println(tt.x);
    }
}