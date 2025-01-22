package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import main.Player;
import java.awt.image.BufferedImage;

public class Entity {
	public int worldX,worldY,speed;
	
	BufferedImage imageIdle,imageRun;
	String direction;
	public boolean collisionOn;
	public Rectangle solidArea=new Rectangle(0,0,48,48);
	public int solidAreaDefaultX;
	public int solidAreaDefaultY;
	
	BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    int playerCounter=0;
    int playerNum=1;
    
    public int npcActionChecker=0;
    //implement object collision of npc or monster if needed...

    //Dialogues
    String[] dialogues=new String[10];
    int dialogueIndex=0;


    GamePanel gp;


    public Entity(GamePanel gp){

        this.gp=gp;
    }

    public void setAction(){

    }
    public void speak(){
        //small small things matter a lot...speak is overridden and setdialogue method is called in the constructor of npcoldman

    }
    public void update(){
        setAction();
       speak();
        collisionOn=false;
        gp.collisionchecker.TileChecker(this);
        gp.collisionchecker.EntityChecker(this);

        if (!collisionOn){
            switch (direction){
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        playerCounter++;
        if (playerCounter > 10) {
            if (playerNum == 1) {
                playerNum = 2;
            } else if (playerNum == 2) {
                playerNum = 1;
            }
            playerCounter = 0;
        }



    }

    public void draw(Graphics2D g2){
        BufferedImage image=null;
        int screenX= worldX- gp.player.worldX + gp.player.screenX;
        int screenY=worldY-gp.player.worldY+ gp.player.screenY;

        // now we need to change drawing tiles....we need a loop to draw the tiles as much screen is visible and as player moves ..we don't need the whole map(ie part outside of the screen ready ...will not be efficient for bigger maps)
        if (worldX+ gp.tileSize> gp.player.worldX -gp.player.screenX && worldX-gp.tileSize<gp.player.worldX+ gp.player.screenX
                && worldY +gp.tileSize> gp.player.worldY-gp.player.screenY && worldY-gp.tileSize<gp.player.worldY+gp.player.screenY){

            switch (direction){
                case ("up"):
                    if (playerNum==1){
                        image=up1;
                    }
                    if (playerNum==2) {
                        image=up2;
                    }
                    break;
                case ("down"):
                    if (playerNum==1){
                        image=down1;
                    }
                    if (playerNum==2) {
                        image=down2;
                    }
                    break;
                case ("left"):
                    if (playerNum==1){
                        image=left1;
                    }
                    if (playerNum==2) {
                        image=left2;
                    }
                    break;
                case ("right"):
                    if (playerNum==1){
                        image=right1;
                    }
                    if(playerNum==2){
                        image=right2;
                    }
                    break;

            }
            g2.drawImage(image,screenX,screenY,gp.tileSize/2,gp.tileSize/2,null);
        }
    }


	

}
