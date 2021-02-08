import java.util.Scanner;

    public class LabCh03ATM1{
        public static void main(String []args){
        Scanner scnr = new Scanner(System.in);
        
        final int numberPin = 3891;
        int inputPin;
        int transChoiceOne;
        double startBalance = 1000;
        double depositAmount;
        double withdrawAmount;
        
        System.out.print("Please enter your PIN: ");
        inputPin = scnr.nextInt();
        
        if (inputPin != numberPin) {
            System.out.println("WTF is wrong with you? ");
            System.exit(0);
        }
        else  
            System.out.println("1: Deposit ");
            System.out.println("2: Withdraw ");
            System.out.println("3: Inquiry ");
            System.out.println("What is your transaction choice: ");
            transChoiceOne = scnr.nextInt();
       
        if (transChoiceOne == 1) 
            System.out.print("How much would you like to deposit: ");
            depositAmount = scnr.nextDouble();
            
        if (depositAmount > 0)
            System.out.println("Your new amount: " + (depositAmount + startBalance));
            
 
        if (depositAmount <= 0)
            System.out.println("C'mon really? ");
            System.exit(0);
            
        if (transChoiceOne == 2)
            System.out.print("How ");
            withdrawAmount = scnr.nextDouble();
            System.out.println(withdrawAmount);
            
            
        
                   
        
        
        
        
        
        
       
        
        
        
        
        
        
        }
  }