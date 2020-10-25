/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package paraadventure.items;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import paraadventure.Handler;

/**
 *
 * @author Suprememajor
 */
public class ItemManager {

    private Handler handler;
    private ArrayList<Item> items;
    public ItemManager(Handler handler){
        this.handler = handler;
        items = new ArrayList<>();
    
    }
    public void tick(){
        Iterator<Item> it = items.iterator();
        while(it.hasNext()){
            Item i = it.next();
            i.tick();
            if(i.isPickedUp()){
                it.remove();
            }
        }
    }
    public void render(Graphics g){
        for(Item i : items)
            i.render(g);
    }
    public void addItem(Item i){
        i.setHandler(handler);
        items.add(i);
    }

    //Getters and Setters
    public Handler getHandler() {
        return handler;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    
}
