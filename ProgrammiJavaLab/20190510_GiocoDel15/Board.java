import java.util.*;

public class Board {

	private final int n;
	private final int hole;
	private int[][] board;

	public Board(int n){
		this.n = n;
		this.hole = n * n;
		this.board = new int[n][n];
		int[] tmp = new int[n*n];
		for (int i = 0; i < n*n; i++) {
			tmp[i] = i + 1;
		}
		this.shuffle(tmp);
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++) {
				this.board[i][j] = tmp[n*i+j];
			}
		}
	}

	private void shuffle(int[] array) {
		int index, temp;
		Random random = new Random();
		for (int i = array.length - 1; i > 0; i--) {
			index = random.nextInt(i+1);
			temp = array[index];
			array[index] =  array[i];
			array[i] = temp;
		}
	}

	public boolean isOrdered() {
		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.n; j++) {
				if (this.board[i][j] != this.n * i + j + 1) return false;
			}
		}
		return true;
	}

	public int findHole() {
		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.n; j++) {
				if (this.board[i][j] == this.hole) return this.n * i + j;
			}
		}
		return -1;
	}

	public boolean movable(int i, int j) {
		int pos = this.findHole();
		int x = pos / this.n;
		int y = pos % this.n;
		return (Math.abs(x - i) + Math.abs(y - j) == 1);
	}

	public void move(int i, int j) {
		int pos = this.findHole();
		int x = pos / this.n;
		int y = pos % this.n;
		this.board[x][y] = this.board[i][j];
		this.board[i][j] = this.hole;
	}

	public int[][] board() {
		return this.board;
	}

	public int hole() {
		return this.hole;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.n; j++) {
				s += ((this.board[i][j] == this.hole)? "-" : this.board[i][j]) + " ";
			}
			s += "\n";
		}
		return s;
	}

}