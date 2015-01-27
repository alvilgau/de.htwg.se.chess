package de.htwg.chess.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.chess.model.impl.Figure.Team;

public class KnightTest {

	Figure springer;
	Field field[][];

	@Before
	public void setUp() {
		springer = new Knight(1, 3, Team.white);
		field = new Field[4][8];

		for (int i = 0; i < 8; i++) {
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
	public void testPossibleMoves() {
		// Collision with figure of the same team
		assertTrue(springer.getPossibleMoves(field).contains(field[3][4]));
		field[3][4].setSet(true);
		field[3][4].setFigur(new Bishop(3, 4, Team.white));
		assertFalse(springer.getPossibleMoves(field).contains(field[3][4]));

		// Kill collision
		assertTrue(springer.getPossibleMoves(field).contains(field[0][5]));
		field[0][5].setSet(true);
		field[0][5].setFigur(new Bishop(3, 4, Team.black));
		assertTrue(springer.getPossibleMoves(field).contains(field[0][5]));
	}

	@Test
	public void testToString() {
		assertEquals("P", springer.toString());
	}
}
