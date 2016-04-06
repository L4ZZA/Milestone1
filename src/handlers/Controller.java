package handlers;

import bodies.FireBall;
import city.cs.engine.*;
import bodies.SuperMario;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Key handler to control an Walker.
 */
public class Controller extends KeyAdapter {
    private static final float JUMPING_SPEED = 15;
    private static final float WALKING_SPEED = 3;
    
    private SuperMario mario;
    private boolean direction,isMoving=false,released=true;
    private static World world;
    
    public Controller(World w,SuperMario mario) {
        world = w;
        this.mario = mario;
    }
    
    /**
     * Handle key press events for walking and jumping.
     * @param e description of the key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        switch(code){
            case KeyEvent.VK_Q: // Q = quit
                System.exit(0);
                break;
                
            case KeyEvent.VK_UP: // arrow up = jump
                Vec2 v = mario.getLinearVelocity();// only jump if mario is not already jumping
                if (Math.abs(v.y) < 0.01f) {
                    mario.jump(JUMPING_SPEED); 
                    mario.addImage(new BodyImage("data/mario_stop.png",1.5f));
                }
                break;
                
            case KeyEvent.VK_LEFT: // walk left
                //isMoving=true;
                //moveMario(WALKING_SPEED);
            
                mario.startWalking(-WALKING_SPEED); 
                mario.setDirection(false);
                mario.addImage(new BodyImage("data/mario_move_left.png",1.5f));
                //System.out.println(mario.getPosition());
                break;
                
            case KeyEvent.VK_RIGHT: // walk right
                //isMoving=true;
                //moveMario(-WALKING_SPEED);

                mario.startWalking(WALKING_SPEED); 
                mario.setDirection(true);
                mario.addImage(new BodyImage("data/mario_move_right.png",1.5f));
                //System.out.println(mario.getPosition());
                break;
            
            case KeyEvent.VK_SPACE:
                if(released){
                    new FireBall(world, mario.getPosition(), mario.getDirection());
                    
                    released=false;
                }
                break;
                
        }
            
    }
    
    /**
     * Handle key release events (stop walking).
     * @param e description of the key event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
        switch(code){
            case KeyEvent.VK_LEFT:
                //isMoving=false;
                mario.stopWalking();
                break;
                
            case KeyEvent.VK_RIGHT:
                //isMoving=false;
                mario.stopWalking();
                break;
                
            case KeyEvent.VK_SPACE:
                released=true;
                break;
        }
        
    }
    /*
    private void moveMario(float walkingSpeed){
        while(isMoving){
                mario.move(new Vec2(walkingSpeed,0));
            }
    }*/
}
