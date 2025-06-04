public class CorrezioneSecondoParziale {

    public static long binDP(int n, int k) {
        long[] mem = new long[n+1];
        mem[0] = 1;

        for (int i=1;i<=n;i++) {
            nextRow(i,mem);
        }
        return mem[k];
    }

    private static void nextRow(int i, long[] mem) {
        long[] v = new long[mem.length];

        for (int j=0; j<mem.length; j++) {
            v[j] = mem[j];
        }

        for (int k=1; k<i; k++) {
            mem[k] = v[k-1]+v[k];
        }
        mem[i] = 1;
    }

}
