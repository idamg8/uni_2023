
/* 
 * Codifica di Huffman (8/05/2014)
 *
 * Classe Node
 * nodi dell'albero di Huffman
 *
 * implements Comparable<Node>:
 * le istanze di Node possono essere ordinate in una coda con priorita'
 */

public class Node  implements Comparable<Node> {


  private final char ch;
  
  private final int weight;
  private final Node left, right;
  
  
  // Costruttori: differenziati per il tipo degli argomenti
  
  public Node( char ch, int weight ) {    // nodi foglia
    
    this.ch = ch;
    this.weight = weight;
    this.left = null;
    this.right = null;
  }
  
  public Node( Node left, Node right ) {  // nodi interni
    
    this.ch = (char) 0;
    this.weight = left.weight() + right.weight();
    this.left = left;
    this.right = right;
  }
  
  
  // Il nodo e' una foglia dell'albero di Huffman?
  
  public boolean isLeaf() {
    
    return ( this.left == null );
  }
  
  
  // Metodi per acquisire le informazioni relative ai nodi
  
  public char character() {
    
    return this.ch;                           // carattere (nodi foglia)
  }
  
  public int weight() {
    
    return this.weight;                       // peso
  }
  
  public Node left() {
    
    return this.left;                         // figlio sinistro
  }
  
  public Node right() {
    
    return this.right;                        // figlio destro
  }
  
  
  // Confronto dei pesi dei nodi
  // in accordo alle specifiche per la gestione in una coda con priorita'
  // (protocollo di Comparable<Node>)
  
  public int compareTo( Node n ) {
  
    if ( this.weight == n.weight() ) {        // =
      return  0;
    } else if ( this.weight < n.weight() ) {  // <
      return -1;
    } else {                             // >
      return  1;
    }
  }
  
  
  // Rappresentazione testuale di un nodo
  
  public String toString() {
    
    if ( this.isLeaf() ) {
      if ( (this.character() == '@') || (this.character() == '\\') ) {
        return "\\" + this.character() + ":" + this.weight();
      } else {
        return this.character() + ":" + this.weight();
      }
    } else {
      return "@:" + this.weight();
    }
  }

}  // class Node

