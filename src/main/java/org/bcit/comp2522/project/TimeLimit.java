package org.bcit.comp2522.project;

/**
 * TimeLimit class.
 *
 * @author towaquimbayo, alexgibbison
 * @version 1.0
 */
public class TimeLimit implements Drawable {
  /** Begin value for time limit. */
  private final int begin;
  /** Duration value for time limit. */
  private final int duration;
  /** Timer decremented timer value for time limit. */
  private int time;
  /** Window. */
  private final Window window;
  /** Minute value. */
  public static final int MINUTE = 60;
  /** Text offset x value. */
  public static final float TEXT_OFFSET_X = 20f;
  /** Text offset y value. */
  public static final float TEXT_OFFSET_Y = 50f;
  /** Text Size. */
  public static final float TEXT_SIZE = 30f;

  /**
   * Time Limit Constructor.
   *
   * @param win for Window
   */
  public TimeLimit(Window win) {
    this.window = win;
    this.begin = this.window.millis();
    this.duration = MINUTE;
    this.time = MINUTE;
  }

  /**
   * Getter method for Time.
   *
   * @return this.time
   */
  public int getTime() {
    return this.time;
  }

  @Override
  public void draw() {
    if (this.time > 0) {
      this.time = this.duration - (this.window.millis() - begin) / CountdownTimer.TIMER_CONVERSION;
    }
    this.window.fill(BoundingBox.TWO_FIFTY_FIVE);
    this.window.textSize(TEXT_SIZE);
    this.window.text(String.format("%d%s", this.time, "s"),
            this.window.width / BoundingBox.TWO - TEXT_OFFSET_X, + TEXT_OFFSET_Y);
  }
}
