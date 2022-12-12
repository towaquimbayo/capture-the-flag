package org.bcit.comp2522.project;

import processing.core.PVector;

/**
 * FlagPole class.
 *
 * @author towaquimbayo, alexgibbison
 * @version 1.0
 */
public class FlagPole implements Drawable {
  /** Width value. */
  private final float width;
  /** Height value. */
  private final float height;
  /** Window. */
  private final Window window;
  /** Position PVector value. */
  private final PVector position;

  /**
   * FlagPole Constructor.
   *
   * @param pos for position
   * @param w for width
   * @param h for height
   * @param win for window
   */
  public FlagPole(PVector pos, float w, float h, Window win) {
    this.position = pos;
    this.width = w;
    this.height = h;
    this.window = win;
  }

  /**
   * Getter for width.
   *
   * @return this.width
   */
  public float getWidth() {
    return this.width;
  }

  /**
   * Draw method.
   */
  @Override
  public void draw() {
    this.window.stroke(0);
    this.window.fill(0);
    this.window.strokeWeight(Flag.TWO);
    this.window.triangle(this.position.x, this.position.y,
        this.position.x, this.position.y + this.height,
        this.position.x + this.width, this.position.y + (this.height / Flag.TWO));
  }
}
