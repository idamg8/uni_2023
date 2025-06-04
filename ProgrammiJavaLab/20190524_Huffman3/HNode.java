public class HNode implements Comparable<HNode> {

	private final char ch;
	private final int wg;
	private final HNode ln;
	private final HNode rn;

	public HNode(char c, int w) {
		this.ch = c;
		this.wg = w;
		this.ln = null;
		this.rn = null;
	}

	public HNode(HNode l, HNode r) {
		this.ch = (char) 0;
		this.wg = l.weight() + r.weight();
		this.ln = l;
		this.rn = r;
	}

	public boolean isLeaf() {
		return this.ln == null;
	}

	public int weight() {
		return this.wg;
	}

	public char character() {
		return this.ch;
	}

	public HNode left() {
		return this.ln;
	}

	public HNode right() {
		return this.rn;
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