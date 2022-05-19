package buttons;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import interfaces.IVisible;

import javax.imageio.ImageIO;
import javax.swing.*;

import static java.awt.Toolkit.getDefaultToolkit;

public class Button implements IVisible {
    private int x, y, w, h;
    private final int Type;
    private Color fundo;
    private BufferedImage img, cor;
    public Button(int x, int y, int w, int h, int Type)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.Type = Type;
        this.fundo = new Color(210, 210, 210);
    }
    public void paint(Graphics g, boolean focused) {
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

        if(focused)
        {
            g2d.setColor(new Color(120, 120, 120));
            g2d.fillRoundRect(x, y, w, h, 25, 25);
        }

        if(this.Type == 0)//Rect
        {
            g2d.setColor(Color.black);
            g2d.drawRect(x+w/4, y+h/4, w/2+1, h/2+1);

            g2d.setColor(Color.white);
            g2d.fillRect(x+w/4+2, y+h/4+2, w/2-2, h/2-2);
        }
        else if(this.Type == 1)//Ellipse
        {
            g2d.setColor(Color.black);
            g2d.draw(new Ellipse2D.Double(x+w/4.0 , y+h/4.0, w/2.0, h/2.0));

            g2d.setColor(Color.white);
            g2d.fill(new Ellipse2D.Double(x+w/4.0+2, y+h/4.0+2, w/2.0-3.5, h/2.0-3.5));
        }
        else if(this.Type == 2)//Triang
        {
            g2d.setColor(Color.black);
            Polygon triangulo = new Polygon();

            triangulo.addPoint(x + 15, y + 35);
            triangulo.addPoint(x+w/2, y+15);
            triangulo.addPoint(x + 35, y + 35);

            g2d.setStroke(new BasicStroke(5));
            g2d.draw(triangulo);

            g2d.setColor(Color.white);
            g2d.fill(triangulo);
        }
        else if(this.Type == 3)//Losang
        {
            g2d.setColor(Color.black);
            Polygon losang = new Polygon();

            losang.addPoint(x+w/2, y + 40);
            losang.addPoint(x + 15, y+h/2);
            losang.addPoint(x+w/2, y+10);
            losang.addPoint(x + 35, y+h/2);

            g2d.setStroke(new BasicStroke(5));
            g2d.draw(losang);

            g2d.setColor(Color.white);
            g2d.fill(losang);
        }
        else if(this.Type == 4)//DEL
        {
            try
            {
                img = ImageIO.read(getClass().getResourceAsStream("trash.png"));
            } catch (IOException e) {
                System.out.println("Error: Image not found! ");
            }
            g2d.drawImage(img, this.x+5, this.y+8, 40, 40, null);
        }
        else if(this.Type == 5)//COR
        {
            try
            {
                cor = ImageIO.read(getClass().getResourceAsStream("color.png"));
            } catch (IOException e) {
                System.out.println("Error: Image not found! ");
            }
            g2d.drawImage(cor, this.x, this.y+5, 50, 40, null);
        }
        else if(this.Type == 6) //GUI
        {
            g2d.setColor(Color.black);
            g2d.setFont(new Font("Consolas", Font.PLAIN, 20));
            g2d.drawString("Menu", x+18, y+20);
        }
    }
    protected int getX(){return this.x;}
    protected int getY(){return this.y;}

    protected int getW(){return this.w;}
    protected int getH(){return this.h;}

    public int getType(){return this.Type;}

    protected Color getFundo(){return this.fundo;}

    @Override
    public boolean clicked(int cx, int cy) {
        return cx >= x && cx <= x+w && cy >= y && cy <= y+h;
    }

    @Override
    public void setColorFundo(Color fundo){this.fundo = fundo;}
}
