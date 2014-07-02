package de.htwg.chess.controller.impl;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import de.htwg.chess.controller.impl.Checkmate.CheckMate;
import de.htwg.chess.controller.impl.ChessController;

public class CheckmateTest {

	ChessController controller;
	
	@Before
	public void setUp() {
		controller = new ChessController();
	}
	
	@Test
	public void testCheckmateWhite() {
		// Move team white
		controller.select(4, 1);
		controller.move(4, 3);
		// Test statusMessage
		assertEquals("", controller.getCheckmateMessage());
		
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
		assertFalse(controller.isGameover());
		// Test statusMessage
		assertEquals("White King is in a check! ", controller.getCheckmateMessage());
		
		// Move team white
		controller.select(0, 2);
		controller.move(0, 3);
		assertTrue(controller.isGameover());
		// Test statusMessage
		assertEquals("White King is mate. Team Black has won. - Game Over! ", controller.getCheckmateMessage());
	}
	
	@Test
	public void testCheckmateBlack() {
		// Move team white
		controller.select(3, 1);
		controller.move(3, 3);
		// Test statusMessage
		assertEquals("", controller.getCheckmateMessage());
		
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
		assertFalse(controller.isGameover());
		// Test statusMessage
		assertEquals("Black King is in a check!", controller.getCheckmateMessage());
		
		// Move team black
		controller.select(0, 6);
		controller.move(0, 5);
		assertTrue(controller.isGameover());
		// Test statusMessage
		assertEquals("Black King is mate. Team White has won. - Game Over!", controller.getCheckmateMessage());
	}
	
	@Test
	public void testEnum() {
		CheckMate cMate = CheckMate.valueOf("check");
		assertEquals("check", cMate.toString());
	}
}
