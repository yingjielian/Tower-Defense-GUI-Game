package game;

import java.awt.Point;
/**
 * This class is a subclass of Tower class. This class is going to load
 * the SaltTower image.
 * 
 * @author Yingjie Lian & Nan Ying
 * @class CS-1410
 * @version 04/16/2017
 *
 */

public class SaltTower extends Tower implements Animatable 
{
	public SaltTower(GameState g, Point p) {
		super(g, p);
		this.towerImage = ResourceLoader.getLoader().getImage("resources/salt.png");
	}
}
