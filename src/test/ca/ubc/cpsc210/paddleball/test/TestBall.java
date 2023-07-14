package ca.ubc.cpsc210.paddleball.test;

import ca.ubc.cpsc210.paddleball.model.Ball;
import ca.ubc.cpsc210.paddleball.model.Paddle;
import ca.ubc.cpsc210.paddleball.model.PBG;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * Unit tests for the Ball class.
 */
class TestBall {
	private static final int XLOC = PBG.DIMENSION1 / 2;
	private static final int YLOC = 50;
	private Ball ball;
	
	@BeforeEach
	void runBefore() {
		ball = new Ball(XLOC, YLOC);
	}
	
	@Test
	void testGetX() {
		assertEquals(XLOC, ball.getBallX());
	}
	
	@Test
	void testGetY() {
		assertEquals(YLOC, ball.getBallY());
	}
	
	@Test
	void testUpdate() {
		final int NUM_UPDATES = 4;
		
		ball.move();
		assertEquals((int) (XLOC + ball.getDx()), ball.getBallX());
		assertEquals((int) (YLOC + ball.getDy()), ball.getBallY());
		
		for(int count = 1; count < NUM_UPDATES; count++) {
			ball.move();
		}

		assertEquals((int) (XLOC + NUM_UPDATES * ball.getDx()), ball.getBallX());
		assertEquals((int) (YLOC + NUM_UPDATES * ball.getDy()), ball.getBallY());
	}

	@Test
	void testBounceOffPaddle() {
		double xVel = ball.getDx();
		double yVel = ball.getDy();
		ball.bounceOffPaddle();
		assertEquals(xVel, ball.getDx());
		assertEquals(-yVel, ball.getDy());
	}
	
	@Test
	void testCollideWith() {
		Paddle p = new Paddle(XLOC);

		Ball b = new Ball(0, 0);
		assertFalse(b.isTouchingPaddle(p));

		b = new Ball(p.getPaddleX(), Paddle.Y_POS);
		assertTrue(b.isTouchingPaddle(p));

		b = new Ball(p.getPaddleX() + Paddle.DIMENSION1 / 2 + Ball.SIZE / 2 - 1, Paddle.Y_POS);
		assertTrue(b.isTouchingPaddle(p));

		b = new Ball(p.getPaddleX() + Paddle.DIMENSION1 / 2 + Ball.SIZE / 2, Paddle.Y_POS);
		assertFalse(b.isTouchingPaddle(p));

		b = new Ball(p.getPaddleX() - Paddle.DIMENSION1 / 2 - Ball.SIZE / 2 + 1, Paddle.Y_POS);
		assertTrue(b.isTouchingPaddle(p));

		b = new Ball(p.getPaddleX() - Paddle.DIMENSION1 / 2 - Ball.SIZE / 2, Paddle.Y_POS);
		assertFalse(b.isTouchingPaddle(p));

		b = new Ball(p.getPaddleX(), Paddle.Y_POS - Paddle.DIMENSION2 / 2 - Ball.SIZE / 2 + 1);
		assertTrue(b.isTouchingPaddle(p));

		b = new Ball(p.getPaddleX(), Paddle.Y_POS - Paddle.DIMENSION2 / 2 - Ball.SIZE / 2);
		assertFalse(b.isTouchingPaddle(p));
	}
}
