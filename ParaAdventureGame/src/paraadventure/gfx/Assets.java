/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paraadventure.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Iterator;


public class Assets {
    private static final int WIDTH = 32, HEIGHT = 32;
    public static BufferedImage mountain,clayMountain, grassHill;
    public static BufferedImage[] playerDown, playerUp, playerLeft, playerRight; //BufferedImages of player
    public static BufferedImage[] skeletonDown, skeletonUp, skeletonLeft, skeletonRight;
    public static BufferedImage[] forest, grass, redSand, road, sandyRoad, water;//BufferedImages of tiles
    public static BufferedImage inventoryScreen;
    
    public static Font font28, font20;
        
    public static void init(){
        //Initializing fonts
        font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
        font20 = FontLoader.loadFont("res/fonts/slkscr.ttf", 20);

        //SpriteSheets
        SpriteSheet player = new SpriteSheet(ImageLoader.loadImage("/textures/character/adventurer.png"));
        SpriteSheet skeleton = new SpriteSheet(ImageLoader.loadImage("/textures/character/skeleton.png"));
        SpriteSheet mountainSprite = new SpriteSheet(ImageLoader.loadImage("/textures/terrain/mountain.png"));
        SpriteSheet clayMountainSprite = new SpriteSheet(ImageLoader.loadImage("/textures/terrain/clayMountain.png"));
        SpriteSheet grassHillSprite = new SpriteSheet(ImageLoader.loadImage("/textures/terrain/grassHill.png"));
        SpriteSheet forestSprite = new SpriteSheet(ImageLoader.loadImage("/textures/terrain/forest.png"));
        SpriteSheet forest2Sprite = new SpriteSheet(ImageLoader.loadImage("/textures/terrain/forest2.png"));
        SpriteSheet grassSprite = new SpriteSheet(ImageLoader.loadImage("/textures/terrain/grass.png"));
        SpriteSheet grass2Sprite = new SpriteSheet(ImageLoader.loadImage("/textures/terrain/grass2.png"));
        SpriteSheet redSandSprite = new SpriteSheet(ImageLoader.loadImage("/textures/terrain/redSand.png"));
        SpriteSheet redSand2Sprite = new SpriteSheet(ImageLoader.loadImage("/textures/terrain/redSand2.png"));
        SpriteSheet roadSprite = new SpriteSheet(ImageLoader.loadImage("/textures/terrain/road.png"));
        SpriteSheet road2Sprite = new SpriteSheet(ImageLoader.loadImage("/textures/terrain/road2.png"));
        SpriteSheet sandyRoadSprite = new SpriteSheet(ImageLoader.loadImage("/textures/terrain/sandyRoad.png"));
        SpriteSheet sandyRoad2Sprite = new SpriteSheet(ImageLoader.loadImage("/textures/terrain/sandyRoad2.png"));
        SpriteSheet waterSprite = new SpriteSheet(ImageLoader.loadImage("/textures/terrain/water.png"));
        SpriteSheet water2Sprite = new SpriteSheet(ImageLoader.loadImage("/textures/terrain/water2.png"));





        inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");
        

        //Character
        playerDown = new BufferedImage[3];
        playerUp = new BufferedImage[3];
        playerLeft = new BufferedImage[3];
        playerRight = new BufferedImage[3];
        skeletonDown = new BufferedImage[3];
        skeletonUp = new BufferedImage[3];
        skeletonLeft = new BufferedImage[3];
        skeletonRight = new BufferedImage[3];

        //Tiles
        forest = new BufferedImage[10];
        grass = new BufferedImage[10];
        redSand = new BufferedImage[10];
        road = new BufferedImage[10];
        sandyRoad = new BufferedImage[10];
        water = new BufferedImage[10];

        //HashMaps
        HashMap<BufferedImage[], SpriteSheet> hashMap = new HashMap<>();
        hashMap.put(forest, forestSprite);
        hashMap.put(grass, grassSprite);
        hashMap.put(redSand, redSandSprite);
        hashMap.put(road, roadSprite);
        hashMap.put(sandyRoad, sandyRoadSprite);
        hashMap.put(water, waterSprite);

        HashMap<BufferedImage[], SpriteSheet> hashMap2 = new HashMap<>();
        hashMap2.put(forest, forest2Sprite);
        hashMap2.put(grass, grass2Sprite);
        hashMap2.put(redSand, redSand2Sprite);
        hashMap2.put(road, road2Sprite);
        hashMap2.put(sandyRoad, sandyRoad2Sprite);
        hashMap2.put(water, water2Sprite);

        //Character
        playerDown[0] = player.crop(0, 0, WIDTH, HEIGHT);
        playerDown[1] = player.crop(WIDTH, 0, WIDTH, HEIGHT);
        playerDown[2] = player.crop(WIDTH * 2, 0, WIDTH, HEIGHT);
        playerUp[0] = player.crop(0, HEIGHT *3, WIDTH, HEIGHT);
        playerUp[1] = player.crop(WIDTH, HEIGHT *3, WIDTH, HEIGHT);
        playerUp[2] = player.crop(WIDTH * 2, HEIGHT *3, WIDTH, HEIGHT);
        playerLeft[0] = player.crop(0, HEIGHT, WIDTH, HEIGHT);
        playerLeft[1] = player.crop(WIDTH, HEIGHT, WIDTH, HEIGHT);
        playerLeft[2] = player.crop(WIDTH * 2, HEIGHT, WIDTH, HEIGHT);
        playerRight[0] = player.crop(0, HEIGHT * 2, WIDTH, HEIGHT);
        playerRight[1] = player.crop(WIDTH, HEIGHT * 2, WIDTH, HEIGHT);
        playerRight[2] = player.crop(WIDTH * 2, HEIGHT * 2, WIDTH, HEIGHT);
        skeletonDown[0] = skeleton.crop(0, 0, WIDTH, HEIGHT);
        skeletonDown[1] = skeleton.crop(WIDTH, 0, WIDTH, HEIGHT);
        skeletonDown[2] = skeleton.crop(WIDTH * 2, 0, WIDTH, HEIGHT);
        skeletonUp[0] = skeleton.crop(0, HEIGHT *3, WIDTH, HEIGHT);
        skeletonUp[1] = skeleton.crop(WIDTH, HEIGHT *3, WIDTH, HEIGHT);
        skeletonUp[2] = skeleton.crop(WIDTH * 2, HEIGHT *3, WIDTH, HEIGHT);
        skeletonLeft[0] = skeleton.crop(0, HEIGHT, WIDTH, HEIGHT);
        skeletonLeft[1] = skeleton.crop(WIDTH, HEIGHT, WIDTH, HEIGHT);
        skeletonLeft[2] = skeleton.crop(WIDTH * 2, HEIGHT, WIDTH, HEIGHT);
        skeletonRight[0] = skeleton.crop(0, HEIGHT * 2, WIDTH, HEIGHT);
        skeletonRight[1] = skeleton.crop(WIDTH, HEIGHT * 2, WIDTH, HEIGHT);
        skeletonRight[2] = skeleton.crop(WIDTH * 2, HEIGHT * 2, WIDTH, HEIGHT);
        
        //Tiles
        grassHill = grassHillSprite.crop(0, 0, WIDTH, HEIGHT);
        clayMountain = clayMountainSprite.crop(0, 0, WIDTH, HEIGHT);
        mountain = mountainSprite.crop(0, 0, WIDTH, HEIGHT);


        for(BufferedImage[] bufferedImages: hashMap.keySet()){
            int index = 0;
            SpriteSheet spriteSheet = hashMap.get(bufferedImages);

            for(int j = 0; j < 3; j++){
                for(int k = 0; k < 2; k++){
                    bufferedImages[index++] = spriteSheet.crop(k * WIDTH, j * HEIGHT, WIDTH, HEIGHT);
                }
            }
        }
        for(BufferedImage[] bufferedImages: hashMap2.keySet()){
            int index = 6;
            SpriteSheet spriteSheet = hashMap2.get(bufferedImages);
            for(int i = 1; i < 5; i++){
                bufferedImages[index++] = spriteSheet.crop(0, i * HEIGHT, WIDTH, HEIGHT);
            }
        }
    }
}
