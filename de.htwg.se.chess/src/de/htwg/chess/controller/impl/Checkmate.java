package de.htwg.chess.controller.impl;

import java.util.List;

import de.htwg.chess.model.IField;
import de.htwg.chess.model.IFigure;

public class Checkmate {

	public static enum CheckMate {
		save, check, mate;
		private CheckMate next() {
			return CheckMate.values()[this.ordinal() + 1];
		}
	}

	private CheckMate checkmateWhite;
	private CheckMate checkmateBlack;
	private String statusMessageWhite;
	private String statusMessageBlack;

	/**
	 * Constructs a new Checkmate object
	 */
	public Checkmate() {
		checkmateWhite = CheckMate.save;
		checkmateBlack = CheckMate.save;
		statusMessageWhite = "";
		statusMessageBlack = "";
	}

	/**
	 * Sets all attributes to the default value
	 */
	public void reset() {
		checkmateWhite = CheckMate.save;
		checkmateBlack = CheckMate.save;
		statusMessageWhite = "";
		statusMessageBlack = "";
	}

	/**
	 * @return true if at least one king is mate
	 */
	public boolean isMate() {
		return checkmateWhite == CheckMate.mate
				|| checkmateBlack == CheckMate.mate;
	}

	/**
	 * @return true if the white king is in a check
	 */
	public boolean isCheckWhite() {
		return checkmateWhite == CheckMate.check;
	}

	/**
	 * @return true if the black king is in a check
	 */
	public boolean isCheckBlack() {
		return checkmateBlack == CheckMate.check;
	}

	/**
	 * Switches to next state of the white king
	 */
	public void nextStateWhite() {
		checkmateWhite = checkmateWhite.next();
	}

	/**
	 * Switches to next state of the black king
	 */
	public void nextStateBlack() {
		checkmateBlack = checkmateBlack.next();
	}

	/**
	 * Checks if the kings are checkmated
	 * 
	 * @param teamWhite
	 *            - list of all figures of the white team
	 * @param teamBlack
	 *            - list of all figures of the black team
	 * @param fields
	 *            - the current playground
	 */
	public void update(List<IFigure> teamWhite, List<IFigure> teamBlack,
			IField fields[][]) {
		updateTeamWhite(teamWhite, teamBlack, fields);
		updateTeamBlack(teamWhite, teamBlack, fields);
	}

	/**
	 * Checks if the white king is mate
	 * 
	 * @param teamWhite
	 *            - list of all figures of the white team
	 * @param teamBlack
	 *            - list of all figures of the black team
	 * @param fields
	 *            - the current playground
	 */
	private void updateTeamWhite(List<IFigure> teamWhite,
			List<IFigure> teamBlack, IField fields[][]) {
		int xPos = teamWhite.get(0).getxPos();
		int yPos = teamWhite.get(0).getyPos();
		boolean updated = false;

		for (IFigure fig : teamBlack) {
			if (fig.getPossibleMoves(fields).contains(fields[xPos][yPos])) {
				nextStateWhite();
				updated = true;
				break;
			}
		}

		if (!updated) {
			checkmateWhite = CheckMate.save;
		}
	}

	/**
	 * Checks if the black king is mate
	 * 
	 * @param teamWhite
	 *            - list of all figures of the white team
	 * @param teamBlack
	 *            - list of all figures of the black team
	 * @param fields
	 *            - the current playground
	 */
	private void updateTeamBlack(List<IFigure> teamWhite,
			List<IFigure> teamBlack, IField fields[][]) {
		int xPos = teamBlack.get(0).getxPos();
		int yPos = teamBlack.get(0).getyPos();
		boolean updated = false;

		for (IFigure fig : teamWhite) {
			if (fig.getPossibleMoves(fields).contains(fields[xPos][yPos])) {
				nextStateBlack();
				updated = true;
				break;
			}
		}

		if (!updated) {
			checkmateBlack = CheckMate.save;
		}
	}

	/**
	 * Getter for the status message of the kings
	 * 
	 * @return status message of the kings
	 */
	public String getStatusMessage() {
		switch (checkmateWhite) {
		case save:
			statusMessageWhite = "";
			break;
		case check:
			statusMessageWhite = "White King is in a check! ";
			break;
		default:
			statusMessageWhite = "White King is mate. Team Black has won. - Game Over! ";
			break;
		}

		switch (checkmateBlack) {
		case save:
			statusMessageBlack = "";
			break;
		case check:
			statusMessageBlack = "Black King is in a check!";
			break;
		default:
			statusMessageBlack = "Black King is mate. Team White has won. - Game Over!";
			break;
		}

		return statusMessageWhite + "" + statusMessageBlack;
	}

}
