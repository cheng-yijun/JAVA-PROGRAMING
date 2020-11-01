//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            ElizaTests
// Files:            ElizaTests.java
// Semester:         Fall2018
//
// Author:           Yijun Cheng
// Email:            cheng229@cs.wisc.edu
// CS Login:         yijunc
// Lecturer's Name:  <cs200>
// Lab Section:      <321>  
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Online source: Confirmed the equation for calculating the area of a
//                circle: https://www.mathsisfun.com/geometry/circle-area.html
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class contains a few methods for testing methods in the Eliza
 * class as they are developed. These methods are private since they are only
 * intended for use within this class.
 * 
 * @author Jim Williams
 * @author Yijun Cheng
 *
 */
//This is a test-calss used to test every methods in the Eliza.calss
//if the value returned by the method of Eliza.java, then the test will be passed
//Otherwise the test will tell you the value returned and the expected value.
public class ElizaTests {
	
    /**
     * This is the main method that runs the various tests. Uncomment the tests
     * when you are ready for them to run.
     * 
     * @param args  (unused)
     */
	//This is the main method.
	//In the main method, we can call test method.
	//If we want test the specific method of Eliza.java, 
	//then we can add notation "//" before other method calls
	//It is very convenient 
	public static void main(String[] args) {
		//Milestone 1: Process User Input
		//M1: main	nothing to do	
		testSeparatePhrases();  
		testFoundQuitWord();    
		testSelectPhrase();     
		testReplaceWord();      
		testAssemblePhrase();   

		//Milestone 2: 
		//M2: implement parts of main as described in main method comments
		testSwapWords();
		testPrepareInput();
	    testLoadResponseTable();
		
		//Milestone 3: 
		//main: implement the rest of main as described in the main method comments
		testFindKeyWordsInPhrase();  
		testSelectResponse();      
		testInputAndResponse(); 
		testSaveDialog();			
	}
	
	
	/**
	 * This runs some tests on the separatePhrases method. 
	 * 1. TODO describe each test in your own words. 
	 * 2.
	 */	
	//this test method is used to test the method SeparatePhrases in Eliza.calss
	//Since the method SeparatePhrases can separate user input into some phrases,
	//we can test some user inputs and see if the method can return the expected phrases.
	private static void testSeparatePhrases() {
		boolean error = false;

		// 1.
		//if the user input is "No.  I'm talking 2 to my dog) ! Bye.. .. "
		//Then, we will get an ArrayList with 3 elements.
		//So, if the number of elements is not correct
		//then print the error.
		ArrayList<String> phrases =
				Eliza.separatePhrases("No.  I'm talking 2 to my dog) ! Bye.. .. ");
		ArrayList<String> expected = new ArrayList<>();
		expected.add("no");
		expected.add("i'm talking 2 to my dog ");
		expected.add("bye");
		if (phrases.size() != expected.size()) {
			error = true;
			System.out.println("testSeparatePhrases: expected " +
			expected.size() + " but found " + phrases.size() + " phrases.");
		}

		// 2.
		//Still for the same user input, check all the elements returned with the
		//expected ArrayList one by one.
		//If there is any difference, then print the different one.
		for (int i = 0; i < expected.size(); i++) {
			if (!expected.get(i).equals(phrases.get(i))) {
				error = true;
				System.out.println("testSeparatePhrases: phrases not the same");
				System.out.println("  " + expected.get(i));
				System.out.println("  " + phrases.get(i));
			}
		}
		// 3.
		//if the user input is "What? This isn't the 4th messy-sentence!"
				//Then, we will get an ArrayList with 2 elements.
				//So, if the number of elements is not correct
				//then print the error.
		String userInput2 = "What? This isn't the 4th messy-sentence!";
		ArrayList<String> phrases2 = Eliza.separatePhrases(userInput2);
		ArrayList<String> expected2 = new ArrayList<>();
		expected2.add("what");
		expected2.add("this isn't the 4th messy sentence");
		if (phrases2.size() != expected2.size()) {
			error = true;
			System.out.println("testSeparatePhrases: expected " + 
			expected2.size() + " but found " + phrases2.size() + " phrases.");
		}
		
		//4
		//Still for the same user input, check all the elements returned with the
				//expected ArrayList one by one.
				//If there is any difference, then print the different one.
		for (int i = 0; i < expected2.size(); i++) {
			if (!expected2.get(i).equals(phrases2.get(i))) {
				error = true;
				System.out.println("testSeparatePhrases: phrases not the same");
				System.out.println("  " + expected2.get(i));
				System.out.println("  " + phrases2.get(i));
			}
		}
		
		//5
		//if the user input is "No"
		//Then, we will get an ArrayList with 1 elements.
		//So, if the number of elements is not correct
		//then print the error.
		String userInput3 = "No";
		ArrayList<String> phrases3 = Eliza.separatePhrases(userInput3);
		ArrayList<String> expected3 = new ArrayList<>();
		expected3.add("no");
		if (phrases3.size() != expected3.size()) {
			error = true;
			System.out.println("testSeparatePhrases: expected " +
			expected3.size() + " but found " + phrases3.size() + " phrases.");
		}
		//6
		//Still for the same user input, check all the elements returned with the
		//expected ArrayList one by one.
		//If there is any difference, then print the different one.
		for (int i = 0; i < expected3.size(); i++) {
			if (!expected3.get(i).equals(phrases3.get(i))) {
				error = true;
				System.out.println("testSeparatePhrases: phrases not the same");
				System.out.println("  " + expected3.get(i));
				System.out.println("  " + phrases3.get(i));
			}
		}
		//7
		//if the user input is "this tab"
				//Then, we will get an ArrayList with 1 elements.
				//So, if the number of elements is not correct
				//then print the error.
		String userInput4 = "this tab";
		ArrayList<String> phrases4 = Eliza.separatePhrases(userInput4);
		ArrayList<String> expected4 = new ArrayList<>();
		expected4.add("this tab");
		if (phrases4.size() != expected4.size()) {
			error = true;
			System.out.println("testSeparatePhrases: expected " +
			expected4.size() + " but found " + phrases4.size() + " phrases.");
		}
		//8
		//Still for the same user input, check all the elements returned with the
		//expected ArrayList one by one.
		//If there is any difference, then print the different one.
		for (int i = 0; i < expected4.size(); i++) {
			if (!expected4.get(i).equals(phrases4.get(i))) {
				error = true;
				System.out.println("testSeparatePhrases: phrases not the same");
				System.out.println("  " + expected4.get(i));
				System.out.println("  " + phrases4.get(i));
			}
		}
		// Additional test suggestions
		// "What? This isn't the 4th messy-sentence!" should result in ?
		// "NO" should result in?
		// "this tab" should result in?
		// "What?" should result in?
		// "Thank you, but no, I have to go. Goodbye!!!" should result in?

		if (error) {
			System.out.println("testSeparatePhrases failed");
		} else {
			System.out.println("testSeparatePhrases passed");
		}
	}
	/**
	 * This runs some tests on the foundQuitWord method. 
	 * 1. TODO describe each test in your own words. 
	 * 2.
	 */
	// TODO should QUIT_WORDS be embedded within foundQuitWord?
	//we can use the Config.java
	//then compare with the Config.QUIT_WORDS to see if there is any quit word.
	private static void testFoundQuitWord() {
		boolean error = false;

		// 1.
		//In the ArrayList phrase,
		//there is a quit word which is "bye"
		//So, check the foundQuitWord method to see if it will return false
		ArrayList<String> phrases = new ArrayList<>();
		phrases.add("thank you for talking");
		phrases.add("bye");

		boolean quit = Eliza.foundQuitWord(phrases);
		if (!quit) {
			error = true;
			System.out.println("testFoundQuitWord 1: failed");
		}

		//2
		//if foundQuitWord return true if bye is a part of a phrase rather than
		// separate, it should return false
		ArrayList<String> phrases2 = new ArrayList<>();
		phrases2.add("thank you for bye");
		phrases2.add("ok");

		boolean quit2 = Eliza.foundQuitWord(phrases2);
		if (quit2) {
			error = true;
			System.out.println("testFoundQuitWord 2: failed");
		}
		
		//3
		//foundQuitWord should return true if goodbye is the first or only phrase?
		ArrayList<String> phrases3 = new ArrayList<>();
		phrases3.add("goodbye");

		boolean quit3 = Eliza.foundQuitWord(phrases3);
		if (!quit3) {
			error = true;
			System.out.println("testFoundQuitWord 3: failed");
		}
		// additional test suggestions:
		// should foundQuitWord return true if bye is a part of a phrase rather than
		// separate?
		// should foundQuitWord return true if goodbye is the first or only phrase?
		// should foundQuitWord return true if there are no quit words in the phrases
		// list.

		if (error) {
			System.err.println("testFoundQuitWord failed");
		} else {
			System.out.println("testFoundQuitWord passed");
		}
	}

