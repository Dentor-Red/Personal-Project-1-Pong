// Pong Scoreboard Class

package Pong_folder;
import javax.swing.*;
import java.awt.*;

public class Pong_Scoreboard extends JFrame {
    private JLabel scoreLabel;
    private int playerScore = 0;
    private int aiScore = 0;

    public Pong_Scoreboard() {
        setUndecorated(true);
        setAlwaysOnTop(true);
        setSize(200, 50);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        scoreLabel = new JLabel("Player: 0 | AI: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(scoreLabel);

        setVisible(true);
    }

    public void addPlayerPoint() {
        playerScore++;
        updateLabel();
    }

    public void addAIPoint() {
        aiScore++;
        updateLabel();
    }

    private void updateLabel() {
        scoreLabel.setText("Player: " + playerScore + " | AI: " + aiScore);
    }
}

