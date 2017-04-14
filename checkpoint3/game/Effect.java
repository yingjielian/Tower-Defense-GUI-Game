package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Effect extends Tower implements Animatable
{

	protected BufferedImage towerImage;
	protected GameState game;
	protected Point position;

	public Effect(GameState g, Point p)
	{
		super(g,p);
	}
	
	public Point getPosition()
	{
		return position;
	}
	
	public void draw(Graphics2D g)
	{
		g.drawImage(towerImage, position.x, position.y, null);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
