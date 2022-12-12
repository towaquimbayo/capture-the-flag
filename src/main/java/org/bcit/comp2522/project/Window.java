package org.bcit.comp2522.project;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import org.bson.Document;
import org.bson.json.JsonObject;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PVector;
import processing.data.JSONObject;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import processing.sound.SoundFile;

/**
 * Window class.
 *
 * @author towaquimbayo, alexgibbison
 * @version 1.0
 */
public class Window extends PApplet {
  /** Obstacles arraylist. */
  protected ArrayList<Obstacle> obstacles;
  /** Player 1. */
  private Player player;
  /** Player 2. */
  private Player player2;
  /** Team Red. */
  private TeamCollection<AbstractCharacter> teamRed;
  /** Team Blue. */
  private TeamCollection<AbstractCharacter> teamBlue;
  /** Color blue. */
  private Color blue;
  /** Color red. */
  private Color red;
  /** Countdown timer. */
  private CountdownTimer countdownTimer;
  /** Time limit. */
  private TimeLimit timeLimit;
  /** MongoDB Username. */
  private static final String USER = System.getenv("USER");
  /** MongoDB Password. */
  private static final String PASS = System.getenv("PASS");
  /** MongoDB Client connection. */
  private final MongoClient client = MongoClients.create("mongodb+srv://" + USER + ":" + PASS);
  /** MongoDB Database. */
  private final MongoDatabase database = client.getDatabase("leaderboard");
  /** MongoDB Collection. */
  private final MongoCollection<JsonObject> collection
          = database.getCollection("leaderboard", JsonObject.class);
  /** OnEvent Handler. */
  private OnEventListener onEventListener;
  /** TeamBlue Winner boolean condition. */
  private boolean teamBlueWinner;
  /** TeamRed Winner boolean condition. */
  private boolean teamRedWinner;
  /** Main Menu selection boolean condition. */
  private boolean menuSelected;
  /** Leaderboard Menu selection boolean condition. */
  private boolean leaderboardSelected;
  /** MousePointer. */
  private PVector mousePointer;
  /** Username Typing String. */
  private String userNameTyping;
  /** Entered username string value. */
  private String userNameTypingSaved;
  /** Disable condition for when user is typing. */
  private boolean disableTyping;
  /** Main menu background sound file. */
  private SoundFile menuBgMusic;
  /** Leaderboard menu background sound file. */
  private SoundFile leaderboardBgMusic;
  /** In Game menu background sound file. */
  private SoundFile inGameBgMusic;
  /** Countdown background sound file. */
  private SoundFile countDownBgMusic;
  /** Window Width. */
  public static final int WINDOW_WIDTH = 1280;
  /** Window Height. */
  public static final int WINDOW_HEIGHT = 720;

  public static final float FIVE = 5f;
  public static final float SEVEN = 7f;
  public static final float TEN = 10f;
  public static final float THIRTEEN = 13f;
  public static final float FIFTEEN = 15f;
  public static final float SIXTEEN = 16f;
  public static final float TWENTY = 20f;
  public static final float TWENTY_FIVE = 25f;
  public static final float THIRTY = 30f;
  public static final float FORTY_FIVE = 45f;
  public static final float FIFTY = 50f;
  public static final float SIXTY = 60f;
  public static final float SIXTY_THREE = 63f;
  public static final float SEVENTY_FIVE = 75f;
  public static final float EIGHTY = 80f;
  public static final float NINETY_TWO = 92f;
  public static final float HUNDRED = 100f;
  public static final float HUNDRED_FIVE = 105f;
  public static final float HUNDRED_TEN = 110f;
  public static final float HUNDRED_TWENTY = 120f;
  public static final float ONE_HUNDRED_THIRTY = 130f;
  public static final float ONE_HUNDRED_FORTY = 140f;
  public static final float ONE_HUNDRED_FORTY_THREE = 143f;
  public static final float HUNDRED_FIFTY = 150f;
  public static final float HUNDRED_SIXTY_TWO = 162f;
  public static final float HUNDRED_SEVENTY = 170f;
  public static final float ONE_HUNDRED_EIGHTY = 180f;
  public static final float TWO_HUNDRED = 200f;
  public static final float TWO_HUNDRED_TEN = 210f;
  public static final float TWO_HUNDRED_TWENTY_TWO = 222f;
  public static final float TWO_HUNDRED_THIRTY_TWO = 232f;
  public static final float TWO_HUNDRED_FORTY = 240f;
  public static final float TWO_HUNDRED_FIFTY = 250f;
  public static final float THREE_HUNDRED = 300f;
  public static final float THREE_HUNDRED_FIVE = 305f;
  public static final float THREE_HUNDRED_THIRTY_FIVE = 335f;
  public static final float THREE_HUNDRED_FIFTY = 350f;
  public static final float THREE_HUNDRED_FIFTY_FIVE = 355f;
  public static final float THREE_HUNDRED_SIXTY = 360f;
  public static final float THREE_HUNDRED_EIGHTY_FIVE = 385f;
  public static final float FOUR_HUNDRED = 400f;
  public static final float FOUR_HUNDRED_TWENTY_FIVE = 425f;
  public static final float FOUR_HUNDRED_THIRTY = 430f;
  public static final float FOUR_HUNDRED_FIFTY = 450f;
  public static final float FOUR_HUNDRED_EIGHTY = 480f;
  public static final float FOUR_HUNDRED_NINETY_THREE = 493f;
  public static final float FIVE_HUNDRED_TEN = 510f;
  public static final float FIVE_HUNDRED_TWENTY = 520f;
  public static final float FIVE_HUNDRED_FORTY = 540f;
  public static final float FIVE_HUNDRED_FORTY_FIVE = 545f;
  public static final float FIVE_HUNDRED_FIFTY = 550f;
  public static final float FIVE_HUNDRED_SIXTY = 560f;
  public static final float FIVE_HUNDRED_NINETY = 590f;
  public static final float SEVEN_HUNDRED_SEVENTY_SEVEN = 777f;
  public static final float SIX_HUNDRED_TEN = 610f;
  public static final float SIX_HUNDRED_TWENTY = 620f;
  public static final float SIX_HUNDRED_THIRTY = 630f;
  public static final float SIX_HUNDRED_FORTY = 640f;
  public static final float SIX_HUNDRED_THIRTY_FIVE = 635f;
  public static final float SIX_HUNDRED_SIXTY_FIVE = 665f;
  public static final float SEVEN_HUNDRED_FORTY_FIVE = 745f;
  public static final float SEVEN_HUNDRED_SEVENTY = 770f;
  public static final float EIGHT_HUNDRED = 800f;
  public static final float EIGHT_HUNDRED_FIVE = 805f;
  public static final float EIGHT_HUNDRED_SEVENTY_FIVE = 875f;
  public static final float THOUSAND_SIXTY_FIVE = 1065f;

