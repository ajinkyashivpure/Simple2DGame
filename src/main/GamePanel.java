package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	//jpanel allows to add components in the jframe ..
	
	int orignalTileSize=16;
	int scale=3;
	
	
	//screen settings
	public int tileSize=orignalTileSize*scale;
	int rows=12;
	int cols=16;
	
	//worldMap settings
	public int worldRow=50;
	public int worldCol=50;
	
	public int FPS=60;
	
	
	
	public int windowWidth=tileSize*cols;
	public int windowHeight=tileSize*rows;
	
	
	//object initializations
	keyHandler keyH=new keyHandler(this);
	TileManager tilemanager=new TileManager(this);
	public Player player=new Player(this,keyH);
	public CollisionChecker collisionchecker=new CollisionChecker(this);
	public UI ui=new UI(this);
	Sound sound=new Sound();
	SoundEffect soundeffect=new SoundEffect();
	
    // variables
	
	public int titlestate=0;
	public int playstate=1;
	public int pausestate=2;
	 public final int interactionState=3;
	public int gamestate;
	
	 public Entity npc[]=new Entity[10];
	
	//objects array
	public SuperObject obj[]=new SuperObject[10];
	
	 public AssetSetter assetSetter=new AssetSetter(this);
	
	
	
	public GamePanel() {
		//this constructor will contain all the basic ass settings!
		
		this.setPreferredSize(new Dimension(windowWidth, windowHeight));  // set size cannot be used ..use preferred size only
		this.setBackground(Color.black);  //title and default screen ko black rakhega
		this.setDoubleBuffered(true);
	    this.addKeyListener(keyH);
	    this.setFocusable(true);
		
		
		
	}
	Thread gameThread;
	public void startGameThread() {
//		gamestate=titlestate;
		gameThread=new Thread(this);
		gameThread.start();
	}
	
	public void setUp() {
		gamestate=titlestate;
		 assetSetter.setObject();
		playMusic();
	}
	
	





// implementing runnable creates this run method automatically...this is a game loop.
	@Override
	public void run() {
		//we create a game thread .......this method is called automatically!
		//this game loop runs very fast..you will have to change its time settings to your choice.
		//mostly 60fps is preferred
		
		//The delta method 
		double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            //we use the delta method to decrease the speed to 60fps
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint(); // this is the way you call the paint component method. its java :)
                delta--;
            }
        }
		
				
	}
	public void update() {
		if (gamestate==playstate){
            player.update();
            for (int i=0;i< npc.length;i++){
                if (npc[i] !=null){
                    npc[i].update();
                }
            }

        }
        else {
        }
		
		
	}
	
	//this is again a default method that needs to be called as repaint in the run method
	public void paintComponent(Graphics g) {
		super.paintComponent(g); //you need to call the constructor of the parent class in this case.
		Graphics2D g2=(Graphics2D)g;
		
		//draw tiles
		
		if(gamestate==titlestate) {
			ui.draw(g2);
			
		}
		else {
			tilemanager.draw(g2);
			
			  for (int i=0;i< obj.length;i++){
	                if (obj[i]!=null){
	                    obj[i].draw(g2,this);
	                }
	            }
			  
			  for (int i=0;i< npc.length;i++){
	                if (npc[i] !=null){
	                    npc[i].draw(g2);
	                }
	            }
	            
			
			player.draw(g2);
			
			ui.draw(g2);
			
			
		}
		
		
		
		
		
	
		 
		
		
		g2.dispose(); // deallocate the resources ..to save memory
		
	}
	public void playMusic() {
		sound.loadsound(0);
		sound.playMusic();
		sound.loopMusic();
		
	}
	public void stopMusic() {
		sound.stopMusic();
	}
	public void playSoundEffect(int i) {
		soundeffect.loadSoundEffect(i);
		soundeffect.playSoundEffect();
		
		
	}


	

}
