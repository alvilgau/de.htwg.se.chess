package de.htwg.chess.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.chess.model.impl.Figure.Team;

public class FieldTest {

	Field feld;
	Field feld2;
	Field feld3;

	@Before
	public void setUp() {
		feld = new Field();
		feld2 = new Field(true, new Bishop(3, 5, Team.black));
		feld3 = new Field(1, 1);
	}

	@Test
	public void testPosition() {
		assertEquals(feld2.getxPos(), feld2.getFigur().getxPos());
		assertEquals(feld2.getyPos(), feld2.getFigur().getyPos());
		assertEquals(feld3.getxPos(), 1);
	}

	@Test
	public void testIsSet() {
		assertEquals(false, feld.isSet());
		feld.setSet(true);
		assertEquals(true, feld.isSet());
		assertTrue(feld2.isSet());
	}

	@Test
	public void testGetFigur() {
		feld.setFigur(new Pawn(5, 4, Team.white, 1));
		assertNotNull(feld.getFigur());
		assertNotNull(feld2.getFigur());
	}

	@Test
	public void testToString() {
		assertEquals("L", feld2.toString());
		assertEquals("-", feld.toString());
	}

	@Test
	public void testIsKing() {
		assertFalse(feld2.isKing());
		feld2.setFigur(new King(0, 5, Team.black));
		assertTrue(feld2.isKing());
	}

	@Test
	public void testReset() {
		feld2.clear();
		assertFalse(feld2.isSet());
	}

}
