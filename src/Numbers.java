import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Numbers {
	public static final int WIDTH = 35, HEIGHT = 60, START = 40;
	private int i;
	private String digit;
	private static Image sprites;
	private static Image[] nums = new Image[10];

	public Numbers(int i, String s) {
		this.i = i;
		digit = s;
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
		nums[1] =  ((BufferedImage)sprites).getSubimage(2,7,193,330).getScaledInstance(WIDTH, HEIGHT, BufferedImage.SCALE_SMOOTH);
		nums[2] =  ((BufferedImage)sprites).getSubimage(210,7,193,330).getScaledInstance(WIDTH, HEIGHT, BufferedImage.SCALE_SMOOTH);
		nums[3] =  ((BufferedImage)sprites).getSubimage(410,7,193,330).getScaledInstance(WIDTH, HEIGHT, BufferedImage.SCALE_SMOOTH);
		nums[4] =  ((BufferedImage)sprites).getSubimage(618,7,193,330).getScaledInstance(WIDTH, HEIGHT, BufferedImage.SCALE_SMOOTH);
		nums[5] =  ((BufferedImage)sprites).getSubimage(815,7,193,330).getScaledInstance(WIDTH, HEIGHT, BufferedImage.SCALE_SMOOTH);
		nums[6] =  ((BufferedImage)sprites).getSubimage(1020,7,193,330).getScaledInstance(WIDTH, HEIGHT, BufferedImage.SCALE_SMOOTH);
		nums[7] =  ((BufferedImage)sprites).getSubimage(1223,7,193,330).getScaledInstance(WIDTH, HEIGHT, BufferedImage.SCALE_SMOOTH);
		nums[8] =  ((BufferedImage)sprites).getSubimage(1428,7,193,330).getScaledInstance(WIDTH, HEIGHT, BufferedImage.SCALE_SMOOTH);
		nums[9] =  ((BufferedImage)sprites).getSubimage(1632,7,193,330).getScaledInstance(WIDTH, HEIGHT, BufferedImage.SCALE_SMOOTH);
		nums[0] =  ((BufferedImage)sprites).getSubimage(1830,7,193,330).getScaledInstance(WIDTH, HEIGHT, BufferedImage.SCALE_SMOOTH);
	}
	
	public void draw(Graphics g) {
		if(digit.equals("100"))
			g.drawImage(nums[i], START, 10, null);
		if(digit.equals("10"))
			g.drawImage(nums[i], START+WIDTH, 10, null);
		if(digit.equals("1"))
			g.drawImage(nums[i], START+(WIDTH*2), 10, null);
		if(digit.equals("ones"))
			g.drawImage(nums[i], Numbers.START+(Numbers.WIDTH*5)+(Board.SPACING*2)+Face.SQUARE_SIZE, 10, null);
		if(digit.equals("tens"))
			g.drawImage(nums[i], Numbers.START+(Numbers.WIDTH*4)+(Board.SPACING*2)+Face.SQUARE_SIZE, 10, null);
		if(digit.equals("hundreds"))
			g.drawImage(nums[i], Numbers.START+(Numbers.WIDTH*3)+(Board.SPACING*2)+Face.SQUARE_SIZE, 10, null);
	}
}
