package ca.ubc.cpsc210.paddleball.test;

import ca.ubc.cpsc210.paddleball.model.PBG;
import ca.ubc.cpsc210.paddleball.model.Paddle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Unit tests for the Paddle class.
 */
class TestPaddle {
	private static final int XLOC = PBG.DIMENSION1 / 2;
	private Paddle p;
	
	@BeforeEach
	void runBefore() {
		p = new Paddle(XLOC);
	}
	
	@Test
	void testGetX() {
		assertEquals(XLOC, p.getPaddleX());
	}
	
	@Test
	void testUpdate() {
		final int NUM_UPDATES = 8;
		
		p.move();
		assertEquals(XLOC + Paddle.DX, p.getPaddleX());
		
		for(int count = 1; count < NUM_UPDATES; count++) {
			p.move();
		}
		
		assertEquals(XLOC + NUM_UPDATES * Paddle.DX, p.getPaddleX());
	}
	
	@Test
	void testFlipDirection() {
		p.move();
		assertEquals(XLOC + Paddle.DX, p.getPaddleX());
		p.faceLeft();
		p.move();
		assertEquals(XLOC, p.getPaddleX());
		p.faceRight();
		p.move();
		assertEquals(XLOC + Paddle.DX, p.getPaddleX());
	}
	
	@Test 
	void testLeftBoundary() {
		p.faceLeft();
		for(int count = 0; count < (PBG.DIMENSION1 / 2 - Paddle.DIMENSION1 / 2) / Paddle.DX + 1; count++)
			p.move();
		assertEquals(Paddle.DIMENSION1 / 2, p.getPaddleX());
		p.move();
		assertEquals(Paddle.DIMENSION1 / 2, p.getPaddleX());
	}
	
	@Test
	void testRightBoundary() {
		p.faceRight();
		for(int count = 0; count < (PBG.DIMENSION1 / 2 - Paddle.DIMENSION1 / 2) / Paddle.DX + 1; count++)
			p.move();
		assertEquals(PBG.DIMENSION1 - Paddle.DIMENSION1 / 2, p.getPaddleX());
		p.move();
		assertEquals(PBG.DIMENSION1 - Paddle.DIMENSION1 / 2, p.getPaddleX());
	}
}
