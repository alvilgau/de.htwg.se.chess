package de.htwg.chess.model.impl;

import de.htwg.chess.model.IField;

public class Pawn extends Figure {
	
	private int startPosY;
	
	/**
	 * Constructs a new Pawn object
	 * @param x - the x position
	 * @param y - the y position
	 * @param team - the team of the figure
	 * @param startPosY - vertical start position of pawn
	 */
	public Pawn(int x, int y, Team team, int startPosY) {
		setxPos(x);
		setyPos(y);
		setTeam(team);
		this.startPosY = startPosY;
		loadImages("res/whitePawn.png", "res/blackPawn.png");
	}
	
	@Override
	public boolean possibleMove(int x, int y, IField fields[][]) {
		if(!super.possibleMove(x, y, fields)) {
			return false;
		}
		/* Check for horizontal move and possible kill */
		else if(x != getxPos()) {
			return possibleKill(x, y, fields);
		}
		else if(!checkCollision(x, y, fields)) {
			return false;
		}
		
		/* difference between old and new yPos */
		int diff = calculateDiff(y);

		if(diff == 1) {
			return true;
		}
		else if(diff == 2 && getyPos() == startPosY) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Calculates the difference between old and new yPos
	 * @param y - the new y Position
	 * @return the difference
	 */
	private int calculateDiff(int y) {
		if(getTeam().equals("white")) {
			return y - getyPos();
		}
		else {
			return getyPos() - y;
		}
	}
	
	/**
	 * Checks if pawn reaches end of playground
	 * @param end - the end of the playground
	 * @return true if reaches end
	 */
	private boolean checkExchange(int end) {
		return getyPos() == end;
	}
	
	@Override
	protected boolean checkCollision(int x, int y, IField fields[][]) {
		/* Checks for collision if moving up */
		if(getTeam().equals("white")) {
			for(int i = getyPos()+1; i <= y; i++) {
				if(fields[x][i].isSet()) {
					return false;
				}
			}	
			return true;
		}
		/* Checks for collision if moving down */
		else {
			for(int i = getyPos()-1; i >= y; i--) {
				if(fields[x][i].isSet()) {
					return false;
				}
			}
			return true;
		}
	}
	
	@Override
	protected boolean possibleKill(int x, int y, IField fields[][]) {
		int diffX = Math.abs(getxPos() - x);
		int diffY = calculateDiff(y);
		
		if(diffX != 1 || diffY != 1) {
			return false;
		}
		else if(fields[x][y].isSet() && getTeamNumber() != fields[x][y].getFigur().getTeamNumber()) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean move(int x, int y) {
		setxPos(x);
		setyPos(y);
		
		if(getTeam().equals("white")) {
			return checkExchange(POS_MAX);
		}
		else {
			return checkExchange(POS_MIN);
		}
	}
	
	@Override
	public String toString() {
		return "B";
	}
	
}
