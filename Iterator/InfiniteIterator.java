//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (InfiniteIterator)
// Files:           (InfiniteIterator.java)
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
 * The infinite iterator class
 * 
 * @author yijuncheng
 *
 * @param <T> the generic type T
 */
public class InfiniteIterator<T> implements Iterator<T> {
  private T current;// current data T
  private Function<T, T> function;// specific function of this iterator

  /**
   * The constructor
   * 
   * @param current  current data
   * @param function specific function of this iterator
   */
  public InfiniteIterator(T current, Function<T, T> function) {
    // initialize
    this.current = current;
    this.function = function;
  }

  @Override
  /**
   * has next method always return true
   */
  public boolean hasNext() {
    return true;
  }

  @Override
  /**
   * next method return the next current data with type T
   */
  public T next() {
    if (hasNext()) {// always has next here
      T currentNumber = current;// record the current data
      current = function.apply(current);// generatoe the next
      if (current.toString().contains("FAILED to find")) {// if some error generated
        return current;
      }
      return currentNumber;// no error
    }
    return null;
  }
  
  public static void main(String[] args) {
    InfiniteIterator<Integer> it = new InfiniteIterator<Integer>(2, new NextPowerOfTwo());

//    it.function = new NextPowerOfTwo();
    // anonymous class
//    it.function = new Function<Integer,Integer>() {
//      public Integer apply(Integer previous) {
//        return 2*previous;
//      }      
//    };
    // lambda expression
    it.function = previous -> 3*previous;    
    
    for(int i=0;i<10;i++)
      System.out.println(it.next());
  }
}

