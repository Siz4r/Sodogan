package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KeyHandler implements KeyListener {
    private final Map<Integer, Boolean> keysStatus;
    public boolean upPressed;
    public boolean downPressed;
    public boolean rightPressed;
    public boolean leftPressed;
    public KeyHandler() {
        this.keysStatus = initializeKeysStatuses();
    }

    private Map<Integer, Boolean> initializeKeysStatuses() {
        return Stream.of(KeyEvent.VK_BACK_SPACE, KeyEvent.VK_TAB, KeyEvent.VK_ENTER, KeyEvent.VK_CLEAR, KeyEvent.VK_SHIFT,
                        KeyEvent.CTRL_DOWN_MASK, KeyEvent.ALT_DOWN_MASK, KeyEvent.VK_A, KeyEvent.VK_B, KeyEvent.VK_C, KeyEvent.VK_D,
                        KeyEvent.VK_E, KeyEvent.VK_F, KeyEvent.VK_G, KeyEvent.VK_H, KeyEvent.VK_W, KeyEvent.VK_J,
                        KeyEvent.VK_S)
                .collect(Collectors.toMap(key -> key, key -> false));
    }

    public boolean getKeyStatus(int keyCode) {
        return keysStatus.get(keyCode);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> leftPressed = true;
            case KeyEvent.VK_D -> rightPressed = true;
            case KeyEvent.VK_S -> downPressed = true;
            case KeyEvent.VK_W -> upPressed = true;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> leftPressed = true;
            case KeyEvent.VK_D -> rightPressed = true;
            case KeyEvent.VK_S -> downPressed = true;
            case KeyEvent.VK_W -> upPressed = true;
        }

        //        keysStatus.put(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> leftPressed = false;
            case KeyEvent.VK_D -> rightPressed = false;
            case KeyEvent.VK_S -> downPressed = false;
            case KeyEvent.VK_W -> upPressed = false;
        }

        //        keysStatus.put(e.getKeyCode(), false);
    }
}
