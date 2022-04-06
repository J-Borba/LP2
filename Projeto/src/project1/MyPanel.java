package project1;

import figures.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static java.awt.Toolkit.getDefaultToolkit;

import static java.awt.event.KeyEvent.*;
import static javax.swing.JOptionPane.showOptionDialog;

//Criando o panel
public class MyPanel extends JPanel
{
    ImageIcon icone = new ImageIcon(getDefaultToolkit().getImage(getClass().getResource("logo.png")));

    ArrayList<Figura> figures = new ArrayList<>();
    ArrayList<Figura> focusChangeList = new ArrayList<>();

    Figura figuraFoco = null;
    Figura figuraMouse = null;

    Cursor nResizeCur = new Cursor(Cursor.N_RESIZE_CURSOR);
    Cursor sResizeCur = new Cursor(Cursor.S_RESIZE_CURSOR);
    Cursor eResizeCur = new Cursor(Cursor.E_RESIZE_CURSOR);
    Cursor wResizeCur = new Cursor(Cursor.W_RESIZE_CURSOR);
    Cursor seResizeCur = new Cursor(Cursor.SE_RESIZE_CURSOR);

    Cursor defaultCur = new Cursor(Cursor.DEFAULT_CURSOR);
    Cursor moveCur = new Cursor(Cursor.MOVE_CURSOR);
    Cursor handCur = new Cursor(Cursor.HAND_CURSOR);

    int[] coordenada, tamanho, oldSize;

    int clickX, clickY, whereX, whereY;

    int cont=0;

