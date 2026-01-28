// Project 1: Pong

// Pong Main Game

// Libraries imported for the project
package Pong_folder;
import Pong_folder.*;
import javax.swing.*;
import javax.tools.Tool;

import java.awt.*;

// Class for the project 

public class Pong extends JFrame {

    public static class GameState {
        public volatile int leftPaddleY = 100;
        public final int GAME_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height / 4;
    }
    
    public static void main(String[] args) {
        GameState state = new GameState();
        int difficulty = 1;
        int size = 1;

        SwingUtilities.invokeLater(() -> {
            Pong_Paddle playerPaddle = new Pong_Paddle(50, Color.BLUE, difficulty, state);
            Pong_AIPaddle aiPaddle = new Pong_AIPaddle(difficulty, Color.GREEN, null, 60, size);
            Pong_Ball ball = new Pong_Ball(Color.RED, playerPaddle, aiPaddle);
            aiPaddle.setBall(ball);
        });
    }
}