package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Figura{

    private int w, h;

    public Ellipse(int x, int y, int z, int w, int h, Color contorno, Color fundo){
        super(x, y, z, contorno, fundo);

        this.w = w;
        this.h = h;

    }

    @Override
    public boolean pressed(int x, int y, int z) {
        if (x >= this.getX() && x <= (this.getX() + this.w) && y >= this.getY() && y <= (this.getY() + this.h) && this.getZ()==1){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void drag() {

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        //desenhando fundo
        g2d.setColor(this.fundo);
        g2d.fill(new Ellipse2D.Double(this.getX(), this.getY(), this.w, this.h));

        //desenhando contorno
        g2d.setColor(this.contorno);
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(new Ellipse2D.Double(this.getX(), this.getY(), this.w, this.h));
    }
}
