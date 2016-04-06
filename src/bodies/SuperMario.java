package bodies;

import city.cs.engine.*;

/**
 * Simple character
 */
public class SuperMario extends Walker {

    // Remember:  using the keyword static below means the fields shape and image belong to the class, rather than any instance. 
    // That means there is a single shape and image for all instances of the class.
    private static final Shape face = new PolygonShape(
            0.206f,0.454f, 0.39f,0.191f, 0.383f,-0.075f, 0.161f,-0.202f, -0.244f,-0.199f, -0.42f,-0.071f, -0.427f,0.098f, -0.131f,0.45f);

    private static final Shape body = new PolygonShape(
            0.143f,-0.217f, 0.266f,-0.334f, 0.21f,-0.731f, -0.251f,-0.735f, -0.304f,-0.551f, -0.304f,-0.27f, -0.247f,-0.21f);
    private static final BodyImage imageStill =
        new BodyImage("data/mario_stop.png", 1.5f);

    private String name = "Mario";
    private int orangeCount;
    private boolean moving, //true = mario is moving , false = mario is not moving
                    direction;//true = moving right  ,  false = moving left
                    

    public SuperMario(World world) {
        super(world);
        Fixture faceFixture = new SolidFixture(this,face);
        Fixture bodyFixture = new SolidFixture(this,body);
        
        addImage(imageStill);
        orangeCount = 0;
        setName(name);
        setDirection(true);
    }

    public int getOrangeCount() {
        return orangeCount;
    }

    public void incrementOrangeCount() {
        orangeCount++;
        System.out.println("Tasty!  Orange count = " + orangeCount);
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean isMoving) {
        this.moving = isMoving;
    }

    public boolean getDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }    

}

