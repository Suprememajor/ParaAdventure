/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package paraadventure.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import paraadventure.Handler;
import paraadventure.gfx.Animation;
import paraadventure.gfx.Assets;

/**
 *
 * @author Suprememajor
 */
public class Zombie extends Creature {
    //Animations
    private Animation animDown , animUp, animLeft , animRight;
    //movementTimer
    private long lastMovementTimer, movementCooldown = 1000000 , movementTimer = movementCooldown;
    
    private Random rand = new Random();

    public Zombie(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        
        bounds.x = 11;
        bounds.y = 16;
        bounds.height = 16;
        bounds.width = 10;
        
        xMove = speed;
        yMove = speed;
        
        //Animations
        animDown = new Animation(500,Assets.zombie_down );
        animUp = new Animation(500,Assets.zombie_up );
        animLeft = new Animation(500,Assets.zombie_left );
        animRight = new Animation(500,Assets.zombie_right );
    }

    @Override
    public void tick() {
        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();
        getInput();
        move();    
    }
    private void getInput(){
        movementTimer += System.currentTimeMillis() - lastMovementTimer;
        lastMovementTimer = System.currentTimeMillis();
        if(movementTimer < movementCooldown)
            return;
        int control = rand.nextInt(4);
        if(control == 0)
            xMove = 1;//speed;
        else if(control == 1)
            xMove = -1;//-speed;
        else if(control == 2)
            yMove = 1;//speed;
        else
            yMove = -1;//speed;
        
        
    }

    @Override
    public void render(Graphics g) {
    g.drawImage(getCurrentAnimationFrame(),(int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()), null);
        }

    @Override
    public void die() {
        
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
}
