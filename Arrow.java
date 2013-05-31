//by: Jason Asher
//Arrow is what the Enemy shoots at Player

import java.awt.Graphics;
import java.awt.Color;
import java.applet.*;

public class Arrow
{
    private int x_pos;
    private int y_pos;

    private final int SPEED;
    private final int RADIUS = 6;
    private final int LENGTH = 5;
    
    public Arrow(int x, int y, int arrowspeed)
    {
        x_pos = x;
        y_pos = y;
        SPEED = arrowspeed;
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
    
    public int getLength() {
        return LENGTH;
    }
    
    public void moveArrow()
    {
        y_pos += SPEED;
    }

    public void drawArrow(Graphics g)
    {
        g.setColor(Color.GREEN);
        g.drawLine(x_pos, y_pos, x_pos, y_pos - LENGTH);
        g.drawLine(x_pos, y_pos, x_pos - RADIUS/2, y_pos - 2);
        g.drawLine(x_pos, y_pos, x_pos + RADIUS/2, y_pos - 2);
    }
}