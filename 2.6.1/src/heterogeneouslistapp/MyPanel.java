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

                        int x = randomizer.nextInt(500);
                        int y = randomizer.nextInt(500);
                        
                        int w = randomizer.nextInt(150);
                        int h = randomizer.nextInt(150);

                        int rFundo = randomizer.nextInt(255);
                        int gFundo = randomizer.nextInt(255);
                        int bFundo = randomizer.nextInt(255);

                        int rContorno = randomizer.nextInt(255);
                        int gContorno = randomizer.nextInt(255);
                        int bContorno = randomizer.nextInt(255);

                        if(e.getKeyChar() == 'r'){

                            figures.add(new Rect(
                                            //Tamanho do retangulo
                                            x, y, w, h,

                                            //Cor de contorno
                                            new Color(rContorno, gContorno, bContorno),

                                            //Cor de fundo
                                            new Color(rFundo, gFundo, bFundo)

                                    )

                            );
                            repaint();
                        }

                        else if(e.getKeyChar() == 'e'){
                            figures.add(new Ellipse(
                                            //Tamanho da ellipse
                                            x, y, w, h,

                                            //Cor de contorno
                                            new Color(rContorno, gContorno, bContorno),

                                            //Cor de fundo
                                            new Color(rFundo, gFundo, bFundo)

                                    )
                            );
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
