package project1;

import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class MyFrame extends JFrame {

    //Criando um panel para o frame
    MyPanel panel;

    //Metodo construtor
    public MyFrame() {
        showMessageDialog(this, "Instruções de Uso:\n(Aperte H para abrir esta janela a qualquer momento):\n" +
                "\nPressionar as teclas:\n" +
                "\n" +
                "\"E\" para criar uma elipse\n" +
                "\n" +
                "\"R\" para criar um retangulo\n" +
                "\n" +
                "\"DELETE\" para deletar a figura selecionada\n" +
                "\n" +
                "\"F\" para mudar a cor de fundo da figura selecionada. Caso nao tenha nenhuma figura em foco, mudara a cor da tela toda \n" +
                "\n" +
                "\"C\" para mudar a cor de contorno da figura selecionada\n" +
                "\n" +
                "\"Pressione e arraste a borda inferior direita da figura ou segure SHIFT e use as setas do teclado\" para mudar o tamanho da figura selecionada\n" +
                "\n" +
                "OBS: PARA SELECIONAR UMA FIGURA, BASTA CLICAR COM O MOUSE EM CIMA DELA");
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