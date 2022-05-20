package project1;

import figures.Figura;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class MyFrame extends JFrame {

    //Metodo construtor
    public MyFrame() {
        try{
            BufferedImage icone = ImageIO.read(getClass().getResourceAsStream("logo.png"));
            this.setIconImage(icone);
        }
        catch(IOException e)
        {
            System.out.println("Imagem nao encontrada");
        }
        //Inicializando o panel
        //Criando um panel para o frame
        MyPanel panel = new MyPanel();
        try{
            FileInputStream f = new FileInputStream("proj.bin");
            ObjectInputStream o = new ObjectInputStream(f);
            MyPanel.figures = (ArrayList<Figura>) o.readObject();
            o.close();
        }
        catch (Exception x)
        {
            System.out.println("ERRO!");
        }

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    FileOutputStream f = new FileOutputStream("proj.bin");
                    ObjectOutputStream o = new ObjectOutputStream(f);
                    o.writeObject(MyPanel.figures);
                    o.flush();
                    o.close();
                }
                catch (Exception x) {
                }
                System.exit(0);
            }
        });

        this.setTitle("Borba's Vector Graphics Editor"); //Mudando o título do frame
        this.add(panel); //Adicionando o panel no frame
        this.pack(); //Fazendo o frame ter o mesmo tamanho que o panel
        this.setLocationRelativeTo(null); //Fazendo o frame inicializar no meio da tela
        this.setVisible(true);
    }
}