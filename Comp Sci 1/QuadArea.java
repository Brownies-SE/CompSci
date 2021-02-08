import java.util.Scanner;

    public class QuadArea {
    
        public static void main(String []args){
        Scanner scnr = new Scanner(System.in);
        
        double x1; double y1; double x2; double y2; double x3; double y3; double x4; double y4;
       
        
        
        System.out.println("Enter x value: ");
        x1 = scnr.nextDouble();
        
        System.out.println("Enter y value: ");
        y1 = scnr.nextDouble();
        
        System.out.println("Enter x value: ");
        x2 = scnr.nextDouble();
        
        System.out.println("Enter y value: ");
        y2 = scnr.nextDouble();
        
        System.out.println("Enter x value: ");
        x3 = scnr.nextDouble();
        
        System.out.println("Enter y value: ");
        y3 = scnr.nextDouble();
        
        System.out.println("Enter x value: ");
        x4 = scnr.nextDouble();
        
        System.out.println("Enter y value: ");
        y4 = scnr.nextDouble();
        
        
        
        System.out.println((x1 * y2 - x2 * y1 + x2 * y3 - x3 * y2 + x3 * y4 - x4 * y3 + x4 * y1 - x1 * y4) * 1 / 2);
        }
    
    
    }