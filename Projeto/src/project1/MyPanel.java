package project1;

import figures.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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

                else if(e.getKeyChar() == 't')
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
                else if(e.getKeyChar() == 'l')
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
                
                COMANDOS COM TECLADO: 
                
                    |E| para criar uma elipse
                
                    |R| para criar um retangulo
                    
                    |T| para criar um triangulo
                    
                    |L| para criar um losango
                    
                    |DEL| para deletar a figura selecionada
                
                    |B| para mudar a cor da tela toda
                
                    |C| para mudar a cor da figura selecionada
                    
                    |SETAS| para movimentar a figura
                
                    COMANDOS COM MOUSE: 
                    
                    Pressione e arraste na borda da figura para redimensiona-la
                    
                    Pressione e arraste na figura para move-la
                    
                    Pressione com o botao direito em uma figura para mudar sua cor
                    
                    Pressione com o botao direito no background para mudar sua cor
                    
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
