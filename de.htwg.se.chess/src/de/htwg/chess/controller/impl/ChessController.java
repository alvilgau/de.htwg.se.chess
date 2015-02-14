package de.htwg.chess.controller.impl;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.htwg.chess.controller.IChessController;
import de.htwg.chess.model.IField;
import de.htwg.chess.model.IFigure;
import de.htwg.chess.model.impl.Bishop;
import de.htwg.chess.model.impl.Field;
import de.htwg.chess.model.impl.Figure.Team;
import de.htwg.chess.model.impl.King;
import de.htwg.chess.model.impl.Knight;
import de.htwg.chess.model.impl.Pawn;
import de.htwg.chess.model.impl.Queen;
import de.htwg.chess.model.impl.Rook;
import de.htwg.util.observer.Observable;

@Singleton
public class ChessController extends Observable implements IChessController {

	private static final int ZERO = 0;
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int FIVE = 5;
	private static final int SIX = 6;
	private static final int SEVEN = 7;
	private static final int FIELD_SIZE = 8;
	private static final int LIST_SIZE = 16;

	// This lists contains all figures of a team
	private List<IFigure> figuresTeamWhite;
	private List<IFigure> figuresTeamBlack;

	// List that contains the possible moves of the current selected figure
	private List<IField> possibleMoves;

	// Checkmate checks if a king is mate or not
	private Checkmate checkmate;

	// The playground
	private IField fields[][];

	// The figure for the actually move
	private IFigure moveFigure;

	// Attribute to check a figure is selected
	private boolean select;

	// Attribute for the exchange of pawn
	private boolean exchange;

	// Attribute to check the game is over or not
	private boolean gameover;

	// Attribute to select the right team for the move
	private int turn;

	private String statusMessage;
	private String turnMessage;

	/**
	 * Constructs a new Chess Controller
	 */
	@Inject
	public ChessController() {
		fields = new IField[FIELD_SIZE][FIELD_SIZE];
		checkmate = new Checkmate();
		moveFigure = null;
		possibleMoves = null;
		select = false;
		exchange = false;
		gameover = false;
		turn = 0;
		statusMessage = "Welcome to Chess";
		turnMessage = "Team white's turn";
		initTeamOne();
		initTeamTwo();
		initFieldsRest();
	}

	/**
	 * Initialize the list and fields for Player 1
	 */
	private void initTeamOne() {
		figuresTeamWhite = new ArrayList<IFigure>(LIST_SIZE);

		figuresTeamWhite.add(new King(FOUR, ZERO, Team.white));
		fields[FOUR][ZERO] = new Field(true, figuresTeamWhite.get(ZERO));

		figuresTeamWhite.add(new Rook(ZERO, ZERO, Team.white));
		fields[ZERO][ZERO] = new Field(true, figuresTeamWhite.get(ONE));

		figuresTeamWhite.add(new Knight(ONE, ZERO, Team.white));
		fields[ONE][ZERO] = new Field(true, figuresTeamWhite.get(TWO));

		figuresTeamWhite.add(new Bishop(TWO, ZERO, Team.white));
		fields[TWO][ZERO] = new Field(true, figuresTeamWhite.get(THREE));

		figuresTeamWhite.add(new Queen(THREE, ZERO, Team.white));
		fields[THREE][ZERO] = new Field(true, figuresTeamWhite.get(FOUR));

		figuresTeamWhite.add(new Bishop(FIVE, ZERO, Team.white));
		fields[FIVE][ZERO] = new Field(true, figuresTeamWhite.get(FIVE));

		figuresTeamWhite.add(new Knight(SIX, ZERO, Team.white));
		fields[SIX][ZERO] = new Field(true, figuresTeamWhite.get(SIX));

		figuresTeamWhite.add(new Rook(SEVEN, ZERO, Team.white));
		fields[SEVEN][ZERO] = new Field(true, figuresTeamWhite.get(SEVEN));

		for (int i = 0; i <= SEVEN; i++) {
			figuresTeamWhite.add(new Pawn(i, ONE, Team.white, ONE));
			fields[i][ONE] = new Field(true, figuresTeamWhite.get(FIELD_SIZE
					+ i));
		}
	}

