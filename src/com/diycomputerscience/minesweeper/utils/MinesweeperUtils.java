package com.diycomputerscience.minesweeper.utils;

import com.diycomputerscience.minesweeper.Board;
import com.diycomputerscience.minesweeper.Point;

public class MinesweeperUtils {
	
	/**
	 * Computes all valid neighbors for the specified {@link Point} 
	 * @param point The specified Point
	 * @return A List containing {@link Point} objects that denote a valid neighbor for the specified Point
	 */
	public static int[][] computeNeibhbours(int row, int col) {		
		int neighbours[][] = new int[8][2]; // At most a square can have 8 neighbours and at least it will have 3 neighbours
		int count = 0; // keep a count because we may have to compact the array
		// row-1, col-1
		if(row-1 >=0 && col-1 >= 0) {
			neighbours[count][0] = row-1;
			neighbours[count][1] = col-1;
			count++;
		}
		
		// row-1, col
		if(row-1 >= 0) {
			neighbours[count][0] = row-1;
			neighbours[count][1] = col;
			count++;
		}
		
		// row-1, col+1
		if(row-1 >= 0 && col+1 < Board.MAX_COLS) {
			neighbours[count][0] = row-1;
			neighbours[count][1] = col+1;
			count++;
		}
		
		// row, col+1
		if(col+1 < Board.MAX_COLS) {
			neighbours[count][0] = row;
			neighbours[count][1] = col+1;
			count++;
		}
		
		// row+1, col+1
		if(row+1 < Board.MAX_ROWS && col+1 < Board.MAX_COLS) {
			neighbours[count][0] = row+1;
			neighbours[count][1] = col+1;
			count++;
		}
		
		// row+1, col
		if(row+1 < Board.MAX_ROWS) {
			neighbours[count][0] = row+1;
			neighbours[count][1] = col;
			count++;
		}
		
		// row+1, col-1
		if(row+1 < Board.MAX_ROWS && col-1 >= 0) {
			neighbours[count][0] = row+1;
			neighbours[count][1] = col-1;
			count++;
		}
		
		// row, col-1
		if(col-1 >= 0) {
			neighbours[count][0] = row;
			neighbours[count][1] = col-1;
			count++;
		}		
		
		if(count < 8) {
			int compressedNeighbours[][] = new int[count][2];
			for(int i=0; i<count; i++) {
				compressedNeighbours[i][0] = neighbours[i][0];
				compressedNeighbours[i][1] = neighbours[i][1];
			}
			return compressedNeighbours;
		} else {
			return neighbours;
		}		
	}
}
