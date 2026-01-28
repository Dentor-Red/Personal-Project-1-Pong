// Pong Paddle Class

package Pong_folder;
import Pong_folder.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pong_Paddle extends JFrame implements KeyListener {

    private int width, height;
    private boolean upPressed, downPressed;
    private Pong.GameState state;

    public Pong_Paddle(int x, Color color, int difficulty, Pong.GameState state) {
        this.width = Toolkit.getDefaultToolkit().getScreenSize().width / 100;
        this.height = Toolkit.getDefaultToolkit().getScreenSize().height / 10 * difficulty;
        this.state = state;

        setTitle("Paddle");
        setUndecorated(true); 
        setAlwaysOnTop(true);
        setFocusableWindowState(true);
        setSize(width, height);
        setBackground(color);
        getContentPane().setBackground(color);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocation(x, state.leftPaddleY); 
        addKeyListener(this);

        setVisible(true);

        EventQueue.invokeLater(() -> {
            requestFocus();
            toFront();
        });

        Timer timer = new Timer(15, e -> {
            move();
        });
        timer.start();

        setVisible(true);
    }

    public void move() {
        Rectangle usableBounds = GraphicsEnvironment
            .getLocalGraphicsEnvironment()
            .getMaximumWindowBounds();
        int maxY = usableBounds.height - height;

        if (upPressed && state.leftPaddleY > 0) {
            state.leftPaddleY -= 30;
        }
        if (downPressed && state.leftPaddleY < maxY) {
            state.leftPaddleY += 30;
        }

        // Clamp just in case
        if (state.leftPaddleY < 0) state.leftPaddleY = 0;
        if (state.leftPaddleY > maxY) state.leftPaddleY = maxY;

        setLocation(getX(), state.leftPaddleY);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) upPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_S) downPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) upPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_S) downPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
    

