package org.bcit.comp2522.project;

/**
 * ScoreTracker class.
 *
 * @author towaquimbayo, alexgibbison
 * @version 1.0
 */
public class ScoreTracker implements Drawable, Comparable<ScoreTracker> {
  /** Score value. */
  private int score;
  /** Position X value. */
  private final float positionX;
  /** Position Y value. */
  private final float positionY;
  /** Window. */
  private final Window window;
  /** Twenty-five. */
  public static final float TWENTY_FIVE = 25f;

  /**
   * ScoreTracker Constructor.
   *
   * @param posX for x position
   * @param posY for y position
   * @param win for window
   */
  public ScoreTracker(float posX, float posY, Window win) {
    this.positionX = posX;
    this.positionY = posY;
    this.score = 0;
    this.window = win;
  }

  /**
   * Draw method.
   */
  @Override
  public void draw() {
    this.window.fill(BoundingBox.TWO_FIFTY_FIVE);
    this.window.textSize(TWENTY_FIVE);
    this.window.text(String.format("Score: %d", score), this.positionX, this.positionY);
  }

  /**
   * Getter for score.
   *
   * @return this.score
   */
  public int getScore() {
    return this.score;
  }

  /**
   * Update score counter.
   */
  public void updateScore() {
    this.score++;
  }

  /**
   * Comparable method for comparing opponent ScoreTracker.
   *
   * @param scoreTracker the object to be compared.
   * @return 1 if this score is higher, -1 if less, or 0 if equal
   */
  @Override
  public int compareTo(ScoreTracker scoreTracker) {
    if (this.score > scoreTracker.score) {
      return 1;
    } else if (this.score < scoreTracker.score) {
      return -1;
    }
    return 0;
  }
}
