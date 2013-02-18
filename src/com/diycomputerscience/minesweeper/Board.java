package com.diycomputerscience.minesweeper;

import com.diycomputerscience.minesweeper.utils.MinesweeperUtils;

public class Board {

	public static final int MAX_ROWS = 6;
	public static final int MAX_COLS = 6;
	
	private Square squares[][];
	
	public Board() {
		this.squares = new Square[MAX_ROWS][MAX_COLS];
		// intitialize squares and set mines
		for(int row=0; row<MAX_ROWS; row++) {
			for(int col=0; col<MAX_COLS; col++) {
				Square square = new Square();
				long ctms = System.nanoTime();
				if(ctms % 2 != 0) {
					square.setMine(true);
				}
				squares[row][col] = square;
			}
		}
		
		computeCounts();
	}
	
	/**
	 * This method uncovers the specified Square
	 * @param point The Point representing the location to uncover
	 * @throws UncoveredMineException if the specified Square is a mine
	 */
	public void uncover(int row, int col) throws UncoveredMineException {
		this.squares[row][col].uncover();
	}
	
	/**
	 * This method marks the specified Square
	 * @param point The point of the specified square
	 */
	public void mark(int row, int col) {
		this.squares[row][col].mark();
	}
	
	public Square[][] getSquares() {
		return this.squares;		
	}

	private void computeCounts() {
		// TODO: determine counts of all squares that are not mines
		for(int row=0; row<MAX_ROWS; row++) {
			for(int col=0; col<MAX_COLS; col++) {
				if(!squares[row][col].isMine()) {
					int neighbours[][] = MinesweeperUtils.computeNeibhbours(row, col);
					int count=0;
					for(int i=0; i<neighbours.length; i++) {
						int r = neighbours[i][0];
						int c = neighbours[i][1];
						if(squares[r][c].isMine()) {
							count++;
						}
					}
					squares[row][col].setCount(count);
				}				
			}
		}
	}
}
