
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
/**
 
* This file contains testing methods for the Sokoban project. These methods are intended to 
 * provide an example of a way to incrementally test your code, and to provide example method calls
 * for the Sokoban methods
 *
 * Toward these objectives, the expectation is that part of the grade for the Sokoban project is 
 * to write some tests and write header comments summarizing the tests that have been written. 
 * Specific places are noted with FIXME but add any other comments you feel would be useful.
 */

import java.util.Arrays;

/**
 * This class contains a few methods for testing methods in the Sokoban class as
 * they are developed. These methods are all private as they are only intended
 * for use within this class.
 * 
 * @author Marc Renault
 * @author Yijun Cheng && Yunzikai Chen
 *
 */
public class TestSokoban {

	/**
	 * This is the main method that runs the various tests. Uncomment the tests when
	 * you are ready for them to run.
	 * 
	 * @param args (unused)
	 */
	public static void main(String[] args) {
		// Milestone 1
		testCheckLevel();
		// Milestone 2
		testInitBoard();
		testCheckWin();
		testCalcDelta();
		testCheckDelta();
		// Milestone 3
		testTogglePos();
		testShiftBox();
		testDoMove();
		testProcessMove();
	}

	/**
	 * This method is used to check the method CheckLevel in Sokoban class. 
	 * Test 1: Check if if it return 0 if the lvl is invalid (lvl is smaller than 0) 
	 * Test 2: Check if if it return -3 if the levels is null 
	 * Test 3: Check if if it return -2 if the goals in a specific level has odd elements. 
	 * Test 4: Check if it return -3 if the number of boxes is less than 0 
	 * Test 5: Check if it return -4 if the number of boxes is not equal the number of goals. 
	 * Test 6: Check if it return -5 if the coordinate of goal is a wall char 
	 * Test 7: Check if it return -6 if the number of worker is not 1 
	 * Test 8: Check if it return -7 if there some duplicate goals.
	 */
	private static void testCheckLevel() {
		int numTests = 8;
		int passed = numTests;
		int res;
		// Test 1
		if ((res = Sokoban.checkLevel(-1, Config.LEVELS, Config.GOALS)) != 0) {
			System.out.println("FAILED: Sokoban.checkLevel Test 1. Expected 0, but value returned " + res);
			passed--;
		}

		// Test 2
		char[][][] lvl = new char[2][][];
		if ((res = Sokoban.checkLevel(1, lvl, Config.GOALS)) != -1) {
			System.out.println("FAILED: Sokoban.checkLevel Test 2. Expected -1, but value returned " + res);
			passed--;
		}

		// Test 3
		int[][] goals = new int[2][3];
		if ((res = Sokoban.checkLevel(1, Config.LEVELS, goals)) != -2) {
			System.out.println("FAILED: Sokoban.checkLevel Test 3. Expected -2, but value returned " + res);
			passed--;
		}

		// Test 4
		char[][][] lvl2 = new char[2][2][2];
		if ((res = Sokoban.checkLevel(1, lvl2, Config.GOALS)) != -3) {
			System.out.println("FAILED: Sokoban.checkLevel Test 4. Expected -3, but value returned " + res);
			passed--;
		}
		// Test 5
		char[][][] lvl3 = { { { Config.BOX_CHAR } }, { { Config.BOX_CHAR, Config.BOX_CHAR, Config.BOX_CHAR,
				Config.BOX_CHAR, Config.BOX_CHAR, Config.WORKER_CHAR, Config.WORKER_CHAR } } };

		if ((res = Sokoban.checkLevel(1, lvl3, Config.GOALS)) != -4) {
			System.out.println("FAILED: Sokoban.checkLevel Test 4. Expected -4, but value returned " + res);
			passed--;
		}

		// Test 6
		int[][] goal1 = { { 1, 1 }, { 0, 3, 2, 2, 1, 2, 1, 4, 1, 5, 1, 6 } };
		if ((res = Sokoban.checkLevel(1, Config.LEVELS, goal1)) != -5) {
			System.out.println("FAILED: Sokoban.checkLevel Test 4. Expected -5, but value returned " + res);
			passed--;
		}

		// Test 7
		char[][][] lvl4 = { { { Config.BOX_CHAR } }, { { Config.BOX_CHAR, Config.BOX_CHAR, Config.BOX_CHAR,
				Config.BOX_CHAR, Config.BOX_CHAR, Config.BOX_CHAR, Config.WORKER_CHAR, Config.WORKER_CHAR } } };
		int[][] goal2 = { { 0, 0 }, { 0, 1, 0, 2, 0, 3, 0, 4, 0, 5, 0, 0 } };
		if ((res = Sokoban.checkLevel(1, lvl4, goal2)) != -6) {
			System.out.println("FAILED: Sokoban.checkLevel Test 4. Expected -6, but value returned " + res);
			passed--;
		}
		// Test 8
		int[][] goal3 = { { 0, 0 }, { 0, 0, 0, 2, 0, 4, 0, 4, 0, 5, 0, 6 } };
		if ((res = Sokoban.checkLevel(1, Config.LEVELS, goal3)) != -7) {
			System.out.println("FAILED: Sokoban.checkLevel Test 4. Expected -7, but value returned " + res);
			passed--;
		}
		// FIXME Add more tests

		System.out.println("testCheckLevel: Passed " + passed + " of " + numTests + " tests.");
	}

