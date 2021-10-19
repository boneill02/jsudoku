package xyz.benoneill.sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SudokuGenerator {

	/**
	 * Check if the given number can be placed in cell at (x,y)
	 * @param grid the Sudoku grid
	 * @param n the number to place at (x,y)
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @return true if the number can be placed there
	 */
	public static boolean canPut(int[][] grid, int n, int x, int y) {
		int[] boxNums = new int[9];

		// if cell isn't already empty
		if (grid[x][y] != 0) {
			return false;
		}

		// fill container
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int gridX = x - (x % 3) + i;
				int gridY = y - (y % 3) + j;
				boxNums[(i * 3) + j] = grid[gridX][gridY];
			}
		}

		for (int i = 0; i < 9; i++) {
			System.out.printf("%d ", boxNums[i]);
		}
		System.out.printf("%n");

		// check row
		for (int i = 0; i < 9; i++) {
			if (grid[x][i] == n) {
				return false;
			}
		}

		// check column
		for (int i = 0; i < 9; i++) {
			if (grid[i][y] == n) {
				return false;
			}
		}

		// check box
		for (int i = 0; i < 9; i++) {
			if(boxNums[i] == n) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Generate a completely solved Sudoku puzzle.
	 * @return the Sudoku grid.
	 */
	public static int[][] generate() {
		int[][] result = new int[9][9];

		// board must be filled at random
		ArrayList<Integer> rowOrder = new ArrayList<Integer>(9);
		ArrayList<Integer> colOrder = new ArrayList<Integer>(9);
		Collections.shuffle(rowOrder);
		Collections.shuffle(colOrder);

		ArrayList<Integer> numList = new ArrayList<Integer>(9);

		// initially fill number list
		for (int j = 0; j < 9; j++) {
			numList.add(j + 1);
		}


		// fill result grid
		for (int i = 0; i < 9; i++) { // row

			// fill number list
			for (int j = 0; j < 9; j++) {
				numList.add(j + 1);
			}

			// shuffle it
			Collections.shuffle(numList);

			// fill result by column j on row i
			for (int j = 0; j < 9; j++) { // column
				for (int k = 0; k < numList.size(); k++) {
					if (canPut(result, numList.get(k), i, j)) {
						result[i][j] = numList.get(k);
						numList.remove(k);
						break;
					}
				}
			}
		}

		return result;
	}

	/**
	 * Print a Sudoku grid.
	 * @param grid the grid to print
	 */
	public static void printGrid(int[][] grid) {
		System.out.printf("%n");
		System.out.printf("%n");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.printf("%d ", grid[i][j]);
			}

			System.out.printf("%n");
		}
	}

	/**
	 * main
	 * @param args yes
	 */
	public static void main(String[] args) {
		int[][] result = generate();
		if (result == null) {
			System.out.printf("Error while creating board.%n");
			return;
		}

		printGrid(result);
	}

}
