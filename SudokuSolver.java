import java.io.*;
import java.util.*;

public class SudokuSolver
{
    public static void main(String args[])
    {
	Scanner sc = new Scanner(System.in);
	char[][] board = new char[9][9];

	System.out.println("Enter the Sudoku Puzzle elements. Insert '.' for a blank character");

	for(int i=0; i<9; i++)
	    for(int j=0; j<9; j++)
		board[i][j] = sc.next(".").charAt(0);

	if(isValidSudoku(board))
	{
	    solveSudoku(board);

	    for(int i=0; i<9; i++)
	    {
		for(int j=0; j<9; j++)
		    System.out.print(board[i][j]+" ");
		System.out.println();
	    }
	}
	else
	    System.out.println("The entered Sudoku Puzzle is invalid.");
    }

    public boolean isValidSudoku(char[][] board)

    {
	Set seen = new HashSet();

	for (int i=0; i<9; ++i)
	    for (int j=0; j<9; ++j)
	    {
		char number = board[i][j];

		if (number != '.')
		if (!seen.add(number + " in row " + i) ||
		    !seen.add(number + " in column " + j) ||
		    !seen.add(number + " in block " + i/3 + "-" + j/3))
		    return false;
	    }

	return true;
    }

    public void solveSudoku(char[][] board)
    {
        if(board == null || board.length == 0)
            return;
        solve(board);
    }
    
    public boolean solve(char[][] board)
    {
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[0].length; j++)
                if(board[i][j] == '.')
		{
                    for(char c = '1'; c <= '9'; c++)
                        if(isValid(board, i, j, c))
			{
                            board[i][j] = c;
                            
                            if(solve(board))
                                return true;
                            else
                                board[i][j] = '.';
                        }
                    
                    return false;
                }

        return true;
    }
    
    private boolean isValid(char[][] board, int row, int col, char c)
    {
        for(int i = 0; i < 9; i++)
	{
            if(board[i][col] != '.' && board[i][col] == c) return false; //check row
            if(board[row][i] != '.' && board[row][i] == c) return false; //check column
            if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' &&
	       board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c)
		return false; //check 3*3 block
        }

        return true;
    }
}