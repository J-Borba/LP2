package figures;

import ivisible.IVisible;

import java.awt.*;

public abstract class Figura implements IVisible {

    public int[] xPoints = new int[] {0};
    public int[] yPoints = new int[] {0};

    private boolean focus;

    protected String type;

    Color contorno, fundo;

    public Figura(int x, int y, Color contorno, Color fundo, boolean focus){
        this.xPoints[0] = x;
        this.yPoints[0] = y;

        this.contorno = contorno;
        this.fundo = fundo;

        this.focus = focus;
    }

    public abstract boolean corner(int[] coordenada);

    public abstract int[] getPosition();

    public abstract void setPosition(int[] coordenada);

    public abstract int[] getSize();

    public abstract void resize(int[] dTamanho, int tipo);

    public String getType(){return type;}

    public boolean getFocus(){return focus;}
    public void setFocus(boolean foco){focus = foco;}

    public Color getColorContorno(){return this.contorno;}
    public void setColorContorno(Color contorno){this.contorno = contorno;}

    public Color getColorFundo(){return this.fundo;}
    public void setColorFundo(Color fundo){this.fundo = fundo;}
}
