package de.htwg.chess.model.impl;

import de.htwg.chess.model.IField;

public class King extends Figure{
	
	/**
	 * Constructs a new King object
	 * @param x - the x position
	 * @param y - the y position
	 * @param team - the team of the figure
	 */
	public King(int x, int y, Team team) {
		setyPos(y);
		setxPos(x);
		loadImages("res/whiteKing.png", "res/blackKing.png");
		setTeam(team);
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
		
		/* Checks moves with radius of 1 */
		if(diffX < 2 && diffY < 2 ) {
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
		return "K";
	}
	
}
