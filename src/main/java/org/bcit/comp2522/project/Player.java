package org.bcit.comp2522.project;

import java.awt.Color;
import processing.core.PVector;

/**
 * Player class.
 *
 * @author towaquimbayo, alexgibbison
 * @version 1.0
 */
public class Player implements AbstractCharacter {
  /** PVector Position value. */
  private PVector position;
  /** Width value. */
  private final float width;
  /** Window. */
  private final Window window;
  /** Color. */
  private final Color color;
  /** KeyLeft boolean condition. */
  private boolean keyLeft;
  /** KeyRight boolean condition. */
  private boolean keyRight;
  /** KeyUp boolean condition. */
  private boolean keyUp;
  /** KeyDown boolean condition. */
  private boolean keyDown;
  /** X coordinate speed value. */
  private float xSpeed;
  /** Y coordinate speed value. */
  private float ySpeed;
  /** If player has flag boolean condition. */
  protected boolean hasFlag;
  /** Width Factor. */
  public static final float WIDTH_FACTOR = 30f;
  /** Speed Factor. */
  public static final float SPEED_FACTOR = 0.8f;
  /** Speed Drag Factor. */
  public static final float SPEED_DRAG_FACTOR = 0.9f;

  /**
   * Player Constructor.
   *
   * @param pos for position
   * @param color for color
   * @param win for Window
   */
  public Player(PVector pos, Color color, Window win) {
    this.position = pos;
    this.width = WIDTH_FACTOR;
    this.color = color;
    this.window = win;
    this.hasFlag = false;
    this.xSpeed = 0f;
    this.ySpeed = 0f;
  }

  /**
   * Getter for position.
   *
   * @return this.position
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
    this.position = pos;
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
   * Set KeyLeft value.
   *
   * @param keyLeftCondition for keyLeft
   */
  public void setKeyLeft(boolean keyLeftCondition) {
    this.keyLeft = keyLeftCondition;
  }

  /**
   * Set KeyRight value.
   *
   * @param keyRightCondition for keyRight
   */
  public void setKeyRight(boolean keyRightCondition) {
    this.keyRight = keyRightCondition;
  }

  /**
   * Set KeyUp value.
   *
   * @param keyUpCondition for keyUp
   */
  public void setKeyUp(boolean keyUpCondition) {
    this.keyUp = keyUpCondition;
  }

  /**
   * Set KeyDown value.
   *
   * @param keyDownCondition for keyDown
   */
  public void setKeyDown(boolean keyDownCondition) {
    this.keyDown = keyDownCondition;
  }

  /**
   * Getter for X Speed.
   *
   * @return this.xSpeed
   */
  public float getXSpeed() {
    return this.xSpeed;
  }

  /**
   * Getter for Y speed.
   *
   * @return this.ySpeed
   */
  public float getYSpeed() {
    return this.ySpeed;
  }

  /**
   * Bounding box method to check
   * if player is within the boundary.
   */
  protected boolean isOutOfBounds() {
    return (this.position.x - this.width < 0
            || this.position.x + this.width > this.window.width
            || this.position.y - this.width < 0
            || this.position.y + this.width > this.window.height);
  }

  /**
   * BoundingBox method.
   *
   * @return BoundingBox
   */
  @Override
  public BoundingBox getBoundingBox() {
    return new BoundingBox(this.position, this.width, this.width, this.window);
  }

  /**
   * Count Speed method based on key pressed.
   */
  private void countSpeed() {
    if (this.keyLeft) {
      this.xSpeed -= SPEED_FACTOR;
    }
    if (this.keyRight) {
      this.xSpeed += SPEED_FACTOR;
    }
    if (this.keyUp) {
      this.ySpeed -= SPEED_FACTOR;
    }
    if (this.keyDown) {
      this.ySpeed += SPEED_FACTOR;
    }
  }

  /**
   * Change position based on speed.
   */
  private void changePosition() {
    this.position.x += this.xSpeed;
    this.position.y += this.ySpeed;
  }

  /**
   * Draw method.
   */
  @Override
  public void draw() {
    this.window.stroke(0);
    this.window.fill(color.getRGB());
    this.window.strokeWeight(BoundingBox.TWO);
    this.window.rect(this.position.x - (this.width / BoundingBox.TWO),
            this.position.y - (this.width / BoundingBox.TWO), this.width, this.width);

    countSpeed();   // Change speed based on current keys pressed.
    changePosition(); // Change position based on speed.

    // Apply some drag so that the square wouldn't fly off the screen indefinitely
    this.xSpeed *= SPEED_DRAG_FACTOR;
    this.ySpeed *= SPEED_DRAG_FACTOR;

    outOfBoundsChecking();
    obstacleCollision();
    keyPressedOutOfBounds();
  }

