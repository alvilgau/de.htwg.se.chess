package de.htwg.chess.controller.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ControllerTest {

	ChessController controller;
	String start;

	@Before
	public void setUp() {
		controller = new ChessController();
		start = controller.toString();
	}

	@Test
	public void testToString() {
		assertTrue(controller.toString().contains("T  P  L  D  K  L  P  T  "));
	}

	@Test
	public void testSetExchange() {
		assertFalse(controller.getExchange());
		controller.setExchange(true);
		assertTrue(controller.getExchange());
	}

	@Test
	public void testSelect() {
		// Invalid select
		controller.select(0, 3);
		controller.move(3, 3);
		assertFalse(controller.isSelect());
		assertTrue(start.equals(controller.toString()));

		// Invalid select
		controller.select(0, 6);
		controller.move(0, 5);
		assertTrue(start.equals(controller.toString()));
	}

	@Test
	public void testSelectedPos() {
		controller.select(0, 1);
		assertEquals(68, controller.getSelectedPosX());
		assertEquals(668, controller.getSelectedPosY());
	}

	@Test
	public void testMove() {
		// Correct move
		controller.select(1, 0);
		controller.move(2, 2);
		assertFalse(start.equals(controller.toString()));
		start = controller.toString();

		// Correct move
		controller.select(0, 6);
		controller.move(0, 5);
		assertFalse(start.equals(controller.toString()));
		start = controller.toString();

		// Invalid move
		controller.select(0, 1);
		controller.move(3, 3);
		assertTrue(start.equals(controller.toString()));
	}

	@Test
	public void testRestart() {
		controller.select(1, 0);
		controller.move(2, 2);
		assertFalse(start.equals(controller.toString()));

		// Restart
		controller.restart();
		assertEquals(start, controller.toString());
	}

	@Test
	public void testHandleMovement() {
		// Select
		controller.handleMovement(0, 1);
		// Move
		controller.handleMovement(0, 3);
		assertFalse(start.equals(controller.toString()));
		start = controller.toString();

		// Fail handle because of exchange
		controller.setExchange(true);
		controller.handleMovement(0, 3);
		controller.handleMovement(0, 5);
		assertTrue(start.equals(controller.toString()));
		controller.setExchange(false);

		// Fail handle because of game over
		controller.setGameover(true);
		controller.handleMovement(0, 3);
		controller.handleMovement(0, 5);
		assertTrue(start.equals(controller.toString()));
	}

	@Test
	public void testExchange() {
		controller.select(0, 1);
		controller.move(0, 3);
		controller.select(0, 7);

		// Exchange with Knight
		controller.exchangeKnight();
		assertTrue(controller.toString().contains("P  P  L  D  K  L  P  T  "));

		// Exchange with Bishop
		controller.exchangeBishop();
		assertTrue(controller.toString().contains("L  P  L  D  K  L  P  T  "));

		// Exchange with Rook
		controller.exchangeRook();
		assertTrue(controller.toString().contains("T  P  L  D  K  L  P  T  "));

		// Exchange with Queen
		controller.exchangeQueen();
		assertTrue(controller.toString().contains("D  P  L  D  K  L  P  T  "));
	}

	@Test
	public void testGetStatusMessage() {
		assertEquals("Welcome to Chess", controller.getStatusMessage());
	}

	@Test
	public void testGetFieldSize() {
		assertEquals(8, controller.getFieldSize());
	}

	@Test
	public void testUpdateCheckmateWhite() {
		// Move team white
		controller.select(4, 1);
		controller.move(4, 3);

		// Move team black
		controller.select(3, 6);
		controller.move(3, 4);

		// Move team white
		controller.select(4, 3);
		controller.move(4, 4);

		// Move team black
		controller.select(3, 7);
		controller.move(3, 5);

		// Move team white
		controller.select(0, 1);
		controller.move(0, 2);

		// Move team black
		controller.select(3, 5);
		controller.move(4, 4);

		// Move team white
		controller.select(5, 0);
		controller.move(4, 1);

		// Move team black
		controller.select(0, 6);
		controller.move(0, 5);
		assertFalse(controller.isGameover());

		// Move team white
		controller.select(4, 1);
		controller.move(3, 2);
		assertTrue(controller.isGameover());
	}

	@Test
	public void testUpdateCheckmateBlack() {
		// Move team white
		controller.select(3, 1);
		controller.move(3, 3);

		// Move team black
		controller.select(4, 6);
		controller.move(4, 4);

		// Move team white
		controller.select(3, 0);
		controller.move(3, 2);

		// Move team black
		controller.select(4, 4);
		controller.move(4, 3);

		// Move team white
		controller.select(3, 2);
		controller.move(4, 3);

		// Move team black
		controller.select(3, 7);
		controller.move(4, 6);

		// Move team white
		controller.select(0, 1);
		controller.move(0, 2);
		assertFalse(controller.isGameover());

		// Move team black
		controller.select(4, 6);
		controller.move(5, 5);
		assertTrue(controller.isGameover());
	}

	@Test
	public void testGetTurnMessage() {
		assertEquals("Team white's turn", controller.getTurnMessage());
	}

	@Test
	public void testGetFieldValue() {
		String fieldValue;
		fieldValue = controller.getFieldValue(0, 0);
		assertEquals(fieldValue, "whiteT");

		fieldValue = controller.getFieldValue(0, 1);
		assertEquals(fieldValue, "whiteB");

		fieldValue = controller.getFieldValue(1, 7);
		assertEquals(fieldValue, "blackP");

		fieldValue = controller.getFieldValue(3, 3);
		assertEquals(fieldValue, "empty");
	}
}
