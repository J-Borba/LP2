package project1;

import javax.swing.*;

public class MyFrame extends JFrame {

    //Criando um panel para o frame
    MyPanel panel;

    //Metodo construtor
    public MyFrame() {

        //Inicializando o panel
        panel = new MyPanel();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //Fazendo o programa fechar ao fechar o frame
        this.setTitle("Frame"); //Mudando o título do frame
        this.add(panel); //Adicionando o panel no frame
        this.pack(); //Fazendo o frame ter o mesmo tamanho que o panel
        this.setLocationRelativeTo(null); //Fazendo o frame inicializar no meio da tela
        this.setVisible(true);
    }
}