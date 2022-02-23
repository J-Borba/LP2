package rectellipse2d;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class RectEllipseApp {

    public static void main (String[] args) {

        //Criando um frame
        MyFrame frame = new MyFrame();
        frame.setVisible(true);

        //Criando um panel
        MyPanel panel = new MyPanel();
        panel.setVisible(true);
    }
}

//Criando o frame
class MyFrame extends JFrame {

    //Criando um panel para o frame
    MyPanel panel;

    //Metodo construtor
    public MyFrame() {

        //Inicializando o panel
        panel = new MyPanel();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //Fazendo o programa fechar ao fechar o frame
        this.setTitle("RectFrame"); //Mudando o título do frame
        this.add(panel); //Adicionando o panel no frame
        this.pack(); //Fazendo o frame ter o mesmo tamanho que o panel
        this.setLocationRelativeTo(null); //Fazendo o frame inicializar no meio da tela
        this.setVisible(true); //Fazendo o frame ficar visivel

    }
}

//Criando o panel
class MyPanel extends JPanel {

    //Inicializando os retangulos dentro do panel
    Rect r1, r2, r3;
    Ellipse e1, e2, e3;

    //Metodo construtor
    public MyPanel(){

        //Definindo tamanho da janela
        this.setPreferredSize(new Dimension(600, 600));

        //Definindo cor de fundo do panel
        this.setBackground(Color.black);

        //Inicializando os retangulos de fato desenhados
        this.r1 = new Rect(15, 10, 250, 100, Color.white, Color.blue);
        this.r2 = new Rect(15, 120, 100, 200, Color.blue, Color.white);
        this.r3 = new Rect(15, 330, 210, 100, Color.red, Color.green);

        //Inicializando as ellipses de fato desenhadas
        this.e1 = new Ellipse(15, 15, 100, 100, Color.red, Color.white);
        this.e2 = new Ellipse(15, 15, 100, 50, Color.blue, Color.green);
        this.e3 = new Ellipse(15, 15, 50, 100, Color.darkGray, Color.magenta);
    }

    //Metodo que pinta o Panel
    public void paint(Graphics g){
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        int WIDTH = this.getWidth();
        int HEIGHT = this.getHeight();

        //Pintando os retangulos no Panel
        this.r1.paint(g);
        this.r1.drag((WIDTH/2) - (r1.w/2), r1.y);

        this.r2.paint(g);
        this.r2.drag((WIDTH/2) - (r2.w/2), r2.y);

        this.r3.paint(g);
        this.r3.drag((WIDTH/2) - (r3.w-50), r3.y);

        //Pintando as ellipses
        this.e1.paint(g);

        this.e2.paint(g);
        this.e2.drag(e2.x, (HEIGHT/2)-25);

        this.e3.paint(g);
        this.e3.drag(e3.x, HEIGHT-e3.h-15);
    }
}

//Criando classe retangulo
class Rect {

    //Criando caracteristicas do retangulo
    int x, y;
    int w, h;
    Color contorno, fundo;

    //Metodo construtor
    Rect(int x, int y, int w, int h, Color contorno, Color fundo) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.contorno = contorno;
        this.fundo = fundo;
    }
    //Metodo que printa no terminal as informacoes do retangulo
    void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
                this.w, this.h, this.x, this.y);

        System.out.printf("Area do retangulo: %d.\n",
                this.area());
    }
    //Metodo que calcula a area do retangulo
    int area(){
        return this.w * this.h;
    }
    //Metodo que arrasta o retangulo pelo x e y
    void drag(int dx, int dy){
        this.x = dx;
        this.y = dy;
    }
    //Metodo que pinta o retangulo em um frame/panel
    void paint(Graphics g){
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

//Criando classe elipse
class Ellipse {
    int x, y;
    int w, h;
    Color contorno, fundo;

    Ellipse (int x, int y, int w, int h, Color contorno, Color fundo) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.contorno = contorno;
        this.fundo = fundo;
    }

    void print () {
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
                this.w, this.h, this.x, this.y);
    }

    void drag(int dx, int dy){
        this.x = dx;
        this.y = dy;
    }

    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        //desenhando fundo
        g2d.setColor(this.fundo);
        g2d.setStroke(new BasicStroke(5));
        g2d.fill(new Ellipse2D.Double(this.x,this.y, this.w,this.h));

        //desenhando contorno
        g2d.setColor(this.contorno);
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
    }

}
