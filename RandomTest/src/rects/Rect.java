package rects;

import java.awt.*;

public class Rect {
    int x, y, w, h;

    public Rect(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.red);
        g2d.fillRect(this.x, this.y, this.w, this.h);
    }
}
