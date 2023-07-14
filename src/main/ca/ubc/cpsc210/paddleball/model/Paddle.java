package ca.ubc.cpsc210.paddleball.model;

import java.awt.Color;

/**
 * Represents a paddle
 */
public class Paddle {
    public static final int DIMENSION1 = 26;  // must be even integer // wrong
    public static final int DIMENSION2 = 10;  // wrong
    public static final int DX = 3;
    public static final int Y_POS = PBG.DIMENSION2 - 40;
    public static final Color COLOR = new Color(250, 222, 54);

    private int whichWay;
    private int paddleX;

    // Construct a paddle.
    // EFFECTS: places paddle at position (x, Y_POS) moving right.
    public Paddle(int x) {
        this.paddleX = x;
        whichWay = 1;
    }

    public int getPaddleX() {
        return paddleX;
    }

    // Paddle moves to right
    // MODIFIES: this
    // EFFECTS: paddle is moving right
    public void faceRight() {
        whichWay = 1;
    }

    // Paddle moves to left
    // MODIFIES: this
    // EFFECTS: paddle is moving left
    public void faceLeft() {
        whichWay = -1;
    }

    // Updates the paddle on clock tick
    // MODIFIES: this
    // EFFECTS:  paddle is moved DX units in whatever direction it is facing and is
    //           constrained to remain within boundaries of game
    public void move() {
        paddleX = paddleX + whichWay * DX;
        checkForEdge();
    }

    // Constrains paddle so that it doesn't travel off sides of screen
    // MODIFIES: this
    // EFFECTS: paddle is constrained to remain within vertical boundaries of game
    private void checkForEdge() {
        if (paddleX - DIMENSION1 / 2 < 0) {
            paddleX = DIMENSION1 / 2;
        } else if (paddleX + DIMENSION1 / 2 > PBG.DIMENSION1) {
            paddleX = PBG.DIMENSION1 - DIMENSION1 / 2;
        }
    }
}