	/**
	 * This runs some tests on the selectPhrase method. 
	 * 1. TODO describe each test in your own words. 
	 * 2.
	 */	
	private static void testSelectPhrase() {
		boolean error = false;

		// choose the longest
		//Create an ArrayList phrases.
		//Then add elements in it.
		//For this one, the longest element is the second one
		//So, the String choice returned should be equal to the second one
		//If not, print the error.
		//1.
		ArrayList<String> phrases = new ArrayList<>();
		phrases.add("no");
		phrases.add("sometimes I remember being on a boat");
		phrases.add("not often");
		String choice = Eliza.selectPhrase(phrases);
		if (!choice.equals("sometimes I remember being on a boat")) {
			error = true;
			System.out.println("testSelectPhrase 1: failed");
		}

		//2.
		//If there are 2 choices with the same length, 
		//then it will return the first one.
		//If no, print the error
		ArrayList<String> phrases2 = new ArrayList<>();
		phrases2.add("aaaaaaaaa");
		phrases2.add("not often");
		String choice2 = Eliza.selectPhrase(phrases2);
		if (!choice2.equals("aaaaaaaaa")) {
			error = true;
			System.out.println("testSelectPhrase 2: failed");
		}
		//3.
		//if an empty list is passed to selectPhrase,
		//then it will retuen an empty String "".
		ArrayList<String> phrases3 = new ArrayList<>();
		String choice3 = Eliza.selectPhrase(phrases3);
		if (!choice3.equals("")) {
			error = true;
			System.out.println("testSelectPhrase 3: failed");
		}
		
		// additional test suggestions:
		// What should happen when there are 2 choices of the same length?
		// What should happen if an empty list is passed to selectPhrase?

		if (error) {
			System.err.println("testSelectPhrase failed");
		} else {
			System.out.println("testSelectPhrase passed");
		}
	}

