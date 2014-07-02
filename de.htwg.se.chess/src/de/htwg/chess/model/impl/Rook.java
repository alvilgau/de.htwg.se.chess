package de.htwg.chess.model.impl;

import de.htwg.chess.model.IField;

public class Rook extends Figure {

	private CollisionStrategy collisionStrategy;
	
	/**
	 * Constructs a new Rook object
	 * @param x - the x position
	 * @param y - the y position
	 * @param team - the team of the figure
	 */
	public Rook(int x, int y, Team team) {
		setxPos(x);
		setyPos(y);
		setTeam(team);
		collisionStrategy = null;
		loadImages("res/whiteRook.png", "res/blackRook.png");
	}
	
	@Override
	public boolean possibleMove(int x, int y, IField fields[][]) {
		if(!super.possibleMove(x, y, fields)) {
			return false;
		}		
		/* Move is diagonally */
		else if(x != getxPos() && y != getyPos()) {
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
		else {
			collisionStrategy = new CollisionStrategyHorizontal();
		}
		
		return collisionStrategy.checkCollision(this, x, y, fields);
	}
	
	@Override
	public String toString() {
		return "T";
	}

}
