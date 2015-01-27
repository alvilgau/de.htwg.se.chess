package de.htwg.chess.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.chess.model.impl.Figure.Team;

public class QueenTest {

	Figure dame;
	Field field[][];

	@Before
	public void setUp() {
		dame = new Queen(1, 4, Team.black);
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
		assertEquals(1, dame.getxPos());
	}

	@Test
	public void testGetPosY() {
		assertEquals(4, dame.getyPos());
	}

	@Test
	public void testPossibleMoves() {
		// Check Collision moving up
		assertTrue(dame.getPossibleMoves(field).contains(field[1][5]));
		field[1][5].setSet(true);
		field[1][5].setFigur(new Bishop(1, 5, Team.black));
		assertFalse(dame.getPossibleMoves(field).contains(field[1][5]));

		// Check Collision moving down
		assertTrue(dame.getPossibleMoves(field).contains(field[1][3]));
		field[1][3].setSet(true);
		field[1][3].setFigur(new Bishop(1, 3, Team.black));
		assertFalse(dame.getPossibleMoves(field).contains(field[1][3]));

		// Check Collision moving right
		assertTrue(dame.getPossibleMoves(field).contains(field[2][4]));
		field[2][4].setSet(true);
		field[2][4].setFigur(new Bishop(2, 4, Team.black));
		assertFalse(dame.getPossibleMoves(field).contains(field[2][4]));

		// Check Collision moving left
		assertTrue(dame.getPossibleMoves(field).contains(field[0][4]));
		field[0][4].setSet(true);
		field[0][4].setFigur(new Bishop(0, 4, Team.black));
		assertFalse(dame.getPossibleMoves(field).contains(field[0][4]));

		// Check collision moving up left
		assertTrue(dame.getPossibleMoves(field).contains(field[0][5]));
		field[0][5].setSet(true);
		field[0][5].setFigur(new Bishop(0, 5, Team.black));
		assertFalse(dame.getPossibleMoves(field).contains(field[0][5]));

		// Check collision moving up right
		assertTrue(dame.getPossibleMoves(field).contains(field[2][5]));
		field[2][5].setSet(true);
		field[2][5].setFigur(new Bishop(2, 5, Team.black));
		assertFalse(dame.getPossibleMoves(field).contains(field[2][5]));

		// Check collision moving down left
		assertTrue(dame.getPossibleMoves(field).contains(field[0][3]));
		field[0][3].setSet(true);
		field[0][3].setFigur(new Bishop(0, 3, Team.black));
		assertFalse(dame.getPossibleMoves(field).contains(field[0][3]));

		// Check collision moving down right
		assertTrue(dame.getPossibleMoves(field).contains(field[2][3]));
		field[2][3].setSet(true);
		field[2][3].setFigur(new Bishop(2, 3, Team.black));
		assertFalse(dame.getPossibleMoves(field).contains(field[2][3]));
	}

	@Test
	public void testToString() {
		assertEquals("D", dame.toString());
	}

}
