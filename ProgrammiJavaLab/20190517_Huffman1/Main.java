import java.util.*;
import huffman_toolkit.*;

public class Main {

    public static void main(String[] args) {
        randomFile(args[0]);
        tableGen(args[0], args[1]);
    }

    public static void randomFile(String name) {
        OutputTextFile otf = new OutputTextFile(name);
        int n = ((int)(Math.random() * 100000)) % 5000;
        for (int i = 0; i < n; i++) {
            char c = (char)(((int)(Math.random() * 100000)) % InputTextFile.CHARS);
            otf.writeChar(c);
        }
        otf.close();
    }

    public static void tableGen(String in, String out) {
        int[] fs = freqs(in);
        HNode t = hTree(fs);
        String[] cs = codesGen(t);
        OutputTextFile otf = new OutputTextFile(out);
        for (int i = 0; i < InputTextFile.CHARS; i++) {
            if (fs[i] != 0) {
                otf.writeTextLine("" + i + " " + (char)i + " " + fs[i] + " " + cs[i] + " " + cs[i].length());
            }
        }
        otf.close();
    }

    public static int[] freqs(String in) {
        int[] fs = new int[InputTextFile.CHARS];
        for (int i = 0; i < fs.length; i++) {
            fs[i] = 0;
        }
        InputTextFile itf = new InputTextFile(in);
        while(itf.textAvailable()) {
            char c = itf.readChar();
            fs[c]++;
        }
        itf.close();
        return fs;
    }

    public static HNode hTree(int[] fs) {
        PriorityQueue<HNode> q = new PriorityQueue<HNode>();
        for (int i = 0; i < fs.length; i++) {
            if (fs[i] != 0) {
                HNode n = new HNode((char)i, fs[i]);
                q.add(n);
            }
        }
        while(q.size() > 1) {
            HNode n = q.poll();
            HNode m = q.poll();
            q.add(new HNode(n, m));
        }
        return q.poll();
    }

    public static String[] codesGen(HNode t) {
        String[] codes = new String[InputTextFile.CHARS];
        for (int i = 0; i < codes.length; i++) {
            codes[i] = "";
        }
        return codesGenRec(t, codes, "");
    }

    public static String[] codesGenRec(HNode n, String[] codes, String prefix) {
        if (n.isLeaf()) {
            codes[n.character()] = prefix;
        } else {
            codes = codesGenRec(n.left(), codes, prefix + "0");
            codes = codesGenRec(n.right(), codes, prefix + "1");
        }
        return codes;
    }

}