	/**
	 * Returns true if the arrays are the same size and have the same contents.
	 */
	private static boolean compBoards(char[][] a, char[][] b) {
		if (a == null || b == null)
			return false;
		if (a.length != b.length)
			return false;
		for (int i = 0; i < a.length; i++) {
			if (!Arrays.equals(a[i], b[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Test1: Check if the position is {4,4} after initializing the level 0 board
	 * and if the board is the same with the expected board.
	 * Test2: Check if the position is {4,4} after initializing 
	 * the level 1 board and if the board is the same with the expected board.
	 */
	private static void testInitBoard() {
		int numTests = 2;
		int passed = numTests;

		// Test 1
		int[] pTest1 = new int[2];
		char[][] bTest1 = Sokoban.initBoard(0, Config.LEVELS, Config.GOALS, pTest1);
		if (!Arrays.equals(pTest1, new int[] { 4, 4 })) {
			System.out.println(
					"FAILED: Sokoban.initBoard Test 1. Expected initial position: {4, 4} , but value after call "
							+ Arrays.toString(pTest1));
			passed--;
		}
		char[][] bCompTest1 = new char[][] {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR } };
		if (!compBoards(bTest1, bCompTest1)) {
			System.out.println("FAILED: Sokoban.initBoard Test 1. Board not as expected!");
			System.out.println("Generated:");
			Sokoban.printBoard(bTest1);
			System.out.println("Expected:");
			Sokoban.printBoard(bCompTest1);
			passed--;
		}
		// End of Test 1
		// Test 2
		int[] pTest2 = new int[2];
		char[][] bTest2 = Sokoban.initBoard(1, Config.LEVELS, Config.GOALS, pTest2);
		if (!Arrays.equals(pTest2, new int[] { 7, 10 })) {
			System.out.println(
					"FAILED: Sokoban.initBoard Test 2. Expected initial position: {7, 10} , but value after call "
							+ Arrays.toString(pTest2));
			passed--;
		}
		char[][] bCompTest2 = new char[][] {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.BOX_CHAR },
				{ Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
						Config.WORKER_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR } };
		if (!compBoards(bTest2, bCompTest2)) {
			System.out.println("FAILED: Sokoban.initBoard Test 2. Board not as expected!");
			System.out.println("Generated:");
			Sokoban.printBoard(bTest2);
			System.out.println("Expected:");
			Sokoban.printBoard(bCompTest2);
			passed--;
		}
		// FIXME Add more tests

		System.out.println("testInitBoard: Passed " + passed + " of " + numTests + " tests.");
	}

	/**
	 * Test 1: Given a board that is not won, check if the method return false 
	 * Test 2: Given a board that is won, check if the method return true.
	 */
	private static void testCheckWin() {
		// Test 1
		int numTests = 2;
		int passed = numTests;
		boolean res;
		char[][] bCompTest1 = new char[][] {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR } };
		if ((res = Sokoban.checkWin(bCompTest1)) != false) {
			System.out.println("FAILED: Sokoban.checkWin Test 1. Expected false, but value returned " + res);
			passed--;
		}
		// Test 2
		char[][] bCompTest2 = new char[][] {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_GOAL_CHAR, Config.BOX_GOAL_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR } };
		if ((res = Sokoban.checkWin(bCompTest2)) != true) {
			System.out.println("FAILED: Sokoban.checkWin Test 2. Expected true, but value returned " + res);
			passed--;
		}

		System.out.println("testcheckWin: Passed " + passed + " of " + numTests + " tests.");
		// FIXME
	}

	/**
	 * Test 1: check if it is returned {0,0} if the first character of moveStr is
	 * not valid. 
	 * Test 2: check a valid moveStr and if it is the same with the
	 * expected delta.
	 */
	private static void testCalcDelta() {
		// Test 1
		int numTests = 2;
		int passed = numTests;
		String movestr = String.valueOf(Config.WALL_CHAR);
		int[] delta = new int[2];
		delta = Sokoban.calcDelta(movestr);
		if (!Arrays.equals(delta, new int[] { 0, 0 })) {
			System.out.println("FAILED: Sokoban.calcDelta Test 1. " + "Expected {0,0}, but value returned {" + delta[0]
					+ ", " + delta[1] + "}");
			passed--;
		}
		// Test 2
		String movestr2 = String.valueOf(Config.UP_CHAR) + "22";
		int[] delta2 = new int[2];
		delta2 = Sokoban.calcDelta(movestr2);
		if (!Arrays.equals(delta2, new int[] { -22, 0 })) {
			System.out.println("FAILED: Sokoban.calcDelta Test 2. " + "Expected {-22, 0}, but value returned {"
					+ delta[0] + ", " + delta[1] + "}");
			passed--;
		}

		System.out.println("testcalcDelta: Passed " + passed + " of " + numTests + " tests.");

	}

	/**
	 * Test 1: Check if it returned -1 if the pos is null 
	 * Test 2: Check if it returned -2 if the delta is {0, 0}. 
	 * Test 3: Check if it returned -3 if the character is not in the valid list 
	 * Test 4: Check if it returned -4 if the new position is a wall char 
	 * Test 5: Check if it returned -5 if the new position is a box char
	 */
	private static void testCheckDelta() {
		int numTests = 5;
		int passed = numTests;
		int res;
		int[] pos2 = null;
		int[] pos = new int[2];
		char[][] board = Sokoban.initBoard(1, Config.LEVELS, Config.GOALS, pos);
		int[] delta = new int[2];
		char[] valid = { Config.BOX_CHAR, Config.BOX_GOAL_CHAR };
		// Test 1
		if ((res = Sokoban.checkDelta(board, pos2, delta, valid)) != -1) {
			System.out.println("FAILED: Sokoban.checkDelta Test 1. Expected -1, but value returned " + res);
			passed--;
		}
		// Test 2
		if ((res = Sokoban.checkDelta(board, pos, delta, valid)) != -2) {
			System.out.println("FAILED: Sokoban.checkDelta Test 2. Expected -2, but value returned " + res);
			passed--;
		}
		// Test 3
		int[] delta2 = null;
		char[] valid2 = { Config.WORKER_CHAR, Config.WORK_GOAL_CHAR };
		if ((res = Sokoban.checkDelta(board, pos, delta2, valid2)) != -3) {
			System.out.println("FAILED: Sokoban.checkDelta Test 3. Expected -3, but value returned " + res);
			passed--;
		}
		// Test 4
		char[][] bCompTest1 = new char[][] {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.BOX_CHAR },
				{ Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
						Config.WORKER_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR } };
		int[] pos3 = new int[] { 7, 10 };
		int[] delta3 = new int[] { 0, -1 };
		if ((res = Sokoban.checkDelta(bCompTest1, pos3, delta3, valid2)) != -4) {
			System.out.println("FAILED: Sokoban.checkDelta Test 4. Expected -4, but value returned " + res);
			passed--;
		}
		// Test 5
		char[][] bCompTest2 = new char[][] {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.BOX_CHAR },
				{ Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
						Config.WORKER_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR } };

