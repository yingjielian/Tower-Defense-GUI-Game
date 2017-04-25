package game;

import java.awt.Point;
/**
 * This class is a subclass of Effect class. This class is going to
 * draw the tower on the menu bar and make it removable when mouse 
 * clicks on the tower.
 * 
 * @author Yingjie Lian & Nan Ying
 * @class CS-1410
 * @version 04/16/2017
 *
 */
public class SaltTowerMenuItem extends Effect implements Animatable
{
	public SaltTowerMenuItem(GameState g, Point p) 
	{
		super(g, p);
		this.towerImage = ResourceLoader.getLoader().getImage("resources/salt.png");
	}

	/**
	 * Makes the tower removable by clicking the mouse on the tower.
	 */
	public void update()
	{
		// If the mouse clicks and within 50 pixels around the tower, the tower 
		// will be able to remove, otherwise not.
		if(game.getPendingButtonAction() && game.getMousePos().distance(getLocation()) < 50){
			game.addAnimatable(new SaltTowerMoving(game, getLocation()));
			game.clearPendingButtonAction();
		}
	}
}
