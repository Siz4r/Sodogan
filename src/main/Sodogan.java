package main;

import javax.swing.*;

public class Sodogan {
    public static void main(String[] args) {
        var window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Sondogan");

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        var gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        gamePanel.startGameThread();
    }
}