	/**
	 * This runs some tests on the assemblePhrase method. 
	 * 1. TODO describe each test in your own words. 
	 * 2.
	 */
	private static void testAssemblePhrase() {
		//1.
		//Given an array of words
		//assemble the words into a sentence by calling AssemblePhrase method
		//then, compare the sentence with the expected one
		boolean error = false;
		String[] words = { "This", "is a", "sentence" };
		String sentence = Eliza.assemblePhrase(words);
		String expectedSentence = "This is a sentence";

		if (!sentence.equals(expectedSentence)) {
		    error = true;
			System.out.println("testAssembleSentence 1 failed '" + sentence + "'");
		}
		//2.
		//if an array with no strings in it is passed in
		//then an empty String will be returned
		//compare the sentence with the expected sentence.
		String[] words2 = {};
		String sentence2 = Eliza.assemblePhrase(words2);
		String expectedSentence2 = "";

		if (!sentence2.equals(expectedSentence2)) {
		    error = true;
			System.out.println("testAssembleSentence 1 failed '" + sentence2 + "'");
		}
		
		//3
		//if just a list of words, with no internal spaces, is
		// passed in, then return that word as an sentence.
		//compare the sentence with the expected sentence.
		String[] words3 = {"aaaaaaaaa"};
		String sentence3 = Eliza.assemblePhrase(words3);
		String expectedSentence3 = "aaaaaaaaa";

		if (!sentence3.equals(expectedSentence3)) {
		    error = true;
			System.out.println("testAssembleSentence 1 failed '" + sentence3 + "'");
		}
		
		// additional test suggestions:
		// what should happen if an array with no strings in it is passed in?
		// what should happen if just a list of words, with no internal spaces, is
		// passed in?

		if (error) {
			System.err.println("testAssemblePhrase failed");
		} else {
			System.out.println("testAssemblePhrase passed");
		}
	}