		if ((res = Sokoban.checkDelta(bCompTest2, pos3, delta3, valid2)) != -5) {
			System.out.println("FAILED: Sokoban.checkDelta Test 5. Expected -5, but value returned " + res);
			passed--;
		}
		System.out.println("testcheckDelta: Passed " + passed + " of " + numTests + " tests.");

	}

	/**
	 * Test 1: Given a board and the original position is a worker char. Check if
	 * the position is changed to a empty char after using the method Togglepos.
	 * Test 2:Given a board and the original position is a empty char. Check if the
	 * position is changed to a box char after using the method Togglepos.
	 */
	private static void testTogglePos() {
		int numTests = 2;
		int passed = numTests;
		char[][] bCompTest = new char[][] {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.BOX_CHAR },
				{ Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
						Config.WORKER_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR } };
		// Test 1
		int[] pos = new int[] { 7, 10 };
		Sokoban.togglePos(bCompTest, pos, Config.WORKER_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR);
		if (bCompTest[7][10] != Config.EMPTY_CHAR) {
			System.out.println("FAILED: Sokoban.testTogglePos Test 1. Expected empty char, but value returned "
					+ bCompTest[7][10]);
			passed--;
		}
		// Test 2
		int[] pos2 = new int[] { 0, 0 };
		Sokoban.togglePos(bCompTest, pos2, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.BOX_GOAL_CHAR);
		if (bCompTest[0][0] != Config.BOX_CHAR) {
			System.out.println(
					"FAILED: Sokoban.testTogglePos Test 2. Expected Box char, but value returned " + bCompTest[0][0]);
			passed--;
		}
		System.out.println("testTogglePos: Passed " + passed + " of " + numTests + " tests.");

	}

	/**
	 * Test 1: Check if a valid shift box returned -1 
	 * Test 2: Check if it is returned -4 if the new position is a wall char
	 */
	private static void testShiftBox() {
		int numTests = 2;
		int passed = numTests;
		int res;
		char[][] bCompTest = new char[][] {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.BOX_CHAR },
				{ Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.EMPTY_CHAR, Config.BOX_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
						Config.WORKER_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR } };
		int[] pos = new int[] { 6, 1 };
		int[] delta = new int[] { 0, -1 };
		res = Sokoban.shiftBox(bCompTest, pos, delta);
		if (res != 1 || bCompTest[6][0] != Config.BOX_CHAR || bCompTest[6][1] != Config.EMPTY_CHAR) {
			System.out.println("FAILED: Sokoban.testShiftBox Test 1. Expected 1, but value returned " + res);
			passed--;
		}
		// Test 2
		int[] delta2 = new int[] { 0, 2 };
		int[] pos2 = new int[] { 6, 0 };
		res = Sokoban.shiftBox(bCompTest, pos2, delta2);
		if (res != -4) {
			System.out.println("FAILED: Sokoban.testShiftBox Test 2. Expected -4, but value returned " + res);
			passed--;
		}
		System.out.println("testShiftBox: Passed " + passed + " of " + numTests + " tests.");

	}

	/**
	 * Test 1: Check a valid move to see if it is returned 1 and the characters on
	 * the new positions are changed to appropriate characters. Test 2: Check a
	 * invalid move to see if it is returned 0 if the new there is a wall char in
	 * front of the box which is pushing.
	 */
	private static void testDoMove() {
		int numTests = 2;
		int passed = numTests;
		int res;
		char[][] bCompTest = new char[][] {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.BOX_CHAR },
				{ Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
						Config.WORKER_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR } };
		// Test 1
		int[] pos = new int[] { 7, 10 };
		int[] delta = new int[] { 0, -1 };
		res = Sokoban.doMove(bCompTest, pos, delta);
		if (res != 1 || bCompTest[7][9] != Config.WORKER_CHAR || bCompTest[7][8] != Config.BOX_CHAR
				|| bCompTest[7][10] != Config.EMPTY_CHAR) {
			System.out.println("FAILED: Sokoban.testDoMove Test 1. Expected 1, but value returned " + res);
			passed--;
		}
		// Test 2
		int[] pos2 = new int[] { 7, 9 };
		int[] delta2 = new int[] { 0, -1 };
		res = Sokoban.doMove(bCompTest, pos2, delta2);
		if (res != 0) {
			System.out.println("FAILED: Sokoban.testDoMove Test 2. Expected 0, but value returned " + res);
			passed--;
		}
		System.out.println("testDoMove: Passed " + passed + " of " + numTests + " tests.");

	}

	/**
	 * Test 1: For a invalid move such that there is a wall char in front of the box
	 * which is pushing. And check if it is returned 0 Test 2: Check a valid move
	 * and see if it is returned 1
	 */
	private static void testProcessMove() {
		int numTests = 2;
		int passed = numTests;
		int res;
		char[][] bCompTest = new char[][] {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.BOX_CHAR },
				{ Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
						Config.WORKER_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR } };
		// Test 1
		int[] pos = new int[] { 7, 10 };
		int[] delta = new int[] { 0, -2 };
		res = Sokoban.processMove(bCompTest, pos, delta);
		if (res != 0) {
			System.out.println("FAILED: Sokoban.testProcessMove Test 1. Expected 0, but value returned " + res);
			passed--;
		}
		// Test 2
		int[] pos2 = new int[] { 7, 9 };
		int[] delta2 = new int[] { 0, 1 };
		res = Sokoban.processMove(bCompTest, pos2, delta2);
		if (res != 1) {
			System.out.println("FAILED: Sokoban.testProcessMove Test 1. Expected 0, but value returned " + res);
			passed--;
		}
		System.out.println("testProcessMove: Passed " + passed + " of " + numTests + " tests.");

	}

}
