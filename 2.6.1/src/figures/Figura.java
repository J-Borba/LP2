package figures;

import java.awt.*;

public abstract class Figura {
    int x, y;
    Color contorno, fundo;

    public abstract void paint(Graphics g);
}
