/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platforms;

import bodies.BonusLife;
import bodies.SuperMario;
import city.cs.engine.CollisionEvent;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import handlers.Pickup;
import java.awt.Color;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Pietro
 */
public class LifePlatform extends StaticBody{
    
   // private final double PLATFORM_BOTTOM= -3.4685683;
    private boolean done;
    private final World world;
    private Vec2 position;
    private SuperMario mario;
    
    
    /**
     * 
     * @param world the world within create the platform
     * @param shape the shape of the platform
     * @param i index of the loop, used to set the relative position 
     * @param mario at the moment not utilised, but I was trying to use this object reference to develop the listener inside this class instead of the world class 
     */
    public LifePlatform(World world, Shape shape, int i, SuperMario mario){
        super(world,shape);
        this.world = world;
        this.done= false;
        this.mario = mario;
        setPosition(new Vec2(4+i, -2.5f));
        setFillColor(Color.YELLOW);
        this.position = getPosition();
        
        /*
        this.addCollisionListener(new Pickup(mario){
                        @Override
                        public void collide(CollisionEvent e) {
                            System.out.println(e.getOtherBody());
                            System.out.println(isDone());
                            //System.out.println(mario.getPosition().y);
                            
                            if (e.getOtherBody() == getMario() && !done &&
                                    getMario().getPosition().y < position.y) {
                                
                                // create instance of a new object bonus
                                BonusLife life = new BonusLife(world, position);
                                done=true;
                                setFillColor(Color.GRAY);
                                /* Debug messages */
                                //System.out.println("Mario position: "+e.getOtherBody().getPosition().y);
                                //System.out.println("P position: "+e.getReportingBody().getPosition().y);
        /*
                            }
                        }
                    });
                    */
    }
/*
    public double getPLATFORM_BOTTOM() {
        return PLATFORM_BOTTOM;
    }
*/
    
    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public SuperMario getMario() {
        return mario;
    }

    public void setMario(SuperMario mario) {
        this.mario = mario;
    }
    
    
    
}
