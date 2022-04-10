package buttons;

import java.awt.*;

public class LosangBtn extends Button{
    public LosangBtn(int x, int y, int w, int h) {
        super(x, y, w, h);

        this.Type = 3;

        this.setColor(new Color(210, 210, 210));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(render);

        g2d.setStroke(new BasicStroke(2));

        g2d.setColor(fundo);
        g2d.fillRoundRect(x, y, w, h, 25, 25);

        g2d.setColor(new Color(160, 160, 160));
        g2d.drawRoundRect(x, y, w, h, 25, 25);

        g2d.setColor(Color.black);
        Polygon losang = new Polygon();

        losang.addPoint(x+w/2, 250);
        losang.addPoint(25, y+h/2);
        losang.addPoint(x+w/2, y+10);
        losang.addPoint(45, y+h/2);

        g2d.setStroke(new BasicStroke(5));
        g2d.draw(losang);

        g2d.setColor(Color.white);
        g2d.fill(losang);
    }
}