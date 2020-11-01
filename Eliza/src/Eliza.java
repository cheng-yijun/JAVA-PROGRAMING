
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            Eliza
// Files:            Eliza.java
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

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The Eliza class holds the user input and response formation for a system that
 * collects user input and responds appropriately. Eliza is based off of a
 * computer program written at MIT in the 1960's by Joseph Weizenbaum. Eliza
 * uses keyword matching to respond to users in a way that displays interest in
 * the users and continues the conversation until instructed otherwise.
 */
//Eliza is a intelligent robot that can talk with human.
//Eliza can analyze the input of users can select the corresponding
//response from its database (here is the response table).
//So, this algorithm acts like a human that can talking.
public class Eliza {

	/*
	 * This method does input and output with the user. It calls supporting methods
	 * to read and write files and process each user input.
	 * 
	 * @param args (unused)
	 */
	//the main method is used to deal with the method calls and user prompts.
	//In the main, we can make Eliza deal with the user input by calling the methods below.
	//Then, it will get an appropriate response the the user.
	//So, it looks like a robot which can talk.
	public static void main(String[] args) {
		// Milestone 2
		// create a scanner for reading user input and a random number
		// generator with Config.SEED as the seed
		
		// Milestone 3
		// How the program starts depends on the command-line arguments.
		// Command-line arguments can be names of therapists for example:
		// Eliza Joe Laura
		// If no command-line arguments then the therapists name is Eliza
		// and this reads a file with that name and the Config.RESPONSE_FILE_EXTENSION.
		// Example filename: Eliza.rsp
		// If only one command-line argument, then read the responses from
		// the corresponding file with Config.RESPONSE_FILE_EXTENSION.
		// If there is more than one command-line argument then offer them
		// as a list of people to talk with. For the three therapists above the prompt
		// is
		// "Would you like to speak with Eliza, Joe, or Laura?"
		// When a user types a name then read the responses from the file which
		// is the selected name and Config.RESPONSE_FILE_EXTENSION extension.
		// Whatever name the user types has the extension appended and
		// is read using loadResponseTable. If loadResponseTable can't load
		// the file then it will report an error.
		// Milestone 2
		
		// name prompt

		// Milestone 2
		// begin conversation loop
		
		// Milestone 2
		// obtain user input

		// Milestone 2
		// prepareInput

		// Milestone 3
		// if no quit words then prepareResponse

		// Milestone 2
		// end loop if quit word

		// Milestone 2
		// ending prompt
		
		// Milestone 3
		// Save all conversation (user and system responses) starting
		// with this program saying "Hi I'm..." and concludes with
		// "Goodbye <name>.".
		// Always prompt the user to see if they would like to save a
		// record of the conversation. If the user enters a y or Y as the
		// first non-whitespace character then prompt for filename and save,
		// otherwise don't save dialog. After successfully saving a dialog
		// print the message "Thanks again for talking! Our conversation is saved in:
		// <filename>".
		// If saveDialog throws an IOException then catch it, print out the error:
		// "Unable to save conversation to: " <name of file>
		// Repeat the code prompting the user if they want to save the dialog.
		Scanner scrn = new Scanner(System.in);//The scanner is used to scan the user input
		Random randGen = new Random(Config.SEED);//this is a random generator with the fixed seed
		String userInput = null;// used to collect user input
		//save the dialog used for saving into file
		ArrayList<String> dialog = new ArrayList<String>();
		String therapist = null;//This is the robot who will talk with users
		//responseTable is load from a specific file
		ArrayList<ArrayList<String>> responseTable = new ArrayList<ArrayList<String>>();
		String response;//this is a string used to store the response and print to users
		//These are the command line operations. Check to see how many command arguments there
		//Then according to different arguments, select the appropriate therapist.
		if (args.length == 0) {//no argument, then the therapist is Eliza
			therapist = "Eliza";
		} else if (args.length == 1) {//one argument, it is the therapist
			therapist = args[0];
		} else {//many arguments, user can make choice
			System.out.print("Would you like to speak with");
			for (int i = 0; i < args.length - 1; i++) {
				System.out.print(" " + args[i] + ",");
			}
			System.out.print(" or " + args[args.length - 1] + "? ");
			therapist = scrn.next();
			scrn.nextLine();
		}
		//read file and load the data into the ArrayList responseTable.
		responseTable = loadResponseTable(therapist + Config.RESPONSE_FILE_EXTENSION);
		//namePrompt will be printed to user
		String namePrompt = "Hi I'm " + therapist + ", what is your name?";
		dialog.add(namePrompt);//record the namePrompt.
		System.out.println(namePrompt);
		//name is the name of the user
		String name = scrn.nextLine();//get the user's name
		dialog.add(name);//record the user's name
		//welcomPromt will be printed to user
		String welcomPrompt = "Nice to meet you " + name + ". What is on your mind?";
		System.out.println(welcomPrompt);
		dialog.add(welcomPrompt);//save the welcomPrompt

		boolean quit = false;//justify if there is a quit word
		String[] preparedInput = null;//used to store the data returned by method prepareInput
		//preparedInput2 is the ArrayList of the same content with String[] preparedInput 
		ArrayList<String> preparedInput2 = new ArrayList<String>();
		//while there is no quit word
		while (!quit) {
			userInput = scrn.nextLine();//get the user input
			preparedInput = prepareInput(userInput);//prepare the user input into appropriate form
			if (preparedInput == null) {//this means there is a quit word, so quit is true
				quit = true;
			} else {//store the content into the ArrayList form
				for (int i = 0; i < preparedInput.length; i++) {
					preparedInput2.add(preparedInput[i]);
				}
				if (foundQuitWord(preparedInput2)) {//then, search for the quit word again.
					quit = true;
				} else {//if no quit word, we need to prepare the response and print to users.
					response = prepareResponse(preparedInput, randGen, responseTable);
					dialog.add(response);//save the response to dialog
					System.out.println(response);
				}
			}
		}
		String endprompt = "Goodbye " + name + "."; // when conversation is end
		System.out.println(endprompt);// When user say some quit word, print the endPrompt
		dialog.add(endprompt);// save the last sentence
		String fileWriter;// sued to write the dialog into a file
		//Variable wantSave is to decide if the user want to save the dialog to a file
		boolean wantSave = false;
		do {//at least one loop
			wantSave = false;//assume the user do not want to save the dialog
			System.out.print("Would you like to have a record of our conversation (y/n): ");
			String answer = scrn.nextLine();//answer of user if he wants to save
			//If user wants to save:
			if (answer.length() != 0 && (answer.charAt(0) == 'y' || answer.charAt(0) == 'Y')) {
				System.out.print("Enter filename: ");
				fileWriter = scrn.nextLine();
				try {//try to write the dialog into a file.
					saveDialog(dialog, fileWriter);
					System.out.println("Thanks again for talking! Our conversation is saved in: "
					+ fileWriter);
				} catch (IOException e) {//if fail to write the dialog into a file
					wantSave = true;//loop to ask user to save dialog to another file name
					System.out.println("Unable to save conversation to: " + fileWriter);
				}
			}
		} while (wantSave == true);//if user wants to save the dialog, then loop again
		scrn.close();

	}

