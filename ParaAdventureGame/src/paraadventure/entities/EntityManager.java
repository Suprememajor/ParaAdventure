/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package paraadventure.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import paraadventure.Handler;
import paraadventure.entities.creatures.Player;

/**
 *
 * @author Suprememajor
 */
public class EntityManager {

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities;
    private Comparator<Entity> renderSorter = (a, b) -> {
        if (a.getY() + a.getHeight() < b.getY() + b.getHeight())
            return -1;
        return 1;
    };
    
    public EntityManager(Handler handler, Player player){
        this.handler = handler;
        this.player = player;
        entities = new ArrayList<>();
        entities.add(player);
    }
    public void tick(){
        Iterator<Entity> it = entities.iterator();
        while(it.hasNext()){
            Entity e = it.next();
            e.tick();
            if(!e.isActive())
                it.remove();
        }
        entities.sort(renderSorter);
    }
    public void render(Graphics g){
        for(Entity e : entities){
            e.render(g);
        }
        player.postRender(g);
        
    }
    public void addEntity(Entity e){
        entities.add(e);
    }
    
    //SETTERS AND GETTERS

    public Player getPlayer() {
        return player;
    }

    public Handler getHandler() {
        return handler;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}