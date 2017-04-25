package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
/**
 * The enemy class is going to set all the features(such as enemy image, 
 * enemy moving speed-percentage, etc) that enemies need. And will make 
 * enemy subclass simpler.
 * 
 * @author Yingjie Lian & Nan Ying
 * @class CS-1410
 * @version 04/16/2017
 *
 */
public class Enemy implements Animatable
{
	// Fields
	protected double percentage;
	protected Path path;
	protected BufferedImage Enemyimage;
	protected GameState game;
	
	// This will change in different enemy subclasses in order to 
	// change the speed of the enemies.
	protected double diffPercentage; 
	
	/**
	 * The constructor initializes the percentage to 0, 
	 * loads the path for all enemy classes at once, and 
	 * stores the GameState object from the parameter.
	 * 
	 * @param pathName a String object of the path file name
	 * @param g a GameState object
	 */
	public Enemy(String pathName, GameState g)
	{
		percentage = 0;
		path = ResourceLoader.getLoader().getPath(pathName);
		game = g;
	}
	
	/**
	 * This method advances the animating enemies by changing
	 * the percentage of the distance (for example 0.1% means 
	 * one thousandth the distance) along the path, and redraw 
	 * the screen. 
	 * 
	 * Also, if the enemies pass the bottom line of 
	 * the path on screen, reduce the lives by one for each 
	 * enemy and clear that enemy.
	 */
	public void update()
	{
		// Variable diffPercentage will be called in different enemies
		// subclass, by changing the amount of that to change the speed
		// of that enemy.
		percentage += diffPercentage;
		
		if (percentage >= 1){
			game.adjustLives(-1); // lives reduce
			game.removeAnimatable(this); // Clear the enemy
		}
	}

	/**
	 * Draws the target enemy image and the animating the enemy follow
	 * the path.
	 * 
	 * @param g the graphics2D object for drawing on this panel
	 */
	public void draw(Graphics2D g)
	{
		Point c = path.getPathPosition(percentage);
		
		// Draw the image and center it along the path.
		g.drawImage(Enemyimage, c.x-Enemyimage.getWidth()/2, c.y-Enemyimage.getHeight()/2, null);
	}

	/**
	 * Get the point each single time after changing the percentage
	 * in order to animate the enemies.
	 * @return a Point object on the path 
	 */
	public Point getLocation()
	{
		return path.getPathPosition(percentage);
	}
	
	/**
	 * Get the image that loads from different enemies sub class.
	 * 
	 * @return a BufferedImage object, image that loads from different enemies sub class
	 */
	public BufferedImage getImage()
	{
		return Enemyimage;
	}
}
