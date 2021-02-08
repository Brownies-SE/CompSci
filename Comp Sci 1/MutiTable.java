import java.util.Scanner;
public class MultiplicationTable

{

    public static void main(String[] args)

    {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter a number to generate a multiplication table: ");

        int number = input.nextInt();
        int rows = number;

        int columns = number;

         

        for(int i=0;i<=rows;i++)

       {

            System.out.print(i* rows + " ");

        }
           for (int j=0; j<=columns; j++)

           {

               System.out.println(j*columns + " ");

            }

    }
    }
