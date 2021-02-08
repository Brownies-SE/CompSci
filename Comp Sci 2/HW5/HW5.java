/* Joshua Brown

*/


import java.io.*;
import java.util.*;
import java.io.BufferedWriter;
  
public class HW5 {
   
   private static ArrayList <String> dataArraylist = new ArrayList<>();
   private static java.util.LinkedList<String> datalinkedList = new java.util.LinkedList<>();
   private static linkedList[] linkedListArray = new linkedList[36];
   private static linkedList data = new linkedList();
   private static long alisTime;
   private static long llTime;
   
   public static void main(String[] args) throws Exception {
      
   
      //linkedList data = new linkedList();
      
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
         initializeLinkedLists();
         loadingArray();
         askUser();
         br.close();
         bw.close();         
         
         //System.out.println(linkedListArray[1].start.word + " " + linkedListArray[1].size);
      }
           
      catch(Exception e) {
       
         System.out.println("File was not found ");
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
      bw.write("array list number of words " + dataArraylist.size() + " in " + alisTime + "ms ");
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
                //gets the next word
            node = node.getLinkNext();
         }
      }
   }
        
        
   public static void initializeLinkedLists() {
        for(int i = 0; i < linkedListArray.length; i++){
            linkedListArray[i] = new linkedList();
        }
   }
   
     public static void askUser() {
   
        char choice;
        Scanner input = new Scanner(System.in);
        System.out.print("What character would you like to search for? ");
        char userInput = input.next().charAt(0);
        userInput = Character.toLowerCase(userInput);
        
        // if (Character.isWhitespace(userInput)) {
//         
//             System.out.println("Rerun the program, you messed it up! ");
//             System.exit(0);
//         }
                  
          
           switch (userInput) {
            case 'a':
                 int caseIndex = 0;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }
                break;
                
             case 'b':
                 caseIndex = 1;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }
                break;
                
             case 'c':
                 caseIndex = 2;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }

                break;

             case 'd':
                 caseIndex = 3;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }

                break;
   
             case 'e':
                 caseIndex = 4;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }

                break;
                
             case 'f':
                 caseIndex = 5;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }

                break;
   
              case 'g':
                 caseIndex = 6;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }

                break;
  
    
              case 'h':
                 caseIndex = 7;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }

                break;
  
              case 'i':
                 caseIndex = 8;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }

                break;
 
              case 'j':
                 caseIndex = 9;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }

                break;
 
              case 'k':
                 caseIndex = 10;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }

                break;

              case 'l':
                 caseIndex = 11;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }

                break;

              case 'm':
                 caseIndex = 12;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }

                break;

              case 'n':
                 caseIndex = 13;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }

                break;

              case 'o':
                 caseIndex = 15;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }

                break;

              case 'p':
                 caseIndex = 15;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }

                break;
                
              case 'q':
                 caseIndex = 16;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }

                break;

              case 'r':
                 caseIndex = 17;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }

                break;

              case 's':
                 caseIndex = 18;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }
                break;
  
              case 't':
                 caseIndex = 19;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }
                break;

              case 'u':
                 caseIndex = 20;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }
                break;

              case 'v':
                 caseIndex = 21;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }
                break;

              case 'w':
                 caseIndex = 22;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }
                break;

              case 'x':
                caseIndex = 23;
                System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                System.out.println(linkedListArray[caseIndex].get(i).word);
                 }
                break;

              case 'y':
                 caseIndex = 24;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }
                break;

              case 'z':
                 caseIndex = 25;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }
                break;

              case '0':
                 caseIndex = 26;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                 System.out.println(linkedListArray[caseIndex].get(i).word);
                 }
                break;

              case '1':
                 caseIndex = 27;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                 System.out.println(linkedListArray[caseIndex].get(i).word);
                 }
                break;

              case '2':
                 caseIndex = 28;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                 System.out.println(linkedListArray[caseIndex].get(i).word);
                 }
                break;

              case '3':
                 caseIndex = 29;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                 System.out.println(linkedListArray[caseIndex].get(i).word);
                 }
                break;

              case '4':
                 caseIndex = 30;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                 System.out.println(linkedListArray[caseIndex].get(i).word);
                 }
                break;

              case '5':
                 caseIndex = 31;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                 System.out.println(linkedListArray[caseIndex].get(i).word);
                 }
                break;
                
              case '6':
                 caseIndex = 32;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }
                break;

              case '7':
                 caseIndex = 33;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }
                break;

              case '8':
                 caseIndex = 34;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }
                break;

              case '9':
                 caseIndex = 35;
                 System.out.println(linkedListArray[caseIndex].getSize() + " occurrences");
                 for(int i = 0; i < linkedListArray[caseIndex].size; i++){
                  System.out.println(linkedListArray[caseIndex].get(i).word);
                 }
                break;
                
              default:
                System.out.println("Not a choice ");
                break;
            } 
    }
   
   public static void loadingArray() {
   

   // for (Node node: data) {
   Node node = data.start;
      for (int i = 1; i < dataArraylist.size(); i++) {
      
        switch (dataArraylist.get(i).charAt(0)) {
        
            case 'a':
                if (linkedListArray[0].check(node)) {
                node.count++;
                //linkedListArray[0].node.count++;
                //linkedListArray[0].get(i).count++;
                }
                else {
                linkedListArray[0].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
                
            case 'b':
                if (linkedListArray[1].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[1].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
                
            case 'c':
                if (linkedListArray[2].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[2].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
                
            case 'd':
                if (linkedListArray[3].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[3].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
                
            case 'e':
                if (linkedListArray[4].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[4].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
                
            case 'f':
                if (linkedListArray[5].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[5].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
                
            case 'g':
                if (linkedListArray[6].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[6].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
                
            case 'h':
                if (linkedListArray[7].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[7].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
                
            case 'i':
                if (linkedListArray[8].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[8].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
                
            case 'j':
                if (linkedListArray[9].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[9].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
                
            case 'k':
              if (linkedListArray[10].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[10].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
                
            case 'l':
                if (linkedListArray[11].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[11].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
                
            case 'm':
                if (linkedListArray[12].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[12].insertAtEnd(dataArraylist.get(i), i);
                }  
                break;
                
            case 'n':
                if (linkedListArray[13].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[13].insertAtEnd(dataArraylist.get(i), i);
                }   
                break;
                
            case 'o':
                if (linkedListArray[14].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[14].insertAtEnd(dataArraylist.get(i), i);
                }    
                break;
                
            case 'p':
                if (linkedListArray[15].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[15].insertAtEnd(dataArraylist.get(i), i);
                }  
                break;
                
            case 'q':
                if (linkedListArray[16].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[16].insertAtEnd(dataArraylist.get(i), i);
                }   
                break;
                
            case 'r':
                if (linkedListArray[17].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[17].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
                   
            case 's':
                if (linkedListArray[18].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[18].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
                
            case 't':
                if (linkedListArray[19].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[19].insertAtEnd(dataArraylist.get(i), i);
                }    
                break;
                
            case 'u':
                if (linkedListArray[20].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[20].insertAtEnd(dataArraylist.get(i), i);
                }    
                break;
            case 'v':
                if (linkedListArray[21].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[21].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
                
            case 'w':
                if (linkedListArray[22].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[22].insertAtEnd(dataArraylist.get(i), i);
                } 
                break;
                
            case 'x':
                if (linkedListArray[23].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[23].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
                
            case 'y':
                if (linkedListArray[24].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[24].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
                
            case 'z':
                if (linkedListArray[25].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[25].insertAtEnd(dataArraylist.get(i), i);
                } 
                break;
                
            case '0':
                if (linkedListArray[26].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[26].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
            
            case '1':
                if (linkedListArray[27].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[27].insertAtEnd(dataArraylist.get(i), i);
                }  
                break;
            
            case '2':
               if (linkedListArray[28].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[28].insertAtEnd(dataArraylist.get(i), i);
                }
               break;
               
            case '3':
               if (linkedListArray[29].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[29].insertAtEnd(dataArraylist.get(i), i);
                }
               break;
               
            case '4':
                if (linkedListArray[30].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[30].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
            
            case '5':
                if (linkedListArray[31].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[31].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
                
            case '6':
                if (linkedListArray[32].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[32].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
                
            case '7':
                if (linkedListArray[33].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[33].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
                
            case '8':
                if (linkedListArray[34].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[34].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
                
            case '9':
                if (linkedListArray[35].check(node)) {
                node.count++;
                }
                else {
                linkedListArray[35].insertAtEnd(dataArraylist.get(i), i);
                }
                break;
                
            default:
                System.out.print("Thats is not a choice");
                break;
                     
         }
         node = node.getLinkNext();
      }
   }
}
