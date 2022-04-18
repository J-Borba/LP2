package buttons;

import ivisible.IVisible;

import java.awt.*;

public abstract class Button implements IVisible {
    protected int x, y, w, h, Type;
    protected Color fundo;

    public Button(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public int getType(){return Type;}

    @Override
    public boolean clicked(int cx, int cy) {
        return cx >= x && cx <= x+w && cy >= y && cy <= y+h;
    }

    @Override
    public void setColorFundo(Color fundo){this.fundo = fundo;}
}
