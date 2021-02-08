import java.util.ArrayList;

public class SelectionSort {

    private ArrayList<String> stuff;
    
    public SelectionSort(ArrayList<String> stuff) {
        this.stuff = stuff;
    
    }
    
    public void sort() {
        
    
        for (int i = 0; i < stuff.size(); i++) {
            int minIndex = i;
      
                for (int j = i + 1; j < stuff.size(); j++) {
        
                     if (stuff.get(j).compareTo(stuff.get(minIndex)) < 0) {
                        minIndex = j;
          
                     }
                }
      
                        String temp = stuff.get(i);
                        stuff.set(i, stuff.get(minIndex));
                        stuff.set(minIndex, temp);
        }
    }
    
    
    
    
    public ArrayList<String> getArrayList() {
        return stuff;
    }
   
  
    
}