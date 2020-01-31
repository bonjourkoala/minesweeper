import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Board {

	Tile temp = new Tile();// just for testing purposes...
	
	public Board(int rows, int cols, int mines) {
		
	}

	public void draw(Graphics g) {
		temp.draw(g);
	}

	public void handleClick(MouseEvent me) {
		// write your clicking code here!!
		System.out.println("You just clicked: "+me);
	}

}