  /**
   * OutOfBoundsChecking method.
   */
  private void outOfBoundsChecking() {
    if (isOutOfBounds()) {
      if (this.getBoundingBox().getTop() <= this.getWidth()) {
        this.position.y = this.getWidth();
        this.ySpeed *= -1;
      } else if (this.getBoundingBox().getBottom() >= this.window.height - this.getWidth()) {
        this.ySpeed *= -1;
        this.position.y = this.window.height - this.getWidth();
      }

      if (this.getBoundingBox().getRight() >= this.window.width - this.getWidth()) {
        this.xSpeed *= -1;
        this.position.x = this.window.width - this.getWidth();
      } else if (this.getBoundingBox().getLeft() <= this.getWidth()) {
        this.xSpeed *= -1;
        this.position.x = this.getWidth();
      }
    }
  }

  /**
   * Obstacle Collision detection.
   */
  private void obstacleCollision() {
    for (Obstacle obstacle : this.window.obstacles) {
      if (this.getBoundingBox().collides(obstacle.getBoundingBox())) {
        if (this.getBoundingBox().getTop() >= obstacle.getBoundingBox().getTop()
                && this.getBoundingBox().getBottom() > obstacle.getBoundingBox().getBottom()) {
          // playerTop Collides ObsBottom
          this.ySpeed *= -1;
          this.position.y = obstacle.getBoundingBox().getBottom()
                  + (this.getWidth() / BoundingBox.TWO);
        } else if (this.getBoundingBox().getTop() < obstacle.getBoundingBox().getTop()
                && this.getBoundingBox().getBottom() <= obstacle.getBoundingBox().getBottom()) {
          // playerBottom Collides ObsTop
          this.ySpeed *= -1;
          this.position.y = obstacle.getBoundingBox().getTop()
                  - (this.getWidth() / BoundingBox.TWO);
        } else if (this.getBoundingBox().getRight() <= obstacle.getBoundingBox().getRight()
                && this.getBoundingBox().getLeft() < obstacle.getBoundingBox().getLeft()) {
          // playerRight Collides ObsLeft
          this.xSpeed *= -1;
          this.position.x = obstacle.getBoundingBox().getLeft()
                  - (this.getWidth() / BoundingBox.TWO);
        } else if (this.getBoundingBox().getRight() > obstacle.getBoundingBox().getRight()
                && this.getBoundingBox().getLeft() >= obstacle.getBoundingBox().getLeft()) {
          // playerLeft Collides ObsRight
          this.xSpeed *= -1;
          this.position.x = obstacle.getBoundingBox().getRight()
                  + (this.getWidth() / BoundingBox.TWO);
        }
      }
    }
  }

  /**
   * KeyPressed is out of bounds checking method.
   */
  private void keyPressedOutOfBounds() {
    if (this.keyUp || this.keyDown) {
      if (this.isOutOfBounds()) {
        this.ySpeed *= -1;
        if (this.getBoundingBox().getTop() < this.getWidth()) {
          this.position.y = this.getWidth();
        } else if (this.getBoundingBox().getBottom() > this.window.height - this.getWidth()) {
          this.position.y = this.window.height - this.getWidth();
        }
      }
    }

    if (this.keyRight || this.keyLeft) {
      if (this.isOutOfBounds()) {
        this.xSpeed *= -1;
        this.ySpeed *= -1;
        if (this.getBoundingBox().getRight() > this.window.width - this.getWidth()) {
          this.position.x = this.window.width - this.getWidth();
        } else if (this.getBoundingBox().getLeft() < this.getWidth()) {
          this.position.x = this.getWidth();
        }
      }
    }
  }

  /**
   * Equals method.
   *
   * @param o for object
   * @return true if object is equals
   */
  @Override
  public boolean equals(Object o) {
    return super.equals(o);
  }

  /**
   * Hashcode of object.
   *
   * @return the hashcode value
   */
  @Override
  public int hashCode() {
    return super.hashCode();
  }

  /**
   * String value of the Player.
   *
   * @return string message
   */
  @Override
  public String toString() {
    return super.toString();
  }
}
