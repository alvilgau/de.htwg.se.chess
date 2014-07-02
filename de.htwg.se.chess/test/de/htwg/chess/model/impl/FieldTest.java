package de.htwg.chess.model.impl;

import org.junit.Before;
import org.junit.Test;

import de.htwg.chess.model.impl.Bishop;
import de.htwg.chess.model.impl.Field;
import de.htwg.chess.model.impl.Pawn;
import de.htwg.chess.model.impl.Figure.Team;
import static org.junit.Assert.*;


public class FieldTest {
	
	Field feld;
	Field feld2;
	
	@Before
	public void setUp() {
		feld = new Field();
		feld2 = new Field(true, new Bishop(3, 5, Team.black));
	}
	
	@Test
	public void testIsSet(){
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