	/**
	 * This method processes the user input, returning an ArrayList containing
	 * Strings, where each String is a phrase from the user's input. This is done by
	 * removing leading and trailing whitespace, making the user's input all lower
	 * case, then going through each character of the user's input. When going
	 * through each character this keeps all digits, alphabetic characters and '
	 * (single quote). The characters ? ! , . signal the end of a phrase, and
	 * possibly the beginning of the next phrase, but are not included in the
	 * result. All other characters such as ( ) - " ] etc. should be replaced with a
	 * space. This method makes sure that every phrase has some visible characters
	 * but no leading or trailing whitespace and only a single space between words
	 * of a phrase. If userInput is null then return null, if no characters then
	 * return a 0 length list, otherwise return a list of phrases. Empty phrases and
	 * phrases with just invalid/whitespace characters should NOT be added to the
	 * list.
	 * 
	 * Example userInput: "Hi, I am! a big-fun robot!!!" Example returned: "hi", "i
	 * am", "a big fun robot"
	 * 
	 * @param userInput text the user typed
	 * @return the phrases from the user's input
	 */
	//this method will separate the user input to an ArrayList 
	//according to some specific notations. Generally speaking,
	//the separated sentences have some properties that we can use. 
	//In the following methods, we will use the separated ArrayList.
	public static ArrayList<String> separatePhrases(String userInput) {
		//used to store the sentences into phrases
		ArrayList<String> phrase = new ArrayList<String>();
		if (userInput == null) {
			return null;
		}
		if (userInput.length() == 0) {//if the user input is empty
			return phrase;
		} else {
			userInput = userInput.trim();//remove the useless space
			userInput = userInput.toLowerCase();
			int count = 0;//used to count for the phrases
			phrase.add("");//Assume there is at least phrase
			//for loop, used to loop to check each character.
			for (int i = 0; i < userInput.length(); i++) {
				//if the character is the notation we need to separate
				if (userInput.charAt(i) != '?' && userInput.charAt(i) != '!' 
					&& userInput.charAt(i) != ',' && userInput.charAt(i) != '.') {
					if (userInput.charAt(i) != ' ') {//if the character is not a space
						//if the character should be save
						if ((userInput.charAt(i) >= 'a' && userInput.charAt(i) <= 'z') 
							|| (userInput.charAt(i) == '\'') 
							|| (userInput.charAt(i) >= '0' && userInput.charAt(i) <= '9')) {
							phrase.set(count, phrase.get(count) 
							+ String.valueOf(userInput.charAt(i)));
						} else {//add the character into the phrase
							phrase.set(count, phrase.get(count) + " ");
						}//this character is some notation, save it as a space
					} else if (userInput.charAt(i) == ' ' && ((userInput.charAt(i - 1) >= 'a' 
							&& userInput.charAt(i - 1) <= 'z') || (userInput.charAt(i - 1) == '\'') 
							|| (userInput.charAt(i - 1) >= '0'  
							&& userInput.charAt(i - 1) <= '9'))) {
						phrase.set(count, phrase.get(count) + " ");
					}//it is the separate notation and it is not the first character
				} else if ((i != 0) && (userInput.charAt(i - 1) != '?' 
						&& userInput.charAt(i - 1) != '!' && userInput.charAt(i - 1) != ',' 
						&& userInput.charAt(i - 1) != '.')) {
					count = count + 1;
					phrase.add("");
				}
			}
			int count2 = 0;//count for the non empty phrase
			for (int i = 0; i < phrase.size(); i++) {
				if (phrase.get(i) != "") {
					count2++;
				}
			}
			//the ArrayList we got has some empty phrases. So, create another ArrayList phrase2
			//to store the data of phrase and remove the empty phrase
			ArrayList<String> phrase2 = new ArrayList<String>();
			for (int i = 0; i < count2; i++) {
				String phstr = phrase.get(i);
				phrase2.add(phstr);
			}
			//phrase2 is the ArrayList that we want
			return phrase2;
		}
	}