	/**
	 * Initialize the list and fields for Player 2
	 */
	private void initTeamTwo() {
		figuresTeamBlack = new ArrayList<IFigure>(LIST_SIZE);

		figuresTeamBlack.add(new King(FOUR, SEVEN, Team.black));
		fields[FOUR][SEVEN] = new Field(true, figuresTeamBlack.get(ZERO));

		figuresTeamBlack.add(new Rook(ZERO, SEVEN, Team.black));
		fields[ZERO][SEVEN] = new Field(true, figuresTeamBlack.get(ONE));

		figuresTeamBlack.add(new Knight(ONE, SEVEN, Team.black));
		fields[ONE][SEVEN] = new Field(true, figuresTeamBlack.get(TWO));

		figuresTeamBlack.add(new Bishop(TWO, SEVEN, Team.black));
		fields[TWO][SEVEN] = new Field(true, figuresTeamBlack.get(THREE));

		figuresTeamBlack.add(new Queen(THREE, SEVEN, Team.black));
		fields[THREE][SEVEN] = new Field(true, figuresTeamBlack.get(FOUR));

		figuresTeamBlack.add(new Bishop(FIVE, SEVEN, Team.black));
		fields[FIVE][SEVEN] = new Field(true, figuresTeamBlack.get(FIVE));

		figuresTeamBlack.add(new Knight(SIX, SEVEN, Team.black));
		fields[SIX][SEVEN] = new Field(true, figuresTeamBlack.get(SIX));

		figuresTeamBlack.add(new Rook(SEVEN, SEVEN, Team.black));
		fields[SEVEN][SEVEN] = new Field(true, figuresTeamBlack.get(SEVEN));

		for (int i = 0; i <= SEVEN; i++) {
			figuresTeamBlack.add(new Pawn(i, SIX, Team.black, SIX));
			fields[i][SIX] = new Field(true, figuresTeamBlack.get(FIELD_SIZE
					+ i));
		}
	}

	/**
	 * Initialize the remaining fields
	 */
	private void initFieldsRest() {
		for (int i = 2; i <= FIVE; i++) {
			for (int k = 0; k <= SEVEN; k++) {
				fields[k][i] = new Field(k, i);
			}
		}
	}

	/**
	 * Selects the team for the next move
	 */
	private void nextTurn() {
		if (turn == 0) {
			turn = 1;
			turnMessage = "Team black's turn";
		} else {
			turn = 0;
			turnMessage = "Team white's turn";
		}
	}

	/**
	 * Updates the checkmate states of the kings
	 */
	private void updateCheckmate() {
		checkmate.update(figuresTeamWhite, figuresTeamBlack, fields);

		if (checkmate.isCheckWhite() && turn == 1) {
			checkmate.nextStateWhite();
		} else if (checkmate.isCheckBlack() && turn == 0) {
			checkmate.nextStateBlack();
		}

		gameover = checkmate.isMate();
	}

	/**
	 * Sets the exchange status
	 * 
	 * @param exchange
	 *            - new exchange status
	 */
	public void setExchange(boolean exchange) {
		this.exchange = exchange;
	}

	@Override
	public boolean getExchange() {
		return exchange;
	}

	@Override
	public String getStatusMessage() {
		return statusMessage;
	}

	@Override
	public String getTurnMessage() {
		return turnMessage;
	}

	@Override
	public String getCheckmateMessage() {
		return checkmate.getStatusMessage();
	}

	@Override
	public boolean isGameover() {
		return gameover;
	}

	@Override
	public boolean isSelect() {
		return select;
	}

	@Override
	public boolean isWhiteTurn() {
		return turn == 0;
	}

	@Override
	public int getFieldSize() {
		return FIELD_SIZE;
	}

	@Override
	public IFigure getSelectedFigure() {
		return moveFigure;
	}

	/**
	 * Sets the game over status
	 * 
	 * @param gameover
	 *            - new game over status
	 */
	public void setGameover(boolean gameover) {
		this.gameover = gameover;
	}

	@Override
	public void handleMovement(int x, int y) {
		if (gameover || exchange) {
			return;
		}

		if (!select) {
			select(x, y);
		} else {
			move(x, y);
		}
	}

	@Override
	public void select(int x, int y) {
		if (fields[x][y].getFigur() != null
				&& turn == fields[x][y].getFigur().getTeamNumber()) {
			moveFigure = fields[x][y].getFigur();
			possibleMoves = moveFigure.getPossibleMoves(fields);
			if (!possibleMoves.isEmpty()) {
				select = true;
				statusMessage = "One Figure is selected.";
				notifyObservers();
			}
		} else {
			statusMessage = "No Figure is selected.";
			select = false;
			notifyObservers();
		}
	}

	@Override
	public void move(int x, int y) {
		if (select && possibleMoves.contains(fields[x][y])) {
			statusMessage = "Figure was moved successfully.";
			int oldPosX = moveFigure.getxPos();
			int oldPosY = moveFigure.getyPos();

			/* Removes the killed figure from the list */
			figuresTeamBlack.remove(fields[x][y].getFigur());
			figuresTeamWhite.remove(fields[x][y].getFigur());

			/* Moves the figure */
			fields[x][y].setFigur(moveFigure);
			fields[x][y].setSet(true);
			fields[oldPosX][oldPosY].clear();
			exchange = moveFigure.move(x, y);
			nextTurn();

			/* Checks for checkmate and game over */
			updateCheckmate();
		} else {
			statusMessage = "Move failed. No Figure is selected.";
		}

		select = false;
		notifyObservers();
	}

