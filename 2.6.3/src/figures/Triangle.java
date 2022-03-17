package figures;

import java.awt.*;

public class Triangle extends Figura{

    int[] xPoints;
    int[] yPoints;
    int vertices;

    public Triangle(int[] xPoints, int[] yPoints, int vertices, Color contorno, Color fundo){
        super(contorno, fundo);

        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.vertices = vertices;

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(this.fundo);
        g2d.fillPolygon(this.xPoints, this.yPoints, this.vertices);

        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(this.contorno);
        g2d.drawPolygon(this.xPoints, this.yPoints, this.vertices);
    }
}
