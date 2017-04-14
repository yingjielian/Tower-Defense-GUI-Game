package game;

import java.awt.Point;

public class SaltTower extends Tower implements Animatable 
{

	public SaltTower(GameState g, Point p) {
		super(g, p);
		this.towerImage = ResourceLoader.getLoader().getImage("resources/salt.png");
		// TODO Auto-generated constructor stub
	}

}
