package worlds;

import bodies.BowlingBall;
import bodies.BonusLife;
import bodies.Goomba;
import city.cs.engine.*;
import bodies.Orange;
import handlers.Pickup;
import bodies.SuperMario;
import handlers.Tracker;
import interfaces.Lifes;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.jbox2d.common.Vec2;
import platforms.LifePlatform;

/**
 *
 * @author Lazza
 */

/**
 * A world with some bodies.
 */
public class GameWorld extends World {
    
    private SuperMario mario;
    private GameWorld world;
    private BowlingBall ball;
    private ArrayList<Lifes> heartsList;
    private JFrame frame;
    private UserView currentView;
    private StepListener stepListener;
    private Goomba goomba;
    
    public GameWorld() {
        super();
        world = this;
        
        { // make the ground
            Shape shape = new BoxShape(13, 0.5f);
            Body ground = new StaticBody(this, shape);
            ground.setPosition(new Vec2(0, -12f));
            ground.setName("Ground");
            // walls
         /*   Shape leftWallShape = new BoxShape(0.5f, 6, new Vec2(-11.5f, 5.5f));
            Fixture leftWall = new SolidFixture(ground, leftWallShape);
            Shape rightWallShape = new BoxShape(0.5f, 6, new Vec2(11.5f, 5.5f));
            Fixture rightWall = new SolidFixture(ground, rightWallShape);*/
        }

        { // make platforms
            Shape shape = new BoxShape(4, 0.5f);
            Body platform1 = new StaticBody(this, shape);
            platform1.setPosition(new Vec2(-7, 5.5f));
            platform1.setName("Platform");
            
            Shape shape1 = new BoxShape(2f, 0.5f);
            Body platform2 = new StaticBody(this, shape1);
            platform2.setPosition(new Vec2(-5.5f, -5.5f));
            platform2.setName("Platform");
            
                Shape shape2 = new BoxShape(0.5f, 0.5f);
            Body platform3 =new StaticBody(this, shape1);
            platform3.setPosition(new Vec2(3.5f, 4));
            platform3.setName("Platform");
                
            for (int i = 0; i < 5; i++) {
                if(i%2 == 1){
                    //
                    LifePlatform platform = new LifePlatform(this,shape2, i, mario);
                    //System.out.println("lifesss: "+platform.getPosition());
                    
                    platform.addCollisionListener(new Pickup(mario){
                        @Override
                        public void collide(CollisionEvent e) {
                            if (e.getOtherBody() == mario && !platform.isDone() &&
                                mario.getPosition().y < e.getReportingBody().getPosition().y) {
                                // create instance of a new object bonus
                                BonusLife life = new BonusLife(world,e.getReportingBody().getPosition());
                                life.addCollisionListener(new Pickup(mario){
                                    @Override
                                    public void collide(CollisionEvent e){
                                        //if mario collide with the life bonus then he gets an extra life
                                        if (e.getOtherBody() == mario && heartsList.size()< 10){
                                            Lifes life = new Lifes(world);
                                            life.setPosition(heartsList.get(heartsList.size()-1).getPosition().add(new Vec2(1,0)));
                                            heartsList.add(life);
                                            e.getReportingBody().destroy();
                                        }
                                    }
                                });
                                platform.setDone(true);
                                platform.setFillColor(Color.GRAY);
                                /* Debug messages */
                                //System.out.println("Mario position: "+e.getOtherBody().getPosition().y);
                                //System.out.println("P position: "+e.getReportingBody().getPosition().y);
                            }
                        }
                    });
                }
                else{
                    Body platform = new StaticBody(this, shape2);
                    platform.setPosition(new Vec2(4+i, -2.5f));
                    platform.setName("Platform");
                    //System.out.println("normal: "+platform.getPosition());
                }
            }
        }

        { // make a character
            mario = new SuperMario(this);
            mario.setPosition(new Vec2(0, -10));
            mario.addCollisionListener(new CollisionListener(){

                @Override
                public void collide(CollisionEvent e) {
                    //System.out.println(e.getOtherBody().getName());
                    
                    // if mario touches the ball and there is at least one life(heart object) 
                    if(e.getOtherBody().getName().equals("Ball") && heartsList.size()>0){
                        ball=(BowlingBall)e.getOtherBody();
                        if(!ball.getTouchedMario()){
                            
                            //hide one heart from the view
                            heartsList.get(heartsList.size()-1).destroy();
                            heartsList.remove(heartsList.size()-1);
                            ball.setTouchedMario(true);
                            //System.out.println("heartsList.size: "+heartsList.size());
                        } 
                    }else if(e.getOtherBody().getName().equals("Goomba") && heartsList.size()>0){
                        goomba=(Goomba)e.getOtherBody();
                        if(!goomba.getTouchedMario()){
                            
                            //for each element --> destroy();
                            //this code doesn't hide the hearts from the view even it's the same logic of when the bowling ball hit mario.... 
                            for(int i=heartsList.size(); i>0; i--){
                                //System.out.println(heartsList.get(i-1));
                                Lifes life = heartsList.get(i-1);
                                life.destroy();
                                heartsList.remove(i-1);
                                goomba.setTouchedMario(true);
                            }
                        }
                    }
                    
                    
                    // if there are no more heart in the list destroy mario
                    if(heartsList.isEmpty()){
                        
                        mario.destroy();
                        try {
                            Thread.sleep(500);
                            frame.setVisible(false);
                            //open the final JFrame
                            new GameOver("Game Over").setVisible(true);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(GameWorld.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
        }
        
        {
            //gomba creation
            goomba = new Goomba(this,new Vec2(-9, 6.5f));
            goomba.setLinearVelocity(new Vec2(6.5f,0));
            
        }
        
        {   
            //oranges creation
            for (int i = 0; i < 11; i++) {
                Random r = new Random();
                float x = r.nextInt(25)-12;
                float y = r.nextInt(6) +5.5f;
                
                Body orange = new Orange(this,0);
                orange.setPosition(new Vec2(x, y));
                orange.addCollisionListener(new Pickup(mario));
                
            }
        }
        
        {
            // life's interface 
            heartsList = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Lifes life = new Lifes(world);
                life.setPosition(new Vec2(-12, 12).add(new Vec2(i,0)));
                //System.out.println(life.getPosition().x +" and "+ life.getPosition().y);
                heartsList.add(life);
            }
        }
        
        {
            // this code should create in different moments a ball in a random x posistion
            
            /* these lines don't work, I guess because the setView() is call after the creation 
               of the GameWorld but I'm using it in the constructor, before that the setView() is called in the Game class*/
            
          //  int high = currentView.getWidth();
          //  int low = currentView.getX();
          //  System.out.println("high: " +high);
          //  System.out.println("low: "+ low);
            
            Random r = new Random();
            float randomPosition = r.nextInt(5) +6.5f;// between the lowest platform and the ground range[7.5,11.5]
            float randomVelocity  = r.nextInt(10)+5; //range [5,15]
            //System.out.println(-randomPosition);
            //System.out.println(randomVelocity);
            ball = new BowlingBall(this);
            ball.setPosition(new Vec2(12,-randomPosition));
            ball.setGravityScale(0);
            ball.setLinearVelocity(new Vec2(-randomVelocity,0));
            
            
            /**
             * if the previous ball hits mario then create another one
             * but it doesn't work I have to restructure the logic
            ball.addDestructionListener(new DestructionListener(){

                @Override
                public void destroy(DestructionEvent e) {
                    Random r = new Random();
                    float randomPosition = r.nextInt(6) +5.6f;
                    float randomVelocity  = r.nextInt(20);
                    System.out.println(-randomPosition);
                    System.out.println(randomVelocity);
                    Ball b = new Ball(world);
                    b.setPosition(new Vec2(12,-randomPosition));
                    b.setGravityScale(0);
                    b.setLinearVelocity(new Vec2(-randomVelocity,0));
                    b.addDestructionListener(this);
                    ball=b;
                }
            });*/
            
            
            
            
            /*
            while(this.isRunning()){
                Random r = new Random();
                int high = frame.getBounds().width;
                int low = frame.getBounds().x;
                float randomSeconds = r.nextInt(10)*100;
                try {
                    Thread.sleep((long) randomSeconds);
                    ball = new Ball(this);
                    ball.setGravityScale(0);
                    ball.setPosition(new Vec2(13,-10));
                    ball.setLinearVelocity(new Vec2(-10,0));
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameWorld.class.getName()).log(Level.SEVERE, null, ex);
                }
            }*/
        
        }
        
        addStepListener(new Tracker(currentView,ball));
        addStepListener(new Tracker(currentView,goomba));
        
    }
    //
    public void setView(UserView uv){
        currentView = uv;
    }
    public SuperMario getPlayer() {
        return mario;
    }

    public void setStepListener(StepListener stepListener) {
        this.stepListener = stepListener;
    }
    
    public StepListener getStepListener(){
        
        return stepListener;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
    
}
