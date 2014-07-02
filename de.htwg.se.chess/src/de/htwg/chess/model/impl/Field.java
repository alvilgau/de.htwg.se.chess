package de.htwg.chess.model.impl;

import de.htwg.chess.model.IField;
import de.htwg.chess.model.IFigure;

public class Field implements IField{
	
	private boolean set;
	private Figure figur;
	
	/**
	 * Constructs a new Field object with default values
	 */
	public Field() {
		set = false;
		figur = null;
	}
	
	/**
	 * Constructs a new Field object
	 * @param set - field is set or not
	 * @param figur - reference to a figure object
	 */
	public Field(boolean set, IFigure figur) {
		this.set = set;
		this.figur = (Figure) figur;
	}

	/**
	 * Changes the set status of a field
	 * @param set - the new set value
	 */
	public void setSet(boolean set) {
		this.set = set;
	}
	
	/**
	 * Getter for set
	 * @return value of set
	 */
	public boolean isSet() {
		return set;
	}

	/**
	 * Getter for the figure
	 * @return the figure of a field
	 */
	public Figure getFigur() {
		return figur;
	}

	/**
	 * References the field with a new figure
	 * @param figure - the new figure
	 */
	public void setFigur(IFigure figure) {
		this.figur = (Figure) figure;
	}
	
	@Override
	public boolean isKing() {
		return figur.toString().equals("K");
	}
	
	/**
	 * Sets the attributes of a field to a default value
	 */
	public void clear() {
		set = false;
		figur = null;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(set) {
			sb.append(figur.toString());
		}
		else {
			sb.append("-");
		}
		return sb.toString();
	}
	
}
