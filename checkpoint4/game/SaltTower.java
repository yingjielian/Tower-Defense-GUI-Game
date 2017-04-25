package game;

import java.awt.Point;
/**
 * This class is a subclass of Tower class. This class is going to load
 * the SaltTower image and shot the salt crystal to the enemy.
 * 
 * @author Yingjie Lian & Nan Ying
 * @class CS-1410
 * @version 04/16/2017
 *
 */

public class SaltTower extends Tower implements Animatable 
{
	private int frameCounter;
	public SaltTower(GameState g, Point p) {
		super(g, p);
		this.towerImage = ResourceLoader.getLoader().getImage("resources/salt.png");	
		frameCounter = 0;
	}

	/**
	 * This method is going to check if there is a enemy near the tower.
	 * If yes, then fire. Otherwise not.
	 */
	public void update()
	{
		frameCounter++;// Whenever update method get called, frameCounter will plus 1

		// Control how long the salt crystal bullet will stay showing up on screen.
		if(frameCounter % 30 != 0)
			return;

		Enemy nearbyEnemy = game.findNearestEnemy(position);

		if(nearbyEnemy == null)
			return;

		int deltaX = nearbyEnemy.getLocation().x - position.x;
		int deltaY = nearbyEnemy.getLocation().y - position.y;
		Effect e;

		// The shooting range for the beer tower is 150 pixels.
		if(nearbyEnemy.getLocation().distance(position) <= 150)
		{
			e = new FlyingSalt(game, new Point(position), deltaX, deltaY);
			game.addAnimatable(e);
		}
	}
}
