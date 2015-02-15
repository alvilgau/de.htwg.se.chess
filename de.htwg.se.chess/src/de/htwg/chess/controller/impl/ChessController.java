package de.htwg.chess.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
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
		this.fields = new IField[FIELD_SIZE][FIELD_SIZE];
		this.checkmate = new Checkmate();
		this.moveFigure = null;
		this.possibleMoves = new ArrayList<>();
		this.select = false;
		this.exchange = false;
		this.gameover = false;
		this.turn = 0;
		this.statusMessage = "Welcome to Chess";
		this.turnMessage = "Team white's turn";
		initTeamOne();
		initTeamTwo();
		initFieldsRest();
	}

	/**
	 * Initialize the list and fields for Player 1
	 */
	private void initTeamOne() {
		this.figuresTeamWhite = new ArrayList<IFigure>(LIST_SIZE);

		this.figuresTeamWhite.add(new King(FOUR, ZERO, Team.white));
		this.fields[FOUR][ZERO] = new Field(true,
				this.figuresTeamWhite.get(ZERO));

		this.figuresTeamWhite.add(new Rook(ZERO, ZERO, Team.white));
		this.fields[ZERO][ZERO] = new Field(true,
				this.figuresTeamWhite.get(ONE));

		this.figuresTeamWhite.add(new Knight(ONE, ZERO, Team.white));
		this.fields[ONE][ZERO] = new Field(true, this.figuresTeamWhite.get(TWO));

		this.figuresTeamWhite.add(new Bishop(TWO, ZERO, Team.white));
		this.fields[TWO][ZERO] = new Field(true,
				this.figuresTeamWhite.get(THREE));

		this.figuresTeamWhite.add(new Queen(THREE, ZERO, Team.white));
		this.fields[THREE][ZERO] = new Field(true,
				this.figuresTeamWhite.get(FOUR));

		this.figuresTeamWhite.add(new Bishop(FIVE, ZERO, Team.white));
		this.fields[FIVE][ZERO] = new Field(true,
				this.figuresTeamWhite.get(FIVE));

		this.figuresTeamWhite.add(new Knight(SIX, ZERO, Team.white));
		this.fields[SIX][ZERO] = new Field(true, this.figuresTeamWhite.get(SIX));

		this.figuresTeamWhite.add(new Rook(SEVEN, ZERO, Team.white));
		this.fields[SEVEN][ZERO] = new Field(true,
				this.figuresTeamWhite.get(SEVEN));

		for (int i = 0; i <= SEVEN; i++) {
			this.figuresTeamWhite.add(new Pawn(i, ONE, Team.white, ONE));
			this.fields[i][ONE] = new Field(true,
					this.figuresTeamWhite.get(FIELD_SIZE + i));
		}
	}

	/**
	 * Initialize the list and fields for Player 2
	 */
	private void initTeamTwo() {
		this.figuresTeamBlack = new ArrayList<IFigure>(LIST_SIZE);

		this.figuresTeamBlack.add(new King(FOUR, SEVEN, Team.black));
		this.fields[FOUR][SEVEN] = new Field(true,
				this.figuresTeamBlack.get(ZERO));

		this.figuresTeamBlack.add(new Rook(ZERO, SEVEN, Team.black));
		this.fields[ZERO][SEVEN] = new Field(true,
				this.figuresTeamBlack.get(ONE));

		this.figuresTeamBlack.add(new Knight(ONE, SEVEN, Team.black));
		this.fields[ONE][SEVEN] = new Field(true,
				this.figuresTeamBlack.get(TWO));

		this.figuresTeamBlack.add(new Bishop(TWO, SEVEN, Team.black));
		this.fields[TWO][SEVEN] = new Field(true,
				this.figuresTeamBlack.get(THREE));

		this.figuresTeamBlack.add(new Queen(THREE, SEVEN, Team.black));
		this.fields[THREE][SEVEN] = new Field(true,
				this.figuresTeamBlack.get(FOUR));

		this.figuresTeamBlack.add(new Bishop(FIVE, SEVEN, Team.black));
		this.fields[FIVE][SEVEN] = new Field(true,
				this.figuresTeamBlack.get(FIVE));

		this.figuresTeamBlack.add(new Knight(SIX, SEVEN, Team.black));
		this.fields[SIX][SEVEN] = new Field(true,
				this.figuresTeamBlack.get(SIX));

		this.figuresTeamBlack.add(new Rook(SEVEN, SEVEN, Team.black));
		this.fields[SEVEN][SEVEN] = new Field(true,
				this.figuresTeamBlack.get(SEVEN));

		for (int i = 0; i <= SEVEN; i++) {
			this.figuresTeamBlack.add(new Pawn(i, SIX, Team.black, SIX));
			this.fields[i][SIX] = new Field(true,
					this.figuresTeamBlack.get(FIELD_SIZE + i));
		}
	}

	/**
	 * Initialize the remaining fields
	 */
	private void initFieldsRest() {
		for (int i = 2; i <= FIVE; i++) {
			for (int k = 0; k <= SEVEN; k++) {
				this.fields[k][i] = new Field(k, i);
			}
		}
	}

	/**
	 * Selects the team for the next move
	 */
	private void nextTurn() {
		if (this.turn == 0) {
			this.turn = 1;
			this.turnMessage = "Team black's turn";
		} else {
			this.turn = 0;
			this.turnMessage = "Team white's turn";
		}
	}

	/**
	 * Updates the checkmate states of the kings
	 */
	private void updateCheckmate() {
		this.checkmate.update(this.figuresTeamWhite, this.figuresTeamBlack,
				this.fields);

		if (this.checkmate.isCheckWhite() && this.turn == 1) {
			this.checkmate.nextStateWhite();
		} else if (this.checkmate.isCheckBlack() && this.turn == 0) {
			this.checkmate.nextStateBlack();
		}

		this.gameover = this.checkmate.isMateBlack()
				|| this.checkmate.isMateWhite();
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
		return this.exchange;
	}

	@Override
	public String getStatusMessage() {
		return this.statusMessage;
	}

	@Override
	public String getTurnMessage() {
		return this.turnMessage;
	}

	@Override
	public String getCheckmateMessage() {
		return this.checkmate.getStatusMessage();
	}

	@Override
	public boolean isGameover() {
		return this.gameover;
	}

	@Override
	public boolean isSelect() {
		return this.select;
	}

	@Override
	public boolean isWhiteTurn() {
		return this.turn == 0;
	}

	@Override
	public int getFieldSize() {
		return FIELD_SIZE;
	}

	@Override
	public IFigure getSelectedFigure() {
		return this.moveFigure;
	}

	@Override
	public Checkmate getCheckmate() {
		return this.checkmate;
	}

	@Override
	public String getFieldValue(int x, int y) {
		IFigure fig = this.fields[x][y].getFigur();
		if (fig != null) {
			return fig.getTeam() + fig;
		} else {
			return "empty";
		}
	}

	@Override
	public int[][] getPossibleMoves() {
		int possMoves[][] = new int[this.possibleMoves.size()][2];
		for (int i = 0; i < this.possibleMoves.size(); i++) {
			possMoves[i][0] = this.possibleMoves.get(i).getxPos();
			possMoves[i][1] = this.possibleMoves.get(i).getyPos();
		}

		return possMoves;
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
		if (this.gameover || this.exchange) {
			return;
		}

		if (!this.select) {
			select(x, y);
		} else {
			move(x, y);
		}
	}

	@Override
	public void select(int x, int y) {
		if (this.fields[x][y].getFigur() != null
				&& this.turn == this.fields[x][y].getFigur().getTeamNumber()) {
			this.moveFigure = this.fields[x][y].getFigur();
			this.possibleMoves = this.moveFigure.getPossibleMoves(this.fields);
			if (!this.possibleMoves.isEmpty()) {
				this.select = true;
				this.statusMessage = "One Figure is selected.";
				notifyObservers();
			}
		} else {
			this.statusMessage = "No Figure is selected.";
			this.select = false;
			notifyObservers();
		}
	}

	@Override
	public void move(int x, int y) {
		if (this.select && this.possibleMoves.contains(this.fields[x][y])) {
			this.statusMessage = "Figure was moved successfully.";
			int oldPosX = this.moveFigure.getxPos();
			int oldPosY = this.moveFigure.getyPos();

			/* Removes the killed figure from the list */
			this.figuresTeamBlack.remove(this.fields[x][y].getFigur());
			this.figuresTeamWhite.remove(this.fields[x][y].getFigur());

			/* Moves the figure */
			this.fields[x][y].setFigur(this.moveFigure);
			this.fields[x][y].setSet(true);
			this.fields[oldPosX][oldPosY].clear();
			this.exchange = this.moveFigure.move(x, y);
			nextTurn();

			/* Checks for checkmate and game over */
			updateCheckmate();
		} else {
			this.statusMessage = "Move failed. No Figure is selected.";
		}

		this.select = false;
		notifyObservers();
	}

	@Override
	public void exchangeKnight() {
		int xPos = this.moveFigure.getxPos();
		int yPos = this.moveFigure.getyPos();
		Team team = Team.valueOf(this.moveFigure.getTeam());

		if (team == Team.black) {
			this.figuresTeamBlack.remove(this.moveFigure);
			this.figuresTeamBlack.add(new Knight(xPos, yPos, team));
			this.fields[xPos][yPos].setFigur(this.figuresTeamBlack
					.get(this.figuresTeamBlack.size() - 1));
		} else {
			this.figuresTeamWhite.remove(this.moveFigure);
			this.figuresTeamWhite.add(new Knight(xPos, yPos, team));
			this.fields[xPos][yPos].setFigur(this.figuresTeamWhite
					.get(this.figuresTeamWhite.size() - 1));
		}

		this.exchange = false;
		updateCheckmate();
		notifyObservers();
	}

	@Override
	public void exchangeBishop() {
		int xPos = this.moveFigure.getxPos();
		int yPos = this.moveFigure.getyPos();
		Team team = Team.valueOf(this.moveFigure.getTeam());

		if (team == Team.black) {
			this.figuresTeamBlack.remove(this.moveFigure);
			this.figuresTeamBlack.add(new Bishop(xPos, yPos, team));
			this.fields[xPos][yPos].setFigur(this.figuresTeamBlack
					.get(this.figuresTeamBlack.size() - 1));
		} else {
			this.figuresTeamWhite.remove(this.moveFigure);
			this.figuresTeamWhite.add(new Bishop(xPos, yPos, team));
			this.fields[xPos][yPos].setFigur(this.figuresTeamWhite
					.get(this.figuresTeamWhite.size() - 1));
		}

		this.exchange = false;
		updateCheckmate();
		notifyObservers();
	}

	@Override
	public void exchangeRook() {
		int xPos = this.moveFigure.getxPos();
		int yPos = this.moveFigure.getyPos();
		Team team = Team.valueOf(this.moveFigure.getTeam());

		if (team == Team.black) {
			this.figuresTeamBlack.remove(this.moveFigure);
			this.figuresTeamBlack.add(new Rook(xPos, yPos, team));
			this.fields[xPos][yPos].setFigur(this.figuresTeamBlack
					.get(this.figuresTeamBlack.size() - 1));
		} else {
			this.figuresTeamWhite.remove(this.moveFigure);
			this.figuresTeamWhite.add(new Rook(xPos, yPos, team));
			this.fields[xPos][yPos].setFigur(this.figuresTeamWhite
					.get(this.figuresTeamWhite.size() - 1));
		}

		this.exchange = false;
		updateCheckmate();
		notifyObservers();
	}

	@Override
	public void exchangeQueen() {
		int xPos = this.moveFigure.getxPos();
		int yPos = this.moveFigure.getyPos();
		Team team = Team.valueOf(this.moveFigure.getTeam());

		if (team == Team.black) {
			this.figuresTeamBlack.remove(this.moveFigure);
			this.figuresTeamBlack.add(new Queen(xPos, yPos, team));
			this.fields[xPos][yPos].setFigur(this.figuresTeamBlack
					.get(this.figuresTeamBlack.size() - 1));
		} else {
			this.figuresTeamWhite.remove(this.moveFigure);
			this.figuresTeamWhite.add(new Queen(xPos, yPos, team));
			this.fields[xPos][yPos].setFigur(this.figuresTeamWhite
					.get(this.figuresTeamWhite.size() - 1));
		}

		this.exchange = false;
		updateCheckmate();
		notifyObservers();
	}

	@Override
	public void restart() {
		this.moveFigure = null;
		this.possibleMoves = null;
		this.select = false;
		this.exchange = false;
		this.gameover = false;
		this.statusMessage = "Welcome to Chess";
		this.turnMessage = "Team white's turn";
		this.turn = 0;
		initTeamOne();
		initTeamTwo();
		initFieldsRest();
		this.checkmate.reset();
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
				sb.append(this.fields[k][i] + "  ");
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
		JSONArray array = new JSONArray();
		obj.put("statusMessage", getStatusMessage());
		obj.put("checkmateMessage", getCheckmateMessage());
		obj.put("turnMessage", getTurnMessage());
		obj.put("select", isSelect());
		obj.put("exchange", getExchange());
		obj.put("gameover", isGameover());
		for (int[] move : getPossibleMoves()) {
			JSONArray arr = new JSONArray();
			arr.add(move[0]);
			arr.add(move[1]);
			array.add(arr);
		}
		obj.put("possMoves", array);
		return obj.toJSONString();
	}
}
