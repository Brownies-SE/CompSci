public class linkedList {

    Node start;
    Node end ;
    public int size;
 
    /* Constructor */
    public linkedList() {
    
        start = null;
        end = null;
        size = 0;
    }
    /* Function to check if list is empty */
    public boolean isEmpty() {
    
        return start == null;
    }
    /* Function to get size of list */
    public int getSize() {
    
        return size;
    }
    /* Function to insert element at begining */
    public void insertAtStart(String w, int val) {
    
        Node nptr = new Node(w, val, null, null);        
        if(start == null) {
        
            start = nptr;
            end = start;
        }
        else {
        
            start.setLinkPrev(nptr);
            nptr.setLinkNext(start);
            start = nptr;
        }
        size++;
    }
    /* Function to insert element at end */
    public void insertAtEnd(String w, int val) {
    
        Node nptr = new Node(w, val, null, null);        
        if(start == null) {
        
            start = nptr;
            end = start;
        }
        else {
        
            nptr.setLinkPrev(end);
            end.setLinkNext(nptr);
            end = nptr;
        }
        size++;
    }
    /* Function to insert element at position */
    public void insertAtPos(String w, int val , int pos) {
    
        Node nptr = new Node(w, val, null, null);    
        if (pos == 1) {
        
            insertAtStart(w, val);
            return;
        }            
        Node ptr = start;
        for (int i = 2; i <= size; i++) {
        
            if (i == pos) {
            
                Node tmp = ptr.getLinkNext();
                ptr.setLinkNext(nptr);
                nptr.setLinkPrev(ptr);
                nptr.setLinkNext(tmp);
                //tmp.setLinkPrev(nptr);
            }
            ptr = ptr.getLinkNext();            
        }
        size++ ;
    }
    /* Function to delete node at position */
    public void deleteAtPos(int pos) {
            
        if (pos == 1) {
        
            if (size == 1) {
            
                start = null;
                end = null;
                size = 0;
                return; 
            }
            start = start.getLinkNext();
            start.setLinkPrev(null);
            size--; 
            return ;
        }
        if (pos == size) {
        
            end = end.getLinkPrev();
            end.setLinkNext(null);
            size-- ;
        }
        Node ptr = start.getLinkNext();
        for (int i = 2; i <= size; i++) {
        
            if (i == pos) {
            
                Node p = ptr.getLinkPrev();
                Node n = ptr.getLinkNext();
 
                p.setLinkNext(n);
                n.setLinkPrev(p);
                size-- ;
                return;
            }
            ptr = ptr.getLinkNext();
        }        
    }    
    /* Function to display status of list */
    public void display()
    {
        if (size == 0) {
        
            System.out.print("empty\n");
            return;
        }
        if (start.getLinkNext() == null) {
        
            System.out.println(start.getData() );
            return;
        }
        Node ptr = start;
        System.out.print(start.getData()+ "\n");
        ptr = start.getLinkNext();
        while (ptr.getLinkNext() != null) {
        
            System.out.print(ptr.getData()+ "\n");
            ptr = ptr.getLinkNext();
        }
        System.out.print(ptr.getData()+ "\n");
        
    }
    
    public boolean check(Node node) {
        
        Node currentNode = start;
        for (int i = 0; i < size; i++) {
            if(currentNode.word.equalsIgnoreCase(node.word)) {
                return true;
            
            }
                currentNode = currentNode.getLinkNext();
        
        }
                return false;
    }
    
    public Node get(int index){
        Node current = start;
        for(int i = 0; i < index; i++){
            current = current.getLinkNext();
        }
        return current;
    }
}