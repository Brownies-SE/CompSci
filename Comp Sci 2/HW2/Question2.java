import java.io.*;
import java.util.*;
import java.io.BufferedWriter;
 
 
public class Question2 {
    
    private static int [] counterArray = new int[40000];
    private static String [] unique = new String[40000];
    private static String [] splited = new String[40000];
    private static String line; 
     
    public static void main(String[] args) throws Exception {
       
       
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
       /*catch(FileNotFoundException ex) {
       
           System.out.println("Error: It must be a txt file '" +  filename + "'");                
       }*/
       catch(IOException e) {
       
           System.out.println("Error reading file '" + filename + "'");                   
       }
           
   }
   
    public static void fileImport(BufferedReader br) throws Exception {
     
      int k = 0;
      String [] temp = new String[50];
      String line = null;
      
         while((line = in.readLine()) != null){
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