package worlds;

import worlds.GameWorld;
import city.cs.engine.*;
import handlers.Controller;
import handlers.GiveFocus;
import handlers.MouseHandler;
import unused_classes.MouseTest;

import javax.swing.JFrame;
import handlers.Tracker;

/**
 * The computer game.
 */
public class Game {

    /** The World in which the bodies move and interact. */
    private GameWorld world;

    /** A graphical display of the world (a specialised JPanel). */
    private UserView view;
    
   // private EngineerView view;

    /** Initialise a new Game. */
    public Game() {
        // uncomment this to draw a 1-metre grid over the view
        // view.setGridResolution(1);

        // display the view in a frame
        final JFrame frame = new JFrame("Super Mario BRO");
        // make the world
        world = new GameWorld();

        // make a view
        view = new UserView(world, 500, 500);
        world.setView(view);  //the only way I've found with Charlie to be able to get the UserView in the world
        
        // quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // display the world in the window
        frame.add(view);
        // don't let the game window be resized
        frame.setResizable(false);
        // size the game window to fit the world view
        frame.pack();
        // make the window visible
        frame.setVisible(true);
        // get keyboard focus
        frame.requestFocus();
        // give focus to the frame whenever the mouse enters the view
        view.addMouseListener(new GiveFocus(frame));
        // do things with the mouse
        view.addMouseListener(new MouseHandler(view));
        
        
        
        frame.addKeyListener(new Controller(world,world.getPlayer()));
        
        // uncomment to make the view track the bird
        Tracker tracker = new Tracker(view, world.getPlayer());
        world.addStepListener(tracker);
        world.setStepListener(tracker);
        // uncomment this to make a debugging view
        // JFrame debugView = new DebugViewer(world, 500, 500);
        world.setFrame(frame);
        // start!
        world.start();
    }

    /** Run the game. */
   /* public static void main(String[] args) {
        new Game();
     
        
    }*/
}
