package figures;

import java.awt.*;

public class Rect extends Figura{

    private int w, h;

    public Rect(int x, int y, int z, int w, int h, Color contorno, Color fundo){
        super(x, y, z, contorno, fundo);

        this.w = w;
        this.h = h;

    }

    @Override
    public boolean pressed(int x, int y, int z) {
        if (x >= this.getX() && x <= (this.getX() + this.w) && y >= this.getY() && y <= (this.getY() + this.h) && this.getZ() == 1){
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

        g2d.setColor(this.fundo);
        g2d.fillRect(this.getX(), this.getY(), this.w, this.h);

        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(this.contorno);
        g2d.drawRect(this.getX(), this.getY(), this.w, this.h);
    }
}
