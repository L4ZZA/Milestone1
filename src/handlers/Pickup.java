package handlers;

import city.cs.engine.*;
import bodies.SuperMario;
import worlds.Win;

/**
 * Collision listener that allows the mario to collect things.
 */
public class Pickup implements CollisionListener {
    private static SuperMario mario;
    
    public Pickup(SuperMario mario) {
        this.mario = mario;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == mario) {
            
            mario.incrementOrangeCount();
            e.getReportingBody().destroy();
            
        }
        
    }
    
}
