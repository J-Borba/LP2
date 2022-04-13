package figures;

import java.awt.*;

public class Triangle extends Figura{
    private Polygon triangulo;

    private int w, h;

    int[] pontosX = new int[] {0, 0, 0};
    int[] pontosY = new int[] {0, 0, 0};

    public Triangle(int x, int y, int w, int h, Color contorno, Color fundo)
    {
        super(x, y, contorno, fundo, false);
        this.pontosX[0] = xPoints[0];
        this.pontosY[0] = yPoints[0]+h;

        this.type = "Triang";

        this.w = w;
        this.h = h;

        this.pontosX[1] = xPoints[0]+w/2;
        this.pontosY[1] = yPoints[0];

        this.pontosX[2] = xPoints[0] + w;
        this.pontosY[2] = yPoints[0] + h;

        triangulo = new Polygon();

        triangulo.addPoint(pontosX[0], pontosY[0]);
        triangulo.addPoint(pontosX[1], pontosY[1]);
        triangulo.addPoint(pontosX[2], pontosY[2]);

    }


    @Override
    public int[] getPosition() {
        return new int[]{this.xPoints[0], this.yPoints[0]};
    }

    @Override
    public void setPosition(int[] coordenada) {
        this.xPoints[0] += coordenada[0];
        this.yPoints[0] += coordenada[1];
        triangulo.translate(coordenada[0], coordenada[1]);
    }

    @Override
    public int[] getSize() {
        return new int[]{this.w, this.h};
    }

    @Override
    public boolean corner(int[] coordenada) {
        return coordenada[0] >= this.xPoints[0] + this.w-5 && coordenada[0] <= this.xPoints[0] + this.w &&
                coordenada[1] >= this.yPoints[0] + this.h-5 && coordenada[1] <= this.yPoints[0] + this.h;
    }

    @Override
    public boolean pressed(int[] coordenada) {
        return coordenada[0] >= this.xPoints[0] && coordenada[0] <= (this.xPoints[0] + this.w) &&
                coordenada[1] >= this.yPoints[0] && coordenada[1] <= (this.yPoints[0] + this.h);
    }

    @Override
    public void resize(int[] dTamanho){
        if(dTamanho[2] == 5) //SE
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

        else if(dTamanho[2] == 4) //W
        {
            this.w += dTamanho[0]*2;
            this.xPoints[0] -= dTamanho[0];

            if(this.w <= 10)
            {
                this.w -= dTamanho[0]*2;
                this.xPoints[0] += dTamanho[0];
            }
        }

        else if(dTamanho[2] == 3) // E
        {
            this.w -= dTamanho[0]*2;
            this.xPoints[0] += dTamanho[0];

            if(this.w <= 10)
            {
                this.w += dTamanho[0]*2;
                this.xPoints[0] -= dTamanho[0];
            }
        }

        else if(dTamanho[2] == 2) //S
        {
            this.h += dTamanho[1]*2;
            this.yPoints[0] -= dTamanho[1];

            if(this.h <= 10)
            {
                this.h -= dTamanho[1]*2;
                this.yPoints[0] += dTamanho[1];
            }
        }

        else if(dTamanho[2] == 1) //N
        {
            this.h -= dTamanho[1]*2;
            this.yPoints[0] += dTamanho[1];

            if(this.h <= 10)
            {
                this.h += dTamanho[1]*2;
                this.yPoints[0] -= dTamanho[1];
            }
        }

        triangulo.reset();

        this.pontosX[0] = xPoints[0];
        this.pontosY[0] = yPoints[0]+h;

        this.pontosX[1] = xPoints[0]+w/2;
        this.pontosY[1] = yPoints[0];

        this.pontosX[2] = xPoints[0] + w;
        this.pontosY[2] = yPoints[0] + h;

        triangulo.addPoint(pontosX[0], pontosY[0]);
        triangulo.addPoint(pontosX[1], pontosY[1]);
        triangulo.addPoint(pontosX[2], pontosY[2]);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHints(render);

        g2d.setColor(this.fundo);
        g2d.fill(triangulo);

        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(this.contorno);
        g2d.draw(triangulo);

        if(this.getFocus()){
            g2d.setColor(Color.red);
            g2d.setStroke(new BasicStroke(1));
            g2d.draw(triangulo);

            g2d.setStroke(new BasicStroke(0.5F));
            g2d.setColor(this.fundo);
            g2d.drawRect(this.xPoints[0], this.yPoints[0], w, h);
        }
    }
}
