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

	static final int POS_MAX = 7;
	static final int POS_MIN = 0;
	private static final int IMG_FIELD_SIZE = 100;
	private static final int IMG_GAP = 68;

	private Logger logger = Logger.getLogger("de.htwg.chess.model.impl");
	protected MoveValidator moveValidator = new MoveValidator();

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

	@Override
	public String getTeam() {
		return team.toString();
	}

	@Override
	public int getTeamNumber() {
		return team.ordinal();
	}

	@Override
	public boolean move(int x, int y) {
		xPos = x;
		yPos = y;
		return false;
	}

	/**
	 * Gets a neighbor field
	 * 
	 * @param x
	 *            - vertical distance
	 * @param y
	 *            - horizontal distance
	 * @param fields
	 *            - current playground
	 * @return the corresponding field
	 */
	public IField getNeighbour(int x, int y, IField[][] fields) {
		int newXPos = xPos + x;
		int newYPos = yPos + y;

		if (newXPos > POS_MAX || newYPos > POS_MAX || newXPos < POS_MIN
				|| newYPos < POS_MIN) {
			return null;
		}

		return fields[newXPos][newYPos];
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

	@Override
	public void paint(Graphics g) {
		if (getTeam().equals("white")) {
			g.drawImage(whiteFigure, getImgPosX(), getImgPosY(), null);
		} else {
			g.drawImage(blackFigure, getImgPosX(), getImgPosY(), null);
		}
	}

}
