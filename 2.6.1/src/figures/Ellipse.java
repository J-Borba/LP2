package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Figura{

    int x, y, w, h;

    Color contorno, fundo;

    public Ellipse(int x, int y, int w, int h, Color contorno, Color fundo){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.contorno = contorno;
        this.fundo = fundo;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        //desenhando fundo
        g2d.setColor(this.fundo);
        g2d.fill(new Ellipse2D.Double(this.x, this.y, this.w, this.h));

        //desenhando contorno
        g2d.setColor(this.contorno);
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(new Ellipse2D.Double(this.x, this.y, this.w, this.h));
    }
}
