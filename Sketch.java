import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
	
  PImage movingItem;
  PImage cursor;
  PImage runner;
  PImage finishLine;

  float fltImgX = 500;
  float fltImgY = 285;

  float fltCursorX = 500;
  float fltCursorY = 5;

  float fltRunnerX = 100;
  float fltRunnerY = 300;

  float fltLineX = 450;
  float fltLineY = 75;
	
  boolean clickedA = false, clickedB = false, clickedC = false, clickedD = false, clickedE = false, clickedF = false;
  boolean letterOne = true, letterTwo = false, letterThree = false, letterFour = false, letterFive = false;
  boolean stageZero = false, stageOne = false, stageTwo = false, stageThree = false, stageFour = false, didLose = false, didWin = false;
  boolean upPressed = false, downPressed = false, rightPressed = false, leftPressed = false;
  int loopCnt = 0, lastMovedMouse = 0, backgroundR = 210, backgroundG = 255, backgroundB = 173;
  boolean hasCat = false, userGone = false;

  public void settings() {
    size (600, 400);
  }

  public void setup() {
    background (backgroundR, backgroundG, backgroundB);

    movingItem = loadImage ("movingPic.png");
    movingItem.resize (100, 130);

    cursor = loadImage ("cursor.png");
    cursor.resize (50, 50);

    runner = loadImage ("runner.png");
    runner.resize (70, 70);

    finishLine = loadImage ("finishLine.png");
    finishLine.resize (100, 100);
  }

  public void draw() {
    loopCnt ++;

    if (stageZero == false) {

      if (loopCnt - lastMovedMouse > 300) {
        userGone = true;
      }
      else {
        userGone = false;
      }

      background (backgroundR, backgroundG, backgroundB);
      fill (0);
      stroke (0);
      textSize (15);
      text ("Put the cat on your preferred background colour and press shift.", 50, 30);
      if (userGone == true && loopCnt % 100 / 10 >= 0 && loopCnt % 100 / 10 <= 7) {
        text ("Hello? You there?!", 50, 350);
      }

      fill (179, 64, 64);
      stroke (179, 64, 64);
      rect (40, 100, 40, 200);
      fill (179, 118, 64);
      stroke (179, 118, 64);
      rect (120, 100, 40, 200);
      fill (199, 199, 62);
      stroke (199, 199, 62);
      rect (200, 100, 40, 200);
      fill (76, 199, 62);
      stroke (76, 199, 62);
      rect (280, 100, 40, 200);
      fill (62, 199, 174);
      stroke (62, 199, 174);
      rect (360, 100, 40, 200);
      fill (62, 117, 199);
      stroke (62, 117, 199);
      rect (440, 100, 40, 200);
      fill (121, 62, 199);
      stroke (121, 62, 199);
      rect (520, 100, 40, 200);
      
      if (hasCat == false) {
        image (cursor, fltCursorX, fltCursorY);
      }
      else {
        image (cursor, mouseX - 25, mouseY - 25);
      }
    }

    if (stageZero == true) {
      background (backgroundR, backgroundG, backgroundB);
      fill (0);
      stroke (0);
      textSize (15);
      text ("Click on the pink letter", 50, 30);

      for (int i = 1; i <= 7; i ++) {
        fill (127, 125, 240);
        stroke (127, 125, 240);
        ellipse (((float) i) / 8 * width, 70, 40, 40);
        fill (255);
        stroke (255);
        textSize (30); 
        text ((char) (i + 96), ((float) i) / 8 * width - 8, 75);
      }

      if (clickedD == false && letterOne == true) {
        makePink ('d');
      }
      if (clickedE == false && letterTwo == true) {
        makePink ('e');
      }
      if (clickedC == false && letterThree == true) {
        makePink ('c');
      }
      if (clickedA == false && letterFour == true) {
        makePink ('a');
      }
      if (clickedF == false && letterFive == true) {
        makePink ('f');
      }
    }
    
    if (stageOne == true) {
      if (loopCnt / 10 % 2 == 0) {
        background(232, 90, 168);
      }
      else {
        background(219, 112, 171);
      }

      fill (0);
      stroke (0);
      textSize (20);
      text ("What was the word, champion?", 75, 60);
      textSize (16);
      text ("TYPE THE LETTER", 75, 80);

      fill (105, 255, 122);
      rect (100, 140, 120, 30);
      rect (100, 240, 120, 30);
      rect (400, 140, 120, 30);
      rect (400, 240, 120, 30);

      fill (0);
      text ("a) faced", 140, 160);
      text ("b) fade", 440, 160);
      text ("c) decaf", 140, 260);
      text ("b) deaf", 440, 260);

      if (keyPressed == true) {
        if (key == 'a' || key == 'b' || key == 'd') {
          userLost();
          stageTwo = true;
          stageOne = false;
        }
        else if (key == 'c') {
          stageTwo = true;
          stageOne = false;
        } 
      }
    }

    if (stageTwo == true && didLose == false) {
      background(0);
      fill (255);
      stroke (255);
      textSize (25);
      text ("keep pressing your mouse till the llama is gone", 50, 200);
      if (mousePressed == true) {
        if (fltImgX > -100) {
          image (movingItem, fltImgX, fltImgY);
          fltImgX -= 2;
        }
        if (fltImgX == -100) {
          stageThree = true;
          stageTwo = false;
        }
      }
      else {
        fltImgX = 500;
        fltImgY = 285;
      }
    }

    if (stageThree == true && didLose == false) {
      background (backgroundR, backgroundG, backgroundB);
      
      if (upPressed == true) {
        fltRunnerY -= 3;
      }
      if (downPressed == true) {
        fltRunnerY += 3;
      }
      if (leftPressed == true) {
        fltRunnerX -= 3;
      }
      if (rightPressed == true) {
        fltRunnerX += 3;
      }
      
      image (finishLine, fltLineX, fltLineY);
      image (runner, fltRunnerX, fltRunnerY);
      fill (0);
      stroke (0);
      textSize (20);
      text ("move the runner to the finish line!", 52, 100);
      text ("use arrow keys", 52, 150);

      if (fltRunnerX + 50 >= fltLineX && fltRunnerY <= fltLineY + 80) {
        stageThree = false;
        stageFour = true;
      }
      
    }

    if (stageFour == true && didLose == false) {
      background (backgroundR, backgroundG, backgroundB);
      fill (0);
      stroke (0);
      textSize (30);
      text ("use your mouse wheel to WIN", 100, 150);
    }

    if (didWin == true) {
      background(0);
      fill (255);
      stroke (255);
      textSize (30);
      text ("WOW! YOU FINISHED THE PROGRAM!", 52, 200);
    }
  }


  private void makePink (char chrC) {
    int intDifference = (int) (chrC - 'a') + 1; // number of character on the circles
    fill (227, 84, 206);
    stroke (227, 84, 206);
    ellipse (((float) intDifference) / 8 * width, 70, 40, 40);
    fill (255);
    stroke (255);
    textSize (30); 
    text (chrC, ((float) intDifference) / 8 * width - 8, 75);
  }
  
  public void mouseClicked () {

    if (stageOne == false && Math.pow(mouseX -  0.5 * width, 2) + Math.pow(mouseY - 70, 2)  <= 400 && clickedD == false && letterOne == true) {
      clickedD = true;
      letterTwo = true;
      letterOne = false;
    }

    if (stageOne == false && Math.pow(mouseX -  0.625 * width, 2) + Math.pow(mouseY - 70, 2)  <= 400 && clickedE == false && letterTwo == true) {
      clickedE = true;
      letterThree = true;
      letterTwo = false;
    }

    if (stageOne == false && Math.pow(mouseX -  0.375 * width, 2) + Math.pow(mouseY - 70, 2)  <= 400 && clickedC == false && letterThree == true) {
      clickedC = true;
      letterFour = true;
      letterThree = false;
    }
    if (stageOne == false && Math.pow(mouseX -  0.125 * width, 2) + Math.pow(mouseY - 70, 2)  <= 400 && clickedA == false && letterFour == true) {
      clickedA = true;
      letterFive = true;
      letterFour = false;
    }

    if (stageOne == false && Math.pow(mouseX -  0.75 * width, 2) + Math.pow(mouseY - 70, 2)  <= 400 && clickedF == false && letterFive == true) {
      clickedF = true;
      stageOne = true;
      letterFive = false;
      stageZero = false;
    }
  }

 
  public void mousePressed () {
    if (stageZero == false) {
      if (Math.pow ((double) mouseX - (fltCursorX + 25) ,2) + Math.pow ((double) mouseY - (fltCursorY + 5), 2) <= 625) {
        hasCat = true;
      }
    }
  }

  public void mouseReleased () {
    if (stageZero == false) {
      if (hasCat == true) {
        image (cursor, mouseX - 25, mouseY - 25);
        fltCursorX = mouseX - 25;
        fltCursorY = mouseY - 25;
      }
      hasCat = false;
    }
  }

  public void mouseDragged () {
    lastMovedMouse = loopCnt;
  }

  public void mouseMoved () {
    lastMovedMouse = loopCnt;
  }

  public void keyPressed () {
    if (stageZero == false && key == CODED) {
      if (keyCode == SHIFT) {

        if (fltCursorX + 25 > 40 && fltCursorX + 25 < 80 && fltCursorY + 25 > 100 && fltCursorY + 25 < 200) {
          backgroundR = 179;
          backgroundG = 64;
          backgroundB = 64;
        }

        else if (fltCursorX + 25 > 120 && fltCursorX + 25 < 160 && fltCursorY + 25 > 100 && fltCursorY + 25 < 200) {
          backgroundR = 179;
          backgroundG = 118;
          backgroundB = 64;
        }
        else if (fltCursorX + 25 > 200 && fltCursorX + 25 < 240 && fltCursorY + 25 > 100 && fltCursorY + 25 < 200) {
          backgroundR = 199;
          backgroundG = 199;
          backgroundB = 62;
        }
        else if (fltCursorX + 25 > 280 && fltCursorX + 25 < 320 && fltCursorY + 25 > 100 && fltCursorY + 25 < 200) {
          backgroundR = 76;
          backgroundG = 199;
          backgroundB = 62;
        }
        else if (fltCursorX + 25 > 360 && fltCursorX + 25 < 400 && fltCursorY + 25 > 100 && fltCursorY + 25 < 200) {
          backgroundR = 62;
          backgroundG = 199;
          backgroundB = 174;
        }
        else if (fltCursorX + 25 > 440 && fltCursorX + 25 < 480 && fltCursorY + 25 > 100 && fltCursorY + 25 < 200) {
          backgroundR = 62;
          backgroundG = 117;
          backgroundB = 199;
        }
        else if (fltCursorX + 25 > 520 && fltCursorX + 25 < 560 && fltCursorY + 25 > 100 && fltCursorY + 25 < 200) {
          backgroundR = 121;
          backgroundG = 62;
          backgroundB = 199;
        }

        stageZero = true;
      }
    }

    if (stageThree == true && key == CODED) {
      if (keyCode == UP) {
        upPressed = true;
      }
      else if (keyCode == DOWN) {
        downPressed = true;
      }
      else if (keyCode == LEFT) {
        leftPressed = true;
      }
      else if (keyCode == RIGHT) {
        rightPressed = true;
      }
    }
  }

  public void keyReleased () {
    if (stageThree == true && key == CODED) {
      if (keyCode == UP) {
        upPressed = false;
      }
      else if (keyCode == DOWN) {
        downPressed = false;
      }
      else if (keyCode == LEFT) {
        leftPressed = false;
      }
      else if (keyCode == RIGHT) {
        rightPressed = false;
      }
    }
  }

  private void userLost () {
    didLose = true;
    background(0);
    fill (255);
    stroke (255);
    textSize (30);
    text ("YOU LOST. CAN'T EXPERIENCE THE REST!", 52, 200);

  }

  public void mouseWheel () {
    if (stageFour == true) {
      didWin = true;
    }
  }
   
}