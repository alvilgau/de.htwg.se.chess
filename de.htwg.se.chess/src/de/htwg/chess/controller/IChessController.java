package de.htwg.chess.controller;

import java.awt.Point;
import java.util.List;

import de.htwg.chess.model.IFigure;
import de.htwg.util.observer.IObservable;

public interface IChessController extends IObservable {

	/**
	 * Getter for the status message
	 * 
	 * @return status message
	 */
	String getStatusMessage();

	/**
	 * Getter for the turn message
	 * 
	 * @return turn message
	 */
	String getTurnMessage();

	/**
	 * Getter for the checkmate message
	 * 
	 * @return checkmate message
	 */
	String getCheckmateMessage();

	/**
	 * Checks game is over or not
	 * 
	 * @return true if game is over
	 */
	boolean isGameover();

	/**
	 * Checks a figure is selected or not
	 * 
	 * @return true if a figure is selected
	 */
	boolean isSelect();

	/**
	 * @return size of the playground
	 */
	int getFieldSize();

	/**
	 * @return the exchange status
	 */
	boolean getExchange();

	/**
	 * @return true if team whites turn
	 */
	boolean isWhiteTurn();

	/**
	 * @return the selected figure
	 */
	IFigure getSelectedFigure();

	/**
	 * Handles the selection and movement of a figure
	 * 
	 * @param x
	 *            - horizontal position
	 * @param y
	 *            - vertical position
	 */
	void handleMovement(int x, int y);

	/**
	 * Selects a figure to move it later
	 * 
	 * @param x
	 *            - vertical position of the figure
	 * @param y
	 *            - horizontal position of the figure
	 */
	void select(int x, int y);

	/**
	 * Moves a figure from one to another field
	 * 
	 * @param x
	 *            - new vertical position
	 * @param y
	 *            - new horizontal position
	 */
	void move(int x, int y);

	/**
	 * Restarts the game
	 */
	void restart();

	/**
	 * Exchanges the Pawn with a new Knight
	 */
	void exchangeKnight();

	/**
	 * Exchanges the Pawn with a new Bishop
	 */
	void exchangeBishop();

	/**
	 * Exchanges the Pawn with a new Rook
	 */
	void exchangeRook();

	/**
	 * Exchanges the Pawn with a new Queen
	 */
	void exchangeQueen();

	/**
	 * Converts the controller to JSON
	 * 
	 * @return controller with JSON
	 */
	String toJson();

	/**
	 * @param x
	 *            - vertical position
	 * @param y
	 *            - horizontal position
	 * @return value from the field formatted as String
	 */
	String getFieldValue(int x, int y);

	/**
	 * Gets the possible moves of the current selected figure
	 * 
	 * @return list with the possible moves
	 */
	List<Point> getPossibleMoves();
}
