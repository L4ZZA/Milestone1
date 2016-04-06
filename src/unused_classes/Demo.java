/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unused_classes;

import interfaces.Lifes;
import bodies.BowlingBall;
import handlers.MouseHandler;
import city.cs.engine.Body;
import city.cs.engine.BoxShape;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.UserView;
import city.cs.engine.World;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.Timer;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Pietro
 */
public class Demo {
    /** The World in which the bodies move and interact. */
    private World world;

    /** A graphical display of the world (a specialised JPanel). */
    private UserView view;
    
    ArrayList<Lifes> heartsList;
    BowlingBall ball;
    Bird bird;
    ActionListener timerMuoviTask;
    
    public Demo(){  
        world = new World();

        { // make the ground
            Shape shape = new BoxShape(11, 0.5f);
            Body ground = new StaticBody(world, shape);
            ground.setPosition(new Vec2(0, -12f));
            ground.setName("Ground");
        }

        { // make a platform
            Shape shape = new BoxShape(4, 0.5f);
            Body platform1 = new StaticBody(world, shape);
            platform1.setPosition(new Vec2(-9, 5.5f));
            platform1.setName("Platform");

          // add another platform here
            Shape shape1 = new BoxShape(6.5f, 0.5f);
            Body wall = new StaticBody(world, shape1);
            Body wall2 = new StaticBody(world, shape1);
            wall.setPosition(new Vec2(-10.5f, -5f));
            wall.setAngleDegrees(-90);
            wall2.setPosition(new Vec2(10.5f, -5f));
            wall2.setAngleDegrees(-90);
            wall.setName("Wall");
            wall2.setName("Wall");
                    
          
            heartsList = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Lifes life = new Lifes(world);
                life.setPosition(new Vec2(-12, 12).add(new Vec2(i,0)));
                //System.out.println(life.getPosition().x +" and "+ life.getPosition().y);
                heartsList.add(life);
                
            }
            
        }


        Block block = new Block(world);
        bird = new Bird(world);



        // make a view
        view = new UserView(world, 500, 500);
    
        
        // add some mouse actions
        // add this to the view, so coordinates are relative to the view
        MouseHandler click = new MouseHandler(view);
        view.addMouseListener(click);
        ball = click.getBall();
        
        bird.addCollisionListener(new CollisionListener(){

            @Override
            public void collide(CollisionEvent e) {
                //System.out.println(e.getOtherBody().getName());
                if(e.getOtherBody().getName().equals("Ball") && heartsList.size()>0){
                    ball=(BowlingBall)e.getOtherBody();
                    if(!ball.getTouchedMario()){
                        heartsList.get(heartsList.size()-1).destroy();
                        heartsList.remove(heartsList.size()-1);
                        ball.setTouchedMario(true);
                        
                        System.out.println("heartsList.size: "+heartsList.size());
                    } 
                }
                
                if(heartsList.size() == 0){
                    bird.destroy();
                }
                
                
                if(!world.getDynamicBodies().contains(bird)) 
                    world.stop();
            }
        });
        
        
        
        
                
        
        
        
       // display the view in a frame
        final JFrame frame = new JFrame("Basic world");

        // quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // display the world in the window
        frame.add(view);
        // don't let the game window be resized
        frame.setResizable(false);
        // size the game window to fit the world view
        frame.pack();
        // make the window visible
        frame.setVisible(true);

        
        
        //frame.addKeyListener(birdMoving);
        
        
        // uncomment this to make a debugging view
        // JFrame debugView = new DebugViewer(world, 500, 500);
        
        
        // start!
        world.start();
    }
}
