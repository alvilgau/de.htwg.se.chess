package de.htwg.chess.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.htwg.chess.model.IField;
import de.htwg.chess.model.impl.Figure.Team;

public class PawnTest {

	Figure bauer;
	Figure bauer2;
	Figure bauer3;
	Figure bauer4;
	Field field[][];

	@Before
	public void setUp() {
		bauer = new Pawn(1, 1, Team.white, 1);
		bauer2 = new Pawn(2, 2, Team.black, 6);
		bauer3 = new Pawn(0, 7, Team.white, 1);
		bauer4 = new Pawn(1, 6, Team.black, 6);
		field = new Field[8][8];
		for (int i = 0; i <= 7; i++) {
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
		assertEquals(1, bauer.getxPos());
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
	public void testPossibleMoves() {
		field[1][1].setFigur(bauer);
		field[1][1].setSet(true);
		field[2][2].setFigur(bauer2);
		field[2][2].setSet(true);
		field[0][2].setFigur(new Pawn(0, 2, Team.white, 1));
		field[0][2].setSet(true);
		field[0][7].setFigur(bauer3);
		field[0][7].setSet(true);
		field[1][6].setFigur(bauer4);
		field[1][6].setSet(true);

		List<IField> possibleMoves = bauer.getPossibleMoves(field);
		List<IField> possibleMoves2 = bauer2.getPossibleMoves(field);
		List<IField> possibleMoves3 = bauer3.getPossibleMoves(field);
		List<IField> possibleMoves4 = bauer4.getPossibleMoves(field);

		// Move back
		assertFalse(possibleMoves.contains(field[1][0]));

		// Move 1 field
		assertTrue(possibleMoves.contains(field[1][2]));
		assertTrue(possibleMoves2.contains(field[2][1]));

		// Move 2 fields from the start point
		assertTrue(possibleMoves.contains(field[1][3]));
		assertTrue(possibleMoves4.contains(field[1][4]));

		// Move 3 fields
		assertFalse(possibleMoves.contains(field[1][4]));

		// Move diagonally for a kill
		assertTrue(possibleMoves.contains(field[1][2]));

		// Move on figure of the same team
		assertFalse(possibleMoves.contains(field[0][2]));

		// No possible moves availabe
		assertTrue(possibleMoves3.isEmpty());
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
