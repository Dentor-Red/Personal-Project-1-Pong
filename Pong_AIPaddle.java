// Pong AI Opponent Paddle Class
package Pong_folder;
import javax.swing.*;
import java.awt.*;

public class Pong_AIPaddle extends JFrame {
    private int width, height;
    private int x;
    private Color color;

    private Pong_Ball ball;
    private int y;
    private int speed;

    public Pong_AIPaddle(int difficulty, Color color, Pong_Ball ball, int side, int size) {
        this.ball = ball;
        this.color = color;

        Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        width = screenBounds.width / 100;
        height = screenBounds.height / 10 * size;
        x = screenBounds.width - width - side; // right side offset
        y = screenBounds.height / 2;

        speed = 10 + difficulty * 2;

        setUndecorated(true);
        setAlwaysOnTop(true);
        setSize(width, height);
        setBackground(color);
        getContentPane().setBackground(color);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(x, y);
        setVisible(true);

        Timer timer = new Timer(15, e -> move());
        timer.start();
    }

    public void setBall(Pong_Ball ball) {
        this.ball = ball;
    }

    private void move() {
        Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int maxY = screenBounds.height - height;

        int targetY = ball.getY();

        if (y + height / 2 < targetY) {
            y += speed;
        } else if (y + height / 2 > targetY) {
            y -= speed;
        }

        // Clamp to screen
        if (y < 0) y = 0;
        if (y > maxY) y = maxY;

        setLocation(x, y);
    }
}