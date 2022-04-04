package figures;

import java.awt.*;

public class Triangle extends Figura{
    private Polygon triangulo;
    int[] pontosX = new int[] {0, 0, 0};
    int[] pontosY = new int[] {0, 0, 0};

    public Triangle(int x, int y, int x2, int y2, int x3, int y3, Color contorno, Color fundo)
    {
        super(x, y, contorno, fundo, false);
        this.pontosX[0] = xPoints[0];
        this.pontosY[0] = yPoints[0];


        this.type = "Triang";

        this.pontosX[1] = x2;
        this.pontosY[1] = y2;

        this.pontosX[2] = x3;
        this.pontosY[2] = y3;

        triangulo = new Polygon();

        triangulo.addPoint(pontosX[0], pontosY[0]);
        triangulo.addPoint(pontosX[1], pontosY[1]);
        triangulo.addPoint(pontosX[2], pontosY[2]);

    }
    @Override
    public boolean pressed(int[] coordenada) {
        if(triangulo.contains(coordenada[0], coordenada[1]))
        {
            return true;
        }
        return false;
    }

    @Override
    public int[] getPosition() {
        return new int[] {this.pontosX[0], this.pontosY[0],
                          this.pontosX[1], this.pontosY[1],
                          this.pontosX[2], this.pontosY[2]};
    }

    @Override
    public void setPosition(int[] coordenada) {
        this.pontosX[0] = coordenada[0];
        this.pontosY[0] = coordenada[0];

        this.pontosX[1] = coordenada[1];
        this.pontosY[1] = coordenada[1];

        this.pontosX[2] = coordenada[2];
        this.pontosY[2] = coordenada[2];
    }

    @Override
    public void setSize(int[] tamanho) {
        this.pontosX[0] = tamanho[0];
        this.pontosY[0] = tamanho[0];

        this.pontosX[1] = tamanho[1];
        this.pontosY[1] = tamanho[1];

        this.pontosX[2] = tamanho[2];
        this.pontosY[2] = tamanho[2];
    }

    @Override
    public int[] getSize() {
        return new int[] {this.pontosX[0], this.pontosY[0],
                          this.pontosX[1], this.pontosY[1],
                          this.pontosX[2], this.pontosY[2]};
    }

    @Override
    public void resize(int[] dTamanho) {
        this.pontosX[0] += dTamanho[0];
        this.pontosY[0] += dTamanho[0];

        this.pontosX[1] += dTamanho[1];
        this.pontosY[1] += dTamanho[1];

        this.pontosX[2] += dTamanho[2];
        this.pontosY[2] += dTamanho[2];
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
        }
    }
}
