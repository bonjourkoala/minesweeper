import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MineSweeperPanel extends JPanel {

	private final int SIZE_PANEL = 600;
	private Board board;
	private int rows, cols, mines;
	Timer timer = new Timer(1000, null);



	public static void main(String[] args) {
		JFrame frame = new JFrame("Minesweeper!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new MineSweeperPanel());
		frame.pack();
		frame.setVisible(true);

	}
	public MineSweeperPanel() {
		JFrame f = new JFrame();
		f.setAlwaysOnTop(true);
		Object[] options = {"Beginner (9x9, 10 mines)", "Intermediate (16x16, 40 mines)", "Expert (20x16, 66 mines)", "Custom"};
		Object selectionObject = JOptionPane.showInputDialog(f, "Choose", "Level", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		String selectionString = selectionObject.toString();
		if(selectionString.equals("Beginner (9x9, 10 mines)")) {
			rows = 9;
			cols = 9;
			mines = 10;
		}
		if(selectionString.equals("Intermediate (16x16, 40 mines)")) {
			rows = 16;
			cols = 16;
			mines = 40;
		}
		if(selectionString.equals("Expert (20x16, 66 mines)")) {
			rows = 20;
			cols = 16;
			mines = 66;
		}
		if(selectionString.equals("Custom")) {
			rows = Integer.parseInt(JOptionPane.showInputDialog(null, "Choose a width for your board."));
			cols = Integer.parseInt(JOptionPane.showInputDialog(null, "Choose a height for your board."));
			int x = Integer.parseInt(JOptionPane.showInputDialog(null, "Choose a number of mines for your board."));
			if(x<rows*cols)
				mines=x;
			else
				mines = Integer.parseInt(JOptionPane.showInputDialog
						(null, "Choose a smaller number of mines for your board."));

		}
		board = new Board(rows, cols, mines);
		this.setPreferredSize(new Dimension(this.SIZE_PANEL,SIZE_PANEL));
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				board.handleClick(me);
				repaint();
			}		
		});
		timer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				board.tick();
				repaint();
			}
		});
		timer.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		board.draw(g);
	}
}
