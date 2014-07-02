package de.htwg.chess.model;

import java.awt.Graphics;

public interface IFigure {

	/**
	 * Method to get the x position of a figure
	 * @return x position
	 */
	int getxPos();

	/**
	 * Method to get the y position of a figure
	 * @return y position
	 */
	int getyPos();
	
	/**
	 * @return x position of the figure image
	 */
	int getImgPosX();
	
	/**
	 * @return y position of the figure image
	 */
	int getImgPosY();

	/**
	 * Gets the team number of a figure
	 * @return team number
	 */
	int getTeamNumber();
	
	/**
	 * Gets the team of a figure
	 * @return String - the team name
	 */
	String getTeam();
	
	/**
	 * Moves a figure
	 * @param x - new x position
	 * @param y - new y position
	 * @return only true if Pawn reaches end of field, otherwise it returns always false
	 */
	boolean move(int x, int y);
	
	/**
	 * Check the move is valid or not
	 * @param x - the new x position
	 * @param y - the new y position
	 * @param fields - the current playground
	 * @return true if is a possible move
	 */
	boolean possibleMove(int x, int y, IField[][] fields);
	
	/**
	 * Paints a figure
	 * @param g - Graphics
	 */
	void paint(Graphics g);
}