  public static final int MAX_USERNAME_LENGTH = 20;
  public static final int SPACE_KEY = 32;
  public static final int BACKSLASH_KEY = 92;
  public static final int UNDERSCORE_KEY = 95;
  public static final int DELETE_KEY = 127;
  public static final int PLAYER_LEFT = 65;
  public static final int PLAYER_RIGHT = 68;
  public static final int PLAYER_UP = 87;
  public static final int PLAYER_DOWN = 83;
  public static final int MAX_COLLECTION_SIZE = 10;

  /**
   * Runs before applet starts.
   * Set up all objects.
   */
  public void setup() {
    teamBlueWinner = false;
    teamRedWinner = false;
    menuSelected = true;
    leaderboardSelected = false;
    userNameTyping = "";
    userNameTypingSaved = "";
    disableTyping = false;
    obstacles = new ArrayList<>();
    mousePointer = new PVector(0, 0);

    // Sound files
    menuBgMusic = new SoundFile(this, "src/media/menu.wav");
    leaderboardBgMusic = new SoundFile(this, "src/media/menu.wav");
    inGameBgMusic = new SoundFile(this, "src/media/ingame.wav");
    countDownBgMusic = new SoundFile(this, "src/media/countdown.wav");

    // Setup Team Colors
    blue = new Color(0, 0, BoundingBox.TWO_FIFTY_FIVE);
    red = new Color(BoundingBox.TWO_FIFTY_FIVE, 0, 0);

    setupObstacles();
    setupTeam();
  }

  /**
   * Set up the obstacles on the Window.
   */
  private void setupObstacles() {
    PVector obstaclePos;
    Obstacle obstacle;

    obstaclePos = new PVector(this.width / BoundingBox.TWO,
            this.height / BoundingBox.TWO - HUNDRED_FIFTY);
    obstacle = new Obstacle(SEVENTY_FIVE, HUNDRED_TWENTY, obstaclePos, this);
    obstacles.add(obstacle);

    obstaclePos = new PVector(this.width / BoundingBox.TWO,
            this.height / BoundingBox.TWO - TWO_HUNDRED_FORTY);
    obstacle = new Obstacle(THREE_HUNDRED, SEVENTY_FIVE, obstaclePos, this);
    obstacles.add(obstacle);

    obstaclePos = new PVector(this.width / BoundingBox.TWO,
            this.height / BoundingBox.TWO + HUNDRED_FIFTY);
    obstacle = new Obstacle(SEVENTY_FIVE, HUNDRED_TWENTY, obstaclePos, this);
    obstacles.add(obstacle);

    obstaclePos = new PVector(this.width / BoundingBox.TWO,
            this.height / BoundingBox.TWO + TWO_HUNDRED_FORTY);
    obstacle = new Obstacle(THREE_HUNDRED, SEVENTY_FIVE, obstaclePos, this);
    obstacles.add(obstacle);

    obstaclePos = new PVector(this.width / BoundingBox.TWO / BoundingBox.TWO,
            this.height / BoundingBox.TWO);
    obstacle = new Obstacle(HUNDRED_TWENTY, TWO_HUNDRED, obstaclePos, this);
    obstacles.add(obstacle);

    obstaclePos = new PVector(this.width / BoundingBox.TWO + this.width
            / BoundingBox.TWO / BoundingBox.TWO, this.height / BoundingBox.TWO);
    obstacle = new Obstacle(HUNDRED_TWENTY, TWO_HUNDRED, obstaclePos, this);
    obstacles.add(obstacle);
  }

  /**
   * Set up the team and players.
   */
  private void setupTeam() {
    teamBlue = new TeamCollection<>(this, SEVENTY_FIVE, FIFTY, FIFTY);
    teamRed = new TeamCollection<>(this, this.width - SEVENTY_FIVE,
            this.width - ONE_HUNDRED_FORTY, FIFTY);

    PVector teamBluePos = new PVector(TWO_HUNDRED, this.height / BoundingBox.TWO);
    player = new Player(teamBluePos, blue, this);
    teamBlue.addPlayer(player);

    PVector teamRedPos = new PVector(this.width - TWO_HUNDRED, this.height / BoundingBox.TWO);
    player2 = new Player(teamRedPos, red, this);
    teamRed.addPlayer(player2);
  }

