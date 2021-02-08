import java.io.*;
import java.util.*;
import java.io.BufferedWriter;
import java.io.IOException;


public class sorting {

    private static ArrayList<String> aList = new ArrayList<String>();
    
    public static void main(String []args){
    
        
    getStuff();
    Collections.sort(aList);
    System.out.println(aList);
    
    BubbleSort bsort = new BubbleSort(aList);
    bsort.sort();
    ArrayList<String> bsortwords = bsort.getArrayList();
    System.out.println(bsortwords);
    }
    
    
    public static void getStuff() {
    
     Scanner userInput = new Scanner(System.in);
     
      try {
         System.out.print("Enter a file name: ");   
         String getfilename = userInput.nextLine();
         BufferedReader bufReader = new BufferedReader(new FileReader(getfilename));
         
         
         System.out.print("Enter output file name: ");
         String outputFileName = userInput.nextLine();
         BufferedWriter bufWriter = new BufferedWriter(new FileWriter(outputFileName));
         
         String line;
         
        while ((line = bufReader.readLine()) != null) {
            String[] words = line.split(" ");
            for (String word: words) {
                aList.add(word.toLowerCase());
                
            }
           
        }
        
         	System.out.println(aList.size());	
      
                 
        bufReader.close();
        bufWriter.close();
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
 }
 
    
