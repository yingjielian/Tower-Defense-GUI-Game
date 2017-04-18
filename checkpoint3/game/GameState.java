package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
/**
 * This class is going to set all the states(such as set the background, 
 * animate the enemies, and count lives. etc) that this Tower Defense
 * game need.
 * 
 * @author Yingjie Lian & Nan Ying
 * @version 04/07/2017
 * @class CS-1410
 *
 */
public class GameState {
	// Fields (object variables) 
	private String pathName;
	private BufferedImage backdrop, gameOver; // the background image
	private int credits, lives;
	List<Animatable> active, expired, pending;
	private Point mouseLoc;
	private boolean isMousePressed;

	/**
	 * The constructor stores and initializes the variables. Also, it inputs
	 * the image files and path file.
	 */
	public GameState()
	{
		ResourceLoader myLoader = ResourceLoader.getLoader(); // Gets the resource loader object
		backdrop = myLoader.getImage("resources/path_1.jpg"); // Sets the background
		gameOver = myLoader.getImage("resources/game_over.png"); // Sets the game-over image.
		pathName ="resources/path_1.txt"; // Sets the path(invisible)
		lives = 10;
		credits = 500;

		active = new ArrayList<>();
		expired = new ArrayList<>();
		pending = new ArrayList<>();

		
		active.add(new SaltTowerMenuItem(this, new Point(660,300)));// Set the Tower on the menu bar.
		mouseLoc = new Point(0,0);
	}

	/**
	 * This method advances the animating enemies by randomizing the number.
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

		active.addAll(pending); // Adds the object to our expired list
		pending.clear();

		active.removeAll(expired); // Adds the object to our active list
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
		// Expand the menu bar panel.
		g.setColor(Color.WHITE);
		g.fillRect(getWidth(), 0, 200, getWidth());

		// If user's lives remained more than 0, the game continues 
		// and enemies run. Otherwise, the game stops.
		if(lives > 0){
			// Draw the background.
			g.drawImage(backdrop,  0, 0, null);

			// Animate enemies.
			for (Animatable a : active)
				a.draw(g);
		}
		else
		{
			g.setColor(Color.white);
			g.fillRect(0, 0, 600, 600);	
		}

		// When users have no lives(live equals to 0), show the game-
		// over image on screen to remind users.
		if(lives == 0)
			g.drawImage(gameOver, 0, 0, null);

		// Show users how many lives remain.
		Font livesFont = new Font("Ultra", Font.BOLD, 28); // Set the font for the string
		g.setFont(livesFont);
		g.setColor(Color.BLUE);
		g.drawString("Lives =" + lives, 650, 200);
		
		// Show users how many credits remain
		Font creditsFont = new Font("Times New Roman", Font.BOLD, 28); // Set the font for the string
		g.setFont(creditsFont);
		g.setColor(Color.blue.darker());
		g.drawString("Credits =" + credits, 620, 100);
		
		
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

	/**
	 * 
	 * @param amount
	 */
	public void adjustCredits(int amount)
	{
		credits += amount;
		
		if(credits < 0)
			credits = 0;
	}

	/**
	 * 
	 * @param amount
	 */
	public void adjustLives(int amount)
	{
		lives += amount;

		if(lives < 0)
			lives = 0;
	}

	/**
	 * Adds the object to our expired list
	 * @param expired
	 */
	public void removeAnimatable(Animatable expired)
	{
		this.expired.add(expired);
	}

	/**
	 * Adds the object to our active list
	 * @param a
	 */
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
	// Not complete yet, will do it on checkpoint#4!!!
	
	//	public Enemy findNearestEnemy(Point p)
	//	{
	//		if(a instanceof Enemy)
	//	}

	/**
	 * Records the current mouse position
	 * @param p a Point object
	 */
	public void  setMousePos(Point p) 
	{
		mouseLoc = p;
	}

	/**
	 * 
	 * @return the current mouse position
	 */
	public Point getMousePos()   
	{
		return mouseLoc;
	}

	/**
	 * Sets a boolean flag to true (to indicate a mouse press)
	 */
	public void  setMousePressed()   
	{
		isMousePressed = true;
	}

	/**
	 * Clears the mouse press boolean flag
	 */
	public void  clearMousePressed()      
	{
		isMousePressed = false;
	}

	/**
	 * @return the value of the mouse press flag
	 */
	public boolean getMousePressed() 
	{
		return isMousePressed;
	}
}
