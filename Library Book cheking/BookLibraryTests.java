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

public class BookLibraryTests {
  /**
   * method checks whether the constructor of your Book class initializes correctly the new Book
   *  instance fields: title, author, borrowerCardBarCode, ID, and increments nextID static field.
   * 
   * @return true if test passed, false otherwise.
   */
  public static boolean testBookConstructorGetters() {
    boolean testpass = true;//initialize the test pass to true
    Book book = new Book("java", "yijun");//create the new object
    if (!book.getTitle().equals("java")){//if the title of the book is not "java"
      System.out.println("Problem detected: wrong title");
      testpass = false;//test pass become false
    }
    if(!book.getAuthor().equals("yijun")) {
      System.out.println("Problem detected: wrong author");//if the author is not "yijun"
      testpass = false;//test pass become false
    }
    if(book.getID() != 1) {
      System.out.println("Problem detected: wrong id");//if the id is not "1"
      testpass = false;//test pass become false
    }
    if(!book.isAvailable()) {
      System.out.println("Problem detected: unavailable book");//if the book is unavailable
      testpass = false;//test pass become false
    }
    Book bookTwo = new Book("python","yuedong");//create second object with different title & author
    if (bookTwo.getID() != 2) {//book id should be 2
      System.out.println("Problem detected: fail to update nextId");
      testpass = false;//test pass become false
    }
    return testpass;
  }
  
  
  /**
   * check if a book which is checked out is available now.
   * @return test result
   */
  public static boolean testBookIsAvailableAfterCheckout() {
    boolean testpass = true;//initialize the test pass to true
    Book book = new Book("java", "yijun");//create the new object
    book.borrowBook(201900001);//borrow the book first.
    if(book.isAvailable()) {
      System.out.println("Error, book should be unavailabel now");
      testpass = false;
    }
    return testpass;
  }
 
  
  /**
   * checks whether returnBook() method defined within your Book class sets correctly 
   * the instance field borrowerCardBarCode.
   * 
   * @return true if test passed, false otherwise.
   */
  public static boolean testBookReturnBook() {
    boolean testpass = true;//initialize the test pass to true
    Book book = new Book("java", "yijun");//create new object;
    book.borrowBook(201900001);//borrow the book first.
    book.returnBook();//then, return the book to see some changes
    if(book.getBorrowerCardBarCode() != null) {//if the book now is unavailable, test fails.
      System.out.println("book should be available now");
      testpass = false;
    }
    return testpass;
  }
  
  /**
   * this method used to check the method checkoutBook(book). If a book has already been 
   * ckecked, it is unavailable, so it cannot be checked again.
   * 
   * @return test result
   */
  public static boolean testCheckedOutBook() {
    boolean testpass = true;//initialize the test pass to true
    Book book = new Book("java", "yijun");//create the new object
    book.borrowBook(201900001);//borrow the book first.
    Subscriber yijun = new Subscriber("yijun", 1111, "China", "110"); // a new subscriber
    yijun.checkoutBook(book);//let yijun borrow this book again
    if(yijun.isBookInBooksCheckedOut(book)) {//if he borrows this book successfully.
      System.out.println("Error: this book canner be checked out.");
      testpass = false;
    }
    return testpass;
   }
   
