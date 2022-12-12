package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.Iterator;
import processing.core.PVector;

/**
 * TeamCollection class for type AbstractCharacter.
 *
 * @author towaquimbayo, alexgibbison
 * @version 1.0
 */
public class TeamCollection<AbstractCharacter> implements Iterable<AbstractCharacter> {
  /** Team ArrayList. */
  private final ArrayList<AbstractCharacter> team;
  /** Flag object. */
  private Flag flag;
  /** Window. */
  private final Window window;
  /** ScoreTracker object. */
  private final ScoreTracker scoreTracker;
  /** Flag Width. */
  public static final float FLAG_WIDTH = 3f;
  /** Flag Height. */
  public static final float FLAG_HEIGHT = 30f;

  /**
   * TeamCollection Constructor.
   *
   * @param win for Window
   * @param homeX for Home X Position
   * @param scorePosX for Score position x
   * @param scorePosY for Score position y
   */
  public TeamCollection(Window win, float homeX, float scorePosX, float scorePosY) {
    this.team = new ArrayList<>();
    this.window = win;
    this.scoreTracker = new ScoreTracker(scorePosX, scorePosY, this.window);
    setFlagPosition(homeX);
  }

  /**
   * Getter for ScoreTracker.
   *
   * @return this.scoreTracker
   */
  public ScoreTracker getScoreTracker() {
    return this.scoreTracker;
  }

  /**
   * Setter for Flag Position.
   *
   * @param homeX for x home position
   */
  private void setFlagPosition(float homeX) {
    PVector home = new PVector(homeX, this.window.height / BoundingBox.TWO);
    this.flag = new Flag(home, home, FLAG_WIDTH, FLAG_HEIGHT, this.window);
  }

  /**
   * Add player method.
   *
   * @param character for Abstract character
   */
  public void addPlayer(AbstractCharacter character) {
    this.team.add(character);
  }

  /**
   * Reset player method.
   *
   * @param character for Abstract character
   */
  public void resetPlayer(AbstractCharacter character) {
    PVector resetPos = new PVector(this.flag.getHome().x, this.flag.getHome().y);
    if (character instanceof Player) {
      ((Player) character).setPosition(resetPos);
    }
  }

  /**
   * Getter for Flag.
   *
   * @return this.flag
   */
  public Flag getFlag() {
    return this.flag;
  }

  /**
   * Iterator for the arraylist.
   *
   * @return new TeamIterator
   */
  @Override
  public Iterator<AbstractCharacter> iterator() {
    return new TeamIterator<>(this.team);
  }
}