  /**
   * Register the event listener for updating leaderboard.
   *
   * @param eventListener for OnEventListener
   */
  public void registerOnEventListener(OnEventListener eventListener) {
    onEventListener = eventListener;
  }

  /**
   * Synchronous task handler.
   */
  public void doStuff() {
    if (onEventListener != null) {
      onEventListener.onEvent(this);
    }
  }

  /**
   * Drawing Enemy Background to Window.
   */
  private void drawEnemyBg() {
    noStroke();
    fill(red.getRGB());
    rect(this.width / BoundingBox.TWO, 0, WINDOW_WIDTH / BoundingBox.TWO, WINDOW_HEIGHT);
  }

  /**
   * Main Menu screen.
   */
  public void mainMenuScreen() {
    PImage mainMenuBg = loadImage("src/media/mainMenuBG.jpg");
    image(mainMenuBg, 0, 0);
    fill(BoundingBox.TWO_FIFTY_FIVE);
    PFont title = createFont("src/fonts/Montserrat-Medium.ttf", SIXTY);
    textFont(title);
    text(String.format("%s", "CAPTURE THE FLAG"), HUNDRED,
            this.height / BoundingBox.TWO + SEVENTY_FIVE);

    PFont menuButtons = createFont("src/fonts/Montserrat-Medium.ttf", TWENTY);
    textFont(menuButtons);

    fill(HUNDRED_FIVE, HUNDRED_FIVE, HUNDRED_FIVE, EIGHTY);
    rect(HUNDRED, this.height / BoundingBox.TWO + ONE_HUNDRED_THIRTY, HUNDRED_FIFTY, FIFTY);
    fill(BoundingBox.TWO_FIFTY_FIVE);
    text(String.format("%s", "PLAY"), HUNDRED_FIFTY,
            this.height / BoundingBox.TWO + HUNDRED_SIXTY_TWO);

    fill(HUNDRED_FIVE, HUNDRED_FIVE, HUNDRED_FIVE, EIGHTY);
    rect(HUNDRED, this.height / BoundingBox.TWO + TWO_HUNDRED, TWO_HUNDRED_FIFTY, FIFTY);
    fill(BoundingBox.TWO_FIFTY_FIVE);
    text(String.format("%s", "LEADERBOARD"), HUNDRED_FIFTY,
            this.height / BoundingBox.TWO + TWO_HUNDRED_THIRTY_TWO);
  }

