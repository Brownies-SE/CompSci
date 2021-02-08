import java.util.Scanner;

public class DividebyTwo {

    public static void main(String []args){
    
    int num;
    int count = 0;
    
    Scanner keys = new Scanner(System.in);
    System.out.print("Enter Number: ");
    num = keys.nextInt();
    
    while (num >= 2) {
        num = num - 2;
        count++;
       }
       
       System.out.println(count);
    
    }
}