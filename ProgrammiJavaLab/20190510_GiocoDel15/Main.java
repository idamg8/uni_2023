import puzzleboard.*;

public class Main {
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		PuzzleBoard gui = new PuzzleBoard(n);
		Board b = new Board(n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				gui.setNumber(i+1, j+1, b.board()[i][j]);
				if (b.board()[i][j] == b.hole()) gui.clear(i+1, j+1);
			}
		}
		gui.display();
		while(!b.isOrdered()) {
			int x = 0;
			int y = 0;
			int k = gui.get();
			// Find position of tile k
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (b.board()[i][j] == k) {
						x = i;
						y = j;
					}
				}
			}
			// Move tile
			if (b.movable(x, y)) {
				gui.clear(x+1, y+1);
				int q = b.findHole();
				int i = q / n;
				int j = q % n;
				gui.setNumber(i+1, j+1, k);
				gui.display();
				b.move(x, y);
			}
		}
		System.out.println("You won!");
	}
	
}