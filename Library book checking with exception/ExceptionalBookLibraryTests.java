//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (ExceptionalBookLibraryTests)
// Files:           (ExceptionalBookLibraryTests.java)
// Course:          (cs300 , 2019 Spring,)
//
// Author:          (Yijun Cheng)
// Email:           (cheng229@wisc.edu )
// Lecturer's Name: (Gary Dahl)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (yuedong cui)
// Partner Email:   ( cui54@wisc.edu)
// Partner Lecturer's Name: (Gary Dahl)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   X Write-up states that pair programming is allowed for this assignment.
//   X We have both read and understand the course Pair Programming Policy.
//   X We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.text.ParseException;

/**
 * this is a test class to test some method in the ExceptionalLibrary class
 * 
 * @author yijun cheng && yuedong cui
 *
 */
public class ExceptionalBookLibraryTests {

  /**
   * test if there are one more subscriber than the max number required, if there will a exception
   * throw
   * 
   * @return
   */
  public static boolean testCreateOverNumberSubscriber() {
    boolean testPass = false;// initialize the test to false
    try {
      for (int i = 0; i < 999999; i++) {// add 999999 subscriber, which is one more than the max
                                        // number
        Subscriber subscriber = new Subscriber("yijun", 1111, "huainan", "7203299699");
      }
    } catch (InstantiationException e) {// if the expected exception is catched
      testPass = true;// test passed
    }
    return testPass;
  }

  /**
   * test ParseCardBarCode. if the card bar code is not a number format
   * 
   * @return false is no exception catched. true if the numberformatException exception catched
   */
  public static boolean testLibraryParseCardBarCode() {
    boolean test = false;// initialize the test to false
    ExceptionalLibrary library = new ExceptionalLibrary("China", "yuedong", "1234567890");
    try {
      // try to parse the card bar code
      int cardBarCode = library.parseCardBarCode("123456789a", 1);
    } catch (ParseException e) {
      test = true;// test passed
    }
    return test;
  }

  /**
   * test the method ParsePinCode. if the pin number is not 4 digit, exception should be throwed
   * 
   * @return false is no exception catched. true if the parseException exception catched
   */
  public static boolean testLibraryParsePinCode() {
    boolean test = false;// initialize the test to false
    ExceptionalLibrary library = new ExceptionalLibrary("China", "yuedong", "1234567890");
    try {
      int pin = library.parsePinCode("111", 1);// try to parse the pin
    } catch (ParseException e) {
      test = true;// test passed
    }
    return test;
  }

  /**
   * test ParseBookId() method. if the book id is not a number format, exception should be throwed
   * 
   * @return false is no exception catched. true if the numberformatException exception catched
   */
  public static boolean testLibraryParseBookId() {
    boolean test = false;// initialize the test to false
    ExceptionalLibrary library = new ExceptionalLibrary("China", "yuedong", "1234567890");
    try {
      int bookId = library.parseBookId("a", 1);// try to parse the bookId
    } catch (ParseException e) {
      test = true;// test passed
    }
    return test;
  }

  /**
   * test LibraryParseRunLibrarianCheckoutBookCommand. if the user check out a book with a wrong
   * format of card bar code, exception should be throw
   * 
   * @return false is no exception catched. true if the numberformatException exception catched
   */
  public static boolean testLibraryParseRunLibrarianCheckoutBookCommand() {
    boolean test = false;// initialize the test to false
    ExceptionalLibrary library = new ExceptionalLibrary("China", "yuedong", "1234567890");
    String[] commands = {"3", "123456789a", "1"};
    try {
      library.parseRunLibrarianCheckoutBookCommand(commands);// try to let a user with wrong
                                                             // bar code check out the book
    } catch (ParseException e) {
      test = true;// test passed
    }
    return test;
  }

  /**
   * test LibraryParseRunSubscriberReturnBookCommand, if the user who return the book with a wrong
   * card bar code, a exception should be throw
   * 
   * @return false is no exception catched. true if the numberformatException exception catched
   */
  public static boolean testLibraryParseRunSubscriberReturnBookCommand() {
    boolean test = false;// initialize the test to false
    ExceptionalLibrary library = new ExceptionalLibrary("China", "yuedong", "1234567890");
    String[] commands = {"4", "123456789", "1"};
    try {// try to let a user with wrong bar code check out the book
      library.parseRunLibrarianCheckoutBookCommand(commands);
    } catch (ParseException e) {
      test = true;
    }
    return test;// test passed
  }

  public static void main(String[] args) {
    System.out.println("testCreateOverNumberSubscriber: " + testCreateOverNumberSubscriber());
    System.out.println("testLibraryParseCardBarCode: " + testLibraryParseCardBarCode());
    System.out.println("testLibraryParsePinCode: " + testLibraryParsePinCode());
    System.out.println("testLibraryParseBookId: " + testLibraryParseBookId());
    System.out.println("testLibraryParseRunLibrarianCheckoutBookCommand: "
        + testLibraryParseRunLibrarianCheckoutBookCommand());
    System.out.println("testLibraryParseRunSubscriberReturnBookCommand: "
        + testLibraryParseRunSubscriberReturnBookCommand());
  }

}

