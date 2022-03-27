package project1;

import figures.Ellipse;
import figures.Figura;
import figures.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

//Criando o panel
public class MyPanel extends JPanel {

    ArrayList<Figura> figures = new ArrayList<>();
    Figura foco = null;

    Random randomizer = new Random();
    int cont = 0;

    public MyPanel(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                foco = null;

                for(Figura fig : figures){
                    if(fig.pressed(e.getX(), e.getY(), fig.getZ())){
                        foco = fig;

                        System.out.printf("Clicou %d!\n", cont);
                        cont++;
                    }
                }
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                int x = randomizer.nextInt(350);
                int y = randomizer.nextInt(350);
                int z = 1;

                int w = randomizer.nextInt(250);
                int h = randomizer.nextInt(250);

                int rFundo = randomizer.nextInt(255);
                int gFundo = randomizer.nextInt(255);
                int bFundo = randomizer.nextInt(255);

                int rContorno = randomizer.nextInt(255);
                int gContorno = randomizer.nextInt(255);
                int bContorno = randomizer.nextInt(255);

                if(e.getKeyChar() == 'r'){
                    figures.add(new Rect(x, y, z, w, h, new Color(rContorno, gContorno, bContorno), new Color(rFundo, gFundo, bFundo)));
                    repaint();
                }

                else if(e.getKeyChar() == 'e'){
                    figures.add(new Ellipse(x, y, z, w, h, new Color(rContorno, gContorno, bContorno), new Color(rFundo, gFundo, bFundo)));
                    repaint();
                }

                else if(e.getKeyChar() == 'c'){
                    figures.removeAll(figures);
                    repaint();
                }
            }
        });

        this.setFocusable(true);
        this.requestFocusInWindow();

        //Definindo tamanho da janela
        this.setPreferredSize(new Dimension(600, 600));

        this.setVisible(true);

    }

    //Metodo que pinta o Panel
    public void paint(Graphics g){
        super.paint(g);

        for(Figura fig: figures){
            fig.paint(g);
        }
    }
}
