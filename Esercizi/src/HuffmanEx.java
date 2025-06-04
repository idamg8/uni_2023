import huffman_toolkit.*;

public class HuffmanEx {

    // input n: numero di caratteri da scrivere
    public static OutputTextFile randomText(String nameFile) {

        // crea nuovo file in scrittura
        OutputTextFile file = new OutputTextFile(nameFile);  // creato file per scrittura

        // scrive file di lunghezza nameFile.length*128
        for (int i=0; i<nameFile.length()*100; i++) {


            // 128 caratteri di ascii, ne restituisce uno casuale
            char carattere = (char) (128 * Math.random());
            //System.out.println(carattere);

            file.writeChar(carattere);

        }

        file.close();

        return file;

    }


}  // class HuffmanEx
