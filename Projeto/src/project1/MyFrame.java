package project1;

import javax.swing.*;

import java.awt.*;

import static java.awt.Toolkit.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class MyFrame extends JFrame {

    //Criando um panel para o frame
    MyPanel panel;
    //ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("icone.png"));
    ImageIcon icone = new ImageIcon(getDefaultToolkit().getImage(getClass().getResource("logo.png")));

    //Metodo construtor
    public MyFrame() {
        this.setIconImage(icone.getImage());

        showMessageDialog(this, "Aperte H a qualquer momento para abrir a janela de comandos");

        //Inicializando o panel
        panel = new MyPanel();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //Fazendo o programa fechar ao fechar o frame
        this.setTitle("Borba's Vector Graphics Editor"); //Mudando o título do frame
        this.add(panel); //Adicionando o panel no frame
        this.pack(); //Fazendo o frame ter o mesmo tamanho que o panel
        this.setLocationRelativeTo(null); //Fazendo o frame inicializar no meio da tela
        this.setVisible(true);
    }
}