import java.util.Scanner;
   public class Arrays{
      public static void main(String []args){
      
      Scanner input = new Scanner(System.in);
      
      System.out.println("How many numbers would you like to enter?");
      int num = input.nextInt();
      
      int array[] = new int[num];
      
      System.out.println("Enter the " + num + " numbers now:");
      
      for(int i = 0; i< array.length; i++){
         array[i] = input.nextInt();
      }   
      System.out.println("You entered these numbers: ");
         printArray(array);
      }   
         
      public static void printArray(int arr[]){
         int news = arr.length;
         
      for(int i = 0; i < news; i++){
      System.out.println(arr[i] + " "); 
      }
     }
        
      /*public static int getMinValue(int arr[]){
         int minValue = arr[0];
      for(int i = 1;i < arr.length; i++){
      if(arr[i] < minValue){
	      minValue = arr[i];
	}
  }
       return minValue;
       
}    
      
      System.out.println(array[i] + " ");
      
      
      */
      
      
      }
   
 