	/**
	 * Checks whether any of the phrases in the parameter match a quit word from
	 * Config.QUIT_WORDS. Note: complete phrases are matched, not individual words
	 * within a phrase.
	 * 
	 * @param phrases List of user phrases
	 * @return true if any phrase matches a quit word, otherwise false
	 */
	//in the separated phrase above,
	//we will check each phrase to see that if it contains a quit words.
	public static boolean foundQuitWord(ArrayList<String> phrases) {
		boolean find = false;//assume there is no quit word.
		for (int i = 0; i < phrases.size(); i++) {
			for (int j = 0; j < Config.QUIT_WORDS.length; j++) {
				if (phrases.get(i).equals(Config.QUIT_WORDS[j])) {
					find = true;//if find the quit word, set find to be true
				}
			}
		}
		return find;
	}

	/**
	 * Iterates through the phrases of the user's input, finding the longest phrase
	 * to which to respond. If two phrases are the same length, returns whichever
	 * has the lower index in the list. If phrases parameter is null or size 0 then
	 * return "" (Update 11/15/18).
	 * 
	 * @param phrases List of user phrases
	 * @return the selected phrase
	 */
	//in the separated phrases, this method will select a phrase that is longest.
	//This is because, the most important keywords are most likely in the longest phrase.
	//So, the selected longest phrase is useful for the following methods.
	public static String selectPhrase(ArrayList<String> phrases) {
		if (phrases == null || phrases.size() == 0) {//phrase is null or phrase is empty
			return "";
		} else if (phrases.size() == 1) {//if there is only one element
			return phrases.get(0);
		} else {//compare to find the longest phrase
			String longest = phrases.get(0);//assume the longest phrase is the first one
			for (int i = 1; i < phrases.size(); i++) {
				if (phrases.get(i).length() > longest.length()) {
					longest = phrases.get(i);//change the phrase to be the longer one
				}
			}
			return longest;
		}
	}

