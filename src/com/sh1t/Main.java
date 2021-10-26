package com.sh1t;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.setBounds(10, 10 , 700, 600);
        frame.setTitle("FlappyBird");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
