package de.htwg.chess.model.impl;

import org.junit.Before;
import org.junit.Test;

import de.htwg.chess.model.impl.Bishop;
import de.htwg.chess.model.impl.Field;
import de.htwg.chess.model.impl.Figure;
import de.htwg.chess.model.impl.Pawn;
import de.htwg.chess.model.impl.Queen;
import de.htwg.chess.model.impl.Figure.Team;
import static org.junit.Assert.*;

public class PawnTest {

	Figure bauer;
	Figure bauer2;
	Field field[][];
	
	@Before
	public void setUp() {
		bauer = new Pawn(0, 1, Team.white, 1);
		bauer2 = new Pawn(0, 6, Team.black, 1);
		field = new Field[2][8];
		for(int i = 0; i < 7; i++) {
			field[0][i] = new Field();
			field[1][i] = new Field();
		}
	}
	
	@Test
	public void testGetPosX() {
		assertEquals(0, bauer.getxPos());
	}
	
	@Test
	public void testGetPosY() {
		assertEquals(1, bauer.getyPos());
	}
	
	@Test
	public void testGetTeam() {
		assertEquals("white", bauer.getTeam());
	}
	
	@Test
	public void testPossibleMove() {	
		// Move out of field 	
		assertFalse(bauer.possibleMove(9, 1, field));
		
		// Move out of field
		assertFalse(bauer.possibleMove(-5, 1, field));
		
		// Move out of field 
		assertFalse(bauer.possibleMove(0, 15, field));
		
		// Move out of field 
		assertFalse(bauer.possibleMove(0, -3, field));
		
		// Move back	
		assertFalse(bauer.possibleMove(0, 0, field));
		
		// Move 2 fields from the start point
		assertTrue(bauer.possibleMove(0, 3, field));
		
		// Move 3 fields
		assertFalse(bauer.possibleMove(0, 6, field));
		
		// Move 1 field
		assertTrue(bauer.possibleMove(0, 2, field));
		
		// Invalid horizontal move
		assertFalse(bauer.possibleMove(4, 2, field));
		
		// Invalid horizontal move
		assertFalse(bauer.possibleMove(1, 3, field));
		
		// Invalid Move 2 fields
		assertFalse(bauer2.possibleMove(0, 4, field));
		
	}
	
	@Test
	public void checkCollision() {
		// move by team white
		assertTrue(bauer.possibleMove(0, 3, field));
		field[0][3].setSet(true);
		field[0][3].setFigur(new Bishop(0, 3, Team.black));
		assertFalse(bauer.possibleMove(0, 3, field));
		
		// move by team black
		assertTrue(bauer2.possibleMove(0, 5, field));
		field[0][5].setSet(true);
		field[0][5].setFigur(new Bishop(0, 5, Team.white));
		assertFalse(bauer2.possibleMove(0, 5, field));
		
		// Kill collision
		assertFalse(bauer.possibleMove(1, 2, field));
		field[1][2].setSet(true);
		field[1][2].setFigur(new Queen(1, 2, Team.black));
		assertTrue(bauer.possibleMove(1, 2, field));
		
		// Invalid Kill collision
		assertFalse(bauer2.possibleMove(1, 5, field));
		field[1][5].setSet(true);
		field[1][5].setFigur(new Queen(1, 5, Team.black));
		assertFalse(bauer2.possibleMove(1, 5, field));
	}
	
	@Test
	public void testCheckExchange() {
		// White Pawn reaches end
		assertFalse(bauer.move(0, 5));
		assertTrue(bauer.move(0, 7));
		
		// Black Pawn reaches end
		assertFalse(bauer2.move(0, 2));
		assertTrue(bauer2.move(0, 0));
	}
	
	@Test
	public void testToString() {
		assertEquals("B", bauer.toString());
	}
}