	/**
	 * Looks for a replacement word for the word parameter and if found, returns the
	 * replacement word. Otherwise if the word parameter is not found then the word
	 * parameter itself is returned. The wordMap parameter contains rows of match
	 * and replacement strings. On a row, the element at the 0 index is the word to
	 * match and if it matches return the string at index 1 in the same row. Some
	 * example word maps that will be passed in are Config.INPUT_WORD_MAP and
	 * Config.PRONOUN_MAP.
	 * 
	 * If word is null return null. If wordMap is null or wordMap length is 0 simply
	 * return word parameter. For this implementation it is reasonable to assume
	 * that if wordMap length is >= 1 then the number of elements in each row is at
	 * least 2.
	 * 
	 * @param word    The word to look for in the map
	 * @param wordMap The map of words to look in
	 * @return the replacement string if the word parameter is found in the wordMap
	 *         otherwise the word parameter itself.
	 */
	//This method will search for the 2-d array wordMap to see if the word can be replaced.
	//And if the word can be replaced, replace the word with the second element of the array.
	public static String replaceWord(String word, String[][] wordMap) {
		if (word == null) {//the word to be replaced is null
			return null;
		} else if (wordMap == null || wordMap.length == 0) {//the 2-d array is null or empty
			return word;
		} else {//change the word to be another one on the wordMap
			for (int i = 0; i < wordMap.length; i++) {
				if (word.equals(wordMap[i][0])) {
					return wordMap[i][1];//change the word to be the corresponding one
				}
			}
		}
		return word;
	}

	/**
	 * Concatenates the elements in words parameter into a string with a single
	 * space between each array element. Does not change any of the strings in the
	 * words array. There are no leading or trailing spaces in the returned string.
	 * 
	 * @param words a list of words
	 * @return a string containing all the words with a space between each.
	 */
	//Given am array of words, this method will assemble the words to be a appropriate sentence.
	public static String assemblePhrase(String[] words) {
		if (words == null) {//array of the word is null
			return null;
		}
		if (words.length == 0) {//the array of the word is empty.
			return "";
		}
		String asemble = words[0];//store the first element into a string
		if (words.length == 1) {//if the array has only one element, just return it as a sentence
			return asemble;
		}
		asemble = asemble + " ";//add the words together
		for (int i = 1; i < words.length; i++) {
			asemble = asemble + words[i] + " ";
		}
		return asemble.trim();//remove the useless space
	}

