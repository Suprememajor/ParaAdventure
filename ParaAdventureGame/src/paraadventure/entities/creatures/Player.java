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


public class Player extends Creature {
    
    //Animations
    private Animation animDown , animUp, animLeft , animRight;
    //AttackTimer
    private long lastAttackTimer, attackCooldown = 800 , attackTimer = attackCooldown;
    //Inventory
    private Inventory inventory;
    //Direction
    private Direction direction = Direction.Down;
    //Standing Image
    private BufferedImage[] standing = new BufferedImage[4];

    public enum Direction{
        Up, Down, Left, Right
    }//provide current direction of player
    
    public Player(Handler handler, float x, float y) {
        super(handler,x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        
        bounds.x = 11;
        bounds.y = 16;
        bounds.height = 16;
        bounds.width = 10;
        
        //Animations
        animDown = new Animation(500,Assets.playerDown);
        animUp = new Animation(500,Assets.playerUp);
        animLeft = new Animation(500,Assets.playerLeft);
        animRight = new Animation(500,Assets.playerRight);

        //standing images
        standing[0] = Assets.playerUp[1];
        standing[1] = Assets.playerRight[1];
        standing[2] = Assets.playerDown[1];
        standing[3] = Assets.playerLeft[1];

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

        if(handler.getKeyManager().attack){
            switch (direction){
                case Down:
                    ar.x =cb.x + cb.width / 2 - arSize / 2;
                    ar.y = cb.y + cb.height;
                    break;
                case Up:
                    ar.x =cb.x + cb.width / 2 - arSize / 2;
                    ar.y = cb.y - arSize;
                    break;
                case Left:
                    ar.x =cb.x - arSize;
                    ar.y = cb.y - cb.height / 2 - arSize /2;
                    break;
                case Right:
                    ar.x =cb.x + cb.width;
                    ar.y = cb.y - cb.height / 2 - arSize /2;
                    break;
            }
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

    /**
     * receives user input and aids in moving player
     */

    private void getInput(){
        if(inventory.isActive())
            return;
        
        xMove = 0;
        yMove = 0;
        
        if(handler.getKeyManager().up){
            yMove= -speed;
            direction = Direction.Up;
        }else if(handler.getKeyManager().down){
            yMove= speed;
            direction = Direction.Down;
        }
        if(handler.getKeyManager().left){
            xMove= -speed;
            direction = Direction.Left;
        }else if(handler.getKeyManager().right){
            xMove= +speed;
            direction = Direction.Right;
        }
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
        }else if(yMove > 0){
            return animDown.getCurrentFrame();
        }else{
            switch (direction){
                case Down:
                    return standing[2];
                case Up:
                    return standing[0];
                case Left:
                    return standing[3];
                case Right:
                    return standing[1];
            }
        }
        return null;
    }

    public Inventory getInventory() {
        return inventory;
    }
    

}