    public MyPanel()
    {
        this.addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent e)
            {
                whereX = e.getX();
                whereY = e.getY();

                for (Figura figura : figures) {
                    if(figura.getType().equals("Rect") || figura.getType().equals("Ellipse"))
                    {
                        coordenada = figura.getPosition();
                        tamanho = figura.getSize();

                        if (whereX >= coordenada[0] && whereX <= (coordenada[0] + tamanho[0]) &&
                            whereY >= coordenada[1] && whereY <= (coordenada[1] + tamanho[1]))
                        {
                            figuraMouse = figura;
                        }
                    }

                    else if(figura.getType().equals("Triang") || figura.getType().equals("Losang"))
                    {
                        if(figura.pressed(new int[]{whereX, whereY}))
                        {
                            figuraMouse = figura;
                        }
                    }

                }

                if (figuraMouse != null)
                {

                    if(figuraMouse.getType().equals("Rect") || figuraMouse.getType().equals("Ellipse"))
                    {
                        coordenada = figuraMouse.getPosition();
                        tamanho = figuraMouse.getSize();

                        if (figuraMouse.corner(new int[] {whereX, whereY}))
                        {
                            MyPanel.super.setCursor(seResizeCur);
                        }

                        else if(whereX >= coordenada[0] && whereX <= coordenada[0] + tamanho[0] &&
                                whereY >= coordenada[1] && whereY <= coordenada[1] + 5)
                        {
                            MyPanel.super.setCursor(nResizeCur);
                        }

                        else if(whereX >= coordenada[0] && whereX <= coordenada[0] + tamanho[0] &&
                                whereY >= coordenada[1] + tamanho[1] - 5 && whereY <= coordenada[1] + tamanho[1])
                        {
                            MyPanel.super.setCursor(sResizeCur);
                        }

                        else if(whereX >= coordenada[0] && whereX <= coordenada[0] + 5 &&
                                whereY >= coordenada[1] && whereY <= coordenada[1] + tamanho[1])
                        {
                            MyPanel.super.setCursor(eResizeCur);
                        }

                        else if(whereX >= coordenada[0] + tamanho[0] - 5 && whereX <= coordenada[0] + tamanho[0] &&
                                whereY >= coordenada[1] && whereY <= coordenada[1] + tamanho[1])
                        {
                            MyPanel.super.setCursor(wResizeCur);
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
                    else if(figuraMouse.getType().equals("Triang"))
                    {
                        if(figuraMouse.corner(new int[] {whereX, whereY}))
                        {
                            MyPanel.super.setCursor(seResizeCur);
                        }

                        else if(figuraMouse.pressed(new int[]{whereX, whereY}))
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
                    else if(figuraMouse.getType().equals("Losang"))
                    {
                        if(figuraMouse.corner(new int[] {whereX, whereY}))
                        {
                            MyPanel.super.setCursor(sResizeCur);
                        }

                        else if(figuraMouse.pressed(new int[]{whereX, whereY}))
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

                    if(figuraFoco.getType().equals("Rect") || figuraFoco.getType().equals("Ellipse"))
                    {
                        if(MyPanel.super.getCursor() == seResizeCur)
                        {
                            figuraFoco.resize(new int[]{e.getX()-clickX, e.getY()-clickY, 5});
                        }

                        else if(MyPanel.super.getCursor() == nResizeCur)
                        {
                            figuraFoco.resize(new int[]{e.getX()-clickX, e.getY()-clickY, 1});
                        }

                        else if(MyPanel.super.getCursor() == sResizeCur)
                        {
                            figuraFoco.resize(new int[]{e.getX()-clickX, e.getY()-clickY, 2});
                        }

                        else if(MyPanel.super.getCursor() == eResizeCur)
                        {
                            figuraFoco.resize(new int[]{e.getX()-clickX, e.getY()-clickY, 3});
                        }

                        else if(MyPanel.super.getCursor() == wResizeCur)
                        {
                            figuraFoco.resize(new int[]{e.getX()-clickX, e.getY()-clickY, 4});
                        }

                        else if (MyPanel.super.getCursor() == moveCur)
                        {
                            figuraFoco.setPosition(new int[] {e.getX()-clickX, e.getY()-clickY});
                        }
                    }
                    
                    else if(figuraFoco.getType().equals("Triang") || figuraFoco.getType().equals("Losang"))
                    {
                        if(MyPanel.super.getCursor() == moveCur)
                        {
                            figuraFoco.setPosition(new int[] {e.getX()-clickX, e.getY()-clickY});
                        }
                        else if(MyPanel.super.getCursor() == seResizeCur || MyPanel.super.getCursor() == sResizeCur)
                        {
                            figuraFoco.resize(new int[] {e.getX()-clickX, e.getY()-clickY});
                        }
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
                cont = 0;
                if(e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3)
                {
                    figuraFoco = null;
                    repaint();
                }

                clickX = e.getX();
                clickY = e.getY();

                if(e.getButton() == MouseEvent.BUTTON1 && e.isAltDown())
                {
                    Object[] options = {"Ellipse", "Retangulo", "Triangulo", "Losango"};

                    int escolha = showOptionDialog(null, "Deseja criar qual figura?", "Criando Figuras!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icone, options, options[0]);

                    if (escolha == 0)
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

                        focusChangeList.add(figuraFoco);

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
                    else if (escolha == 1)
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

                        focusChangeList.add(figuraFoco);

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
                    else if(escolha == 2)
                    {
                        figuraFoco = null;

                        for (Figura figure : figures)
                        {
                            figure.setFocus(false);
                        }
                        repaint();
                        figuraFoco =  new Triangle(
                                whereX-50,
                                whereY+50,
                                whereX,
                                whereY-75,
                                whereX+50,
                                whereY+50,
                                Color.black,
                                Color.gray);

                        figures.add(figuraFoco);

                        focusChangeList.add(figuraFoco);

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
                    else if(escolha == 3)
                    {
                        figuraFoco = null;

                        for (Figura figure : figures)
                        {
                            figure.setFocus(false);
                        }
                        repaint();
                        figuraFoco =  new Losango(
                                whereX-50,
                                whereY+50,
                                whereX,
                                whereY-50,
                                whereX+50,
                                whereY+50,
                                whereX,
                                whereY+150,
                                Color.black,
                                Color.gray);

                        figures.add(figuraFoco);

                        focusChangeList.add(figuraFoco);

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
                }

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
                            Cursor curCursor = MyPanel.super.getCursor();

                            if(curCursor != seResizeCur && curCursor != nResizeCur && curCursor != sResizeCur &&
                               curCursor != eResizeCur && curCursor != wResizeCur)
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
                        focusChangeList.remove((figuraFoco));
                    }


                    else if(e.getKeyChar() == 'c' || e.getKeyChar() == 'C')
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
                if(figures.size() > 0) {
                    if (e.getKeyCode() == VK_TAB)
                    {
                        if(focusChangeList.size() > 0)
                        {
                            figuraFoco = null;

                            for (Figura figure : figures)
                            {
                                figure.setFocus(false);
                            }

                            figuraFoco = focusChangeList.get(cont);

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
                            if(cont == focusChangeList.size()-1)
                            {
                                cont = 0;
                            }
                            else
                            {
                                cont++;
                            }
                        }
                    }
                }
                if(e.getKeyChar() == 'r' || e.getKeyChar() == 'R')
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

                    focusChangeList.add(figuraFoco);

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

                else if(e.getKeyChar() == 'e' || e.getKeyChar() == 'E')
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

                    focusChangeList.add(figuraFoco);

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

                else if(e.getKeyChar() == 't' || e.getKeyChar() == 'T')
                {
                    figuraFoco = null;

                    for (Figura figure : figures)
                    {
                        figure.setFocus(false);
                    }
                    repaint();
                    figuraFoco =  new Triangle(
                            whereX-50,
                            whereY+50,
                            whereX,
                            whereY-75,
                            whereX+50,
                            whereY+50,
                            Color.black,
                            Color.gray);

                    figures.add(figuraFoco);

                    focusChangeList.add(figuraFoco);

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
                else if(e.getKeyChar() == 'l' || e.getKeyChar() == 'L')
                {
                    figuraFoco = null;

                    for (Figura figure : figures)
                    {
                        figure.setFocus(false);
                    }
                    repaint();
                    figuraFoco =  new Losango(
                            whereX-50,
                            whereY+50,
                            whereX,
                            whereY-50,
                            whereX+50,
                            whereY+50,
                            whereX,
                            whereY+150,
                            Color.black,
                            Color.gray);

                    figures.add(figuraFoco);

                    focusChangeList.add(figuraFoco);

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
                    if(e.getKeyChar() == 'b' || e.getKeyChar() == 'B')
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
                repaint();
            }
        });

        setFocusTraversalKeysEnabled(false);

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
