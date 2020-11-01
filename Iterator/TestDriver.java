//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (NextPowerOfTwo)
// Files:           (NextPowerOfTwo.java)
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
 * This is the test class used to test the function of the iterators including evenNumber iterator,
 * Finite iterator and infinite iterator and a generator.
 * 
 * @author yijuncheng
 *
 */
public class TestDriver {

  /**
   * Test the EvenNumbers iterator. Input some even value and see it we can get the expected number
   * from the iterator.
   * 
   * @return true if test pass. false otherwise
   */
  public static boolean testEvenNumbers() {
    EvenNumbers it = new EvenNumbers(44);// craete the new object
    if (it.next() != 44) {// test if the next is 44
      System.out.println("The first call of EvenNumbers.next() "
          + "did not return the value passed into its constructor.");
      return false;
    }
    if (it.next() != 46) {// test if the next is 46
      System.out.println(
          "The second call of EvenNumbers.next() " + "did not return the smallest even number, "
              + "that is larger than the previously returned number.");
      return false;
    }
    if (it.hasNext() != true) { // hasNext should be true
      System.out.println("EvenNumbers.next() returned false, " + "but should always return true.");
      return false;
    }
    return true;
  }

  /**
   * test a function of power of two of the infinite iterator,
   * 
   * @return true if test pass. false otherwise
   */
  public static boolean testPowersOfTwo() {
    InfiniteIterator<Integer> it = new InfiniteIterator<Integer>(8, new NextPowerOfTwo());
    if (it.next() != 8) {// test if the next is 8
      System.out.println("The first call of InfiniteIterator.next() "
          + "did not return the number passed into its constructor.");
      return false;
    }
    if (it.next() != 16) {// test if the next is 16
      System.out.println("The second call of InfiniteIterator.next() "
          + "did not return the smallest power of two number, "
          + "that is larger than the previously returned number.");
      return false;
    }
    if (it.hasNext() != true) { // hasNext should be true
      System.out
          .println("InfiniteIterator.next() returned false, " + "but should always return true.");
      return false;
    }
    return true;
  }

  /**
   * test a function AddExtraSmile of the infinite iterator
   * 
   * @return true if test pass. false otherwise
   */
  public static boolean testAddExtraSmile() {
    InfiniteIterator<String> it = new InfiniteIterator<>("Hello", new AddExtraSmile());
    if (!it.next().equals("Hello")) {// test if the next is Hello
      System.out.println("The first call of InfiniteIterator.next() "
          + "did not return the string passed into its constructor.");
      return false;
    }
    if (!it.next().contains(" :)")) {// test if the next contains ":)"
      System.out.println("The second call of InfiniteIterator.next() "
          + "did not return the a string with one more :), "
          + "than the previously returned string.");
      return false;
    }
    if (it.hasNext() != true) {// hasNext should be true
      System.out
          .println("InfiniteIterator.next() returned false, " + "but should always return true.");
      return false;
    }
    return true;
  }

  /**
   * Test the finite itertor for 8 length.
   * 
   * @return true if test pass. false otherwise
   */
  public static boolean testFiniteIterator() {
    InfiniteIterator<Integer> infinite = new InfiniteIterator<>(2, new NextPowerOfTwo());
    FiniteIterator<Integer> it = new FiniteIterator<>(infinite, 8);
    String s = "";
    while (it.hasNext())// iterator to generate the next number
      s += " " + it.next();
    if (!s.equals(" 2 4 8 16 32 64 128 256")) {// The list of the 8 length number expected
      System.out.println("Repeatedly called the next() method of a FiniteIterator,"
          + "and the incorrect valuese were returned:" + s);
      return false;
    }
    return true;
  }

  /**
   * test the generator. Create several generator to generate some iterator. And then test those
   * iterators to see if they can work well.
   * 
   * @return true if test pass. false otherwise
   */
  public static boolean testGenerator() {
    // crete some generators generating iterators with different types.
    Generator<Integer> generator1 = new Generator<Integer>(8, new NextPowerOfTwo());
    Iterator<Integer> itIntrger = generator1.iterator();
    Generator<String> generator2 = new Generator<String>("Hello", new AddExtraSmile());
    Iterator<String> itString = generator2.iterator();
    Generator<Integer> generator3 = new Generator<Integer>(2, new NextPowerOfTwo(), 8);
    Iterator<Integer> itIntegerFinite = generator3.iterator();
    // test the first iterator.
    if (itIntrger.next() != 8) {// next should be 8
      System.out.println("The first call of InfiniteIterator.next() "
          + "did not return the number passed into its constructor.");
      return false;
    }
    if (itIntrger.next() != 16) {// next should be 16
      System.out.println("The second call of InfiniteIterator.next() "
          + "did not return the smallest power of two number, "
          + "that is larger than the previously returned number.");
      return false;
    }
    if (itIntrger.hasNext() != true) {// should has next being true
      System.out
          .println("InfiniteIterator.next() returned false, " + "but should always return true.");
      return false;
    }
    // test the second iterator generated by generator with the function of add extra smile
    if (!itString.next().equals("Hello")) {
      System.out.println("The first call of InfiniteIterator.next() "
          + "did not return the string passed into its constructor.");
      return false;
    }
    if (!itString.next().contains(" :)")) {
      System.out.println("The second call of InfiniteIterator.next() "
          + "did not return the a string with one more :), "
          + "than the previously returned string.");
      return false;
    }
    if (itString.hasNext() != true) {
      System.out
          .println("InfiniteIterator.next() returned false, " + "but should always return true.");
      return false;
    }
    // test the finite iterator generated by generator.
    String s = "";
    while (itIntegerFinite.hasNext())
      s += " " + itIntegerFinite.next();
    if (!s.equals(" 2 4 8 16 32 64 128 256")) {
      System.out.println("Repeatedly called the next() method of a FiniteIterator,"
          + "and the incorrect valuese were returned:" + s);
      return false;
    }
    return true;
  }

  /**
   * this is the main method to run this test class
   * 
   * @param args String type
   */
  public static void main(String[] args) {
    System.out.println("testEvenNumbers(): " + testEvenNumbers());
    System.out.println("testPowersOfTwo(): " + testPowersOfTwo());
    System.out.println("testAddExtraSmile(): " + testAddExtraSmile());
    System.out.println("testFiniteIterator(): " + testFiniteIterator());
    System.out.println("testGenerator(): " + testGenerator());
  }
}

/**
 * the class with function of the power of 2
 * 
 * @author yijuncheng
 *
 */
class NextPowerOfTwo implements Function<Integer, Integer> {
  @Override
  public Integer apply(Integer previous) {
    return 2 * previous;
  }
}

/**
 * the class with function of add extra smile
 * 
 * @author yijuncheng
 *
 */
class AddExtraSmile implements Function<String, String> {
  @Override
  public String apply(String t) {
    return t + " :)";
  }
}
