package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class EnemySCargo extends Enemy implements Animatable 
{
	private BufferedImage carImage;

	public EnemySCargo(String pathName, GameState g) 
	{
		super(pathName, g);
		this.Enemyimage = ResourceLoader.getLoader().getImage("resources/s-cargo.png");
		this.diffPercentage = 0.003;
	}

}
