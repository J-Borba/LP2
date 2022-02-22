public class ElipseApp {
    public static void main(String[] args){
        Elipse e1 = new Elipse(15, 15, 5);
        e1.print();
    }
}

class Elipse {

    int x, y, r;

    public Elipse(int x, int y, int radius){
        this.x = x;
        this.y = y;
        this.r = radius;
    }

    public void print(){
        System.out.printf("Elipse de raio %d esta na posicao (%d, %d).\n", this.r, this.x, this.y);
    }
}