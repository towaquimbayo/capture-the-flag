package org.bcit.comp2522.project;

/**
 * AbstractCharacter class.
 * Defines methods for all characters.
 *
 * @author towaquimbayo, alexgibbison
 * @version 1.0
 */
public interface AbstractCharacter extends Drawable {

  BoundingBox getBoundingBox();

  @Override
  void draw();
}