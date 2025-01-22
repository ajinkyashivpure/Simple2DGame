package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class UI {
	
	double playTime;
    DecimalFormat decimalFormat=new DecimalFormat("#0.00 ");
	GamePanel gp;
	Font endFont;
	 private int lineHeight = 20;
	 private int padding = 20;
	public boolean gameFinished;
	 String currentDialogue="";
	public UI(GamePanel gp) {
		this.gp=gp;
		 endFont=new Font("Arial",Font.BOLD,80);
		
	}
	
	public void draw(Graphics2D g2) {
		
	      if (gameFinished) {
	            //showMessage("YOU OPENED THE TREASURE");
	            g2.setFont(endFont);
	            g2.setColor(Color.WHITE);

	            String endText="FINISH";
	            int endTextLength=(int)g2.getFontMetrics().getStringBounds(endText,g2).getWidth();
	            int x=gp.windowWidth/2-endTextLength/2;
	            int y=gp.windowHeight/2+gp.tileSize*3;
	            g2.drawString(endText,x,y);

	            gp.gameThread=null;

	        }
		if(gp.gamestate==gp.pausestate) {
			drawPauseScreen(g2);
		} 
		else if(gp.gamestate==gp.titlestate) {
			drawTitleScreen(g2);
		}
		else if(gp.gamestate==gp.playstate) {
			playTime+=(double)1/60;
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,30));
			g2.setColor(Color.BLACK);
            g2.drawString("Time:"+decimalFormat.format(playTime),gp.tileSize*12,70 );
		}
		 else if (gp.gamestate==gp.interactionState){
	            int x=gp.tileSize*4;
	            int y=gp.tileSize/2;
	            int width=gp.tileSize*9;
	            int height=gp.tileSize*4;
	            drawWindow(x,y,width,height,g2);
	        }
		
	}
	
	public void drawWindow(int x,int y,int width,int height,Graphics2D g2){
        Color c=new Color(0,0,0,220);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,25,25);

        c=new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5)); //basically bold kardega
        g2.drawRoundRect(x+5,y+5,width-10,height-10,25,25);

        x+=gp.tileSize;
        y+=gp.tileSize;
        g2.drawString(currentDialogue,x,y);



    }

	private void drawTitleScreen(Graphics2D g2) {
		Font fontTitle=new Font("Ariel",Font.BOLD,60);
		g2.setFont(fontTitle);
		g2.setColor(Color.GREEN);
		String text="M.E.N.U";
		int textPos=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x=gp.windowWidth/2-textPos/2;
		int y=gp.windowHeight/2-gp.tileSize*3;
		
		g2.drawString(text, x, y);
		
		Font fontText=new Font("Ariel",Font.ITALIC,15);
		g2.setFont(fontText);
		g2.setColor(Color.white);
		String para="Welcome to My Java NPC Game! This simple yet engaging game is a testament to my core knowledge and hands-on experience in Java programming. Designed with functionality in mind, the game features tile-based collision, object interactions, item pickups, dynamic game states, sound effects, and a clean user interface. It showcases a deep understanding of Java fundamentals, emphasizing structured design and practical implementation. While the gameplay is straightforward, each element reflects careful coding and problem-solving, making this project a clear demonstration of my skills and passion for Java development.";
		
//		int textPos2=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
//		int x2=gp.windowWidth/2-textPos2/2;
//		int y2=gp.windowHeight/2+gp.tileSize;
		
		List<String> lines = wrapText(para, g2, gp.windowWidth - 2 * padding);
		int x3= padding;
	    int y3 = padding + lineHeight;
	        for (String line : lines) {
	            g2.drawString(line, x3, y3);
	            y += lineHeight;
	        }
		 
		 
//		String text2="---> NEW GAME";
//		int textPos2=(int)g2.getFontMetrics().getStringBounds(text2, g2).getWidth();
//		int x2=gp.windowWidth/2-textPos2/2-gp.tileSize;
//		int y2=gp.windowHeight/2+gp.tileSize;
//		g2.setFont(g2.getFont().deriveFont(Font.ITALIC,20));
//		
//		g2.drawString(text2, x2, y2);

		
	}
	   private List<String> wrapText(String text, Graphics2D g2, int maxWidth) {
	        List<String> lines = new ArrayList<>();
	        String[] words = text.split(" ");
	        StringBuilder currentLine = new StringBuilder();

	        for (String word : words) {
	            String testLine = currentLine + word + " ";
	            int lineWidth = g2.getFontMetrics().stringWidth(testLine);

	            if (lineWidth > maxWidth) {
	                // Add current line to lines and start a new line
	                lines.add(currentLine.toString().trim());
	                currentLine = new StringBuilder(word).append(" ");
	            } else {
	                currentLine.append(word).append(" ");
	            }
	        }

	        // Add the last line
	        if (!currentLine.isEmpty()) {
	            lines.add(currentLine.toString().trim());
	        }

	        return lines;
	    }
	   private void drawPauseScreen(Graphics2D g2) {
		String text="PAUSED";
		int textPos=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x=gp.windowWidth/2-textPos/2;
		int y=gp.windowHeight/2;
		Font font=new Font("Courier",Font.BOLD,80);
		g2.setFont(font);
		g2.drawString(text, x-gp.tileSize*2, y);
	
		
	}

}
