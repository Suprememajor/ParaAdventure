/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paraadventure.states;

import java.awt.Graphics;
import paraadventure.Handler;
import paraadventure.worlds.World;

/**
 *
 * @author ngan
 */
public class GameState extends State {
    
    private World world;
    
    
    public GameState(Handler handler){
        super(handler);
        
        world = new World(handler,"res/worlds/world1.txt");
        handler.setWorld(world);
        
       
    }

    @Override
    public void tick() {
        world.tick();
        
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
       
    }
    
}
