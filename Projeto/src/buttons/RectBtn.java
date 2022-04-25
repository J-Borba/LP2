package buttons;

import java.awt.*;

public class RectBtn extends Button {
    private int x, y, w, h;
    public RectBtn(int x, int y, int w, int h) {
        super(x, y, w, h);

        this.setType(0);

        this.setColorFundo(new Color(210, 210, 210));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Color fundo = this.getFundo();

        x = this.getX();
        y = this.getY();
        w = this.getW();
        h = this.getH();

        RenderingHints render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(render);

        g2d.setStroke(new BasicStroke(2));

        g2d.setColor(fundo);
        g2d.fillRoundRect(x, y, w, h, 25, 25);

        g2d.setColor(new Color(160, 160, 160));
        g2d.drawRoundRect(x, y, w, h, 25, 25);

        g2d.setColor(Color.black);
        g2d.drawRect(x+w/4, y+h/4, w/2+1, h/2+1);

        g2d.setColor(Color.white);
        g2d.fillRect(x+w/4+2, y+h/4+2, w/2-2, h/2-2);
    }
}
