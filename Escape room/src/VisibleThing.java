//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (VisibleThing)
// Files:           (VisibleThing.java)
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
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * A inherited class of Thing. This is a class of VisibleThing, which we can control the vidsible
 * things in the escape room
 * 
 * @author yijun cheng & yurdong cui
 *
 */
public class VisibleThing extends Thing {
  private PImage image; // the graphical representation of this thing
  private int x; // the horizontal position (in pixels of this thing's left side)
  private int y; // the vertical position (in pixels of this thing's top side)

  /**
   * The constructor of the VisibleThing. Initialize the field variables here.
   * 
   * @param name the name of the thing
   * @param x    the x coordinate of the thing
   * @param y    the y coordinate of the thing
   */
  public VisibleThing(String name, int x, int y) {
    super(name);// call the construct of the base class
    String filename = "images" + File.separator + name + ".png";
    image = Thing.getProcessing().loadImage(filename);// initialize the image
    this.x = x;// initialize the x
    this.y = y;// initialize the y
  } // initialize this new thing
  // the image for this visible thing should be loaded from :
  // "images"+File.separator+ name +".png"

  /**
   * An override method update. This method can update the state of the visible thing. So, we need
   * to draw the new image of the thing in each update.
   */
  @Override
  public Action update() {
    Thing.getProcessing().image(image, x, y);// draw the thing according to it's coordinates.
    return null;
  } // draws image at its position before returning null

  /**
   * move method used to move the thing.
   * 
   * @param dx the variation of x coordinate
   * @param dy the variation of x coordinate
   */
  public void move(int dx, int dy) {
    x = x + dx;
    y = y + dy;
  } // changes x by adding dx to it (and y by dy)

  /**
   * this is a boolean method used to check if the location is over this visible thing
   * 
   * @param x the x coordinate we need to check
   * @param y the y coordinate we need to check
   * @return true if it is over this thing. False otherwise.
   */
  public boolean isOver(int x, int y) {
    if (x >= this.x && x <= this.x + image.width && y >= this.y && y <= this.y + image.height) {
      return true;
    }
    return false;
  } // return true only when point x,y is over image

  /**
   * the overload
   * 
   * @param other another thing to be checked.
   * @return true if the other thing is over this thing
   */
  public boolean isOver(VisibleThing other) {
    // check if the other thing is not over this thing on the horizontal coordinate
    if (x > other.x + other.image.width || other.x > x + image.width) {
      return false;
    }
    // check if the other thing is not over this thing on the vertical coordinate
    if (y + image.height < other.y || other.y + other.image.height < y) {
      return false;
    }
    return true;
  } // return true only when other's image overlaps this one's
}
