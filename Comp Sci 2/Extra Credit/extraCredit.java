import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.io.BufferedWriter;
import java.io.IOException;


public class extraCredit {

    private static ArrayList<String> aList = new ArrayList<String>();
    private static long bubbleTime;
    private static long selectionTime;
    private static long javaTime;
    private static long quickTime;

    public static void main(String []args) throws Exception {
    
    Scanner userInput = new Scanner(System.in);
    
    try {
         System.out.print("Enter a file name: ");   
         String getfilename = userInput.nextLine();
         BufferedReader bufReader = new BufferedReader(new FileReader(getfilename));
                 
         PrintWriter pw = new PrintWriter ("EX1.out");
         
         BufferedWriter bufWriter = null;
         bufWriter = new BufferedWriter(pw);
         
         

         String st; 
            while ((st = bufReader.readLine()) != null) {
             String[] nums = st.split("\\s+");
                for (String numbers: nums) {
                    aList.add(numbers);
                }    
              //System.out.println(aList.size());
            }
            
            long start;
            long end;
            start = System.currentTimeMillis();
            bubbleSort.bubblesort(aList);
            end = System.currentTimeMillis();
            bubbleTime = (end - start);
            System.out.println(aList);
            Collections.shuffle(aList);
            
            start = System.currentTimeMillis();
            SelectionSort.selectionsort(aList);
            end = System.currentTimeMillis();
            selectionTime = (end - start);
            System.out.println(aList);
            Collections.shuffle(aList);
            
            start = System.currentTimeMillis();
            Collections.sort(aList);
            end = System.currentTimeMillis();
            javaTime = (end - start);
            System.out.println(aList);
            Collections.shuffle(aList);
            
            timing(bufWriter, aList);
            
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


    public static void timing(BufferedWriter bufWriter, ArrayList aList) throws Exception  {
    
        bufWriter.write("Bubble sort time " + bubbleTime + "ms ");
        bufWriter.newLine();
        bufWriter.write("Selection sort time " + selectionTime + "ms ");
        bufWriter.newLine();
        bufWriter.write("Java collections sort time " + javaTime + "ms ");
    }
}

    class bubbleSort {
    
        public static void bubblesort(ArrayList<String> aList) {
        
        
        for (int j = 1; j < aList.size(); j++) {
            for (int i = 0; i < aList.size() - 1; i++) {
                if (aList.get(i).compareTo(aList.get(i + 1)) > 0) { 
                 
                    
                        String temp = aList.get(i);
                        aList.set(i,aList.get(i+1));
                        aList.set(i+1, temp);
                        
         }
            }               
                } 
        }
    }
    
    class SelectionSort {
    
        public static void selectionsort(ArrayList<String> aList) {
        
            for (int i = 0; i < aList.size(); i++) {
                int minIndex = i;
      
                for (int j = i + 1; j < aList.size(); j++) {
        
                     if (Integer.valueOf(aList.get(j)) < Integer.valueOf(aList.get(minIndex))) {
                        minIndex = j;
          
                     }
                }
      
                        String temp = aList.get(i);
                        aList.set(i, aList.get(minIndex));
                        aList.set(minIndex, temp);
            }
        
        }
    
    
    }
    
    class QuickSort {
    
    
    
    
    
    }