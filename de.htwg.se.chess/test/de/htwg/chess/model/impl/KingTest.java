package de.htwg.chess.model.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.chess.model.impl.Field;
import de.htwg.chess.model.impl.Figure;
import de.htwg.chess.model.impl.King;
import de.htwg.chess.model.impl.Queen;
import de.htwg.chess.model.impl.Figure.Team;

public class KingTest {

	Figure koenig;
	Field field[][];
	
	@Before
	public void setUp() {
		koenig = new King(1, 1, Team.white);
		field = new Field[4][8];
		
		for(int i = 0; i < 8; i++) {
			field[0][i] = new Field();
			field[1][i] = new Field();
			field[2][i] = new Field();
			field[3][i] = new Field();
		}
	}
	
	@Test
	public void testGetPosX() {
		assertEquals(1, koenig.getxPos());
	}
	
	@Test
	public void testGetPosY() {
		assertEquals(1, koenig.getyPos());
	}
	
	@Test
	public void testPossibleMove() {
		// Move out of field
		assertFalse(koenig.possibleMove(15, 2, field));
		
		// Move out of field
		assertFalse(koenig.possibleMove(-3, 2, field));
		
		// Move out of field
		assertFalse(koenig.possibleMove(4, 12, field));
		
		// Move out of field
		assertFalse(koenig.possibleMove(5, -1, field));
		
		// Move same position
		assertFalse(koenig.possibleMove(1, 1, field));
		
		// Correct move
		assertTrue(koenig.possibleMove(1, 2, field));
		
		// Wrong move
		assertFalse(koenig.possibleMove(1, 3, field));
		
		// Wrong move
		assertFalse(koenig.possibleMove(3, 1, field));

	}
	
	@Test
	public void testCheckCollision() {
		// Collision with figure of same team
		assertTrue(koenig.possibleMove(2, 2, field));
		field[2][2].setSet(true);
		field[2][2].setFigur(new Queen(2, 2, Team.white));
		assertFalse(koenig.possibleMove(2, 2, field));
		
		// Kill collision
		assertTrue(koenig.possibleMove(0, 0, field));
		field[0][0].setSet(true);
		field[0][0].setFigur(new Queen(0, 0, Team.black));
		assertTrue(koenig.possibleMove(0, 0, field));
	}
	
	@Test
	public void testToString() {
		assertEquals("K", koenig.toString());
	}
}
