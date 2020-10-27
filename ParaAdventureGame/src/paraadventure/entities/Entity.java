/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paraadventure.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import paraadventure.Handler ;

/**
 *
 * @author Suprememajor
 */
public abstract class Entity {
    public static final int DEFAULT_HEALTH = 3;
    protected int health;
    protected Handler handler;
    protected float x, y;
    protected int width,height;
    protected Rectangle bounds;
    protected boolean active = true;
    
    public Entity(Handler handler,float x,float y, int width, int height){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        health = DEFAULT_HEALTH;
        
        bounds = new Rectangle(0,0,width,height);
    }
    public boolean checkEntityCollision(float xOffset , float yOffset){
        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))){
                return true;                
            }
        }
        return false;
    }
    
    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int)(x + bounds.x + xOffset), (int)(y + bounds.y + yOffset),
                bounds.width, bounds.height);
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
    public abstract void tick();
    public abstract void render(Graphics g);
    public void hurt(int amt){
        health -= amt;
        if(health <= 0){
            active = false;
            die();
        }
    }  
    public abstract void die();
        
}
