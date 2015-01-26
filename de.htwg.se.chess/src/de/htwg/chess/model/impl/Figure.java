package de.htwg.chess.model.impl;

import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.log4j.helpers.Loader;

import de.htwg.chess.model.IField;
import de.htwg.chess.model.IFigure;

public abstract class Figure implements IFigure {

	public enum Team {
		white, black
	};

	private Logger logger = Logger.getLogger("de.htwg.chess.model.impl");

	protected static final int POS_MAX = 7;
	protected static final int POS_MIN = 0;
	private static final int IMG_FIELD_SIZE = 100;
	private static final int IMG_GAP = 68;

	private int xPos;
	private int yPos;
	private Team team;
	private Image whiteFigure;
	private Image blackFigure;

	@Override
	public int getxPos() {
		return xPos;
	}

	@Override
	public int getyPos() {
		return yPos;
	}

	@Override
	public int getImgPosX() {
		return xPos * IMG_FIELD_SIZE + IMG_GAP;
	}

	@Override
	public int getImgPosY() {
		return (POS_MAX - yPos) * IMG_FIELD_SIZE + IMG_GAP;
	}

	/**
	 * Method to change the x position of a figure
	 * 
	 * @param xPos
	 *            - the new x position
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	/**
	 * Method to change the y position of a figure
	 * 
	 * @param yPos
	 *            - the new y position
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	/**
	 * Sets the team of a figure
	 * 
	 * @param team
	 *            - the new team
	 */
	public void setTeam(Team team) {
		this.team = team;
	}

	/**
	 * Gets the team of a figure
	 * 
	 * @return String - the team name
	 */
	public String getTeam() {
		return team.toString();
	}

	/**
	 * Gets the team number of a figure
	 * 
	 * @return team number
	 */
	public int getTeamNumber() {
		return team.ordinal();
	}

	/**
	 * Moves a figure
	 * 
	 * @param x
	 *            - new x position
	 * @param y
	 *            - new y position
	 * @return only true if Pawn reaches end of field, otherwise it returns
	 *         always false
	 */
	public boolean move(int x, int y) {
		xPos = x;
		yPos = y;
		return false;
	}

	/**
	 * Check the move is valid or not
	 * 
	 * @param x
	 *            - the new x position
	 * @param y
	 *            - the new y position
	 * @param fields
	 *            - the current playground
	 * @return true if is a possible move
	 */
	public boolean possibleMove(int x, int y, IField fields[][]) {
		/* Check out of field */
		if (x > POS_MAX || y > POS_MAX || x < POS_MIN || y < POS_MIN) {
			return false;
		}
		/* Check same position */
		else if (x == getxPos() && y == getyPos()) {
			return false;
		}
		return true;
	}

	/**
	 * Loads the image for a figure
	 * 
	 * @param white
	 *            - URI to the white image
	 * @param black
	 *            - URI to the black image
	 */
	protected void loadImages(String white, String black) {
		try {
			whiteFigure = ImageIO.read(Loader.getResource(white));
			blackFigure = ImageIO.read(Loader.getResource(black));
		} catch (Exception e) {
			logger.info("Can't Read File");
		}
	}

	/**
	 * Checks if a figure can kill another figure
	 * 
	 * @param x
	 *            - the new x position
	 * @param y
	 *            - the new y position
	 * @param fields
	 *            - the current playground
	 * @return true if can kill the other figure
	 */
	protected boolean possibleKill(int x, int y, IField fields[][]) {
		if (fields[x][y].isSet()
				&& getTeamNumber() == fields[x][y].getFigur().getTeamNumber()) {
			return false;
		}

		return true;
	}

	/**
	 * Checks for collision between figures
	 * 
	 * @param x
	 *            - the new x position
	 * @param y
	 *            - the new y position
	 * @param fields
	 *            - current playground
	 * @return true if no collisions available
	 */
	protected abstract boolean checkCollision(int x, int y, IField fields[][]);

	@Override
	public void paint(Graphics g) {
		if (getTeam().equals("white")) {
			g.drawImage(whiteFigure, getImgPosX(), getImgPosY(), null);
		} else {
			g.drawImage(blackFigure, getImgPosX(), getImgPosY(), null);
		}
	}

}
