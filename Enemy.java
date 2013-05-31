//moves like Space Invaders (down and increasing with a reverse in direction)
//can fire upon the Player

import java.awt.*;
import java.awt.Graphics.*;
import java.awt.Color.*;

public class Enemy {
    private int x_pos;
    private int y_pos;
    private final int SIZE = 20;
    private int row = 1;
    private Arrow arrow;

    public Enemy(int x, int y) {
        x_pos = x;
        y_pos = y;
    }

    public Arrow generateArrow() {
        arrow = new Arrow (x_pos, y_pos, 5);
        return arrow;
    }

    public void moveEnemy() {
        if (row % 2 == 1) {
            if (x_pos <= 0 + SIZE/2) {
                row++;
                y_pos += 50;
            } else {
                x_pos -= row;
            }
        } else {
            if (x_pos >= 500 - SIZE/2) {
                row++;
                y_pos += 75;
            } else {
                x_pos += row;
            }
        }
    }

    public int getXPos() {
        return x_pos;
    }

    public int getYPos() {
        return y_pos;
    }

    public int getSize() {
        return SIZE;
    }

    public void drawEnemy(Graphics g) {
        arrow.drawArrow(g);
    }
}

    