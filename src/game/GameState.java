package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
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
	private Path gardenPath;
	private double ciclePercentage;
	private BufferedImage backdrop, snail; // the background image
	
	/**
	 * The constructor stores and initializes the variables. Also, it inputs
	 * the image files and path file.
	 */
	public GameState()
	{
		ResourceLoader myLoader = ResourceLoader.getLoader(); // Gets the resource loader object
		backdrop = myLoader.getImage("resources/path_1.jpg");
		snail = myLoader.getImage("resources/snail.png");
		gardenPath = myLoader.getPath("resources/path_1.txt");  
		
		ciclePercentage = 0.0;
	}
	
	/**
	 * This method advances the animating staff 0.1% (one thousandth the distance)
	 * along the path, and redraw the screen.
	 */
	public void update()
	{
		ciclePercentage += 0.001;
		if (ciclePercentage > 1){
			ciclePercentage = 0.0;
		}
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

				// Have the path object draw itself.
				gardenPath.draw(g);

				// Draw the snail from the image we have stored, centered on its location.
				// (Must get it's location first.)
				Point c = gardenPath.getPathPosition(ciclePercentage);
				g.drawImage(snail, c.x-25, c.y-14, null);
				
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
}
