package de.htwg.chess.view.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import de.htwg.chess.controller.IChessController;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final String COLS = "ABCDEFGH";
	private static final Color LIGHT = Color.decode("#E5CEA4");
	private static final Color DARK = Color.decode("#A4785B");
	private static final Color LIGHT_BLUE = Color.decode("#B8CFE5");

	private JButton[][] chessField = new JButton[8][8];
	private IChessController controller;

	/**
	 * Creates a GamePanel
	 * 
	 * @param controller
	 *            - Chess Controller
	 */
	public GamePanel(final IChessController controller) {
		this.controller = controller;
		setLayout(new GridLayout(0, 9));

		// create the single fields
		for (int column = 0; column < chessField.length; column++) {
			for (int row = 0; row < chessField.length; row++) {
				JButton field = new JButton();
				field.setMargin(new Insets(0, 0, 0, 0));
				field.setActionCommand(row + ";" + column);

				field.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String[] pos = e.getActionCommand().split(";");
						int x = Integer.parseInt(pos[0]);
						int y = Integer.parseInt(pos[1]);
						controller.handleMovement(x, y);
					}
				});

				chessField[row][column] = field;
			}
		}

		// setting field style
		configureFields();

		// add column descriptions
		this.add(new JLabel(""));
		for (int i = 0; i < chessField.length; i++) {
			this.add(new JLabel(COLS.substring(i, i + 1), SwingConstants.CENTER));
		}

		// add fields
		for (int column = chessField.length - 1; column >= 0; column--) {
			for (int row = 0; row < chessField.length; row++) {
				switch (row) {
				case 0:
					this.add(new JLabel("" + (column + 1),
							SwingConstants.CENTER));
				default:
					this.add(chessField[row][column]);
				}
			}
		}
	}

	/**
	 * Setting field style e.g. background color
	 */
	private void configureFields() {
		for (int column = 0; column < chessField.length; column++) {
			for (int row = 0; row < chessField.length; row++) {
				// setting background color
				if ((column + row) % 2 == 1) {
					chessField[row][column].setBackground(DARK);
				} else {
					chessField[row][column].setBackground(LIGHT);
				}

				// setting figure images
				String imageName = controller.getFieldValue(row, column);
				if (imageName.equals("empty")) {
					chessField[row][column].setIcon(null);
				} else {
					chessField[row][column].setIcon(new ImageIcon("res/"
							+ imageName + ".png"));
				}
			}
		}
	}

	/**
	 * Refreshes the game panel
	 */
	public void refresh() {
		configureFields();
		if (controller.isSelect()) {
			int x = controller.getSelectedFigure().getxPos();
			int y = controller.getSelectedFigure().getyPos();
			chessField[x][y].setBackground(LIGHT_BLUE);
			for (Point point : controller.getPossibleMoves()) {
				chessField[point.x][point.y].setBackground(LIGHT_BLUE);
			}
		}
	}
}
