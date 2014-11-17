import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class AsteroidsGame extends PApplet {

SpaceShip aeneas; 
//Sidus spes;
Asteroid monitus;
Asteroid [] saxa;
Sidus [] ordo;
public void setup() 
{
  
  size(1000,800);
  background(0);
  aeneas = new SpaceShip();
  //spes = new Sidus();
  ordo = new Sidus[100];
  saxa = new Asteroid[5];
  monitus = new Asteroid();
  for(int i = 0; i<ordo.length;i++)
  {
    ordo[i] = new Sidus(); 
  }
  for(int i = 0; i<saxa.length;i++)
  {
    saxa[i] = new Asteroid();
  }
}
public void draw() 
{
  background(0);
  aeneas.show();
  aeneas.move();
  //spes.show();
  monitus.show();
  monitus.move();
  for(int i = 0; i<ordo.length;i++)
  {
    ordo[i].show();
  }
  for(int i = 0; i<saxa.length;i++)
  {
    saxa[i].show();
    saxa[i].move();
  }
}
public void keyPressed()
  {
    if (key == CODED)
    {
     if (keyCode == UP )
     {
      aeneas.setPointDirection(270);
      double t = .5f;
      aeneas.accelerate(t);
    }
    if (keyCode == DOWN)
    {
      aeneas.setPointDirection(90);
      double t = .5f;
      aeneas.accelerate(t);
    }
    if (keyCode == LEFT)
    {
      aeneas.setPointDirection(180);
      double t = .5f;
      aeneas.accelerate(t);
      aeneas.setDirectionX(-20);
    }
    if (keyCode == RIGHT)
    {
      aeneas.setPointDirection(0);
      double t = .5f;
      aeneas.accelerate(t);
    }
   }
    if (key == 'd')
    {
      aeneas.rotate(5);
    }
    if(key == 's')
    {
      aeneas.rotate(-5);
    }
    if (key == 't')
    {
      aeneas.setX((int)(Math.random()*980+10));
      aeneas.setY((int)(Math.random()*780+10));
      aeneas.setDirectionX((int)(Math.random()*25-12));
      aeneas.setDirectionY((int)(Math.random()*25-12));
    }

  }
 class Asteroid extends Floater 
 {
    private int deg, size;
    private int myRot;
    Asteroid()
    {
      corners = 10;
      //deg = (360/10)*((PI)/180);
      size = 20;
      xCorners = new int[corners];
      yCorners = new int[corners];

      for(int i =0; i<corners;i++)
      {
        xCorners[i] = (int)(Math.random()*101-50);
        yCorners[i] = (int)(Math.random()*101-50);
      }

      myColor = color(200,200,200);
      myCenterX = (int)(Math.random()*1000+1);
      myCenterY = (int)(Math.random()*1000+1);
      myDirectionX = (int)(Math.random()*10 +1);
      if((Math.random())<.5f)
      {
        myDirectionX = myDirectionX*-1;
      }
      myDirectionY = (int)(Math.random()*10+1);
      if(Math.random()> .5f)
      {
        myDirectionY = myDirectionY*-1;
      }
      myPointDirection = -40;

      myRot = (int)(Math.random()*8-4); 
     
    }
    public void setX(double x){myCenterX = x;}
    public double getX(){return myCenterX;}
    public void setY(double y){myCenterY = y;}
    public double getY(){return myCenterY;}
    public void setDirectionX(double x){myDirectionX = x;}
    public double getDirectionX(){return myDirectionX;}
    public void setDirectionY(double y){myDirectionY = y;}
    public double getDirectionY(){return myDirectionY;}
    public void setPointDirection(int degrees){myPointDirection =degrees;}   
    public double getPointDirection(){return myPointDirection;}
   public void move ()   //move the floater in the current direction of travel
  {      
    rotate(myRot);

    super.move();
  }   
  public void show ()  //Draws the floater at the current position  
  {             
    
    fill(myColor);   
    stroke(myColor);    
    //convert degrees to radians for sin and cos         
    double dRadians = myPointDirection*(Math.PI/180);                 
    int xRotatedTranslated, yRotatedTranslated;    
    beginShape();

    for(int nI = 0; nI < corners; nI++)    
    {     
      //rotate and translate the coordinates of the floater using current direction 
      xRotatedTranslated = (int)((xCorners[nI]* Math.cos(dRadians)) - (yCorners[nI] * Math.sin(dRadians))+myCenterX);     
      yRotatedTranslated = (int)((xCorners[nI]* Math.sin(dRadians)) + (yCorners[nI] * Math.cos(dRadians))+myCenterY);      
      vertex(xRotatedTranslated,yRotatedTranslated);    
    }   
    endShape(CLOSE);  
  }   
 }
class Sidus
{
  private int x,y,l,w;
  Sidus()
  {
    x = (int)(Math.random()*1000+1);
    y = (int)(Math.random()*800+1);
    w = (int)(Math.random()*3+1);
    l = (int)(Math.random()*3+1);
  }
  public void show()
  {
    stroke(200);
    fill(200);
    ellipse(x,y,w,l);

  }
  
}
class SpaceShip extends Floater 
{   
    SpaceShip()
    {
      

      //myColor = color(222,24,100);
      corners  = 5;
      xCorners = new int[corners];
      yCorners = new int[corners];

      xCorners[0] = 16;
      yCorners[0] = 0;
      xCorners[1] = -8;
      yCorners[1] = -8;
      xCorners[2] = -4;
      yCorners[2] = -4;
      xCorners[3] = -4;
      yCorners[3] = 4;
      xCorners[4] = -8;
      yCorners[4] = 8;

      myColor = color(23,200,9);
      myCenterX = 500;
      myCenterY = 400;
      myDirectionX = 3;
      myDirectionY = 0;
      myPointDirection = -40;
    }
    public void setX(double x){myCenterX = x;}
    public double getX(){return myCenterX;}
    public void setY(double y){myCenterY = y;}
    public double getY(){return myCenterY;}
    public void setDirectionX(double x){myDirectionX = x;}
    public double getDirectionX(){return myDirectionX;}
    public void setDirectionY(double y){myDirectionY = y;}
    public double getDirectionY(){return myDirectionY;}
    public void setPointDirection(int degrees){myPointDirection =degrees;}   
    public double getPointDirection(){return myPointDirection;}


}
abstract class Floater //Do NOT modify the Floater class! Make changes in the SpaceShip class 
{   

  protected int corners;  //the number of corners, a triangular floater has 3   
  protected int[] xCorners;   
  protected int[] yCorners;   
  protected int myColor;   
  protected double myCenterX, myCenterY; //holds center coordinates   
  protected double myDirectionX, myDirectionY; //holds x and y coordinates of the vector for direction of travel   
  protected double myPointDirection; //holds current direction the ship is pointing in degrees    
  abstract public void setX(double x);  
  abstract public double getX();   
  abstract public void setY(double y);   
  abstract public double getY();   
  abstract public void setDirectionX(double x);   
  abstract public double getDirectionX();   
  abstract public void setDirectionY(double y);   
  abstract public double getDirectionY();   
  abstract public void setPointDirection(int degrees);   
  abstract public double getPointDirection(); 

  //Accelerates the floater in the direction it is pointing (myPointDirection)   
  public void accelerate (double dAmount)   
  {          
    //convert the current direction the floater is pointing to radians    
    double dRadians =myPointDirection*(Math.PI/180);     
    //change coordinates of direction of travel    
    myDirectionX += ((dAmount) * Math.cos(dRadians));    
    myDirectionY += ((dAmount) * Math.sin(dRadians));       
  }   
  public void rotate (int nDegreesOfRotation)   
  {     
    //rotates the floater by a given number of degrees    
    myPointDirection+=nDegreesOfRotation;   
  }   
  public void move ()   //move the floater in the current direction of travel
  {      
    //change the x and y coordinates by myDirectionX and myDirectionY       
    myCenterX += myDirectionX;    
    myCenterY += myDirectionY;     

    //wrap around screen    
    if(myCenterX >width)
    {     
      myCenterX = 0;    
    }    
    else if (myCenterX<0)
    {     
      myCenterX = width;    
    }    
    if(myCenterY >height)
    {    
      myCenterY = 0;    
    }   
    else if (myCenterY < 0)
    {     
      myCenterY = height;    
    }   
  }   
  public void show ()  //Draws the floater at the current position  
  {             
    fill(myColor);   
    stroke(myColor);    
    //convert degrees to radians for sin and cos         
    double dRadians = myPointDirection*(Math.PI/180);                 
    int xRotatedTranslated, yRotatedTranslated;    
    beginShape();         
    for(int nI = 0; nI < corners; nI++)    
    {     
      //rotate and translate the coordinates of the floater using current direction 
      xRotatedTranslated = (int)((xCorners[nI]* Math.cos(dRadians)) - (yCorners[nI] * Math.sin(dRadians))+myCenterX);     
      yRotatedTranslated = (int)((xCorners[nI]* Math.sin(dRadians)) + (yCorners[nI] * Math.cos(dRadians))+myCenterY);      
      vertex(xRotatedTranslated,yRotatedTranslated);    
    }   
    endShape(CLOSE);  
  }   
} 

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "AsteroidsGame" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
