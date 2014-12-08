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
Sidus spes;
//Bullet telum;
ArrayList <Asteroid> saxa;
ArrayList <Bullet> artillery; 
Sidus [] ordo;
boolean up, down, left, right;
public void setup() 
{
  up = false;
  down = false;
  left= false;
  right = false;
  size(1000,800);
  background(0);
  aeneas = new SpaceShip();
  //telum = new Bullet(aeneas);
  spes = new Sidus();
  ordo = new Sidus[100];

  for(int i = 0; i<ordo.length;i++)
  {
    ordo[i] = new Sidus();
  }
  
  artillery = new ArrayList <Bullet>();
  
   saxa = new ArrayList <Asteroid>();
   for(int j = 0; j<10; j++)
   {
     saxa.add(new Asteroid());
   }
 }
public void draw() 
{
  background(0);
  aeneas.show();
  aeneas.move();
  spes.show();
  //telum.show();
  //telum.move();
  for(int i = 0; i<ordo.length;i++)
   {
     ordo[i].show();
   }
   
   for(int i  = 0; i<artillery.size();i++)
   {
     artillery.get(i).show();
     artillery.get(i).move();
     if (artillery.get(i).getEnd() == true)
     {
      artillery.remove(i);
     }
   }
  for(int i = 0;i<saxa.size();i++)
  {
      saxa.get(i).show();
      saxa.get(i).move();
      for(int j= 0;j< artillery.size();j++)
      {
        float xx = dist(saxa.get(i).getX(),saxa.get(i).getY(),artillery.get(j).getX(),artillery.get(j).getY());

        float nn = 5*(saxa.get(i).getSize());
        if(xx<= nn)
        {
          saxa.remove(i);
          artillery.remove(j);
          break;
        }
      }

   if (up ==true)
   {
      double t = .05f;
    aeneas.accelerate(t);
   }  
    if (down ==true)
   {
      double t = .05f;
    aeneas.accelerate(t);
   }  
   if(left ==true)
   {
      aeneas.rotate(-1);
   }
   System.out.println(right);
   if (right == true)
   {
      aeneas.rotate(1);
   }
  }

}
public void keyPressed()
{
  if (key == CODED)
  {
   if (keyCode == UP )
   {

    up =true;
  }
  if (keyCode == DOWN)
  {

    down = true;
  }
  if (keyCode == LEFT)
  {
    left = true;
  }
  if (keyCode == RIGHT)
  {
    right  = true;
  }
}

if(key == ' ')
{
 artillery.add(new Bullet(aeneas)); 
}
if (key == 't')
{
  aeneas.setX((int)(Math.random()*980+10));
  aeneas.setY((int)(Math.random()*780+10));
  aeneas.setDirectionX((int)(Math.random()*25-12));
  aeneas.setDirectionY((int)(Math.random()*25-12));
}

}
public void keyReleased()
{
  if (key == CODED)
  {
   if (keyCode == UP )
   {

    up =false;
   }
   if (keyCode == DOWN)
   {

     down = false;
   }
    if (keyCode == LEFT)
   {
    left = false;
   }
   if (keyCode == RIGHT)
   {
     right  = false;
   }
 }
}
 class Bullet extends Floater
 {
  boolean end;
  double dRadians;
    Bullet(SpaceShip one)
    {
      myCenterX = one.getX();
      myCenterY = one.getY();
      myPointDirection = one.getPointDirection();
      dRadians = myPointDirection*((Math.PI)/(180));
      myDirectionX = 5*Math.cos(dRadians)+ one.getDirectionX();
      myDirectionY = 5*Math.sin(dRadians)+ one.getDirectionY();
      myColor = color(200,0,0);
      end = false;
    }
    public void show()
    {
      fill(200,0,0);
      noStroke();
        ellipse((float)myCenterX,(float)myCenterY,8,8);
    }
    public void setX(double x){myCenterX = x;}
    public int getX(){return (int)myCenterX;}
    public void setY(double y){myCenterY = y;}
    public int getY(){return (int)myCenterY;}
    public void setDirectionX(double x){myDirectionX = x;}
    public double getDirectionX(){return myDirectionX;}
    public void setDirectionY(double y){myDirectionY = y;}
    public double getDirectionY(){return myDirectionY;}
    public void setPointDirection(int degrees){myPointDirection =degrees;}   
    public double getPointDirection(){return myPointDirection;}
    public boolean getEnd(){return end;}
    public void move ()   //move the floater in the current direction of travel
  {      
    //change the x and y coordinates by myDirectionX and myDirectionY       
    myCenterX += myDirectionX;    
    myCenterY += myDirectionY;     

    //wrap around screen    
    if(myCenterX >width)
    {     
      boolean end = true;    
    }    
    else if (myCenterX<0)
    {     
      boolean end = true;    
    }    
    if(myCenterY >height)
    {    
      boolean end = true;
    }   
    else if (myCenterY < 0)
    {     
      boolean end = true;    
    }   
  }   
 }
 class Asteroid extends Floater 
 {
    private int size;

    
    private int myRot;
    Asteroid()
    {
      corners = (int)(Math.random()*17+7);
      
      double deg = ((360/corners)*((Math.PI)/180));
      size = (int)(Math.random()*5+2);
      xCorners = new int[corners];
      yCorners = new int[corners];
      
      for(int i =0; i<corners;i++)
      {
        xCorners[i] = (size)*(int)((Math.cos(i*deg)) * (Math.random()*5+5));
        yCorners[i] = (size)*(int)((Math.sin(i*deg)) * (Math.random()*5+5));
      }

      //myColor = color((int)(Math.random()*225+1),(int)(Math.random()*225+1),(int)(Math.random()*225+1));
      myColor = color(200,200,200);
      myCenterX = (int)(Math.random()*1000+1);
      myCenterY = (int)(Math.random()*1000+1);
      myDirectionX = (int)(Math.random()*7 +1);
      if((Math.random())<.5f)
      {
        myDirectionX = myDirectionX*-1;
      }
      myDirectionY = (int)(Math.random()*7+1);
      if(Math.random()> .5f)
      {
        myDirectionY = myDirectionY*-1;
      }
      myPointDirection = -40;

      myRot = (int)(Math.random()*10-6);  
    }
    public float getRot(){return (float)myRot;}
    public float getSize(){return (float)size;}

    public void setX(double x){myCenterX = x;}
    public int getX(){return (int)myCenterX;}
    public void setY(double y){myCenterY = y;}
    public int getY(){return (int)myCenterY;}
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
    //fill(200);
    noFill();
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
    public int getX(){return (int)myCenterX;}
    public void setY(double y){myCenterY = y;}
    public int getY(){return (int)myCenterY;}
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
  abstract public int getX();   
  abstract public void setY(double y);   
  abstract public int getY();   
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
  public void show ()  //Draws the floater at the current bbposition  
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
