package figures;

import java.awt.*;

public class Losango extends Figura{
    private Polygon losango;
    int[] pontosX = new int[] {0, 0, 0, 0};
    int[] pontosY = new int[] {0, 0, 0, 0};

    public Losango(int x, int y, int x2, int y2, int x3, int y3, int x4, int y4, Color contorno, Color fundo)
    {
        super(x, y, contorno, fundo, false);
        this.pontosX[0] = xPoints[0];
        this.pontosY[0] = yPoints[0];

        this.type = "Losang";

        this.pontosX[1] = x2;
        this.pontosY[1] = y2;

        this.pontosX[2] = x3;
        this.pontosY[2] = y3;

        this.pontosX[3] = x4;
        this.pontosY[3] = y4;

        losango = new Polygon();

        losango.addPoint(pontosX[0], pontosY[0]);
        losango.addPoint(pontosX[1], pontosY[1]);
        losango.addPoint(pontosX[2], pontosY[2]);
        losango.addPoint(pontosX[3], pontosY[3]);

    }

    @Override
    public boolean corner(int[] coordenada) {
        return coordenada[0] >= losango.xpoints[3]-10 && coordenada[0] <= losango.xpoints[3] &&
                coordenada[1] >= losango.ypoints[3]-10 && coordenada[1] <= losango.ypoints[3];
    }

    @Override
    public boolean pressed(int[] coordenada) {
        return losango.contains(coordenada[0], coordenada[1]);
    }

    @Override
    public int[] getPosition() {
        return new int[] {this.pontosX[0], this.pontosY[0],
                          this.pontosX[1], this.pontosY[1],
                          this.pontosX[2], this.pontosY[2],
                          this.pontosX[3], this.pontosY[3]};
    }

    @Override
    public void setPosition(int[] coordenada) {
        losango.translate(coordenada[0], coordenada[1]);
    }

    @Override
    public int[] getSize() {
        return new int[] {this.pontosX[0], this.pontosY[0],
                          this.pontosX[1], this.pontosY[1],
                          this.pontosX[2], this.pontosY[2],
                          this.pontosX[3], this.pontosY[3]};
    }

    @Override
    public void resize(int[] dTamanho) {
        int[] newPointx, newPointy;

        if(losango.xpoints[2] - losango.xpoints[0] <= 25)
        {
            losango.xpoints[0] += dTamanho[1];

            losango.ypoints[1] += dTamanho[1]*2;

            losango.xpoints[2] -= dTamanho[1];

            losango.ypoints[3] -= dTamanho[1]*2;
        }
        else
        {
            losango.xpoints[0] -= dTamanho[1];

            losango.ypoints[1] -= dTamanho[1]*2;

            losango.xpoints[2] += dTamanho[1];

            losango.ypoints[3] += dTamanho[1]*2;
        }

        newPointx = losango.xpoints;
        newPointy = losango.ypoints;

        losango.reset();

        losango.addPoint(newPointx[0], newPointy[0]);
        losango.addPoint(newPointx[1], newPointy[1]);
        losango.addPoint(newPointx[2], newPointy[2]);
        losango.addPoint(newPointx[3], newPointy[3]);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHints(render);

        g2d.setColor(this.fundo);
        g2d.fill(losango);

        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(this.contorno);
        g2d.draw(losango);

        if(this.getFocus()){
            g2d.setColor(Color.red);
            g2d.setStroke(new BasicStroke(1));
            g2d.draw(losango);
        }
    }
}
