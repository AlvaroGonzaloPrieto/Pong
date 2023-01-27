package com.alvaro.pong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private final int HEIGHT = 800;
    private final int PADDLE_SPEED = 20;

    @Override
    public void start(Stage stage) throws IOException {
        stage = new Stage();

        int WIDTH = 800;
        Scene scene = new Scene(new Group(), WIDTH, HEIGHT);

        stage.setScene(scene);
        stage.setTitle("Pong by Alvaro");
        stage.show();

        Rectangle leftPaddle = new Rectangle(20, 200);
        Rectangle rightPaddle = new Rectangle(20, 200);
        Circle ball = new Circle(10);

        Group root = (Group) scene.getRoot();
        root.getChildren().addAll(leftPaddle, rightPaddle, ball);

        rightPaddle.setX(WIDTH - 20);
        ball.setCenterX(WIDTH / 2);
        ball.setCenterY(HEIGHT / 2);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    if (leftPaddle.getY() - PADDLE_SPEED <= 0) {
                        leftPaddle.setY(0);
                    } else {
                        leftPaddle.setY(leftPaddle.getY() - PADDLE_SPEED);
                    }
                    break;
                case S:
                    if (leftPaddle.getY() + 200 + PADDLE_SPEED >= HEIGHT) {
                        leftPaddle.setY(HEIGHT - 200);
                    } else {
                        leftPaddle.setY(leftPaddle.getY() + PADDLE_SPEED);
                    }
                    break;
                case UP:
                    if (rightPaddle.getY() - PADDLE_SPEED <= 0) {
                        rightPaddle.setY(0);
                    } else {
                        rightPaddle.setY(rightPaddle.getY() - PADDLE_SPEED);
                    }
                    break;
                case DOWN:
                    if (rightPaddle.getY() + 200 + PADDLE_SPEED >= HEIGHT) {
                        rightPaddle.setY(HEIGHT - 200);
                    } else {
                        rightPaddle.setY(rightPaddle.getY() + PADDLE_SPEED);
                    }
                    break;
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}