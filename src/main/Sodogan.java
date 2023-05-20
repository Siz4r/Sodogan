package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

//public class Sodogan{
//
//    public static void main(String[] args ){
//
//        // Key Bindings = 	bind an Action to a KeyStroke
//        //					don't require you to click a component to give it focus
//        //					all Swing components use Key Bindings
//        //					increased flexibility compared to KeyListeners
//        //					can assign key strokes to individual Swing components
//        //					more difficult to utilize and set up :(
//
//        Game game = new Game();
//    }
//}
//*************************************************

class Game {

    JFrame frame;
    JLabel label;
    Action upAction;
    Action downAction;
    Action leftAction;
    Action rightAction;

    Game(){

        frame = new JFrame("KeyBinding Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);

        label = new JLabel();
        label.setBackground(Color.red);
        label.setBounds(100, 100, 100, 100);
        label.setOpaque(true);

        upAction = new UpAction();
        downAction = new DownAction();
        leftAction = new LeftAction();
        rightAction = new RightAction();

        label.getInputMap().put(KeyStroke.getKeyStroke('w'), "upAction");
        label.getActionMap().put("upAction", upAction);
        label.getInputMap().put(KeyStroke.getKeyStroke('s'), "downAction");
        label.getActionMap().put("downAction", downAction);
        label.getInputMap().put(KeyStroke.getKeyStroke('a'), "leftAction");
        label.getActionMap().put("leftAction", leftAction);
        label.getInputMap().put(KeyStroke.getKeyStroke('d'), "rightAction");
        label.getActionMap().put("rightAction", rightAction);

        frame.add(label);
        frame.setVisible(true);
    }

    public class UpAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            label.setLocation(label.getX(), label.getY()-10);
        }
        
    }
    public class DownAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            label.setLocation(label.getX(), label.getY()+10);
        }
    }
    public class LeftAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            label.setLocation(label.getX()-10, label.getY());
        }
    }
    public class RightAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            label.setLocation(label.getX()+10, label.getY());
        }
    }
}
//*************************************************


public class Sodogan {
    private static GamePanel gamePanel;
    public static void main(String[] args) {
        var window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Sondogan");

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel = new GamePanel();
        gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "upAction");
        gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "downAction");
        gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "leftAction");
        gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "rightAction");

        gamePanel.getActionMap().put("upAction", new UpAction());
        gamePanel.getActionMap().put("downAction", new DownAction());
        gamePanel.getActionMap().put("leftAction", new LeftAction());
        gamePanel.getActionMap().put("rightAction", new RightAction());
        window.add(gamePanel);
        window.pack();
        gamePanel.startGameThread();
    }

    static class UpAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            gamePanel.player.direction = "up";
            gamePanel.collisionChecker.checkTile(gamePanel.player);
            if (!gamePanel.player.collisionOn) {
                gamePanel.player.worldY -= gamePanel.player.speed;
            }
        }
    }
    static class DownAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            gamePanel.player.direction = "down";
            gamePanel.collisionChecker.checkTile(gamePanel.player);
            if (!gamePanel.player.collisionOn) {
                gamePanel.player.worldY += gamePanel.player.speed;
            }
        }
    }
    static class LeftAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            gamePanel.player.direction = "left";
            gamePanel.collisionChecker.checkTile(gamePanel.player);
            if (!gamePanel.player.collisionOn) {
                gamePanel.player.worldX -= gamePanel.player.speed;
            }
        }
    }
    static class RightAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            gamePanel.player.direction = "right";
            gamePanel.collisionChecker.checkTile(gamePanel.player);
            if (!gamePanel.player.collisionOn) {
                gamePanel.player.worldX += gamePanel.player.speed;
            }
        }
    }
}