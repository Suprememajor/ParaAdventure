/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paraadventure.states;

import java.awt.Graphics;
import paraadventure.Handler;
import paraadventure.gfx.Assets;
import paraadventure.ui.ClickListener;
import paraadventure.ui.UIImageButton;
import paraadventure.ui.UIManager;

/**
 *
 * @author ngan
 */
public class MenuState extends State{
    private UIManager uiManager;
    
    public MenuState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
         handler.getMouseManager().setUiManager(uiManager);
        
        uiManager.addObject(new UIImageButton(200,200,164,82,Assets.btn_start,new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(uiManager);
                State.setCurrentState(handler.getGame().gameState);
            }
        }));
       
     }

    @Override
    public void tick() {
       uiManager.tick();
       
       handler.getMouseManager().setUiManager(null);
       State.setCurrentState(handler.getGame().gameState);
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
    
}
