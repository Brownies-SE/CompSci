/*Josh Brown
This program takes 3 inputs, pay, savings rate, and ira rate.
using those inputs it calculates the savings, IRA, and
the total */


import java.util.Scanner;

    public class Savings{
    
        public static void main(String []args){
        Scanner scnr = new Scanner(System.in);
        //Declaring variables
        double grossPay;
        double savingsRate;
        double iraRate;
        double total;
        //printing and storing in grosspay
        System.out.println("Enter your gross pay: ");
        grossPay = scnr.nextDouble();
        //printing and storing in savingsRate
        System.out.println("Enter savings rate: ");
        savingsRate = scnr.nextDouble();
        //printing and storing in irarate
        System.out.println("Enter IRA rate: ");
        iraRate = scnr.nextDouble();
        //math to figure numbers
        savingsRate = grossPay * savingsRate / 100;
        iraRate = grossPay * iraRate / 100;
        total = savingsRate + iraRate;
         
        //printing
        System.out.println("Amount to savings: " + savingsRate);
        System.out.println("Amount to IRA: " + iraRate);
        System.out.println("Total saving and IRA: " + total);
        
        }
    
    
    }