package de.htwg.chess.model.impl;

import de.htwg.chess.model.IField;

public class Queen extends Figure {

	private CollisionStrategy collisionStrategy;
	
	/**
	 * Constructs a new Queen object
	 * @param x - the x position
	 * @param y - the y position
	 * @param team - the team of the figure
	 */
	public Queen(int x, int y, Team team) {
		setxPos(x);
		setyPos(y);
		setTeam(team);
		collisionStrategy = null;
		loadImages("res/whiteQueen.png", "res/blackQueen.png");
	}
	
	@Override
	public boolean possibleMove(int x, int y, IField fields[][]) {
		if(!super.possibleMove(x, y, fields)) {
			return false;
		}
		
		/* Absolute difference between new and old position */
		int diffX = Math.abs(getxPos() - x);
		int diffY = Math.abs(getyPos() - y);
	
		/* Move is not vertical, horizontal or diagonal */
		if(diffX != 0 && diffY != 0 && diffX != diffY) {
			return false;
		}
		
		return checkCollision(x, y, fields);
	}
	
	@Override
	protected boolean checkCollision(int x, int y, IField[][] fields) {
		/* Vertical move */
		if(x == getxPos()) {
			collisionStrategy = new CollisionStrategyVertical();
		}
		/* Horizontal move */
		else if(y == getyPos()) {
			collisionStrategy = new CollisionStrategyHorizontal();
		}
		/* Diagonal move */
		else {
			collisionStrategy = new CollisionStrategyDiagonal();
		}
		
		return collisionStrategy.checkCollision(this, x, y, fields);
	}
	
	@Override
	public String toString() {
		return "D";
	}

}
