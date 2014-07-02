package de.htwg.chess;

import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.chess.controller.IChessController;
import de.htwg.chess.view.gui.ChessFrame;
import de.htwg.chess.view.tui.TextUI;

public final class Chess {

	private static Scanner scanner;
	private static TextUI tui;
	@SuppressWarnings("unused")
	private static ChessFrame gui;
	@SuppressWarnings("unused")
	private static IChessController controller;
	
	private Chess() {
		Injector injector = Guice.createInjector(new ChessModule());
		scanner = new Scanner(System.in);
		controller = injector.getInstance(IChessController.class);
		tui = injector.getInstance(TextUI.class);
		gui = injector.getInstance(ChessFrame.class);
	}
	
	public static void main(String[] args) {
		// Set up logging through log4j
		PropertyConfigurator.configure("log4j.properties");
		
		new Chess();
		tui.printTUI();
		
		boolean run = true;
		while(run) {
			run = tui.processInputLine(scanner.next());
		}
	}

}
