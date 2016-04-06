/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import city.cs.engine.BodyImage;
import city.cs.engine.CircleShape;
import city.cs.engine.Fixture;
import city.cs.engine.GhostlyFixture;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import city.cs.engine.World;

/**
 *
 * @author Pietro
 */
public class Lifes extends StaticBody{

    private final String name="Life";
    private Shape heartShape;
    
    public Lifes(World w) {
        super(w);
        
        this.setName(name);
        float radius = 0.5f;
        heartShape = new CircleShape(radius);
        Fixture fixture = new GhostlyFixture(this, heartShape);
        this.addImage(new BodyImage("data/heart.png"));
    }
}
