package com.sh1t;

import java.awt.*;
import java.util.Random;

public class Pipes {
    private Random random = new Random();
    int randomInt;

    int topPipePosX = -100;
    int botPipePosX = -100;
    public Pipes(){
    }

    public void draw(Graphics g){
        g.setColor(Color.green);
        g.fillRect(topPipePosX,0, 100, randomInt);
        g.fillRect(botPipePosX, randomInt+200, 100, 600);
    }
}