	/**
	 * This runs some tests on the findKeyWordsInPhrase method. 
	 * 1. TODO describe each test in your own words. 
	 * 2.
	 */
	private static void testFindKeyWordsInPhrase() {
		boolean error = false;

		{// block so each test has its own variable scope.
			// 1.
			//Given a keywords ArrayList with keywords
			//Check to see if the phrase with keyword on the end of the sentence
			//can be separated appropriately according to the keywords.
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("computer");
			String[] phrase = { "are", "you", "a", "computer" };
			String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
			if (matches == null || matches.length != 2 || !matches[0].equals("are you a") ||
					!matches[1].equals("")) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 1 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}

		{
			// 2.
			//Given a keywords ArrayList with keywords
			//Check to see if the phrase with keyword on the beginning of the sentence
			//can be separated appropriately according to the keywords.
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("computer");
			String[] phrase = { "computer", "are", "you" };

			String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
			if (matches == null || matches.length != 2 || !matches[0].equals("") ||
					!matches[1].equals("are you")) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 2 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}

		{
			// 3.
			//Given a keywords ArrayList with keywords
			//Check to see if the phrase with keyword on the middle of the sentence
			//can be separated appropriately according to the keywords.
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("computer");
			String[] phrase = { "does", "that", "computer", "on", "your", "desk", "work" };
			String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
			if (matches == null || matches.length != 2 || !matches[0].equals("does that")
					|| !matches[1].equals("on your desk work")) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 3 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}

		{
			// 4.
			//Given a keywords ArrayList with 2 keywords
			//Check to see if the phrase with the 2 keywords in order
			//can be separated appropriately according to the keywords.
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("you");			
			keywords.add("me");			
			String[] phrase = { "why", "don't", "you", "like", "me" };
			String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
			if (matches == null || matches.length != 3 || !matches[0].equals("why don't") ||
					!matches[1].equals("like")
					|| !matches[2].equals("")) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 4 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}

		{
			// 5.
			//Given a keywords ArrayList with 2 keywords
			//Check to see if the phrase with the 2 keyword but with wrong order
			//can return null
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("you");			
			keywords.add("me");				
			String[] sentence = { "me", "don't", "like", "you" };
			String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
			if (matches != null) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 5 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}
			
		{
			//6.
			//Given a keywords ArrayList with 2 keywords
			//Check to see if the phrase with the 2 keyword but with wrong order
			//can return null
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("keyword");			
			keywords.add("for");				
			String[] sentence = { "test", "phrase", "for", "incorrect","keyword",
					"ordering","in","zybooks" };
			String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
			if (matches != null) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 6 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}
		
		{
			// 7.
			//Given a keywords ArrayList with 1 keyword
			//Check to see if the phrase without the 1 keyword
			//can return null
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("computer");
			String[] phrase = { "aaa", "are", "you" };

			String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
			if (matches != null) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 7 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}
		
		// additional tests?
		{
			// 8.
			//Given a keywords ArrayList with 2 keywords
			//Check to see if the phrase only with 1 keyword
			//can return null
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("computer");
			keywords.add("ddd");
			String[] phrase = { "computer", "are", "you" };
			String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
			if (matches != null) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 7 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}
		if (error) {
			System.err.println("testFindKeyWordsInPhrase failed");
		} else {
			System.out.println("testFindKeyWordsInPhrase passed");
		}
	}
	
