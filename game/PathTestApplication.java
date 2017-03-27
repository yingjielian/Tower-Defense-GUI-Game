/**
 * 
 */
package game;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * An application for testing the PathPoints class and the
 * PathPosition class.  The path is drawn on the screen, and
 * a ball follows along on the path.
 * 
 * This class won't be part of the final project - we're just
 * using it for testing.
 * 
 * @author Peter Jensen
 * @version March 21, 2017
 * @class  CS-1410
 */
public class PathTestApplication implements Runnable
{

    /**
     * The application entry point.
     * 
     * @param args unused
     */
    public static void main (String[] args)
    {
        // Main runs in the 'main' execution thread, and the GUI
        //   needs to be built by the GUI execution thread.
        //   Ask the GUI thread to run our 'run' method (at some
        //   later time).
        
        SwingUtilities.invokeLater(new PathTestApplication());

        // Done.  Let the main thread of execution finish.  All the
        //   remaining work will be done by the GUI thread.
    }
    
    /**
     * Builds the GUI for this application.  This method must
     * only be called/executed by the GUI thread. 
     */
    public void run ()
    {
        JFrame f = new JFrame("Path Tester");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        f.setContentPane(new PathTestPanel());
        
        f.pack();
        f.setLocationRelativeTo(null);  // Centers window
        f.setVisible(true);
    }

}
