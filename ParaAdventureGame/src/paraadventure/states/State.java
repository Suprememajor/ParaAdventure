/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paraadventure.states;

import java.awt.Graphics;
import paraadventure.Game;
import paraadventure.Handler;

/**
 *
 * @author Suprememajor
 */
public abstract class State {
    protected Handler handler;
    public State(Handler handler){
        this.handler = handler;
    }
    private static State currentState = null;

    public static State getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(State currentState) {
        State.currentState = currentState;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
}
