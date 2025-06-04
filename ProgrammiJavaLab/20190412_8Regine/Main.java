public class Main {

    public static int numberOfCompletions(Board b) {
        int n = b.size();
        int q = b.queensOn();
        if (q == n) {
            return 1;
        } else {
            int i = q + 1;
            int count = 0;
            for (int j = 1; j <= n; j++) {
                if (!b.underAttack(i, j)) {
                    count += numberOfCompletions(b.addQueen(i, j));
                }
            }
            return count;
        }
    }

    public static int numberOfSolutions(int n) {
        return numberOfCompletions(new Board(n));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        System.out.println(numberOfSolutions(n));
    }

}