	/**
	 * Replaces words in phrase parameter if matching words are found in the mapWord
	 * parameter. A word at a time from phrase parameter is looked for in wordMap
	 * which may result in more than one word. For example: i'm => i am Uses the
	 * replaceWord and assemblePhrase methods. Example wordMaps are
	 * Config.PRONOUN_MAP and Config.INPUT_WORD_MAP. If wordMap is null then phrase
	 * parameter is returned. Note: there will Not be a case where a mapping will
	 * itself be a key to another entry. In other words, only one pass through
	 * swapWords will ever be necessary.
	 * 
	 * @param phrase  The given phrase which contains words to swap
	 * @param wordMap Pairs of corresponding match & replacement words
	 * @return The reassembled phrase
	 */
	//In a sentence 'phrase', swap the words that 
	//can be replaced in the replaceWords method.
	//then we will get a sentence with the replaced words
	public static String swapWords(String phrase, String[][] wordMap) {
		if (wordMap == null) {//if wordMap 2-d array is null
			return phrase;
		}
		//store the words into an array for check conveniently
		String[] arrayPhrase = phrase.split(" ");
		for (int i = 0; i < arrayPhrase.length; i++) {//check for each word
			arrayPhrase[i] = replaceWord(arrayPhrase[i], wordMap);
		}
		String newPhrase = assemblePhrase(arrayPhrase);//assemble the words to become a sentence
		return newPhrase;
	}

	/**
	 * This prepares the user input. First, it separates input into phrases (using
	 * separatePhrases). If a phrase is a quit word (foundQuitWord) then return
	 * null. Otherwise, select a phrase (selectPhrase), swap input words (swapWords
	 * with Config.INPUT_WORD_MAP) and return an array with each word its own
	 * element in the array.
	 * 
	 * @param input The input from the user
	 * @return words from the selected phrase
	 */
	//Given a user input, this method will separate it 
	//into ArrayList will the method separatePhrase.
	//Then check if there is a quit word.
	//if no quit word, select the longest phrase and replace 
	//the words than can be replaced.
	public static String[] prepareInput(String input) {
		ArrayList<String> phrases = new ArrayList<String>();
		phrases = separatePhrases(input);//separate the user input according to some notations
		if (foundQuitWord(phrases)) {
			return null;
		}
		//if no quit words, select the longest phrase of the separated ArrayList
		String longestPhrase = selectPhrase(phrases);
		//replace the words in the sentence
		longestPhrase = swapWords(longestPhrase, Config.INPUT_WORD_MAP);
		String[] arrayPhrase = longestPhrase.split(" ");//split the sentence into an array
		return arrayPhrase;
	}

