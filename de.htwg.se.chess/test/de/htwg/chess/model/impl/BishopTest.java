package de.htwg.chess.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.chess.model.impl.Figure.Team;

public class BishopTest {

	Figure laeufer;
	Field field[][];

	@Before
	public void setUp() {
		laeufer = new Bishop(1, 4, Team.white);
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
		assertEquals(1, laeufer.getxPos());
	}

	@Test
	public void testGetPosY() {
		assertEquals(4, laeufer.getyPos());
	}

	@Test
	public void testGetTeam() {
		assertEquals("white", laeufer.getTeam());
	}

	@Test
	public void testPossibleMoves() {
		// Check collision moving up left
		assertTrue(laeufer.getPossibleMoves(field).contains(field[0][5]));
		field[0][5].setSet(true);
		field[0][5].setFigur(new Bishop(0, 5, Team.white));
		assertFalse(laeufer.getPossibleMoves(field).contains(field[0][5]));

		// Check collision moving up right
		assertTrue(laeufer.getPossibleMoves(field).contains(field[2][5]));
		field[2][5].setSet(true);
		field[2][5].setFigur(new Bishop(2, 5, Team.white));
		assertFalse(laeufer.getPossibleMoves(field).contains(field[2][5]));

		// Check collision moving down left
		assertTrue(laeufer.getPossibleMoves(field).contains(field[0][3]));
		field[0][3].setSet(true);
		field[0][3].setFigur(new Bishop(0, 3, Team.white));
		assertFalse(laeufer.getPossibleMoves(field).contains(field[0][3]));

		// Check collision moving down right
		assertTrue(laeufer.getPossibleMoves(field).contains(field[2][3]));
		field[2][3].setSet(true);
		field[2][3].setFigur(new Bishop(2, 3, Team.white));
		assertFalse(laeufer.getPossibleMoves(field).contains(field[2][3]));
	}

	@Test
	public void testToString() {
		assertEquals("L", laeufer.toString());
	}

}
