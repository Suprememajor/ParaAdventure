/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paraadventure.display;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Suprememajor
 */
public class Display {
    private JFrame frame;
    private Canvas canvas;
    private String title;
    private int width, height;
    
    public Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        
        createDisplay();
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public int getWidth() {
        return width;
    }

    public String getTitle() {
        return title;
    }

    public int getHeight() {
        return height;
    }

    public JFrame getFrame() {
        return frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }
    private void createDisplay(){
        frame = new JFrame(title);
        frame.setSize(width , height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setMaximumSize(new Dimension(width,height));
        canvas.setMinimumSize(new Dimension(width,height));
        
        canvas.setFocusable(false);
        
        frame.add(canvas);
        frame.pack();
    }
    
}
