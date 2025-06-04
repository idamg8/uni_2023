import java.util.*;

import huffman_toolkit.*;


public class huffmanCode_II {


    private static final int CHARS = InputTextFile.CHARS;


    // ----------  Compressione  ----------

    // 1.
    // Istogramma delle frequenze dei caratteri
    // src: nome del documento da comprimere

    public static int[] charHistogram( String src ) {

        InputTextFile in = new InputTextFile( src );

        int[] freq = new int[ CHARS ];
        // x/100000
        freq[0] = 1;	                        //  Null character
        freq[1] = 1;	                        // 	Start of Heading
        freq[2] = 1;	                        // 	Start of Text
        freq[3] = 1;	                        // 	End of Text
        freq[4] = 1;	                        // 	End of Transmission
        freq[5] = 1;	                        // 	Enquiry
        freq[6] = 1;	                        // 	Acknowledge
        freq[7] = 1;	                        // 	Bell, Alert
        freq[8] = 1;	                        // 	Backspace
        freq[9] = 1;	                        // 	Horizontal Tab
        freq[10] = 1;	                        // 	Line Feed
        freq[11] = 1;	                        // 	Vertical Tabulation
        freq[12] = 1;	                        // 	Form Feed
        freq[13] = 1;	                        // 	Carriage Return
        freq[14] = 1;	                        // 	Shift Out
        freq[15] = 1;	                        // 	Shift In
        freq[16] = 1;	                        // 	Data Link Escape
        freq[17] = 1;	                        // 	Device Control One (XON)
        freq[18] = 1;	                        // 	Device Control Two
        freq[19] = 1;	                        // 	Device Control Three (XOFF)
        freq[20] = 1;	                        // 	Device Control Four
        freq[21] = 1;	                        // 	Negative Acknowledge
        freq[22] = 1;	                        // 	Synchronous Idle
        freq[23] = 1;	                        // 	End of Transmission Block
        freq[24] = 1;	                        // 	Cancel
        freq[25] = 1;	                        // 	End of medium
        freq[26] = 1;	                        // 	Substitute
        freq[27] = 1;	                        // 	Escape
        freq[28] = 1;	                        // 	File Separator
        freq[29] = 1;	                        // 	Group Separator
        freq[30] = 1;	                        // 	Record Separator
        freq[31] = 1;	                        // 	Unit Separator
        freq[32] = 19607;	                    // 	Space
        freq[33] = 1;	                        // 	Exclamation mark
        freq[34] = 204;	                        // 	Double quotes (or speech marks)
        freq[35] = 1;	                        // 	Number sign
        freq[36] = 1;	                        // 	Dollar
        freq[37] = 1;	                        // 	Per cent sign
        freq[38] = 1;	                        // 	Ampersand
        freq[39] = 1632;	                    // 	Single quote
        freq[40] = 1;	                        // 	Open parenthesis (or open bracket)
        freq[41] = 1;	                        // 	Close parenthesis (or close bracket)
        freq[42] = 1;	                        // 	Asterisk
        freq[43] = 1;	                        // 	Plus
        freq[44] = 1836;	                    // 	Comma
        freq[45] = 1;	                        // 	Hyphen-minus
        freq[46] = 1;	                        // 	Period, dot or full stop
        freq[47] = 1;	                        // 	Slash or divide
        freq[48] = 1;	                        // 	Zero
        freq[49] = 1;	                        // 	One
        freq[50] = 1;	                        // 	Two
        freq[51] = 1;                           // 	Three
        freq[52] = 1;	                        // 	Four
        freq[53] = 1;	                        // 	Five
        freq[54] = 1;	                        // 	Six
        freq[55] = 1;	                        // 	Seven
        freq[56] = 1;	                        // 	Eight
        freq[57] = 1;	                        // 	Nine
        freq[58] = 204;	                        // 	Colon
        freq[59] = 204;	                        // 	Semicolon
        freq[60] = 1;	                        // 	Less than (or open angled bracket)
        freq[61] = 1;	                        // 	Equals
        freq[62] = 1;	                        // 	Greater than (or close angled bracket)
        freq[63] = 1;	                        // 	Question mark
        freq[64] = 4081;	                        // 	At sign
        freq[65] = 333;	                        // 	Uppercase A
        freq[66] = 60;	                        // 	Uppercase B
        freq[67] = 113;	                        // 	Uppercase C
        freq[68] = 173;	                        // 	Uppercase D
        freq[69] = 518;	                        // 	Uppercase E
        freq[70] = 90;	                        // 	Uppercase F
        freq[71] = 82;	                        // 	Uppercase G
        freq[72] = 248;	                        // 	Uppercase H
        freq[73] = 284;	                        // 	Uppercase I
        freq[74] = 6;	                        // 	Uppercase J
        freq[75] = 31;	                        // 	Uppercase K
        freq[76] = 164;	                        // 	Uppercase L
        freq[77] = 98;	                        // 	Uppercase M
        freq[78] = 275;	                        // 	Uppercase N
        freq[79] = 306;	                        // 	Uppercase O
        freq[80] = 78;	                        // 	Uppercase P
        freq[81] = 3;	                        // 	Uppercase Q
        freq[82] = 244;	                        // 	Uppercase R
        freq[83] = 258;	                        // 	Uppercase S
        freq[84] = 369;	                        // 	Uppercase T
        freq[85] = 112;	                        // 	Uppercase U
        freq[86] = 39;	                        // 	Uppercase V
        freq[87] = 96;	                        // 	Uppercase W
        freq[88] = 6;	                        // 	Uppercase X
        freq[89] = 80;	                        // 	Uppercase Y
        freq[90] = 3;	                        // 	Uppercase Z
        freq[91] = 1;	                        // 	Opening bracket
        freq[92] = 1;	                        // 	Backslash
        freq[93] = 1;	                        // 	Closing bracket
        freq[94] = 1;	                        // 	Caret - circumflex
        freq[95] = 1;	                        // 	Underscore
        freq[96] = 1;	                        // 	Grave accent
        freq[97] = 8167;	                        // 	Lowercase a
        freq[98] = 1492;	                        // 	Lowercase b
        freq[99] = 2782;	                        // 	Lowercase c
        freq[100] = 4253;	                        // 	Lowercase d
        freq[101] = 12702;	                        //  Lowercase e
        freq[102] = 2228;	                        // 	Lowercase f
        freq[103] = 2015;	                        // 	Lowercase g
        freq[104] = 6094;	                        // 	Lowercase h
        freq[105] = 6966;	                        // 	Lowercase i
        freq[106] = 153;	                        // 	Lowercase j
        freq[107] = 772;	                        // 	Lowercase k
        freq[108] = 4025;	                        // 	Lowercase l
        freq[109] = 2406;	                        // 	Lowercase m
        freq[110] = 6749;	                        // 	Lowercase n
        freq[111] = 7507;	                        // 	Lowercase o
        freq[112] = 1929;	                        // 	Lowercase p
        freq[113] = 95;	                        // 	Lowercase q
        freq[114] = 5987;	                        // 	Lowercase r
        freq[115] = 6327;	                        // 	Lowercase s
        freq[116] = 9056;	                        // 	Lowercase t
        freq[117] = 2758;	                        // 	Lowercase u
        freq[118] = 978;	                        // 	Lowercase v
        freq[119] = 2361;	                        // 	Lowercase w
        freq[120] = 150;	                        // 	Lowercase x
        freq[121] = 1974;	                        // 	Lowercase y
        freq[122] = 74;	                        // 	Lowercase z
        freq[123] = 1;	                        // 	Opening brace
        freq[124] = 1;	                        // 	Vertical bar
        freq[125] = 1;	                        // 	Closing brace
        freq[126] = 1;	                        // 	Equivalency sign - tilde
        freq[127] = 1;	                        // 	Delete

        return freq;
    }


