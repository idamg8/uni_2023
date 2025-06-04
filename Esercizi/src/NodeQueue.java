public class NodeQueue {

    /**
     *
     * 2. Realizzazione di una “Coda con Priorità”
     *
     * Definisci una classe NodeQueue che possa sostituire PriorityQueue<Node> nel programma Huffman offrendo
     * le stesse funzionalità della classe predefinita quando gli oggetti inseriti sono di tipo Node. Il protocollo deve pertanto
     * prevedere il costruttore e i metodi così specificati:
     *
     * public NodeQueue() costruttore: creazione della coda di nodi vuota
     * public int size() restituisce il numero di elementi contenuti nella coda
     * public Node peek() restituisce l’elemento con “peso minore” (senza rimuoverlo dalla coda)
     * public Node poll() restituisce e rimuove dalla coda l’elemento con “peso minore”
     * public void add( Node n ) aggiunge un nuovo elemento n alla coda
     *
     */

    private Node[] array;

    public NodeQueue(){             // costruttore pubblico per nuova Queue vuota

        this.array = new Node[]{};

    }

    private NodeQueue(Node n){      // costruttore privato per aggiungere un nodo a Queue

        this.array = new Node[]{n};

    }

    public int size(){

        return array.length;

    }

    public Node peek(){

        return array[size()-1];

    }

    public Node poll(){
        // prendo l'ultimo elemento dell'array
        Node temp = array[size()-1];
        // creo un nuovo array con dimensione diminuita
        Node[] tempA = new Node[size()-1];
        // riempio il nuovo array con tutti gli elementi del vecchio array (tranne l'ultimo)
        for (int i = 0; i < size()-1; i++){
            tempA[i] = array[i];
        }
        // assegno alla variabile d'istanza array il nuovo array più piccolo
        array = tempA;
        // ritorno l'ultimo nodo del vecchio array (quello che mostra peek ma senza toglierlo)
        return temp;

    }

    public void add(Node n){

        int position = 0;
        // trovo la posizione in cui inserire il nuovo nodo
        for (int i = 0; i < size(); i++){
            // se il peso del nuovo nodo è maggiore del peso del nodo in posizione i
            // ho trovato la posizione in cui inserire il nodo, esco dal ciclo
            if(n.weight() > array[i].weight()){
                break;
            }
            // continuo ad incrementare l'indice finché non trovo un nodo che abbia peso minore di n
            position++;
        }

        // creo nuovo array di dimensione incrementata per poterci inserire il nodo aggiuntivo
        Node[] temp = new Node[size()+1];

        // nell'indice trovato in precedenza inserico il nuovo nodo
        temp[position] = n;
        // nelle posizioni precedenti a position re-inserisco tutti i valori come da array precedente
        for (int i = 0; i < position; i++){
            temp[i] = array[i];
        }
        // da position in poi inserisco i valori successivi dell'array originale
        for (int i = position; i < size(); i++){
            temp[i+1] = array[i];
        }
        // assegno il nuovo array alla variabile d'istanza del costruttore
        array = temp;

    }

    /*public void printer(){
        for (int i = 0; i < size(); i++){
            System.out.println(array[i]);
        }
    }*/

}