  /**
   * This unit test method checks whether the checkoutBook() method defined within the
   * Subscriber class works correctly.
   * @return test result
   */
  public static boolean testSubscriberCheckoutBook() {
   boolean testpass = true; // boolean variable
   ArrayList<Book> booksCheckedOut = new ArrayList<>(); // a new arrayList to store book
   //create some new objects.
   Book book1 = new Book("java1", "Yuedong");
   Book book2 = new Book("java2", "Yuedong");
   Book book3 = new Book("java3", "Yuedong");
   Book book4 = new Book("java4", "Yuedong");
   Book book5 = new Book("java5", "Yuedong");
   Book book6 = new Book("java6", "Yuedong");
   Book book7 = new Book("java7", "Yuedong");
   Book book8 = new Book("java8", "Yuedong");
   Book book9 = new Book("java9", "Yuedong");
   Book book10 = new Book("java10", "Yuedong");
   Book book11 = new Book("java11", "Yuedong"); // 11 new books
   Subscriber Yuedong = new Subscriber("Yuedong", 1111, "China", "110"); // a new subscriber
   //check these books 
   Yuedong.checkoutBook(book1);
   Yuedong.checkoutBook(book2);
   Yuedong.checkoutBook(book3);
   Yuedong.checkoutBook(book4);
   Yuedong.checkoutBook(book5);
   Yuedong.checkoutBook(book6);
   Yuedong.checkoutBook(book7);
   Yuedong.checkoutBook(book8);
   Yuedong.checkoutBook(book9);
   Yuedong.checkoutBook(book10);
   Yuedong.checkoutBook(book11); // checkout 11 books
   // Make sure that the subscriber cannot have more than MAX_BOOKS_CHECKED_OUT books
   if (booksCheckedOut.size() == 11) {
    testpass = false;
   }
   // Make sure that the checked out book is added to the list 
   if (!Yuedong.isBookInBooksCheckedOut(book1) || !Yuedong.isBookInBooksCheckedOut(book2)
     || !Yuedong.isBookInBooksCheckedOut(book3)
     || !Yuedong.isBookInBooksCheckedOut(book4)
     || !Yuedong.isBookInBooksCheckedOut(book5)
     || !Yuedong.isBookInBooksCheckedOut(book6)
     || !Yuedong.isBookInBooksCheckedOut(book7)
     || !Yuedong.isBookInBooksCheckedOut(book8)
     || !Yuedong.isBookInBooksCheckedOut(book9)
     || !Yuedong.isBookInBooksCheckedOut(book10)) {
    testpass = false;
   }
   // Make sure that the books are not available after being checked out
   if (book1.isAvailable() || book2.isAvailable() || book3.isAvailable()
     || book4.isAvailable() || book5.isAvailable() || book6.isAvailable()
     || book7.isAvailable()|| book8.isAvailable() || book9.isAvailable()
     || book10.isAvailable()) {
    testpass = false;
   }
   // Make sure that the book’s borrowerCardBarCode is correctly set
   if (book1.getBorrowerCardBarCode() == null || book2.getBorrowerCardBarCode() == null
     || book3.getBorrowerCardBarCode() == null
     || book4.getBorrowerCardBarCode() == null
     || book5.getBorrowerCardBarCode() == null
     || book6.getBorrowerCardBarCode() == null
     || book7.getBorrowerCardBarCode() == null
     || book8.getBorrowerCardBarCode() == null
     || book9.getBorrowerCardBarCode() == null
     || book10.getBorrowerCardBarCode() == null) {
    testpass = false;
   }
   return testpass;
  }
  
  /**
   * checks the good functioning of findBookByAuthor method 
   * @return test result
   */
  public static boolean testLibraryFindBookByAuthor() {
   boolean testpass = true; // boolean variable
   Library YuedongLibrary = new Library("China", "Yuedong", "0729"); // a new library
   ArrayList<Book> bookList = new ArrayList<>(); // a new arrayList for library
   YuedongLibrary.addBook("java1", "Yuedong");
   YuedongLibrary.addBook("java2", "Yuedong"); // add two books to library
   bookList = YuedongLibrary.findBookByAuthor("Yuedong"); // find books by author
   // Make sure that findBookByAuthor method is correct
   if (bookList.size() != 2) {
    testpass = false;
   }
   return testpass;
  }
  
  /**
   * test if the setter can change the address correctly.
   * 
   * @return test result
   */
  public static boolean testUpdateAddress() {
    boolean testpass = true; // boolean variable
    //create a new subscriber object
    Subscriber subscriber = new Subscriber( "yijun",  1111, "colorado", "7203299999");
    subscriber.setAddress("Madison");//change the address
    if(!subscriber.getAddress().equals("Madison")) {//if the address is not changed
      System.out.print("Error, Address not changed ");
      testpass = false;
    }
    return testpass;
  }
  
  
  public static void main(String[] args) {
   System.out.println("testBookConstructorGetters() " + testBookConstructorGetters());
   System.out.println("testBookIsAvailableAfterCheckout() " + testBookIsAvailableAfterCheckout());
   System.out.println("testBookReturnBook() " + testBookReturnBook());
   System.out.println("testCheckedOutBook()" + testCheckedOutBook());
   System.out.println("testSubscriberCheckoutBook() " + testSubscriberCheckoutBook());
   System.out.println("testLibraryFindBookByAuthor " + testLibraryFindBookByAuthor());
   System.out.println("testUpdateAddress() " + testUpdateAddress()); 
  }
  
 
  
}
