/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worlds;

import bodies.SuperMario;
import city.cs.engine.UserView;
import city.cs.engine.World;
import java.awt.Graphics;

/**
 *
 * @author Pietro
 */
public class DrawableView extends UserView{

    private SuperMario mario;
    
    public DrawableView(World w, int width, int height) {
        super(w, width, height);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Tasty", (int) mario.getPosition().addLocal(1, 0).x, (int) mario.getPosition().addLocal(1, 0).y);
    }
    
    public void setPlayer(SuperMario mario){
        this.mario=mario;
    }
    
}
