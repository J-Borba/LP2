package figures;

import java.awt.*;
import java.io.Serializable;

import interfaces.IVisible;

public abstract class Figura implements IVisible, Serializable {

    protected int[] xPoints = new int[] {0};
    protected int[] yPoints = new int[] {0};

    private boolean focus;

    protected String type;

    private Color contorno, fundo;

    public Figura(int x, int y, Color contorno, Color fundo){
        this.xPoints[0] = x;
        this.yPoints[0] = y;

        this.contorno = contorno;
        this.fundo = fundo;

        this.focus = focus;
    }

    public abstract boolean corner(int[] coordenada);

    public abstract void setPosition(int[] coordenada);
    public abstract int[] getPosition();

    public abstract int[] getSize();

    public abstract void resize(int[] dTamanho, int tipo);

    public void setColorContorno(Color contorno){this.contorno = contorno;}
    public Color getColorContorno(){return this.contorno;}

    @Override
    public void setColorFundo(Color fundo){this.fundo = fundo;}
    public Color getColorFundo(){return this.fundo;}
}
