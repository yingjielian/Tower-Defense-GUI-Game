package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * This class is going to set all the effects (such as moves the tower,
 * kills the enemies, and reduces the lives, etc) for the Tower Defense
 * game. And will make other effect subclass simpler.
 * 
 * @author Yingjie Lian & Nan Ying
 * @class CS-1410
 * @version 04/16/2017
 *
 */
public class Effect implements Animatable
{
	protected BufferedImage towerImage;
	protected GameState game;
	protected Point position;

	/**
	 * The constructor stores the GameState and Point from the
	 * parameters. And will be called for using in other class.
	 * 
	 * @param g a GameState object
	 * @param p a Point object
	 */
	public Effect(GameState g, Point p)
	{
		game = g;
		position = p;
	}
	
	/**
	 * Get the position of the input point.
	 * 
	 * @return a Point object represents the position of the tower
	 */
	public Point getPosition()
	{
		return position;
	}
	
	/**
	 * Draws the target tower image on a specific position.
	 */
	public void draw(Graphics2D g)
	{
		g.drawImage(towerImage, position.x, position.y, null);
	}

	@Override
	public void update() {}
}
