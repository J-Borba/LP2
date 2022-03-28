package figures;

import java.awt.*;

public class Rect extends Figura{

    public Rect(int x, int y, int w, int h, Color contorno, Color fundo){
        super(x, y, w, h, contorno, fundo, false);
    }

    @Override
    public boolean pressed(int x, int y) {
        return x >= this.getX() && x <= (this.getX() + this.getW()) && y >= this.getY() && y <= (this.getY() + this.getH());
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(this.fundo);
        g2d.fillRect(this.getX(), this.getY(), this.getW(), this.getH());

        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(this.contorno);
        g2d.drawRect(this.getX(), this.getY(), this.getW(), this.getH());

        if(this.getFocus()){
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(Color.red);
            g2d.drawRect(this.getX()-2, this.getY()-2, this.getW()+5, this.getH()+5);
        }
    }
}
