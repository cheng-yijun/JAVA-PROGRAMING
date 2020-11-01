//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (Action)
// Files:           (Action.java)
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

/**
 * 
 * this is a class used to deal with the action of the things.
 * 
 * @author yijun cheng
 *
 */
public class Action {
  private String message; // message printed by this action (or null to do nothing)
  private Thing thing; // The thing that will have this action

  /**
   * The construct: initialize this new action with a message
   * 
   * @param message the message that will be printed by this action.
   */
  public Action(String message) {
    this.message = message;
  } // initialize this new action

  /**
   * The construct: initialize this new action with a thing
   * 
   * @param thing the thing that will use this action.
   */
  public Action(Thing thing) {
    this.thing = thing;
  }// initialize this new thing

  /**
   * The construct: initialize this new action with thing and message
   * 
   * @param message the message that will be printed by this action.
   * @param thing   the thing that will use this action.
   */
  Action(String message, Thing thing) {
    this.message = message;
    this.thing = thing;
  }// initialize this new action and the new thing.

  /**
   * Let this thing do something to act.
   * 
   * @param things the ArrayList of all of the things used to add the corresponding thing
   */
  public void act(ArrayList<Thing> things) {
    if (thing != null) {// if the thing exists
      thing.activate();// make the thing be active
      things.add(thing);// add this thing to the ArrayList Thing
      thing = null;// clear the thing
    }
    if (message != null) {// if the thing has its message
      System.out.println(this.message);// print it's message
    }
  } // when message is not null, message is printed to System.out
}
