package de.htwg.chess.model.impl;

import de.htwg.chess.model.IField;

public class Knight extends Figure{
	
	/**
	 * Constructs a new Knight object
	 * @param x - the x position
	 * @param y - the y position
	 * @param team - the team of the figure
	 */
	public Knight(int x, int y, Team team) {
		setxPos(x);
		setyPos(y);
		setTeam(team);
		loadImages("res/whiteKnight.png", "res/blackKnight.png");
	}
	
	@Override
	public boolean possibleMove(int x, int y, IField fields[][]) {
		if(!super.possibleMove(x, y, fields)) {
			return false;
		}
		else if(!checkCollision(x, y, fields)) {
			return false;
		}
		
		/* Absolute difference between old and new position */
		int diffX = Math.abs(getxPos() - x);
		int diffY = Math.abs(getyPos() - y);
		
		if(diffX == 2 && diffY == 1) {
			return true;
		}
		else if(diffY == 2 && diffX == 1) {		
			return true;
		}
		
		return false;
	}
	
	@Override
	protected boolean checkCollision(int x, int y, IField[][] fields) {
		return possibleKill(x, y, fields);
	}
	
	@Override
	public String toString() {
		return "P";
	}
	
}
