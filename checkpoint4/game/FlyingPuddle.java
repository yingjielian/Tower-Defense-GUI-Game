package game;

import java.awt.Point;
/**
 * This class is a sub class of Effect class. It will load the FlyingPuddle image
 * whenever the enemies are close enough to the towers.
 * 
 * 
 * @author Yingjie & Nan Ying
 * @class CS-1410
 * @version 04/24/2017
 *
 */

public class FlyingPuddle extends Effect {
	private int frameCounter;
	private double vectorLength, vX, vY;

	public FlyingPuddle(GameState g, Point p, int vX, int vY) {
		super(g, p);
		vectorLength = Math.sqrt(vX * vX + vY * vY);
		this.towerImage = ResourceLoader.getLoader().getImage("resources/FlyingPuddle.png");
		this.vX = vX/vectorLength;
		this.vY = vY/vectorLength;
		this.frameCounter = 0;
	}

	/**
	 * This method will do several things below:
	 * 		-If enemies are close enough, then fire and send the bullet to the enemies.
	 * 		-If the bullets hit the enemies, then show up the effects.
	 * 			-If the enemy is snail, then show up splat effect.
	 * 			-If the enemy is car, then show up car crash effect.
	 */
	public void update()
	{
		position.x += (vX *0.000001); // Change FlyingPuddle Speed
		position.y += (vY *0.000001);
		frameCounter++;

		// Control how long the puddle bullet will stay showing up on screen.
		if(frameCounter == 100)
			game.removeAnimatable(this);

		Enemy nearbyEnemy = game.findNearestEnemy(position);
		if(nearbyEnemy == null)
			return;

		Point nearbyEnemyPoint = new Point(nearbyEnemy.getLocation().x - 25, nearbyEnemy.getLocation().y - 25);

		if(nearbyEnemy.getLocation().distance(position) <= 200)
		{
			// No matter the enemy is defeated or not, bullets show up
			game.addAnimatable(this);
			if(this.getLocation().distance(nearbyEnemyPoint) <= 80)
			{
				if (game.pending.contains(nearbyEnemy)) 
				{
					return;
				} 
				else 
				{
					// Check if the enemy is snail or not.
					if (nearbyEnemy.getImage().equals(ResourceLoader.getLoader().getImage("resources/snail.png"))) {
						game.addAnimatable(new SplatEffect(game, nearbyEnemyPoint));
						game.removeAnimatable(nearbyEnemy);
						
						// Once one snail enemy is defeated, player get 10 credits
						game.adjustCredits(10);
					} 
					else 
					{
						game.addAnimatable(new CrashEffect(game, nearbyEnemyPoint));
						game.removeAnimatable(nearbyEnemy);
						
						// Once one car enemy is defeated, player get 20 credits
						game.adjustCredits(20);
					}
				}
				// Avoid add credits multiple times.
				game.removeAnimatable(this);
			}
		}
	}
}
