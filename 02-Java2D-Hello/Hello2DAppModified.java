import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Hello2DAppModified {
    public static void main (String[] args) {
        MyFrame frame = new MyFrame();
        frame.setVisible(true);

        MyPanel panel = new MyPanel();
        panel.setVisible(true);
    }
}

class MyPanel extends JPanel implements ActionListener{
    
    Image animation;
    Image background;
    Timer timer;

    int xVel = 5;
    int yVel = 4;

    int x = 0;
    int y = 0;


    public MyPanel() {

        this.setPreferredSize(new Dimension(1366, 768));
        background = new ImageIcon("02-Java2D-Hello\\background.jpg").getImage();
        animation = new ImageIcon("02-Java2D-Hello\\Animation.png").getImage();
        timer = new Timer(10, this);
        timer.start();

    }
    
    public void paint (Graphics g) {
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;

        int WIDTH = this.getWidth();
        int HEIGHT = this.getHeight();

    //Putting an animated image
        g2d.drawImage(background, 0, 0, null); 
        g2d.drawImage(animation, x, y, null);

    //Drawing "LP2"
        g2d.setPaint(Color.white);
        g2d.setFont(new Font("Consolas", Font.BOLD, 150));
        g2d.drawString("LP2", (WIDTH/2) - 130, 100);
        
    //Drawing X through Panel
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(0, 0, WIDTH, HEIGHT);
        g2d.drawLine(0, HEIGHT, WIDTH, 0);

    //-=-=-=-=-=-=-=-=Drawing prohibited plate-=-=-=-=-=-=-=-=

    //Inside white part
        g2d.fillArc(15, (HEIGHT/2)-100, 200, 200, 0, 360);

    //Outside Circle
        g2d.setPaint(Color.red);
        g2d.setStroke(new BasicStroke(20));
        g2d.drawArc(15, (HEIGHT/2)-100, 200, 200, 0, 360);
        
    //Programming Logo
        g2d.setPaint(Color.black);
        g2d.setFont(new Font("Consolas", Font.BOLD, 110));
        g2d.drawString("</>", 25, (HEIGHT/2)+30);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        int WIDTH = this.getWidth();
        int HEIGHT = this.getHeight();
        
        int animationWidth = animation.getWidth(null);
        int animationHeight = animation.getHeight(null);

        if(x >= WIDTH - animationWidth || x < 0){
            xVel *= -1;
        }
        if(y >= HEIGHT - animationHeight || y < 0){
            yVel *= -1;
        }

        x = x + xVel;
        y = y + yVel;
        repaint();
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
