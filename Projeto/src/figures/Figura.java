package figures;

import java.awt.*;

public abstract class Figura {
    protected int x, y;
    private boolean focus;

    Color contorno, fundo;

    public Figura(int x, int y, Color contorno, Color fundo, boolean focus){
        this.x = x;
        this.y = y;

        this.contorno = contorno;
        this.fundo = fundo;

        this.focus = focus;
    }

    public abstract boolean pressed(int[] coordenada);

    public abstract int[] getPosition();

    public abstract void setPosition(int[] coordenada);

    public abstract void setSize(int[] tamanho);

    public abstract int[] getSize();

    public abstract int resize(int[] dTamanho);

    public boolean getFocus(){return focus;}

    public void setFocus(boolean foco){focus = foco;}
    public Color getColorContorno(){return this.contorno;}

    public void setColorContorno(Color contorno){this.contorno = contorno;}

    public Color getColorFundo(){return this.fundo;}

    public void setColorFundo(Color fundo){this.fundo = fundo;}

    public abstract void paint(Graphics g);
}
