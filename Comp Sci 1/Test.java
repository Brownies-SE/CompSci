import java.util.Scanner;

class Test {

    public static void main(String args[]) {

        Scanner s = new Scanner(System.in);
        int a[] = new int[100];
        int n, l, k = 0;

        System.out.print("Enter Size of an Array :");
        n = s.nextInt();

        System.out.print("\nEnter Array Elements :");
        for (int i = 0; i < n; i++) {
            a[i] = s.nextInt();
        }

        System.out.print("\nEnter Number for Frequency :");
        l = s.nextInt();

        for (int i = 0; i < n; i++) {
            if (l == a[i]) {
                k++;
            }
        }

        System.out.println("\nNumber Of Frequency :" + k);

    }
}