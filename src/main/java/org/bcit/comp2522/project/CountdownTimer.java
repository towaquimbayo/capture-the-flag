package org.bcit.comp2522.project;

/**
 * CountdownTimer class.
 * Used as a three-second countdown timer when game starts.
 *
 * @author towaquimbayo, alexgibbison
 * @version 1.0
 */
public class CountdownTimer implements Drawable {
  /** Begin value for countdown. */
  private final int begin;
  /** Duration value for countdown. */
  private final int duration;
  /** Countdown decremented timer value for countdown. */
  private int time;
  /** Window. */
  private final Window window;
  /** Two. */
  public static final int TWO = 2;
  /** Maximum time. */
  public static final int MAX_TIME = 3;
  /** White RGB Value. */
  public static final float WHITE = 255f;
  /** Background Alpha Value. */
  public static final float ALPHA = 75f;
  /** Text Size. */
  public static final float TEXT_SIZE = 300f;
  /** Text position offset factor. */
  public static final float TEXT_POSITION_OFFSET = 100f;
  /** Timer conversion factor. */
  public static final int TIMER_CONVERSION = 1000;

  /**
   * Constructor for CountdownTimer.
   *
   * @param win for Window
   */
  public CountdownTimer(Window win) {
    this.window = win;
    this.begin = this.window.millis();
    this.duration = MAX_TIME;
    this.time = MAX_TIME;
  }

  /**
   * Getter method for Time.
   *
   * @return this.time
   */
  public int getTime() {
    return this.time;
  }

  /**
   * Draw the countdownTimer background on the Window.
   */
  private void drawCountdownBG() {
    if (this.time > 0 && (this.time % TWO == 0)) {
      this.window.fill(0, 0, 0, ALPHA);
      this.window.rect(0, 0, this.window.width, this.window.height);
    } else {
      this.window.fill(WHITE, WHITE, WHITE, ALPHA);
      this.window.rect(0, 0, this.window.width, this.window.height);
    }
  }

  /**
   * Draw method.
   */
  @Override
  public void draw() {
    if (this.time > 0) {
      drawCountdownBG();
      this.time = this.duration - (this.window.millis() - begin) / TIMER_CONVERSION;
      if (this.time % TWO == 0) {
        this.window.fill(WHITE, WHITE, WHITE);
      } else {
        this.window.fill(0, 0, 0);
      }
      this.window.textSize(TEXT_SIZE);
      this.window.text(this.time, this.window.width / ((float) TWO) - TEXT_POSITION_OFFSET,
              this.window.height / ((float) TWO) + ALPHA);
    }
  }
}
