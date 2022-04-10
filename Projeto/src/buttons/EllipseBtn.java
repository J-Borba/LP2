package buttons;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class EllipseBtn extends Button {
    double dx;
    double dy;
    double dw;
    double dh;

    public EllipseBtn(int x, int y, int w, int h) {
        super(x, y, w, h);

        dx = (double) x;
        dy = (double) y;
        dw = (double) w;
        dh = (double) h;


        this.Type = 1;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(render);

        g2d.setStroke(new BasicStroke(2));

        g2d.setColor(new Color(210, 210, 210));
        g2d.fillRoundRect(x, y, w, h, 25, 25);

        g2d.setColor(new Color(160, 160, 160));
        g2d.drawRoundRect(x, y, w, h, 25, 25);

        g2d.setColor(Color.black);
        g2d.draw(new Ellipse2D.Double(x+dw/4, y+dh/4, dw/2, dh/2));

        g2d.setColor(Color.white);
        g2d.fill(new Ellipse2D.Double(x+dw/4+2, y+dh/4+2, dw/2-3.5, dh/2-3.5));
    }
}
