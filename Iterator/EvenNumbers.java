//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (EvenNumbers)
// Files:           (EvenNumbers.java)
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
import java.util.Iterator;

/**
 * This is the class of EvenNumbers iterator which can iterate to generate next even number.
 * 
 * @author yijuncheng
 *
 */
public class EvenNumbers implements Iterator<Integer> {
  private Integer number;// a number to iterte

  /**
   * the constructor
   * 
   * @param number as the first number to iterate
   */
  public EvenNumbers(Integer number) {
    this.number = number;//initialize
  }

  @Override
  /**
   * has Next method always return true here
   */
  public boolean hasNext() {
    return true;
  }

  @Override
  /**
   * next method used to generate the next even number.
   */
  public Integer next() {
    if (hasNext()) {// if it can has next
      if (number % 2 == 0) {// if this number is even number
        number = number + 2;// number update to be the next even number
        return number - 2;
      } else {// number is odd
        number++;// the next even number
        return number - 1;
      }
    }
    return null;
  }
}
