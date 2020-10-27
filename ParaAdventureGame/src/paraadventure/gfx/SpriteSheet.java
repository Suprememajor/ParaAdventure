/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paraadventure.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author Suprememajor
 */
public class SpriteSheet {
    private BufferedImage sheet;
    public SpriteSheet(BufferedImage sheet){
        this.sheet = sheet;
    }

    /**
     *
     * @param x Horizontal starting point
     * @param y Vertical starting point
     * @param width Width of cropped image
     * @param height Height of cropped image
     * @return cropped BufferedImage
     */
    public BufferedImage crop(int x,int y,int width, int height){
        return sheet.getSubimage(x, y, width, height);
    }
}
