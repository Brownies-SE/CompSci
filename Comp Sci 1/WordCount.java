import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;


public class WordCount {

   public static class WordObject {
   
      public String word;
      public int count;
      public WordObject prev, next;
   
   
      public WordObject() {
         this.prev = null;
         this.next = null;
         
         this.count = 0;
      }
   }
   
   private static String wordLine [] = new String[100000]; 
   private static WordObject head = new WordObject();
   //private static String newWord [] = new String[30000];
   //private static int wordCount [] = new int[30000];
   private static String line;
   private static int uniqueWords = 0;
   
   public static WordObject insert(String word){
   
      WordObject currentNode = new WordObject();
      currentNode.word = word;
      currentNode.count = 1;
      
      if (head == null){
         
         head = currentNode;
         return head;
      }
      
      if (word.compareTo(head.word) == -1){
        
         head.prev = currentNode;
         currentNode.next = head;
         head = currentNode;
         
         return head;
      
      }
      
      WordObject before = head;
      
      for (; before.next != null; before = before.next){
         
         if (before.next.word.compareTo(word) == 1){
         
            break;
            
         }
      }
      
      currentNode.next = before.next;
      
      if (before.next != null){
      
         before.next.prev = currentNode;
      }
      
      before.next = currentNode;
      currentNode.prev = before;
         
      return head;      
   }
      
   public static void main (String[] args)throws Exception{
     
      Scanner keys = new Scanner(System.in);
     
      while(true){
      
         try{
          
            System.out.print("Give input file ");
            String input = keys.nextLine();
         
            File story = new File(input);
            BufferedReader in = new BufferedReader(new FileReader(story));
            fileImport(in, keys);
         
            break;
         }
         
         catch(Exception e){
         
            System.out.println(e.toString());
            
         }
         
      }
      
   }
     
   public static void  fileImport(BufferedReader in, Scanner keys) throws Exception {
       
      BufferedWriter bw = null;
      System.out.print("Name output file ");
      String output = keys.nextLine();
      output = output + ".txt";
      File out = new File(output);
      FileWriter word = new FileWriter(out);
      bw = new BufferedWriter(word);
      
      String lineList [] = new String[1000];
      String line = null;
      int loc = 0;
      
      while((line = in.readLine()) != null){
         //Make every character in the line lowercase
         //Remove puncuation
         //Split line into seperate words 
         line = line.toLowerCase();
         line = line.replaceAll("\\.|\\!|\\?|\\;|\\,|\\:|\"", "");
         lineList = line.split("\\s+");
            
         for(int i = 0; i < lineList.length; i++){
            //Read in each split line into an array
            wordLine[loc] = lineList[i];
            loc++;
               
         }
         
         wordFrequency(lineList, bw);
        
      }
      
      for(int k = 0; k < uniqueWords; k++){
         //Wrtie each line into a new location into an array
         bw.write(newWord[k] + " " + wordCount[k] + "\n");
      
      }
      
      bw.close();
      
   }
   
   public static void wordFrequency(String LineList [], BufferedWriter bw)throws IOException {
      
      boolean foundCopy = false;
      
      for(String word : LineList){
         //Iterate over every word in the old list
         
         for(int i = 0; i < newWord.length; i++) {
            //Iterate over every word in the new list
            
            if(word.equals(newWord[i])) {
               //Check if the word is already in the list
               
               wordCount[i]++;
               foundCopy = true;
            
            }
            
         }
         
         if(foundCopy == false) {
            // The word is not already in the newWord array
            
            newWord[uniqueWords] = word;
            wordCount[uniqueWords] = 1;
            uniqueWords++;
         }
         
         foundCopy = false;
         
      }
             
   
   }   
   
         
}