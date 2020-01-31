import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
	/**
	 * Might be plenty of instance variables you might want here
	 */
	
	private static Image sprites;
	private static Image[] images;// might want to store all the subimages in this shared array
	private static int[][] coords;// or might want to save the coordinates of the subimages
	
	
	public Tile() {
		setUpImages();
	}
	private void setUpImages() {
		if(sprites == null) {// only open the file once
			try {
				sprites = ImageIO.read(new File("minesweepersprites.PNG"));
			} catch (IOException e) {	
				e.printStackTrace();
			}
		}
	}
	/**
	 * Draw this tile in its current state.  What are the states of a Tile?
	 */
	public void draw(Graphics g) {
		
		g.drawImage(sprites, 0, 0, null);
	}
	
	
}
