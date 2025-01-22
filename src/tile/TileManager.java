package tile;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	public Tile [] tile;
	public int [][] mapTiles;
	
	
	public TileManager(GamePanel gp) {
		this.gp=gp;
		tile=new Tile[10];
		mapTiles=new int [gp.worldCol][gp.worldRow];
		loadMap("/maps/world01.txt");
		setMapImage();
		
//		levelOne =new Tile(getLevelData());
//		importOutsideSprites();
//		
	}
	private void loadMap(String string) {
		InputStream is=getClass().getResourceAsStream(string);
		BufferedReader br=new BufferedReader(new InputStreamReader(is));
		
		int row=0;
		int col=0;
		while(row<gp.worldRow && col<gp.worldCol) {
			try {
				String line =br.readLine();
				while(col<gp.worldCol) {
					String [] nums=line.split(" ");
					int num=Integer.parseInt(nums[col]);
					mapTiles[col][row]=num;
					col++;
					
				}
				if(col==gp.worldCol) {
					col=0;
					row++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	public void setMapImage() {
	
		
		try {
			tile[0]=new Tile();
			tile[0].image=ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
	
			tile[1]=new Tile();
			tile[1].image=ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[1].collision=true;
			
			tile[2]=new Tile();
			tile[2].image=ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			tile[2].collision=true;
			
			tile[3]=new Tile();
			tile[3].image=ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
			
			tile[4]=new Tile();
			tile[4].image=ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			tile[4].collision=true;
			
			
			tile[5]=new Tile();
			tile[5].image=ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	 
	 public void draw(Graphics2D g2) {
			int row=0;
			int col=0;
			while(row<gp.worldRow && col<gp.worldCol) {
				
				int n=mapTiles[col][row];
				int worldX=col*gp.tileSize;
				int worldY=row*gp.tileSize;
				
				int screenX=worldX-gp.player.worldX+gp.player.screenX;
				int screenY=worldY-gp.player.worldY+gp.player.screenY;
				
				 if (worldX+ gp.tileSize> gp.player.worldX -gp.player.screenX && worldX-gp.tileSize<gp.player.worldX+ gp.player.screenX
			          && worldY +gp.tileSize> gp.player.worldY-gp.player.screenY && worldY-gp.tileSize<gp.player.worldY+gp.player.screenY) {
					 
					  g2.drawImage(tile[n].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
				 }
				 col++;
				 if(col==gp.worldCol) {
					 col=0;
					 row++;
				 }
				
			}
			
			
		}

	 
	 //rendering ki maa chud gayi :(!!
//    private void importOutsideSprites() {
//    	BufferedImage img=importImage("/tiles/outside_sprites.png");
//    	
//    	//outside_sprites has 4 rows 12 cols worth of tiles ...48 tiles .....indexing and loading of each tile in our image array is done accordingly
//    	levelSprites=new BufferedImage[48];
//    	for(int j=0;j<4;j++) {
//    		for(int i=0;i<12;i++) {
//    			int index=j*12+i;
//    			levelSprites[index]=img.getSubimage(i*32, j*32, 32, 32);
//    		}
//    	}
//		
//		
//	}
//    public BufferedImage importImage(String path) {
//		BufferedImage image=null;
//		
//		
//		try {
//			image=ImageIO.read(getClass().getResourceAsStream(path));
//			
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//		
//		return image;
//		
//	}
//    public int [][] getLevelData(){
//		int [][] lvlData=new int [gp.windowHeight][gp.windowWidth]; 
//		BufferedImage img=importImage("/tiles/level_one_data.png");
//		for(int j=0;j<img.getHeight();j++) {
//			for(int i=0;i<img.getWidth();i++) {
//				Color color=new Color(img.getRGB(i,j));
//				int value=color.getRed();
//				if(value>=48) {
//					value=0;
//				}
//				lvlData[j][i]=value;
//			}
//		}
//		return lvlData;
//		
//	}
//
	
	
	
	

}
