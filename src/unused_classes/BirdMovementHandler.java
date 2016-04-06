/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unused_classes;

import city.cs.engine.WorldView;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import javax.swing.Timer;
import org.jbox2d.common.Vec2;

/**
 *
 * @author Pietro
 */
public class BirdMovementHandler extends KeyAdapter{
    
    private WorldView view;
    private Bird bird;
    private Vec2 previousPosition;
    private boolean isMoving=false;
    float x, y;                   // Ball position.
    int dx, dy;                   // Directions: -1 = left/down --- 0 = still --- 1 = right/up
    float speed;
    
    
    
    public BirdMovementHandler(WorldView view, Bird bird) {
        this.view = view;
        this.bird=bird;
        this.previousPosition= bird.getPosition();
        this.x=bird.getPosition().x;
        this.y=bird.getPosition().y;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyChar()){
            case 'w':
                dy=1;
                break;
            case 'a':
                dx=-1;
                break;
            case 'd':
                dx=1;
                break;
        }
    }
    
    
    @Override
    public void keyReleased(java.awt.event.KeyEvent evt) {                                 
        switch(evt.getKeyChar()){
            case 'w':
                stopDrive(evt.getKeyChar());
                break;
            case 'a':
                stopDrive(evt.getKeyChar());
                break;
            case 'd':
                stopDrive(evt.getKeyChar());
                break;
        }
    } 
  
    
    //start
    void drive(char k) {
        if (k == 'w') {
          dy = 1;
        } else if (k == 'd' ) {
          dx = 1;
        } else if (k == 's') {
          dy = -1;
        } else if (k == 'a') {
          dx = -1;
        }
    }
    
    
    //stop
    void stopDrive(char k) {
        if (k == 'w' && dy == 1) {
          dy = 0;
        } else if (k == 'd' && dx == 1) {
          dx = 0;
        } else if (k == 's' && dy == -1) {
          dy = 0;
        } else if (k == 'a' && dx == -1) {
          dx = 0;
        }
    }

    public Bird getBird() {
        return bird;
    }

    public boolean isIsMoving() {
        return isMoving;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public float getSpeed() {
        return speed;
    }

    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
  
  
    
    
}
