package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
/**
 * This class is a subclass of Effect class. This class is going to
 * let the tower follow the mouse when the mouse is dragging. Then, 
 * if the mouse click somewhere, the tower will locate there.
 * 
 * @author Yingjie Lian & Nan Ying
 * @class CS-1410
 * @version 04/16/2017
 *
 */
public class SaltTowerMoving extends Effect implements Animatable
{
	public SaltTowerMoving(GameState g, Point p) 
	{
		super(g, p);
		this.towerImage = ResourceLoader.getLoader().getImage("resources/salt.png");
	}

	public void update()
	{
		position = game.getMousePos();

		// If the mouse presses, set the tower right on that point
		// and then clear the tower, that way the tower will not
		// follow the mouse forever even after being set.

		// However, the tower will not be able to set on the path 
		// and the menu bar area.
		if(game.getPendingButtonAction())
		{
			int x = game.getMousePos().x;
			int y = game.getMousePos().y;

			BufferedImage temp = ResourceLoader.getLoader().getImage("resources/path_2mask.png");

			// If there are some areas which are black color, the
			// tower will not be able to set on.
			if(x>= 0 && x<= 600 && (temp.getRGB(x, y) != 0))
			{
				return;
			}

			// If it is menu bar area, the tower will not be able to 
			// set on.
			if(x < 0 || x>600)
				return;

			game.addAnimatable(new SaltTower(game, position));
			
			// When tower loads, credits reduces. One SaltTower 
			// requires 50 credits.
			game.adjustCredits(-50); 
			game.removeAnimatable(this);
			game.clearPendingButtonAction();
		}
	}

	/**
	 * Center the tower follows the mouse while dragging.
	 */
	public void draw(Graphics2D g)
	{
		g.drawImage(towerImage, position.x - towerImage.getWidth()/2, position.y-towerImage.getHeight()/2, null);
	}
}
