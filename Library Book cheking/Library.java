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
import java.util.Scanner;

/**
 * library class store the information of the library and can run the application
 * 
 * @author yijuncheng and yuedong cui
 *
 */
public class Library {
  // instance fields
  private String address; // Street address of this library
  private Librarian librarian; // this library's librarian. This library must have only ONE
                               // librarian
  private ArrayList<Book> books = new ArrayList<>(); // list of the books in this library
  private ArrayList<Subscriber> subscribers = new ArrayList<>(); // list of this library's
                                                                 // subscribers

  /**
   * construct of the Library class.
   * 
   * @param address           of the subscriber
   * @param librarianUsername name of the librarian
   * @param librarianLogin    password of the librarian
   */
  public Library(String address, String librarianUsername, String librarianLogin) {
    this.address = address;
    this.librarian = new Librarian(librarianUsername, librarianLogin);
  }

  /**
   * getter method
   * 
   * @return the librarian object
   */
  public Librarian getLibrarian() {
    return librarian;
  }

  /**
   * getter method
   * 
   * @return address of the subscriber
   */
  public String getAddress() {
    return address;
  }

  public Book findBook(int bookId) {// find specific book according to the book id
    for (int i = 0; i < books.size(); i++) {
      if (books.get(i).getID() == bookId) {// find the correspond id
        return books.get(i);// get the book object
      }
    } // do not find the book
    System.out.println("Error: this book identifier didn't match any of our books identifiers.");
    return null;
  }

  /**
   * this method is used to find all of the book in the library that has the specific title
   * 
   * @param title of the book
   * @return
   */
  public ArrayList<Book> findBookByTitle(String title) {
    ArrayList<Book> bookTitle = new ArrayList<>();// create a new book Array list
    for (int i = 0; i < books.size(); i++) {// find the book with the title
      if (books.get(i).getTitle().equals(title)) {
        bookTitle.add(books.get(i));
      }
    }
    return bookTitle;
  }

  /**
   * this method is used to find all of the book in the library that has the specific author
   * 
   * @param author of the book
   * @return
   */
  public ArrayList<Book> findBookByAuthor(String author) {
    ArrayList<Book> bookAuthor = new ArrayList<>();// create a new book Array list
    for (int i = 0; i < books.size(); i++) {// find the book with the author
      if (books.get(i).getAuthor().equals(author)) {
        bookAuthor.add(books.get(i));
      }
    }
    return bookAuthor;
  }

  /**
   * this is a method to add new book into the library.
   * 
   * @param title  of the book
   * @param author of the book
   */
  public void addBook(String title, String author) {
    Book newBook = new Book(title, author);// create a new book Array list
    books.add(newBook);// add this book into library
    // print out message for librarian.
    System.out.println("Book with Title " + title + " is successfully added to the library.");
  }

  /**
   * remove a book from this library
   * 
   * @param bookId id of this book
   * @return
   */
  public Book removeBook(int bookId) {
    Book bookRemove = null;// initialize the book object to null
    for (int i = 0; i < books.size(); i++) {// try to find the book
      if (books.get(i).getID() == bookId) {// if find the book
        bookRemove = books.get(i);
        books.remove(books.get(i));
      }
    }
    if (bookRemove == null || !bookRemove.isAvailable()) {// if the book doesn't exist or
                                                          // unavailable
      return null;
    }
    return bookRemove;
  }

  /**
   * a method to add a librarian for this library
   * 
   * @param name        of the librarian
   * @param pin         of the librarian
   * @param address     of the librarian
   * @param phoneNumber of the librarian
   */
  public void addSubscriber(String name, int pin, String address, String phoneNumber) {
    // create a new librarian object
    Subscriber newSubscriber = new Subscriber(name, pin, address, phoneNumber);
    subscribers.add(newSubscriber);// add it into library class
    System.out.println("Library card with bar code " + newSubscriber.getCARD_BAR_CODE()
        + " is successfully issued to the new subscriber " + name + ".");
  }

  /**
   * find a subscriber according to her card bar code
   * 
   * @param cardBarCode of that subscriber.
   * @return
   */
  public Subscriber findSubscriber(int cardBarCode) {
    Subscriber subscriberFind = null;// create a new subscriber object
    for (int i = 0; i < subscribers.size(); i++) {// find her using card bar code
      if (subscribers.get(i).getCARD_BAR_CODE() == cardBarCode) {
        subscriberFind = subscribers.get(i);
      }
    }
    if (subscriberFind == null) {// if fail to find the subscriber
      System.out.println("Error: this card bar code didn't match any of our records.");
      return null;
    }
    return subscriberFind;
  }

  /**
   * Displays a list of books
   * 
   * @param books ArrayList of books
   */
  public static void displayBooks(ArrayList<Book> books) {
    // Traverse the list of books and display book id, title, author, and availability of each book
    for (int i = 0; i < books.size(); i++) {
      System.out.print("<Book ID>: " + books.get(i).getID() + " ");
      System.out.print("<Title>: " + books.get(i).getTitle() + " ");
      System.out.print("<Author>: " + books.get(i).getAuthor() + " ");
      System.out.println("<Is Available>: " + books.get(i).isAvailable());
    }
  }

