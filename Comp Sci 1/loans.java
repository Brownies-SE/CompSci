import java.util.Scanner;

    public class loans{
    
        public static void main(String []args){
        Scanner scnr = new Scanner(System.in);
        
        boolean hasGoodCredit;
        boolean hasEnoughIncome;
        boolean hasLowDebt;
        int annualIncome;
        int loanAmount;
        int creditRating;
        int totalAmount;
        int numJuan = 700;
        int creditRatingTwo = 750;
        int numTwo = 75000;
        int numThree = 10000;
        int loanAmountTwo;
        String name;
        
        System.out.println("Welcome to your loan program");
        
        System.out.print("Enter your name: ");
        name = scnr.nextLine();
        
        System.out.print("What loan amount: ");
        loanAmount = scnr.nextInt();
        
        System.out.print("Enter credit rating: ");
        creditRating = scnr.nextInt();
        
        System.out.print("Enter annual income: ");
        annualIncome = scnr.nextInt();
        
        System.out.print("Enter the total amount of debt: ");
        totalAmount = scnr.nextInt();
        
        hasGoodCredit = (creditRating >= numJuan);
        hasEnoughIncome = (annualIncome >= numTwo);
        hasLowDebt = (totalAmount <= numThree);
        
        System.out.println(hasGoodCredit);
        System.out.println(hasEnoughIncome);
        System.out.println(hasLowDebt);
        loanAmountTwo = loanAmount * 2;
        
        
        if (hasGoodCredit && hasEnoughIncome && hasLowDebt){
        System.out.println(name + ",you qualify for a loan");
        }
        else if ((annualIncome > loanAmountTwo) && (creditRating > numJuan)){
        System.out.println(name + ",you qualify for a loan");    
        }
        else if((annualIncome < numThree)  && (creditRating > creditRatingTwo)){
        System.out.println(name + ",you qualify for a loan");
        } 
        else if (hasGoodCredit == false && hasEnoughIncome == false && hasLowDebt == false);
        System.out.println(name + ", you do not qualify for a loan");
        
       

     
     
     
        }
    }