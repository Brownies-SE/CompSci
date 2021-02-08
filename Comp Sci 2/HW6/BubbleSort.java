import java.util.ArrayList;

public class BubbleSort {

    private ArrayList<String> words;
    
    public BubbleSort(ArrayList<String> words) {
        this.words = words;
    
    }
    
    public void sort() {
        //Bubble sort here

        
         for (int i = 0; i < words.size() - 1; i++) {
             if (words.get(i).compareTo(words.get(i + 1)) > 0) {
                    String temp = words.set(i, temp);
                    words.get(i) = words.set(i + 1, temp);
                    words.get(i + 1) = temp;
                        
             } 
         }
    }
    
    public ArrayList<String> getArrayList() {
        return words;
    }
   
  
    
}