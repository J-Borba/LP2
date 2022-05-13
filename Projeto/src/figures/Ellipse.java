package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Figura{
    private int w, h;

    public Ellipse(int x, int y, int w, int h, Color contorno, Color fundo){
        super(x, y, contorno, fundo);
        this.w = w;
        this.h = h;
        this.type = "Ellipse";
    }
    @Override
    public int[] getPosition() {
        return new int[]{this.xPoints[0], this.yPoints[0]};
    }
    @Override
    public void setPosition(int[] coordenada) {
        this.xPoints[0] += coordenada[0];
        this.yPoints[0] += coordenada[1];
    }
    @Override
    public int[] getSize() {
        int[] tamanho = {this.w, this.h};
        return tamanho;
    }
    @Override
    public boolean corner(int[] coordenada) {
        return coordenada[0] >= this.xPoints[0] + this.w-5 && coordenada[0] <= this.xPoints[0] + this.w &&
                coordenada[1] >= this.yPoints[0] + this.h-5 && coordenada[1] <= this.yPoints[0] + this.h;
    }
    @Override
    public boolean clicked(int clickX, int clickY) {
        return clickX >= this.xPoints[0] && clickX <= (this.xPoints[0] + this.w) &&
               clickY >= this.yPoints[0] && clickY <= (this.yPoints[0] + this.h);
    }
    @Override
    public void resize(int[] dTamanho, int tipo)
    {
        if(tipo == 5) //SE
        {
            this.w += dTamanho[0]*2;
            this.h += dTamanho[0]*2;
            this.xPoints[0] -= dTamanho[0];
            this.yPoints[0] -= dTamanho[0];

            if(this.w <= 10 || this.h <= 10)
            {
                this.w -= dTamanho[0]*2;
                this.xPoints[0] += dTamanho[0];

                this.h -= dTamanho[0]*2;
                this.yPoints[0] += dTamanho[0];
            }
        }
        else if(tipo == 4) //W
        {
            this.w += dTamanho[0]*2;
            this.xPoints[0] -= dTamanho[0];

            if(this.w <= 10)
            {
                this.w -= dTamanho[0]*2;
                this.xPoints[0] += dTamanho[0];
            }
        }
        else if(tipo == 3) // E
        {
            this.w -= dTamanho[0]*2;
            this.xPoints[0] += dTamanho[0];

            if(this.w <= 10)
            {
                this.w += dTamanho[0]*2;
                this.xPoints[0] -= dTamanho[0];
            }
        }
        else if(tipo == 2) //S
        {
            this.h += dTamanho[1]*2;
            this.yPoints[0] -= dTamanho[1];

            if(this.h <= 10)
            {
                this.h -= dTamanho[1]*2;
                this.yPoints[0] += dTamanho[1];
            }
        }
        else if(tipo == 1) //N
        {
            this.h -= dTamanho[1]*2;
            this.yPoints[0] += dTamanho[1];

            if(this.h <= 10)
            {
                this.h += dTamanho[1]*2;
                this.yPoints[0] -= dTamanho[1];
            }
        }
    }
    @Override
    public void paint(Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(render);

        //desenhando fundo
        g2d.setColor(this.getColorFundo());
        g2d.fill(new Ellipse2D.Double(this.xPoints[0], this.yPoints[0], this.w, this.h));

        //desenhando contorno
        g2d.setColor(this.getColorContorno());
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(new Ellipse2D.Double(this.xPoints[0], this.yPoints[0], this.w, this.h));

        if(focused)
        {
            g2d.setColor(Color.red);
            g2d.setStroke(new BasicStroke(1));
            g2d.draw(new Ellipse2D.Double(this.xPoints[0]-2, this.yPoints[0]-2, this.w+4, this.h+4));
            g2d.setColor(this.getColorFundo());
            g2d.setStroke(new BasicStroke(0.5F));
            g2d.drawRect(this.xPoints[0]-2, this.yPoints[0]-2, this.w+4, this.h+4);
        }
    }
}
