package game;

import java.awt.Point;
/**
 * This class is a sub class of Effect class. It will load the splat image
 * whenever car enemies will be defeated.
 * 
 * @author Yingjie & Nan Ying
 * @class CS-1410
 * @version 04/24/2017
 *
 */

public class SplatEffect extends Effect {
	
	private int frameCounter;
	public SplatEffect(GameState g, Point p) 
	{
		super(g, p);
		this.towerImage = ResourceLoader.getLoader().getImage("resources/splat.png");
		frameCounter = 0;
	}
	
	public void update()
	{
		frameCounter++;
		
		// Control how long the puddle bullet will stay showing up on screen.
		if(frameCounter == 10)
			game.removeAnimatable(this);
	}

}
