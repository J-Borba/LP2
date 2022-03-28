package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Figura{

    public Ellipse(int x, int y, int w, int h, Color contorno, Color fundo){
        super(x, y, w, h, contorno, fundo, false);
    }

    @Override
    public boolean pressed(int x, int y) {
        return x >= this.getX() && x <= (this.getX() + this.getW()) && y >= this.getY() && y <= (this.getY() + this.getH());
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        //desenhando fundo
        g2d.setColor(this.fundo);
        g2d.fill(new Ellipse2D.Double(this.getX(), this.getY(), this.getW(), this.getH()));

        //desenhando contorno
        g2d.setColor(this.contorno);
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(new Ellipse2D.Double(this.getX(), this.getY(), this.getW(), this.getH()));

        if(this.getFocus()){
            g2d.setColor(Color.red);
            g2d.setStroke(new BasicStroke(2));
            g2d.draw(new Ellipse2D.Double(this.getX()-2, this.getY()-2, this.getW()+4, this.getH()+4));
        }
    }
}
