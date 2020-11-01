//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (Generator)
// Files:           (Generator.java)
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
import java.util.function.Function;

/**
 * This is the  generator calss to generator specific iterator.
 * @author yijuncheng
 *
 * @param <T> T type generic.
 */
public class Generator<T> implements Iterable<T> {
  private T firstValue;//the first value to be iterated
  private Function<T, T> generateNextFromLast;//specific function of the iterator
  private int length;//length for finite iterator
  private int choice;//choose infinite or finite iterator

  /**
   * The constructor
   * @param firstValue the first value to be iterated
   * @param generateNextFromLast specific function of the iterator
   */
  public Generator(T firstValue, Function<T, T> generateNextFromLast) {
    //initialize
    this.firstValue = firstValue;
    this.generateNextFromLast = generateNextFromLast;
    length = 0;
    choice = 0;
  }

  /**
   * Another constructor
   * @param firstValue firstValue the first value to be iterated
   * @param generateNextFromLast generateNextFromLast specific function of the iterator
   * @param length length for finite iterator
   */
  public Generator(T firstValue, Function<T, T> generateNextFromLast, int length) {
    //initialize
    this.firstValue = firstValue;
    this.generateNextFromLast = generateNextFromLast;
    this.length = length;
    choice = 1;
  }

  /**
   * The override method to return a new specific iterator
   */
  @Override
  public Iterator<T> iterator() {
    if (choice == 0) {
      return new InfiniteIterator<T>(firstValue, generateNextFromLast);
    }else {
      return new FiniteIterator<T>(new InfiniteIterator<T>(firstValue, generateNextFromLast), length);
    }
  }


}
