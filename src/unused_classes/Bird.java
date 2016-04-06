/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unused_classes;

import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.DynamicBody;
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
public class Bird extends Walker{

    private final String name= "Bird";
    
    public Bird(World w) {
          
        super(w);
        
        setName(name);
        Shape birdShape = new PolygonShape(
                0.149f,0.975f, 0.775f,0.193f, 0.772f,-0.099f, 0.401f,-0.928f, -0.36f,-0.922f, -0.719f,-0.025f, -0.725f,0.163f, -0.14f,0.972f);
        Fixture fixture = new SolidFixture(this, birdShape);
        addImage(new BodyImage("data/yellow-bird.gif", 2.25f));
        setPosition(new Vec2(2, -10));
        
    }
    
    
    
    
}
