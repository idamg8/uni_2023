public class Main {

    public static final int UNKNOWN = -1;

    public static void main(String[] args) {
        System.out.println("Check if number of solutions equal the 5 test cases:");
        System.out.println(llis(new int[] {5, 4, 3, 2, 1}) == llisDP(new int[] {5, 4, 3, 2, 1}));
        System.out.println(llis(new int[] {47, 38, 39, 25, 44}) == llisDP(new int[] {47, 38, 39, 25, 44}));
        System.out.println(llis(new int[] {27, 90, 7, 29, 49, 8, 53, 1, 28, 6}) == llisDP(new int[] {27, 90, 7, 29, 49, 8, 53, 1, 28, 6}));
        System.out.println(llis(new int[] {9, 46, 54, 71, 60, 47, 0, 32, 35, 61}) == llisDP(new int[] {9, 46, 54, 71, 60, 47, 0, 32, 35, 61}));
        System.out.println(llis(new int[] {54, 52, 42, 33, 14, 40, 37, 61, 53, 1}) == llisDP(new int[] {54, 52, 42, 33, 14, 40, 37, 61, 53, 1}));
        System.out.println("Find one of the solutions of the test cases:");
        System.out.println(lis(new int[] {5, 4, 3, 2, 1}));
        System.out.println(lis(new int[] {47, 38, 39, 25, 44}));
        System.out.println(lis(new int[] {27, 90, 7, 29, 49, 8, 53, 1, 28, 6}));
        System.out.println(lis(new int[] {9, 46, 54, 71, 60, 47, 0, 32, 35, 61}));
        System.out.println(lis(new int[] {54, 52, 42, 33, 14, 40, 37, 61, 53, 1}));
        System.out.println("Count the number of steps for the test cases:"); // Non supera mai ((n^2 + 3n + 2) / 2)
        System.out.println(countSteps(new int[] {5, 4, 3, 2, 1}));
        System.out.println(countSteps(new int[] {47, 38, 39, 25, 44}));
        System.out.println(countSteps(new int[] {27, 90, 7, 29, 49, 8, 53, 1, 28, 6}));
        System.out.println(countSteps(new int[] {9, 46, 54, 71, 60, 47, 0, 32, 35, 61}));
        System.out.println(countSteps(new int[] {54, 52, 42, 33, 14, 40, 37, 61, 53, 1}));
    }

    // RECURSIVE COUNT SOLUTIONS

    public static int llis(int[] s) {
        return llisRec(s, 0, 0);
    }

    public static int llisRec(int[] s, int i, int t) {
        final int n = s.length;
        if (i == n) {
            return 0;
        } else if (s[i] <= t) {
            return llisRec(s, i+1, t);
        } else {
            return Math.max(1+llisRec(s, i+1, s[i]), llisRec(s, i+1, t));
        }
    }

    // TOP-DOWN COUNT SOLUTIONS

    public static int llisDP(int[] s) {
        int[] s1 = new int[s.length+1];
        for (int i = 0; i < s1.length; i++) {
            s1[i] = (i != s.length)? s[i] : 0;
        }
        int[][] h = new int[s.length+1][s.length+1];
        for (int i = 0; i <= s.length; i++) {
            for (int j = 0; j <= s.length; j++) {
                h[i][j] = UNKNOWN;
            }
        }
        return llisDPRec(s1, h, 0, s.length);
    }

    public static int llisDPRec(int[] s, int[][] h, int i, int j) {
        final int n = s.length - 1;
        if (i == n) {
            h[i][j] = 0;
        } else if (s[i] <= s[j]) {
            h[i][j] = llisDPRec(s, h, i+1, j);
        } else {
            h[i][j] = Math.max(1+llisDPRec(s, h, i+1, i), llisDPRec(s, h, i+1, j));
        }
        return h[i][j];
    }

    // CALCULATE ONE POSSIBLE SOLUTION

    public static SList<Integer> lis(int[] s) {
        SList<Integer> sl = new SList<Integer>();
        // Extend s to s1 (add one 0 at the end)
        int[] s1 = new int[s.length+1];
        for (int i = 0; i < s1.length; i++) {
            s1[i] = (i != s.length)? s[i] : 0;
        }
        // Compute h
        int[][] h0 = new int[s.length+1][s.length+1];
        for (int i = 0; i <= s.length; i++) {
            for (int j = 0; j <= s.length; j++) {
                h0[i][j] = UNKNOWN;
            }
        }
        int[][] h = fillMatrix(s1, h0, 0, s.length);
        // Find one solution
        int j = s.length;
        for (int i = 0; i < s.length; i++) {
            if(h[i][j] != h[i+1][j]) {
                sl = sl.cons(s1[i]);
                j = i;
            }
        }
        return sl.reverse();
    }

    private static int[][] fillMatrix(int[] s, int[][] h, int i, int j) {
        final int n = s.length -1;
        if (i == n) {
            h[i][j] = 0;
        } else if (s[i] <= s[j]) {
            h[i][j] = llisDPRec(s, h, i+1, j);
        } else {
            h[i][j] = Math.max(1+llisDPRec(s, h, i+1, i), llisDPRec(s, h, i+1, j));
        }
        return h;
    }

    // COUNT THE NUMBER OF STEPS TO FIND THE NUMBER OF SOLUTIONS

    public static int countSteps(int[] s) {
        int c = 0;
        // Extend s to s1 (add one 0 at the end)
        int[] s1 = new int[s.length+1];
        for (int i = 0; i < s1.length; i++) {
            s1[i] = (i != s.length)? s[i] : 0;
        }
        // Compute h
        int[][] h0 = new int[s.length+1][s.length+1];
        for (int i = 0; i <= s.length; i++) {
            for (int j = 0; j <= s.length; j++) {
                h0[i][j] = UNKNOWN;
            }
        }
        int[][] h = fillMatrix(s1, h0, 0, s.length);
        // Count steps
        for (int i = 0; i <= s.length; i++) {
            for (int j = 0; j <= s.length; j++) {
                if (h[i][j] != UNKNOWN) {
                    c++;
                }
            }
        }
        return c;
    }

}