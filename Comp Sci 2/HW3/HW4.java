/* Joshua Brown

This program takes input from a file and splits it into strings.
Thos strings are stored in a array list as well as a linked list.
Then it is sorted and counted, and displays the words and count,
as well at the sorting times for the array list and linked list.*/


import java.io.*;
import java.util.*;
import java.io.BufferedWriter;

public class HW4 {
   
   private static ArrayList <String> dataArraylist = new ArrayList<>();
   private static java.util.LinkedList<String> datalinkedList = new java.util.LinkedList<>();
   private static long alisTime;
   private static long llTime;
   
   public static void main(String[] args) throws Exception {
      
   
      linkedList data = new linkedList();
      
      Scanner scnr = new Scanner(System.in); 
           
      try {
         System.out.print("Enter a file name: ");   
         String getfilename = scnr.nextLine();
         File filename = new File (getfilename);
         
         String line = null;
         FileReader fileReader = new FileReader(filename);
         BufferedReader br = new BufferedReader(fileReader);
         
         System.out.print("Enter ouput name: ");
         String outputFile = scnr.nextLine();
         
         
         BufferedWriter bw = null;
         File output = new File(outputFile);
         FileWriter a = new FileWriter(output);
         bw =  new BufferedWriter(a);
         
         fileImport(br, data);
         long start;
         long end;
         
         start = System.currentTimeMillis();
         Collections.sort(dataArraylist);
         end = System.currentTimeMillis();
         alisTime = (end - start);
         
         start = System.currentTimeMillis();
         Collections.sort(datalinkedList);
         end = System.currentTimeMillis();
         llTime = (end - start);
         
         fileWriting(bw, data);
         br.close();
         bw.close();         
         
      }
           
      catch(FileNotFoundException e) {
       
         System.out.println("File was not found ");
         e.printStackTrace();                
      }
      catch(IOException e) {
         
          System.out.println("Error reading file '");
          e.printStackTrace();                   
       }
   }


   public static void fileImport(BufferedReader br, linkedList data) throws Exception {
     //importing the file into lines and then splitting into words
      String [] array;
      String line = null;
        
        while ((line = br.readLine()) != null) {
                line = line.toLowerCase();
                line = line.replaceAll("[\\p{P}\\d]", " ");
                array = line.split("\\s+");
                
                for(int k = 0; k < array.length; k++) {
                    String word = array[k];
                    sorting(word, data);
                    
                    if (!dataArraylist.contains(word)) {
                    dataArraylist.add(word);
                    datalinkedList.add(word);
                    }
                }
         		
        }
            
   }
     
    public static void fileWriting(BufferedWriter bw, linkedList data) throws Exception {
        //writing to the file, writing the word and the count
        Node node = data.start;
        node = node.getLinkNext();
            for (int k = 2; k <= data.getSize(); k++) {
            bw.write(node.word + " " + node.count + "\r\n");
            //moves to the next word in the file once counted
            node = node.getLinkNext();
            }   
            bw.write("array list number of words " + dataArraylist.size() + " in " + alisTime + "ms " + "\n");
            bw.newLine();
            bw.write("Linked list number of words " + datalinkedList.size() + " in " + llTime + "ms");
    } 
    
    public static void sorting(String w, linkedList data) {
        //if the data is empty start ar position 1
        Node node = data.start;
        if (data.isEmpty()) {
         data.insertAtStart(w, 1);
      }
      
         else {
      
            for(int k = 1; k <= data.getSize(); k++) {
                //compares with word and then adds word to the end of the list
                if ((w.compareTo(node.word) > 0) && node.next == null ) {
                    data.insertAtEnd(w, 1);
                    break;
            }
                //compares words and then adds to the beginning
                else if (w.compareTo(node.word) < 0) {
                    data.insertAtPos(w, 1, k);
                    break;
            }    
                //sees the word has already been implemented and adds to count
                else if (w.compareTo(node.word) == 0) {
                    node.count++;
                    break;
            }
                    //get the next word
                    node = node.getLinkNext();
      }
    }
  } 
}
