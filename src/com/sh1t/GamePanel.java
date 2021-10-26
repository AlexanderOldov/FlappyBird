package com.sh1t;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class GamePanel extends JPanel implements KeyListener, ActionListener {
    private boolean running = true;
    private int score = 0;

    private Timer timer;
    private int delay = 20;

    private int birdposX = 50;
    private int birdposY = 250;

    int speed =5;
    Pipes pipes1 = new Pipes();
    Pipes pipes2 = new Pipes();
    Pipes pipes3 = new Pipes();

    Random random = new Random();



    public GamePanel(){
        addKeyListener(this);
        setFocusable(true);

        timer = new Timer(delay, this);
        timer.start();


    }
    public void paint(Graphics g){
        // background
        g.setColor(new Color(0x38DCCF));
        g.fillRect(0, 0, 692, 592);

        // score
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(""+score, 340, 130);

        // bird
        g.setColor(Color.white);
        g.fillOval(birdposX, birdposY, 20, 20);

        // pipes
        pipes1.draw(g);
        pipes2.draw(g);
        pipes3.draw(g);

        // game over
        if(running){
            for(int i = 1; i<4; i++) {
                if (i == 1) {
                    if (new Rectangle(birdposX, birdposY, 20, 20).intersects(new Rectangle(pipes1.topPipePosX, 0, 100, pipes1.randomInt)) ||
                            new Rectangle(birdposX, birdposY, 20, 20).intersects(new Rectangle(pipes1.botPipePosX, pipes1.randomInt + 200, 100, 600))) {
                        running = false;
                    }
                }
                if (i == 2) {
                    if (new Rectangle(birdposX, birdposY, 20, 20).intersects(new Rectangle(pipes2.topPipePosX, 0, 100, pipes2.randomInt)) ||
                            new Rectangle(birdposX, birdposY, 20, 20).intersects(new Rectangle(pipes2.botPipePosX, pipes2.randomInt + 200, 100, 600))) {
                        running = false;
                    }
                }
                if (i == 3) {
                    if (new Rectangle(birdposX, birdposY, 20, 20).intersects(new Rectangle(pipes3.topPipePosX, 0, 100, pipes3.randomInt)) ||
                            new Rectangle(birdposX, birdposY, 20, 20).intersects(new Rectangle(pipes3.botPipePosX, pipes3.randomInt + 200, 100, 600))) {
                        running = false;
                    }
                    if(birdposY>600){
                        running = false;
                    }
                    if(birdposY<=0){
                        birdposY =0;
                    }
                }
                if (!running) {
                    g.setColor(Color.red);
                    g.setFont((new Font("serif", Font.BOLD, 30)));
                    g.drawString("Game Over", 280, 300);
                    g.setFont((new Font("serif", Font.BOLD, 20)));
                    g.drawString("Press Enter to Restart", 260, 350);
                }
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

       if(running){
           if(pipes3.botPipePosX <= 300) {
               pipes3.topPipePosX -=speed;
               pipes3.botPipePosX -=speed;
               pipes1.topPipePosX -= speed;
               pipes1.botPipePosX -= speed;
               pipes2.botPipePosX = 700;
               pipes2.topPipePosX = 700;
               pipes2.randomInt = random.nextInt(300);
           }
           if(pipes1.botPipePosX <= 300) {
               pipes1.topPipePosX -= speed;
               pipes1.botPipePosX -= speed;
               pipes2.topPipePosX -= speed;
               pipes2.botPipePosX -= speed;
               pipes3.botPipePosX = 700;
               pipes3.topPipePosX = 700;
               pipes3.randomInt = random.nextInt(300);
           }
           if(pipes2.botPipePosX <= 300){
               pipes2.topPipePosX -= speed;
               pipes2.botPipePosX -= speed;
               pipes3.topPipePosX -=speed;
               pipes3.botPipePosX -=speed;
               pipes1.botPipePosX = 700;
               pipes1.topPipePosX = 700;
               pipes1.randomInt = random.nextInt(300);
           }
           if(pipes1.botPipePosX == -50 || pipes2.botPipePosX == -50 || pipes3.botPipePosX == -50){
               score++;
           }
           birdposY += speed;
           repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(running){
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            jump();
        }
        }else{
           if(e.getKeyCode() == KeyEvent.VK_ENTER){
               running = true;
               birdposY = 250;
               pipes1.topPipePosX = -100;
               pipes1.botPipePosX = -100;
               pipes2.topPipePosX = 700;
               pipes2.botPipePosX = 700;
               pipes3.topPipePosX = 700;
               pipes3.botPipePosX = 700;
               score = 0;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public void jump(){
        running = true;
        birdposY-=80;
    }
}
