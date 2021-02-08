import java.io.*;
import java.util.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.Clock;

public class HW6 {

    private static ArrayList<String> aList = new ArrayList<String>();
    private static long bubbleTime;
    private static long selectionTime;
    private static long mergeTime;
    private static long javaTime;
    private static long javaclockTime;
    private static long bubbleclockTime;
    private static long selectionclockTime;
    private static long mergeclockTime;
    
    public static void main(String []args) throws Exception {
    
    Scanner userInput = new Scanner(System.in);
     
      try {
         System.out.print("Enter a file name: ");   
         String getfilename = userInput.nextLine();
         BufferedReader bufReader = new BufferedReader(new FileReader(getfilename));
                 
         PrintWriter pw = new PrintWriter ("P5Output.txt");
         
         BufferedWriter bufWriter = null;
         bufWriter = new BufferedWriter(pw);
          
         String line;
         
        while ((line = bufReader.readLine()) != null) {
            String[] words = line.split(" ");
            line = line.replaceAll("[\\p{P}\\d]", " ");
            for (String word: words) {
                aList.add(word.toLowerCase());
                
            }
        }
           
    long start;
    long end;
    long clockStart;
    long clockEnd;
      
    Clock clock = Clock.systemDefaultZone();    
    start = System.nanoTime();
    clockStart = clock.millis();
    Collections.sort(intList);
    end = System.nanoTime();
    clockEnd = clock.millis();
    javaTime = ((end - start) / 100000);
    javaclockTime = (clockEnd - clockStart);
    //System.out.println(aList);
    //System.out.println(javaTime + "ms ");
    Collections.shuffle(aList);
    
    start = System.nanoTime();
    clockStart = clock.millis();
    BubbleSort.bubblesort(aList);
    end = System.nanoTime();
    clockEnd = clock.millis();
    bubbleTime = ((end - start) / 100000);
    bubbleclockTime = (clockEnd - clockStart);
    //System.out.println(bubbleTime + "ms ");
    //System.out.println(aList);
    Collections.shuffle(aList);
    
    start = System.nanoTime();
    clockStart = clock.millis();
    SelectionSort.selectionsort(aList);
    end = System.nanoTime();
    clockEnd = clock.millis();
    selectionTime = ((end - start) / 100000);
    selectionclockTime = (clockEnd - clockStart); 
    //System.out.println(selectionTime + "ms ");
    //System.out.println(aList);
    Collections.shuffle(aList);
    
    start = System.nanoTime();
    clockStart = clock.millis();
    MergeSort.mergeSort(aList);
    clockEnd = clock.millis();
    end = System.nanoTime();
    mergeclockTime = (clockEnd - clockStart);
    mergeTime = ((end - start) / 100000);
    //System.out.println(aList);
    //System.out.println(mergeTime + "ms ");
    
    writeToFile(bufWriter, aList);
      
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
    
  public static void writeToFile (BufferedWriter bufWriter, ArrayList aList) throws Exception {
  
         bufWriter.write("Number of elements " + aList.size());
         bufWriter.newLine(); 
         bufWriter.write("Java Array List time " + javaTime + " ms for CPU");
         bufWriter.newLine();
         bufWriter.write("Java Array List wall clock time " + javaclockTime + " ms");
         bufWriter.newLine();
         bufWriter.write("Bubble sort time to sort " + bubbleTime + " ms for CPU");
         bufWriter.newLine();
         bufWriter.write("Bubble sort wall clock time " + bubbleclockTime + " ms");
         bufWriter.newLine();
         bufWriter.write("Selection sort time to sort " + selectionTime + " ms for CPU");
         bufWriter.newLine();
         bufWriter.write("Selection sort wall clock time " + selectionclockTime + " ms");
         bufWriter.newLine();
         bufWriter.write("Merge sort time to sort " + mergeTime + " ms for CPU");
         bufWriter.newLine();
         bufWriter.write("Merge sort wall clock time " + mergeclockTime + " ms");
  }
 }
   class BubbleSort {
 
        public static void bubblesort(ArrayList<String> words) {
 
            for (int k = 1; k < words.size(); k++) {
        
                for (int i = 0; i < words.size() - 1; i++) {
                    if (words.get(i).compareTo(words.get(i + 1)) > 0) {
                        String temp = words.get(i);
                        words.set(i,words.get(i+1));
                        words.set(i+1, temp);
                        
                    } 
                }
            }
            //  for(int i=0;i<words.size();i++) {
//              System.out.print(words.get(i) + " ");
//              }
       }
 
 } 


    class SelectionSort {
    
        public static void selectionsort(ArrayList<String> words) {
        
            for (int i = 0; i < words.size(); i++) {
            int minIndex = i;
      
                for (int j = i + 1; j < words.size(); j++) {
        
                     if (words.get(j).compareTo(words.get(minIndex)) < 0) {
                        minIndex = j;
          
                     }
                }
      
                        String temp = words.get(i);
                        words.set(i, words.get(minIndex));
                        words.set(minIndex, temp);
            }
             // for(int i=0;i<words.size();i++) {
//             System.out.print(words.get(i) + " ");
//              }
        }   
        
     }
     
 class MergeSort {
        
        
   private ArrayList<String> strList;
 
    // Constructor
   public MergeSort(ArrayList<String> aList) {
      strList = aList;
   }
     
   public void sort() {
      strList = mergeSort(strList);
   }
 
   public static ArrayList<String> mergeSort(ArrayList<String> whole) {
      ArrayList<String> left = new ArrayList<String>();
      ArrayList<String> right = new ArrayList<String>();
      int center;
   
      if (whole.size() == 1) {    
         return whole;
      } else {
         center = whole.size()/2;
            // copy the left half of whole into the left.
         for (int i=0; i<center; i++) {
            left.add(whole.get(i));
         }
      
            //copy the right half of whole into the new arraylist.
         for (int i=center; i<whole.size(); i++) {
            right.add(whole.get(i));
         }
      
            // Sort the left and right halves of the arraylist.
         left  = mergeSort(left);
         right = mergeSort(right);
      
            // Merge the results back together.
         merge(left, right, whole);
      }
      return whole;
   }
 
   private static void merge(ArrayList<String> left, ArrayList<String> right, ArrayList<String> whole) {
      int leftIndex = 0;
      int rightIndex = 0;
      int wholeIndex = 0;
   
        // As long as neither the left nor the right ArrayList has
        // been used up, keep taking the smaller of left.get(leftIndex)
        // or right.get(rightIndex) and adding it at both.get(bothIndex).
      while (leftIndex < left.size() && rightIndex < right.size()) {
         if ( (left.get(leftIndex).compareTo(right.get(rightIndex))) < 0) {
            whole.set(wholeIndex, left.get(leftIndex));
            leftIndex++;
         } else {
            whole.set(wholeIndex, right.get(rightIndex));
            rightIndex++;
         }
         wholeIndex++;
      }
   
      ArrayList<String> rest;
      int restIndex;
      if (leftIndex >= left.size()) {
            // The left ArrayList has been use up...
         rest = right;
         restIndex = rightIndex;
      } else {
            // The right ArrayList has been used up...
         rest = left;
         restIndex = leftIndex;
      }
   
        // Copy the rest of whichever ArrayList (left or right) was not used up.
      for (int i=restIndex; i<rest.size(); i++) {
         whole.set(wholeIndex, rest.get(i));
         wholeIndex++;
      }
   
  }

 
}
   