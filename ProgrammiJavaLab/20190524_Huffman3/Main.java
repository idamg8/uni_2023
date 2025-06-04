import java.util.*;
import huffman_toolkit.*;

public class Main {

    public static void main(String[] args) {
        compress(args[0], args[1]);
        decompress(args[1], "Decompressed.txt");
    }

    // COMPRESS

    public static int[] occTab() {
        int[] f = new int[InputTextFile.CHARS];
        f[0] = 1; // null
        f[1] = 1; // start of heading
        f[2] = 1; // start of text
        f[3] = 1; // end of text
        f[4] = 1; // end of transmission
        f[5] = 1; // enquiry
        f[6] = 1; // acknowledge
        f[7] = 1; // bell
        f[8] = 1; // backspace
        f[9] = 1; // horizontal tab
        f[10] = 1632; // new line
        f[11] = 1; // vertical tab
        f[12] = 1; // new page
        f[13] = 1632; // carriage return
        f[14] = 1; // shift out
        f[15] = 1; // shift in
        f[16] = 1; // data link escape
        f[17] = 1; // device control 1
        f[18] = 1; // device control 2
        f[19] = 1; // device control 3
        f[20] = 1; // device control 4
        f[21] = 1; // negative acknowledge
        f[22] = 1; // synchronus idle
        f[23] = 1; // end of transmission block
        f[24] = 1; // cancel
        f[25] = 1; // end of medium
        f[26] = 1; // substitute
        f[27] = 1; // escape
        f[28] = 1; // file separator
        f[29] = 1; // group separator
        f[30] = 1; // record separator
        f[31] = 1; // unit separator
        f[32] = 19607; // space
        f[33] = 1; // !
        f[34] = 4081; // "
        f[35] = 1; // #
        f[36] = 1; // $
        f[37] = 1; // %
        f[38] = 1; // &
        f[39] = 1; // '
        f[40] = 1; // (
        f[41] = 1; // )
        f[42] = 1; // *
        f[43] = 1; // +
        f[44] = 4082; // ,
        f[45] = 1; // -
        f[46] = 4082; // .
        f[47] = 1; // /
        f[48] = 816; // 0
        f[49] = 816; // 1
        f[50] = 816; // 2
        f[51] = 816; // 3
        f[52] = 816; // 4
        f[53] = 816; // 5
        f[54] = 816; // 6
        f[55] = 816; // 7
        f[56] = 816; // 8
        f[57] = 816; // 9
        f[58] = 1; // :
        f[59] = 1; // ;
        f[60] = 1; // <
        f[61] = 1; // =
        f[62] = 1; // >
        f[63] = 1; // ?
        f[64] = 1; // @
        f[65] = 1542; // A
        f[66] = 282; // B
        f[67] = 525; // C
        f[68] = 803; // D
        f[69] = 2399; // E
        f[70] = 421; // F
        f[71] = 381; // G
        f[72] = 1151; // H
        f[73] = 1317; // I
        f[74] = 29; // J
        f[75] = 146; // K
        f[76] = 760; // L
        f[77] = 454; // M
        f[78] = 1275; // N
        f[79] = 1418; // O
        f[80] = 364; // P
        f[81] = 18; // Q
        f[82] = 1131; // R
        f[83] = 1195; // S
        f[84] = 1710; // T
        f[85] = 521; // U
        f[86] = 186; // V
        f[87] = 446; // W
        f[88] = 28; // X
        f[89] = 373; // Y
        f[90] = 15; // Z
        f[91] = 1; // [
        f[92] = 1; // \
        f[93] = 1; // ]
        f[94] = 1; // ^
        f[95] = 1; // _
        f[96] = 1; // `
        f[97] = 3085; // a
        f[98] = 564; // b
        f[99] = 1051; // c
        f[100] = 1606; // d
        f[101] = 4797; // e
        f[102] = 842; // f
        f[103] = 761; // g
        f[104] = 2302; // h
        f[105] = 2632; // i
        f[106] = 58; // j
        f[107] = 292; // k
        f[108] = 1520; // l
        f[109] = 909; // m
        f[110] = 2549; // n
        f[111] = 2835; // o
        f[112] = 729; // p
        f[113] = 36; // q
        f[114] = 2261; // r
        f[115] = 2390; // s
        f[116] = 3420; // t
        f[117] = 1042; // u
        f[118] = 369; // v
        f[119] = 892; // w
        f[120] = 57; // x
        f[121] = 746; // y
        f[122] = 29; // z
        f[123] = 1; // {
        f[124] = 1; // |
        f[125] = 1; // }
        f[126] = 1; // ~
        f[127] = 1; // delete
        return f;
    }

    public static HNode huffTree(int[] freq) {
        PriorityQueue<HNode> q = new PriorityQueue<HNode>();
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                HNode n = new HNode((char) i, freq[i]);
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

    public static String[] codesTab(HNode n) {
        String[] ctab = new String[InputTextFile.CHARS];
		Stack<Pair> s = new Stack<Pair>();
		s.push(new Pair(n, ""));
		while(!s.empty()) {
			Pair cp = s.pop();
			n = cp.node;
			if (n.isLeaf()) {
				char c = n.character();
				ctab[c] = cp.prefix;
			} else {
				s.push(new Pair(n.right(), cp.prefix + "1"));
				s.push(new Pair(n.left(), cp.prefix + "0"));
			}
        }
        return ctab;
    }

    public static void compress(String doc, String com) {
        int[] freq = occTab();
        HNode t = huffTree(freq);
        String[] codes = codesTab(t);
        InputTextFile itf = new InputTextFile(doc);
        OutputTextFile otf = new OutputTextFile(com);
        int count = 0;
        InputTextFile in = new InputTextFile(doc);
        while(in.textAvailable()) {
            char c = in.readChar();
            count++;
        }
        in.close();
        otf.writeTextLine("" + count);
        for (int i = 0; i < count; i++) {
            char c = itf.readChar();
            otf.writeCode(codes[c]);
        }
        itf.close();
        otf.close();
    }

    // DECOMPRESS

    public static void decompress(String com, String dec) {
        InputTextFile in = new InputTextFile(com);
        OutputTextFile out = new OutputTextFile(dec);
        int count = Integer.parseInt(in.readTextLine());
        int[] freq = occTab();
        HNode t = huffTree(freq);
        for (int i = 0; i < count; i++) {
            HNode n = t;
            while(!n.isLeaf()) {
                int bit = in.readBit();
                if (bit == 0) n = n.left();
                else n = n.right();
            }
            out.writeChar(n.character());
        }
        in.close();
        out.close();
    }

}