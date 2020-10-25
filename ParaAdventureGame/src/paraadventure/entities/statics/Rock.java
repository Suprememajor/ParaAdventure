/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package paraadventure.entities.statics;

import java.awt.Graphics;
import paraadventure.Handler;
import paraadventure.gfx.Assets;
import paraadventure.items.Item;
import paraadventure.tiles.Tile;

/**
 *
 * @author Suprememajor
 */
public class Rock extends StaticEntity {

    public Rock(Handler handler, float x, float y) {
        super(handler, x, y,  Tile.TILEWIDTH, Tile.TILEHEIGHT);
        
        bounds.x = 8;
        bounds.y =(int) (height/1.5f) - 14 ;
        bounds.height = (int) (height -  height/1.5f) + 12;
        bounds.width = width - 15;
    }

    @Override
    public void tick() {

    }
    public void die(){
        handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int)x,(int) y));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.rock,(int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()),width, height, null);
        //g.setColor(Color.red);
        //g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
        }

    
}
