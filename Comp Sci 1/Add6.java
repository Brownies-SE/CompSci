/* Josh Brown
This program uses 2 variables to grab 
6 numbers and add their totals */


import java.util.Scanner;

    public class Add6 {
    
        public static void main(String []args){
        Scanner scnr = new Scanner(System.in);
        
        int input;
        int sum;
        
        System.out.println("Enter a number: ");
        input = scnr.nextInt();
        
        sum = input;
        
        System.out.println("Enter a number: ");
        input = scnr.nextInt();
        
        sum  = input  + sum;
        
        System.out.println("Enter a number: ");
        input = scnr.nextInt();
        
        sum = input+ sum; 
        
        System.out.println("Enter a number: ");
        input = scnr.nextInt();
        
        sum = input+ sum;
        
        
        System.out.println("Enter a number: ");
        input = scnr.nextInt();
        
        sum = input+ sum;
        
        
        System.out.println("Enter a number: ");
        input = scnr.nextInt();
        
        sum = input+ sum;
        
        
        System.out.println("total: " + sum);
       
        
        }
    
    }