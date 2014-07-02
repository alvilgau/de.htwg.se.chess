package de.htwg.chess.model.impl;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import de.htwg.chess.model.impl.Bishop;
import de.htwg.chess.model.impl.Field;
import de.htwg.chess.model.impl.Figure;
import de.htwg.chess.model.impl.Knight;
import de.htwg.chess.model.impl.Figure.Team;

public class KnightTest {
	
	Figure springer;
	Field field[][];
	
	@Before
	public void setUp() {
		springer = new Knight(1, 3, Team.white);
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
		assertEquals(1, springer.getxPos());
	}
	
	@Test
	public void testGetPosY() {
		assertEquals(3, springer.getyPos());
	}
	
	@Test
	public void testPossibleMove() {
		// Move out of field
		assertFalse(springer.possibleMove(15, 2, field));
		
		// Move out of field
		assertFalse(springer.possibleMove(-3, 2, field));
		
		// Move out of field
		assertFalse(springer.possibleMove(4, 12, field));
		
		// Move out of field
		assertFalse(springer.possibleMove(5, -1, field));
		
		// Move same position
		assertFalse(springer.possibleMove(1, 3, field));
		
		// Correct move
		assertTrue(springer.possibleMove(2, 1, field));
		
		// Correct move
		assertTrue(springer.possibleMove(3, 2, field));
		
		// Wrong move
		assertFalse(springer.possibleMove(3, 3, field));
		
		// Wrong move
		assertFalse(springer.possibleMove(3, 5, field));
	}
	
	@Test
	public void testCheckCollision() {
		// Collision with figure of the same team
		assertTrue(springer.possibleMove(3, 4, field));
		field[3][4].setSet(true);
		field[3][4].setFigur(new Bishop(3, 4, Team.white));
		assertFalse(springer.possibleMove(3, 4, field));
		
		// Kill collision
		assertTrue(springer.possibleMove(0, 5, field));
		field[0][5].setSet(true);
		field[0][5].setFigur(new Bishop(3, 4, Team.black));
		assertTrue(springer.possibleMove(0, 5, field));
	}
	
	@Test
	public void testToString() {
		assertEquals("P", springer.toString());
	}
}
