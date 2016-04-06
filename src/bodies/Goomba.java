/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodies;

import city.cs.engine.BodyImage;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.Fixture;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.Walker;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Pietro
 */
public class Goomba extends Walker{

    private static final Shape body = new PolygonShape(
        0.125f,0.5f, 0.436f,0.248f, 0.497f,-0.033f, 0.313f,-0.498f, -0.375f,-0.497f, -0.498f,-0.03f, -0.437f,0.25f, -0.125f,0.498f);
    private static final BodyImage image =new BodyImage("data/goomba.png");
    private static String name = "Goomba";
    private  boolean direction = true;// true = move right, false = move left
    private  boolean touchedMario=false;
    
    public Goomba(World w) {
        super(w);
        Fixture f = new SolidFixture(this,body);
        addImage(image);
        setName(name);this.addCollisionListener(new CollisionListener() {

                @Override
                public void collide(CollisionEvent e) {
                    if(e.getOtherBody().getName().equals("Mario")){
                        touchedMario=true;  
                        destroy();
                    }
                }
        });
    }
    
    public Goomba(World w, Vec2 position){
        this(w);
        setPosition(position);
    }

    public boolean getDirection() {
        return direction;
    }

    public  void setDirection(boolean direction) {
        this.direction = direction;
    }

    public boolean getTouchedMario() {
        return touchedMario;
    }

    public void setTouchedMario(boolean touchedMario) {
        this.touchedMario = touchedMario;
    }
    
    
    
}
