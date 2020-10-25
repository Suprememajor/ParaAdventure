/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package paraadventure.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import paraadventure.ui.UIManager;

/**
 *
 * @author Suprememajor
 */
public class MouseManager implements MouseListener , MouseMotionListener{
    
    private boolean leftPressed, rightPressed;
    private int mouseX, mouseY;
    private UIManager uiManager;

    //GETTERS
    
    public void setUiManager(UIManager uiManager){
        this.uiManager = uiManager;
    }

    public boolean isLeftPresssed() {
        return leftPressed;
    }
    public boolean isRightPressed(){
        return rightPressed;
    }
    public int getMouseY() {
        return mouseY;
    }

    public int getMouseX() {
        return mouseX;
    }
    
    //Implemented
    public MouseManager(){
    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
            leftPressed = true;
        else if(e.getButton() == MouseEvent.BUTTON3)
            rightPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
            leftPressed = false;
        else if(e.getButton() == MouseEvent.BUTTON3)
            rightPressed = false;
        
        if(uiManager != null)
            uiManager.onMouseRelease(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        
        if(uiManager != null)
            uiManager.onMouseMove(e);
    }
}
