package ivisible;

import java.awt.*;

public interface IVisible {
    boolean clicked(int x, int y);
    void setColorFundo(Color fundo);
    void paint(Graphics g);
}
