package superclasses;

import javax.swing.*;
import java.awt.*;

//Criando o panel
class MyPanel extends JPanel {

    //Metodo construtor
    public MyPanel(){

        //Definindo tamanho da janela
        this.setPreferredSize(new Dimension(600, 600));

        //Definindo cor de fundo do panel
        this.setBackground(Color.black);

    }

    //Metodo que pinta o Panel
    public void paint(Graphics g){
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        int WIDTH = this.getWidth();
        int HEIGHT = this.getHeight();

    }
}