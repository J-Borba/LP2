package figures;

import project1.MyPanel;

import java.awt.*;

public abstract class Figura {
    private int x, y, w, h;
    private boolean focus;

    Color contorno, fundo;

    public Figura(int x, int y, int w, int h, Color contorno, Color fundo, boolean focus){
        this.x = x;
        this.y = y;

        this.w = w;
        this.h = h;

        this.contorno = contorno;
        this.fundo = fundo;

        this.focus = focus;
    }

    public int getX(){return x;}
    public int getY(){return y;}

    public void setPosition(int dx, int dy){x += dx; y += dy;}

    public boolean getFocus(){return focus;}

    public void setFocus(boolean foco){focus = foco;}

    public void setColorContorno(Color contorno){this.contorno = contorno;}

    public void setColorFundo(Color fundo){this.fundo = fundo;}

    public int getW(){return w;}
    public int getH(){return h;}

    public void setSize(int dw, int dh){w = dw; h = dh;}

    public int resize(int dw, int dh){
        if(dw <= w-1 && dh <= h-1){
            w += dw;
            h += dh;
        }
         else{
             return 1;
         }
         return 0;
    }

    public abstract boolean pressed(int x, int y);

    public abstract void paint(Graphics g);
}
