import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * @author J-Borba
 */

public class Hello2DApp {
    public static void main (String[] args) {
        MyFrame frame = new MyFrame();
        frame.setVisible(true);

        MyPanel panel = new MyPanel();
        panel.setVisible(true);
    }
}

class MyPanel extends JPanel{
    public MyPanel () {
        this.setPreferredSize(new Dimension(600, 600));
        this.setBackground(Color.black);
        
    }

    public void paint (Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        
        int WIDTH = this.getWidth();
        int HEIGHT = this.getHeight();
        
    //Drawing "LP2"
        g2d.setPaint(Color.white);
        g2d.setFont(new Font("Consolas", Font.BOLD, 150));
        g2d.drawString("LP2", 170, 100);
        
    //Drawing X through Panel
        g2d.setStroke(new BasicStroke(5));
        g2d.setPaint(Color.red);
        g2d.drawLine(0, 0, WIDTH, HEIGHT);
        g2d.drawLine(0, WIDTH, HEIGHT, 0);

    //-=-=-=-=-=-=-=-=Drawing prohibited plate-=-=-=-=-=-=-=-=

    //Inside white part
        g2d.setPaint(Color.white);
        g2d.fillArc(15, 200, 200, 200, 0, 360);

    //Outside Circle
        g2d.setPaint(Color.red);
        g2d.setStroke(new BasicStroke(20));
        g2d.drawArc(15, 200, 200, 200, 0, 360);
        
    //Programming Logo
        g2d.setPaint(Color.black);
        g2d.setFont(new Font("Consolas", Font.BOLD, 110));
        g2d.drawString("</>", 25, 330);

    //Crossing Line
        g2d.setPaint(Color.red);
        g2d.drawLine(45, 370, 180, 235);

    }
}

class MyFrame extends JFrame {

    MyPanel panel;

    public MyFrame () {

        panel = new MyPanel();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("MyFirstLp2Frame");
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
