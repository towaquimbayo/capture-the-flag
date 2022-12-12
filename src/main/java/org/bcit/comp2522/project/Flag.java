package org.bcit.comp2522.project;

import processing.core.PVector;

/**
 * Flag class.
 *
 * @author towaquimbayo, alexgibbison
 * @version 1.0
 */
public class Flag implements Drawable {
  /** Position PVector value. */
  private PVector position;
  /** Home PVector value. */
  private final PVector home;
  /** Width value. */
  private final float width;
  /** Height value. */
  private final float height;
  /** Window. */
  private final Window window;
  /** FlagPole object aggregation. */
  private FlagPole flagPole;
  /** Two. */
  public static final float TWO = 2f;
  /** Fifteen. */
  public static final float FIFTEEN = 15f;

  /**
   * Flag Constructor.
   *
   * @param pos for position
   * @param homePos for home position
   * @param w for width
   * @param h for height
   * @param win for window
   */
  public Flag(PVector pos, PVector homePos, float w, float h, Window win) {
    this.position = pos;
    this.home = homePos;
    this.width = w;
    this.height = h;
    this.window = win;
    setFlagPole(); // set the flagpole
  }

  /**
   * Set the flag pole position.
   */
  private void setFlagPole() {
    PVector flagPolePos = new PVector(this.position.x, this.position.y - this.height);
    this.flagPole = new FlagPole(flagPolePos, this.height, this.height, this.window);
  }

  /**
   * Getter for home position.
   *
   * @return the PVector value for Home
   */
  public PVector getHome() {
    return this.home;
  }

  /**
   * Getter for Flag position.
   *
   * @return the PVector value for Flag.
   */
  public PVector getPosition() {
    return this.position;
  }

  /**
   * Setter for position.
   *
   * @param pos for position
   */
  public void setPosition(PVector pos) {
    this.position = new PVector(pos.x - (this.flagPole.getWidth() / TWO),
            pos.y - (this.height / TWO) - FIFTEEN);
    setFlagPole();
  }

  /**
   * BoundingBox method.
   *
   * @return BoundingBox
   */
  public BoundingBox getBoundingBox() {
    PVector boundBoxPos = new PVector(this.position.x
            + (this.flagPole.getWidth() / TWO), this.position.y);
    return new BoundingBox(boundBoxPos, this.flagPole.getWidth(),
            this.height * TWO, this.window);
  }

  /**
   * Draw method.
   */
  @Override
  public void draw() {
    this.window.stroke(0);
    this.window.noFill();
    this.window.strokeWeight(TWO);
    this.window.line(this.position.x, this.position.y - this.height,
            this.position.x, this.position.y + this.height);
    this.flagPole.draw();
  }
}
