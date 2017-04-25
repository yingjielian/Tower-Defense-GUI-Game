package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
/**
 * The Animatable interface is going to make the code simpler.
 * Whenever I need to store or call any classes which are implemented 
 * by Animatable, just store them into a Animatable type ArrayList.
 * 
 * The goal is that when we create classes for enemies, towers, etc., 
 * we'll have those classes implement this interface.  That way, we can 
 * treat those types of objects as Animatable objects (when we want to 
 * ignore the details), but they'll retain their specific type.
 * 
 * The principle for this is because when a class implements an interface, 
 * thereby inheriting the abstract methods of the interface.
 * 
 * @author Yingjie Lian & Nan Ying
 * @class CS-1410
 * @version 04/16/2017
 *
 */

/**
 * The interface is a collection of abstract methods. So all the methods
 * down below are abstract.
 *
 */
public interface Animatable 
{
	public void update();

	public void draw(Graphics2D g);
}
