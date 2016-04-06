package bodies;

import city.cs.engine.*;

import java.awt.Color;

/**
 *
 * @author greg
 */


/**
 * An orange.
 */
public class Orange extends DynamicBody {
    private static final Shape shape = new CircleShape(0.2f);
    private float gravity;
    
    
    public Orange(World world) {
        super(world, shape);
        setName("Orange");
        setFillColor(Color.orange);
    }
    
    public Orange(World world,float gravity) {
        this(world);
        this.gravity= gravity;
        setGravityScale(gravity);
    }
}
