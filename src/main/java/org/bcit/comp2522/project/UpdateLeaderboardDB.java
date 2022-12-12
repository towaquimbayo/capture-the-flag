package org.bcit.comp2522.project;

/**
 * UpdateLeaderboardDB class.
 *
 * @author towaquimbayo, alexgibbison
 * @version 1.0
 */
public class UpdateLeaderboardDB implements OnEventListener {

  /**
   * Async event handler.
   *
   * @param window for window
   */
  @Override
  public void onEvent(Window window) {
    window.updateLeaderboard();
  }
}