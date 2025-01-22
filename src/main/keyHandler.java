package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandler implements KeyListener {
	
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	boolean pressEnter;
	GamePanel gp;
	
	
	public keyHandler(GamePanel gp) {
		this.gp=gp;
		
	}

	@Override //Not in use  
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code =e.getKeyCode();
		if(gp.gamestate==gp.playstate) {
		if(code==KeyEvent.VK_W) {
			up=true;
			
		}
		if(code==KeyEvent.VK_S) {
			down=true;
			
		}
		if(code==KeyEvent.VK_A) {
			left=true;
			
		}
		if(code==KeyEvent.VK_D) {
			right=true;
			
		}
		if(code==KeyEvent.VK_P) {
			gp.gamestate=gp.pausestate;
			
			
		}
		
		  if (code==KeyEvent.VK_ENTER){
            pressEnter=true;
        }
		}
		else if(gp.gamestate==gp.pausestate) {
			if(code==KeyEvent.VK_P) {
				gp.gamestate=gp.playstate;
				
				
			}
			
		}
		 else if (gp.gamestate==gp.interactionState) {
	            if (code==KeyEvent.VK_ENTER){
	                gp.gamestate=gp.playstate;
	            }
	        }
		else if(gp.gamestate==gp.titlestate) {
			if(code==KeyEvent.VK_ENTER) {
				gp.gamestate=gp.playstate;
			}
		}
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code =e.getKeyCode();
		
		if(code==KeyEvent.VK_W) {
			up=false;
			
		}
		if(code==KeyEvent.VK_S) {
			down=false;
			
		}
		if(code==KeyEvent.VK_A) {
			left=false;
			
		}
		if(code==KeyEvent.VK_D) {
			right=false ;
			
		}
		
		
		
	}
	

}
