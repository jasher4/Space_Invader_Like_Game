//by: Jason Asher
//Space Invader Like Game
//Instructions: Use arrow keys after clicking into the applet to control the Player and space to shoot Arrows


import java.applet.*;
import java.awt.*;

public class Main extends Applet implements Runnable
{
    private Thread th;
    private Player player;
    private Shot [] shots;
    private Enemy[] enemys;
    private Arrow[] arrows;

    private Image playerImage;
    private Image enemyImage;
    private Image arrowImage;
    private Image background;
    private Image shotImage;

    private final int shotSpeed = 5;
    private final int playerLeftSpeed = -3;
    private final int playerRightSpeed = 3;

    private boolean playerMoveLeft;
    private boolean playerMoveRight;

    private boolean won = false;
    private boolean lost = false;

    private int time = 0;
    private int life = 3;

    private Image dbImage;
    private Graphics dbg;

    public void init()
    {
        setBackground (Color.WHITE);
        player = new Player(150, 450);
        shots = new Shot[1];
        enemys = new Enemy[10];
        arrows = new Arrow[10];
        for (int i = 0; i < enemys.length; i++) {
            enemys[i] = new Enemy(48*i, 50);
        }

        playerImage = getImage(getCodeBase(), "Player.gif");
        enemyImage = getImage(getCodeBase(), "Enemy.gif");
        arrowImage = getImage(getCodeBase(), "Arrow.gif");
        background = getImage(getCodeBase(), "Background.gif");
        shotImage = getImage(getCodeBase(), "Shot.gif");
    }

    public void start ()
    {
        th = new Thread(this);
        th.start ();
    }

    public void stop()
    {
        th.stop();
    }

    public void destroy()
    {
        th.stop();
    }

    public void run ()
    {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

        while (true)
        {
            if (!won && !lost) {
                for(int i=0; i<shots.length; i++)
                {
                    if(shots[i] != null)
                    {
                        shots[i].moveShot();

                        if(shots[i].getYPos() < 0 || shots[i].getYPos() > 500 || shots[i].getXPos() < 0 || shots[i].getXPos() > 500)
                        {
                            shots[i] = null;
                        }
                    }
                }

                for (int i = 0; i < enemys.length; i++) {
                    if (enemys[i] != null) {
                        if (time%2 == 0) {
                            enemys[i].moveEnemy();
                        }
                        for (int j = 0; j < shots.length; j++) {
                            if (shots[j] != null) {
                                if (Math.abs(enemys[i].getXPos() - shots[j].getXPos()) < shots[j].getRadius()/2 + enemys[i].getSize()/2
                                && Math.abs(enemys[i].getYPos() - shots[j].getYPos()) < shots[j].getRadius()/2 + enemys[i].getSize()/2) {
                                    shots[j] = null;
                                    enemys[i] = null;
                                }
                            }
                        }
                    }
                }

                for (int i = 0; i < enemys.length; i++) {
                    if (enemys[i] != null) {
                        if (Math.random() * 10 < 3 && time%10 == 0 && arrows[i] == null) {
                            arrows[i] = enemys[i].generateArrow();    
                        }
                        if (enemys[i].getYPos() > 400 - enemys[i].getSize()/2) {
                            lost = true;
                        }
                    }
                }

                for (int i = 0; i < arrows.length; i++) {
                    if (arrows[i] != null) {
                        arrows[i].moveArrow();
                        if (Math.abs(player.getYPos() - arrows[i].getYPos()) < player.getSize()/2 && Math.abs(player.getXPos() - arrows[i].getXPos()) < player.getSize()/2 + arrows[i].getRadius()/2) {
                            life--;
                            arrows[i] = null;
                        } else if (arrows[i].getYPos() > 500) {
                            arrows[i] = null;
                        }
                    } 
                }

                if(playerMoveLeft)
                {
                    player.moveX(playerLeftSpeed);
                }
                else if(playerMoveRight)
                {
                    player.moveX(playerRightSpeed);
                }
                int x = 0;
                for (int i = 0;  i < enemys.length; i++) {
                    if (enemys[i] == null) {
                        x++;
                    }
                }
                if (x == enemys.length) {
                    won = true;
                } else {
                    x = 0;
                }

                time++;
                if (life <= 0) {
                    lost = true;
                }
            } 
            repaint();
            try
            {
                Thread.sleep(10);
            }
            catch (InterruptedException ex)
            {
            }

            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        }
    }

    public boolean keyDown(Event e, int key)
    {
        if (!won && !lost) {
            if(key == Event.LEFT)
            {
                playerMoveLeft = true;
            } 
            else if(key == Event.RIGHT)
            {
                playerMoveRight = true;
            }
            else if(key == 32)
            {
                for(int i=0; i<shots.length; i++)
                {
                    if(shots[i] == null)
                    {
                        shots[i] = player.generateShot();
                        break;
                    }
                }
            }
        }
        return true;
    }

    public boolean keyUp(Event e, int key)
    {
        if (!won && !lost) {
            if(key == Event.LEFT)
            {
                playerMoveLeft = false;
            }
            else if(key == Event.RIGHT)
            {
                playerMoveRight = false;
            }
        }
        return true;
    }

    public void update (Graphics g)
    {
        if (dbImage == null)
        {
            dbImage = createImage (this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics ();
        }

        dbg.setColor (getBackground ());
        dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);
        dbg.drawImage(background, 0, 0, 500, 500, this);

        dbg.setColor (getForeground());
        paint (dbg);

        g.drawImage (dbImage, 0, 0, this);
    }

    public void paint (Graphics g)
    {
        if (!won && !lost) {
            //player.drawPlayer(g);
            g.drawImage(playerImage, player.getXPos() - player.getSize()/2, player.getYPos() - player.getSize()/2, player.getSize(), player.getSize(), this);

            for(int i=0; i<shots.length; i++)
            {
                if(shots[i] != null)
                {
                    g.drawImage(shotImage, shots[i].getXPos() - shots[i].getRadius()/2, shots[i].getYPos() - shots[i].getRadius()/2, shots[i].getRadius(), shots[i].getRadius(), this);
                    //shots[i].drawShot(g);
                }
            }
            for (int i = 0; i < enemys.length; i++) {
                if (enemys[i] != null) {
                    g.drawImage(enemyImage, enemys[i].getXPos() - enemys[i].getSize()/2, enemys[i].getYPos() - enemys[i].getSize()/2, enemys[i].getSize(), enemys[i].getSize(), this);
                    //g.drawImage(arrowImage, arrows[i].getXPos() - arrows[i].getRadius()/2, arrows[i].getYPos() - arrows[i].getLength()/2, arrows[i].getRadius(), arrows[i].getLength(), this);
                    enemys[i].drawEnemy(g);
                }
            }
            g.setColor(Color.WHITE);
            g.drawLine(0, 400, 500, 400);
            g.drawString("Lives: "+life, 5, 490);
        } else if (won) {
            g.setColor(Color.RED);
            g.drawString("YOU WIN", 220, 250);
        } else if (lost) {
            g.setColor(Color.RED);
            g.drawString("YOU LOSE", 220, 250);
        }
    }
}
