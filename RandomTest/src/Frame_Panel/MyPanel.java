package Frame_Panel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import rects.Rect;

//Criando o panel
public class MyPanel extends JPanel {

    ArrayList<Rect> Rects = new ArrayList<>();

    Random randomizer = new Random();

    //Metodo construtor
    public MyPanel(){

        //Definindo tamanho da janela
        this.setPreferredSize(new Dimension(600, 600));

        //Definindo cor de fundo do panel
        this.setBackground(Color.black);

        this.setVisible(true);

        for(int i=0; i<5; i++){
            int x = randomizer.nextInt(500);
            int y = randomizer.nextInt(500);
            int w = randomizer.nextInt(100);
            int h = randomizer.nextInt(100);

            Rects.add(new Rect(x, y, w, h));
        }

    }

    //Metodo que pinta o Panel
    public void paint(Graphics g){
        super.paint(g);

        for(Rect r: this.Rects){
            r.paint(g);
        }
    }
}
