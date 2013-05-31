//by: Jason Asher
//Shot is what the Player shoots at Enemys

import java.awt.Graphics;
import java.awt.Color;

public class Shot
{
    private int x_pos;
    private int y_pos;

    private final int RADIUS = 4;
    private final int SPEED;

    public Shot(int x, int y, int shotspeed)
    {
        x_pos = x - 5;
        y_pos = y - 5;
        SPEED = shotspeed;
    }
    
    public int getYPos()
    {
        return y_pos;
    }

    public int getXPos() {
        return x_pos;
    }

    public int getRadius() {
        return RADIUS;
    }

    public void moveShot()
    {
        y_pos -= SPEED;
    }

    public void drawShot(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillOval(x_pos - RADIUS/2, y_pos - RADIUS/2, RADIUS, RADIUS);
    }
}