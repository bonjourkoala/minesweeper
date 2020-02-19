import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class Board {
	public static final int OFFSET_Y = 80, OFFSET_X = 40, SPACING = 25;
	private Tile[][] tiles;
	private int rows, cols, originalMines, mines, uncovered, flagged, hundreds, ones, tens;
	private String numMines;
	private Numbers one, ten, hundred, minesone, minesten, mineshundred;
	private Face face = new Face(0);
	public boolean firstClick = false, gameOver = false;

	public Board(int rows, int cols, int mines) {
		this.rows = rows;
		this.cols = cols;
		this.mines = mines;
		originalMines = mines;
		tiles = new Tile[rows][cols];
		setMineCounter();
		reset();
	}

	private void setMineCounter() {
		if(mines<0) {
			minesone = new Numbers(0, "1");
			minesten = new Numbers(0, "10");
			mineshundred = new Numbers(0, "100");
		}
		else {
			numMines = mines+"";
			if(numMines.length()==1) {
				minesone = new Numbers(mines, "1");
				minesten = new Numbers(0, "10");
				mineshundred = new Numbers(0, "100");
			}
			if(numMines.length()==2) {
				minesone = new Numbers(Integer.parseInt(numMines.substring(1)), "1");
				minesten = new Numbers(Integer.parseInt(numMines.substring(0,1)), "10");
				mineshundred = new Numbers(0, "100");
			}
			if(numMines.length()==3) {
				minesone = new Numbers(Integer.parseInt(numMines.substring(2)), "1");
				minesten = new Numbers(Integer.parseInt(numMines.substring(1,2)), "10");
				mineshundred = new Numbers(Integer.parseInt(numMines.substring(0,1)), "100");
			}	
		}
	}

	public void reset() {
		mines = originalMines;
		hundreds = 0; 
		tens = 0; 
		ones = 0;
		one = new Numbers(ones, "ones");
		ten = new Numbers(tens, "tens");
		hundred = new Numbers(hundreds, "hundreds");
		fill();
		fillMines(mines);
		assignNums();
		setMineCounter();
		firstClick = false;
		gameOver = false;
	}

	private void assignNums() {
		int mines = 0;
		for(int r = 0; r < tiles.length; r++) {
			for(int c = 0; c < tiles[r].length; c++) {
				mines = 0;
				if(tiles[r][c].hasMine()==false) {
					if(inbounds(r-1,c) && tiles[r-1][c].hasMine())
						mines++;
					if(inbounds(r+1,c) && tiles[r+1][c].hasMine())
						mines++;
					if(inbounds(r,c-1) && tiles[r][c-1].hasMine())
						mines++;
					if(inbounds(r,c+1) && tiles[r][c+1].hasMine())
						mines++;
					if(inbounds(r-1,c-1) && tiles[r-1][c-1].hasMine())
						mines++;
					if(inbounds(r-1,c+1) && tiles[r-1][c+1].hasMine())
						mines++;
					if(inbounds(r+1,c+1) && tiles[r+1][c+1].hasMine())
						mines++;
					if(inbounds(r+1,c-1) && tiles[r+1][c-1].hasMine())
						mines++;
				}
				tiles[r][c].setNum(mines);
			}
		}
	}

	public void draw(Graphics g) {
		for(Tile[] r : tiles) {
			for(Tile t : r) {
				t.draw(g);
			}
		}
		minesone.draw(g);
		minesten.draw(g);
		mineshundred.draw(g);
		one.draw(g);
		ten.draw(g);
		hundred.draw(g);
		face.draw(g);
	}

	public void cascade(int r, int c){
		leftClick(r-1,c);
		leftClick(r+1,c);
		leftClick(r,c+1);
		leftClick(r,c-1);
		leftClick(r-1,c-1);
		leftClick(r-1,c+1);
		leftClick(r+1,c-1);
		leftClick(r+1,c+1);
	}

	
	public void fill() {
		for(int r = 0; r < tiles.length; r++) {
			for(int c = 0; c < tiles[r].length; c++) {
				tiles[r][c] = new Tile(r,c);
			}
		}
	}

	public void handleClick(MouseEvent me) {
		int row = (me.getY()-OFFSET_Y)/Tile.SQUARE_SIZE;
		int col = (me.getX()-OFFSET_X)/Tile.SQUARE_SIZE;
		if(faceClicked(me)||gameOver)
			return;
		face = new Face(0);
		firstClick = true;
		if(!inbounds(row,col))
			return;
		if(tiles[row][col].getClickState()==1) {
			face = new Face(2);
			return;
		}
		if(me.getButton()==3)
			rightClick(row,col);
		if(me.getButton()==1)
			leftClick(row,col);
		findFlagged();
		findUncovered();
		checkGameOver();
		setMineCounter();
	}

	private void checkGameOver() {
		if(flagged==originalMines && uncovered==(rows*cols)-originalMines) {
			face = new Face(3);
			gameOver = true;	
			JOptionPane.showMessageDialog(null, "You win!");
		}
	}

	private void leftClick(int row, int col) {
		if(!inbounds(row,col))
			return;
		Tile t = tiles[row][col];
		if(t.hasFlag()||t.getClickState()==1)
			return;
		if(t.hasMine()) {
			bombClicked(row,col);
			return;
		}
		t.setClickState(1);
		if(t.getNumMines()==0)
			cascade(row,col);
	}


	private void bombClicked(int row, int col) {
		tiles[row][col].setClickState(3);
		gameOver=true;
		face = new Face(4);
		for(Tile[] r : tiles) {
			for(Tile t : r) {
				if(t.hasMine() && !t.equals(tiles[row][col]))
					t.setClickState(4);
				if(t.hasFlag() && t.hasMine())
					t.setClickState(2);
				if(t.hasFlag() && t.hasMine()==false)
					t.setClickState(5);
			}
		}		
	}

	private void rightClick(int row, int col) {
		if(!inbounds(row,col)||tiles[row][col].getClickState()==1)
			return;
		if(!tiles[row][col].hasFlag()) {
			tiles[row][col].addFlag();
			mines--;
		}
		else {
			tiles[row][col].removeFlag();
			mines++;
		}
	}

	private boolean faceClicked(MouseEvent me) {
		if(me.getX()>Face.FACE_START && me.getX()<Face.FACE_START+Board.SPACING+Face.SQUARE_SIZE && 
				me.getY()>10 && me.getY()<Face.SQUARE_SIZE) {
			face = new Face(0);
			reset();
			return true;
		}
		return false;		
	}

	private void findUncovered() {
		int x = 0;
		for(Tile[] r : tiles) {
			for(Tile t : r) {
				if(t.getClickState()==1)
					x++;
			}
		}
		uncovered =	x;
	}

	public void tick() {
		if(!gameOver && firstClick) {
			ones++;
			if(ones==10) {
				tens++;
				ones=0;
			}
			if(tens==10) {
				hundreds++;
				tens=0;
			}
			one = new Numbers(ones, "ones");
			ten = new Numbers(tens, "tens");
			hundred = new Numbers(hundreds, "hundreds");
		}
	}

	public void fillMines(int mines) {
		for(int i = 0; i < mines; i++) {
			int r = (int) (Math.random()*rows);
			int c = (int) (Math.random()*cols);
			if(tiles[r][c].hasMine()==false) 
				tiles[r][c].addMine();
			else
				i--;
		}
	}

	public void findFlagged() {
		int x = 0;
		for(Tile[]r:tiles) {
			for(Tile t:r) {
				if(t.hasMine() && t.hasFlag())
					x++;
			}
		}
		flagged = x;
	}

	public boolean inbounds(int r, int c) {
		if(r<tiles.length && c<tiles[0].length && r>=0 && c>=0)
			return true;
		return false;
	}

}

