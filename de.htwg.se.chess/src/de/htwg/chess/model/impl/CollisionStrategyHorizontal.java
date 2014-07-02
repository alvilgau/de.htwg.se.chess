package de.htwg.chess.model.impl;

import de.htwg.chess.model.IField;

/**
 * This class implements the strategy for horizontal collision detection
 */
public class CollisionStrategyHorizontal extends CollisionStrategy {

	@Override
	public boolean checkCollision(Figure fig, int x, int y, IField[][] fields) {		
		/* Moving right */
		if(x > fig.getxPos()) {
			return checkCollisionRight(fig, x, y, fields);
		}
		/* Moving left */
		else {
			return checkCollisionLeft(fig, x, y, fields);
		}
	}

}
