import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.io.BufferedWriter;
import java.io.IOException;


public class extraCredits {

    private static ArrayList<String> aList = new ArrayList<String>();
    private static ArrayList<Integer> intList = new ArrayList<>();
    //private static long bubbleTime;
    private static ArrayList<Long> bubbleTimes = new ArrayList<>();
    //private static long selectionTime;
    private static ArrayList<Long> selectionTimes = new ArrayList<>();
    //private static long javaTime;
    private static ArrayList<Long> javaTimes = new ArrayList<>();
    //private static long quickTime;
    private static ArrayList<Long> quickTimes = new ArrayList<>();

    public static void main(String []args) throws Exception {
    
    Scanner userInput = new Scanner(System.in);
    

    sortAll();
         

    
    }
    
    public static void sortAll(){
      String ext = ".txt";
      String[] textFiles = {"R1000" + ext, "R4000" + ext, "R8000" + ext, "R16000" + ext,};
      
      long start;
      long end;
      
      for(String fileName : textFiles){
         
         try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            PrintWriter pw = new PrintWriter ("EX1.out");
            
            BufferedWriter bw = null;
            bw = new BufferedWriter(pw);
            
            String line;
            while((line = br.readLine()) != null){
               String[] nums = line.split("\\s+");
               for(String numbers : nums){
                  numbers = numbers.replaceAll("[^\\d]", "");
                  //aList.add(numbers);
                  if (numbers != null && !numbers.isEmpty()) {
                     aList.add(numbers);
                     intList.add(Integer.valueOf(numbers));
                  }
               
               }//end for
               
            }//end while
               
               start = System.nanoTime();
               new QuickSort(intList).sort();
               end = System.nanoTime();
               quickTimes.add((end - start) / 10000000);
               Collections.shuffle(aList); 
               
               start = System.nanoTime();
               bubbleSort.bubblesort(aList);
               end = System.nanoTime();
               bubbleTimes.add((end - start) / 10000000);
               Collections.shuffle(aList);
         
               start = System.nanoTime();
               SelectionSort.selectionsort(aList);
               end = System.nanoTime();
               selectionTimes.add((end - start) / 10000000);
               Collections.shuffle(aList);
               
               start = System.nanoTime();
               Collections.sort(intList);
               end = System.nanoTime();
               javaTimes.add((end - start) / 10000000);
               Collections.shuffle(intList);
               
               
               
               br.close();
               timing(bw, aList, intList);
               bw.close();
            
         }catch(Exception e){
            e.printStackTrace();
         }finally{
         }//end try catch finally
      }//end for each
      
      
      //output should be done here.
      
      // for (long bubTime : bubbleTimes) {
//          System.out.println(bubTime + "ms ");
//       }  
//       for(long time : selectionTimes){
//          System.out.println(time + "ms ");
//       }
//       for (long javTime : javaTimes) {
//         System.out.println(javTime + "ms ");
//       }
     //  for(long qtime : QuickTimes) {
//         System.out.println("Hello");
//       }
     
      
      
    }//end sort all


    public static void timing(BufferedWriter bw, ArrayList aList, ArrayList intList) throws Exception  {
    
    
        bw.write("              " + " 1000  " + "   4000  " + "    8000 " + "       16000 ");
        bw.newLine();
        bw.write("Quicksort    ");
        for (long qTime : quickTimes) {
        bw.write(qTime + " ms       ");
        }
        bw.newLine();
        bw.write("Selection     ");
        for(long time : selectionTimes){
        bw.write(time + " ms    ");
        }
        bw.newLine();
        bw.write("Java          ");
        for (long javTime : javaTimes) {
        bw.write(javTime + " ms      ");
        }
        bw.newLine();
        bw.write("Bubble        ");
        for (long bubTime : bubbleTimes) {
        bw.write(bubTime + " ms    ");
        }
        
    }
}

    class bubbleSort {
    
        public static void bubblesort(ArrayList<String> aList) {
        
        
        for (int j = 1; j < aList.size(); j++) {
            for (int i = 0; i < aList.size() - 1; i++) {
                //if (aList.get(i).compareTo(aList.get(i + 1)) > 0) { 
                if(Integer.valueOf(aList.get(i)) > Integer.valueOf(aList.get(i + 1))){
                 
                    
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
        
                     //if (aList.get(j).compareTo(aList.get(minIndex)) < 0) {
                     if(Integer.valueOf(aList.get(j)) < Integer.valueOf(aList.get(minIndex))){
                     
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
    
     private ArrayList<Integer> strList;
      
     public QuickSort(ArrayList<Integer> aList) {
        strList = aList;
    }

    public void sort() {
    
        sort_r(0, strList.size()-1);
    }

    private void sort_r(int low, int high) {
        if (low < high) {
        
            int find = partition(low, high);

            sort_r(low, find - 1);
            sort_r(find + 1, high);
        }
    }

    private int partition(int low, int high) {
    
            int pivot = strList.get(high); // let's pick the highest as our pivot
        int i = low;

        for (int j = low; j < high; j++) {
        
            if (strList.get(j) <= pivot)
                Collections.swap(strList, i++, j);
        }
        // swap i and pivot
        Collections.swap(strList, i, high);
        return i;
    }
}
    
    
    