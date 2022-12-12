package org.bcit.comp2522.project;

import java.awt.Color;
import processing.core.PVector;

/**
 * BoundingBox class.
 * Defines BoundingBox for all Drawables.
 *
 * @author towaquimbayo, alexgibbison
 * @version 1.0
 */
public class BoundingBox implements Drawable {
  /** Top right PVector value. */
  private final PVector topRight;
  /** Top left PVector value. */
  private final PVector topLeft;
  /** Bottom right PVector value. */
  private final PVector bottomRight;
  /** Bottom left PVector value. */
  private final PVector bottomLeft;
  /** Top PVector value. */
  private final float top;
  /** Right PVector value. */
  private final float right;
  /** Bottom PVector value. */
  private final float bottom;
  /** Left PVector value. */
  private final float left;
  /** Color object value. */
  private final Color color;
  /** Window. */
  private final Window window;
  /** Two Floating Value. */
  public static final float TWO = 2f;
  /** Red Color HEX value. */
  public static final int TWO_FIFTY_FIVE = 255;

  /**
   * BoundingBox constructor.
   *
   * @param pos centre point position
   * @param width width of object
   * @param height height of object
   */
  public BoundingBox(PVector pos, float width, float height, Window win) {
    this.top = pos.y - (height / TWO);
    this.bottom = pos.y + (height / TWO);
    this.right = pos.x + (width / TWO);
    this.left = pos.x - (width / TWO);

    this.topRight = new PVector(pos.x + (width / TWO), pos.y - (height / TWO));
    this.topLeft = new PVector(pos.x - (width / TWO), pos.y - (height / TWO));
    this.bottomRight = new PVector(pos.x + (width / TWO), pos.y + (height / TWO));
    this.bottomLeft = new PVector(pos.x - (width / TWO), pos.y + ((height / TWO)));

    this.color = new Color(TWO_FIFTY_FIVE, 0, 0);
    this.window = win;
  }

  /**
   * Gets the top boundary of the bounding box.
   *
   * @return top
   */
  public float getTop() {
    return this.top;
  }

  /**
   * Gets the bottom boundary of the bounding box.
   *
   * @return bottom
   */
  public float getBottom() {
    return this.bottom;
  }

  /**
   * Gets the left boundary of the bounding box.
   *
   * @return left
   */
  public float getLeft() {
    return this.left;
  }

  /**
   * Gets the right boundary of the bounding box.
   *
   * @return right
   */
  public float getRight() {
    return this.right;
  }

  /**
   * Processing coordinates are "top-left", i.e., Y increases in the
   * down direction. If the BoundingBox of the other character is
   * outside the current BoundingBox, then a collision is not
   * happening. We return the negation of that for collides.
   *
   * @param b the other bounding box
   * @return true if other bounding box is outside this one
   */
  public boolean collides(BoundingBox b) {
    return !(this.getBottom() < b.getTop()
            || this.getTop() > b.getBottom()
            || this.getRight() < b.getLeft()
            || this.getLeft() > b.getRight());
  }

  /**
   * Draw the boundingBox if necessary to
   * check where the boundingBox is for Drawable Character.
   */
  @Override
  public void draw() {
    this.window.stroke(color.getRed(), color.getGreen(), color.getBlue());
    this.window.line(this.topLeft.x, this.topLeft.y, this.topRight.x, this.topRight.y);
    this.window.line(this.topRight.x, this.topRight.y, this.bottomRight.x, this.bottomRight.y);
    this.window.line(this.bottomRight.x, this.bottomRight.y, this.bottomLeft.x, this.bottomLeft.y);
    this.window.line(this.topLeft.x, this.topLeft.y, this.bottomLeft.x, this.bottomLeft.y);
  }
}