  /**
   * Game Over screen.
   */
  private void gameOverScreen() {
    fill(0, 0, FORTY_FIVE, FIVE);
    rect(0, 0, width, height);

    if (teamBlue.getScoreTracker().compareTo(teamRed.getScoreTracker()) > 0) {
      teamBlueWinner = true;
      gameEnd("Blue");
    } else if (teamBlue.getScoreTracker().compareTo(teamRed.getScoreTracker()) < 0) {
      teamRedWinner = true;
      gameEnd("Red");
    } else {
      gameEnd("No");
    }

    // Username entry
    String usernameMsg = "ENTER USERNAME:";
    textSize(TWENTY);
    fill(BoundingBox.TWO_FIFTY_FIVE);
    if (!disableTyping) {
      text(String.format("%s", usernameMsg), THREE_HUNDRED_SIXTY, THREE_HUNDRED_EIGHTY_FIVE);
    } else {
      usernameMsg = "USERNAME IS SAVED";
      text(String.format("%s", usernameMsg), THREE_HUNDRED_THIRTY_FIVE, THREE_HUNDRED_EIGHTY_FIVE);
    }
    fill(SIXTY_THREE);
    rect(SIX_HUNDRED_TEN, THREE_HUNDRED_FIFTY_FIVE, THREE_HUNDRED_FIVE, FIFTY);
    fill(BoundingBox.TWO_FIFTY_FIVE);
    textSize(SIXTEEN);
    text(userNameTyping, SIX_HUNDRED_THIRTY, THREE_HUNDRED_EIGHTY_FIVE);

    // Buttons
    fill(SIXTY_THREE);
    rect(FOUR_HUNDRED_TWENTY_FIVE, FIVE_HUNDRED_TEN, TWO_HUNDRED_TEN, FIFTY);
    fill(BoundingBox.TWO_FIFTY_FIVE);
    text(String.format("%s", "PLAY AGAIN"), FOUR_HUNDRED_EIGHTY, FIVE_HUNDRED_FORTY);

    fill(SIXTY_THREE);
    rect(SIX_HUNDRED_SIXTY_FIVE, FIVE_HUNDRED_TEN, TWO_HUNDRED_TEN, FIFTY);
    fill(BoundingBox.TWO_FIFTY_FIVE);
    text(String.format("%s", "QUIT"), SEVEN_HUNDRED_FORTY_FIVE, FIVE_HUNDRED_FORTY);

    fill(SIXTY_THREE);
    rect(FOUR_HUNDRED_TWENTY_FIVE, FIVE_HUNDRED_NINETY, FOUR_HUNDRED_FIFTY, FIFTY);
    fill(BoundingBox.TWO_FIFTY_FIVE);
    text(String.format("%s", "VIEW LEADERBOARDS"), FIVE_HUNDRED_FIFTY, SIX_HUNDRED_TWENTY);

    // Hovering
    if (mouseX >= FOUR_HUNDRED_TWENTY_FIVE && mouseX <= SIX_HUNDRED_THIRTY_FIVE
            && mouseY >= FIVE_HUNDRED_TEN && mouseY <= FIVE_HUNDRED_SIXTY) {
      // For Play Again Button
      fill(BoundingBox.TWO_FIFTY_FIVE, 0, 0);
      rect(FOUR_HUNDRED_TWENTY_FIVE, FIVE_HUNDRED_TEN, TEN, FIFTY);
    } else if (mouseX >= SIX_HUNDRED_SIXTY_FIVE && mouseX <= EIGHT_HUNDRED_SEVENTY_FIVE
            && mouseY >= FIVE_HUNDRED_TEN && mouseY <= FIVE_HUNDRED_SIXTY) {
      // For QUIT Button
      fill(BoundingBox.TWO_FIFTY_FIVE, 0, 0);
      rect(SIX_HUNDRED_SIXTY_FIVE, FIVE_HUNDRED_TEN, TEN, FIFTY);
    } else if (mouseX >= FOUR_HUNDRED_TWENTY_FIVE && mouseX <= EIGHT_HUNDRED_SEVENTY_FIVE
            && mouseY >= FIVE_HUNDRED_NINETY && mouseY <= SIX_HUNDRED_FORTY) {
      // For LEADERBOARDS Button
      fill(BoundingBox.TWO_FIFTY_FIVE, 0, 0);
      rect(FOUR_HUNDRED_TWENTY_FIVE, FIVE_HUNDRED_NINETY, TEN, FIFTY);
    }

    // Clicked
    if (mousePointer.x >= FOUR_HUNDRED_TWENTY_FIVE
            && mousePointer.x <= SIX_HUNDRED_THIRTY_FIVE
            && mousePointer.y >= FIVE_HUNDRED_TEN
            && mousePointer.y <= FIVE_HUNDRED_SIXTY) {
      // For Play Again Button
      menuSelected = true;
      setup();
    } else if (mousePointer.x >= SIX_HUNDRED_SIXTY_FIVE
            && mousePointer.x <= EIGHT_HUNDRED_SEVENTY_FIVE
            && mousePointer.y >= FIVE_HUNDRED_TEN
            && mousePointer.y <= FIVE_HUNDRED_SIXTY) {
      // For QUIT Button
      exit();
    } else if (mousePointer.x >= FOUR_HUNDRED_TWENTY_FIVE
            && mousePointer.x <= EIGHT_HUNDRED_SEVENTY_FIVE
            && mousePointer.y >= FIVE_HUNDRED_NINETY
            && mousePointer.y <= SIX_HUNDRED_FORTY) {
      // For LEADERBOARDS Button
      leaderboardSelected = true;
    }
  }

