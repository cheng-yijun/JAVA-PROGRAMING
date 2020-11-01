////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           (DragAndDroppableThing)
//Files:           (DragAndDroppableThing.java)
//Course:          (cs300 , 2019 Spring,)
//
//Author:          (Yijun Cheng)
//Email:           (cheng229@wisc.edu )
//Lecturer's Name: (Gary Dahl)
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//Partner Name:    (Yuedong Cui)
//Partner Email:   ( cui54@wisc.edu)
//Partner Lecturer's Name: (Gary Dahl)
//
//VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//___ Write-up states that pair programming is allowed for this assignment.
//___ We have both read and understand the course Pair Programming Policy.
//___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully 
//acknowledge and credit those sources of help here.  Instructors and TAs do 
//not need to be credited here, but tutors, friends, relatives, room mates, 
//strangers, and others do.  If you received no outside help from either type
//of source, then please explicitly indicate NONE.
//
//Persons:         (identify each person and describe their help in detail)
//Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This is the DragAndDroppableThing class. if the thing can be dropped and has some actions, it
 * will need some operation in this class
 * 
 * @author yijuncheng
 *
 */
public class DragAndDroppableThing extends DraggableThing {
  private VisibleThing target; // object over which this object can be dropped
  private Action action; // action that results from dropping this object over target

  /**
   * The constructor of the DragAndDroppableThing to initialize the fields
   * 
   * @param name   the name of the thing
   * @param x      the x coordinate of the thing
   * @param y      the y coordinate of the thing
   * @param target the target to be dropped
   * @param action action of the thing
   */
  public DragAndDroppableThing(String name, int x, int y, VisibleThing target, Action action) {
    super(name, x, y);
    this.target = target;
    this.action = action;
  } // initialize new object

  /**
   * drop method to check if the thing is dropped over the target. if so, return action of the
   * thing.
   */
  @Override
  protected Action drop() {
    // check if the thing is over the target
    boolean over = isOver(target);
    // if so, return action
    if (over && target.isActive()) {
      deactivate();
      target.deactivate();
      return action;
    }
    return null;
  } // returns action and deactivates objects in response to successful drop
  // When this object is over its target and its target is active:
  // deactivate both this object and the target object, and return action,
  // otherwise return null
}
