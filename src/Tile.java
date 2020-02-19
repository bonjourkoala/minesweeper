import java.awt.Graphics;                                                                                                                     
import java.awt.Image;                                                                                                                        
import java.awt.image.BufferedImage;                                                                                                          
import java.io.File;                                                                                                                          
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;                                                                                                                 
                                                                                                                                              
public class Tile {                                                                                                                           
	public static final int SQUARE_SIZE = 36;                                                                                                 
	private int row, col, i, numMines = 0, clickState;                                                                                        
	private boolean hasMine = false, hasFlag = false;                                                                        
	private static Image sprites, tile, flag;                                                                                                                
	private static Image[] adjacent = new Image[9], bombStates = new Image[3];                                                                                        
                                                                                                                                              
	public Tile(int r, int c) {                                                                                                               
		row = r;                                                                                                                              
		col = c;                                                                                                                              
		setUpImages();                                                                                                                        
	}                                                                                                                                                                                                                                                                                 
                                                                                                                                              
	private void setUpImages() {                                                                                                              
		if(sprites == null) {// only open the file once                                                                                       
			try {                                                                                                                             
				sprites = ImageIO.read(new File("minesweepersprites.PNG"));                                                                   
                                                                                                                                              
			}                                                                                                                                 
			catch (IOException e) {	                                                                                                          
				e.printStackTrace();                                                                                                          
			}                                                                                                                                 
		}                                                                                                                                     
		tile =  ((BufferedImage)sprites).getSubimage(4,746,230,230).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);  
		flag =  ((BufferedImage)sprites).getSubimage(498,746,230,230).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
		bombStates[0] = ((BufferedImage)sprites).getSubimage(1238,746,230,230).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
		bombStates[1] = ((BufferedImage)sprites).getSubimage(1486,746,230,230).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
		adjacent[0] =  ((BufferedImage)sprites).getSubimage(256,746,230,230).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
		bombStates[2] = ((BufferedImage)sprites).getSubimage(1733,746,230,230).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
		adjacent[1] =  ((BufferedImage)sprites).getSubimage(4,992,230,230).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
		adjacent[2] =  ((BufferedImage)sprites).getSubimage(252,992,230,230).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
		adjacent[3] =  ((BufferedImage)sprites).getSubimage(498,992,230,230).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
		adjacent[4] =  ((BufferedImage)sprites).getSubimage(743,992,230,230).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
		adjacent[5] =  ((BufferedImage)sprites).getSubimage(991,992,230,230).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
		adjacent[6] =  ((BufferedImage)sprites).getSubimage(1239,992,230,230).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
		adjacent[7] =  ((BufferedImage)sprites).getSubimage(1486,992,230,230).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
		adjacent[8] =  ((BufferedImage)sprites).getSubimage(1732,992,230,230).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
		}                                                                                                                                         
	                                                                                                                                          
	public void draw(Graphics g) {                                                                                                            
		if (clickState == 0)//not clicked                                                                                                     
			g.drawImage(tile, this.col*this.SQUARE_SIZE+Board.OFFSET_X, this.row*this.SQUARE_SIZE+Board.OFFSET_Y, null);                      
		if (clickState == 1)//regular tile                                                                                                    
			g.drawImage(adjacent[numMines], (this.col*this.SQUARE_SIZE)+Board.OFFSET_X, (this.row*this.SQUARE_SIZE)+Board.OFFSET_Y, null);    
		if (clickState == 2) //right clicked                                                                                                  
			g.drawImage(flag, this.col*this.SQUARE_SIZE+Board.OFFSET_X, this.row*this.SQUARE_SIZE+Board.OFFSET_Y,null);                       
		if (clickState == 3)//clicked bomb                                                                                                    
			g.drawImage(bombStates[1], this.col*this.SQUARE_SIZE+Board.OFFSET_X, this.row*this.SQUARE_SIZE+Board.OFFSET_Y,null);              
		if (clickState == 4)//show bombs                                                                                                      
			g.drawImage(bombStates[0], this.col*this.SQUARE_SIZE+Board.OFFSET_X, this.row*this.SQUARE_SIZE+Board.OFFSET_Y,null);              
		if (clickState == 5)//flagged but no bomb                                                                                             
			g.drawImage(bombStates[2], this.col*this.SQUARE_SIZE+Board.OFFSET_X, this.row*this.SQUARE_SIZE+Board.OFFSET_Y,null);                                                                                                         
	}                                                                                                                                         
	public int getRow() {                                                                                                                     
		return row;                                                                                                                           
	}                                                                                                                                         
                                                                                                                                              
	public int getCol() {                                                                                                                     
		return col;                                                                                                                           
	}   
	
	public void setClickState(int i) {                                                                                                        
		clickState = i;                                                                                                                       
	}                                                                                                                                         
                                                                                                                                              
	public boolean hasMine() {                                                                                                                
		return hasMine;                                                                                                                       
	}  
	
	public boolean hasFlag() {                                                                                                                
		return hasFlag;                                                                                                                       
	} 
	
	public void addFlag() {                                                                                                                   
		hasFlag = true;  
		clickState = 2;
	} 
	
	public void addMine() {                                                                                                                   
		hasMine = true;                                                                                                                       
	}  
	
	public void setNum(int mines) {                                                                                                           
		numMines = mines;                                                                                                                                                                                                                                                             
	}  
	
	public int getNumMines() {
		return numMines;
	}
	
	public int getClickState() {                                                                                                              
		return clickState;                                                                                                                    
	}

	public void removeFlag() {
		hasFlag = false;
		clickState = 0;
	}

	public boolean inChecked(ArrayList<Tile> tiles) {
		for(int i=0; i<tiles.size(); i++) {
			if(this.equals(tiles.get(i)))
				return true;
		}
		return false;
	}

	public boolean hasMinesAround() {
		if(numMines>0)
			return true;
		return false;
	}                                                                                                                                                                                                                                                                                   
}                                                                                                                                             
                                                                                                                                              