public class Node {

    int count;
    String word;
    Node next, prev;
 
    /* Constructor */
    public Node() {
    
        next = null;
        prev = null;
        count = 1;
        word = null;
    }
    /* Constructor */
    public Node(String w, int d, Node n, Node p) {
    
        count = d;
        next = n;
        prev = p;
        word = w;
    }
    /* Function to set link to next node */
    public void setLinkNext(Node n) {
        next = n;
    }
    /* Function to set link to previous node */
    public void setLinkPrev(Node p) {
    
        prev = p;
    }    
    /* Funtion to get link to next node */
    public Node getLinkNext() {
    
        return next;
    }
    /* Function to get link to previous node */
    public Node getLinkPrev() {
    
        return prev;
    }
    /* Function to set count to node */
    public void setData(String w, int d)
    {
        count = d;
        word = w;
    }
    /* Function to get count from node */
    public String getData()
    {
        return (word + " " + count);
        
    }
}
 
