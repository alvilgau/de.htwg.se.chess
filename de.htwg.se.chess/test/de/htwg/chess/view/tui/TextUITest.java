package de.htwg.chess.view.tui;

import static org.junit.Assert.*;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import de.htwg.chess.controller.impl.ChessController;

public class TextUITest {

	TextUI tui;
	ChessController controller;
	
	@Before
	public void setUp() {
		PropertyConfigurator.configure("log4j.properties");
		controller = new ChessController();
		tui = new TextUI(controller);
	}
	
	@Test
	public void testProcessInputLine() {
		/* Movement input */
		assertFalse(tui.processInputLine("q"));
		assertTrue(tui.processInputLine("r"));
		assertTrue(tui.processInputLine("sa2"));
		assertTrue(tui.processInputLine("ma4"));
		assertTrue(tui.processInputLine("s2323"));
		assertTrue(tui.processInputLine("m4323"));
		
		/* exchange input */
		controller.setExchange(true);
		assertTrue(tui.processInputLine("knight"));
		
		controller.setExchange(true);
		assertTrue(tui.processInputLine("bishop"));
		
		controller.setExchange(true);
		assertTrue(tui.processInputLine("rook"));
		
		controller.setExchange(true);
		assertTrue(tui.processInputLine("queen"));
		
		controller.setExchange(true);
		assertTrue(tui.processInputLine("sa2"));
		
		/* game over input */
		controller.setExchange(false);
		controller.setGameover(true);
		assertTrue(tui.processInputLine("sa3"));
	}
	
	@Test
	public void testPrintTUI() {
		tui.printTUI();
		
		controller.setExchange(true);
		tui.printTUI();
		
		controller.setExchange(false);
		controller.setGameover(true);
		tui.printTUI();
	}
	
}
