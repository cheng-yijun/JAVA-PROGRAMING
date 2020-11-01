//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            Sokoban
// Files:            Sokoban.java
// Semester:         Fall 2018
//
// Author:           Yijun Cheng
// Email:            cheng229@cs.wisc.edu
// CS Login:         yijunc
// Lecturer's Name:  <cs200>
// Lab Section:      <321> && <322> 
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Online source: Confirmed the equation for calculating the area of a
//                circle: https://www.mathsisfun.com/geometry/circle-area.html
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Scanner;
import java.util.Random;
/**
 * This is a Sokoban game. We will control the worker to push those boxes to goals. 
 * Plyer can choose game levels. If all boxes are pushed to be on the goals, he will win.
 */
public class Sokoban {

	/**
	 * Prompts the user for a value by displaying prompt. Note: This method should
	 * not add a new line to the output of prompt.
	 *
	 * After prompting the user, the method will consume an entire line of input
	 * while reading an int. If the value read is between min and max (inclusive),
	 * that value is returned. Otherwise, "Invalid value." terminated by a new line
	 * is output to the console and the user is prompted again.
	 *
	 * @param sc     The Scanner instance to read from System.in.
	 * @param prompt The name of the value for which the user is prompted.
	 * @param min    The minimum acceptable int value (inclusive).
	 * @param max    The maximum acceptable int value (inclusive).
	 * @return Returns the value read from the user.
	 */
	public static int promptInt(Scanner sc, String prompt, int min, int max) {
		// FIX ME
		System.out.print("Choose a level between " + min + " and " + max + ": ");
		String lvl = sc.next(); // level from user input
		while (Integer.parseInt(lvl) < -1 || Integer.parseInt(lvl) > max) {
			System.out.println("Invalid value.");
			System.out.print("Choose a level between " + min + " and " + max + ": ");
			lvl = sc.next();
		}

		return Integer.parseInt(lvl);
	}

	/**
	 * Prompts the user for a char value by displaying prompt. Note: This method
	 * should not be a new line to the output of prompt.
	 *
	 * After prompting the user, the method will read an entire line of input and
	 * return the first non-whitespace character converted to lower case.
	 *
	 * @param sc     The Scanner instance to read from System.in
	 * @param prompt The user prompt.
	 * @return Returns the first non-whitespace character (in lower case) read from
	 *         the user. If there are no non-whitespace characters read, the null
	 *         character is returned.
	 */
	public static char promptChar(Scanner sc, String prompt) {
		System.out.print(prompt);
		String yon = sc.nextLine(); // a user input to exit the game
		yon = yon.toLowerCase();//change the characters in the string to lower case
		return yon.charAt(0);//returned the first character of the string
	}

	/**
	 * Prompts the user for a string value by displaying prompt. Note: This method
	 * should not be a new line to the output of prompt.
	 *
	 * After prompting the user, the method will read an entire line of input,
	 * remove any leading and trailing whitespace, and return the input converted to
	 * lower case.
	 *
	 * @param sc     The Scanner instance to read from System.in
	 * @param prompt The user prompt.
	 * @return Returns the string entered by the user, converted to lower case with
	 *         leading and trailing whitespace removed.
	 */
	public static String promptString(Scanner sc, String prompt) {
		// FIX ME
		System.out.print(prompt);
		String inputDelta = sc.nextLine();// user input of delta or the user input of quit.
		inputDelta = inputDelta.toLowerCase();//change the characters in the string to lowercase
		inputDelta = inputDelta.trim();//delete the white space
		return inputDelta;
	}

