/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paraadventure.tiles;

import paraadventure.gfx.Assets;

/**
 *
 * @author Suprememajor
 */
public class Mountain extends Tile{
    
    public Mountain(int id) {
        super(Assets.mountain, id);
    }
    @Override
    public boolean isSolid(){
        return true;
    }
}
