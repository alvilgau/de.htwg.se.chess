package de.htwg.chess.model.impl;

import de.htwg.chess.model.IField;

/**
 * This class implements the strategy for vertical collision detection
 */
public class CollisionStrategyVertical extends CollisionStrategy {

	@Override
	public boolean checkCollision(Figure fig, int x, int y, IField[][] fields) {	
		/* Moving up */
		if(y > fig.getyPos()) {
			return checkCollisionUp(fig, x, y, fields);
		}
		/* Moving down */
		else {
			return checkCollisionDown(fig, x, y, fields);
		}
	}

}
