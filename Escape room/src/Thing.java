//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (Thing)
// Files:           (Thing.java)
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
import processing.core.PApplet;

/**
 * The class of Thing. This class can do some basic operation that all things will need.
 * 
 * @author yijuncheng & yuedong cui
 *
 */
public class Thing {
  private final String NAME; // the constant name identifying this object
  private boolean isActive; // active means thing is visible and can be interacted with
  private static PApplet processing = null;
  protected static boolean DragWhenOver = true;
  /**
   * The construct: thing to be initialize
   * 
   * @param name the name of that thing.
   */
  public Thing(String name) {
    NAME = name;
    isActive = true;
  } // initialize name, and set isActive to true

  /**
   * this is a boolean method to check if the thing has a name
   * 
   * @param name the name of that thing
   * @return true if the thing has a name, false other wise
   */
  public boolean hasName(String name) {
    if (NAME.equals(name)) {// if the thing has a name as expected.
      return true;
    }
    return false;
  } // returns true only when contents of name equal NAME

  /**
   * check if the thing is active.
   * 
   * @return true if the thing is active, false otherwise.
   */
  public boolean isActive() {
    return isActive;//
  } // returns true only when isActive is true

  /**
   * make the thing be active
   */
  public void activate() {
    isActive = true;
  } // changes isActive to true

  /**
   * make the thing be non-active
   */
  public void deactivate() {
    isActive = false;
  } // changes isActive to false

  /**
   * update method used to update the state of the thing. In this class, do nothing with the thing.
   * 
   * @return null. No operation need for a thing here
   */
  public Action update() {
    return null;
  } // this method returns null
  // subclass types will override this update() method to do more interesting things

  /**
   * initialize the processing field so that the thing can access the PApplet object.
   * 
   * @param processing a PApplet object.
   */
  public static void setProcessing(PApplet processing) {
    Thing.processing = processing;
  } // initializes processing field

  /**
   * getter method to get the field processing.
   * 
   * @return processing, the processing field.
   */
  protected static PApplet getProcessing() {
    return processing;
  } // accesser method to retrieve this static field
}
