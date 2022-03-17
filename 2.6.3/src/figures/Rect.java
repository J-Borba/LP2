package figures;

import java.awt.*;

public class Rect extends Figura{

    int w, h;

    public Rect(int x, int y, int w, int h, Color contorno, Color fundo){
        super(contorno, fundo);

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(this.fundo);
        g2d.fillRect(this.x, this.y, this.w, this.h);

        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(this.contorno);
        g2d.drawRect(this.x, this.y, this.w, this.h);
    }
}
