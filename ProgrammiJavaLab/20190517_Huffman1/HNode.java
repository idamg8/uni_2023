public class HNode implements Comparable<HNode> {

    private final char c;
    private final int w;
    private final HNode l;
    private final HNode r;

    HNode(char c, int w) {
        this.c = c;
        this.w = w;
        this.l = null;
        this.r = null;
    }

    HNode(HNode l, HNode r) {
        this.c = 0;
        this.w = l.weight() + r.weight();
        this.l = l;
        this.r = r;
    }

    public char character() {
        return this.c;
    }

    public int weight() {
        return this.w;
    }

    public HNode left() {
        return this.l;
    }

    public HNode right() {
        return this.r;
    }

    public boolean isLeaf() {
        return this.l == null;
    }

    @Override
    public int compareTo(HNode n) {
        if (this.weight() < n.weight()) return -1;
        else if (this.weight() > n.weight()) return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return "" + this.weight();
    }

}