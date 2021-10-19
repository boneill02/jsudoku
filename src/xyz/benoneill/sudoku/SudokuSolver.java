package xyz.benoneill.sudoku;

public class SudokuSolver {

	/**
	 * Tries to fill a Sudoku cell.
	 * @param board the Sudoku board to solve.
	 * @param x the row of the board
	 * @param y the column of the board
	 * @return true if successful
	 */
	public static boolean fillCell(int[][] board, int x, int y) {
		// TODO implement
		return false;
	}

	/**
	 * Tries to solve the given Sudoku board.
	 * @param board the Sudoku board to solve.
	 * @return solved board if successful, otherwise null.
	 */
	public static int[][] solve(int[][] board) {
		boolean canSolve = true;
		int[][] result = new int[9][9];

		if (board.length != 9 || board[0].length != 9) {
			return null;
		}

		// copy board to result
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				result[i][j] = board[i][j];
			}
		}

		while (canSolve) {
			// find next cell to fill
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (result[i][j] == 0 && fillCell(board, i, j)) {
						continue;
					}
				}
			}

			// failed to fill another cell
			canSolve = false;
		}

		return result;
	}
}
