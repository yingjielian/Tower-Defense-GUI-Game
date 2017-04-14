package game;

import java.awt.Point;

public class SaltTowerMenuItem extends Effect implements Animatable
{

	public SaltTowerMenuItem(GameState g, Point p) 
	{
		super(g, p);
		this.towerImage = ResourceLoader.getLoader().getImage("resources/salt.png");
	}
	
	public void update()
	{
		if(game.getMousePressed() && game.getMousePos().distance(position) < 50)
			game.addAnimatable(new SaltTowerMenuItem(game, position));
		game.clearMousePressed();
	}

}
