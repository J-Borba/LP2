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
public class MyPanel extends JPanel
{
    ArrayList<Figura> figures = new ArrayList<>();
    Figura figuraFoco = null;
    Figura figuraMouse = null;

    Cursor resizeCur = new Cursor(Cursor.SE_RESIZE_CURSOR);
    Cursor defaultCur = new Cursor(Cursor.DEFAULT_CURSOR);
    Cursor moveCur = new Cursor(Cursor.MOVE_CURSOR);
    Cursor handCur = new Cursor(Cursor.HAND_CURSOR);

    Random randomizer = new Random();

    int clickX, clickY, whereX, whereY;

    int prevW;
    int prevH;

    public MyPanel()
    {
        this.addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent e)
            {
                figuraMouse = null;
                whereX = e.getX();
                whereY = e.getY();

                for (Figura figura : figures)
                {
                    if (whereX >= figura.getX() && whereX <= (figura.getX() + figura.getW()) && whereY >= figura.getY() && whereY <= (figura.getY() + figura.getH()))
                    {
                        figuraMouse = figura;
                    }
                }

                if (figuraMouse != null)
                {
                    if (whereX >= figuraMouse.getX() + figuraMouse.getW()-5 && whereX <= figuraMouse.getX() + figuraMouse.getW() &&
                            whereY >= figuraMouse.getY() + figuraMouse.getH()-5 && whereY <= figuraMouse.getY() + figuraMouse.getH())
                    {
                        MyPanel.super.setCursor(resizeCur);
                    }

                    else if(whereX >= figuraMouse.getX() && whereX <= (figuraMouse.getX() + figuraMouse.getW()) &&
                            whereY >= figuraMouse.getY() && whereY <= (figuraMouse.getY() + figuraMouse.getH()))
                    {
                        if(figuraMouse == figuraFoco)
                        {
                            MyPanel.super.setCursor(moveCur);
                        }
                        else
                        {
                            MyPanel.super.setCursor(handCur);
                        }
                    }

                    else
                    {
                        MyPanel.super.setCursor(defaultCur);
                    }
                }
                else
                {
                    MyPanel.super.setCursor(defaultCur);
                }
            }
            @Override
            public void mouseDragged(MouseEvent e)
            {
                if(figuraFoco != null){
                    if(clickX >= figuraFoco.getX()+figuraFoco.getW()-5 && clickX <= figuraFoco.getX()+figuraFoco.getW()+5 &&
                            clickY >= figuraFoco.getY()+figuraFoco.getH()-5 && clickY <= figuraFoco.getY()+figuraFoco.getH()+5)
                    {
                        if(figuraFoco.resize(e.getX() - clickX, e.getY() - clickY) == 1)
                        {
                            showMessageDialog(MyPanel.this, "Tamanho minimo atingido!");
                            figuraFoco.setSize(prevW, prevH);
                        }

                    }
                    else
                    {
                        figuraFoco.setPosition(e.getX() - clickX, e.getY() - clickY);
                    }
                    repaint();
                    clickX = e.getX();
                    clickY = e.getY();
                }
            }
        });

        this.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                figuraFoco = null;
                clickX = e.getX();
                clickY = e.getY();

                repaint();
                for (Figura figure : figures)
                {
                    figure.setFocus(false);
                    if (figure.pressed(e.getX(), e.getY()))
                    {
                        MyPanel.super.setCursor(moveCur);
                        figuraFoco = figure;
                        prevH = figuraFoco.getH();
                        prevW = figuraFoco.getW();
                    }
                }

                if(figuraFoco != null)
                {
                    for(int j=0; j < figures.size(); j++)
                    {
                        if(figuraFoco == figures.get(j))
                        {
                            figures.get(j).setFocus(true);
                            figures.remove(figures.get(j));
                            figures.add(figuraFoco);
                        }
                    }
                }
            }
        });

        this.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (figuraFoco != null)
                {
                    if(e.getKeyCode() == VK_UP)
                    {
                        figuraFoco.setPosition(0, -10);
                    }

                    else if(e.getKeyCode() == VK_LEFT)
                    {
                        figuraFoco.setPosition(-10, 0);
                    }

                    else if(e.getKeyCode() == VK_DOWN)
                    {
                        figuraFoco.setPosition(0, 10);
                    }

                    else if(e.getKeyCode() == VK_RIGHT)
                    {
                        figuraFoco.setPosition(10, 0);
                    }

                    else if(e.getKeyCode() == VK_DELETE)
                    {
                        figures.remove(figuraFoco);
                        if(figures.size() == 0)
                        {
                            figuraFoco = null;
                        }
                    }


                    else if(e.getKeyChar() == 'c')
                    {
                        figuraFoco.setColorContorno(new Color(
                                randomizer.nextInt(255),
                                randomizer.nextInt(255),
                                randomizer.nextInt(255)));
                    }

                    else if(e.getKeyChar() == 'f')
                    {
                        figuraFoco.setColorFundo(new Color(
                                randomizer.nextInt(255),
                                randomizer.nextInt(255),
                                randomizer.nextInt(255)));
                    }
                repaint();
                }
                if(e.getKeyChar() == 'r')
                {
                    figuraFoco = null;
                    for (Figura figure : figures)
                    {
                        figure.setFocus(false);
                    }
                    repaint();
                    figuraFoco =  new Rect(whereX - 50,
                            whereY - 50,
                            100,
                            100,
                            Color.black,
                            Color.gray);

                    figures.add(figuraFoco);

                    for(int j=0; j < figures.size(); j++)
                    {
                        if(figuraFoco == figures.get(j))
                        {
                            figures.get(j).setFocus(true);
                            figures.remove(figures.get(j));
                            figures.add(figuraFoco);
                        }
                    }
                    repaint();
                }

                else if(e.getKeyChar() == 'e')
                {
                    figuraFoco = null;
                    for (Figura figure : figures)
                    {
                        figure.setFocus(false);
                    }

                    repaint();

                    figuraFoco =  new Ellipse(whereX - 50,
                            whereY - 50,
                            100,
                            100,
                            Color.black,
                            Color.gray);

                    figures.add(figuraFoco);

                    for(int j=0; j < figures.size(); j++)
                    {
                        if(figuraFoco == figures.get(j))
                        {
                            figures.get(j).setFocus(true);
                            figures.remove(figures.get(j));
                            figures.add(figuraFoco);
                        }
                    }
                    repaint();
                }

                else
                {
                    if(e.getKeyChar() == 'b')
                    {
                        MyPanel.super.setBackground(new Color(randomizer.nextInt(255), randomizer.nextInt(255), randomizer.nextInt(255)));
                    }
                }
                if(e.getKeyChar() == 'h')
                {
                    showMessageDialog(MyPanel.this, """
                Instruções de Uso:
                (Aperte H para abrir esta janela a qualquer momento)
                
                Pressionar as teclas:
                
                    "E" para criar uma elipse
                
                    "R" para criar um retangulo
                
                    "DELETE" para deletar a figura selecionada
                
                    "F" para mudar a cor de fundo da figura selecionada
                     
                    "B" para mudar a a cor da tela toda
                
                    "C" para mudar a cor de contorno da figura selecionada
                
                    "Pressione e arraste a borda inferior direita da figura ou segure SHIFT e use as setas do teclado" para mudar o tamanho da figura selecionada
                
                    OBS: PARA SELECIONAR UMA FIGURA, BASTA CLICAR COM O MOUSE EM CIMA DELA""");
                }

                repaint();
            }
        });

        this.setFocusable(true);

        //Definindo tamanho da janela
        this.setPreferredSize(new Dimension(600, 600));

        this.setVisible(true);

    }

    //Metodo que pinta o Panel
    public void paint(Graphics g)
    {
        super.paint(g);

        for(Figura fig: figures)
        {
            fig.paint(g);
        }
    }
}
