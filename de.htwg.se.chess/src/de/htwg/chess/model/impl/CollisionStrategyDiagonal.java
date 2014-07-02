package de.htwg.chess.model.impl;

import de.htwg.chess.model.IField;

/**
 * This class implements the strategy for diagonal collision detection
 */
public class CollisionStrategyDiagonal extends CollisionStrategy {

	@Override
	public boolean checkCollision(Figure fig, int x, int y, IField[][] fields) {
		if(x < fig.getxPos()) {
			/* Moving up-left */
			if(y > fig.getyPos()) {
				return checkCollisionUpLeft(fig, x, y, fields);
			}
			/* Moving down-left */
			else {
				return checkCollisionDownLeft(fig, x, y, fields);
			}
		}
		else {
			/* Moving up-right */
			if(y > fig.getyPos()) {
				return checkCollisionUpRight(fig, x, y, fields);
			}
			/* Moving up-right */
			else {
				return checkCollisionDownRight(fig, x, y, fields);
			}
		}
	}

}
