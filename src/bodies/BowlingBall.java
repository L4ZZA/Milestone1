/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodies;

import city.cs.engine.BodyImage;
import city.cs.engine.CircleShape;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.DynamicBody;
import city.cs.engine.Fixture;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.World;
import java.util.Random;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Pietro
 */
public class BowlingBall extends DynamicBody{

    private final String name="Ball";
    private Shape ballShape;
    private BodyImage ballImage;
    private  boolean touchedMario=false;
    private  boolean touchedGround=false;
    
    
    public BowlingBall(World w) {
        
        super(w);
       
        this.setName(name);
        float radius = 0.5f;
        ballShape = new CircleShape(radius);
        Fixture fixture = new SolidFixture(this, ballShape);
        ballImage = new BodyImage("data/bowl.png", 2*radius);
        
        Random r = new Random();
        float randomPosition = r.nextInt(6) +5.6f;
        float randomVelocity  = r.nextInt(20);
        
        setPosition(new Vec2(12,-randomPosition));
        setGravityScale(0);
        setLinearVelocity(new Vec2(-randomVelocity,0));
        addImage(ballImage);
        this.addCollisionListener(new CollisionListener() {

                @Override
                public void collide(CollisionEvent e) {
                    if(e.getOtherBody().getName().equals("Mario")){
                        touchedMario=true;  
                        destroy();
                    }
                    else if(e.getOtherBody().getName().equals("Ground")){
                        touchedGround=true;
                        //destroy();
                    }
                    else if(e.getOtherBody().getName().equals("LifeBonus")){
                        destroy();
                    }
                }
        });
        
    }
    
    
    public boolean getTouchedMario(){
        return this.touchedMario;
    }

    public void setTouchedMario(boolean touchedGround) {
        this.touchedMario = touchedGround;
    }

    public boolean isTouchedGround() {
        return touchedGround;
    }
    
    
}
