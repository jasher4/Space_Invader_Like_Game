//by: Jason Asher
//Can shoot towards an Enemy and can move using keyboard

import java.awt.Graphics;
import java.awt.Color;

public class Player
{
    private int x_pos;
    private int y_pos;
    
    private final int SIZE = 16;
    
    public void init() {
    }

    public Player(int x, int y)
    {
        x_pos = x - SIZE/2;
        y_pos = y - SIZE/2;
    }

    public void moveX(int speed)
    {
        if (x_pos >= 490) {
            x_pos -= 5;
        } else if (x_pos <= 0) {
            x_pos += 5;
        }
        x_pos += speed;
    }
    
    public int getSize() {
        return SIZE;
    }

    public int getXPos( ){
        return x_pos;
    }

    public int getYPos() {
        return y_pos;
    }

    public Shot generateShot()
    {
        Shot shot;

        shot = new Shot(x_pos, y_pos, 5);

        return shot;
    }

    public void drawPlayer(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect(x_pos - SIZE/2, y_pos - SIZE/2, SIZE, SIZE);
    }
}