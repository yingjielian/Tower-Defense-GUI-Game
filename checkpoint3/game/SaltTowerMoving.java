package game;

import java.awt.Point;

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
		
		if(game.getMousePressed())
		{
			System.out.println("Get it");
			game.addAnimatable(new SaltTower(game, position));
			game.removeAnimatable(this);
			game.clearMousePressed();
		}
	}


}
