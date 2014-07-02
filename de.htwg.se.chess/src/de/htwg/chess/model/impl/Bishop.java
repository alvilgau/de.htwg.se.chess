package de.htwg.chess.model.impl;

import de.htwg.chess.model.IField;

public class Bishop extends Figure {

	private CollisionStrategy collisionStrategy;
	
	/**
	 * Constructs a new Bishop object
	 * @param x - the x position
	 * @param y - the y position
	 * @param team - the team of the figure
	 */
	public Bishop(int x, int y, Team team) {
		setyPos(y);
		setxPos(x);
		setTeam(team);
		collisionStrategy = new CollisionStrategyDiagonal();
		loadImages("res/whiteBishop.png", "res/blackBishop.png");
	}
	
	@Override
	public boolean possibleMove(int x, int y, IField fields[][]) {
		if(!super.possibleMove(x, y, fields)) {
			return false;
		}
		
		/* Absolute difference between new and old position */
		int diffX = Math.abs(getxPos() - x);
		int diffY = Math.abs(getyPos() - y);
		
		/* Check for wrong diagonal moves */
		if(diffX != diffY) {
			return false;
		}
		
		return checkCollision(x, y, fields);
	}
	
	@Override
	protected boolean checkCollision(int x, int y, IField[][] fields) {
		/* Diagonal collision check */
		return collisionStrategy.checkCollision(this, x, y, fields);
	}
	
	@Override
	public String toString() {
		return "L";
	}

}