	/**
	 * Initializes the game board to a given level. You can assume that the level at
	 * lvl has been successfully verified by the checkLevel method and that pos is
	 * an array of length 2.
	 *
	 * 1 - The game board should be created row-by-row. a - For each row, copy the
	 * values from the corresponding row in the 2-d array contained at index lvl in
	 * levels. b - When the worker is located, it's position should be recorded in
	 * the pos parameter. 2 - For each goal described in the array at index lvl of
	 * goals, convert the character at the goal coordinate to: -
	 * Config.WORK_GOAL_CHAR if it contains the worker - Config.BOX_GOAL_CHAR if it
	 * contains a box - Config.GOAL_CHAR otherwise
	 * 
	 * @param lvl    The index of the level to load.
	 * @param levels The array containing the levels.
	 * @param goals  The parallel array to levels, containing the goals for the
	 *               levels.
	 * @param pos    The starting pos of the worker. A length 2 array, where index 0
	 *               is the row and index 1 is the column.
	 * @return A two dimension array representing the initial configuration for the
	 *         given level.
	 */
	public static char[][] initBoard(int lvl, char[][][] levels, int[][] goals, int[] pos) {
		// FIX ME
		char[][] copy = new char[levels[lvl].length][];//the copy of the game board
		for (int i = 0; i < levels[lvl].length; i++) {
			copy[i] = new char[levels[lvl][i].length];
			for (int j = 0; j < levels[lvl][i].length; j++) {
				copy[i][j] = levels[lvl][i][j];
				if (levels[lvl][i][j] == Config.WORKER_CHAR) {
					pos[0] = i;
					pos[1] = j;
				}
			}
		}
		//change the characters in the copied board to the appropriate characters
		for (int i = 0; i < goals[lvl].length; i = i + 2) {     
			if (copy[goals[lvl][i]][goals[lvl][i + 1]] == Config.WORKER_CHAR) {
				copy[goals[lvl][i]][goals[lvl][i + 1]] = Config.WORK_GOAL_CHAR;
			} else if (copy[goals[lvl][i]][goals[lvl][i + 1]] == Config.BOX_CHAR) {
				copy[goals[lvl][i]][goals[lvl][i + 1]] = Config.BOX_GOAL_CHAR;
			} else {
				copy[goals[lvl][i]][goals[lvl][i + 1]] = Config.GOAL_CHAR;
			}
		}
		return copy;
	}

	/**
	 * Prints out the game board.
	 * 
	 * 1 - Since the game board does not contain the outer walls, print out a
	 * sequence of Config.WALL_CHAR with a length equal to that of the first row of
	 * board, plus the outer wall to the left and the right. 2 - For each row in
	 * board, print out a Config.WALL_CHAR, followed by the contents of the row,
	 * followed by a Config.WALL_CHAR. 3 - Finally, print out a sequence of
	 * Config.WALL_CHAR with a length equal to that of the last row of board, plus
	 * the outer wall to the left and the right.
	 *
	 * Note: each row printed out should be terminated by a new line.
	 *
	 * @param board The board to print.
	 */
	public static void printBoard(char[][] board) {
		//add wall characters at the first row of the board
		for (int i = 0; i < board[0].length; i++) {
			System.out.print(Config.WALL_CHAR);
			// FIX ME
		}
		System.out.print(Config.WALL_CHAR + "" + Config.WALL_CHAR);
		System.out.println();

		for (int i = 0; i < board.length; i++) {
			System.out.print(Config.WALL_CHAR);
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.print(Config.WALL_CHAR);
			System.out.println();
		}
		//add wall characters at the last row of the board
		for (int i = 0; i < board[board.length - 1].length; i++) {
			System.out.print(Config.WALL_CHAR);
			// FIX ME
		}
		System.out.print(Config.WALL_CHAR + "" + Config.WALL_CHAR);
		System.out.println();

	}

