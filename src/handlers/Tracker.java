package handlers;

import bodies.BowlingBall;
import bodies.Goomba;
import bodies.SuperMario;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Pan the view to follow a particular body.
 */
public class Tracker implements StepListener {
    /** The view */
    private WorldView view;

    /** The body */
    private BowlingBall ball = null;
    private SuperMario body = null;
    private Goomba goomba;
    private boolean direction  = false;
    private float p= 12.1f;
    
    public Tracker(WorldView view, SuperMario body) {
        this.view = view;
        this.body = body;
    }
    
    public Tracker(WorldView view, BowlingBall body) {
        this.view = view;
        this.ball = body;
    }
    
    public Tracker(WorldView view, Goomba body) {
        this.view = view;
        this.goomba = body;
    }

    /**
     * stop the character to go outside the view before each step(at the moment hard-coded because I can't figure out how to get the size of the view)
     * @param e the step event 
     */
    @Override
    public void preStep(StepEvent e) {
        //if exist work with mario
        if(body != null){
            if(body.getPosition().x < -p && !body.getDirection()){
                body.setPosition(new Vec2(-p,body.getPosition().y));
            }
            else if((body.getPosition().x > p && body.getDirection())){
                body.setPosition(new Vec2(p,body.getPosition().y));
            }
            else if(body.getPosition().y > p ){
                body.setPosition(new Vec2(body.getPosition().x, p-0.3f));
                body.setLinearVelocity(new Vec2(body.getLinearVelocity().x, 0 ));
            }
        }
        
        
        // if exist work with the bowling ball
        if(ball != null){
            if(ball.getPosition().x < -p){
                ball.setLinearVelocity(new Vec2(ball.getLinearVelocity().negate().x, ball.getLinearVelocity().y));
            }
            else if(ball.getPosition().x > p){
                ball.setLinearVelocity(new Vec2(-ball.getLinearVelocity().x, ball.getLinearVelocity().y));
            }
        }
        
        
        //if exist work with goomba
        if(goomba != null){
            //System.out.println("goomba: "+goomba.getPosition().x);
            if(goomba.getPosition().x < -10.7f){
                goomba.setLinearVelocity(new Vec2(6.5f, goomba.getLinearVelocity().y));
            }
            else if(goomba.getPosition().x > (-10+6.7f)){
                //System.out.println(goomba.getLinearVelocity().x);
                goomba.setLinearVelocity(new Vec2(-6.5f, goomba.getLinearVelocity().y));
            }
        }
    }
    
    /**
     * stop the character to go outside the view after each step(at the moment hard-coded because I can't figure out how to get the size of the view)
     * @param e the step event 
     */
    @Override
    public void postStep(StepEvent e) {
        //view.setCentre(new Vec2(body.getPosition()));
        if(body != null){
            if(body.getPosition().x < -p && !body.getDirection()){
                body.setPosition(new Vec2(-p,body.getPosition().y));
            }
            else if((body.getPosition().x > p && body.getDirection())){
                body.setPosition(new Vec2(p,body.getPosition().y));
            }
            else if(body.getPosition().y > p ){
                body.setPosition(new Vec2(body.getPosition().x, p-0.3f));
                body.setLinearVelocity(new Vec2(body.getLinearVelocity().x, 0 ));
            }
        }
        
        
    }
    
}
