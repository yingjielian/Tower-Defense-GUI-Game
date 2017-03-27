package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Scanner;

/**
 * 
 * @author Yingjie Lian
 * @version 03/25/2017
 * @class CS-1410
 *
 */

public class Path 
{
	private Point[] path;
	
	// Constructor
	public Path(Scanner in)
	{
		int size = in.nextInt();
		
		path = new Point[size];
		
		for (int i = 0; i < size; i++)
		{
			int x = in.nextInt();
			int y = in.nextInt();
			
			Point p = new Point(x, y);
			path[i] = p;
		}
		System.out.println("Got here" +size);
		
		
	}
	
	public void draw (Graphics g)
	{
		g.setColor(Color.YELLOW);
		
		for (int i = 1; i < path.length; i++)
		{
			g.drawLine(path[i-1].x, path[i-1].y, path[i].x, path[i].y);
			g.drawLine(path[i-1].x-1, path[i-1].y-1, path[i].x, path[i].y);
			g.drawLine(path[i-1].x, path[i-1].y, path[i].x-1, path[i].y-1);
			g.drawLine(path[i-1].x+1, path[i-1].y+1, path[i].x, path[i].y);
			g.drawLine(path[i-1].x, path[i-1].y, path[i].x+1, path[i].y+1);
		}
		
		
	}

}