    // 2.
    // Albero binario di codifica dei caratteri
    // freq: istogramma delle frequenze

    public static Node huffmanTree( int[] freq ) {

        PriorityQueue<Node> queue = new PriorityQueue<Node>();  // coda con priorita' di nodi

        for ( int c=0; c<CHARS; c=c+1 ) {                       // per ogni carattere utilizzato
            if ( freq[c] > 0 ) {
                Node n = new Node( (char) c, freq[c] );             // creazione del nodo corrispondente
                queue.add( n );                                     // e inserimento nella coda
            }}
        while ( queue.size() > 1 ) {                            // finche' c'e' piu' di un nodo nella coda

            Node l = queue.poll();                                // rimozione dei due nodi di peso minimo...
            Node r = queue.poll();

            Node n = new Node( l, r );                            // ...costruzione di un (sotto) albero
            queue.add( n );                                       // e inserimento nella coda
        }
        return queue.poll();                                    // radice dell'albero di Huffman
    }


    // 3.
    // Tabella di codifica dei caratteri:
    // root: nodo radice dell'albero di Huffman

    public static String[] huffmanCodesTable( Node root ) {

        String[] codes = new String[ CHARS ];                   // tabella dei codici di Huffman

        fillTable( root, "", codes );                           // compilazione della tabella

        return codes;
    }

