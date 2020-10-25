/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paraadventure.worlds;

import java.awt.Graphics;
import paraadventure.Handler;
import paraadventure.entities.EntityManager;
import paraadventure.entities.creatures.Player;
import paraadventure.entities.creatures.Zombie;
import paraadventure.entities.statics.Rock;
import paraadventure.entities.statics.Tree;
import paraadventure.items.ItemManager;
import paraadventure.tiles.Tile;
import paraadventure.utils.Utils;

public class World {
    private Handler handler;
    private int width, height;
    private int[][] tiles;
    private int spawnX, spawnY;
    
    //Entities
    private EntityManager entityManager;
    
    //Item 
    private ItemManager itemManager;
    
    public World(Handler handler,String path){
        this.handler = handler;
        
        entityManager = new EntityManager(handler , new Player(handler,100,20));
        itemManager = new ItemManager(handler);
        entityManager.addEntity(new Tree(handler, 320, 300));
        entityManager.addEntity(new Tree(handler, 320, 400));
        entityManager.addEntity(new Rock(handler, 220, 420));
        entityManager.addEntity(new Zombie(handler, 340, 400));
        entityManager.addEntity(new Zombie(handler, 220, 350));
        
        loadWorld(path);
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }
    
    public void tick(){
        itemManager.tick();
        entityManager.tick();
    }
    public void render(Graphics g){
        
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1 );
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1 );
        
        for(int y=yStart;y<yEnd;y++){
            for(int x=xStart;x<xEnd;x++){
                getTile(x,y).render(g,(int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset() ),(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
        itemManager.render(g);
        entityManager.render(g);
    }
    
    public Tile getTile(int x, int y){
        
        if(x < 0 || y < 0 || x > width || y > height )
            return Tile.grassTile;
        
        Tile t = Tile.tiles[tiles[x][y]];
        if(t==null)
            return Tile.dirtTile;
        return t;
    }
    
    private void loadWorld(String path){
        String file = Utils.loadFileAsString(path);
        String[] token = file.split("\\s+");
        width = Utils.parseInt(token[0]);
        height = Utils.parseInt(token[1]);
        spawnX = Utils.parseInt(token[2]);
        spawnY = Utils.parseInt(token[3]);
        
        tiles = new int[width][height];
        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
                tiles[x][y] = Utils.parseInt(token[(x + y * width) + 4]);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }
    
}
