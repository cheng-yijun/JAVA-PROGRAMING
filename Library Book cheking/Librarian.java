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

/**
 * librarian class stores the information of the librarian
 * 
 * @author yijuncheng and yuedong cui
 *
 */
public class Librarian {
  // instance fields
  private String username; // librarian's username
  private String password; // librarian password to have access to this application

  /**
   * construct of the Librarian class
   * 
   * @param username of the librarian
   * @param password of the librarian
   */
  public Librarian(String username, String password) {
    // initialize the variables in the field
    this.username = username;
    this.password = password;
  }

  /**
   * getter method
   * 
   * @return the name of the librarian
   */
  public String getUsername() {
    return username;
  }

  /**
   * check if the password entered is correct
   * 
   * @param password of the librarian
   * @return
   */
  public boolean checkPassword(String password) {
    if (password.equals(this.password)) {// if the password is correct, return true
      return true;
    }
    return false;
  }
}
