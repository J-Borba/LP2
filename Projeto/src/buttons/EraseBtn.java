package buttons;

import java.awt.*;

public class EraseBtn extends Button{
    public EraseBtn(int x, int y, int w, int h) {
        super(x, y, w, h);

        this.Type = 4;

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
        g2d.setFont(new Font("Consolas", Font.BOLD, 20));
        g2d.drawString("DEL", x+10, y+30);
    }
}
