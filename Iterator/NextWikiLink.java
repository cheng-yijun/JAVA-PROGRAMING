//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (NextWikiLink)
// Files:           (NextWikiLink.java)
// Course:          (cs300 , 2019 Spring)
//
// Author:          (Yijun Cheng)
// Email:           (cheng229@wisc.edu )
// Lecturer's Name: (Gary Dahl)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (NONE)
// Partner Email:   (NONE)
// Partner Lecturer's Name: (NONE)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (NONE)
// Online Sources:  (NONE)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.Function;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
 
/**
 * The NextWikiLink class to generate next web page by using iterator
 * @author yijuncheng
 *
 */
public class NextWikiLink implements Function<String, String> {
  @Override
  public String apply(String t) {
    try {
      // Download a Wikipedia page, using t in their internal link format: /wiki/Some_Subject
      Document doc = Jsoup.connect("https://en.wikipedia.org" + t).get();
      // Use .css selector to retrieve a collection of links from this page's description
      // "p a" selects links within paragraphs
      // ":not([title^='Help'])" skips pronunciations
      // ":not(sup a)" skips citations
      Elements links = doc.select("p a:not([title^='Help']):not(sup a)");
      // return the link attribute from the first element of this list
      return links.get(0).attr("href");
      // Otherwise return an appropriate error message:
    } catch (IOException | IllegalArgumentException e) {
      return "FAILED to find wikipedia page: " + t;
    } catch (IndexOutOfBoundsException e) {
      return "FAILED to find a link in wikipedia page: " + t;
    }
  }
 
  /**
   * A main method used to create a user interface to iterate the Wiki web page
   * @param args String type
   */
  public static void main(String[] args) {
    // Implement your own Wikipedia crawling application here.
    // 1. prompt the user to enter a topic name and number of iterations to follow
    // 2. prepend "/wiki/" to the user's input, and replace spaces with underscores 
    // 3. use a for-each loop to iterate through the number of links requested
    Scanner scan = new Scanner(System.in);// create a Scanner
    String userInput;//store user input
    int length;//length of this iterator
    System.out.print("Enter a wikipedia page topic: ");
    userInput = scan.nextLine();
    System.out.print("Enter the number of pages you'd like to step through: ");
    length = scan.nextInt();
    userInput = userInput.trim();
    userInput = userInput.replace(' ', '_');
    userInput = "/wiki/" + userInput;
    //create a new generator
    Generator<String> generator = new Generator<String>(userInput, new NextWikiLink(),length);
    for(String webPage : generator) {//loop to print next web page
        System.out.println(webPage);
    }
    scan.close();//close the scanner.
  }
}