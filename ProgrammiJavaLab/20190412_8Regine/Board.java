import java.util.function.*;

public class Board {

    private static final String COL = " abcdefghijklmno";
    private static final String ROW = " 123456789ABCDEF";

    private int size;
    private int queens;
    private SList<Integer> th_rows;
	private SList<Integer> th_cols;
	private SList<Integer> th_diag_dx;
	private SList<Integer> th_diag_sx;
    private String config;

    public Board(int n) {
        this.size = n;
        this.queens = 0;
        this.th_rows = new SList<Integer>();
		this.th_cols = new SList<Integer>();
		this.th_diag_dx = new SList<Integer>();
		this.th_diag_sx = new SList<Integer>();
        this.config = " ";
    }

    private Board(Board b, int i, int j) {
        this.size = b.size;
        this.queens = b.queens + 1;
		this.th_rows = b.th_rows.cons(i);
		this.th_cols = b.th_cols.cons(j);
		this.th_diag_dx = b.th_diag_dx.cons(i-j);
		this.th_diag_sx = b.th_diag_sx.cons(i+j);
        this.config = b.arrangement() + COL.charAt(j) + ROW.charAt(i) + " ";
    }

    public int size() {
        return this.size;
    }

    public int queensOn() {
        return this.queens;
    }

    public boolean underAttack(int i, int j) {
        for (int x = 0; x < this.th_rows.length(); x++) {
			if (this.th_rows.ref(x) == i) return true;
		}
		for (int x = 0; x < this.th_cols.length(); x++) {
			if (this.th_cols.ref(x) == j) return true;
		}
		for (int x = 0; x < this.th_diag_dx.length(); x++) {
			if (this.th_diag_dx.ref(x) == i-j) return true;
		}
		for (int x = 0; x < this.th_diag_sx.length(); x++) {
			if (this.th_diag_sx.ref(x) == i+j) return true;
		}
		return false;
    }

    public Board addQueen(int i, int j) {
        return new Board(this, i, j);
    }

    public String arrangement() {
        return this.config;
    }

    public String toString() {
        return "[" + this.arrangement() + "]";
    }

}