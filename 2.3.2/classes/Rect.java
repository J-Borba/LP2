package classes;

import java.awt.*;

public class Rect {

    //Criando caracteristicas do retangulo
    public int x, y;
    public int w, h;
    private Color contorno, fundo;

    //Metodo construtor
    public Rect(int x, int y, int w, int h, Color contorno, Color fundo) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.contorno = contorno;
        this.fundo = fundo;
    }
    //Metodo que printa no terminal as informacoes do retangulo
    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
                this.w, this.h, this.x, this.y);

        System.out.printf("Area do retangulo: %d.\n",
                this.area());
    }
    //Metodo que calcula a area do retangulo
    private int area(){
        return this.w * this.h;
    }
    //Metodo que arrasta o retangulo pelo x e y
    public void drag(int dx, int dy){
        this.x = dx;
        this.y = dy;
    }
    //Metodo que pinta o retangulo em um frame/panel
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        //Desenha o fundo do retangulo
        g2d.setColor(fundo);
        g2d.fillRect(this.x, this.y, this.w, this.h);

        //Desenha o contorno do retangulo
        g2d.setStroke(new BasicStroke(4));
        g2d.setColor(contorno);
        g2d.drawRect(this.x, this.y, this.w, this.h);
    }
}