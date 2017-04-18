package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
/**
 * The enemy class is going to set all the features(such as tower image, 
 * tower position, etc) that towers need. And will make 
 * tower subclass simpler.
 * 
 * @author Yingjie Lian & Nan Ying
 * @class CS-1410
 * @version 04/16/2017
 *
 */
public class Tower implements Animatable
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
	public Tower(GameState g, Point p)
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
		g.drawImage(towerImage, position.x - towerImage.getWidth()/2, position.y-towerImage.getHeight()/2, null);
	}

	@Override
	public void update() {}
}
