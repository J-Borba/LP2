package figures;

import java.awt.*;

public abstract class Figura {
    private int x, y, z;

    Color contorno, fundo;

    public Figura(int x, int y, int z, Color contorno, Color fundo){
        this.x = x;
        this.y = y;
        this.z = z;
        this.contorno = contorno;
        this.fundo = fundo;
    }

    public int getX(){return x;}
    public int getY(){return y;}
    public int getZ(){return z;}

    public abstract boolean pressed(int x, int y, int z);

    public abstract void drag();

    public abstract void paint(Graphics g);
}
