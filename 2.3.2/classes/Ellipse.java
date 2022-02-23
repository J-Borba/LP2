package classes;

import java.awt.*;
import java.awt.geom.Ellipse2D;

//Criando classe elipse
public class Ellipse {
    public int x, y;
    public int w, h;
    private Color contorno, fundo;

    public Ellipse(int x, int y, int w, int h, Color contorno, Color fundo) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.contorno = contorno;
        this.fundo = fundo;
    }

    public void print() {
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
                this.w, this.h, this.x, this.y);
    }

    public void drag(int dx, int dy) {
        this.x = dx;
        this.y = dy;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        //desenhando fundo
        g2d.setColor(this.fundo);
        g2d.setStroke(new BasicStroke(5));
        g2d.fill(new Ellipse2D.Double(this.x, this.y, this.w, this.h));

        //desenhando contorno
        g2d.setColor(this.contorno);
        g2d.draw(new Ellipse2D.Double(this.x, this.y, this.w, this.h));
    }
}