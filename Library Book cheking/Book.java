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
import java.util.Scanner;

/**
 * a book class store the information of the book
 * @author yijuncheng and yuedong cui
 *
 */
public class Book {
  // class/static fields
  private static int nextId = 1; // class variable that represents the identifier of the next
                                 // book
  // Instance fields
  private final int ID; // unique identifier of this book
  private String author; // name of the author of this book
  private String title; // title of this book
  private Integer borrowerCardBarCode; // card bar code of the borrower of this book
                                       // When borrowerCardBarCode == null, the book is available
                                       // (no one has the book)

  /**
   * a construct of Book class
   * 
   * @param title  the title of the book
   * @param author the author of the book
   */
  public Book(String title, String author) {
    this.title = title;// initialize the title in the field
    this.author = author;// initialize the author in the field
    // initialize the borrowerCardBarCode in the field to be available
    this.borrowerCardBarCode = null;
    ID = nextId;// initialize the ID in the field to be available
    nextId++;// update the nextId
  }

  /**
   * update the borrowerCardBarCode of the book's subscriber
   * 
   * @param borrowerCardBarCode the borrowerCardBarCode of the book's subscriber
   */
  public void borrowBook(Integer borrowerCardBarCode) {
    this.borrowerCardBarCode = borrowerCardBarCode;// update the borrowerCardBarCode
  }

  /**
   * getter method
   * 
   * @return author of the book
   */
  public String getAuthor() {
    return author;
  }

  /**
   * getter method
   * 
   * @return title of the book
   */
  public String getTitle() {
    return title;
  }

  /**
   * getter method
   * 
   * @return borrowerCardBarCode of the book
   */
  public Integer getBorrowerCardBarCode() {
    return borrowerCardBarCode;
  }

  /**
   * getter method
   * 
   * @return id of the book
   */
  public int getID() {
    return ID;
  }

  /**
   * set the book to be available. It acts like returning the book
   */
  public void returnBook() {
    borrowerCardBarCode = null;// set the borrowerCardBarCode so that the book is available
  }

  /**
   * check if the book is available
   * 
   * @return true if the book is available. False otherwise.
   */
  public boolean isAvailable() {
    if (borrowerCardBarCode == null) {// if the borrowerCardBarCode is null, the book is available
      return true;
    } else {// otherwise, the book is not available.
      return false;
    }
  }
}
