/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package paraadventure;

import paraadventure.gfx.GameCamera;
import paraadventure.input.KeyManager;
import paraadventure.input.MouseManager;
import paraadventure.worlds.World;

/**
 *
 * @author Suprememajor
 */
public class Handler {
    
    private Game game;
    private World world;
    
    public Handler(Game game){
        this.game = game;
    }
    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }
    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }
    public GameCamera getGameCamera(){
        return game.getGameCamera();
    }
    
    public int getWidth(){
        return game.getWidth();
    }
    public int getHeight(){
        return game.getHeight();
    }

    
    //SETTERS AND GETTERS

    public void setWorld(World world) {
        this.world = world;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public Game getGame() {
        return game;
    }
}
