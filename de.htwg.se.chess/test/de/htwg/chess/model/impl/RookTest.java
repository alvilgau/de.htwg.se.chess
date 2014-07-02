package de.htwg.chess.model.impl;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import de.htwg.chess.model.impl.Bishop;
import de.htwg.chess.model.impl.Field;
import de.htwg.chess.model.impl.Figure;
import de.htwg.chess.model.impl.Rook;
import de.htwg.chess.model.impl.Figure.Team;

public class RookTest {
	
	Figure turm;
	Field field[][];
	
	@Before
	public void setUp() {
		turm = new Rook(1, 2, Team.white);
		field = new Field[3][8];
		
		for(int i = 0; i < 8; i++) {
			field[0][i] = new Field();
			field[1][i] = new Field();
			field[2][i] = new Field();
		}
	}
	
	@Test
	public void testGetPosX() {
		assertEquals(1, turm.getxPos());
	}
	
	@Test
	public void testGetPosY() {
		assertEquals(2, turm.getyPos());
	}
	
	@Test
	public void testPossibleMove() {
		// Move out of field 
		assertFalse(turm.possibleMove(11, 2, field));
		
		// Move out of field 
		assertFalse(turm.possibleMove(2, 15, field));
		
		// Move out of field 
		assertFalse(turm.possibleMove(-3, 2, field));
		
		// Move out of field 
		assertFalse(turm.possibleMove(2, -5, field));
		
		// Move the yPos
		assertTrue(turm.possibleMove(1, 5, field));
		
		// Move the xPos
		assertTrue(turm.possibleMove(2, 2, field));
		
		// Move same position
		assertFalse(turm.possibleMove(1, 2, field));
		
		//Move the xPos and yPos 
		assertFalse(turm.possibleMove(5, 1, field));
	}
	
	@Test
	public void testCheckCollision() {
		// Collision moving up
		assertTrue(turm.possibleMove(1, 4, field));
		field[1][4].setSet(true);
		field[1][4].setFigur(new Bishop(1, 4, Team.white));
		assertFalse(turm.possibleMove(1, 4, field));
		
		// Collision moving down
		assertTrue(turm.possibleMove(1, 0, field));
		field[1][0].setSet(true);
		field[1][0].setFigur(new Bishop(1, 0, Team.white));
		assertFalse(turm.possibleMove(1, 0, field));
		
		// Collision moving right
		assertTrue(turm.possibleMove(2, 2, field));
		field[2][2].setSet(true);
		field[2][2].setFigur(new Bishop(2, 2, Team.white));
		assertFalse(turm.possibleMove(2, 2, field));
		
		// Collision moving left
		assertTrue(turm.possibleMove(0, 2, field));
		field[0][2].setSet(true);
		field[0][2].setFigur(new Bishop(0, 2, Team.white));
		assertFalse(turm.possibleMove(0, 2, field));
	}
	
	@Test
	public void testCheckCollision2(){
		// Collision moving up
		assertTrue(turm.possibleMove(1, 4, field));
		field[1][3].setSet(true);
		field[1][3].setFigur(new Bishop(1, 4, Team.white));
		assertFalse(turm.possibleMove(1, 4, field));
		
		// Collision moving down
		assertTrue(turm.possibleMove(1, 0, field));
		field[1][1].setSet(true);
		field[1][1].setFigur(new Bishop(1, 0, Team.white));
		assertFalse(turm.possibleMove(1, 0, field));
		
		// Collision moving right
		turm.move(0, 2);
		assertTrue(turm.possibleMove(2, 2, field));
		field[1][2].setSet(true);
		field[1][2].setFigur(new Bishop(2, 2, Team.white));
		assertFalse(turm.possibleMove(2, 2, field));
		
		// Collision moving left
		turm.move(2, 4);
		assertTrue(turm.possibleMove(0, 4, field));
		field[1][4].setSet(true);
		field[1][4].setFigur(new Bishop(0, 4, Team.white));
		assertFalse(turm.possibleMove(0, 4, field));
	}
	
	@Test
	public void testToString() {
		assertEquals("T", turm.toString());
	}

}
