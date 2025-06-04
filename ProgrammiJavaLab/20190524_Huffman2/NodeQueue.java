public class NodeQueue {

    private Node[] queue;

    public NodeQueue() {
        this.queue = new Node[0];
    }

    public int size() {
        return this.queue.length;
    }

    public Node peek() {
        return this.queue[0];
    }

    public Node poll() {
        int s = this.size();
        Node r = this.queue[0];
        Node[] tmp = new Node[s-1];
        for (int i = 0; i < s-1; i++) {
            tmp[i] = this.queue[i+1];
        }
        this.queue = new Node[s-1];
        this.queue = tmp;
        return r;
    }

    public void add(Node n) {
        int s = this.size();
        Node[] tmp = new Node[s+1];
        boolean n_inserted = false;
        for (int i = 0; i < s; i++) {
            if (!n_inserted) {
                if (n.compareTo(this.queue[i]) == -1) {
                    tmp[i] = n;
                    n_inserted = true;
                } else {
                    tmp[i] = this.queue[i];
                }
            } else {
                tmp[i] = this.queue[i-1];
            }
        }
        if (!n_inserted) {
            tmp[s] = n;
        } else {
            tmp[s] = queue[s-1];
        }
        this.queue = new Node[s+1];
        this.queue = tmp;
    }

}