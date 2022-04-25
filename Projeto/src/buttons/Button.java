package buttons;

import ivisible.IVisible;

import java.awt.*;

public abstract class Button implements IVisible {
    private int x, y, w, h, Type;
    private Color fundo;

    public Button(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    protected void setX(int x){this.x = x;}
    protected void setY(int y){this.y = y;}

    protected int getX(){return this.x;}
    protected int getY(){return this.y;}

    protected int getW(){return this.w;}
    protected int getH(){return this.h;}

    protected void setType(int Type){this.Type = Type;}

    protected Color getFundo(){return this.fundo;}

    public int getType(){return Type;}

    @Override
    public boolean clicked(int cx, int cy) {
        return cx >= x && cx <= x+w && cy >= y && cy <= y+h;
    }

    @Override
    public void setColorFundo(Color fundo){this.fundo = fundo;}
}
