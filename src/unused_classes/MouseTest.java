package unused_classes;

import city.cs.engine.World;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import worlds.GameWorld;

/**
 * Dummy mouse listener.
 */
public class MouseTest implements MouseListener {

    private boolean mouseExited=false;
    private GameWorld world;
    
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouse clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mouse pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouse released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
            System.out.println("mouse entered: if exited then restart the game");
    }

    @Override
    public void mouseExited(MouseEvent e) {
            System.out.println("mouse exited: then pause the game");
    }
   
}
