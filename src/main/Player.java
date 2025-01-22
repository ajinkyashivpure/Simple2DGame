package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import object.ObjectOpenedChest;

import static utilz.PlayerAnimations.PlayerConstants.*;

public class Player extends Entity {
	
	BufferedImage[]animationIdle;
	BufferedImage [] animationRunning;
	private int aniTick,aniIndex,aniSpeed=7;
	private int playerAction=IDLE;
	private int playerAnimation=getPlayerAnimation(playerAction);
	private int imageInfo=0;
	
	
	

	
	
	
	GamePanel gp;
	keyHandler keyH;
	
	public final int screenX; //final ints 
	public final int screenY;
	public Rectangle SolidArea;
	
	
	
	
	public Player(GamePanel gp,keyHandler keyH) {
		super(gp);
		this.gp=gp;
		this.keyH=keyH;
		screenX=gp.windowWidth/2-(gp.tileSize/2);
		screenY=gp.windowHeight/2-(gp.tileSize/2);
		//SolidArea=new Rectangle(worldX+(48-collisionWidth)/2,worldY+(48-collisionHeight)/2,collisionWidth,collisionHeight);
		
		  solidArea=new Rectangle();
	        solidArea.x=8;
	        solidArea.y=16;
	        solidArea.width=32;
	        solidArea.height=32;

	        //for objects interaction
	        solidAreaDefaultX= solidArea.x;
	        solidAreaDefaultY=solidArea.y;
	        direction="down";
		
		
		setDeafaultValues();
		setImage();
		loadAnimations();
		
		
	}
	private int collisionWidth=18;
	private int collisionHeight=18;
	//public Rectangle SolidArea=new Rectangle(screenX+(48-collisionWidth)/2,screenY+(48-collisionHeight)/2,collisionWidth,collisionHeight);
	public  void setDeafaultValues() {
		worldX=gp.tileSize*23;
		worldY=gp.tileSize*21;
		speed=8;
		
		
		
	}
	public void setImage() {
		
		try {
			
				imageIdle=ImageIO.read(getClass().getResourceAsStream("/player/Idle_new.png"));
				imageRun=ImageIO.read(getClass().getResourceAsStream("/player/Walk.png"));
				
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void loadAnimations() {
		animationIdle=new BufferedImage[6];
		animationRunning=new BufferedImage[8];
		for(int j=0;j<animationIdle.length;j++) {
			animationIdle[j]=imageIdle.getSubimage(j*96,0, 96, 96);
		}
		
		for(int j=0;j<animationRunning.length;j++) {
			animationRunning[j]=imageRun.getSubimage(j*96,0, 96, 96);
		}
		
		
	
		
			
		
	}
	private void updateIndex() {
		aniTick++;
		
		if(aniTick>=aniSpeed) {
			aniTick=0;
			aniIndex++;
			if(aniIndex>=6) {
				aniIndex=0;
				
			}
			
		}
		
	}
	public void update() {
		if(gp.gamestate==gp.playstate) {
		if(keyH.up || keyH.down || keyH.left || keyH.right) {
			imageInfo=1;
			

			if(keyH.up) {
				direction="up";
			}
			if(keyH.down) {
				direction="down";
			}
			if(keyH.left) {
				direction="left";
			}
			if(keyH.right) {
				direction="right";
			}
			
			collisionOn=false;
			
		gp.collisionchecker.TileChecker(this);
		
		
		   int objIndex=gp.collisionchecker.ObjectChecker(this,true);
           objectPickUp(objIndex);
           
           int npcIndex=gp.collisionchecker.NpcChecker(this, gp.npc);
           npcInteraction(npcIndex);

			
		if(!collisionOn) {
			
			switch(direction) {
			case "up":
				worldY-= speed;
				break;
			case "down":
				worldY+=speed;
				break;
			case "left":
				worldX-=speed;
				break;
			case "right":
				worldX+=speed;
				break;
				
			}
			}
		}else {
			imageInfo=0;
			
		}
		}
		
	}
	
	 public int hasKey=0;
	    public void objectPickUp(int i){
	        if (i!=999){
	            String objectName=gp.obj[i].name;
	            switch (objectName){
	                case "key":
	                    gp.playSoundEffect(0);
	                    hasKey++;
	                    gp.obj[i]=null;
	                    break;
	                case "door":
	                    if (hasKey>0){
	                        gp.playSoundEffect(1);
	                        hasKey--;
	                        gp.obj[i]=null;
	                    }
	                    break;
	                case "chest":
	                   gp.playSoundEffect(2);
	                    gp.obj[i]=new ObjectOpenedChest();
	                    gp.obj[i].worldX=10* gp.tileSize;
	                    gp.obj[i].worldY=7*gp.tileSize;
	                    break;
	                case "opened_chest":
                    gp.stopMusic();
                    gp.playSoundEffect(3);
	                    gp.ui.gameFinished=true;


	            }

	        }
	    }
	    
	    public void npcInteraction(int i){
	        if (i!=999){
	            if (gp.keyH.pressEnter) {
	                gp.gamestate = gp.interactionState;
	                gp.npc[i].speak();
	            }
	            gp.keyH.pressEnter=false;

	        }


	    }

	public void draw(Graphics2D g2) {
		updateIndex();
		if(imageInfo==0) {
			g2.drawImage(animationIdle[aniIndex], screenX,screenY,gp.tileSize,gp.tileSize,null);
		}
		else if(imageInfo==1){
			g2.drawImage(animationRunning[aniIndex], screenX,screenY,gp.tileSize,gp.tileSize,null);
			
		}
		//player to be drawn at screenx and y to fix its position...we move the tiles acc to the screenx and y
	}

}
