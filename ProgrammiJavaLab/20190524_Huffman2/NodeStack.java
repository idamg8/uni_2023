public class NodeStack {

    private Node[] stack;

    public NodeStack() {
        this.stack = new Node[0];
    }

    public boolean empty() {
        return this.stack.length == 0;
    }

    public Node peek() {
        return this.stack[0];
    }

    public Node pop() {
        int s = this.stack.length;
        Node r = this.stack[0];
        Node[] tmp = new Node[s-1];
        for (int i = 0; i < s-1; i++) {
            tmp[i] = this.stack[i+1];
        }
        this.stack = new Node[s-1];
        this.stack = tmp;
        return r;
    }

    public void push(Node n) {
        int s = this.stack.length;
        Node[] tmp = new Node[s+1];
        tmp[0] = n;
        for (int i = 0; i < s; i++) {
            tmp[i+1] = this.stack[i];
        }
        this.stack = new Node[s];
        this.stack = tmp;
    }

}