	/**
	 * Runs a given level through some basic sanity checks.
	 *
	 * This method performs the following tests (in order): 
	 * 1 - lvl >= 0 
	 * 2 - lvl is a valid index in levels, that the 2-d array at index lvl exists and that it
	 * contains at least 1 row. 
	 * 3 - lvl is a valid index in goals, the 1-d array at index lvl exists and that it 
	 * contains an even number of cells. 
	 * 4 - the number of boxes is more than 0. 
	 * 5 - the number of boxes equals the number of goals.
	 * 6 - the coordinate of each goal is valid for the given lvl and does not
	 * correspond to a wall cell. 
	 * 7 - the number of workers is exactly 1. 
	 * 8 - check for duplicate goals.
	 *
	 * @param lvl    The index of the level to load.
	 * @param levels The array containing the levels.
	 * @param goals  The parallel array to levels, containg the goals for the
	 *               levels.
	 * @return 1 if all tests pass. Otherwise if test: 
	 * - Test 1 fails: 0 
	 * - Test 2 fails: -1 
	 * - Test 3 fails: -2 
	 * - Test 4 fails: -3 
	 * - Test 5 fails: -4 
	 * - Test 6 fails: -5 
	 * - Test 7 fails: -6 
	 * - Test 8 fails: -7
	 * 
	 */
	public static int checkLevel(int lvl, char[][][] levels, int[][] goals) {
		// FIX ME
		// 1
		if (lvl < 0) {
			return 0;
		}
		// 2
		if (levels[0] == null || lvl > levels.length - 1) {
			return -1;
		}
		// 3
		int count2 = 0;// number of goals
		for (int j = 0; j < goals[lvl].length; j++) {
			count2 = count2 + 1;
		}
		if (count2 % 2 == 1 || lvl > goals.length - 1) {
			return -2;
		}
		// 4
		int count = 0;// number of boxes
		for (int j = 0; j < levels[lvl].length; j++) {
			for (int k = 0; k < levels[lvl][j].length; k++) {
				if (levels[lvl][j][k] == Config.BOX_CHAR) {
					count = count + 1;
				}
			}
		}
		if (count <= 0) {
			return -3;
		}
		// 5
		count2 = count2 / 2;
		if (count != count2) {
			return -4;
		}
		// 6
		boolean validgoal = false; //boolean to check if it is a valid goal
		for (int j = 0; j < goals[lvl].length - 1; j = j + 2) {
			if (levels[lvl][goals[lvl][j]][goals[lvl][j + 1]] == Config.WALL_CHAR) {
				validgoal = true;
			}
		}
		if (validgoal) {
			return -5;
		}
		// 7
		int count3 = 0;//number of workers.
		for (int j = 0; j < levels[lvl].length; j++) {
			for (int k = 0; k < levels[lvl][j].length; k++) {
				if (levels[lvl][j][k] == Config.WORKER_CHAR) {
					count3++;
				}
			}
		}
		if (count3 != 1) {
			return -6;
		}
		// Test 8 -- Add in comments to explain the code
		// for each goal coordinate, check from the next goal coordinate to the end of
		// the list to
		// see if there are some duplicated coordinates.
		for (int i = 0; i < goals[lvl].length - 1; i += 2) {
			for (int j = i + 2; j < goals[lvl].length - 1; j += 2) {
				if (goals[lvl][i] == goals[lvl][j] && goals[lvl][i + 1] == goals[lvl][j + 1]) {
					return -7;
				}
			}
		}
		return 1;
	}

