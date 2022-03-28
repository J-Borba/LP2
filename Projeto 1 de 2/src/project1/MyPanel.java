package project1;

import figures.Ellipse;
import figures.Figura;
import figures.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import static javax.swing.JOptionPane.showMessageDialog;

import static java.awt.event.KeyEvent.*;

//Criando o panel
public class MyPanel extends JPanel{

    ArrayList<Figura> figures = new ArrayList<>();
    Figura figuraFoco = null;

    Random randomizer = new Random();

    int clickX;
    int clickY;

    public MyPanel(){
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(figuraFoco != null){
                    if(clickX >= figuraFoco.getX()+figuraFoco.getW()-15 && clickX <= figuraFoco.getX()+figuraFoco.getW()+10 &&
                            clickY >= figuraFoco.getY()+figuraFoco.getH()-15 && clickY <= figuraFoco.getY()+figuraFoco.getH()+10)
                    {
                        figuraFoco.resize(e.getX() - clickX, e.getY() - clickY);
                    }
                    else
                    {
                        figuraFoco.setX(e.getX() - clickX);
                        figuraFoco.setY(e.getY() - clickY);
                    }
                    repaint();
                    clickX = e.getX();
                    clickY = e.getY();
                }
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                figuraFoco = null;
                clickX = e.getX();
                clickY = e.getY();
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
                        if(figures.size() == 0){
                            figuraFoco = null;
                        }
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
                if(e.getKeyChar() == 'h'){
                    showMessageDialog(MyPanel.this, "Instruções de Uso:\n(Aperte H para abrir esta janela a qualquer momento):\n" +
                            "\nPressionar as teclas:\n" +
                            "\n" +
                            "\"E\" para criar uma elipse\n" +
                            "\n" +
                            "\"R\" para criar um retangulo\n" +
                            "\n" +
                            "\"DELETE\" para deletar a figura selecionada\n" +
                            "\n" +
                            "\"F\" para mudar a cor de fundo da figura selecionada. Caso nao tenha nenhuma figura em foco, mudara a cor da tela toda \n" +
                            "\n" +
                            "\"C\" para mudar a cor de contorno da figura selecionada\n" +
                            "\n" +
                            "\"Pressione e arraste a borda inferior direita da figura ou segure SHIFT e use as setas do teclado\" para mudar o tamanho da figura selecionada\n" +
                            "\n" +
                            "OBS: PARA SELECIONAR UMA FIGURA, BASTA CLICAR COM O MOUSE EM CIMA DELA");
                }
                else if(e.getKeyChar() == 'r'){
                    figures.add(new Rect(MyPanel.super.getWidth()/2 - 50,
                                         MyPanel.super.getHeight()/2 - 50,
                                         100,
                                         100,
                                            Color.black,
                                            Color.gray));
                    repaint();
                }

                else if(e.getKeyChar() == 'e'){
                    figures.add(new Ellipse(MyPanel.super.getWidth()/2 - 50,
                            MyPanel.super.getHeight()/2 - 50,
                            100,
                            100,
                            Color.black,
                            Color.gray));
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
