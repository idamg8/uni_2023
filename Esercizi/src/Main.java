import java.util.*;

public class Main {

    /*
    public static String closestPair(double[] array) {

        double[] lowestCouple = new double[]{array[0],array[1]};
        double currentSub = Math.abs(array[0] - array[1]);
        double temp = 0;

        for (int i=0; i<array.length; i++) {
            for (int j=0; j<array.length; j++) {

                if (array[i] != array[j]) {
                    temp = Math.abs(array[i] - array[j]);
                    if (temp < currentSub) {
                        lowestCouple[0] = array[i];
                        lowestCouple[1] = array[j];
                        currentSub = temp;
                    }
                }
            }
        }

        return Arrays.toString(lowestCouple);

    }

     */

    public static void main(String[] args) {

        /*
        FifteenGameK Board = new FifteenGameK(3);
        Board.game();

         */

        // FifteenGame.playGame(3);

        /*
        HuffmanEx.randomText("ProvaProvaVediamoSeFunziona.txt");

        Huffman.compress("ProvaProvaVediamoSeFunziona.txt", "ProvaProvaVediamoSeFunzionaCompressed.txt");

        Huffman.decompress("ProvaProvaVediamoSeFunzionaCompressed.txt", "ProvaProvaVediamoSeFunzionaDecompressed.txt" );

         */


        /*NodeQueue coda = new NodeQueue();
        Node n = new Node('C',8);
        coda.add(n);
        System.out.println(coda.queueToString());*/

        /*int[][] matriz = new int[][]{{1,2,3},{2,1,4},{3,4,1}};
        System.out.println(JavaProgramming.isSimmetrico(matriz));*/


        //System.out.println(closestPair(new double[] {0.3, 0.1, 0.6, 0.8, 0.5, 1.1}));



        //Node tree = Huffman.huffmanTree(Huffman.charHistogram("CleanLorem.txt"));

        //System.out.println(JavaProgramming.shortestCodeLength(tree));

        //System.out.println(JavaProgramming.huffmanCodeSize(tree));

        //System.out.println(JavaProgramming.codeSizeIter(tree));


        //System.out.println(JavaProgramming.commonStretches("1010110110", "1100011101"));

        //System.out.println(JavaProgramming.llcs3Mem("ciao", "come", "stai"));


        /*System.out.println(Arrays.toString(JavaProgramming.heapTest(new double[] {5.0,3.1,5.7,3.1,8.5,6.0,3.8,4.2,9.3})));
        System.out.println(Arrays.toString(JavaProgramming.heapTest(new double[] {5.0,3.1,5.7,3.1,8.5,6.0,3.0,4.2,9.3})));

        System.out.println(JavaProgramming.heapCheck(new double[] {5.0,3.1,5.7,3.1,8.5,6.0,3.8,4.2,9.3}));
        System.out.println(JavaProgramming.heapCheck(new double[] {5.0,3.1,5.7,3.1,8.5,6.0,3.0,4.2,9.3}));*/

        //System.out.println(JavaProgramming.lpsDP("irradiare"));

        //FifteenGame.playGame(4);

        /*HuffmanEx.randomText("oddio.txt");
        Huffman.compress("oddio.txt", "oddioCompressed.txt");
        Huffman.decompress("oddioCompressed.txt", "oddioDecompressed.txt");*/

        //String[][][] matrix = new String[1][2][2];
        //matrix[0][1] = new String[2];

        //System.out.println(Arrays.toString(matrix[0][1]));

        System.out.println(CorrezioneSecondoParziale.binDP(5,2));

    }


}

