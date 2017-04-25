package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
/**
 * This class is a subclass of Enemy(super class).
 * This class does two functions:
 *    -Load the image for enemy car.
 *    -Change the speed of the enemy car.
 *    
 * @author Yingjie Lian & Nan Ying
 * @class CS-1410
 * @version 04/16/2017
 *
 */
public class EnemySCargo extends Enemy implements Animatable 
{

	public EnemySCargo(String pathName, GameState g) 
	{
		super(pathName, g);
		this.Enemyimage = ResourceLoader.getLoader().getImage("resources/s-cargo.png");
		
		// Change the percentage to 0.4% in order to change the 
		// enemy cars' speed that shows on screen.
		this.diffPercentage = 0.004; 
	}
}
