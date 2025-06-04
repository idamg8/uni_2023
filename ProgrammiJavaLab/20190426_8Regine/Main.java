import queens.*; // Need to compile with "javac -cp ;./queens.jar Main.java Board.java SList.java"

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

    public static SList<Board> listOfCompletions(Board b) {
        int n = b.size();
        int q = b.queensOn();
        if (q == n) {
            return (new SList<Board>()).cons(b);
        } else {
            int i = q + 1;
            SList<Board> solutions = new SList<Board>();
            for (int j = 1; j <= n; j++) {
                if (!b.underAttack(i, j)) {
                    solutions = solutions.append(listOfCompletions(b.addQueen(i, j)));
                }
            }
            return solutions;
        }
    }

    public static SList<Board> listOfAllSolutions(int n) {
        return listOfCompletions(new Board(n));
    }

    public static void displayAllSolutions(int n) {
        SList<Board> bl = listOfCompletions(new Board(n));
        ChessboardView gui = new ChessboardView(n);
        for (int i = 0; i < bl.length(); i++) {
            gui.setQueens(bl.ref(i).arrangement());
                }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        System.out.println(numberOfSolutions(n));
        displayAllSolutions(n);
    }

}