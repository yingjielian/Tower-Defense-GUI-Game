package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public abstract class Enemy implements Animatable
{

	protected double percentage;
	protected Path path;
	protected BufferedImage Enemyimage;
	protected GameState game;
	protected double diffPercentage;
	
	public Enemy(String pathName, GameState g)
	{
	
		percentage = 0;
		path = ResourceLoader.getLoader().getPath(pathName);
		game = g;
	}
	
	public void update()
	{
		percentage += diffPercentage;
		if (percentage >= 1){
			game.adjustLives(-1);
			game.removeAnimatable(this);
		}
	}

	public void draw(Graphics2D g)
	{
		Point c = path.getPathPosition(percentage);
		g.drawImage(Enemyimage, c.x-Enemyimage.getWidth()/2, c.y-Enemyimage.getHeight()/2, null);
	}

	public Point getLocation()
	{
		return path.getPathPosition(percentage);
	}
}