	/**
     * Reads a file that contains keywords and responses. A line contains either a list of keywords
     * or response, any blank lines are ignored. All leading and trailing whitespace on a line
     * is ignored. A keyword line begins with "keywords" with all the following tokens on the line, 
     * the keywords. Each line that follows a keyword line that is not blank is a possible response
     * for the keywords. For example (the numbers are for our description purposes here and are not 
     * in the file): 
     * 
     *1  keywords computer
     *2  Do computers worry you?
     *3  Why do you mention computers?
     *4
     *5  keywords i dreamed
     *6  Really, <3>?
     *7  Have you ever fantasized <3> while you were awake?
     *8
     *9  Have you ever dreamed <3> before?
     *
     *   In line 1 is a single keyword "computer" followed by two possible responses on lines
     *   2 and 3. Line 4 and 8 are ignored since they are blank (contain only whitespace).
     *   Line 5 begins new keywords that are the words "i" and "dreamed".  This keyword list
     *   is followed by three possible responses on lines 6, 7 and 9.
     *   
     *   The keywords and associated responses are each stored in their own ArrayList. The
     *   response table is an ArrayList of the keyword and responses lists. For every keywords list
     *   there is an associated response list. They are added in pairs into the list
     *   that is returned.  There will always be an even number of items in the returned list.
     *   
     *   Note that in the event an IOException occurs when trying to read the file then
     *   an error message "Error reading <fileName>", where <fileName> is the parameter, 
     *   is printed and a non-null reference is returned, which may or may not have any elements
     *   in it.
     * 
     * @param fileName  The name of the file to read
     * @return  The response table
     */
	//This method will read the file and save the file as a 2-D ArrayList 'responseTable'
	//The ArrayList has even number with pairs of keywords list and response list.
	public static ArrayList<ArrayList<String>> loadResponseTable(String fileName) {
		ArrayList<String> key = new ArrayList<String>();//store the keywords into a list
		ArrayList<String> response = new ArrayList<String>();//store the response into a list
		//table is the 2-d ArrayList need to be retuern.
		ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
		File file = new File(fileName);
		Scanner input = null;//Assume the scanner is null, then try to read the file
		try {
			input = new Scanner(file);
			String line = null;
			while (input.hasNextLine()) {//if the file has not been read fully
				line = input.nextLine();//store that line into a string
				if (line.length() > 0) {//if that line is not empty
					if (line.split(" ")[0].equals("keywords")) {//if this is a keyword line
						if (response.size() > 0) {
							table.add(response);//store the response list into the 2-d ArrayList
							response = new ArrayList<String>();//renew the response list
						}
						if (line.length() > 9) {//if the keyword line has at least one keyword
							//store the keywords into array
							String[] keyLine = line.substring(9).split(" ");
							for (int i = 0; i < keyLine.length; i++) {
								key.add(keyLine[i]);//store all the keywords into the list key
							}
							table.add(key);//store the ArrayList key into 2-d ArrayList table
							key = new ArrayList<String>();//renew the key list
						} else {
							key.add("");//keyword line is empty
							table.add(key);//add the empty String into the table
							key = new ArrayList<String>();//renew the key list
							;
						}
					} else {
						response.add(line);//the while line is response
					}
				}

			}
			table.add(response);//add the last line of the response 
		} catch (IOException e) {//if fail to read the file
			System.out.println("Error reading " + fileName);
		} finally {
			if (input != null)
				input.close();
		}
		return table;
	}

	/**
	 * Checks to see if the keywords match the sentence. In other words, checks to
	 * see that all the words in the keyword list are in the sentence and in the
	 * same order. If all the keywords match then this method returns an array with
	 * the unmatched words before, between and after the keywords. If the keywords
	 * do not match then null is returned.
	 * 
	 * When the phrase contains elements before, between, and after the keywords,
	 * each set of the three is returned in its own element String[] keywords =
	 * {"i", "dreamed"}; String[] phrase = {"do", "you", "know", that", "i", "have",
	 * "dreamed", "of", "being", "an", "astronaut"};
	 * 
	 * toReturn[0] = "do you know that" toReturn[1] = "have" toReturn[2] = "of being
	 * an astronaut"
	 * 
	 * In an example where there is a single keyword, the resulting List's first
	 * element will be the the pre-sequence element and the second element will be
	 * everything after the keyword, in the phrase String[] keywords = {"always"};
	 * String[] phrase = {"I", "always", "knew"};
	 * 
	 * toReturn[0] = "I" toReturn[1] = "knew"
	 * 
	 * In an example where a keyword is not in the phrase in the correct order, null
	 * is returned. String[] keywords = {"computer"}; String[] phrase = {"My","dog",
	 * "is", "lost"};
	 * 
	 * return null
	 * 
	 * @param keywords The words to match, in order, in the sentence.
	 * @param phrase   Each word in the sentence.
	 * @return The unmatched words before, between and after the keywords or null if
	 *         the keywords are not all matched in order in the phrase.
	 */
	//This method will use the ArrayList of the loaded file and find if there is any keywords
	//in the prepared user input, which is the parameter 'String [] phrase'.
	public static String[] findKeyWordsInPhrase(ArrayList<String> keywords, String[] phrase) {
		if (keywords.size() == 0) {//if the keywords is empty
			return new String[] { assemblePhrase(phrase) };
		}
		int index = 0;//the index of the keywords ArrayList
		int unmatchedSize = keywords.size() + 1;//the unmatched array to be returned
		String[] unmatched = new String[unmatchedSize];
		for (int i = 0; i < unmatched.length; i++) {
			unmatched[i] = "";
		}
		for (int i = 0; i < phrase.length; i++) {//iterate for the phrase to search for key words.
			if (index < keywords.size() && keywords.get(index).equals(phrase[i])) {
				index += 1;//if find the key word, then index update to next one
			} else {
				unmatched[index] += phrase[i] + " ";
			}//if it is not a key word, store it into unmatched
		}
		if (index != keywords.size())//this means there are some disorder keywords in the phrase
			return null;
		for (int i = 0; i < unmatched.length; i++) {
			unmatched[i] = unmatched[i].trim();//remove the useless space
		}
		return unmatched;
	}