    // Compilazione della tabella tramite visita ricorsiva dell'albero di Huffman
    // n:     nodo visitato
    // code:  codice binario del percorso dalla radice al nodo n
    // codes: variabile di stato per registrare i codici dei caratteri

    private static void fillTable( Node n, String code, String[] codes ) {

        if ( n.isLeaf() ) {
            codes[ n.character() ] = code;                        // nodo foglia: code = codice del carattere
        } else {
            fillTable( n.left(),  code+"0", codes );              // figlio sinistro: bit 0 nel codice
            fillTable( n.right(), code+"1", codes );              // figlio destro:   bit 1 nel codice
        }
    }


    // 5.
    // Compressione
    // src: nome del documento originale
    // dst: nome del documento compresso

    public static void compress( String src, String dst ) {

        // Scansione I : frequenze dei carattteri

        int[] freq = charHistogram(src);                      // istogramma delle frequenze

        Node root = huffmanTree( freq );                        // albero di Huffman
        String[] codes = huffmanCodesTable( root );             // tabella (array) dei codici

        // Scansione II : codifica di Huffman

        InputTextFile in = new InputTextFile( src );            // documento originale

        int num = 0;
        while (in.textAvailable()){
            char c = in.readChar();
            num++;
        }

        in = new InputTextFile( src );
        OutputTextFile out = new OutputTextFile( dst );         // documento compresso

        out.writeTextLine(""+num);

        while ( in.textAvailable() ) {                       // scansione: codifica dei caratteri

            char c = in.readChar();
            out.writeCode( codes[c] );
        }
        in.close();
        out.close();
    }


    // ---------  Decompressione  ---------

    // 7.
    // Decompressione
    // src: nome del documento compresso
    // dst: nome del documento ripristinato

    public static void decompress( String src, String dst ) {

        InputTextFile in = new InputTextFile( src );            // documento compresso

        int count = Integer.parseInt( in.readTextLine() );

        int[] freq = charHistogram(src);
        Node root = huffmanTree( freq );

        OutputTextFile out = new OutputTextFile( dst );         // documento ripristinato

        for (int i = 0; i < count; i++) {                       // scansione: decodifica dei caratteri

            char c = decodeNextChar( root, in );
            out.writeChar( c );
        }
        in.close();
        out.close();
    }

    // Decodifica del carattere successivo
    // root: nodo radice dell'albero di Huffman
    // in:   documento compresso

    private static char decodeNextChar( Node root, InputTextFile in ) {

        Node n = root;                                          // percorso lungo l'albero di Huffman

        do {
            if ( in.readBit() == 0 ) {
                n = n.left();                                       // bit 0: figlio sinistro
            } else {
                n = n.right();                                      // bit 1: figlio destro
            }
        } while ( !n.isLeaf() );                                // fino al raggiungimento di una foglia

        return n.character();                                   // carattere individuato
    }


    // --------------  Main  --------------


    // 8.
    // Programma principale
    // Utilizzo:
    //   java -classpath "huffman_toolkit.jar:." Huffman compress   <documento originale> <documento compresso>
    //   java -classpath "huffman_toolkit.jar:." Huffman decompress <documento compresso> <documento ripristinato>

    public static void main( String[] args ) {

        System.out.println( "huffman coding:" );

        if ( args[0].equals("compress") ) {

            System.out.println( "compressing..." );
            compress( args[1], args[2] );

        } else if ( args[0].equals("decompress") ) {

            System.out.println( "decompressing..." );
            decompress( args[1], args[2] );

        } else {
            System.out.println( "no operation specified: 1st parameter should be either 'compress' or 'decompress'" );
        }
        System.out.println( "done." );
    }

}  // class Huffman


/*

Esempio:

  javac -classpath "huffman_toolkit.jar:." Huffman.java
  java  -classpath "huffman_toolkit.jar:." Huffman compress Huffman.java C.txt
  java  -classpath "huffman_toolkit.jar:." Huffman decompress C.txt D.txt
  diff Huffman.java D.txt

Fattore di compressione: circa 1/3 !

*/
