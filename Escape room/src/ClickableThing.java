//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (ClickableThing)
// Files:           (ClickableThing.java)
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
 * this is an inherited class which extends the visible thing. All the visible things that can be
 * clicked will need some operation in this class.
 * 
 * @author yijun cheng & yuedong cui
 * 
 */
public class ClickableThing extends VisibleThing {
  private Action action; // action returned from update when this object is clicked
  private boolean mouseWasPressed; // tracks whether the mouse was pressed during the last update()

  /**
   * The constructor of the ClickableThing to initialize the field
   * 
   * @param name   the name of the thing
   * @param x      the x coordinate of the thing
   * @param y      the y coordinate of the thing
   * @param action the action of the thing
   */
  public ClickableThing(String name, int x, int y, Action action) {
    super(name, x, y);
    this.action = action;
    mouseWasPressed = false;
  } // initializes this new object

  /**
   * An override method update. This method can update the state of the visible thing. So, we need
   * to call the update method of the visible thing. and check if this thing is clicked. If it is
   * clicked, call it's action to response the click.
   */
  @Override
  public Action update() {
    super.update();// call the override update method
    // check if the mouse if over the thing.
    boolean over = isOver(Thing.getProcessing().mouseX, Thing.getProcessing().mouseY);
    // if it was not clicked and it is clicked now, return action
    if (over && Thing.getProcessing().mousePressed && !mouseWasPressed && DragWhenOver) {
      mouseWasPressed = Thing.getProcessing().mousePressed;
      return action;
    } else {// it is not clicked in the first time.
      return null;
    }

  } // calls VisibleThing update, then returns action only when mouse is first clicked
}