  /**
   * Reads and processes the user commands with respect to the menu of this application
   * 
   * @param scanner Scanner object used to read the user command lines
   */
  public void readProcessUserCommand(Scanner scanner) {
    final String promptCommandLine = "ENTER COMMAND: ";
    displayMainMenu(); // display the library management system main menu
    System.out.print(promptCommandLine);
    String command = scanner.nextLine(); // read user command line
    String[] commands = command.trim().split(" "); // split user command
    while (commands[0].trim().charAt(0) != '3') { // '3': Exit the application
      switch (commands[0].trim().charAt(0)) {
        case '1': // login as librarian commands[1]: password
          if (this.librarian.checkPassword(commands[1])) {
            // read and process librarian commands
            readProcessLibrarianCommand(scanner);
          } else { // error password
            System.out.println("Error: Password incorrect!");
          }
          break;
        case '2': // login as subscriber commands[1]: card bar code
          Subscriber subscriber = this.findSubscriber(Integer.parseInt(commands[1]));
          if (subscriber != null) {
            if (subscriber.getPin() == Integer.parseInt(commands[2])) // correct PIN
              // read and process subscriber commands
              readProcessSubscriberCommand(subscriber, scanner);
            else
              System.out.println("Error: Incorrect PIN.");
          }
          break;
      }
      // read and split next user command line
      displayMainMenu(); // display the library management system main menu
      System.out.print(promptCommandLine);
      command = scanner.nextLine(); // read user command line
      commands = command.trim().split(" "); // split user command line
    }
  }

  /**
   * Reads and processes the librarian commands according to the librarian menu
   * 
   * @param scanner Scanner object used to read the librarian command lines
   */
  public void readProcessLibrarianCommand(Scanner scanner) {
    final String promptCommandLine = "ENTER COMMAND: ";
    displayLibrarianMenu();// display the library management librarian menu
    System.out.print(promptCommandLine);
    String command = scanner.nextLine(); // read user command line
    String[] commands = command.trim().split(" "); // split user command
    while (commands[0].trim().charAt(0) != '9') {// '9' exit the application
      switch (commands[0].trim().charAt(0)) {
        case '1':// Add new Book
          addBook(commands[1], commands[2]);
          break;
        case '2':// Add new subscriber
          addSubscriber(commands[1], Integer.parseInt(commands[2]), commands[3], commands[4]);
          break;
        case '3':// Check out a Book
          // get the book object
          Subscriber newSubscriber = findSubscriber(Integer.parseInt(commands[1]));
          Book newbook = findBook(Integer.parseInt(commands[2]));// get the book object
          if (newSubscriber != null && newbook != null) {// check if the book and subscriber exist
            newSubscriber.checkoutBook(newbook);
          }
          break;
        case '4':// Return a Book for a subscriber
          // get the book object
          Subscriber newSubscriber2 = findSubscriber(Integer.parseInt(commands[1]));
          Book checkedBook = findBook(Integer.parseInt(commands[2]));
          if (newSubscriber2 != null && checkedBook != null) {
            // check if the book is checked by her
            if (newSubscriber2.isBookInBooksCheckedOut(checkedBook))
              newSubscriber2.returnBook(checkedBook);
            else
              System.out.println("Sorry, this book has not been checked out for you.");
          }
          break;
        case '5':// Display Personal Info of a Subscriber
          // get the book object
          Subscriber newSubscriber3 = findSubscriber(Integer.parseInt(commands[1]));
          if (newSubscriber3 != null) {
            newSubscriber3.displayPersonalInfo();
          }
          break;
        case '6':// Display Books Checked out by a Subscriber
          // get the subscriber object
          Subscriber newSubscriber4 = findSubscriber(Integer.parseInt(commands[1]));
          if (newSubscriber4 != null) {
            newSubscriber4.displayBooksCheckedOut();
          }
          break;
        case '7':// Display All Books
          displayBooks(books);
          break;
        case '8':// Remove a Book
          Book BooktoRmove = removeBook(Integer.parseInt(commands[1]));// get the book object
          if (BooktoRmove == null) {// cannot find the book
            System.out.println("Error, book not find in the library now");
          }
          break;
      }
      displayLibrarianMenu(); // display the library management librarian menu
      System.out.print(promptCommandLine);
      command = scanner.nextLine(); // read user command line
      commands = command.trim().split(" "); // split user command line
    }
  }