	/**
	 * This method builds an int array with 2 cells, representing a movement vector,
	 * based on the String parameter.
	 *
	 * The rules to create the length 2 int array are as follows: - The 1st
	 * character of the String represents the direction. - The remaining characters
	 * (if there are any) are interpreted as integer and represent the magnitude or
	 * the number of steps to take.
	 *
	 * The cell at index 0 represents movement in the rows. Hence, a negative value
	 * represents moving up the rows and a positive value represents moving down the
	 * rows.
	 *
	 * The cell at index 1 represents movement in the columns. Hence, a negative
	 * value represents moving left in the columns and a positive value represents
	 * moving right in the columns.
	 *
	 * If the first character of moveStr does not match on of Config.UP_CHAR,
	 * Config.DOWN_CHAR, Config.LEFT_CHAR, or Config.RIGHT_CHAR, then return an
	 * array with 0 in both cells.
	 *
	 * If there are no characters after the first character of moveStr or the
	 * characters cannot be interpreted as an int, set the magnitude of the movement
	 * to 1.
	 *
	 * Hint: Use Scanner to parse the magnitude.
	 *
	 * Some examples: - If the parameter moveStr is "81": An array {-1, 0} would
	 * represent moving up by one character. - If the parameter moveStr is "65": An
	 * array {0, 5} would represent moving right by 5 characters.
	 *
	 * @param moveStr The string to parse.
	 * @return The calculated movement vector as a 2 cell int array.
	 */
	public static int[] calcDelta(String moveStr) {
		// FIX ME
		boolean hasdirection = false; //if the user input has the appropriate direction
		int[] move = new int[2]; //the array of 2 elements of delta to be returned
		char first = moveStr.charAt(0); //the first element of moveStr
		if (first == Config.UP_CHAR || first == Config.DOWN_CHAR || first == Config.LEFT_CHAR
				|| first == Config.RIGHT_CHAR) {
			hasdirection = true;
			if (hasdirection) {
				if (moveStr.length() == 1) {
					if (first == Config.UP_CHAR) {
						move[0] = -1;
						move[1] = 0;
					} else if (first == Config.DOWN_CHAR) {
						move[0] = 1;
						move[1] = 0;
					} else if (first == Config.LEFT_CHAR) {
						move[0] = 0;
						move[1] = -1;
					} else if (first == Config.RIGHT_CHAR) {
						move[0] = 0;
						move[1] = 1;
					}
				} else {
					boolean isdigit = true;//to check the number of steps from user input is digit
					for (int i = 1; i < moveStr.length(); i++) {
						if (!Character.isDigit(moveStr.charAt(i))) {
							isdigit = false;
						}
					}
					if (isdigit) {
						int mag = Integer.parseInt(moveStr.substring(1));//magnitude of steps
						if (first == Config.UP_CHAR) {
							move[0] = -1 * mag;
							move[1] = 0;
						} else if (first == Config.DOWN_CHAR) {
							move[0] = 1 * mag;
							move[1] = 0;
						} else if (first == Config.LEFT_CHAR) {
							move[0] = 0;
							move[1] = -1 * mag;
						} else if (first == Config.RIGHT_CHAR) {
							move[0] = 0;
							move[1] = 1 * mag;
						}
					} else {
						if (first == Config.UP_CHAR) {
							move[0] = -1;
							move[1] = 0;
						} else if (first == Config.DOWN_CHAR) {
							move[0] = 1;
							move[1] = 0;
						} else if (first == Config.LEFT_CHAR) {
							move[0] = 0;
							move[1] = -1;
						} else if (first == Config.RIGHT_CHAR) {
							move[0] = 0;
							move[1] = 1;
						}
					}
				}
			} else {
				move[0] = 0;
				move[1] = 0;
			}

		}
		return move;
	}

