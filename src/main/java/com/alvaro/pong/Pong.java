package com.alvaro.pong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class Pong extends Application {
    private final int HEIGHT = 800;
    private final int WIDTH = 800;
    private final int PLAYER_WIDTH = 15;
    private final int PLAYER_HEIGHT = 100;
    private final double BALL_RADIUS = 15;

    private int scoreP1 = 0;
    private int scoreP2 = 0;
    private int ballYSpeed = 1;
    private int ballXSpeed = 1;
    private int playerOneXPos = 0;
    private boolean gameStarted;
    private double ballXPos = WIDTH / 2;
    private double ballYPos = HEIGHT / 2;
    private double playerOneYPos = HEIGHT / 2;
    private double playerTwoYPos = HEIGHT / 2;
    private double playerTwoXPos = WIDTH - PLAYER_WIDTH;

    public void start(Stage stage) {
        stage.setTitle("PONG by Alvaro");
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Timeline tl = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
        tl.setCycleCount(Timeline.INDEFINITE);

        canvas.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.W) {
                playerOneYPos -= 10;
            } else if (e.getCode() == KeyCode.S) {
                playerOneYPos += 10;
            } else if (e.getCode() == KeyCode.UP) {
                playerTwoYPos -= 10;
            } else if (e.getCode() == KeyCode.DOWN) {
                playerTwoYPos += 10;
            }
        });

        canvas.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                gameStarted = true;
            }
        });

        canvas.setFocusTraversable(true);
        canvas.requestFocus();
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();

        tl.play();
    }

    private void run(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(25));

        if (gameStarted) {
            ballXPos += ballXSpeed;
            ballYPos += ballYSpeed;

            gc.fillOval(ballXPos, ballYPos, BALL_RADIUS, BALL_RADIUS);
        } else {
            gc.setStroke(Color.WHITE);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.setFont(Font.font("Gabriola", FontWeight.EXTRA_BOLD, 40));
            gc.strokeText("Presiona espacio para comenzar", WIDTH / 2, HEIGHT / 2);

            ballXPos = WIDTH / 2;
            ballYPos = HEIGHT / 2;

            ballXSpeed = new Random().nextInt(2) == 0 ? 1 : -1;
            ballYSpeed = new Random().nextInt(2) == 0 ? 1 : -1;
        }

        if (ballYPos > HEIGHT || ballYPos < 0) ballYSpeed *= -1;

        if (ballXPos < playerOneXPos - PLAYER_WIDTH) {
            scoreP2++;
            gameStarted = false;
        }

        if (ballXPos > playerTwoXPos + PLAYER_WIDTH) {
            scoreP1++;
            gameStarted = false;
        }

        if (((ballXPos + BALL_RADIUS > playerTwoXPos) && ballYPos >= playerTwoYPos && ballYPos <= playerTwoYPos + PLAYER_HEIGHT) ||
                ((ballXPos < playerOneXPos + PLAYER_WIDTH) && ballYPos >= playerOneYPos && ballYPos <= playerOneYPos + PLAYER_HEIGHT)) {
            ballYSpeed += 1 * Math.signum(ballYSpeed);
            ballXSpeed += 1 * Math.signum(ballXSpeed);
            ballXSpeed *= -1;
            ballYSpeed *= -1;
        }

        if (playerOneYPos <= 0) {
            playerOneYPos = 0;
        } else if (playerOneYPos >= HEIGHT - PLAYER_HEIGHT) {
            playerOneYPos = HEIGHT - PLAYER_HEIGHT;
        }

        if (playerTwoYPos <= 0) {
            playerTwoYPos = 0;
        } else if (playerTwoYPos >= HEIGHT - PLAYER_HEIGHT) {
            playerTwoYPos = HEIGHT - PLAYER_HEIGHT;
        }

        gc.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 40));
        gc.fillText(scoreP1 + "\t\t\t\t\t\t\t\t" + scoreP2, WIDTH / 2, 100);

        gc.fillRect(playerTwoXPos, playerTwoYPos, PLAYER_WIDTH, PLAYER_HEIGHT);
        gc.fillRect(playerOneXPos, playerOneYPos, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    public static void main(String[] args) {
        launch(args);
    }
}




