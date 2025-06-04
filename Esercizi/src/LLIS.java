import java.util.LinkedList;
// .get(i)
// .size
// .isEmpty()
// .add()
// .subList()

// s = int[] {9, 46, 54, 71, 60, 47, 1, 32, 25, 61}

public class LLIS {
    private static final LinkedList<LinkedList<Integer>> list = new LinkedList<LinkedList<Integer>>();
    public static int llis(int[] s) {

        // foreach >> tipo ottenuto : da cosa lo prendo
        for (int n : s) { // per ogni elemento di s assegna ad n il valore di s (crescente)
            llisRec(n);
        }

        int i = 1;

        for (LinkedList<Integer> l : list) {

            System.out.println("Questo è l'array numero " + i + ": " + l);
            i++;

        }

        /*
        for (int i=0; i<list.size(); i++) {
            System.out.println(list.get(i));
        }
        */

        int longest = 0;

        for (LinkedList<Integer> l : list) {
            int length = l.size();
            if (length > longest) {
                longest = length;
            }
        }

        return longest;
    }

    public static void llisRec(int k) {
        // se la lista è vuota
        if (list.isEmpty()) {
            LinkedList<Integer> l = new LinkedList<Integer>();
            l.add(k);
            list.add(l);
            return;
        }

        boolean added = false;

        for (int i=list.size()-1; i>=0; i--) {
            added = false;

            for (int j=list.get(i).size()-1; j>=0 && !added; j--) {

                // se l'ultimo elemento della lista è < k
                if (k > list.get(i).get(j) && j == list.get(i).size()-1) {
                    list.get(i).add(k);
                    // break;  // esce dal for
                    added = true;

                // se gli elementi prima dell'ultimo sono < k
                } else if (k > list.get(i).get(j)) {
                    LinkedList<Integer> l1 = new LinkedList<Integer>(list.get(i).subList(0,j+1));
                    l1.add(k);
                    list.add(l1);
                    added = true;
                }

            } // for interno

        } // for esterno

        // se k non è stato aggiunto a nessuna sottolista, ne creo una nuova e la aggiungo a list
        if (!added) {
            LinkedList<Integer> l2 = new LinkedList<Integer>();
            l2.add(k);
            list.add(l2);
        }

        System.out.println();
        System.out.println();

    }

}