	/**
	 * Selects a randomly generated response within the list of possible responses
	 * using the provided random number generator where the number generated
	 * corresponds to the index of the selected response. Use Random nextInt(
	 * responseList.size()) to generate the random number. If responseList is null
	 * or 0 length then return null.
	 * 
	 * @param rand         A random number generator.
	 * @param responseList A list of responses to choose from.
	 * @return A randomly selected response
	 */
	//This method can generate a random number which is used 
	//to the response of that response ArrayList
	public static String selectResponse(Random rand, ArrayList<String> responseList) {
		if (responseList == null || responseList.size() == 0) {
			return null;
		}
		int index = rand.nextInt(responseList.size());//the index of the response list is random.
		String response = responseList.get(index);//use random number to select a response
		return response;
	}

	/**
	 * This method takes processed user input and forms a response. This looks
	 * through the response table in order checking to see if each keyword pattern
	 * matches the userWords. The first matching keyword pattern found determines
	 * the list of responses to choose from. A keyword pattern matches the
	 * userWords, if all the keywords are found, in order, but not necessarily
	 * contiguous. This keyword matching is done by findKeyWordsInPhrase method. See
	 * the findKeyWordsInPhrase algorithm in the Eliza.pdf.
	 * 
	 * If no keyword pattern matches then Config.NO_MATCH_RESPONSE is returned.
	 * Otherwise one of possible responses for the matched keywords is selected with
	 * selectResponse method. The response selected is checked for the replacement
	 * symbol <n> where n is 1 to the length of unmatchedWords array returned by
	 * findKeyWordsInPhrase. For each replacement symbol the corresponding unmatched
	 * words element (index 0 for <1>, 1 for <2> etc.) has its pronouns swapped with
	 * swapWords using Config.PRONOUN_MAP and then replaces the replacement symbol
	 * in the response.
	 * 
	 * @param userWords     using input after preparing.
	 * @param rand          A random number generator.
	 * @param responseTable A table containing a list of keywords and response
	 *                      pairs.
	 * @return The generated response
	 */
	//This method can return a prepared response to the user.
	//Firstly, use the findKeyWords method to find the corresponding response list.
	//Then, check if there are some <n> notations.
	//If there are, then replace it with the (n - 1)th element of the unmatched array.
	//Then return the response.
	public static String prepareResponse(String[] userWords, Random rand,
			ArrayList<ArrayList<String>> responseTable) {
		// Iterate through the response table.
		// The response table has paired rows. The first row is a list of key
		// words, the next a list of corresponding responses. The 3rd row another
		// list of keywords and 4th row the corresponding responses.

		// checks to see if the current keywords match the user's words
		// using findKeyWordsInPhrase.

		// if no keyword pattern was matched, return Config.NO_MATCH_RESPONSE
		// else, select a response using the appropriate list of responses for the
		// keywords

		// Look for <1>, <2> etc in the chosen response. The number starts with 1 and
		// there won't be more than the number of elements in unmatchedWords returned by
		// findKeyWordsInPhrase. Note the number of elements in unmatchedWords will be
		// 1 more than the number of keywords.
		// For each <n> found, find the corresponding unmatchedWords phrase (n-1) and
		// swap
		// its pronoun words (swapWords using Config.PRONOUN_MAP). Then use the
		// result to replace the <n> in the chosen response.

		// in the selected echo, swap pronouns

		// inserts the new phrase with pronouns swapped, into the response
		//System.out.println(userWords[0] + " " + userWords[1] + " " + userWords[2]);
		
		int replacedNumber;// for a <n>. this is the 'n', which to be replaced.
		boolean findKeywords = false;// assume we do not find the keywords
		String replacedStr;// this is the string form of <n>, which will be replaced.
		String selectedResponse;// choose a response from the response list to replace <n>
		String[] unmatched = null;// returned by the method findKeyWordsInPhrase
		ArrayList<String> keywords = null;// the line with keywords of the loaded 2-d ArrayList
		// the line with response list of the loaded 2-d ArrayList
		ArrayList<String> responseList = new ArrayList<String>();
		// for each keyword line of the table
		for (int i = 0; i < responseTable.size() - 1; i = i + 2) {
			keywords = responseTable.get(i);
			unmatched = findKeyWordsInPhrase(keywords, userWords);
			// iterate to search the matched keyword, if find, it is nut null
			if (unmatched != null) {
				findKeywords = true;
				responseList = responseTable.get(i + 1);
				break;
			}
		}//if no keyword found
		if (findKeywords == false) {
			return Config.NO_MATCH_RESPONSE;
		} else {//if find the keyword, select the response from the corresponding response list
			selectedResponse = selectResponse(rand, responseList);
		}
		boolean hasNumber = true;//assume there exists <n> to enter the loop
		while (hasNumber) {
			hasNumber = false;//set to non <n> notation
			if (selectedResponse.contains("<")) {//if the response has <n>
				hasNumber = true;
				//find the number of the 'n;
				replacedNumber = 
						Integer.valueOf(selectedResponse.substring(selectedResponse.indexOf("<") 
								+ 1, selectedResponse.indexOf("<") + 2));
				//find the <n> with the number of n used for replacing later
				replacedStr = selectedResponse.substring(selectedResponse.indexOf("<"),
						selectedResponse.indexOf("<") + 3);
				//if there are some pronoun, then replace it
				unmatched[replacedNumber - 1] = swapWords(unmatched[replacedNumber - 1],
						Config.PRONOUN_MAP);
				//replace <n> with appropriate string
				selectedResponse = selectedResponse.replaceAll(replacedStr, 
						unmatched[replacedNumber - 1]);
			}

		}

		return selectedResponse;
	}

	/**
	 * Creates a file with the given name, and fills that file line-by-line with the
	 * tracked conversation. Every line ends with a newline. Throws an IOException
	 * if a writing error occurs.
	 * 
	 * @param dialog   the complete conversation
	 * @param fileName The file in which to write the conversation
	 * @throws IOException
	 */
	//This method can write all of the conversation to a file whose name is given by user.
	//If there are some error when saving dialog into a file, then an IOException is throw.
	public static void saveDialog(ArrayList<String> dialog, String fileName) throws IOException {
		File file = new File(fileName);
		PrintWriter writer = null;//Assume the writer is null. then try to write it
		writer = new PrintWriter(file);
		for (int i = 0; i < dialog.size(); i++) {
			writer.println(dialog.get(i));//write the conversation into file
		}
		if (writer != null) {//if write successfully, then close it finally
			writer.close();
		}
	}
}
