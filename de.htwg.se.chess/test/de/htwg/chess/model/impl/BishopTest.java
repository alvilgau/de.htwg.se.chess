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
		field = new Field[3][8];

		for (int i = 0; i < 8; i++) {
			field[0][i] = new Field();
			field[1][i] = new Field();
			field[2][i] = new Field();
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
	public void testPossibleMove() {
		// Move out of field
		assertFalse(laeufer.possibleMove(11, 4, field));

		// Move out of field
		assertFalse(laeufer.possibleMove(-2, 4, field));

		// Move out of field
		assertFalse(laeufer.possibleMove(3, 12, field));

		// Move out of field
		assertFalse(laeufer.possibleMove(3, -3, field));

		// Move same position
		assertFalse(laeufer.possibleMove(3, 4, field));

		// Correct move
		assertTrue(laeufer.possibleMove(2, 5, field));

		// Correct move
		assertTrue(laeufer.possibleMove(2, 3, field));

		// Wrong move
		assertFalse(laeufer.possibleMove(2, 4, field));

		// Wrong move
		assertFalse(laeufer.possibleMove(3, 5, field));
	}

	@Test
	public void testCheckCollision() {
		// Check collision moving up left
		assertTrue(laeufer.possibleMove(0, 5, field));
		field[0][5].setSet(true);
		field[0][5].setFigur(new Bishop(0, 5, Team.white));
		assertFalse(laeufer.possibleMove(0, 5, field));

		// Check collision moving up right
		assertTrue(laeufer.possibleMove(2, 5, field));
		field[2][5].setSet(true);
		field[2][5].setFigur(new Bishop(2, 5, Team.white));
		assertFalse(laeufer.possibleMove(2, 5, field));

		// Check collision moving down left
		assertTrue(laeufer.possibleMove(0, 3, field));
		field[0][3].setSet(true);
		field[0][3].setFigur(new Bishop(0, 3, Team.white));
		assertFalse(laeufer.possibleMove(0, 3, field));

		// Check collision moving down right
		assertTrue(laeufer.possibleMove(2, 3, field));
		field[2][3].setSet(true);
		field[2][3].setFigur(new Bishop(2, 3, Team.white));
		assertFalse(laeufer.possibleMove(2, 3, field));
	}

	@Test
	public void testCheckCollision2() {
		// Check collision moving up left
		laeufer.move(2, 2);
		assertTrue(laeufer.possibleMove(0, 4, field));
		field[1][3].setSet(true);
		field[1][3].setFigur(new Bishop(0, 4, Team.white));
		assertFalse(laeufer.possibleMove(0, 4, field));

		// Check collision moving up right
		laeufer.move(0, 0);
		assertTrue(laeufer.possibleMove(2, 2, field));
		field[1][1].setSet(true);
		field[1][1].setFigur(new Bishop(2, 2, Team.white));
		assertFalse(laeufer.possibleMove(2, 2, field));

		// Check collision moving down left
		laeufer.move(2, 3);
		assertTrue(laeufer.possibleMove(0, 1, field));
		field[1][2].setSet(true);
		field[1][2].setFigur(new Bishop(0, 1, Team.white));
		assertFalse(laeufer.possibleMove(0, 1, field));

		// Check collision moving down right
		laeufer.move(0, 5);
		assertTrue(laeufer.possibleMove(2, 3, field));
		field[1][4].setSet(true);
		field[1][4].setFigur(new Bishop(2, 3, Team.white));
		assertFalse(laeufer.possibleMove(2, 3, field));
	}

	@Test
	public void testToString() {
		assertEquals("L", laeufer.toString());
	}

	@Test
	public void testLoadImage() {
		laeufer.loadImages("res/whiteBishop.png", "res/blackBishop.png");
		laeufer.loadImages("wrong", "path");
	}

}
