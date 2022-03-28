package project1;

import figures.Ellipse;
import figures.Figura;
import figures.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import static java.awt.event.KeyEvent.*;

//Criando o panel
public class MyPanel extends JPanel{

    ArrayList<Figura> figures = new ArrayList<>();
    Figura figuraFoco = null;

    Random randomizer = new Random();

    public MyPanel(){
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(figuraFoco != null){
                    figuraFoco.setMotionX(e.getX()-figuraFoco.getW()/2);
                    figuraFoco.setMotionY(e.getY()-figuraFoco.getH()/2);
                    repaint();
                }
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                figuraFoco = null;
                repaint();

                for (Figura figure : figures) {
                    figure.setFocus(false);
                    if (figure.pressed(e.getX(), e.getY())) {
                        figuraFoco = figure;
                    }
                }

                if(figuraFoco != null){
                    for(int j=0; j < figures.size(); j++){
                        if(figuraFoco == figures.get(j)){
                            figures.get(j).setFocus(true);
                            figures.remove(figures.get(j));
                            figures.add(figuraFoco);
                        }
                    }
                }
            }
        });

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if (figuraFoco != null){

                    if(e.getKeyCode() == VK_UP){
                        if(e.isShiftDown()){
                            figuraFoco.resize(0, -5);
                            repaint();
                        }
                        else{
                            figuraFoco.setY(-10);
                            repaint();
                        }
                    }

                    else if(e.getKeyCode() == VK_LEFT){
                        if(e.isShiftDown()){
                            figuraFoco.resize(-5, 0);
                            repaint();
                        }
                        else{
                            figuraFoco.setX(-10);
                            repaint();
                        }
                    }

                    else if(e.getKeyCode() == VK_DOWN){
                        if(e.isShiftDown()){
                            figuraFoco.resize(0, 5);
                            repaint();
                        }
                        else{
                            figuraFoco.setY(10);
                            repaint();
                        }
                    }

                    else if(e.getKeyCode() == VK_RIGHT){
                        if(e.isShiftDown()){
                            figuraFoco.resize(5, 0);
                            repaint();
                        }
                        else{
                            figuraFoco.setX(10);
                            repaint();
                        }
                    }

                    else if(e.getKeyCode() == VK_DELETE){
                        figures.remove(figuraFoco);
                        repaint();
                    }

                    else if(e.getKeyChar() == 'c'){

                        figuraFoco.setColorContorno(new Color(
                                randomizer.nextInt(255),
                                randomizer.nextInt(255),
                                randomizer.nextInt(255)));

                        repaint();
                    }

                    else if(e.getKeyChar() == 'f'){

                        figuraFoco.setColorFundo(new Color(
                                randomizer.nextInt(255),
                                randomizer.nextInt(255),
                                randomizer.nextInt(255)));

                        repaint();
                    }
                }
                else{
                    if(e.getKeyChar() == 'f'){
                        MyPanel.super.setBackground(new Color(randomizer.nextInt(255), randomizer.nextInt(255), randomizer.nextInt(255)));
                    }
                }
                if(e.getKeyChar() == 'r'){
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
                    figures.add(new Rect(x, y,w, h, new Color(rContorno, gContorno, bContorno), new Color(rFundo, gFundo, bFundo)));
                    repaint();
                }

                else if(e.getKeyChar() == 'e'){
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
                    figures.add(new Ellipse(x, y, w, h, new Color(rContorno, gContorno, bContorno), new Color(rFundo, gFundo, bFundo)));
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
