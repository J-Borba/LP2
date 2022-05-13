package project1;

import buttons.*;
import buttons.Button;
import figures.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import static java.awt.Toolkit.getDefaultToolkit;
import static java.awt.event.KeyEvent.*;
import static javax.swing.JOptionPane.showOptionDialog;
public class MyPanel extends JPanel
{
    private final ImageIcon icone = new ImageIcon(getDefaultToolkit().getImage(getClass().getResource("logo.png")));
    public static ArrayList<Figura> figures = new ArrayList<>();
    private final ArrayList<Button> buttons = new ArrayList<>();
    private Figura figuraFoco = null;
    private Figura figuraMouse = null;
    private Button botaoFoco = null;
    private Button botaoMouse = null;
    private final Cursor nResizeCur = new Cursor(Cursor.N_RESIZE_CURSOR);
    private final Cursor sResizeCur = new Cursor(Cursor.S_RESIZE_CURSOR);
    private final Cursor eResizeCur = new Cursor(Cursor.E_RESIZE_CURSOR);
    private final Cursor wResizeCur = new Cursor(Cursor.W_RESIZE_CURSOR);
    private final Cursor seResizeCur = new Cursor(Cursor.SE_RESIZE_CURSOR);
    private final Cursor defaultCur = new Cursor(Cursor.DEFAULT_CURSOR);
    private final Cursor moveCur = new Cursor(Cursor.MOVE_CURSOR);
    private final Cursor handCur = new Cursor(Cursor.HAND_CURSOR);
    private int[] coordenada, tamanho;
    private int clickX;
    private int clickY;
    private int whereX;
    private int whereY;
    public MyPanel()
    {
        buttons.add(new RectBtn(10, 30, 50, 50));
        buttons.add(new EllipseBtn(10, 90, 50, 50));
        buttons.add(new TriangBtn(10, 150, 50, 50));
        buttons.add(new LosangBtn(10, 210, 50, 50));
        buttons.add(new ColorBtn(10, 270, 50, 50));
        buttons.add(new EraseBtn(10, 330, 50, 50));
        this.addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent e)
            {
                whereX = e.getX();
                whereY = e.getY();

                for (Button btn : buttons)
                {
                    if(btn.clicked(whereX, whereY))
                    {
                        botaoMouse = btn;
                        figuraMouse = null;
                    }
                }
                for (Figura figura : figures) {
                    if(figura.clicked(whereX, whereY))
                    {
                        figuraMouse = figura;
                        botaoMouse = null;
                    }
                }
                if(botaoMouse != null)
                {
                    if(botaoMouse.clicked(whereX, whereY))
                    {
                        MyPanel.super.setCursor(handCur);
                    }
                    else
                    {
                        MyPanel.super.setCursor(defaultCur);
                    }
                }
                else if (figuraMouse != null)
                {
                    coordenada = figuraMouse.getPosition();
                    tamanho = figuraMouse.getSize();

                    if (figuraMouse.corner(new int[]{whereX, whereY}))
                    {
                        MyPanel.super.setCursor(seResizeCur);
                    }
                    else if (whereX >= coordenada[0] && whereX <= coordenada[0] + tamanho[0] &&
                            whereY >= coordenada[1] && whereY <= coordenada[1] + 5)
                    {
                        MyPanel.super.setCursor(nResizeCur);
                    }
                    else if (whereX >= coordenada[0] && whereX <= coordenada[0] + tamanho[0] &&
                            whereY >= coordenada[1] + tamanho[1] - 5 && whereY <= coordenada[1] + tamanho[1])
                    {
                        MyPanel.super.setCursor(sResizeCur);
                    }
                    else if (whereX >= coordenada[0] && whereX <= coordenada[0] + 5 &&
                            whereY >= coordenada[1] && whereY <= coordenada[1] + tamanho[1])
                    {
                        MyPanel.super.setCursor(eResizeCur);
                    }
                    else if (whereX >= coordenada[0] + tamanho[0] - 5 && whereX <= coordenada[0] + tamanho[0] &&
                            whereY >= coordenada[1] && whereY <= coordenada[1] + tamanho[1])
                    {
                        MyPanel.super.setCursor(wResizeCur);
                    }
                    else if (figuraMouse.clicked(whereX, whereY))
                    {
                        if (figuraMouse == figuraFoco)
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
                    Cursor curCursor = MyPanel.super.getCursor();
                    int[] coordenadasMouse = new int[] {e.getX()-clickX, e.getY()-clickY};

                    if(curCursor == seResizeCur)
                    {
                        figuraFoco.resize(coordenadasMouse, 5);
                    }
                    else if(curCursor == nResizeCur)
                    {
                        figuraFoco.resize(coordenadasMouse, 1);
                    }
                    else if(curCursor == sResizeCur)
                    {
                        figuraFoco.resize(coordenadasMouse, 2);
                    }
                    else if(curCursor == eResizeCur)
                    {
                        figuraFoco.resize(coordenadasMouse, 3);
                    }
                    else if(curCursor == wResizeCur)
                    {
                        figuraFoco.resize(coordenadasMouse, 4);
                    }
                    else if (curCursor == moveCur)
                    {
                        figuraFoco.setPosition(coordenadasMouse);
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
                if(botaoMouse == null)
                {
                    if(e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3)
                    {
                        figuraFoco = null;
                        repaint();
                    }
                }
                clickX = e.getX();
                clickY = e.getY();
                for(Button button : buttons)
                {
                    coordenada = new int[]{e.getX(), e.getY()};

                    if(button.clicked(coordenada[0], coordenada[1]))
                    {
                        if(e.getButton() == MouseEvent.BUTTON1)
                        {
                            button.setColorFundo(new Color(100, 100, 100));

                            if(button.getType() == 0) //Rect
                            {
                                figuraFoco =  new Rect( MyPanel.super.getWidth()/2-50,
                                        MyPanel.super.getHeight()/2-50,
                                        100,
                                        100,
                                        Color.black,
                                        Color.gray);

                                figures.add(figuraFoco);

                                for(int j=0; j < figures.size(); j++)
                                {
                                    if(figuraFoco == figures.get(j))
                                    {
                                        figures.remove(figures.get(j));
                                        figures.add(figuraFoco);
                                    }
                                }
                                repaint();
                            }
                            else if(button.getType() == 1) //Ellipse
                            {
                                figuraFoco =  new Ellipse( MyPanel.super.getWidth()/2-50,
                                        MyPanel.super.getHeight()/2-50,
                                        100,
                                        100,
                                        Color.black,
                                        Color.gray);

                                figures.add(figuraFoco);

                                for(int j=0; j < figures.size(); j++)
                                {
                                    if(figuraFoco == figures.get(j))
                                    {
                                        figures.remove(figures.get(j));
                                        figures.add(figuraFoco);
                                    }
                                }
                                repaint();
                            }
                            else if(button.getType() == 2) //Triang
                            {
                                figuraFoco =  new Triangle( MyPanel.super.getWidth()/2-50,
                                        MyPanel.super.getHeight()/2-50,
                                        100,
                                        100,
                                        Color.black,
                                        Color.gray);

                                figures.add(figuraFoco);

                                for(int j=0; j < figures.size(); j++)
                                {
                                    if(figuraFoco == figures.get(j))
                                    {
                                        figures.remove(figures.get(j));
                                        figures.add(figuraFoco);
                                    }
                                }
                                repaint();
                            }
                            else if(button.getType() == 3) //Losang
                            {
                                figuraFoco =  new Losango( MyPanel.super.getWidth()/2-50,
                                        MyPanel.super.getHeight()/2-50,
                                        100,
                                        100,
                                        Color.black,
                                        Color.gray);

                                figures.add(figuraFoco);

                                for(int j=0; j < figures.size(); j++)
                                {
                                    if(figuraFoco == figures.get(j))
                                    {
                                        figures.remove(figures.get(j));
                                        figures.add(figuraFoco);
                                    }
                                }
                                repaint();
                            }
                            else if(button.getType() == 4) //Eraser
                            {
                                figures.remove(figuraFoco);

                                figuraFoco = null;
                                figuraMouse = null;
                                repaint();
                            }
                            else if(button.getType() == 5) //Color chooser
                            {
                                Object[] options;
                                if(figuraFoco != null)
                                {
                                    options = new Object[]{"Fundo", "Contorno"};

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
                                else
                                {
                                    options = new Object[]{"Sim", "Nao"};
                                    int escolha = showOptionDialog(null, "Deseja mudar a cor da tela toda? ", "Troca de cor", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, icone, options, options[0]);

                                    if(escolha == 0)
                                    {
                                        Color cor = JColorChooser.showDialog(null, "Seletor de cores!", MyPanel.super.getBackground());
                                        MyPanel.super.setBackground(cor);
                                    }
                                }
                                buttons.get(4).setColorFundo(new Color(210, 210, 210));//Voltar a cor do botao de cor
                            }
                        }
                    }
                    botaoMouse = null;
                    repaint();
                }
                for (Figura figure : figures)
                {
                    coordenada = new int[]{e.getX(), e.getY()};

                    if (figure.clicked(coordenada[0], coordenada[1]))
                    {
                        figuraFoco = figure;

                        if(e.getButton() == MouseEvent.BUTTON1)
                        {
                            Cursor curCursor = MyPanel.super.getCursor();

                            if(curCursor == defaultCur || curCursor == handCur)
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
                            figures.remove(figures.get(j));
                            figures.add(figuraFoco);
                        }
                    }
                    repaint();
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                for(Button button : buttons) {
                    button.setColorFundo(new Color(210, 210, 210));
                }
                repaint();
            }
        });
        this.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == VK_DELETE && e.isControlDown())
                {
                    figures.removeAll(figures);
                    figuraFoco = null;
                    figuraMouse = null;
                    repaint();
                }
                
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
                    else if(e.getKeyCode() == VK_DELETE || e.getKeyCode() == VK_BACK_SPACE)
                    {
                        figures.remove(figuraFoco);

                        figuraFoco = null;
                        figuraMouse = null;
                        repaint();
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
                    if (e.getKeyCode() == VK_TAB) {

                    }
                }
                if(e.getKeyChar() == 'r' || e.getKeyChar() == 'R')
                {
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
                            figures.remove(figures.get(j));
                            figures.add(figuraFoco);
                        }
                    }
                    repaint();
                }

                else if(e.getKeyChar() == 'e' || e.getKeyChar() == 'E')
                {
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
                            figures.remove(figures.get(j));
                            figures.add(figuraFoco);
                        }
                    }
                    repaint();
                }
                else if(e.getKeyChar() == 't' || e.getKeyChar() == 'T')
                {

                    figuraFoco =  new Triangle(whereX - 50,
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
                            figures.remove(figures.get(j));
                            figures.add(figuraFoco);
                        }
                    }
                    repaint();
                }
                else if(e.getKeyChar() == 'l' || e.getKeyChar() == 'L')
                {
                    figuraFoco =  new Losango(whereX - 50,
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

        this.setFocusTraversalKeysEnabled(false);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(600, 600));
        this.setVisible(true);
    }
    public void paint(Graphics g)
    {
        super.paint(g);
        for(Figura fig: figures)
        {
            fig.paint(g, fig == figuraFoco);
        }
        for(Button but : buttons)
        {
            but.paint(g, but == botaoMouse);
        }
    }
}
