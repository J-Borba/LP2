package figures;

import java.awt.*;

public class Rect extends Figura{

    private int w, h;

    public Rect(int x, int y, int w, int h, Color contorno, Color fundo){
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
    public void resize(int[] dTamanho){
        w += dTamanho[0];
        h += dTamanho[1];
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(this.fundo);
        g2d.fillRect(this.x, this.y, this.w, this.h);

        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(this.contorno);
        g2d.drawRect(this.x, this.y, this.w, this.h);

        if(this.getFocus()){
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(Color.red);
            g2d.drawRect(this.x-2, this.y-2, this.w+5, this.h+5);
        }
    }
}
