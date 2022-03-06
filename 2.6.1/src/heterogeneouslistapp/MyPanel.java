package heterogeneouslistapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import figures.*;

//Criando o panel
public class MyPanel extends JPanel {

    ArrayList<Figura> figures = new ArrayList<>();

    Random randomizer = new Random();

    public MyPanel(){
        this.addKeyListener(
                new KeyAdapter() {
                    public void keyPressed(KeyEvent e) {

                        int x = randomizer.nextInt(350);
                        int y = randomizer.nextInt(350);
                        
                        int w = randomizer.nextInt(250);
                        int h = randomizer.nextInt(250);

                        int rFundo = randomizer.nextInt(255);
                        int gFundo = randomizer.nextInt(255);
                        int bFundo = randomizer.nextInt(255);

                        int rContorno = randomizer.nextInt(255);
                        int gContorno = randomizer.nextInt(255);
                        int bContorno = randomizer.nextInt(255);

                        if(e.getKeyChar() == 'r'){

                            figures.add(new Rect(x, y, w, h, new Color(rContorno, gContorno, bContorno), new Color(rFundo, gFundo, bFundo)));
                            repaint();
                        }

                        else if(e.getKeyChar() == 'e'){
                            figures.add(new Ellipse(x, y, w, h, new Color(rContorno, gContorno, bContorno), new Color(rFundo, gFundo, bFundo)));
                            repaint();
                        }

                        else if(e.getKeyChar() == 't'){

                            int[] xPoints = {

                                        randomizer.nextInt(500),
                                        randomizer.nextInt(500),
                                        randomizer.nextInt(500)

                                    };

                            int[] yPoints = {

                                    randomizer.nextInt(500),
                                    randomizer.nextInt(500),
                                    randomizer.nextInt(500)

                                    };

                            figures.add(new Triangle(xPoints, yPoints, 3, new Color(rContorno, gContorno, bContorno), new Color(rFundo, gFundo, bFundo)));
                            repaint();
                        }

                        else if(e.getKeyChar() == 'c'){
                            figures.removeAll(figures);
                            repaint();
                        }
                    }
                }
        );

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
