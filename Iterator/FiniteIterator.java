//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (FiniteIterator)
// Files:           (FiniteIterator.java)
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
 * This is the finite iterator class which can iterate specific length.
 * 
 * @author yijuncheng
 *
 * @param <T> the generic type
 */
public class FiniteIterator<T> implements Iterator<T> {
  private InfiniteIterator<T> infiniteIterator;// A infinite iterator
  private int length;// the length of this iterator
  private int count;// count the number of T that has been iterated
  private T current;// current to be returned

  /**
   * The constructor
   * 
   * @param infiniteIterator the parameter of infinite iterator
   * @param length           specific length
   */
  public FiniteIterator(InfiniteIterator<T> infiniteIterator, int length) {
    //initialize
    this.infiniteIterator = infiniteIterator;
    this.length = length;
    count = 0;
    current = null;
  }

  @Override
  /**
   * has next method return true when number of count is smaller than length and no error generated
   */
  public boolean hasNext() {
    if (count < length
        && (current == null || (current != null && !current.toString().contains("FAILED to find"))))
      return true;
    return false;
  }

  @Override
  /**
   * next method to generate the next T.
   */
  public T next() {
    if (hasNext()) {
      count++;// count increase by 1
      current = infiniteIterator.next();// generate next
      return current;
    } else
      return null;
  }
}