	@Override
	public void exchangeKnight() {
		int xPos = moveFigure.getxPos();
		int yPos = moveFigure.getyPos();
		Team team = Team.valueOf(moveFigure.getTeam());

		if (team == Team.black) {
			figuresTeamBlack.remove(moveFigure);
			figuresTeamBlack.add(new Knight(xPos, yPos, team));
			fields[xPos][yPos].setFigur(figuresTeamBlack.get(figuresTeamBlack
					.size() - 1));
		} else {
			figuresTeamWhite.remove(moveFigure);
			figuresTeamWhite.add(new Knight(xPos, yPos, team));
			fields[xPos][yPos].setFigur(figuresTeamWhite.get(figuresTeamWhite
					.size() - 1));
		}

		exchange = false;
		updateCheckmate();
		notifyObservers();
	}

	@Override
	public void exchangeBishop() {
		int xPos = moveFigure.getxPos();
		int yPos = moveFigure.getyPos();
		Team team = Team.valueOf(moveFigure.getTeam());

		if (team == Team.black) {
			figuresTeamBlack.remove(moveFigure);
			figuresTeamBlack.add(new Bishop(xPos, yPos, team));
			fields[xPos][yPos].setFigur(figuresTeamBlack.get(figuresTeamBlack
					.size() - 1));
		} else {
			figuresTeamWhite.remove(moveFigure);
			figuresTeamWhite.add(new Bishop(xPos, yPos, team));
			fields[xPos][yPos].setFigur(figuresTeamWhite.get(figuresTeamWhite
					.size() - 1));
		}

		exchange = false;
		updateCheckmate();
		notifyObservers();
	}

	@Override
	public void exchangeRook() {
		int xPos = moveFigure.getxPos();
		int yPos = moveFigure.getyPos();
		Team team = Team.valueOf(moveFigure.getTeam());

		if (team == Team.black) {
			figuresTeamBlack.remove(moveFigure);
			figuresTeamBlack.add(new Rook(xPos, yPos, team));
			fields[xPos][yPos].setFigur(figuresTeamBlack.get(figuresTeamBlack
					.size() - 1));
		} else {
			figuresTeamWhite.remove(moveFigure);
			figuresTeamWhite.add(new Rook(xPos, yPos, team));
			fields[xPos][yPos].setFigur(figuresTeamWhite.get(figuresTeamWhite
					.size() - 1));
		}

		exchange = false;
		updateCheckmate();
		notifyObservers();
	}

	@Override
	public void exchangeQueen() {
		int xPos = moveFigure.getxPos();
		int yPos = moveFigure.getyPos();
		Team team = Team.valueOf(moveFigure.getTeam());

		if (team == Team.black) {
			figuresTeamBlack.remove(moveFigure);
			figuresTeamBlack.add(new Queen(xPos, yPos, team));
			fields[xPos][yPos].setFigur(figuresTeamBlack.get(figuresTeamBlack
					.size() - 1));
		} else {
			figuresTeamWhite.remove(moveFigure);
			figuresTeamWhite.add(new Queen(xPos, yPos, team));
			fields[xPos][yPos].setFigur(figuresTeamWhite.get(figuresTeamWhite
					.size() - 1));
		}

		exchange = false;
		updateCheckmate();
		notifyObservers();
	}

	@Override
	public void restart() {
		moveFigure = null;
		possibleMoves = null;
		select = false;
		exchange = false;
		gameover = false;
		statusMessage = "Welcome to Chess";
		turnMessage = "Team white's turn";
		turn = 0;
		initTeamOne();
		initTeamTwo();
		initFieldsRest();
		checkmate.reset();
		notifyObservers();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n |  a  b  c  d  e  f  g  h  |");
		sb.append("\n-+--------------------------+");

		for (int i = SEVEN; i >= ZERO; i--) {
			sb.append("\n" + (i + 1) + "|  ");
			for (int k = 0; k <= SEVEN; k++) {
				sb.append(fields[k][i] + "  ");
			}
			sb.append("|");
		}

		sb.append("\n-+--------------------------+");
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toJson() {
		JSONObject obj = new JSONObject();
		obj.put("statusMessage", getStatusMessage());
		obj.put("checkmateMessage", getCheckmateMessage());
		obj.put("turnMessage", getTurnMessage());
		obj.put("select", isSelect());
		obj.put("exchange", getExchange());
		obj.put("gameover", isGameover());
		return obj.toJSONString();
	}

	@Override
	public String getFieldValue(int x, int y) {
		IFigure fig = fields[x][y].getFigur();
		if (fig != null) {
			return fig.getTeam() + fig;
		} else {
			return "empty";
		}
	}

	@Override
	public List<Point> getPossibleMoves() {
		List<Point> possMoves = new ArrayList<Point>();
		for (IField field : possibleMoves) {
			Point p = new Point(field.getxPos(), field.getyPos());
			possMoves.add(p);
		}
		return possMoves;
	}

}
