package de.htwg.chess.model.impl;

import de.htwg.chess.model.IField;

/**
 * Patterns: Strategy and TemplateMethod
 * This class is the abstract strategy for the strategy pattern.
 * At the same time it is a template for the implementation of a strategy
 */
public abstract class CollisionStrategy {
	
	/**
	 * Checks for collision if moving up
	 * @param fig - the figure that will moved
	 * @param x - the new x position
	 * @param y - the new y position
	 * @param fields - current playground
	 * @return true if no collisions available
	 */
	protected boolean checkCollisionUp(Figure fig, int x, int y, IField fields[][]) {
		for(int i = fig.getyPos()+1; i < y; i++) {
			if(fields[x][i].isSet()) {
				return false;
			}
		}
		
		return fig.possibleKill(x, y, fields);
	}
	
	/**
	 * Checks for collision if moving down
	 * @param fig - the figure that will moved
	 * @param x - the new x position
	 * @param y - the new y position
	 * @param fields - current playground
	 * @return true if no collisions available
	 */
	protected boolean checkCollisionDown(Figure fig, int x, int y, IField fields[][]) {
		for(int i = fig.getyPos()-1; i > y; i--) {
			if(fields[x][i].isSet()) {
				return false;
			}
		}
		
		return fig.possibleKill(x, y, fields);
	}
	
	/**
	 * Checks for collision if moving right
	 * @param fig - the figure that will moved
	 * @param x - the new x position
	 * @param y - the new y position
	 * @param fields - current playground
	 * @return true if no collisions available
	 */
	protected boolean checkCollisionRight(Figure fig, int x, int y, IField fields[][]) {
		for(int i = fig.getxPos()+1; i < x; i++) {
			if(fields[i][y].isSet()) {
				return false;
			}
		}
		
		return fig.possibleKill(x, y, fields);
	}
	
	/**
	 * Checks for collision if moving left
	 * @param fig - the figure that will moved
	 * @param x - the new x position
	 * @param y - the new y position
	 * @param fields - current playground
	 * @return true if no collisions available
	 */
	protected boolean checkCollisionLeft(Figure fig, int x, int y, IField fields[][]) {
		for(int i = fig.getxPos()-1; i > x; i--) {
			if(fields[i][y].isSet()) {
				return false;
			}
		}
		
		return fig.possibleKill(x, y, fields);
	}
	
	/**
	 * Checks for collision if moving up-left
	 * @param fig - the figure that will moved
	 * @param x - the new x position
	 * @param y - the new y position
	 * @param fields - current playground
	 * @return true if no collisions available
	 */
	protected boolean checkCollisionUpLeft(Figure fig, int x, int y, IField fields[][]) {
		int p1 = fig.getxPos()-1, p2 = fig.getyPos()+1;
		
		while(p1 > x) {
			if(fields[p1][p2].isSet()) {
				return false;
			}
			p1--;
			p2++;
		}	
		
		return fig.possibleKill(x, y, fields);
	}
	
	/**
	 * Checks for collision if moving up-right
	 * @param fig - the figure that will moved
	 * @param x - the new x position
	 * @param y - the new y position
	 * @param fields - current playground
	 * @return true if no collisions available
	 */
	protected boolean checkCollisionUpRight(Figure fig, int x, int y, IField fields[][]) {
		int p1 = fig.getxPos()+1, p2 = fig.getyPos()+1;
		
		while(p1 < x) {
			if(fields[p1][p2].isSet()) {
				return false;
			}
			p1++;
			p2++;
		}
		
		return fig.possibleKill(x, y, fields);
	}
	
	/**
	 * Checks for collision if moving down-left
	 * @param fig - the figure that will moved
	 * @param x - the new x position
	 * @param y - the new y position
	 * @param fields - current playground
	 * @return true if no collisions available
	 */
	protected boolean checkCollisionDownLeft(Figure fig, int x, int y, IField fields[][]) {
		int p1 = fig.getxPos()-1, p2 = fig.getyPos()-1;
		
		while(p1 > x) {
			if(fields[p1][p2].isSet()) {
				return false;
			}
			p1--;
			p2--;
		}
		
		return fig.possibleKill(x, y, fields);
	}
	
	/**
	 * Checks for collision if moving down-right
	 * @param fig - the figure that will moved
	 * @param x - the new x position
	 * @param y - the new y position
	 * @param fields - current playground
	 * @return true if no collisions available
	 */
	protected boolean checkCollisionDownRight(Figure fig, int x, int y, IField fields[][]) {
		int p1 = fig.getxPos()+1, p2 = fig.getyPos()-1;
		
		while(p1 < x) {
			if(fields[p1][p2].isSet()) {
				return false;
			}
			p1++;
			p2--;
		}
		
		return fig.possibleKill(x, y, fields);
	}
	
	/**
	 * Checks for collision between figures
	 * @param fig - the figure that will moved
	 * @param x - the new x position
	 * @param y - the new y position
	 * @param fields - current playground
	 * @return true if no collisions available
	 */
	public abstract boolean checkCollision(Figure fig, int x, int y, IField fields[][]);
	
}
