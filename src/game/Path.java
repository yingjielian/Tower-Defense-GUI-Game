package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Scanner;

/**
 * This class is going to draw a visible path and a moving circle.
 * 
 * @author Yingjie Lian & Nan Ying
 * @version 03/29/2017
 * @class CS-1410
 *
 */

public class Path 
{
	private Point[] path;
	private Point start, end;
	private int sx, sy, ex, ey, deltaX, deltaY;
	private double totalLength;

	/** This constructor does the following:
	 *     - It creates a new array (or ArrayList) to hold the path coordinates,
	 *          and stores it in the path variable.
	 *     - It reads a number of coordinates, n, from the scanner.
	 *     - It loops n times, each time scanning a coordinate x,y pair, creating an
	 *         object to represent the coordinate, and storing the coordinate object in the path.
	 * 
	 * @param in  a Scanner set up by the caller to provide a list of coordinates
	 */
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
		System.out.println("Got here" +size); // Test if it works.
	}

	/**
	 * Draws the current path to the screen (to wherever the graphics object points)
	 * using a highly-visible color.
	 * 
	 * @param g   a graphics object
	 */  
	public void draw (Graphics g)
	{
		g.setColor(Color.YELLOW);



		for (int i = 1; i < path.length; i++)
		{
			start = path[i-1];
			end = path[i];
			sx = start.x;
			sy = start.y;
			ex = end.x;
			ey = end.y;
			g.drawLine(sx, sy+1, ex, ey+1);

		}
	}

	/** 
	 * Returns the total length of the path. Since the path
	 * is specified using screen coordinates, the length is
	 * in pixel units (by default).
	 * 
	 * @return the length of the path
	 */
	public double getPathLength ()
	{
		double totalLength = 0;
		for (int i = 1; i < path.length; i++)
		{
			start = path[i-1];
			end = path[i];
			sx = start.x;
			sy = start.y;
			ex = end.x;
			ey = end.y;
			totalLength += start.distance(end);

		}

		return totalLength;
	}

	/** 
	 * Given a percentage between 0% and 100%, this method calculates
	 * the location along the path that is exactly this percentage
	 * along the path. The location is returned in a Point object
	 * (int x and y), and the location is a screen coordinate.
	 * 
	 * If the percentage is less than 0%, the starting position is
	 * returned. If the percentage is greater than 100%, the final
	 * position is returned.
	 * 
	 * If students don't want to use Point objects, they may 
	 * write or use another object to represent coordinates. 
	 *
	 * Caution: Students should never directly return a Point object
	 * from a path list. It could be changed by the outside caller.
	 * Instead, always create and return new point objects as
	 * the result from this method.
	 * 
	 * @param percentage a distance along the path
	 * @return Point the screen coordinate of this position along the path
	 */
	public Point getPathPosition(double percentage)
	{

		System.out.println("in get path position");
		// Special cases
		if(percentage <= 0.0) // Start
			return new Point(path[0]);
		if(percentage >= 1.0) // End
			return new Point(path[path.length-1]);
		
		double distanceToTravel = this.getPathLength() * percentage;
		double segTravelled = 0;
		double difference;
		double segDistance = 0;
		int atX = 0, atY = 0;

		for (int i = 1; i < path.length; i++)
		{
			start = path[i-1];
			end = path[i];
			sx = start.x;
			sy = start.y;
			ex = end.x;
			ey = end.y;
			segDistance = start.distance(end); // Count distance of each segment.
			segTravelled += segDistance; // Add the distance from beginning to the segment where the circle are at.
			difference = segTravelled - distanceToTravel; 

			if (segTravelled > distanceToTravel)
			{
				double rate = difference/segDistance;
				atX = (int) (rate*sx + (1-rate)*ex);
				atY = (int) (rate*sy + (1-rate)*ey);
				break;
			}
		}
		System.out.println("x: " + atX + " : " + "y: " + atY); // Make the points are visible for me to debug.
		return new Point(atX, atY);
	}



}
