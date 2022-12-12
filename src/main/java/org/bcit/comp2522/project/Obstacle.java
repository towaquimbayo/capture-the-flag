package org.bcit.comp2522.project;

import processing.core.PVector;

/**
 * Obstacle class.
 *
 * @author towaquimbayo, alexgibbison
 * @version 1.0
 */
public class Obstacle implements Drawable {
  /** Width value. */
  private final float width;
  /** Height value. */
  private final float height;
  /** PVector Position value. */
  private final PVector position;
  /** Window. */
  private final Window window;

  /**
   * Obstacle Constructor.
   *
   * @param w for width
   * @param h for height
   * @param pos for position
   * @param win for window.
   */
  public Obstacle(float w, float h, PVector pos, Window win) {
    this.width = w;
    this.height = h;
    this.position = pos;
    this.window = win;
  }

  /**
   * BoundingBox method.
   *
   * @return BoundingBox
   */
  public BoundingBox getBoundingBox() {
    return new BoundingBox(this.position, this.width, this.height, this.window);
  }

  /**
   * Draw method.
   */
  @Override
  public void draw() {
    this.window.fill(0);
    this.window.rect(this.position.x - (this.width / BoundingBox.TWO),
            this.position.y - (this.height / BoundingBox.TWO), this.width, this.height);
  }
}
