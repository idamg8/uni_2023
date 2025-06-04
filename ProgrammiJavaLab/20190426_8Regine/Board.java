import java.util.function.*;

public class Board {

    private static final String COL = " abcdefghijklmno";
    private static final String ROW = " 123456789ABCDEF";

    private int size;
    private int queens;
    private BiPredicate<Integer,Integer> threat;
    private String config;

    public Board(int n) {
        this.size = n;
        this.queens = 0;
        this.threat = (u, v) -> false;
        this.config = " ";
    }

    private Board(Board b, int i, int j) {
        this.size = b.size;
        this.queens = b.queens + 1;
        this.threat = (u, v) -> ((u == i) || (v == j) || (u-v == i-j) || (u+v == i+j) || b.underAttack(u, v));
        this.config = b.arrangement() + COL.charAt(j) + ROW.charAt(i) + " ";
    }

    public int size() {
        return this.size;
    }

    public int queensOn() {
        return this.queens;
    }

    public boolean underAttack(int i, int j) {
        return this.threat.test(i, j);
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