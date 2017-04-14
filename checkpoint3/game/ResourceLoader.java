package game;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class is going to help me with loading images and paths
 * in order to make the code in PathTestPanel much simpler and 
 * readable.
 * 
 * @author Yingjie Lian & Nan Ying
 * @version 04/07/2017
 * @class CS-1410
 *
 */
public class ResourceLoader 
{
	// Fields (object variables) 
	Map<String, BufferedImage> imageMap;
	Map<String, Path> pathMap;

	// Create a static variable that can hold one ResourceLoader object
	private static ResourceLoader singleInstance;

	/**
	 * The constructor creates two private field variables and stores
	 * two HashMap object inside the two Map variables. Inside the HashMap 
	 * use Strings as keytype and BufferedImages, Path as valuetype.
	 */
	public ResourceLoader()
	{
		// Set up all ResouceLoader needs
		imageMap = new HashMap<String, BufferedImage>();
		pathMap = new HashMap<String, Path>();
	}

	/**
	 * This method is going to make the code simpler by calling the ResourceLoader once
	 * instead of calling it multiple times whenever they need outside the class.
	 * @return a ResourceLoader object
	 */
	public static ResourceLoader getLoader() 
	{
		if (singleInstance == null)
			singleInstance = new ResourceLoader();

		return singleInstance;
	}

	/**
	 * This method does the following:
	 *         -It checks to see if this image has been already loaded (by looking for an 
	 *         entry with that name in a map).
	 *         -If the image has not been loaded before, it is loaded and stored in a map 
	 *         	of loaded images.  (Use the name as the key and the image as the value.)
	 * @param imageName
	 * @return a BufferedImage object (the image that we need)
	 */
	public BufferedImage getImage(String imageName) {
		// Check to see if this image has been already loaded 
		// (by looking for an entry with that name in a map).
		if(imageMap.containsKey(imageName))
			return imageMap.get(imageName);

		// If the image has not been loaded before, it is loaded 
		// and stored in a map of loaded images.  (Use the name as 
		// the key and the image as the value. And use try-catch to
		// do it.)
		try
		{	
			ClassLoader myLoader = this.getClass().getClassLoader();
			
			// Load the background image
			InputStream imageStream = myLoader.getResourceAsStream(imageName);
			imageMap.put(imageName, javax.imageio.ImageIO.read(imageStream));  // A handy helper method //???

			return imageMap.get(imageName);
		}
		catch (IOException e)
		{
			// On error, just print a message and exit.  
			//   (You should make sure the files are in the correct place.)
			System.err.println ("Could not load one of the files: " + imageName);
			System.exit(0);  // Bail out.
			return null;
		}        
	}

	/**
	 * This method does the following:
	 *         -It checks to see if this path has been already loaded (by looking for an
	 *          entry with that name in a map).
	 *         -If the path has not been loaded before, a new Path object is built from
	 *          that path file, and the Path object is stored in a map of Path objects.  
	 *          (Using the name as the key and the Path object as the value.)
	 * @param pathName
	 * @return a Path object 
	 */
	public Path getPath(String pathName) {
		if(pathMap.containsKey(pathName))
			return pathMap.get(pathName);

		try
		{	
			ClassLoader myLoader = this.getClass().getClassLoader();

			// Load the path points file
			InputStream pointStream = myLoader.getResourceAsStream(pathName);
			Scanner in = new Scanner (pointStream); 
			pathMap.put(pathName, new Path(in));  // A handy helper method
			in.close();

			return pathMap.get(pathName);
		}
		catch (Exception e)
		{
			// On error, just print a message and exit.  
			//   (You should make sure the files are in the correct place.)
			System.err.println ("Could not load one of the files: " + pathName);
			System.exit(0);  // Bail out.
			return null;
		}        
	}
}
