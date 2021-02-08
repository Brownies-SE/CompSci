import java.io.*;
import java.util.*;
import java.io.BufferedWriter;
 
 
public class HomeWork3 {
    
    private static int [] counterArray = new int[40000];
    private static String [] unique = new String[40000];
    private static String [] splited = new String[40000];
    private static String line; 
     
    public static void main(String[] args) throws Exception {
       
       linkedList shit = new linkedList();
       Scanner in = new Scanner(System.in); 
       System.out.print("Enter a file name: ");   
       String filename = in.nextLine();
       
        
       try {
           FileReader fileReader = new FileReader(filename);
           BufferedReader br = new BufferedReader(fileReader);
           
           fileImport(br);
           counter();
           
           br.close();            
       }
       catch(FileNotFoundException ex) {
       
           System.out.println("Error: It must be a txt file '" +  filename + "'");                
       }
       catch(IOException ex) {
       
           System.out.println("Error reading file '" + filename + "'");                   
       }
           
   }
   
    public static void fileImport(BufferedReader br) throws Exception {
     
      int k = 0;
      String [] temp = new String[50];
      String line = null;
      
         while((line = br.readLine()) != null){
         temp = line.split("\\s+");
         
      	    for(int i = 0; i < temp.length; i++){
                splited[k] = temp[i];
                k++;
     
      }
       
   }
 }
   
   
   public static void counter() throws Exception {
       
       
       boolean hasFound = false;
       int index = 0;
       Scanner scnr = new Scanner(System.in);
       System.out.print("Enter output file name: ");
       String outputname = scnr.nextLine();

       BufferedWriter bw = null;
       File output = new File(outputname);
       FileWriter a = new FileWriter(output);
       bw =  new BufferedWriter(a);
       
            for (int j = 0; j < splited.length; j++) {
            
                if (splited[j] == null) {
                break;
        }
       
                for (int i = 0; i < unique.length; i++) {
                
                    if (splited[j].equals(unique[i])) {
                                       counterArray[i] = counterArray[i] + 1;
                        hasFound = true;
              } 
        }
        if (hasFound == false) {
            unique[index] = splited[j];
            counterArray[index]++;
            
            index++;
        }
        hasFound = false;  
            
      }
        for(int z = 0; z < unique.length; z++){
    		bw.write(unique[z] + " " + counterArray[z] + " ");
            
    }
    bw.close();
          }
}

class Node
{
    int count;
    String word;
    Node next, prev;
 
    /* Constructor */
    public Node()
    {
        next = null;
        prev = null;
        count = 1;
        word = null;
    }
    /* Constructor */
    public Node(String w, int d, Node n, Node p)
    {
        count = d;
        next = n;
        prev = p;
        word = w;
    }
    /* Function to set link to next node */
    public void setLinkNext(Node n)
    {
        next = n;
    }
    /* Function to set link to previous node */
    public void setLinkPrev(Node p)
    {
        prev = p;
    }    
    /* Funtion to get link to next node */
    public Node getLinkNext()
    {
        return next;
    }
    /* Function to get link to previous node */
    public Node getLinkPrev()
    {
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
 
/* Class linkedList */
class linkedList
{
    Node start;
    Node end ;
    public int size;
 
    /* Constructor */
    public linkedList()
    {
        start = null;
        end = null;
        size = 0;
    }
    /* Function to check if list is empty */
    public boolean isEmpty()
    {
        return start == null;
    }
    /* Function to get size of list */
    public int getSize()
    {
        return size;
    }
    /* Function to insert element at begining */
    public void insertAtStart(String w, int val)
    {
        Node nptr = new Node(w, val, null, null);        
        if(start == null)
        {
            start = nptr;
            end = start;
        }
        else
        {
            start.setLinkPrev(nptr);
            nptr.setLinkNext(start);
            start = nptr;
        }
        size++;
    }
    /* Function to insert element at end */
    public void insertAtEnd(String w, int val)
    {
        Node nptr = new Node(w, val, null, null);        
        if(start == null)
        {
            start = nptr;
            end = start;
        }
        else
        {
            nptr.setLinkPrev(end);
            end.setLinkNext(nptr);
            end = nptr;
        }
        size++;
    }
    /* Function to insert element at position */
    public void insertAtPos(String w, int val , int pos)
    {
        Node nptr = new Node(w, val, null, null);    
        if (pos == 1)
        {
            insertAtStart(w, val);
            return;
        }            
        Node ptr = start;
        for (int i = 2; i <= size; i++)
        {
            if (i == pos)
            {
                Node tmp = ptr.getLinkNext();
                ptr.setLinkNext(nptr);
                nptr.setLinkPrev(ptr);
                nptr.setLinkNext(tmp);
                tmp.setLinkPrev(nptr);
            }
            ptr = ptr.getLinkNext();            
        }
        size++ ;
    }
    /* Function to delete node at position */
    public void deleteAtPos(int pos)
    {        
        if (pos == 1) 
        {
            if (size == 1)
            {
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
        if (pos == size)
        {
            end = end.getLinkPrev();
            end.setLinkNext(null);
            size-- ;
        }
        Node ptr = start.getLinkNext();
        for (int i = 2; i <= size; i++)
        {
            if (i == pos)
            {
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
        System.out.print("\nDoubly Linked List = ");
        if (size == 0) 
        {
            System.out.print("empty\n");
            return;
        }
        if (start.getLinkNext() == null) 
        {
            System.out.println(start.getData() );
            return;
        }
        Node ptr = start;
        System.out.print(start.getData()+ " <-> ");
        ptr = start.getLinkNext();
        while (ptr.getLinkNext() != null)
        {
            System.out.print(ptr.getData()+ " <-> ");
            ptr = ptr.getLinkNext();
        }
        System.out.print(ptr.getData()+ "\n");
    }
}
