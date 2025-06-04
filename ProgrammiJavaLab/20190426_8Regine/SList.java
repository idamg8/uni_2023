public class SList<T> {

    private final boolean empty;
    private final T first;
    private final SList<T> rest;

    public SList() {
        this.empty = true;
        this.first = null;
        this.rest = null;
    }

    private SList(T first, SList<T> rest) {
        this.empty = false;
        this.first = first;
        this.rest = rest;
    }

    public boolean isNull() {
        return this.empty;
    }

    public T car() {
        return this.first;
    }

    public SList<T> cdr() {
        return this.rest;
    }

    public SList<T> cons(T s) {
        return new SList<T>(s, this);
    }

    public T ref(int i) {
        if (i == 0) {
            return this.car();
        } else {
            return this.cdr().ref(i-1);
        }
    }

    public SList<T> append(SList<T> r) {
        if (this.isNull()) {
            return r;
        } else {
            return this.cdr().append(r).cons(this.car());
        }
    }

    public SList<T> reverse() {
        SList<T> r = new SList<T>();
        return this.reverseRec(r);
    }

    private SList<T> reverseRec(SList<T> r) {
        if (this.isNull()) {
            return r;
        } else {
            return this.cdr().reverseRec(r.cons(this.car()));
        }
    }

    public int length() {
        if (this.isNull()) {
            return 0;
        } else {
            return 1 + this.cdr().length();
        }
    }

    public boolean equals(SList<T> r) {
        if (this.isNull() || r.isNull()) {
            return (this.isNull() && r.isNull());
        } else if (this.car().equals(r.car())) {
            return this.cdr().equals(r.cdr());
        } else {
            return false;
        }
    }

	@Override
    public String toString() {
        String r = "(";
        boolean first = true;
        SList<T> ref = this;
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