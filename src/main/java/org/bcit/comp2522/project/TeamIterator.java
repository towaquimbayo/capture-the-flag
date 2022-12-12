package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * TeamIterator class.
 *
 * @author towaquimbayo, alexgibbison
 * @version 1.0
 */
public class TeamIterator<AbstractCharacter> implements Iterator<AbstractCharacter> {
  /** ArrayList for iterator. */
  private final ArrayList<AbstractCharacter> teamIterate;
  /** Index value. */
  private int index;

  /**
   * Constructor for TeamIterator.
   *
   * @param team for Team ArrayList
   */
  public TeamIterator(ArrayList<AbstractCharacter> team) {
    this.teamIterate = team;
    this.index = 0;
  }

  /**
   * hasNext method checks if there
   * are anymore characters to be iterated.
   *
   * @return true if index is smaller than list size
   */
  @Override
  public boolean hasNext() {
    return (this.teamIterate.size() > this.index);
  }

  /**
   * Next method returns the AbstractCharacter object from the list
   * given from the index value for the ArrayList.
   *
   * @return AbstractCharacter
   */
  @Override
  public AbstractCharacter next() {
    AbstractCharacter character = this.teamIterate.get(this.index);
    this.index++;
    return character;
  }
}
