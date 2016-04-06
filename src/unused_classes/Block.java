/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unused_classes;

import city.cs.engine.DynamicBody;
import city.cs.engine.*;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Pietro
 */
public class Block extends Walker{

    private final String name= "Bird";
    
    public Block(World w) {
        
        super(w);
        
        setName(name);
        Shape blockShape = new PolygonShape(-0.495f,0.498f, 
                0.498f,0.498f, 0.498f,-0.497f, -0.498f,-0.5f);
        Fixture fixture = new SolidFixture(this, blockShape);
        addImage(new BodyImage("data/block.png"));
        setPosition(new Vec2(0, -10));
    }
    
    /*
    public String getName(){
        return this.name;
    }*/
    
}
