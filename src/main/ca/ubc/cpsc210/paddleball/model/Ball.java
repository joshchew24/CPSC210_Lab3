package ca.ubc.cpsc210.paddleball.model;

import java.awt.Color;
import java.awt.Rectangle;

/*
 * Represents a ball.
 */
public class Ball {
    public static final int SIZE = 20;  // must be even integer
    public static final Color COLOR = new Color(10, 50, 188);

    private double ballX;
    private double ballY;
    private double deltaX;
    private double deltaY;

    // Constructs an ball
    // EFFECTS: ball is positioned at coordinates (x, y) with velocity (2.0, 2.0)
    public Ball(int x, int y) {
        this.ballX = x;
        this.ballY = y;
        deltaX = 2.0;
        deltaY = 2.0;
    }

    public int getBallX() {
        return (int) ballX;
    }

    public int getBallY() {
        return (int) ballY;
    }

    public double getDx() {
        return deltaX;
    }

    public double getDy() {
        return deltaY;
    }

    // Bounce ball off paddle
    // MODIFIES: this
    // EFFECTS: vertical component of ball's velocity is reversed
    public void bounceOffPaddle() {
        deltaY *= -1;
    }

    // Updates ball on clock tick
    // MODIFIES: this
    // EFFECTS:  ball is moved (dx, dy) units
    public void move() {
        ballX = ballX + deltaX;
        ballY = ballY + deltaY;

        checkForWalls();
    }

    // Determines if this ball has collided with the paddle
    // EFFECTS:  returns true if this ball has collided with paddle p,
    //           false otherwise
    public boolean isTouchingPaddle(Paddle p) {
        int pd1 = Paddle.DIMENSION1;
        int pd2 = Paddle.DIMENSION2;
        Rectangle ballBoundingRectangle = new Rectangle(getBallX() - SIZE / 2, getBallY() - SIZE / 2, SIZE, SIZE);
        Rectangle paddleBoundingRectangle = new Rectangle(p.getPaddleX() - pd1 / 2, Paddle.Y_POS - pd2 / 2, pd1, pd2);
        return ballBoundingRectangle.intersects(paddleBoundingRectangle);
    }

    // Constrains ball so that it bounces off top and vertical walls
    // MODIFIES: this
    // EFFECTS: ball is constrained to bounce off top and vertical walls
    private void checkForWalls() {
        if (getBallX() - SIZE / 2 < 0) {
            ballX = SIZE / 2;
            deltaX *= -1;
        } else if (getBallX() + SIZE / 2 > PBG.DIMENSION1) {
            ballX = PBG.DIMENSION1 - SIZE / 2;
            deltaX *= -1;
        } else if (getBallY() - SIZE / 2 < 0) {
            ballY = SIZE / 2;
            deltaY *= -1;
        }
    }
}
