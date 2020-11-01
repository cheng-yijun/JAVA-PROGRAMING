////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           (DraggableThing)
//Files:           (DraggableThing.java)
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

import processing.core.PApplet;

/**
 * The draggablething calss. This is an inherited class that extends visible thing. So, the thing
 * that can be drag will need the operation in this class
 * 
 * @author yijuncheng & yuedong cui
 *
 */
public class DraggableThing extends VisibleThing {
  private boolean mouseWasPressed; // similar to use in ClickableThing
  private boolean isDragging; // true when this object is being dragged by the user
  private int oldMouseX; // horizontal position of mouse during last update
  private int oldMouseY; // vertical position of mouse during last update

  /**
   * The constructor of the DraggableThing class. It is used to initialize all of the thing.
   * 
   * @param filename the name of the file to be load
   * @param x        x coordinate of the mouse
   * @param y        y coordinate of the mouse
   */
  public DraggableThing(String filename, int x, int y) {
    // initialize new thing
    super(filename, x, y);
    mouseWasPressed = false;
    isDragging = false;
    oldMouseX = 0;
    oldMouseY = 0;
  }

  /**
   * The override method to update the thing. If the thing is being dragged, we will need some
   * operation in this method
   */
  @Override
  public Action update() {
    super.update();
    Action action = null;
    boolean over = isOver(Thing.getProcessing().mouseX, Thing.getProcessing().mouseY);
    
    if (isDragging) {
      DragWhenOver = false;
      int dx = Thing.getProcessing().mouseX - oldMouseX;
      int dy = Thing.getProcessing().mouseY - oldMouseY;
      move(dx, dy);
    }
    
    if (!mouseWasPressed && Thing.getProcessing().mousePressed ) {
      mouseWasPressed = true;
      isDragging = over;
    }
    
    
    
    oldMouseX = Thing.getProcessing().mouseX;
    oldMouseY = Thing.getProcessing().mouseY;
    if (mouseWasPressed && !Thing.getProcessing().mousePressed) {
      mouseWasPressed = false;
      isDragging = false;
      DragWhenOver = true;
      action = drop();
    }
    return action;
  } // calls VisibleThing update(), then moves according to mouse drag
  // each time isDragging changes from true to false, the drop() method below will be called once
  // and any action objects returned from that method should then be returned from update()

  /**
   * The action of the thing. No action for the draggable thing, so just return null.
   * 
   * @return
   */
  protected Action drop() {
    return null;
  } // this method returns null
  // subclass types will override this drop() method to do more interesting things
}
