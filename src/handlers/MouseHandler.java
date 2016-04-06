/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import bodies.BowlingBall;
import city.cs.engine.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Pietro
 */
public class MouseHandler extends MouseAdapter{
    
    private WorldView view;
    private Shape ballShape;
    private BodyImage ballImage;
    private BowlingBall ball;

    public MouseHandler(WorldView view) {
        this.view = view;
        float radius = 0.5f;
        ballShape = new CircleShape(radius);
        ballImage = new BodyImage("data/bowl.png", 2*radius);
    }

    /**
     * Create a bowling ball at the current mouse position.
     * @param e event object containing the mouse position
     */
    @Override
    public void mousePressed(MouseEvent e) {
        ball = new BowlingBall(view.getWorld());
        ball.setPosition(view.viewToWorld(e.getPoint()));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    /** this method will pause the game in case that we click outside of the window
     * @param e */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    public BowlingBall getBall() {
        return this.ball;
    }
    
    
    
}
