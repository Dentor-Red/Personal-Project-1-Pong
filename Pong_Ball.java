// Pong Ball Class

package Pong_folder;
import javax.swing.*;
import java.awt.*;

public class Pong_Ball extends JFrame {

    private JFrame paddle;
    private JFrame aiPaddle;
    private int ballSize = 30;
    private int x, y;
    private int dx = 32; // horizontal velocity
    private int dy = 32; // vertical velocity

    private Rectangle screenBounds;
    private Pong_Scoreboard scoreboard;

    public Pong_Ball(Color color, JFrame paddle, JFrame aiPaddle) {
        this.paddle = paddle;
        this.aiPaddle = aiPaddle;
        this.scoreboard = new Pong_Scoreboard(); // Initialize scoreboard
        screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

        setUndecorated(true); 
        setAlwaysOnTop(true); 
        setSize(ballSize, ballSize);
        getContentPane().setBackground(color);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Start in center of screen
        x = screenBounds.width / 2;
        y = screenBounds.height / 2;
        setLocation(x, y);
        setVisible(true);

        Timer timer = new Timer(15, e -> move());
        timer.start();
    }

    public int getY() {
        return this.y;
    }

    private void move() {
        x += dx;
        y += dy;

        // Bounce off screen edges
        if (x <= 0 || x + ballSize >= screenBounds.width) {
            dx *= -1;
        }
        if (y <= 0 || y + ballSize >= screenBounds.height) {
            dy *= -1;
        }

        Rectangle ballRect = new Rectangle(x, y, ballSize, ballSize);

        // Player paddle collision
        Rectangle playerRect = paddle.getBounds();
        if (ballRect.intersects(playerRect)) {
            dx *= -1;
            x = playerRect.x + playerRect.width; // push ball to the right
        }

        // AI paddle collision
        Rectangle aiRect = aiPaddle.getBounds();
        if (ballRect.intersects(aiRect)) {
            dx *= -1;
            x = aiRect.x - ballSize; // push ball to the left
        }

        if (x <= 0) {
            scoreboard.addAIPoint();      // AI scores
            resetBall();
            return;
        }
        if (x + ballSize >= screenBounds.width) {
            scoreboard.addPlayerPoint();  // Player scores
            resetBall();
            return;
        }

        setLocation(x, y);
    }

    private void resetBall() {
        x = screenBounds.width / 2;
        y = screenBounds.height / 2;

        // Randomize direction
        dx = (Math.random() > 0.5 ? 1 : -1) * 16;
        dy = (Math.random() > 0.5 ? 1 : -1) * 16;

        setLocation(x, y);
    }
}

