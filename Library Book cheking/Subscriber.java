//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (ShorppingCart)
// Files:           (ShorppingCart.java)
// Course:          (cs300 , 2019 Spring,)
//
// Author:          (Yijun Cheng)
// Email:           (cheng229@wisc.edu )
// Lecturer's Name: (Gary Dahl)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (Yuedong Cui)
// Partner Email:   ( cui54@wisc.edu)
// Partner Lecturer's Name: (Gary Dahl)
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
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.ArrayList;

/**
 * a subscriber class store the information of the subscriber
 * 
 * @author yijuncheng and yuedongcui
 *
 */
public class Subscriber {
  // static fields
  private final static int MAX_BOOKS_CHECKED_OUT = 10; // maximum number of books to be checked out
                                                       // one subscriber
  private static int nextCardBarCode = 2019000001; // class variable that represents the card bar
                                                   // code of the next subscriber to be created
  // Instance fields
  private int pin; // 4-digits Personal Identification Number to verify the identity of this
                   // subscriber
  private final Integer CARD_BAR_CODE; // card bar code of this subscriber

  private String name; // name of this subscriber
  private String address; // address of this subscriber
  private String phoneNumber; // phone number of this subscriber

  private ArrayList<Book> booksCheckedOut = new ArrayList<>(); // list of books checked out by this
                                                               // subscriber and not
  // yet
  // returned. A subscriber can have at most 10 checked out
  // books
  private ArrayList<Book> booksReturned = new ArrayList<>(); // list of the books returned by this
                                                             // subscriber

  /**
   * Construct of the subscriber class
   * 
   * @param name        name of the subscriber
   * @param pin         of the subscriber
   * @param address     of the subscriber
   * @param phoneNumber of the subscriber
   */
  public Subscriber(String name, int pin, String address, String phoneNumber) {
    // initialize the variable in the field
    this.name = name;
    this.address = address;
    this.pin = pin;
    this.phoneNumber = phoneNumber;
    CARD_BAR_CODE = nextCardBarCode;// update the CARD_BAR_CODE
    nextCardBarCode++;
  }

  /**
   * getter method
   * 
   * @return address of the subscriber
   */
  public String getAddress() {
    return address;
  }

  /**
   * 
   * @param address of the subscriber
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * getter method
   * 
   * @return phone number of the subscriber
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * setter method
   * 
   * @param update the phoneNumber of the subscriber
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * getter method
   * 
   * @return pin of the subscriber
   */
  public int getPin() {
    return pin;
  }

  /**
   * getter method
   * 
   * @return cardbarcode of the subscriber
   */
  public Integer getCARD_BAR_CODE() {
    return CARD_BAR_CODE;
  }

  /**
   * getter method
   * 
   * @return name of the subscriber
   */
  public String getName() {
    return name;
  }

  /**
   * check out the book. Need to check if the book is available to check out for this subscriber
   * 
   * @param book The book to be checked
   */
  public void checkoutBook(Book book) {
    if (booksCheckedOut.size() == MAX_BOOKS_CHECKED_OUT) {// if the subscriber has already borrow
                                                          // max number of book
      System.out.println(
          "Checkout Failed: You cannot check out more than " + MAX_BOOKS_CHECKED_OUT + " books.");
    }
    if (isBookInBooksCheckedOut(book)) {// if this subscriber has already borrowed this book
      System.out.println("You have already checked out " + book.getTitle() + " book.");
    }
    if (book.isAvailable() == true) {// if this book can be borrowed
      book.borrowBook(CARD_BAR_CODE);// update the card bar code of the subscriber on the book
      booksCheckedOut.add(book);// add this book to the checked list
    } else {// if the book is not available
      System.out.println("Sorry, " + book.getTitle() + " is not available.");
    }
  }

  /**
   * a method used for subscriber to return the book
   * 
   * @param book the book object of the book to be returned
   */
  public void returnBook(Book book) {
    booksReturned.add(book);// add the book to the return list
    booksCheckedOut.remove(book);// remove the book from the checked list
    book.returnBook();// set card bar code to null. so that the book is available now
  }

  /**
   * Checks if this subscriber booksCheckedOut list contains a given book
   * 
   * @param book book to check if it is within this subscriber booksCheckedOut list
   * @return true if booksCheckedOut contains book, false otherwise
   */
  public boolean isBookInBooksCheckedOut(Book book) {
    return booksCheckedOut.contains(book);
  }

  public boolean isBookInBooksReturned(Book book) {
    return booksReturned.contains(book);
  }

  /**
   * Displays the list of the books checked out and not yet returned
   */
  public void displayBooksCheckedOut() {
    if (booksCheckedOut.isEmpty()) // empty list
      System.out.println("No books checked out by this subscriber");
    else
      // Traverse the list of books checked out by this subscriber and display its content
      for (int i = 0; i < booksCheckedOut.size(); i++) {
        System.out.print("Book ID: " + booksCheckedOut.get(i).getID() + " ");
        System.out.print("Title: " + booksCheckedOut.get(i).getTitle() + " ");
        System.out.println("Author: " + booksCheckedOut.get(i).getAuthor());
      }
  }

  /**
   * Displays the history of the returned books by this subscriber
   */
  public void displayHistoryBooksReturned() {
    if (booksReturned.isEmpty()) // empty list
      System.out.println("No books returned by this subscriber");
    else
      // Traverse the list of borrowed books already returned by this subscriber and display its
      // content
      for (int i = 0; i < booksReturned.size(); i++) {
        System.out.print("Book ID: " + booksReturned.get(i).getID() + " ");
        System.out.print("Title: " + booksReturned.get(i).getTitle() + " ");
        System.out.println("Author: " + booksReturned.get(i).getAuthor());
      }
  }

  /**
   * Displays this subscriber's personal information
   */
  public void displayPersonalInfo() {
    System.out.println("Personal information of the subscriber: " + CARD_BAR_CODE);
    System.out.println("  Name: " + name);
    System.out.println("  Address: " + address);
    System.out.println("  Phone number: " + phoneNumber);
  }
}
