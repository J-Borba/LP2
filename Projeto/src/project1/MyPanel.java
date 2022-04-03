package project1;

import figures.Ellipse;
import figures.Figura;
import figures.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static java.awt.Toolkit.getDefaultToolkit;
import static javax.swing.JOptionPane.showMessageDialog;

import static java.awt.event.KeyEvent.*;
import static javax.swing.JOptionPane.showOptionDialog;

//Criando o panel
public class MyPanel extends JPanel
{
    ImageIcon icone = new ImageIcon(getDefaultToolkit().getImage(getClass().getResource("logo.png")));

    ArrayList<Figura> figures = new ArrayList<>();
    Figura figuraFoco = null;
    Figura figuraMouse = null;

    Cursor resizeCur = new Cursor(Cursor.SE_RESIZE_CURSOR);
    Cursor defaultCur = new Cursor(Cursor.DEFAULT_CURSOR);
    Cursor moveCur = new Cursor(Cursor.MOVE_CURSOR);
    Cursor handCur = new Cursor(Cursor.HAND_CURSOR);

    int[] coordenada, tamanho, oldSize;

    int clickX, clickY, whereX, whereY;

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

                for (Figura figura : figures) {
                    coordenada = figura.getPosition();
                    tamanho = figura.getSize();

                    if (whereX >= coordenada[0] && whereX <= (coordenada[0] + tamanho[0]) && whereY >= coordenada[1] && whereY <= (coordenada[1] + tamanho[1])) {
                        figuraMouse = figura;
                    }
                }

                if (figuraMouse != null)
                {
                    coordenada = figuraMouse.getPosition();
                    tamanho = figuraMouse.getSize();

                    if (whereX >= coordenada[0] + tamanho[0]-5 && whereX <= coordenada[0] + tamanho[0] &&
                            whereY >= coordenada[1] + tamanho[1]-5 && whereY <= coordenada[1] + tamanho[1])
                    {
                        MyPanel.super.setCursor(resizeCur);
                    }

                    else if(whereX >= coordenada[0] && whereX <= (coordenada[0] + tamanho[0]) &&
                            whereY >= coordenada[1] && whereY <= (coordenada[1] + tamanho[1]))
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
                    coordenada = figuraFoco.getPosition();
                    tamanho = figuraFoco.getSize();

                    int[] dTamanho  = {e.getX()-clickX, e.getY()-clickY};

                    if(clickX >= coordenada[0] + tamanho[0]-5 && clickX <= coordenada[0] + tamanho[0]+5 &&
                            clickY >= coordenada[1] + tamanho[1]-5 && clickY <= coordenada[1] + tamanho[1]+5)
                    {
                        if(figuraFoco.getSize()[0] <= 10)
                        {
                            figuraFoco.setSize(new int[]{11, figuraFoco.getSize()[1]});
                        }
                        else if(figuraFoco.getSize()[1] <= 10)
                        {
                            figuraFoco.setSize(new int[]{figuraFoco.getSize()[0], 11});
                        }
                        else if(figuraFoco.getSize()[0] <= 10  && figuraFoco.getSize()[1] <= 10)
                        {
                            figuraFoco.setSize(new int[]{11, 11});
                        }
                        else
                        {
                            figuraFoco.resize(dTamanho);
                        }
                    }
                    else
                    {
                        figuraFoco.setPosition(dTamanho);
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
                    coordenada = new int[]{e.getX(), e.getY()};

                    if (figure.pressed(coordenada))
                    {
                        figuraFoco = figure;
                        oldSize = figuraFoco.getSize();

                        if(e.getButton() == MouseEvent.BUTTON1)
                        {
                            if(MyPanel.super.getCursor() != resizeCur)
                            {
                                MyPanel.super.setCursor(moveCur);
                            }

                        }
                        else if(e.getButton() == MouseEvent.BUTTON3)
                        {
                            Object[] options = {"Fundo", "Contorno"};

                            int escolha = showOptionDialog(null, "Deseja trocar a cor de qual parte da figura?", "Troca de cor", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icone, options, options[0]);

                            if(escolha == 0)
                            {
                                Color cor = JColorChooser.showDialog(null, "Seletor de cores!", figuraFoco.getColorFundo());
                                figuraFoco.setColorFundo(cor);
                            }
                            else if(escolha == 1)
                            {
                                Color cor = JColorChooser.showDialog(null, "Seletor de cores!", figuraFoco.getColorContorno());
                                figuraFoco.setColorContorno(cor);
                            }


                        }
                    }
                    repaint();
                }

                if(figuraFoco == null){
                    if(e.getButton() == MouseEvent.BUTTON3)
                    {
                        Object[] options = {"Sim", "Nao"};
                        int escolha = showOptionDialog(null, "Deseja mudar a cor da tela toda? ", "Troca de cor", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icone, options, options[0]);

                        if(escolha == 0)
                        {
                            Color cor = JColorChooser.showDialog(null, "Seletor de cores!", MyPanel.super.getBackground());
                            MyPanel.super.setBackground(cor);
                        }
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
                        figuraFoco.setPosition(new int[]{0, -10});
                    }

                    else if(e.getKeyCode() == VK_LEFT)
                    {
                        figuraFoco.setPosition(new int[]{-10, 0});
                    }

                    else if(e.getKeyCode() == VK_DOWN)
                    {
                        figuraFoco.setPosition(new int[]{0, 10});
                    }

                    else if(e.getKeyCode() == VK_RIGHT)
                    {
                        figuraFoco.setPosition(new int[]{10, 0});
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
                        Object[] options = {"Fundo", "Contorno"};
                        int escolha = showOptionDialog(null, "Deseja trocar a cor de qual parte da figura?", "Troca de cor", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icone, options, options[0]);

                        if(escolha == 0)
                        {
                            Color cor = JColorChooser.showDialog(null, "Seletor de cores!", figuraFoco.getColorFundo());
                            figuraFoco.setColorFundo(cor);
                        }
                        else if(escolha == 1)
                        {
                            Color cor = JColorChooser.showDialog(null, "Seletor de cores!", figuraFoco.getColorContorno());
                            figuraFoco.setColorContorno(cor);
                        }
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
                        Object[] options = {"Sim", "Nao"};

                        int escolha = showOptionDialog(null, "Deseja mudar a cor da tela toda? ", "Troca de cor", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icone, options, options[0]);

                        if(escolha == 0)
                        {
                            Color cor = JColorChooser.showDialog(null, "Seletor de cores!", MyPanel.super.getBackground());
                            MyPanel.super.setBackground(cor);
                        }
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
