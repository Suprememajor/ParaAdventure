/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package paraadventure.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import paraadventure.Handler;
import paraadventure.gfx.Assets;
import paraadventure.gfx.Text;
import paraadventure.items.Item;

/**
 *
 * @author Suprememajor
 */
public class Inventory {
    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;
    
    private int invX= 64, invY = 48;
    private int invWidth = 512, invHeight = 384 ,
            invListCenterX = invX + 171,
            invListCenterY = invY + invHeight/ 2 + 15,
            invListSpacing = 30;
    
    private int invImageX = 452, invImageY = 82,
            invImageWidth = 64, invImageHeight = 64;
    private int invCountX = 484, invCountY = 182;
    
    private int selectedItem = 0;
    
    public Inventory(Handler handler){

        this.handler = handler;
        inventoryItems = new ArrayList<>();
        
    }
    public void tick(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
            active = !active;
        
        if(!active)
            return;
        
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W))
            selectedItem--;
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S))
            selectedItem++;
        
        if(selectedItem < 0)
            selectedItem = inventoryItems.size() -1 ;
        else if(selectedItem >= inventoryItems.size())
            selectedItem = 0;
        
        
    }
    public void render(Graphics g){
        if(!active)
            return;
        
        g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);
        
        int len = inventoryItems.size();
        if(len == 0)
            return;
        
        for(int i = -5 ; i < 6 ; i++){
            if(selectedItem + i < 0 || selectedItem + i >= len)
                continue;
            if(i == 0){
                Text.drawString(g,"> " + inventoryItems.get(selectedItem + i).getName() + " <", invListCenterX,
                    invListCenterY + i * invListSpacing, true, Color.yellow, Assets.font20);
            }else{
                Text.drawString(g, inventoryItems.get(selectedItem + i).getName(), invListCenterX,
                    invListCenterY + i * invListSpacing, true, Color.white, Assets.font20);
            }            
        }
        Item item = inventoryItems.get(selectedItem);
        g.drawImage(item.getTexture(), invImageX,invImageY , invImageWidth ,invImageHeight, null);
        Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true, Color.white, Assets.font20);
    }
    public void addItem(Item item){
        for(Item i : inventoryItems){
            if(i.getId() == item.getId()){
                i.setCount(i.getCount() + item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
}