	/**
	 * This runs some tests on the saveDialog method. 
	 * 1. TODO describe each test in your own words. 
	 * 2.
	 */	
	private static void testSaveDialog()  {
		boolean error = false;
		final String TEST_FILENAME = "testing.txt";
		{ // 1.
		  //Test if a single line can be saved in to a file
		  //called "testing.txt"
			ArrayList<String> list = new ArrayList<>();
			list.add("this is a single line.");
			try {
				Eliza.saveDialog( list, TEST_FILENAME);
				String readFromFile = readFile( TEST_FILENAME);
				if ( !readFromFile.equals(list.get(0) + "\n")) {
					error = true;
					System.out.println("testSaveDialog 1 failed.");
					System.out.println("content read: " + readFromFile);
				}
				removeFile( TEST_FILENAME);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
				// additional tests
		{
			// 2. test multiple lines of output written to the file.
			//For multiple sentence in an ArrayList
			//Check to see if the saved file can correctly save the content of the 
			//AraryList.
			ArrayList<String> list = new ArrayList<>();
			list.add("this is the first line.");
			list.add("this is the second line");
			try {
				Eliza.saveDialog( list, TEST_FILENAME);
				String readFromFile = readFile( TEST_FILENAME);
				if ( !readFromFile.equals(list.get(0) + "\n" + list.get(1) + "\n") ) {
					error = true;
					System.out.println("testSaveDialog 2 failed.");
					System.out.println("content read: " + readFromFile);
				}
				removeFile( TEST_FILENAME);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 2. test multiple lines of output written to the file.
		// additional tests?

		if (error) {
			System.err.println("testSaveDialog failed");
		} else {
			System.out.println("testSaveDialog passed");
		}
	}
	
	/**
	 * Supporting method for testSaveDialog
	 * @param fileName name of the file to read
	 * @return the contents of the file
	 */
	//This is the support method to offer a support for the 
	//testSaveDialog() above.
	private static String readFile(String fileName) {
		StringBuffer buf = new StringBuffer();
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName));) {
			String line;
			while ((line = reader.readLine()) != null) {
				buf.append(line);
				buf.append("\n");
			}
			return buf.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Supporting method for testSaveDialog.
	 * 
	 * @param filename  file to be removed.
	 */
	//This is also a support method to offer a support 
	//for the testSaveDialog() method above
	private static void removeFile(String filename) {
		File file = new File(filename);
		try {
			if (file.exists())
				file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This runs some tests on the replaceword method. 
	 * 1. TODO describe each test in your own words. 
	 * 2.
	 */	
	private static void testReplaceWord()  {
		boolean error = false;
		
		{    //1. 
			//Given a word and a expected one, check the method ReplaceWord
			//to see if this method can replace the word to the expected one.
			String word = "machine";
			String expected = "computer";
			String result = Eliza.replaceWord(word, Config.INPUT_WORD_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testReplaceWord 1 failed. result:'" + result 
						+ "' expected:'" + expected + "'");
			}
		}	
		
		{    //2. 
			//Given a word and a expected one, check the method ReplaceWord
			//to see if this method can replace the word to the expected one.
			String word = "yourself";
			String expected = "myself";
			String result = Eliza.replaceWord(word, Config.PRONOUN_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testReplaceWord 2 failed. result:'" + result 
						+ "' expected:'" + expected + "'");
			}
		}
		
		
		//additional suggestions:
		//Do these tests meet all the requirements described in the replaceWord header comment,
		//such as handling a null wordMap?
		//So, these tests do not meet all the requirements. We need a test to test the null
		//wordMap situation
		
		
		{	//3.
			//If the wordMap is null, then for the requirement, we need to return the word itself
			String word = "yourself";
			String expected = "yourself";
			String result = Eliza.replaceWord(word, null);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testReplaceWord 2 failed. result:'" + result 
						+ "' expected:'" + expected + "'");
			}
		}
		
		{
			//4.
			//If the word itself is null, then we should return null in the method
			String word = null;
			String result = Eliza.replaceWord(word, null);
			if(result != null) {
				error = true;
				System.out.println("testReplaceWord 2 failed. result should be null here");
			}
		}
		
		if (error) {
			System.err.println("testReplaceWord failed");
		} else {
			System.out.println("testReplaceWord passed");
		}
	}
	
	/**
	 * This runs some tests on the swapWords method. 
	 * 1. TODO describe each test in your own words. 
	 * 2.
	 */	
	private static void testSwapWords()  {
		boolean error = false;
		
		{    //1. 
			//Given a someWords containing words that need to be swapped.
			//Check to see if the word has been swapped by using the Config.INPUT_WORD_MAP.
			//If no, then print the error.
			String someWords = "i'm cant recollect";
			String expected = "i am cannot remember";
			String result = Eliza.swapWords(someWords, Config.INPUT_WORD_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testSwapWords 1 failed. result:'" + result +
						"' expected:'" + expected + "'");
			}
		}		

		{    //2. 
			//Given a someWords containing words that need to be swapped.
			//Check to see if the word has been swapped by using the Config.PRONOUN_MAP.
			//If no, then print the error.
			String someWords = "i'm happy";
			String expected = "you are happy";
			String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testSwapWords 2 failed. result:'" + result + 
						"' expected:'" + expected + "'");
			}
		}
		
		{    //3. 
			//Given a someWords containing words that need to be swapped.
			//Check to see if the word has been swapped by using the Config.PRONOUN_MAP.
			//If no, then print the error.
			String someWords = "about my dog";
			String expected = "about your dog";
			String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testSwapWords 3 failed. result:'" + result + 
						"' expected:'" + expected + "'");
			}
		}
		
		//additional tests?
		{ //4.
			//Given a someWords containing words that need to be swapped.
			//Check to see if the word has been swapped by using the Config.INPUT_WORD_MAP.
			//If no, then print the error.
			String someWords = "about same dog";
			String expected = "about alike dog";
			String result = Eliza.swapWords(someWords, Config.INPUT_WORD_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testSwapWords 3 failed. result:'" + result + 
						"' expected:'" + expected + "'");
			}
		}
		
		if (error) {
			System.err.println("testSwapWords failed");
		} else {
			System.out.println("testSwapWords passed");
		}		
	}	

	/**
	 * This runs some tests on the selectResponse method. 
	 * 1. TODO describe each test in your own words. 
	 * 2.
	 */	
	private static void testSelectResponse() {
		boolean error = false;
		
		{  //1.
			// is one of the 3 strings chosen?
			//Yes, one of the 3 strings will be selected
			//Because the random number will help us to select the response
			Random randGen = new Random(434);
			ArrayList<String> strList = new ArrayList<>();
			strList.add("The");
			strList.add("happy");
			strList.add("cat");			
			String choice = Eliza.selectResponse(randGen, strList);

			if (!(choice.equals("The") || choice.equals("happy") || choice.equals("cat"))) {
				error = true;
				System.out.println("testSelectResponse 1 failed.");
			}
		}
		
		{ // 2.
			// if called 1000 times, are the choices approximately random?
			//Because the seed is fixed.
			//So the random number is fixed for each call.
			//So, we can forecast the result.
			Random randGen = new Random(765);
			ArrayList<String> strList = new ArrayList<>();
			strList.add("this");
			strList.add("is");
			strList.add("a");
			strList.add("list");
			strList.add("to");
			strList.add("choose");
			strList.add("from");
			int [] actualCount = new int[strList.size()];
			int [] expectedCount = new int[] {156, 146, 142, 138, 160, 130, 128};
			for (int iterationIndex = 0; iterationIndex < 1000; iterationIndex++) {
				String choice = Eliza.selectResponse(randGen, strList);
				for ( int wordIndex = 0; wordIndex < strList.size(); wordIndex++) {
					if ( choice.equals(strList.get(wordIndex))) {
						actualCount[wordIndex]++;
					}
				}
			}
			//since we set a seed on the random number generator we should know the expected 
			//outcome
			for ( int countIndex = 0; countIndex < actualCount.length; countIndex++) {
				if ( actualCount[countIndex] != expectedCount[countIndex]) {
					error = true;
					System.out.println("testSelectResponse 2 failed.");
					System.out.println("  expectedCount: " + Arrays.toString( expectedCount));
					System.out.println("  actualCount: " + Arrays.toString( actualCount));
				}
			}

		}
		
		//additional test suggestions: 
		{
			//3/
			//when a list a single string is provided, it will return that String.
			//So, let's see if we can get the original String in this test
			Random randGen = new Random(434);
			ArrayList<String> strList = new ArrayList<>();
			strList.add("The");
			String choice = Eliza.selectResponse(randGen, strList);
			if (!(choice.equals("The"))){
				error = true;
				System.out.println("testSelectResponse 3 failed.");
			}
			
		}
		//What should happen when a list a single string is provided? 
		//What should happen when null is passed to selectResponse?
		//For the null response list and empty response list passed in,
		//We should return null.
		
		
		if (error) {
			System.err.println("testSelectResponse failed");
		} else {
			System.out.println("testSelectResponse passed");
		}			
	}	

	/**
	 * This runs some tests on the prepareInput method. 
	 * 1. TODO describe each test in your own words. 
	 * 2.
	 */		
	private static void testPrepareInput() {
		boolean error = false;
		
		{ // 1.
			//For the single word which is a quit word in the user input.
			//check to see  if the method can return null
			String input = "bye";
			String[] result = null;
			result = Eliza.prepareInput("bye");
			if (result != null) {
				error = true;
				System.out.println("testPrepareInput 1: failed");
				System.out.println("  input: " + input);
				System.out.println("  result: " + Arrays.toString(result));
			}
		} 
		
		//additional test suggestions
		//"I said goodbye" should result in "i", "said", "goodbye"
		//"I can't do that" should result in "i", "cannot", "do", "that"
	
		{
			//2
			//"I said goodbye" should result in "i", "said", "goodbye"
			//check to see if the method can return the expected result
			//if no, print the error
			String input = "I said goodbye";
			String[] result = null;
			result = Eliza.prepareInput("I said goodbye");
			if(!result[0].equals("i") || !result[1].equals("said") 
					|| !result[2].equals("goodbye")) {
				error = true;
				System.out.println("testPrepareInput 2: failed");
				System.out.println("  input: " + input);
				System.out.println("  result: " + Arrays.toString(result));
			}
		}
		
		if (error) {
			System.err.println("testPrepareInput failed");
		} else {
			System.out.println("testPrepareInput passed");
		}	
	}
	
	
	
	
    /**
     * This runs some tests on the loadResponseTable method. 
     * 1. TODO describe each test in your own words. 
     * 2.
     */     
    private static void testLoadResponseTable() {
        boolean error = false;
        
        { // 1.
        	//When read and load the file "Eliza.rsp", the first element of the list 
        	//should be "computer". Then, check to see if the result if the expected one 
            ArrayList<String> expected1stRow = new ArrayList<String>();
            expected1stRow.add("computer");
            ArrayList<ArrayList<String>> table = Eliza.loadResponseTable("Eliza.rsp");
            if (!table.get(0).equals( expected1stRow)) {
                error = true;
                System.out.println("testLoadResponseTable 1: failed");
                System.out.println("  expected1stRow: " + expected1stRow);
                System.out.println("  table1stRow: " +table.get(0));
            }
            
            // 2.
            //this test is used to check the length of the array.
            //Since the keywords and response are pair. So the length should be a even number
            if ( table.size() % 2 != 0) {
                error = true;
                System.out.println("testLoadResponseTable 2: failed");
                System.out.println("  expected an even number of elements in table but found: " 
                + table.size());
            }
        }
        
        //additional test suggestions
        //Are the right number of keywords and responses read in for a keyword/response pair?
        //Are blank lines (containing no spaces or only whitespace) ignored?
        //Are the last rows of the file read in?
        //Are the right number of rows read in?
        
        if (error) {
            System.err.println("testLoadResponseTable failed");
        } else {
            System.out.println("testLoadResponseTable passed");
        }   
    }	
	
	/*
	 * Supporting method for testInputAndResponse.
	 * Returns 1 if the test passed and 0 if the test failed.
	 */
    //This is the support test method to check for the testInputAndResponse
    //if the result is expected, return 1
    //Then, we just need to check if the sum is expected
	private static int checkResponse(String input, String expectedResponse,
			Random rand, ArrayList<ArrayList<String>> table) {
		
		String [] words = Eliza.prepareInput(input);

		if ( words == null) {
			if ( expectedResponse == null) {
				return 1;
			} else {
				System.out.println("testInputLines  checkResponse error");
				System.out.println("  input='" + input + "'");
				System.out.println("  response=null");
				System.out.println("  expected='" + expectedResponse + "'");
				return 0;
			}
		}
		//System.out.println(words[2]);
		String response = Eliza.prepareResponse(words, rand, table);
		if ( !response.equals( expectedResponse)) {
			System.out.println("testPrepareResponse  checkResponse error");
			System.out.println("  input='" + input + "'");
			System.out.println("  response='" + response +"'");
			System.out.println("  expected='" + expectedResponse + "'");
			return 0;
		} else {
			return 1;
		}
	}
	
	/*
	 * Runs tests on a bunch of example sentences using a Random number generator with a seed.
	 */
	private static void testInputAndResponse() {
	    int numPassed = 0;
		//since we are using a seed in the random number generator, 
	    //the responses should be fixed for the provided response table.
		Random randGen = new Random( 923);
        ArrayList<ArrayList<String>> responseTable =
        		Eliza.loadResponseTable("Eliza" + Config.RESPONSE_FILE_EXTENSION);

        numPassed += checkResponse("I like computers.", "What do you think machines "
        		+ "have to do with your problem?", randGen, responseTable);
        
        numPassed += checkResponse("What is your name?", 
        		"I don't care about names -- please continue.", randGen, responseTable);
        numPassed += checkResponse("You remind me of a grumpy uncle.", 
        		"What other connections do you see?", randGen, responseTable);
        numPassed += checkResponse("You don't seem to say much.", "What are your feelings now?",
        		randGen, responseTable);
        numPassed += checkResponse("You reflect me.", "What makes you think I reflect you?", 
        		randGen, responseTable);
        numPassed += checkResponse("I wish to believe you.", 
        		"Why do you think you wish to believe me?", randGen, responseTable);
        numPassed += checkResponse("I dreamed of going into space.", "Have you ever dreamed "
        		+ "of going into space before?", randGen, responseTable);
        numPassed += checkResponse("I apologize for boring you.", "Why do you think you "
        		+ "apologize for boring me?", randGen, responseTable);
        numPassed += checkResponse("Because it seems the polite thing to do.", 
        		"Does that reason seem to explain anything else?", randGen, responseTable);
        numPassed += checkResponse("Yes.", "You are sure.", randGen, responseTable);
        numPassed += checkResponse("I remember being in 1st grade.", "What is the "
        		+ "connection between me and being in 1st grade?", randGen, responseTable);
        numPassed += checkResponse("No, not often. Sometimes I remember being on a boat.",
        		"Why do you recollect being on a boat just now?", randGen, responseTable);
        numPassed += checkResponse("My family liked to play cards.", 
        		"Tell me more about your family.", randGen, responseTable);
        numPassed += checkResponse("Do you remember growing up?", "What about growing up?", 
        		randGen, responseTable);
        numPassed += checkResponse("Are you a Packers fan?", 
        		"Perhaps I am a packers fan in your fantasies.", randGen, responseTable);
        numPassed += checkResponse("I am sad to hear that.", "Can you explain what made you sad?",
        		randGen, responseTable);
        numPassed += checkResponse("I cannot explain this.", 
        		"Do you really want to be able to explain this?", randGen, responseTable);
        numPassed += checkResponse("You seem to have a different perspective than many.",
        		"You're not really talking about me -- are you?", randGen, responseTable);
        numPassed += checkResponse("I'm talking to my dog.", 
        		"How long have you been talking to your dog?", randGen, responseTable);
        numPassed += checkResponse("goodbye", null, randGen, responseTable);
        numPassed += checkResponse("", "I'm not sure I understand you fully.", 
        		randGen, responseTable);
        //there are 21 tests. So the sum should be 21
        int expected = 21;
        if (numPassed == expected) {
            System.out.println("testInputAndResponse passed ");
        } else {
            System.err.println("testInputAndResponse failed " + (expected - numPassed));
        }   
	}
	
}
