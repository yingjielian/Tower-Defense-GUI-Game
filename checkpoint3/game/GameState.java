package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
/**
 * This class is going to set all the states that this Tower Defense
 * game need. (It only contains the path and animating staff so far).
 * 
 * @author Yingjie Lian & Nan Ying
 * @version 04/07/2017
 * @class CS-1410
 *
 */
public class GameState {
	// Fields (object variables) 
	private String pathName;
	private BufferedImage backdrop; // the background image
	private int credits, lives;
	List<Animatable> active;
	List<Animatable> expired;
	List<Animatable> pending;
	EnemySnail snail;
	private Point mouseLoc;
	private boolean isMousePressed;

	/**
	 * The constructor stores and initializes the variables. Also, it inputs
	 * the image files and path file.
	 */
	public GameState()
	{

		ResourceLoader myLoader = ResourceLoader.getLoader(); // Gets the resource loader object
		backdrop = myLoader.getImage("resources/path_1.jpg");
		pathName ="resources/path_1.txt";  
		//		snail = new EnemySnail(path, this);
		lives = 10;

		active = new ArrayList<>();
		expired = new ArrayList<>();
		pending = new ArrayList<>();

		// Debug
//		active.add(new SaltTower(this, new Point(180,100)));
		active.add(new SaltTower(this, new Point(680,300)));
		mouseLoc = new Point(0,0);
	}

	/**
	 * This method advances the animating staff 0.1% (one thousandth the distance)
	 * along the path, and redraw the screen.
	 */
	public void update()
	{

		if(Math.random() < 0.02){
			if(Math.random() < 0.3)
			{
				active.add(new EnemySnail(pathName, this));
			}
			else
			{
				active.add(new EnemySCargo(pathName, this));
			}
		}

		for (Animatable a : active)
			a.update();

		active.addAll(pending);
		pending.clear();
		
		active.removeAll(expired);
		expired.clear();
	}

	/**
	 * Draws the target image, path, and the animating image.
	 * 
	 * (The background is not cleared, it is assumed the backdrop
	 * fills the panel.)
	 * 
	 * @param g the graphics2D object for drawing on this panel
	 */
	public void draw (Graphics2D g)
	{
		// Draw the background.
		g.drawImage(backdrop,  0, 0, null);


		g.setColor(Color.WHITE);
		g.fillRect(getWidth(), 0, 200, getWidth());
		g.setColor(Color.black);
		g.drawString("Lives =" + lives, 650, 200);
		//				g.drawImage(menu, getWidth(), 0, null); // Temporary for testing menu panel

		for (Animatable a : active)
			a.draw(g);
	}

	/**
	 * Returns the the pixel of the image's width.
	 * 
	 * @return a integer, the pixel of the image's width.
	 */
	public int getWidth()
	{
		return backdrop.getWidth();
	}

	/**
	 * Returns the the pixel of the image's height.
	 * 
	 * @return a integer, the pixel of the image's height.
	 */
	public int getHeight()
	{
		return backdrop.getHeight();
	}

	public void adjustCredits(int amount)
	{
		credits += amount;
		if(credits < 0)
			credits = 0;
	}

	public void adjustLives(int amount)
	{
		lives += amount;

		if(lives < 0)
			lives = 0;
	}

	public void removeAnimatable(Animatable expired)
	{
		this.expired.add(expired);
	}

	public void addAnimatable(Animatable a)
	{
		this.pending.add(a);
	}

	/**
	 * Finds the enemy on the path that is the closest to the point p,
	 * if no enemy exit, null is returned.
	 * @param p
	 * @return
	 */
	//	public Enemy findNearestEnemy(Point p)
	//	{
	//		if(a instanceof Enemy)
	//	}

	// Records the current mouse position
	public void  setMousePos(Point p) 
	{
		System.out.println("Positions");
		mouseLoc = p;
	}
	
	// Returns the current mouse position
	public Point getMousePos()   
	{
		
		return mouseLoc;
		
	}
	
	// Sets a boolean flag to true (to indicate a mouse press)
	public void  setMousePressed()   
	{
		isMousePressed = true;
	}
	
	// Clears the mouse press boolean flag
	public void  clearMousePressed()      
	{
		isMousePressed = false;
	}
	
	// Returns the value of the mouse press flag
	public boolean getMousePressed() 
	{
		return isMousePressed;
	}
}
