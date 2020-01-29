import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MineSweeperPanel extends JPanel {

	private final int SIZE_PANEL = 600;
	private Board board;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Minesweeper!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new MineSweeperPanel());
		frame.pack();
		frame.setVisible(true);
		
	}
	public MineSweeperPanel() {
		this.setPreferredSize(new Dimension(this.SIZE_PANEL,SIZE_PANEL));
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				System.out.println("You just entered!! "+arg0);
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				System.out.println("You just exited!! "+arg0);
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// write your clicking code here!!
				System.out.println("You just clicked: "+arg0);
				
				
				
				
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}
			
		});
	}
	
	public void paintComponent(Graphics g) {
		
	}
	
	
	
	

}
