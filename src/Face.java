import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Face {
	public static final int SQUARE_SIZE = 64;
	public static final int FACE_START = Numbers.START+(Numbers.WIDTH*3)+Board.SPACING;
	private int i;
	private static Image sprites;
	private static Image[] faces = new Image[5];

	public Face(int i) {
		this.i = i;
		setUpImages();
	}

	private void setUpImages() {
		if(sprites == null) {
			try {
				sprites = ImageIO.read(new File("minesweepersprites.PNG"));
			} 
			catch (IOException e) {	
				e.printStackTrace();
			}
		}
		faces[0] =  ((BufferedImage)sprites).getSubimage(5,360,370,370).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
		faces[1] =  ((BufferedImage)sprites).getSubimage(397,360,370,370).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
		faces[2] =  ((BufferedImage)sprites).getSubimage(790,360,370,370).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
		faces[3] =  ((BufferedImage)sprites).getSubimage(1182,360,370,370).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
		faces[4] =  ((BufferedImage)sprites).getSubimage(1570,360,370,370).getScaledInstance(SQUARE_SIZE, SQUARE_SIZE, BufferedImage.SCALE_SMOOTH);
	}
	
	public void draw(Graphics g) {
		g.drawImage(faces[i], FACE_START, 10, null);
	}

}
