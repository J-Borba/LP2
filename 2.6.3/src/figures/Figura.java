package figures;

import java.awt.*;

public abstract class Figura {
    int x, y;
    Color contorno, fundo;

    public Figura(Color contorno, Color fundo){
        this.contorno = contorno;
        this.fundo = fundo;
    }

    public abstract void paint(Graphics g);
}
