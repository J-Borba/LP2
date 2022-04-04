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
        if(dTamanho[2] == 5) //SE
        {
            this.w += dTamanho[0]*2;
            this.h += dTamanho[0]*2;
            this.x -= dTamanho[0];
            this.y -= dTamanho[0];

            if(this.w <= 10 || this.h <= 10)
            {
                this.w -= dTamanho[0]*2;
                this.x += dTamanho[0];

                this.h -= dTamanho[0]*2;
                this.y += dTamanho[0];
            }
        }

        else if(dTamanho[2] == 4) //W
        {
            this.w += dTamanho[0]*2;
            this.x -= dTamanho[0];

            if(this.w <= 10)
            {
                this.w -= dTamanho[0]*2;
                this.x += dTamanho[0];
            }
        }

        else if(dTamanho[2] == 3) // E
        {
            this.w -= dTamanho[0]*2;
            this.x += dTamanho[0];

            if(this.w <= 10)
            {
                this.w += dTamanho[0]*2;
                this.x -= dTamanho[0];
            }
        }

        else if(dTamanho[2] == 2) //S
        {
            this.h += dTamanho[1]*2;
            this.y -= dTamanho[1];

            if(this.h <= 10)
            {
                this.h -= dTamanho[1]*2;
                this.y += dTamanho[1];
            }
        }

        else if(dTamanho[2] == 1) //N
        {
            this.h -= dTamanho[1]*2;
            this.y += dTamanho[1];

            if(this.h <= 10)
            {
                this.h += dTamanho[1]*2;
                this.y -= dTamanho[1];
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHints(render);

        g2d.setColor(this.fundo);
        g2d.fillRect(this.x, this.y, this.w, this.h);

        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(this.contorno);
        g2d.drawRect(this.x, this.y, this.w, this.h);

        if(this.getFocus()){
            g2d.setColor(Color.red);
            g2d.setStroke(new BasicStroke(1));
            g2d.drawRect(this.x-2, this.y-2, this.w+4, this.h+4);
        }
    }
}
