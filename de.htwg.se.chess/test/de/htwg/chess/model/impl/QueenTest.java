package de.htwg.chess.model.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.chess.model.impl.Bishop;
import de.htwg.chess.model.impl.Field;
import de.htwg.chess.model.impl.Figure;
import de.htwg.chess.model.impl.Queen;
import de.htwg.chess.model.impl.Figure.Team;

public class QueenTest {

	Figure dame;
	Field field[][];
	
	@Before
	public void setUp() {
		dame = new Queen(1, 4, Team.black);
		field = new Field[3][8];
		
		for(int i = 0; i < 8; i++) {
			field[0][i] = new Field();
			field[1][i] = new Field();
			field[2][i] = new Field();
		}
	}
	
	@Test
	public void testGetPosX() {
		assertEquals(1, dame.getxPos());
	}
	
	@Test
	public void testGetPosY() {
		assertEquals(4, dame.getyPos());
	}
	
	@Test
	public void testPossibleMove() {
		// Move out of field
		assertFalse(dame.possibleMove(15, 2, field));
		
		// Move out of field
		assertFalse(dame.possibleMove(-3, 2, field));
		
		// Move out of field
		assertFalse(dame.possibleMove(4, 12, field));
		
		// Move out of field
		assertFalse(dame.possibleMove(5, -1, field));
		
		// Move same position
		assertFalse(dame.possibleMove(1, 4, field));
		
		// Correct move diagonal
		assertTrue(dame.possibleMove(2, 3, field));
		
		// Correct move vertical
		assertTrue(dame.possibleMove(1, 5, field));
		
		// Correct move horizontal
		assertTrue(dame.possibleMove(2, 4, field));
		
		// Wrong move
		assertFalse(dame.possibleMove(2, 6, field));
	}
	
	@Test
	public void testCheckCollision() {
		// Check Collision moving up
		assertTrue(dame.possibleMove(1, 5, field));
		field[1][5].setSet(true);
		field[1][5].setFigur(new Bishop(1, 5, Team.black));
		assertFalse(dame.possibleMove(1, 5, field));
		
		// Check Collision moving down
		assertTrue(dame.possibleMove(1, 3, field));
		field[1][3].setSet(true);
		field[1][3].setFigur(new Bishop(1, 3, Team.black));
		assertFalse(dame.possibleMove(1, 3, field));
		
		// Check Collision moving right
		assertTrue(dame.possibleMove(2, 4, field));
		field[2][4].setSet(true);
		field[2][4].setFigur(new Bishop(2, 4, Team.black));
		assertFalse(dame.possibleMove(2, 4, field));
		
		// Check Collision moving left
		assertTrue(dame.possibleMove(0, 4, field));
		field[0][4].setSet(true);
		field[0][4].setFigur(new Bishop(0, 4, Team.black));
		assertFalse(dame.possibleMove(0, 4, field));
		
		// Check collision moving up left
		assertTrue(dame.possibleMove(0, 5, field));
		field[0][5].setSet(true);
		field[0][5].setFigur(new Bishop(0, 5, Team.black));
		assertFalse(dame.possibleMove(0, 5, field));
		
		// Check collision moving up right
		assertTrue(dame.possibleMove(2, 5, field));
		field[2][5].setSet(true);
		field[2][5].setFigur(new Bishop(2, 5, Team.black));
		assertFalse(dame.possibleMove(2, 5, field));
		
		// Check collision moving down left
		assertTrue(dame.possibleMove(0, 3, field));
		field[0][3].setSet(true);
		field[0][3].setFigur(new Bishop(0, 3, Team.black));
		assertFalse(dame.possibleMove(0, 3, field));
		
		// Check collision moving down right
		assertTrue(dame.possibleMove(2, 3, field));
		field[2][3].setSet(true);
		field[2][3].setFigur(new Bishop(2, 3, Team.black));
		assertFalse(dame.possibleMove(2, 3, field));
	}
	
	@Test
	public void testToString() {
		assertEquals("D", dame.toString());
	}
	
}