  /**
   * Key Typed method for Game Over screen.
   */
  @Override
  public void keyTyped() {
    super.keyTyped();
    if (((key >= 'A' && key <= 'z') || (key >= '0' && key <= '9')
            || key == '\n' || key == DELETE_KEY || key == SPACE_KEY || key == UNDERSCORE_KEY
            || key == BACKSPACE) && key != BACKSLASH_KEY) {
      if (!disableTyping && key == '\n') {
        if (userNameTyping.trim().equals("")) {
          userNameTyping = "";
        } else {
          userNameTypingSaved = userNameTyping;
          disableTyping = true;

          // Update database
          OnEventListener eventListener = new UpdateLeaderboardDB();
          registerOnEventListener(eventListener);
          doStuff();
        }
      } else if (key == SPACE_KEY) {
        userNameTyping += "_";
      } else if (!disableTyping && (key == DELETE_KEY || key == BACKSPACE)) {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < userNameTyping.length() - 1; i++) {
          temp.append(userNameTyping.charAt(i));
        }
        userNameTyping = String.valueOf(temp);
      } else {
        if (!disableTyping && userNameTyping.length() < MAX_USERNAME_LENGTH) {
          userNameTyping = (userNameTyping + key).toUpperCase();
        }
      }
    }
  }

  /**
   * Game ending Set up.
   *
   * @param teamName for team winner
   */
  public void gameEnd(String teamName) {
    PFont winnerTitle = createFont("src/fonts/Montserrat-Medium.ttf", EIGHTY);
    textFont(winnerTitle);
    fill(BoundingBox.TWO_FIFTY_FIVE);
    if (teamName.equals("Blue")) {
      text(String.format("%s", "TEAM "), TWO_HUNDRED_TWENTY_TWO, TWO_HUNDRED);
      fill(0, 0, BoundingBox.TWO_FIFTY_FIVE);
      text(String.format("%s", teamName.toUpperCase()), FIVE_HUNDRED_TWENTY, TWO_HUNDRED);
      fill(BoundingBox.TWO_FIFTY_FIVE);
      text(String.format("%s", " WINS"), EIGHT_HUNDRED_FIVE, TWO_HUNDRED);
    } else if (teamName.equals("Red")) {
      text(String.format("%s", "TEAM "), TWO_HUNDRED_FIFTY, TWO_HUNDRED);
      fill(BoundingBox.TWO_FIFTY_FIVE, 0, 0);
      text(String.format("%s", teamName.toUpperCase()), FIVE_HUNDRED_FORTY_FIVE, TWO_HUNDRED);
      fill(BoundingBox.TWO_FIFTY_FIVE);
      text(String.format("%s", " WINS"), SEVEN_HUNDRED_SEVENTY_SEVEN, TWO_HUNDRED);
    } else {
      fill(TWO_HUNDRED_TEN, HUNDRED_SEVENTY, TWENTY_FIVE);
      text(String.format("%s", "DRAW"), FOUR_HUNDRED_NINETY_THREE, TWO_HUNDRED);
    }
  }

  /**
   * Leaderboard screen.
   */
  private void leaderboardScreen() {
    background(0, 0, FORTY_FIVE);
    fill(BoundingBox.TWO_FIFTY_FIVE);

    // Title
    PFont title = createFont("src/fonts/Montserrat-Medium.ttf", FIFTY);
    textFont(title);
    text(String.format("%s", "LEADERBOARD"), FOUR_HUNDRED_THIRTY, HUNDRED);
    PImage titleUnderline = loadImage("src/media/leaderboardTitle.png");
    image(titleUnderline, FOUR_HUNDRED, ONE_HUNDRED_THIRTY, FOUR_HUNDRED_EIGHTY, SEVEN);

    // Menu Button
    PFont returnToMenuBtn = createFont("src/fonts/Montserrat-Medium.ttf", TWENTY);
    textFont(returnToMenuBtn);

    fill(HUNDRED_FIVE, HUNDRED_FIVE, HUNDRED_FIVE, EIGHTY);
    rect(HUNDRED, SIXTY, HUNDRED_FIFTY, FIFTY);
    fill(BoundingBox.TWO_FIFTY_FIVE);
    text(String.format("%s", "MENU"), ONE_HUNDRED_FORTY_THREE, NINETY_TWO);

    // Load the table
    float userRowPosX = ONE_HUNDRED_EIGHTY;
    float userRowPosY = TWO_HUNDRED_TEN;

    if (collection.countDocuments() == 0) {
      fill(BoundingBox.TWO_FIFTY_FIVE);
      textSize(SIXTEEN);
      text(String.format("%s", "No users currently on the leaderboard!"),
              userRowPosX + THREE_HUNDRED, userRowPosY - FIFTEEN);
    } else {
      fill(BoundingBox.TWO_FIFTY_FIVE);
      textSize(SIXTEEN);
      text(String.format("%s", "RANK"), userRowPosX, userRowPosY - FIFTEEN);
      stroke(BoundingBox.TWO_FIFTY_FIVE, 0, 0);
      line(userRowPosX, userRowPosY, userRowPosX + THIRTY, userRowPosY);
      noStroke();

      text(String.format("%s", "USERNAME"), userRowPosX + HUNDRED, userRowPosY - FIFTEEN);
      stroke(BoundingBox.TWO_FIFTY_FIVE, 0, 0);
      line(userRowPosX + HUNDRED, userRowPosY, userRowPosX + ONE_HUNDRED_THIRTY, userRowPosY);
      noStroke();

      text(String.format("%s", "SCORE"), userRowPosX + FIVE_HUNDRED_SIXTY, userRowPosY - FIFTEEN);
      stroke(BoundingBox.TWO_FIFTY_FIVE, 0, 0);
      line(userRowPosX + FIVE_HUNDRED_SIXTY, userRowPosY,
              userRowPosX + FIVE_HUNDRED_NINETY, userRowPosY);
      noStroke();

      text(String.format("%s", "DATE"), userRowPosX + SEVEN_HUNDRED_SEVENTY, userRowPosY - FIFTEEN);
      stroke(BoundingBox.TWO_FIFTY_FIVE, 0, 0);
      line(userRowPosX + SEVEN_HUNDRED_SEVENTY, userRowPosY,
              userRowPosX + EIGHT_HUNDRED, userRowPosY);
      noStroke();

      float yPosIncrement = 0;
      for (int i = 0; i < collection.countDocuments(); i++) {
        fill(HUNDRED_FIVE, HUNDRED_FIVE, HUNDRED_FIVE, EIGHTY);
        rect(HUNDRED, TWO_HUNDRED_FORTY + yPosIncrement, THOUSAND_SIXTY_FIVE, FORTY_FIVE);
        fill(BoundingBox.TWO_FIFTY_FIVE);
        textSize(THIRTEEN);

        JSONObject user = parseJSONObject(Objects.requireNonNull(
                collection.find(new Document("id", i)).first()).getJson());
        text(String.format("%s", user.getInt("rank")),
                userRowPosX, userRowPosY + SIXTY + yPosIncrement);
        text(String.format("%s", user.getString("name").toUpperCase()),
                userRowPosX + HUNDRED, userRowPosY + SIXTY + yPosIncrement);
        text(String.format("%s", user.getInt("score")),
                userRowPosX + FIVE_HUNDRED_SIXTY, userRowPosY + SIXTY + yPosIncrement);
        text(String.format("%s", user.getString("date played")),
                userRowPosX + SEVEN_HUNDRED_SEVENTY, userRowPosY + SIXTY + yPosIncrement);

        yPosIncrement += FORTY_FIVE;
      }
    }
  }

  /**
   * Mouse clicked method.
   *
   * @param event for MouseEvent
   */
  @Override
  public void mouseClicked(MouseEvent event) {
    super.mouseClicked(event);
    mousePointer = new PVector(mouseX, mouseY);
  }

  /**
   * Draw the characters on window,
   * runs on each frame.
   */
  public void draw() {
    noStroke();
    if (menuSelected) {
      mainMenuScreen();

      if (!menuBgMusic.isPlaying()) {
        menuBgMusic.play();
        menuBgMusic.loop();
      } else if (leaderboardBgMusic.isPlaying()) {
        leaderboardBgMusic.stop();
      } else if (countDownBgMusic.isPlaying()) {
        countDownBgMusic.stop();
      } else if (inGameBgMusic.isPlaying()) {
        inGameBgMusic.stop();
      }

      // Hovering
      if (mouseX >= HUNDRED && mouseX <= TWO_HUNDRED_FIFTY
              && mouseY >= (this.height / BoundingBox.TWO + ONE_HUNDRED_THIRTY)
              && mouseY <= (this.height / BoundingBox.TWO + ONE_HUNDRED_EIGHTY)) {
        fill(BoundingBox.TWO_FIFTY_FIVE, 0, 0);
        rect(HUNDRED, height / BoundingBox.TWO + ONE_HUNDRED_THIRTY, TEN, FIFTY);
      } else if (mouseX >= HUNDRED && mouseX <= THREE_HUNDRED_FIFTY
              && mouseY >= (this.height / BoundingBox.TWO + TWO_HUNDRED)
              && mouseY <= (this.height / BoundingBox.TWO + TWO_HUNDRED_FIFTY)) {
        fill(BoundingBox.TWO_FIFTY_FIVE, 0, 0);
        rect(HUNDRED, height / BoundingBox.TWO + TWO_HUNDRED, TEN, FIFTY);
      }

      // Clicked
      if (mousePointer.x >= HUNDRED && mousePointer.x <= TWO_HUNDRED_FIFTY
              && mousePointer.y >= (this.height / BoundingBox.TWO + ONE_HUNDRED_THIRTY)
              && mousePointer.y <= (this.height / BoundingBox.TWO + ONE_HUNDRED_EIGHTY)) {
        menuSelected = false;
        countdownTimer = new CountdownTimer(this);
      } else if (mousePointer.x >= HUNDRED && mousePointer.x <= THREE_HUNDRED_FIFTY
              && mousePointer.y >= (this.height / BoundingBox.TWO + TWO_HUNDRED)
              && mousePointer.y <= (this.height / BoundingBox.TWO + TWO_HUNDRED_FIFTY)) {
        leaderboardSelected = true;
        menuSelected = false;
      }
    } else if (leaderboardSelected) {
      leaderboardScreen();

      if (menuBgMusic.isPlaying()) {
        menuBgMusic.stop();
      } else if (!leaderboardBgMusic.isPlaying()) {
        leaderboardBgMusic.play();
        leaderboardBgMusic.loop();
      } else if (countDownBgMusic.isPlaying()) {
        countDownBgMusic.stop();
      } else if (inGameBgMusic.isPlaying()) {
        inGameBgMusic.stop();
      }

      // Hovering
      if (mouseX >= HUNDRED && mouseX <= TWO_HUNDRED_FIFTY
              && mouseY >= SIXTY && mouseY <= HUNDRED_TEN) {
        fill(BoundingBox.TWO_FIFTY_FIVE, 0, 0);
        rect(HUNDRED, SIXTY, TEN, FIFTY);
      }

      // Clicked Menu
      if (mousePointer.x >= HUNDRED && mousePointer.x <= TWO_HUNDRED_FIFTY
              && mousePointer.y >= SIXTY && mousePointer.y <= HUNDRED_TEN) {
        leaderboardBgMusic.stop();
        leaderboardSelected = false;
        menuSelected = true;
        setup();
      }
    } else if (countdownTimer.getTime() != 0) {
      if (menuBgMusic.isPlaying()) {
        menuBgMusic.stop();
      } else if (leaderboardBgMusic.isPlaying()) {
        leaderboardBgMusic.stop();
      } else if (inGameBgMusic.isPlaying()) {
        inGameBgMusic.stop();
      } else if (!countDownBgMusic.isPlaying()) {
        countDownBgMusic.play();
        countDownBgMusic.loop();
      }
      countdownTimer.draw();
      timeLimit = new TimeLimit(this);
    } else {
      if (timeLimit.getTime() == 0) {
        if (menuBgMusic.isPlaying()) {
          menuBgMusic.stop();
        } else if (leaderboardBgMusic.isPlaying()) {
          leaderboardBgMusic.stop();
        } else if (countDownBgMusic.isPlaying()) {
          countDownBgMusic.stop();
        } else if (inGameBgMusic.isPlaying()) {
          inGameBgMusic.stop();
        }
        gameOverScreen();
      } else {
        if (menuBgMusic.isPlaying()) {
          menuBgMusic.stop();
        } else if (leaderboardBgMusic.isPlaying()) {
          leaderboardBgMusic.stop();
        } else if (countDownBgMusic.isPlaying()) {
          countDownBgMusic.stop();
        } else if (!inGameBgMusic.isPlaying()) {
          inGameBgMusic.play();
          inGameBgMusic.loop();
        }

        userNameTyping = "";
        userNameTypingSaved = "";
        background(blue.getRGB());
        drawEnemyBg();

        for (Obstacle obstacle : obstacles) {
          obstacle.draw();
        }

        teamRed.getFlag().draw();
        teamBlue.getFlag().draw();

        for (AbstractCharacter character : teamRed) {
          character.draw();
        }

        for (AbstractCharacter character : teamBlue) {
          character.draw();
        }

        // Collide behavior for Blue Team when Blue player touches Red Player -> Resets Pos
        for (AbstractCharacter blue : teamBlue) {
          for (AbstractCharacter red : teamRed) {
            // Collision between Red and Blue Players
            // Player who is slower would get reset its position
            if (blue.getBoundingBox().collides(red.getBoundingBox())) {
              if (blue instanceof Player && red instanceof Player) {
                float blueSpeedX = Math.abs(((Player) blue).getXSpeed());
                float blueSpeedY = Math.abs(((Player) blue).getYSpeed());
                float redSpeedX = Math.abs(((Player) red).getXSpeed());
                float redSpeedY = Math.abs(((Player) red).getYSpeed());
                if (blueSpeedX + blueSpeedY > redSpeedX + redSpeedY) {
                  teamRed.resetPlayer(red);
                } else {
                  teamBlue.resetPlayer(blue);
                }
              }
            }
          }
        }

        teamCollideFlagBehavior(teamRed, teamBlue);
        teamCollideFlagBehavior(teamBlue, teamRed);

        teamRed.getScoreTracker().draw();
        teamBlue.getScoreTracker().draw();
        timeLimit.draw();
      }
    }
  }

  /**
   * Team Collision behavior detection.
   *
   * @param team for Team 1
   * @param enemy for Team 2
   */
  private void teamCollideFlagBehavior(TeamCollection<AbstractCharacter> team,
                                       TeamCollection<AbstractCharacter> enemy) {
    for (AbstractCharacter teamPlayer : team) {
      // If team collides Enemy Flag, then pick up the flag
      if (teamPlayer.getBoundingBox().collides(enemy.getFlag().getBoundingBox())) {
        if (teamPlayer instanceof Player) {
          enemy.getFlag().setPosition(((Player) teamPlayer).getPosition());
          ((Player) teamPlayer).hasFlag = true;
        }
      } else {
        if (teamPlayer instanceof Player) {
          ((Player) teamPlayer).hasFlag = false;
        }
      }

      if (teamPlayer.getBoundingBox().collides(
              team.getFlag().getBoundingBox())) { // collide with own flag
        // If team's flag is out of position and tPlayer player touches their flag, then reset flag
        if (!(team.getFlag().getPosition().equals(team.getFlag().getHome()))) {
          team.getFlag().setPosition(team.getFlag().getHome());
        }

        // If team has enemy flag and touches home flag, then increment 1 point and reset enemy flag
        if (teamPlayer instanceof Player && ((Player) teamPlayer).hasFlag) {
          team.getScoreTracker().updateScore();
          enemy.getFlag().setPosition(enemy.getFlag().getHome());
        }
      }
    }
  }

  /**
   * Key Pressed function for players to move.
   *
   * @param event for KeyEvent
   */
  @Override
  public void keyPressed(KeyEvent event) {
    super.keyPressed(event);

    // Player 1
    if (event.getKeyCode() == PLAYER_RIGHT) { // RIGHT
      player.setKeyRight(true);
    }
    if (event.getKeyCode() == PLAYER_LEFT) { // LEFT
      player.setKeyLeft(true);
    }
    if (event.getKeyCode() == PLAYER_UP) { // UP
      player.setKeyUp(true);
    }
    if (event.getKeyCode() == PLAYER_DOWN) { // DOWN
      player.setKeyDown(true);
    }

    // Player 2
    if (event.getKeyCode() == RIGHT) { // RIGHT
      player2.setKeyRight(true);
    }
    if (event.getKeyCode() == LEFT) { // LEFT
      player2.setKeyLeft(true);
    }
    if (event.getKeyCode() == UP) { // UP
      player2.setKeyUp(true);
    }
    if (event.getKeyCode() == DOWN) { // DOWN
      player2.setKeyDown(true);
    }
  }

  /**
   * KeyReleased function for Players to move.
   *
   * @param event for KeyEvent
   */
  @Override
  public void keyReleased(KeyEvent event) {
    super.keyReleased(event);

    // Player 1
    if (event.getKeyCode() == PLAYER_RIGHT) { // RIGHT
      player.setKeyRight(false);
    }
    if (event.getKeyCode() == PLAYER_LEFT) { // LEFT
      player.setKeyLeft(false);
    }
    if (event.getKeyCode() == PLAYER_UP) { // UP
      player.setKeyUp(false);
    }
    if (event.getKeyCode() == PLAYER_DOWN) { // DOWN
      player.setKeyDown(false);
    }

    // Player 2
    if (event.getKeyCode() == RIGHT) { // RIGHT
      player2.setKeyRight(false);
    }
    if (event.getKeyCode() == LEFT) { // LEFT
      player2.setKeyLeft(false);
    }
    if (event.getKeyCode() == UP) { // UP
      player2.setKeyUp(false);
    }
    if (event.getKeyCode() == DOWN) { // DOWN
      player2.setKeyDown(false);
    }
  }

  /**
   * UpdateLeaderboard method.
   */
  public void updateLeaderboard() {
    int score;
    if (teamRedWinner) {
      score = teamRed.getScoreTracker().getScore();
    } else if (teamBlueWinner) {
      score = teamBlue.getScoreTracker().getScore();
    } else {
      System.out.println("You couldn't even win man c'mon... "
              + "get better bro. Win to place on leaderboard so, play again!");
      return;
    }

    String username = userNameTypingSaved.toUpperCase();
    int ranking = getRanking(score);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    String date = dtf.format(LocalDateTime.now());
    int position;
    int oldRank = -1;
    boolean isUserExist = false;

    if (userExists(username) != -1) {
      JSONObject existingUser = parseJSONObject(Objects.requireNonNull(
              collection.find(new Document("id", userExists(username))).first()).getJson());
      if (existingUser.getInt("score") < score) {
        isUserExist = true;
        oldRank = existingUser.getInt("rank");
        collection.updateMany(Filters.eq("id", userExists(username)),
            Updates.combine(
                Updates.set("score", score),
                Updates.set("date played", date),
                Updates.set("rank", ranking)));
        position = userExists(username);
      } else {
        System.out.println(username.charAt(0) + username.substring(1).toLowerCase()
                + " ... you did better last time. Play again!");
        return; // don't update if new score is not greater than old score
      }
    } else {
      Document newUser = new Document();
      if (((int) collection.countDocuments()) == MAX_COLLECTION_SIZE) {
        newUser.append("id", ((int) collection.countDocuments()) - 1);
      } else {
        newUser.append("id", ((int) collection.countDocuments()));
      }
      newUser.append("rank", ranking);
      newUser.append("score", score);
      newUser.append("name", username);
      newUser.append("date played", date);

      // Store only top 10 users, if user has less or
      // equal score than last user in the leaderboard then don't store
      // However, if the user has higher score than last user
      if (((int) collection.countDocuments()) == MAX_COLLECTION_SIZE) {
        if (score <= parseJSONObject(Objects.requireNonNull(
                collection.find(new Document("id",
                        ((int) collection.countDocuments()) - 1)).first())
                .getJson()).getInt("score")) {
          System.out.println(username.charAt(0) + username.substring(1).toLowerCase()
                  + " ... you scored below the top 10 unfortunately... "
                  + "get better bro. Play again!");
          return;
        } else {
          // Remove last user and replace with new user in the last index
          collection.deleteOne(Filters.eq("id", ((int) collection.countDocuments()) - 1));
          //Inserting the document into the collection
          database.getCollection("leaderboard").insertOne(newUser);
        }
      } else {
        database.getCollection("leaderboard").insertOne(newUser);
      }
      position = ((int) collection.countDocuments()) - 1;
    }

    sortRanking(position);

    if (isUserExist) {
      System.out.println("\nCongratulations! " + username
              + " has been moved from rank " + oldRank + " to rank " + ranking + "\n");
    } else {
      System.out.println("\nCongratulations! " + username
              + " has been updated to rank " + ranking + "\n");
    }
  }

  /**
   * Sorting the rankings on the database.
   *
   * @param position for position on the leaderboard
   */
  private void sortRanking(int position) {
    JSONObject currUser = parseJSONObject(Objects.requireNonNull(
            collection.find(new Document("id", position)).first()).getJson());

    for (int i = 0; i < position; i++) {
      JSONObject parsedUser = parseJSONObject(Objects.requireNonNull(
              collection.find(new Document("id", i)).first()).getJson());
      if (parsedUser.getInt("score") < currUser.getInt("score")) {
        for (int j = position; j > i; j--) {
          JSONObject shiftedUser = parseJSONObject(Objects.requireNonNull(
                  collection.find(new Document("id", j - 1)).first()).getJson());
          collection.updateMany(Filters.eq("id", j),
              Updates.combine(
                  Updates.set("score", shiftedUser.getInt("score")),
                  Updates.set("name", shiftedUser.getString("name")),
                  Updates.set("date played", shiftedUser.getString("date played")),
                  Updates.set("id", shiftedUser.getInt("id") + 1),
                  Updates.set("rank", j + 1)));
        }

        collection.updateMany(Filters.eq("id", i),
            Updates.combine(
                Updates.set("score", currUser.getInt("score")),
                Updates.set("name", currUser.getString("name")),
                Updates.set("date played", currUser.getString("date played")),
                Updates.set("id", i),
                Updates.set("rank", i + 1)));
        i = position;
      }
    }
  }

  /**
   * Check if username already exists.
   *
   * @param user for username entered
   * @return index if found, -1 if it doesn't exist
   */
  private int userExists(String user) {
    for (int i = 0; i < collection.countDocuments(); i++) {
      JSONObject parsedUser = parseJSONObject(Objects.requireNonNull(
              collection.find(new Document("id", i)).first()).getJson());
      if (parsedUser.getString("name").equals(user)) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Get the ranking of the user from database.
   *
   * @param score score from user
   * @return new ranking
   */
  private int getRanking(int score) {
    for (int i = 0; i < collection.countDocuments(); i++) {
      JSONObject parsedUser = parseJSONObject(Objects.requireNonNull(
              collection.find(new Document("id", i)).first()).getJson());
      if (parsedUser.getInt("score") < score) {
        return i + 1;
      }
    }
    return (int) (collection.countDocuments() + 1);
  }

  /**
   * The size of the window.
   */
  public void settings() {
    size(WINDOW_WIDTH, WINDOW_HEIGHT);
  }

  /**
   * Main function.
   *
   * @param passedArgs arguments from command line
   */
  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[]{"window"};
    Window window = new Window();
    PApplet.runSketch(appletArgs, window);
  }
}
