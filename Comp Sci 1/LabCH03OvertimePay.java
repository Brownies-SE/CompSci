import java.util.Scanner;

    public class LabCH03OvertimePay{
    
        public static void main(String []args){
        Scanner scnr = new Scanner(System.in);
        
        double basePay = 12.50; //base pay
        double timeHalf = 18.75;//41-50 pay
        double doublePay = 25.00;//51+ pay
        double overTimeHours;//41-50 overtime hours
        double overTimeDoubleHours;//51+ overtime hours
        double hours;// hours worked
        final double weekLimit = 40;// limit of normal pay
        
        System.out.print("Enter hours worked: ");
        hours = scnr.nextInt();
        
        if (hours <= 40){
            System.out.println(basePay * hours);
        }
        else if (hours <= 50){
            overTimeHours = hours - weekLimit;
            hours = (((basePay * weekLimit) + (timeHalf * overTimeHours)));
            System.out.println("Pay is: " + hours);
           } 
        else if (hours > 50){
            overTimeDoubleHours = (hours - weekLimit) - 10;
            overTimeHours = (hours - weekLimit) - overTimeDoubleHours;
            hours = ((((basePay * weekLimit) + (timeHalf * overTimeHours) + (doublePay * overTimeDoubleHours)))); 
        
            System.out.println("Pay is: " + hours);
            
        
     }
            
         
        
  }
    
    
}