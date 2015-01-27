package de.htwg.chess.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.chess.model.impl.Figure.Team;

public class RookTest {

	Figure turm;
	Field field[][];

	@Before
	public void setUp() {
		turm = new Rook(1, 2, Team.white);
		field = new Field[8][8];

		for (int i = 0; i < 8; i++) {
			field[0][i] = new Field();
			field[1][i] = new Field();
			field[2][i] = new Field();
			field[3][i] = new Field();
			field[4][i] = new Field();
			field[5][i] = new Field();
			field[6][i] = new Field();
			field[7][i] = new Field();
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
	public void testPossibleMoves() {
		// Collision moving up
		assertTrue(turm.getPossibleMoves(field).contains(field[1][4]));
		field[1][4].setSet(true);
		field[1][4].setFigur(new Bishop(1, 4, Team.white));
		assertFalse(turm.getPossibleMoves(field).contains(field[1][4]));

		// Collision moving down
		assertTrue(turm.getPossibleMoves(field).contains(field[1][0]));
		field[1][0].setSet(true);
		field[1][0].setFigur(new Bishop(1, 0, Team.white));
		assertFalse(turm.getPossibleMoves(field).contains(field[1][0]));

		// Collision moving right
		assertTrue(turm.getPossibleMoves(field).contains(field[2][2]));
		field[2][2].setSet(true);
		field[2][2].setFigur(new Bishop(2, 2, Team.white));
		assertFalse(turm.getPossibleMoves(field).contains(field[2][2]));

		// Collision moving left
		assertTrue(turm.getPossibleMoves(field).contains(field[0][2]));
		field[0][2].setSet(true);
		field[0][2].setFigur(new Bishop(0, 2, Team.white));
		assertFalse(turm.getPossibleMoves(field).contains(field[0][2]));
	}

	@Test
	public void testToString() {
		assertEquals("T", turm.toString());
	}

}
