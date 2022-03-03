package mainfile;

import javax.swing.*;
import java.awt.*;
import figures.*;

//Criando o panel
public class MyPanel extends JPanel {

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
