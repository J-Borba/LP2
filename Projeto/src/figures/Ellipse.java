package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ellipse extends Figura{

    private int w, h;

    public Ellipse(int x, int y, int w, int h, Color contorno, Color fundo){
        super(x, y, contorno, fundo, false);
        this.w = w;
        this.h = h;
    }

    @Override
    public int[] getPosition() {
        int[] coordenadas = {this.x, this.y};
        return coordenadas;
    }

    @Override
    public void setPosition(int[] coordenada) {
        this.x += coordenada[0];
        this.y += coordenada[1];
    }

    @Override
    public int[] getSize() {
        int[] tamanho = {this.w, this.h};
        return tamanho;
    }

    @Override
    public void setSize(int[] tamanho) {
        this.w = tamanho[0];
        this.h = tamanho[1];
    }

    @Override
    public boolean pressed(int[] coordenada) {
        return coordenada[0] >= this.x && coordenada[0] <= (this.x + this.w) && coordenada[1] >= this.y && coordenada[1] <= (this.y + this.h);
    }

    @Override
    public int resize(int[] dTamanho){
        if(dTamanho[0] <= w-1 && dTamanho[1] <= h-1){
            w += dTamanho[0];
            h += dTamanho[1];
        }
        else{
            return 1;
        }
        return 0;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        //desenhando fundo
        g2d.setColor(this.fundo);
        g2d.fill(new Ellipse2D.Double(this.x, this.y, this.w, this.h));

        //desenhando contorno
        g2d.setColor(this.contorno);
        g2d.setStroke(new BasicStroke(3));
        g2d.draw(new Ellipse2D.Double(this.x, this.y, this.w, this.h));

        if(this.getFocus()){
            g2d.setColor(Color.red);
            g2d.setStroke(new BasicStroke(2));
            g2d.draw(new Ellipse2D.Double(this.x-2, this.y-2, this.w+4, this.h+4));
            g2d.setColor(Color.gray);
            g2d.setStroke(new BasicStroke(0.5F));
            g2d.draw(new Rectangle(this.x-2, this.y-2, this.w+4, this.h+4));
        }
    }
}
