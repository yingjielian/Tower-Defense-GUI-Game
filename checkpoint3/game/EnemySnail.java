package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class EnemySnail extends Enemy implements Animatable
{


	public EnemySnail(String pathName, GameState g) 
	{
		super(pathName, g);
		this.Enemyimage = ResourceLoader.getLoader().getImage("resources/snail.png");
		this.diffPercentage = 0.001;
	}

}

