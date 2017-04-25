package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * This class is a subclass of Enemy(super class).
 * This class does two functions:
 *    -Load the image for the enemy snail.
 *    -Change the speed of the enemy snail.
 *    
 * @author Yingjie Lian & Nan Ying
 * @class CS-1410
 * @version 04/16/2017
 *
 */
public class EnemySnail extends Enemy implements Animatable
{
	public EnemySnail(String pathName, GameState g) 
	{
		super(pathName, g);
		this.Enemyimage = ResourceLoader.getLoader().getImage("resources/snail.png");
		
		// Change the percentage to 0.2% in order to change the 
		// enemy snails' speed that shows on screen.
		this.diffPercentage = 0.002;
	}
}