  /**
   * Reads and processes the subscriber commands according to the subscriber menu
   * 
   * @param subscriber Current logged in subscriber
   * @param scanner    Scanner object used to read the librarian command lines
   */
  public void readProcessSubscriberCommand(Subscriber subscriber, Scanner scanner) {
    final String promptCommandLine = "ENTER COMMAND: ";
    displaySubscriberMenu();
    System.out.print(promptCommandLine);
    String command = scanner.nextLine(); // read user command line
    String[] commands = command.trim().split(" "); // split user command
    while (commands[0].trim().charAt(0) != '9') {// '9' to exit this application
      switch (commands[0].trim().charAt(0)) {
        case '1':// Check out a book
          Book newBook = findBook(Integer.parseInt(commands[1]));
          if (newBook != null) {// book exist, ready to check out
            subscriber.checkoutBook(newBook);
          }
          break;
        case '2':// Return a book
          Book checkedBook = findBook(Integer.parseInt(commands[1]));
          if (checkedBook != null) {
            if (subscriber.isBookInBooksCheckedOut(checkedBook)) {// book is checked by her
              subscriber.returnBook(checkedBook);
            } else// she has not checked this book, display the error message
              System.out.println("\"Sorry, this book has not been checked out for you.\"");
          }

          break;
        case '3':// Search a Book by title
          ArrayList<Book> bookListTitle = findBookByTitle(commands[1]);
          for (int i = 0; i < bookListTitle.size(); i++) {// display all the book with the title
            System.out.println("<Book ID>: " + bookListTitle.get(i).getID() + " <Title>: "
                + bookListTitle.get(i).getTitle() + " <Author>: " + bookListTitle.get(i).getAuthor()
                + " <Is Available>: " + String.valueOf(bookListTitle.get(i).isAvailable()));
          }
          break;
        case '4':// Search a Book by author
          ArrayList<Book> bookListAuthor = findBookByAuthor(commands[1]);
          for (int i = 0; i < bookListAuthor.size(); i++) {// display all the book with the author
            System.out.println("<Book ID>: " + bookListAuthor.get(i).getID() + " <Title>: "
                + bookListAuthor.get(i).getTitle() + " <Author>: "
                + bookListAuthor.get(i).getAuthor() + " <Is Available>: "
                + String.valueOf(bookListAuthor.get(i).isAvailable()));
          }
          break;
        case '5':// Print list of books checked out
          subscriber.displayBooksCheckedOut();
          break;
        case '6':// Print history of returned books
          subscriber.displayHistoryBooksReturned();
          break;
        case '7':// Update address
          subscriber.setAddress(commands[1]);
          break;
        case '8':// Update phone number
          subscriber.setPhoneNumber(commands[1]);
          break;
      }
      // read and split next user command line
      displaySubscriberMenu(); // display the library management system subscriber menu
      System.out.print(promptCommandLine);
      command = scanner.nextLine(); // read user command line
      commands = command.trim().split(" "); // split user command line
    }
  }

  /**
   * Displays the main menu for this book library application
   */
  private static void displayMainMenu() {
    System.out.println("\n--------------------------------------------------------");
    System.out.println("     Welcome to our Book Library Management System");
    System.out.println("--------------------------------------------------------");
    System.out.println("Enter one of the following options:");
    System.out.println("[1 <password>] Login as a librarian");
    System.out.println("[2 <card bar code> <4-digits pin>] Login as a Subscriber");
    System.out.println("[3] Exit"); // Exit the application
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Displays the menu for a Subscriber
   */
  private static void displaySubscriberMenu() {
    System.out.println("\n--------------------------------------------------------");
    System.out.println("    Welcome to Subscriber's Space");
    System.out.println("--------------------------------------------------------");
    System.out.println("Enter one of the following options:");
    System.out.println("[1 <book ID>] Check out a book");
    System.out.println("[2 <book ID>] Return a book");
    System.out.println("[3 <title>] Search a Book by title");
    System.out.println("[4 <author>] Search a Book by author");
    System.out.println("[5] Print list of books checked out");
    System.out.println("[6] Print history of returned books");
    System.out.println("[7 <address>] Update address");
    System.out.println("[8 <phone number>] Update phone number");
    System.out.println("[9] Logout");
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Displays the menu for the Librarian
   */
  private static void displayLibrarianMenu() {
    System.out.println("\n--------------------------------------------------------");
    System.out.println("    Welcome to Librarian's Space");
    System.out.println("--------------------------------------------------------");
    System.out.println("Enter one of the following options:");
    System.out.println("[1 <title> <author>] Add new Book");
    System.out.println("[2 <name> <pin> <address> <phone number>] Add new subscriber");
    System.out.println("[3 <card bar code> <book ID>] Check out a Book");
    System.out.println("[4 <card bar code> <book ID>] Return a Book for a subscriber");
    System.out.println("[5 <card bar code>] Display Personal Info of a Subscriber");
    System.out.println("[6 <card bar code>] Display Books Checked out by a Subscriber");
    System.out.println("[7] Display All Books");
    System.out.println("[8 <book ID>] Remove a Book");
    System.out.println("[9] Logout");
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Display the Application GoodBye and logout message.
   */
  private static void displayGoodByeLogoutMessage() {
    System.out.println("\n--------------------------------------------------------");
    System.out.println("       Thanks for Using our Book Library App!!!!");
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Main method that represents the driver for this application
   * 
   * @param args
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in); // create a scanner object to read user inputs
    // create a new library object
    Library madisonLibrary = new Library("Madison, WI", "april", "abc");
    // read and process user command lines
    madisonLibrary.readProcessUserCommand(scanner);
    displayGoodByeLogoutMessage(); // display good bye message
    scanner.close();// close this scanner
  }
}


