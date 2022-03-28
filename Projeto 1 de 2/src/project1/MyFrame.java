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

        showMessageDialog(this, """
                Instruções de Uso:
                (Aperte H para abrir esta janela a qualquer momento):

                Pressionar as teclas:

                "E" para criar uma elipse

                "R" para criar um retangulo

                "DELETE" para deletar a figura selecionada

                "F" para mudar a cor de fundo da figura selecionada. Caso nao tenha nenhuma figura em foco, mudara a cor da tela toda\s

                "C" para mudar a cor de contorno da figura selecionada

                "Pressione e arraste a borda inferior direita da figura ou segure SHIFT e use as setas do teclado" para mudar o tamanho da figura selecionada

                OBS: PARA SELECIONAR UMA FIGURA, BASTA CLICAR COM O MOUSE EM CIMA DELA""");
        //Inicializando o panel
        panel = new MyPanel();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //Fazendo o programa fechar ao fechar o frame
        this.setTitle("Borba's Vector Graphic Editor"); //Mudando o título do frame
        this.add(panel); //Adicionando o panel no frame
        this.pack(); //Fazendo o frame ter o mesmo tamanho que o panel
        this.setLocationRelativeTo(null); //Fazendo o frame inicializar no meio da tela
        this.setVisible(true);
    }
}