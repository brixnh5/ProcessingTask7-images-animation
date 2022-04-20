import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
	
  // initialize variables
	PImage backgroundImg;
  PImage DVD;
  
  double dblCircleX = 50;
  double dblCircleY = 50;
  double dblCircleXSpeed = 10;
  double dblCircleYSpeed;
  double dblCircleMove = 1;

  double dblDvdX = 50;
  double dblDvdY = 50;
  double dblDvdXSpeed = 7;
  double dblDvdYSpeed;
  double dblDvdMove = 2;
  double dblWave;

  // declare size
  public void settings() {
    size(612, 408);
  }

  // load background image
  public void setup() {
    backgroundImg = loadImage("white background.jpg");
    image(backgroundImg, 0, 0);
    
  }

  public void draw() {
    // set background image every seocnd so animated elements do not leave trails
    image(backgroundImg, 0, 0);
    
    // calculate Y speed of circle using sinusoidal function with periods of Pi/20 
    dblCircleYSpeed = Math.sin(dblWave) * 10;
    dblWave += Math.PI/20;

    // check if circle moves beyond the edges of the window, and reverses the motion if so
    if (dblCircleX - 10 < 0 || dblCircleX + 10 > width) {
      dblCircleXSpeed *= -1;
    } 

    if ((dblCircleY - 10 < 0 && dblCircleYSpeed < 0) || (dblCircleY + 10 > height && dblCircleYSpeed > 0)) {
      dblCircleYSpeed *= -1;
      dblCircleMove *= -1;
    }

    // set colour and stroke of circle
    noStroke();
    fill(0);

    // draw + animate circle according to speeds
    ellipse((float)dblCircleX, (float)dblCircleY, 20, 20);
    dblCircleX += dblCircleXSpeed;
    dblCircleY += dblCircleMove + dblCircleYSpeed;

    // calculate DVD Y speed with sinusoidal function, load DVD image
    dblDvdYSpeed = Math.sin(dblWave) * 2;
    DVD = loadImage("DVD_logo.png");
    DVD.resize(width / 5, height / 5);

    // check if DVD moves beyond the edges of the window, and reverses the motion if so
    if (dblDvdX > width - width / 5 || dblDvdX < 0) {
      dblDvdXSpeed *= -1;
    }

    if ((dblDvdY + height / 5 > height && dblDvdYSpeed > 0) || (dblDvdY < 0 && dblDvdYSpeed < 0)) {
      dblDvdYSpeed *= -1;
      dblDvdMove *= -1;
    }

    // draw + animate DVD according to speeds
    image(DVD, (float)dblDvdX, (float)dblDvdY);
    dblDvdX += dblDvdXSpeed;
    dblDvdY += dblDvdYSpeed + dblDvdMove;
  }
}
