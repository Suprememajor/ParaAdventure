package paraadventure;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import paraadventure.display.Display;
import paraadventure.gfx.Assets;
import paraadventure.gfx.GameCamera;
import paraadventure.gfx.SpriteSheet;
import paraadventure.input.KeyManager;
import paraadventure.input.MouseManager;
import paraadventure.states.GameState;
import paraadventure.states.MenuState;
import paraadventure.states.State;

    /**
     *
     * @author Suprememajor
     */
    public class Game implements Runnable {
        private Display display; //Game display
        private int width,height;
        private String title;
        private Thread thread;//Game thread
        private BufferStrategy bufferStrategy;
        private Graphics graphics;
        private boolean running = false;

        //States
        public State gameState;
        public State menuState;

        //Input
        private KeyManager keyManager;
        private MouseManager mouseManager;

        //Camera
        private GameCamera gameCamera;

        //Handler
        private Handler handler;//Links all parts of the game together

        public Game(String title, int width, int height){
            this.height = height;
            this.width = width;
            this.title = title;

            keyManager = new KeyManager();
            mouseManager = new MouseManager();
        }
        private void init(){
            display = new Display(title, width, height);
            display.getFrame().addKeyListener(keyManager);
            display.getFrame().addMouseListener(mouseManager);
            display.getFrame().addMouseMotionListener(mouseManager);
            display.getCanvas().addMouseListener(mouseManager);
            display.getCanvas().addMouseMotionListener(mouseManager);
            Assets.init();
            handler = new Handler(this);
            gameCamera = new GameCamera(handler,0,0);


            gameState =  new GameState(handler);
            menuState = new MenuState(handler);
            State.setCurrentState(menuState);
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
        @Override
        public void run() {
            init();

            int fps = 60;
            double timePerTick = 1000000000/fps;
            double delta = 0;
            long now;
            long lastTime = System.nanoTime();
            long timer= 0;
            int ticks = 0;
            while(running){
                now = System.nanoTime();
                delta += (now-lastTime) / timePerTick;
                timer += now - lastTime;
                lastTime = now;

                if(delta >= 1){
                    tick();
                    render();
                    ticks++;
                    delta--;
                }
                if(timer >= 1000000000){
                    System.out.println("Ticks and Frames: " + ticks);
                    ticks = 0;
                    timer = 0;
                }
            }
            stop();
        }

        public KeyManager getKeyManager() {
            return keyManager;
        }
        public MouseManager getMouseManager(){
            return mouseManager;
        }

        public synchronized void start(){
            if(running)
                return;
            running = true;
            thread = new Thread(this);
            thread.start();
        }
        public synchronized void stop(){
            if(!running)
                return;
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        int x= 0;
        private void tick() {
            keyManager.tick();
            if(State.getCurrentState()!= null)
                State.getCurrentState().tick();
        }

        private void render() {
            bufferStrategy = display.getCanvas().getBufferStrategy();
            if(bufferStrategy == null){
                display.getCanvas().createBufferStrategy(3);
                return;
            }
            graphics = bufferStrategy.getDrawGraphics();

            graphics.clearRect(0, 0, width, height);
       /*g.drawImage(Assets.player, x, 20, null);
       g.drawImage(Assets.grass, x+2, x, null);*/
            State.getCurrentState().render(graphics);
            bufferStrategy.show();
            graphics.dispose();
        }
        public static int clamp(int val,int min,int max){
            if(val > max)
                val = max;
            else if(val  < min)
                val = min;
            return val;
        }
    /*public static float clamp(float val,float min,float max){
       if(val > max)
           val = max;
       else if(val  < min)
           val = min;
           return val;
    }*/

        public GameCamera getGameCamera() {
            return gameCamera;
        }

        public Thread getThread() {
            return thread;
        }
    }