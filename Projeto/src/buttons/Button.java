package buttons;

import ivisible.IVisible;

import java.awt.*;

public abstract class Button implements IVisible {
    protected int x, y, w, h, Type;

    public Button(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public int getType(){return Type;}

    @Override
    public boolean pressed(int[] coordenadas) {
        return coordenadas[0] >= x && coordenadas[0] <= x+w    &&   coordenadas[1] >= y && coordenadas[1] <= y+h;
    }
}
