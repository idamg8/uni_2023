public class StringSList {

	private boolean empty = false;
	private String top = "";
	private StringSList tail;

	public StringSList(){
		this.empty = true;
	}

	private StringSList(String e, StringSList sl) {
		this.top = e;
		this.tail = sl;
	}

	public boolean isNull() {
		return this.empty;
	}

	public String car() {
		return this.top;
	}

	public StringSList cdr() {
		return this.tail;
	}

	public StringSList cons(String e) {
		return new StringSList(e, this);
	}

	public int length() {
		return ((this.isNull())? 0 : 1 + this.cdr().length());
	}

	public String listRef(int k) {
		return ((k == 0)? this.car() : this.cdr().listRef(k-1));
	}

	public boolean equals(StringSList sl) {
		return ((this.isNull())? sl.isNull() : (this.car() == sl.car() && this.cdr().equals(sl.cdr())));
	}

	public StringSList append(StringSList sl) {
		return ((this.isNull())? sl : this.cdr().append(sl).cons(this.car()));
	}

	public StringSList reverse() {
		StringSList sl = new StringSList();
		return this.reverseRec(sl);
	}

	private StringSList reverseRec(StringSList sl) {
		return ((this.isNull())? sl : this.cdr().reverseRec(sl.cons(this.car())));
	}

	@Override
	public String toString() {
		String r = "(";
		boolean first = true;
		StringSList ref = this;
		while (!ref.isNull()) {
			if (first) {
				r += ref.car();
				first = false;
			} else {
				r += ", " + ref.car();
			}
			ref = ref.cdr();
		}
		return r + ")";
	}

}