	/**
	 * This method checks that moving from one position to another position is a
	 * valid move.
	 *
	 * To validate the move, the method should (in order) check: 1 - that pos is
	 * valid. 2 - that the character at pos in board is in the valid array. 3 - that
	 * the delta is valid. 4 - that the new position is valid and not a wall
	 * character. 5 - that the new position is not a box character For what makes
	 * each test invalid, see the return details below.
	 *
	 * @param board The current board.
	 * @param pos   The position to move from. A length 2 array, where index 0 is
	 *              the row and index 1 is the column.
	 * @param delta The move distance. A length 2 array, where index 0 is the change
	 *              in row and index 1 is the change in column.
	 * @param valid A character array containing the valid characters for the cell
	 *              at pos.
	 * @return 1 if the move is valid. Otherwise: -1 : if pos is null, not length 2,
	 *         or not on the board. -2 : if the character at pos is not valid (not
	 *         in the valid array). -3 : if delta is null or not length 2. -4 : if
	 *         the new position is off the board or a wall character -5 : if the new
	 *         position is a box character
	 */
	public static int checkDelta(char[][] board, int[] pos, int[] delta, char[] valid) {
		// FIX ME
		// 1
		boolean tf1 = false; // first boolean to check the valid position
		if (pos == null || pos.length != 2) {
			return -1;
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (pos[0] == i && pos[1] == j) {
					tf1 = true;
				}
			}
		}
		if (!tf1) {
			return -1;
		}
		// 2
		boolean tf2 = false;//second boolean to check if the character is in the char[] valid list
		for (int i = 0; i < valid.length; i++) {
			if (board[pos[0]][pos[1]] == valid[i]) {
				tf2 = true;
			}
		}
		if (!tf2) {
			return -2;
		}
		// 3
		if (delta == null || delta.length != 2) {
			return -3;
		}
		// 4
		int newrow = pos[0] + delta[0];//the new row after moving
		int newcol = pos[1] + delta[1];//the new column after moving
		boolean tf3 = false;//third boolean to check new position is in the board

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (i == newrow && j == newcol) {
					tf3 = true;
				}
			}
		}
		if (!tf3 || board[newrow][newcol] == Config.WALL_CHAR) {
			return -4;
		}
		// 5 check if the nre position is a box character
		if (board[newrow][newcol] == Config.BOX_CHAR || board[newrow][newcol] == Config.BOX_GOAL_CHAR) {
			return -5;
		}

		return 1;
	}

	/**
	 * Changes a character on the board to one of two characters (opt1 or opt2),
	 * depending on the value of the cell.
	 *
	 * Check the cell at position pos. If the character is val, change it to opt1.
	 * Otherwise, change it to opt2.
	 *
	 * @param board The current board.
	 * @param pos   The position to change. A length 2 array, where index 0 is the
	 *              row and index 1 is the column.
	 * @param val   The value to check for in the board.
	 * @param opt1  The character to change to if the value is val.
	 * @param opt2  The character to change to if the value is not val.
	 */
	public static void togglePos(char[][] board, int[] pos, char val, char opt1, char opt2) {
		if (board[pos[0]][pos[1]] == val) {
			board[pos[0]][pos[1]] = opt1;
		} else {
			board[pos[0]][pos[1]] = opt2;
		}

		// FIX ME
	}

	/**
	 * Moves a box on the board.
	 *
	 * Step 1: Use your checkDelta method to check that the move is valid. Recall
	 * that there are 2 characters that can represent a box. Step 2: Use your
	 * togglePos method to correctly change the character at the new position to the
	 * appropriate box character. Step 3: Again use your togglePos method to
	 * correctly change the character at pos to the the appropriate character
	 * without a box.
	 *
	 * @param board The current board.
	 * @param pos   The position to change. A length 2 array, where index 0 is the
	 *              row and index 1 is the column.
	 * @param delta The move distance. A length 2 array, where index 0 is the change
	 *              in row and index 1 is the change in column.
	 * @return The return value of checkDelta if less than 1. Otherwise 1.
	 */
	public static int shiftBox(char[][] board, int[] pos, int[] delta) {
		char[] valid = { Config.BOX_CHAR, Config.BOX_GOAL_CHAR };//the list consisting of valid char
		int avalidDelta = checkDelta(board, pos, delta, valid);
		if (avalidDelta == 1) {
			int[] newpos = new int[2]; //the new position after the box moving
			newpos[0] = pos[0];//copy the first element of original position
			newpos[1] = pos[1];//copy the second element of original position
			newpos[0] = newpos[0] + delta[0];//update the row position
			newpos[1] = newpos[1] + delta[1];//update the column position
			char val = Config.GOAL_CHAR;//if the new position is a goal char
			char opt1 = Config.BOX_GOAL_CHAR;//change the new position to be a box goal character
			char opt2 = Config.BOX_CHAR;//else change the new position to be a box character
			togglePos(board, newpos, val, opt1, opt2);
			char val2 = Config.BOX_CHAR;//if the old position is a box char
			char opt11 = Config.EMPTY_CHAR;//change the old position to be a empty char
			char opt22 = Config.GOAL_CHAR;//else change the old position to be a goal char
			togglePos(board, pos, val2, opt11, opt22);
			// FIX ME
		}
		return avalidDelta;
	}

	/**
	 * Processes a move of the worker step-by-step.
	 *
	 * Go through the delta step-by-step, calling doMove for each step. That is, if
	 * the delta is {0, -3}, your method should call doMove three times with an
	 * argument of {0, -1} for the delta parameter of doMove. Or, if the delta is
	 * {6, 0}, it would call the doMove six times with an argument of {1, 0} for the
	 * delta parameter of the doMove method.
	 *
	 * During the processing of the move, if ever a call to doMove returns a value
	 * less than 1, your method should stop processing and return that value.
	 *
	 * Note: You can assume that one of the cells of delta will be 0.
	 *
	 * @param board The current board.
	 * @param pos   The position to change. A length 2 array, where index 0 is the
	 *              row and index 1 is the column.
	 * @param delta The move distance. A length 2 array, where index 0 is the change
	 *              in row and index 1 is the change in column.
	 * @return If both of the cells of delta are 0, return 0. If the call to doMove
	 *         returns a value less than 1, return that value. Otherwise, return 1.
	 */
	public static int processMove(char[][] board, int[] pos, int[] delta) {
		// FIX ME
		int steps = 0; // how many steps to move
		if (delta[0] == 0 && delta[1] == 0) {
			return 0;
		}
		int[] newdelta = new int[2];// delta with one step
		if (delta[0] == 0) {
			steps = delta[1];
			newdelta[0] = 0;
			newdelta[1] = delta[1] / Math.abs(steps);
		} else {
			steps = delta[0];
			newdelta[0] = delta[0] / Math.abs(steps);
			newdelta[1] = 0;
		}

		int moveBystep = 0;//the value  returned by the method doMove
		steps = Math.abs(steps);

		// int count = 0;
		for (int i = 0; i < steps; i++) {
			moveBystep = doMove(board, pos, newdelta); // move step by step
			// count = count + 1;
			if (moveBystep < 1) {
				// return count;
				return moveBystep;
			}
		}

		return 1;
	}

	/**
	 * Moves the worker on the board.
	 *
	 * Step 1: Use your checkDelta method to check that the move is valid. Recall
	 * that there are 2 characters that can represent the worker. Step 2: If
	 * checkDelta returns -5, use your shiftBox method to move the box by delta
	 * before moving the worker. Step 3: Use your togglePos method to correctly
	 * change the character at the new position to the appropriate worker character.
	 * Step 4: Again use your togglePos method to correctly change the character at
	 * pos to the the appropriate character without a worker. Step 5: Update the
	 * position of the worker in pos.
	 *
	 * @param board The current board.
	 * @param pos   The position to change. A length 2 array, where index 0 is the
	 *              row and index 1 is the column.
	 * @param delta The move distance. A length 2 array, where index 0 is the change
	 *              in row and index 1 is the change in column.
	 * @return If checkDelta returns a value less than 1 that is not -5, return that
	 *         value. If checkDelta returns -5 and shiftBox returns a value less
	 *         than 0, return 0. Otherwise, return 1.
	 */
	public static int doMove(char[][] board, int[] pos, int[] delta) {
		// FIX ME
		char[] valid = { Config.WORKER_CHAR, Config.WORK_GOAL_CHAR };//list of all valid character
		int validDelta = checkDelta(board, pos, delta, valid);//value returned from the charDelta
		int boxShif = 0;//value returned by the shiftBox
		if (validDelta < 1 && validDelta != -5) {//delta is not valid and new position isn't a box
			return validDelta;
		}

		int[] oldpos = new int[2];//copy the original position 
		oldpos[0] = pos[0];
		oldpos[1] = pos[1];
		pos[0] = pos[0] + delta[0];
		pos[1] = pos[1] + delta[1];
		if (validDelta == -5) {//new position is a box char
			boxShif = shiftBox(board, pos, delta);
			if (boxShif < 0) {
				pos[0] = oldpos[0];
				pos[1] = oldpos[1];
				return 0;
			}
		}

		char val = Config.GOAL_CHAR;//if the new position is a goal char
		char opt1 = Config.WORK_GOAL_CHAR;//change the new position to be a work goal char
		char opt2 = Config.WORKER_CHAR;//else change it to be the worker char
		togglePos(board, pos, val, opt1, opt2);
		char val2 = Config.WORKER_CHAR;//if the old poeition is a woker char
		char opt11 = Config.EMPTY_CHAR;//change it to be empty char
		char opt22 = Config.GOAL_CHAR;//else change it to be the goal char
		togglePos(board, oldpos, val2, opt11, opt22);

		return 1;
	}

	/**
	 * Checks all the cells in board and ensures that there are no goals that are
	 * not covered by boxes.
	 *
	 * @param board The current board.
	 * @return true if all the goals are covered by boxes. Otherwise, false.
	 */
	public static boolean checkWin(char[][] board) {

		boolean win = true;//check the condition of win
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == Config.GOAL_CHAR || board[i][j] == Config.WORK_GOAL_CHAR) {
					win = false;
				}
			}
		}
		// FIX ME
		return win;
	}

	/**
	 * This is the main method for the Sokoban game. It consists of the main game
	 * and play again loops with calls to the various supporting methods. The
	 * details of the main method for each milestone can be found in the BP1 -
	 * Sokoban write-up on the CS 200 webpage:
	 * https://cs200-www.cs.wisc.edu/wp/programs/
	 *
	 * For all milestones, you will need to create a Scanner object to read from
	 * System.in that you will pass to the helper methods.
	 *
	 * For milestone 3, you will need to create a Random object using Config.SEED as
	 * the seed. This object should be create at the beginning of the method,
	 * outside of any loops.
	 *
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] pos = new int[2];//an array of position with two element (row and column)
		System.out.println("Welcome to Sokoban!");
		char yon;//user input of 'yes' or 'no'
		Random rand = new Random(Config.SEED);//random number with seed of Config.SEED
		int max = Config.LEVELS.length - 1; //max level
		String promp = "Choose a level between " + 0 + " and " + max + ": ";
		do {
			int lvl = promptInt(sc, promp, 0, max);//level of user input
			if (lvl == -1) {
				lvl = rand.nextInt(max + 1);//use random to select a level
			}

			int isValidLevel = checkLevel(lvl, Config.LEVELS, Config.GOALS);//check level is valid
			if (isValidLevel == 1) {//level is valid
				System.out.println("Sokoban Level " + lvl);
				char[][] copy = initBoard(lvl, Config.LEVELS, Config.GOALS, pos);//initialize board
				sc.nextLine();//input the enter returned from keyboard
				boolean strpro = true;//check if to print map in a loop
				int counter = 0;//count the number of steps
				while (strpro) {
					printBoard(copy);
					String userinput = promptString(sc, ": ");//user input if to move or quit
					if (userinput.isEmpty()) {
					} else if (userinput.charAt(0) == Config.QUIT_CHAR) {
						strpro = false;//exit the loop
					} else {
						int[] delta = calcDelta(userinput);
						if (delta[0] != 0 || delta[1] != 0) {
							if (delta[0] == 0) {
								counter = counter + Math.abs(delta[1]);
							} else {
								counter = counter + Math.abs(delta[0]);
							}
							processMove(copy, pos, delta);
						}
					}
					if (checkWin(copy)) {
						System.out.println("Congratulations! You won in " + counter + " moves!");
						printBoard(copy);
						strpro = false;//exit the loop
					}
				}

				yon = promptChar(sc, "Play again? (y/n) ");
			} else {
				System.out.println("Error loading level!");
				yon = 'n';
				if (isValidLevel == 0) {
					System.out.println("Level lvl must be 0 or greater!");
				} else if (isValidLevel == -1) {
					System.out.println("Error with Config.LEVELS");
				} else if (isValidLevel == -2) {
					System.out.println("Error with Config.GOALS");
				} else if (isValidLevel == -3) {
					System.out.println("Level lvl does not contain any boxes.");
				} else if (isValidLevel == -4) {
					System.out.println("Level lvl does not have the same number of boxes as goals.");
				} else if (isValidLevel == -5) {
					System.out.println("Level lvl has a goal location that is a wall.");
				} else if (isValidLevel == -6) {
					System.out.println("Level lvl has 0 or more than 1 worker(s).");
				} else if (isValidLevel == -7) {
					System.out.println("Level lvl contains duplicate goals.");
				} else {
					System.out.println("Unknown Error");
				}
			}
		} while (yon == 'y');
		System.out.println("Thanks for playing!");
	}
}
