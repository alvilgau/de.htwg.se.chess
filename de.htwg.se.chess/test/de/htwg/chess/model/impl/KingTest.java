package de.htwg.chess.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.chess.model.impl.Figure.Team;

public class KingTest {

	Figure koenig;
	Field field[][];

	@Before
	public void setUp() {
		koenig = new King(0, 0, Team.white);
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
		assertEquals(0, koenig.getxPos());
	}

	@Test
	public void testGetPosY() {
		assertEquals(0, koenig.getyPos());
	}

	@Test
	public void testPossibleMoves() {
		// Collision with figure of same team
		assertTrue(koenig.getPossibleMoves(field).contains(field[1][1]));
		field[1][1].setSet(true);
		field[1][1].setFigur(new Queen(2, 1, Team.white));
		assertFalse(koenig.getPossibleMoves(field).contains(field[1][1]));

		// Kill collision
		assertTrue(koenig.getPossibleMoves(field).contains(field[0][1]));
		field[0][1].setSet(true);
		field[0][1].setFigur(new Queen(0, 1, Team.black));
		assertTrue(koenig.getPossibleMoves(field).contains(field[0][1]));
	}

	@Test
	public void testToString() {
		assertEquals("K", koenig.toString());
	}
}
