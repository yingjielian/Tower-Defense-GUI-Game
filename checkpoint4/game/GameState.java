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
	private boolean buttonActionPending, isPlaying, isGameOver, firstPlay;
	Enemy closest;

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

		buttonActionPending = false;
		mouseLoc = new Point(0,0);

		isPlaying = false;
		isGameOver = true;
		firstPlay = true;
		restart();
	}

	/**
	 * This method advances the animating enemies by randomizing the number if the game
	 * is playing. Otherwise, it will call the restart method to ask player to restart 
	 * the game.
	 */
	public void update()
	{
		if(isGameOver)
		{
			// Once player click the mouse, game start.
			if(buttonActionPending)
			{
				isGameOver = false;
				isPlaying = true;
				restart();
			}
			else
				return;
		}

		// Once the game starts, enemies will be sending along
		// the path.
		if(isPlaying)
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
	}

	/**
	 * This method contains the ArrayList of the tower and enemies, and
	 * two different tower menu bar items.
	 */
	private void restart() 
	{
		active = new ArrayList<>();
		expired = new ArrayList<>();
		pending = new ArrayList<>();

		closest = null;

		active.add(new SaltTowerMenuItem(this, new Point(660,300)));// Set the Tower on the menu bar.
		active.add(new BeerTowerMenuItem(this, new Point(668,400)));
	}

	/**
	 * This methods will do several things below:
	 * 		-First, check if is the first time the user open the game. If yes, remind player clicking to start playing.
	 * 		-If player fails the game, show player game was over and remind player to restart to play again.
	 * 		-Show player his/her current credits
	 * 		-Show player his/her current lives.
	 * 
	 * @param g the graphics2D object for drawing on this panel
	 */
	public void draw (Graphics2D g)
	{
		Font livesFont = new Font("Ultra", Font.BOLD, 38); // Set the font for the string
		Font start = new Font("Ultra", Font.BOLD, 55);
		// Expand the menu bar panel.
		g.setColor(Color.PINK);
		g.fillRect(getWidth(), 0, 200, getWidth());

		// Animate enemies.
		if(isPlaying)
		{
			g.drawImage(backdrop,  0, 0, null);
			for (Animatable a : active)
				a.draw(g);
			firstPlay = false;
		}

		if(isGameOver == true && firstPlay == false)
		{
			g.drawImage(backdrop,  0, 0, null);
			g.drawImage(gameOver, 0, 0, null);
			g.setColor(Color.BLACK);
			g.fillRect(120, 400, 420, 55);
			g.setFont(start);
			g.setColor(Color.WHITE);
			g.drawString("Click to Restart!", 120, 450);
		}
		
		if(firstPlay)
		{
			g.drawImage(backdrop,  0, 0, null);
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(220, 350, 350, 55);
			g.setFont(start);
			g.setColor(Color.CYAN.darker());
			g.drawString("Click to start!", 220, 400);
		}
		// Show users how many lives remain.
		g.setFont(livesFont);
		g.setColor(Color.BLUE);
		g.drawString("Lives =" + lives, 620, 200);

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
	 * Adjust the credits.
	 * @param amount int value
	 */
	public void adjustCredits(int amount)
	{
		credits += amount;

		if(credits < 0)
			credits = 0;
	}

	/**
	 * Adjust the lives.
	 * @param amount int value
	 */
	public void adjustLives(int amount)
	{
		lives += amount;

		if(lives < 0)
			lives = 0;

		// If lives equals to 0, then game over and reset
		// the lives and credits for player.
		if(lives == 0)
		{
			isGameOver = true;
			buttonActionPending = false;
			lives = 10;
			credits = 500;
		}
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
	 * 
	 * @param p a Point object
	 * @return the closest Enemy
	 */
	public Enemy findNearestEnemy(Point p)
	{
		for(Animatable a : active)
		{
			if(a instanceof Enemy)
			{
				Enemy e = (Enemy) a;
				if(closest == null)
					closest = e;
				else if(e.getLocation().distance(p) < closest.getLocation().distance(p))
					closest = e;
			}
		}
		return closest;
	}

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
		buttonActionPending = true;
	}

	/**
	 * Clears the mouse press boolean flag
	 */
	public void  clearPendingButtonAction()      
	{
		buttonActionPending = false;
	}

	/**
	 * @return the value of the mouse press flag
	 */
	public boolean getPendingButtonAction() 
	{
		return buttonActionPending;
	}
}
