/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paraadventure.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import paraadventure.Handler;
import paraadventure.entities.Entity;
import paraadventure.gfx.Animation;
import paraadventure.gfx.Assets;
import paraadventure.inventory.Inventory;

/**
 *
 * @author ngan
 */
public class Player extends Creature {
    
    //Animations
    private Animation animDown , animUp, animLeft , animRight;
    //AttackTimer
    private long lastAttackTimer, attackCooldown = 800 , attackTimer = attackCooldown;
    //Inventory
    private Inventory inventory;
    
    public Player(Handler handler, float x, float y) {
        super(handler,x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        
        bounds.x = 11;
        bounds.y = 16;
        bounds.height = 16;
        bounds.width = 10;
        
        //Animations
        animDown = new Animation(500,Assets.player_down );
        animUp = new Animation(500,Assets.player_up );
        animLeft = new Animation(500,Assets.player_left );
        animRight = new Animation(500,Assets.player_right );
        
        inventory = new Inventory(handler);
        
    }

    @Override
    public void tick() {
        
        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        
        //Attack
        checkAttack();
        inventory.tick();
        
    }
    private void checkAttack(){
        if(inventory.isActive())
            return;
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCooldown)
            return;
        
        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize = 20;
        ar.height  = arSize;
        ar.width  = arSize;
        
        if(handler.getKeyManager().aUp){
            ar.x =cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
        }else if(handler.getKeyManager().aDown){
            ar.x =cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
        }else if(handler.getKeyManager().aLeft){
            ar.x =cb.x - arSize;
            ar.y = cb.y - cb.height / 2 - arSize /2;
        }else if(handler.getKeyManager().aRight){
            ar.x =cb.x + cb.width;
            ar.y = cb.y - cb.height / 2 - arSize /2;
        }else{
            return;
        }
        
        attackTimer = 0;
        
        for (Entity e : handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0, 0).intersects(ar)){
                e.hurt(1);
                return;
            }
        
        }
    }

    private void getInput(){
        if(inventory.isActive())
            return;
        
        xMove = 0;
        yMove = 0;
        
        if(handler.getKeyManager().up)
            yMove= -speed;
        if(handler.getKeyManager().down)
            yMove= speed;
        if(handler.getKeyManager().left)
            xMove= -speed;
        if(handler.getKeyManager().right)
            xMove= +speed;
    }
    @Override
    public void die(){
        System.out.println("You lose");
    }
    
    @Override
    public void render(Graphics g) {
    g.drawImage(getCurrentAnimationFrame(),(int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), null);
    
    //g.setColor(Color.red);
    //g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
    }
    
    public void postRender(Graphics g){
        inventory.render(g);
    }
    
    private BufferedImage getCurrentAnimationFrame(){
        if(xMove < 0 ){
            return animLeft.getCurrentFrame();
        }else if(xMove > 0){
            return animRight.getCurrentFrame();
        }else if(yMove < 0){
            return animUp.getCurrentFrame();
        }else{
            return animDown.getCurrentFrame();
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
    

}