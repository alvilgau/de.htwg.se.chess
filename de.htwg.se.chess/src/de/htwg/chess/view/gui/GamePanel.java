package de.htwg.chess.view.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import de.htwg.chess.controller.IChessController;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int BORDER_SIZE = 50;
	private static final int FIELD_SIZE = 100;
	private static final int COLUMNS = 8;
	private static final int DRAW_THICKNESS = 3;
	private static final int IMAGE_GAP = 18;
	
	private Logger logger = Logger.getLogger("de.htwg.chess.view.gui");
	private IChessController controller;
	private Image field;

	/**
	 * Creates a GamePanel
	 * @param controller - Chess Controller
	 */
	public GamePanel(final IChessController controller) {
		this.controller = controller;
		setBackground(Color.WHITE);
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					controller.handleMovement(getMouseRow(e), getMouseColumn(e));
				} catch (ArrayIndexOutOfBoundsException ex) {
					logger.info("Out of field");
				}	
			}
		});
		
		field = getToolkit().getImage("res/chessfield.jpg");
	}
	
	/**
	 * Calculates the row for the current mouse position
	 * @param e - mouse event
	 * @return row for the current mouse position
	 */
	private int getMouseRow(MouseEvent e) {
		return (e.getX()+BORDER_SIZE) / FIELD_SIZE-1;
	}
	
	/**
	 * Calculates the column for the current mouse position
	 * @param e - mouse event
	 * @return column for the current mouse position
	 */
	private int getMouseColumn(MouseEvent e) {
		return COLUMNS - (e.getY()+BORDER_SIZE) / FIELD_SIZE;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(field, 0, 0, null);
		controller.paint(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(DRAW_THICKNESS));
		g2.setColor(Color.BLACK);
		
		if(controller.isSelect()) {
			g2.drawRect(controller.getSelectedPosX()-IMAGE_GAP, controller.getSelectedPosY()-IMAGE_GAP,
					FIELD_SIZE, FIELD_SIZE);
		}
	}
}
